package com.mobian.controller;

import com.alibaba.fastjson.JSON;
import com.mobian.absx.F;
import com.mobian.concurrent.CacheKey;
import com.mobian.concurrent.CompletionService;
import com.mobian.concurrent.Task;
import com.mobian.listener.Application;
import com.mobian.pageModel.*;
import com.mobian.service.FdMemberServiceI;
import com.mobian.service.FdWithdrawLogServiceI;
import com.mobian.service.UserServiceI;
import com.mobian.service.impl.CompletionFactory;
import com.mobian.util.ConfigUtil;
import com.mobian.util.Constants;
import com.mobian.util.MD5Util;
import com.mobian.util.RSAUtil;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 * FdWithdrawLog管理控制器
 * 
 * @author John
 * 
 */
@Controller
@RequestMapping("/fdWithdrawLogController")
public class FdWithdrawLogController extends BaseController {

	@Autowired
	private FdWithdrawLogServiceI fdWithdrawLogService;

	@Autowired
	private FdMemberServiceI fdMemberService;

	@Autowired
	private UserServiceI userService;


	/**
	 * 跳转到FdWithdrawLog管理页面
	 * 
	 * @return
	 */
	@RequestMapping("/manager")
	public String manager(HttpServletRequest request) {
		return "/fdwithdrawlog/fdWithdrawLog";
	}

	/**
	 * 获取FdWithdrawLog数据表格
	 * 
	 * @param user
	 * @return
	 */
	@RequestMapping("/dataGrid")
	@ResponseBody
	public DataGrid dataGrid(FdWithdrawLog fdWithdrawLog, PageHelper ph) {
		if(fdWithdrawLog.getCreateTimeStartDate() != null) fdWithdrawLog.setCreateTimeStart(fdWithdrawLog.getCreateTimeStartDate().getTime());
		if(fdWithdrawLog.getCreateTimeEndDate() != null) fdWithdrawLog.setCreateTimeEnd(fdWithdrawLog.getCreateTimeEndDate().getTime());

		DataGrid dg = fdWithdrawLogService.dataGrid(fdWithdrawLog, ph);
		List<FdWithdrawLog> list = dg.getRows();
		if(CollectionUtils.isNotEmpty(list)) {
			CompletionService completionService = CompletionFactory.initCompletion();
			for(FdWithdrawLog log : list) {
				completionService.submit(new Task<FdWithdrawLog, FdMember>(new CacheKey("fdMember", log.getUserId()), log) {
					@Override
					public FdMember call() throws Exception {
						return fdMemberService.getSimple(Integer.valueOf(getD().getUserId()));
					}

					protected void set(FdWithdrawLog d, FdMember v) {
						if(v != null) {
							d.setUserName(v.getCustomer().getRealName());
							d.setUserMobile(v.getMobile());
						}
					}
				});
				if(!F.empty(log.getHandleLoginId()))
					completionService.submit(new Task<FdWithdrawLog, String>(new CacheKey("user", log.getHandleLoginId()), log) {
						@Override
						public String call() throws Exception {
							return userService.getFromCache(getD().getHandleLoginId()).getName();
						}

						protected void set(FdWithdrawLog d, String v) {
							if(v != null) {
								d.setHandleLoginName(v);
							}
						}
					});
			}
			completionService.sync();
		}
		return dg;
	}
	/**
	 * 获取FdWithdrawLog数据表格excel
	 * 
	 * @param user
	 * @return
	 * @throws NoSuchMethodException 
	 * @throws SecurityException 
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws IllegalArgumentException 
	 * @throws IOException 
	 */
	@RequestMapping("/download")
	public void download(FdWithdrawLog fdWithdrawLog, PageHelper ph,String downloadFields,HttpServletResponse response) throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException, IOException{
		DataGrid dg = dataGrid(fdWithdrawLog,ph);		
		downloadFields = downloadFields.replace("&quot;", "\"");
		downloadFields = downloadFields.substring(1,downloadFields.length()-1);
		List<Colum> colums = JSON.parseArray(downloadFields, Colum.class);
		downloadTable(colums, dg, response);
	}
	/**
	 * 跳转到添加FdWithdrawLog页面
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/addPage")
	public String addPage(HttpServletRequest request) {
		FdWithdrawLog fdWithdrawLog = new FdWithdrawLog();
		return "/fdwithdrawlog/fdWithdrawLogAdd";
	}

	/**
	 * 添加FdWithdrawLog
	 * 
	 * @return
	 */
	@RequestMapping("/add")
	@ResponseBody
	public Json add(FdWithdrawLog fdWithdrawLog) {
		Json j = new Json();		
		fdWithdrawLogService.add(fdWithdrawLog);
		j.setSuccess(true);
		j.setMsg("添加成功！");		
		return j;
	}

	/**
	 * 跳转到FdWithdrawLog查看页面
	 * 
	 * @return
	 */
	@RequestMapping("/view")
	public String view(HttpServletRequest request, Integer id) {
		FdWithdrawLog fdWithdrawLog = fdWithdrawLogService.get(id);
		request.setAttribute("fdWithdrawLog", fdWithdrawLog);
		return "/fdwithdrawlog/fdWithdrawLogView";
	}

	/**
	 * 跳转到FdWithdrawLog修改页面
	 * 
	 * @return
	 */
	@RequestMapping("/editPage")
	public String editPage(HttpServletRequest request, Integer id) {
		FdWithdrawLog fdWithdrawLog = fdWithdrawLogService.get(id);
		request.setAttribute("fdWithdrawLog", fdWithdrawLog);
		return "/fdwithdrawlog/fdWithdrawLogEdit";
	}

	/**
	 * 修改FdWithdrawLog
	 * 
	 * @param fdWithdrawLog
	 * @return
	 */
	@RequestMapping("/edit")
	@ResponseBody
	public Json edit(FdWithdrawLog fdWithdrawLog) {
		Json j = new Json();		
		fdWithdrawLogService.edit(fdWithdrawLog);
		j.setSuccess(true);
		j.setMsg("编辑成功！");		
		return j;
	}

	/**
	 * 删除FdWithdrawLog
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public Json delete(Integer id) {
		Json j = new Json();
		fdWithdrawLogService.delete(id);
		j.setMsg("删除成功！");
		j.setSuccess(true);
		return j;
	}

	/**
	 * 转发至审核页面
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping("/editAuditPage")
	public String editAuditPage(HttpServletRequest request, Integer id) {
		request.setAttribute("id", id);
		return "/fdwithdrawlog/fdWithDrawLogAudit";
	}

	@RequestMapping("/editAudit")
	@ResponseBody
	public Json editAudit(FdWithdrawLog fdWithdrawLog, String checkPwd, HttpSession session, HttpServletRequest request) {
		Json json = new Json();

		// 获取提现充值密码
		String privateKey = (String)session.getAttribute(RSAUtil.PRIVATE_KEY);
		if(F.empty(privateKey)) {
			json.setMsg("操作失败，请刷新或关闭当前浏览器重新打开！");
			return json;
		}
		checkPwd = RSAUtil.decryptByPravite(checkPwd, privateKey);
		String pwd = Application.getString(Constants.CASH_PASSWORD);
		if(F.empty(checkPwd) || (!F.empty(pwd) && !MD5Util.md5(checkPwd).toUpperCase().equals(pwd.toUpperCase()))) {
			json.fail();
			json.setMsg("校验密码错误");
			return json;
		}


		SessionInfo sessionInfo = (SessionInfo) session.getAttribute(ConfigUtil.getSessionInfoName());
		String loginId = sessionInfo.getId();

		fdWithdrawLogService.editAudit(fdWithdrawLog, loginId, request);
		json.success();

		return json;


	}

}
