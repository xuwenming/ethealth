package com.mobian.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mobian.pageModel.Colum;
import com.mobian.pageModel.FdMessage;
import com.mobian.pageModel.DataGrid;
import com.mobian.pageModel.Json;
import com.mobian.pageModel.PageHelper;
import com.mobian.service.FdMessageServiceI;

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
		return fdMessageService.dataGrid(fdMessage, ph);
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
	public Json add(FdMessage fdMessage) {
		Json j = new Json();		
		fdMessageService.add(fdMessage);
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
		fdMessageService.edit(fdMessage);
		j.setSuccess(true);
		j.setMsg("编辑成功！");		
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
