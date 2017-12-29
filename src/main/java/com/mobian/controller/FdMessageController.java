package com.mobian.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mobian.absx.F;
import com.mobian.pageModel.*;
import com.mobian.service.FdMessageServiceI;

import com.mobian.util.ConfigUtil;
import com.mobian.util.Constants;
import com.mobian.util.DateUtil;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

/**
 * FdMessage管理控制器
 * 
 * @author John
 * 
 */
@Controller
@RequestMapping("/fdMessageController")
public class FdMessageController extends BaseController {

	@Autowired
	private FdMessageServiceI fdMessageService;


	/**
	 * 跳转到FdMessage管理页面
	 * 
	 * @return
	 */
	@RequestMapping("/manager")
	public String manager(HttpServletRequest request) {
		return "/fdmessage/fdMessage";
	}

	/**
	 * 获取FdMessage数据表格
	 * 
	 * @param user
	 * @return
	 */
	@RequestMapping("/dataGrid")
	@ResponseBody
	public DataGrid dataGrid(FdMessage fdMessage, PageHelper ph) {
		if(F.empty(fdMessage.getMtype())) fdMessage.setMtype("MT01,MT03");
		DataGrid dg = fdMessageService.dataGrid(fdMessage, ph);
		List<FdMessage> list = dg.getRows();
		if(CollectionUtils.isNotEmpty(list)) {
			for(FdMessage message : list) {
				if(message.getStartDate() != null) message.setStartDateStr(DateUtil.format(message.getStartDate(), Constants.DATE_FORMAT_YMD));
				if(message.getEndDate() != null) message.setEndDateStr(DateUtil.format(message.getEndDate(), Constants.DATE_FORMAT_YMD));
			}
		}
		return dg;
	}
	/**
	 * 获取FdMessage数据表格excel
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
	public void download(FdMessage fdMessage, PageHelper ph,String downloadFields,HttpServletResponse response) throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException, IOException{
		DataGrid dg = dataGrid(fdMessage,ph);		
		downloadFields = downloadFields.replace("&quot;", "\"");
		downloadFields = downloadFields.substring(1,downloadFields.length()-1);
		List<Colum> colums = JSON.parseArray(downloadFields, Colum.class);
		downloadTable(colums, dg, response);
	}
	/**
	 * 跳转到添加FdMessage页面
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/addPage")
	public String addPage(HttpServletRequest request) {
		FdMessage fdMessage = new FdMessage();
		return "/fdmessage/fdMessageAdd";
	}

	/**
	 * 添加FdMessage
	 * 
	 * @return
	 */
	@RequestMapping("/add")
	@ResponseBody
	public Json add(FdMessage fdMessage, HttpSession session) {
		Json j = new Json();
		if(!F.empty(fdMessage.getStartDateStr())) {
			fdMessage.setStartDate(DateUtil.parse(fdMessage.getStartDateStr(), Constants.DATE_FORMAT_YMD));
		}
		if(!F.empty(fdMessage.getEndDateStr())) {
			fdMessage.setEndDate(DateUtil.parse(fdMessage.getEndDateStr(), Constants.DATE_FORMAT_YMD));
		}

		fdMessageService.addAndPushMessage(fdMessage);
		j.setSuccess(true);
		j.setMsg("添加成功！");		
		return j;
	}

	/**
	 * 跳转到FdMessage查看页面
	 * 
	 * @return
	 */
	@RequestMapping("/view")
	public String view(HttpServletRequest request, Integer id) {
		FdMessage fdMessage = fdMessageService.get(id);
		if(fdMessage.getStartDate() != null) fdMessage.setStartDateStr(DateUtil.format(fdMessage.getStartDate(), Constants.DATE_FORMAT_YMD));
		if(fdMessage.getEndDate() != null) fdMessage.setEndDateStr(DateUtil.format(fdMessage.getEndDate(), Constants.DATE_FORMAT_YMD));
		request.setAttribute("fdMessage", fdMessage);
		return "/fdmessage/fdMessageView";
	}

	/**
	 * 跳转到FdMessage修改页面
	 * 
	 * @return
	 */
	@RequestMapping("/editPage")
	public String editPage(HttpServletRequest request, Integer id) {
		FdMessage fdMessage = fdMessageService.get(id);
		if(fdMessage.getStartDate() != null) fdMessage.setStartDateStr(DateUtil.format(fdMessage.getStartDate(), Constants.DATE_FORMAT_YMD));
		if(fdMessage.getEndDate() != null) fdMessage.setEndDateStr(DateUtil.format(fdMessage.getEndDate(), Constants.DATE_FORMAT_YMD));
		request.setAttribute("fdMessage", fdMessage);
		return "/fdmessage/fdMessageEdit";
	}

	/**
	 * 修改FdMessage
	 * 
	 * @param fdMessage
	 * @return
	 */
	@RequestMapping("/edit")
	@ResponseBody
	public Json edit(FdMessage fdMessage) {
		Json j = new Json();
		if(!F.empty(fdMessage.getStartDateStr())) {
			fdMessage.setStartDate(DateUtil.parse(fdMessage.getStartDateStr(), Constants.DATE_FORMAT_YMD));
		}
		if(!F.empty(fdMessage.getEndDateStr())) {
			fdMessage.setEndDate(DateUtil.parse(fdMessage.getEndDateStr(), Constants.DATE_FORMAT_YMD));
		}
		fdMessageService.edit(fdMessage);
		j.setSuccess(true);
		j.setMsg("编辑成功！");		
		return j;
	}

	/**
	 * 推送消息
	 *
	 * @param fdMessage
	 * @return
	 */
	@RequestMapping("/editAndPushMessage")
	@ResponseBody
	public Json editAndPushMessage(FdMessage fdMessage) {
		Json j = new Json();
		boolean flag = fdMessageService.editAndPushMessage(fdMessage);
		j.setSuccess(true);
		j.setMsg(flag ? "推送成功！" : "推送失败！");
		return j;
	}

	/**
	 * 删除FdMessage
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public Json delete(Integer id) {
		Json j = new Json();
		fdMessageService.delete(id);
		j.setMsg("删除成功！");
		j.setSuccess(true);
		return j;
	}

}
