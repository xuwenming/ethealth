package com.mobian.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mobian.pageModel.Colum;
import com.mobian.pageModel.FdCashLog;
import com.mobian.pageModel.DataGrid;
import com.mobian.pageModel.Json;
import com.mobian.pageModel.PageHelper;
import com.mobian.service.FdCashLogServiceI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

/**
 * FdCashLog管理控制器
 * 
 * @author John
 * 
 */
@Controller
@RequestMapping("/fdCashLogController")
public class FdCashLogController extends BaseController {

	@Autowired
	private FdCashLogServiceI fdCashLogService;


	/**
	 * 跳转到FdCashLog管理页面
	 * 
	 * @return
	 */
	@RequestMapping("/manager")
	public String manager(HttpServletRequest request) {
		return "/fdcashlog/fdCashLog";
	}

	/**
	 * 获取FdCashLog数据表格
	 * 
	 * @param user
	 * @return
	 */
	@RequestMapping("/dataGrid")
	@ResponseBody
	public DataGrid dataGrid(FdCashLog fdCashLog, PageHelper ph) {
		return fdCashLogService.dataGrid(fdCashLog, ph);
	}
	/**
	 * 获取FdCashLog数据表格excel
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
	public void download(FdCashLog fdCashLog, PageHelper ph,String downloadFields,HttpServletResponse response) throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException, IOException{
		DataGrid dg = dataGrid(fdCashLog,ph);		
		downloadFields = downloadFields.replace("&quot;", "\"");
		downloadFields = downloadFields.substring(1,downloadFields.length()-1);
		List<Colum> colums = JSON.parseArray(downloadFields, Colum.class);
		downloadTable(colums, dg, response);
	}
	/**
	 * 跳转到添加FdCashLog页面
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/addPage")
	public String addPage(HttpServletRequest request) {
		FdCashLog fdCashLog = new FdCashLog();
		return "/fdcashlog/fdCashLogAdd";
	}

	/**
	 * 添加FdCashLog
	 * 
	 * @return
	 */
	@RequestMapping("/add")
	@ResponseBody
	public Json add(FdCashLog fdCashLog) {
		Json j = new Json();		
		fdCashLogService.add(fdCashLog);
		j.setSuccess(true);
		j.setMsg("添加成功！");		
		return j;
	}

	/**
	 * 跳转到FdCashLog查看页面
	 * 
	 * @return
	 */
	@RequestMapping("/view")
	public String view(HttpServletRequest request, Integer id) {
		FdCashLog fdCashLog = fdCashLogService.get(id);
		request.setAttribute("fdCashLog", fdCashLog);
		return "/fdcashlog/fdCashLogView";
	}

	/**
	 * 跳转到FdCashLog修改页面
	 * 
	 * @return
	 */
	@RequestMapping("/editPage")
	public String editPage(HttpServletRequest request, Integer id) {
		FdCashLog fdCashLog = fdCashLogService.get(id);
		request.setAttribute("fdCashLog", fdCashLog);
		return "/fdcashlog/fdCashLogEdit";
	}

	/**
	 * 修改FdCashLog
	 * 
	 * @param fdCashLog
	 * @return
	 */
	@RequestMapping("/edit")
	@ResponseBody
	public Json edit(FdCashLog fdCashLog) {
		Json j = new Json();		
		fdCashLogService.edit(fdCashLog);
		j.setSuccess(true);
		j.setMsg("编辑成功！");		
		return j;
	}

	/**
	 * 删除FdCashLog
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public Json delete(Integer id) {
		Json j = new Json();
		fdCashLogService.delete(id);
		j.setMsg("删除成功！");
		j.setSuccess(true);
		return j;
	}

}
