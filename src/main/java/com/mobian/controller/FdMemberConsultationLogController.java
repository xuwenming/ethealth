package com.mobian.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mobian.pageModel.Colum;
import com.mobian.pageModel.FdMemberConsultationLog;
import com.mobian.pageModel.DataGrid;
import com.mobian.pageModel.Json;
import com.mobian.pageModel.PageHelper;
import com.mobian.service.FdMemberConsultationLogServiceI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

/**
 * FdMemberConsultationLog管理控制器
 * 
 * @author John
 * 
 */
@Controller
@RequestMapping("/fdMemberConsultationLogController")
public class FdMemberConsultationLogController extends BaseController {

	@Autowired
	private FdMemberConsultationLogServiceI fdMemberConsultationLogService;


	/**
	 * 跳转到FdMemberConsultationLog管理页面
	 * 
	 * @return
	 */
	@RequestMapping("/manager")
	public String manager(HttpServletRequest request) {
		return "/fdmemberconsultationlog/fdMemberConsultationLog";
	}

	/**
	 * 获取FdMemberConsultationLog数据表格
	 * 
	 * @param user
	 * @return
	 */
	@RequestMapping("/dataGrid")
	@ResponseBody
	public DataGrid dataGrid(FdMemberConsultationLog fdMemberConsultationLog, PageHelper ph) {
		return fdMemberConsultationLogService.dataGrid(fdMemberConsultationLog, ph);
	}
	/**
	 * 获取FdMemberConsultationLog数据表格excel
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
	public void download(FdMemberConsultationLog fdMemberConsultationLog, PageHelper ph,String downloadFields,HttpServletResponse response) throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException, IOException{
		DataGrid dg = dataGrid(fdMemberConsultationLog,ph);		
		downloadFields = downloadFields.replace("&quot;", "\"");
		downloadFields = downloadFields.substring(1,downloadFields.length()-1);
		List<Colum> colums = JSON.parseArray(downloadFields, Colum.class);
		downloadTable(colums, dg, response);
	}
	/**
	 * 跳转到添加FdMemberConsultationLog页面
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/addPage")
	public String addPage(HttpServletRequest request) {
		FdMemberConsultationLog fdMemberConsultationLog = new FdMemberConsultationLog();
		return "/fdmemberconsultationlog/fdMemberConsultationLogAdd";
	}

	/**
	 * 添加FdMemberConsultationLog
	 * 
	 * @return
	 */
	@RequestMapping("/add")
	@ResponseBody
	public Json add(FdMemberConsultationLog fdMemberConsultationLog) {
		Json j = new Json();		
		fdMemberConsultationLogService.add(fdMemberConsultationLog);
		j.setSuccess(true);
		j.setMsg("添加成功！");		
		return j;
	}

	/**
	 * 跳转到FdMemberConsultationLog查看页面
	 * 
	 * @return
	 */
	@RequestMapping("/view")
	public String view(HttpServletRequest request, Integer id) {
		FdMemberConsultationLog fdMemberConsultationLog = fdMemberConsultationLogService.get(id);
		request.setAttribute("fdMemberConsultationLog", fdMemberConsultationLog);
		return "/fdmemberconsultationlog/fdMemberConsultationLogView";
	}

	/**
	 * 跳转到FdMemberConsultationLog修改页面
	 * 
	 * @return
	 */
	@RequestMapping("/editPage")
	public String editPage(HttpServletRequest request, Integer id) {
		FdMemberConsultationLog fdMemberConsultationLog = fdMemberConsultationLogService.get(id);
		request.setAttribute("fdMemberConsultationLog", fdMemberConsultationLog);
		return "/fdmemberconsultationlog/fdMemberConsultationLogEdit";
	}

	/**
	 * 修改FdMemberConsultationLog
	 * 
	 * @param fdMemberConsultationLog
	 * @return
	 */
	@RequestMapping("/edit")
	@ResponseBody
	public Json edit(FdMemberConsultationLog fdMemberConsultationLog) {
		Json j = new Json();		
		fdMemberConsultationLogService.edit(fdMemberConsultationLog);
		j.setSuccess(true);
		j.setMsg("编辑成功！");		
		return j;
	}

	/**
	 * 删除FdMemberConsultationLog
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public Json delete(Integer id) {
		Json j = new Json();
		fdMemberConsultationLogService.delete(id);
		j.setMsg("删除成功！");
		j.setSuccess(true);
		return j;
	}

}
