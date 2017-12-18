package com.mobian.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mobian.pageModel.Colum;
import com.mobian.pageModel.FdMessageReadLog;
import com.mobian.pageModel.DataGrid;
import com.mobian.pageModel.Json;
import com.mobian.pageModel.PageHelper;
import com.mobian.service.FdMessageReadLogServiceI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

/**
 * FdMessageReadLog管理控制器
 * 
 * @author John
 * 
 */
@Controller
@RequestMapping("/fdMessageReadLogController")
public class FdMessageReadLogController extends BaseController {

	@Autowired
	private FdMessageReadLogServiceI fdMessageReadLogService;


	/**
	 * 跳转到FdMessageReadLog管理页面
	 * 
	 * @return
	 */
	@RequestMapping("/manager")
	public String manager(HttpServletRequest request) {
		return "/fdmessagereadlog/fdMessageReadLog";
	}

	/**
	 * 获取FdMessageReadLog数据表格
	 * 
	 * @param user
	 * @return
	 */
	@RequestMapping("/dataGrid")
	@ResponseBody
	public DataGrid dataGrid(FdMessageReadLog fdMessageReadLog, PageHelper ph) {
		return fdMessageReadLogService.dataGrid(fdMessageReadLog, ph);
	}
	/**
	 * 获取FdMessageReadLog数据表格excel
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
	public void download(FdMessageReadLog fdMessageReadLog, PageHelper ph,String downloadFields,HttpServletResponse response) throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException, IOException{
		DataGrid dg = dataGrid(fdMessageReadLog,ph);		
		downloadFields = downloadFields.replace("&quot;", "\"");
		downloadFields = downloadFields.substring(1,downloadFields.length()-1);
		List<Colum> colums = JSON.parseArray(downloadFields, Colum.class);
		downloadTable(colums, dg, response);
	}
	/**
	 * 跳转到添加FdMessageReadLog页面
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/addPage")
	public String addPage(HttpServletRequest request) {
		FdMessageReadLog fdMessageReadLog = new FdMessageReadLog();
		return "/fdmessagereadlog/fdMessageReadLogAdd";
	}

	/**
	 * 添加FdMessageReadLog
	 * 
	 * @return
	 */
	@RequestMapping("/add")
	@ResponseBody
	public Json add(FdMessageReadLog fdMessageReadLog) {
		Json j = new Json();		
		fdMessageReadLogService.add(fdMessageReadLog);
		j.setSuccess(true);
		j.setMsg("添加成功！");		
		return j;
	}

	/**
	 * 跳转到FdMessageReadLog查看页面
	 * 
	 * @return
	 */
	@RequestMapping("/view")
	public String view(HttpServletRequest request, Integer id) {
		FdMessageReadLog fdMessageReadLog = fdMessageReadLogService.get(id);
		request.setAttribute("fdMessageReadLog", fdMessageReadLog);
		return "/fdmessagereadlog/fdMessageReadLogView";
	}

	/**
	 * 跳转到FdMessageReadLog修改页面
	 * 
	 * @return
	 */
	@RequestMapping("/editPage")
	public String editPage(HttpServletRequest request, Integer id) {
		FdMessageReadLog fdMessageReadLog = fdMessageReadLogService.get(id);
		request.setAttribute("fdMessageReadLog", fdMessageReadLog);
		return "/fdmessagereadlog/fdMessageReadLogEdit";
	}

	/**
	 * 修改FdMessageReadLog
	 * 
	 * @param fdMessageReadLog
	 * @return
	 */
	@RequestMapping("/edit")
	@ResponseBody
	public Json edit(FdMessageReadLog fdMessageReadLog) {
		Json j = new Json();		
		fdMessageReadLogService.edit(fdMessageReadLog);
		j.setSuccess(true);
		j.setMsg("编辑成功！");		
		return j;
	}

	/**
	 * 删除FdMessageReadLog
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public Json delete(Integer id) {
		Json j = new Json();
		fdMessageReadLogService.delete(id);
		j.setMsg("删除成功！");
		j.setSuccess(true);
		return j;
	}

}
