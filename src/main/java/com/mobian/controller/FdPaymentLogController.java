package com.mobian.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mobian.pageModel.Colum;
import com.mobian.pageModel.FdPaymentLog;
import com.mobian.pageModel.DataGrid;
import com.mobian.pageModel.Json;
import com.mobian.pageModel.PageHelper;
import com.mobian.service.FdPaymentLogServiceI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

/**
 * FdPaymentLog管理控制器
 * 
 * @author John
 * 
 */
@Controller
@RequestMapping("/fdPaymentLogController")
public class FdPaymentLogController extends BaseController {

	@Autowired
	private FdPaymentLogServiceI fdPaymentLogService;


	/**
	 * 跳转到FdPaymentLog管理页面
	 * 
	 * @return
	 */
	@RequestMapping("/manager")
	public String manager(HttpServletRequest request) {
		return "/fdpaymentlog/fdPaymentLog";
	}

	/**
	 * 获取FdPaymentLog数据表格
	 * 
	 * @param user
	 * @return
	 */
	@RequestMapping("/dataGrid")
	@ResponseBody
	public DataGrid dataGrid(FdPaymentLog fdPaymentLog, PageHelper ph) {
		return fdPaymentLogService.dataGrid(fdPaymentLog, ph);
	}
	/**
	 * 获取FdPaymentLog数据表格excel
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
	public void download(FdPaymentLog fdPaymentLog, PageHelper ph,String downloadFields,HttpServletResponse response) throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException, IOException{
		DataGrid dg = dataGrid(fdPaymentLog,ph);		
		downloadFields = downloadFields.replace("&quot;", "\"");
		downloadFields = downloadFields.substring(1,downloadFields.length()-1);
		List<Colum> colums = JSON.parseArray(downloadFields, Colum.class);
		downloadTable(colums, dg, response);
	}
	/**
	 * 跳转到添加FdPaymentLog页面
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/addPage")
	public String addPage(HttpServletRequest request) {
		FdPaymentLog fdPaymentLog = new FdPaymentLog();
		return "/fdpaymentlog/fdPaymentLogAdd";
	}

	/**
	 * 添加FdPaymentLog
	 * 
	 * @return
	 */
	@RequestMapping("/add")
	@ResponseBody
	public Json add(FdPaymentLog fdPaymentLog) {
		Json j = new Json();		
		fdPaymentLogService.add(fdPaymentLog);
		j.setSuccess(true);
		j.setMsg("添加成功！");		
		return j;
	}

	/**
	 * 跳转到FdPaymentLog查看页面
	 * 
	 * @return
	 */
	@RequestMapping("/view")
	public String view(HttpServletRequest request, Integer id) {
		FdPaymentLog fdPaymentLog = fdPaymentLogService.get(id);
		request.setAttribute("fdPaymentLog", fdPaymentLog);
		return "/fdpaymentlog/fdPaymentLogView";
	}

	/**
	 * 跳转到FdPaymentLog修改页面
	 * 
	 * @return
	 */
	@RequestMapping("/editPage")
	public String editPage(HttpServletRequest request, Integer id) {
		FdPaymentLog fdPaymentLog = fdPaymentLogService.get(id);
		request.setAttribute("fdPaymentLog", fdPaymentLog);
		return "/fdpaymentlog/fdPaymentLogEdit";
	}

	/**
	 * 修改FdPaymentLog
	 * 
	 * @param fdPaymentLog
	 * @return
	 */
	@RequestMapping("/edit")
	@ResponseBody
	public Json edit(FdPaymentLog fdPaymentLog) {
		Json j = new Json();		
		fdPaymentLogService.edit(fdPaymentLog);
		j.setSuccess(true);
		j.setMsg("编辑成功！");		
		return j;
	}

	/**
	 * 删除FdPaymentLog
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public Json delete(Integer id) {
		Json j = new Json();
		fdPaymentLogService.delete(id);
		j.setMsg("删除成功！");
		j.setSuccess(true);
		return j;
	}

}
