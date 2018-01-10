package com.mobian.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mobian.absx.F;
import com.mobian.pageModel.Colum;
import com.mobian.pageModel.FdBalanceLog;
import com.mobian.pageModel.DataGrid;
import com.mobian.pageModel.Json;
import com.mobian.pageModel.PageHelper;
import com.mobian.service.FdBalanceLogServiceI;

import com.mobian.util.Constants;
import com.mobian.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

/**
 * FdBalanceLog管理控制器
 * 
 * @author John
 * 
 */
@Controller
@RequestMapping("/fdBalanceLogController")
public class FdBalanceLogController extends BaseController {

	@Autowired
	private FdBalanceLogServiceI fdBalanceLogService;


	/**
	 * 跳转到FdBalanceLog管理页面
	 * 
	 * @return
	 */
	@RequestMapping("/manager")
	public String manager(Integer userId, HttpServletRequest request) {
		request.setAttribute("userId", userId);
		return "/fdbalancelog/fdBalanceLog";
	}

	/**
	 * 获取FdBalanceLog数据表格
	 * 
	 * @param user
	 * @return
	 */
	@RequestMapping("/dataGrid")
	@ResponseBody
	public DataGrid dataGrid(FdBalanceLog fdBalanceLog, String createTimeBeginStr, String createTimeEndStr, PageHelper ph) {
		if(!F.empty(createTimeBeginStr)) fdBalanceLog.setCreateTimeStart(DateUtil.parse(createTimeBeginStr, Constants.DATE_FORMAT).getTime());
		if(!F.empty(createTimeEndStr)) fdBalanceLog.setCreateTimeEnd(DateUtil.parse(createTimeEndStr, Constants.DATE_FORMAT).getTime());

		return fdBalanceLogService.dataGrid(fdBalanceLog, ph);
	}
	/**
	 * 获取FdBalanceLog数据表格excel
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
	public void download(FdBalanceLog fdBalanceLog, PageHelper ph,String downloadFields,HttpServletResponse response) throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException, IOException{
		DataGrid dg = dataGrid(fdBalanceLog,null,null,ph);
		downloadFields = downloadFields.replace("&quot;", "\"");
		downloadFields = downloadFields.substring(1,downloadFields.length()-1);
		List<Colum> colums = JSON.parseArray(downloadFields, Colum.class);
		downloadTable(colums, dg, response);
	}
	/**
	 * 跳转到添加FdBalanceLog页面
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/addPage")
	public String addPage(HttpServletRequest request) {
		FdBalanceLog fdBalanceLog = new FdBalanceLog();
		return "/fdbalancelog/fdBalanceLogAdd";
	}

	/**
	 * 添加FdBalanceLog
	 * 
	 * @return
	 */
	@RequestMapping("/add")
	@ResponseBody
	public Json add(FdBalanceLog fdBalanceLog) {
		Json j = new Json();		
		fdBalanceLogService.add(fdBalanceLog);
		j.setSuccess(true);
		j.setMsg("添加成功！");		
		return j;
	}

	/**
	 * 跳转到FdBalanceLog查看页面
	 * 
	 * @return
	 */
	@RequestMapping("/view")
	public String view(HttpServletRequest request, Integer id) {
		FdBalanceLog fdBalanceLog = fdBalanceLogService.get(id);
		request.setAttribute("fdBalanceLog", fdBalanceLog);
		return "/fdbalancelog/fdBalanceLogView";
	}

	/**
	 * 跳转到FdBalanceLog修改页面
	 * 
	 * @return
	 */
	@RequestMapping("/editPage")
	public String editPage(HttpServletRequest request, Integer id) {
		FdBalanceLog fdBalanceLog = fdBalanceLogService.get(id);
		request.setAttribute("fdBalanceLog", fdBalanceLog);
		return "/fdbalancelog/fdBalanceLogEdit";
	}

	/**
	 * 修改FdBalanceLog
	 * 
	 * @param fdBalanceLog
	 * @return
	 */
	@RequestMapping("/edit")
	@ResponseBody
	public Json edit(FdBalanceLog fdBalanceLog) {
		Json j = new Json();		
		fdBalanceLogService.edit(fdBalanceLog);
		j.setSuccess(true);
		j.setMsg("编辑成功！");		
		return j;
	}

	/**
	 * 删除FdBalanceLog
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public Json delete(Integer id) {
		Json j = new Json();
		fdBalanceLogService.delete(id);
		j.setMsg("删除成功！");
		j.setSuccess(true);
		return j;
	}

}
