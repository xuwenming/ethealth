package com.mobian.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mobian.pageModel.Colum;
import com.mobian.pageModel.FdMemberAppointment;
import com.mobian.pageModel.DataGrid;
import com.mobian.pageModel.Json;
import com.mobian.pageModel.PageHelper;
import com.mobian.service.FdMemberAppointmentServiceI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

/**
 * FdMemberAppointment管理控制器
 * 
 * @author John
 * 
 */
@Controller
@RequestMapping("/fdMemberAppointmentController")
public class FdMemberAppointmentController extends BaseController {

	@Autowired
	private FdMemberAppointmentServiceI fdMemberAppointmentService;


	/**
	 * 跳转到FdMemberAppointment管理页面
	 * 
	 * @return
	 */
	@RequestMapping("/manager")
	public String manager(HttpServletRequest request) {
		return "/fdmemberappointment/fdMemberAppointment";
	}

	/**
	 * 获取FdMemberAppointment数据表格
	 * 
	 * @param user
	 * @return
	 */
	@RequestMapping("/dataGrid")
	@ResponseBody
	public DataGrid dataGrid(FdMemberAppointment fdMemberAppointment, PageHelper ph) {
		return fdMemberAppointmentService.dataGrid(fdMemberAppointment, ph);
	}
	/**
	 * 获取FdMemberAppointment数据表格excel
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
	public void download(FdMemberAppointment fdMemberAppointment, PageHelper ph,String downloadFields,HttpServletResponse response) throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException, IOException{
		DataGrid dg = dataGrid(fdMemberAppointment,ph);		
		downloadFields = downloadFields.replace("&quot;", "\"");
		downloadFields = downloadFields.substring(1,downloadFields.length()-1);
		List<Colum> colums = JSON.parseArray(downloadFields, Colum.class);
		downloadTable(colums, dg, response);
	}
	/**
	 * 跳转到添加FdMemberAppointment页面
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/addPage")
	public String addPage(HttpServletRequest request) {
		FdMemberAppointment fdMemberAppointment = new FdMemberAppointment();
		return "/fdmemberappointment/fdMemberAppointmentAdd";
	}

	/**
	 * 添加FdMemberAppointment
	 * 
	 * @return
	 */
	@RequestMapping("/add")
	@ResponseBody
	public Json add(FdMemberAppointment fdMemberAppointment) {
		Json j = new Json();		
		fdMemberAppointmentService.add(fdMemberAppointment);
		j.setSuccess(true);
		j.setMsg("添加成功！");		
		return j;
	}

	/**
	 * 跳转到FdMemberAppointment查看页面
	 * 
	 * @return
	 */
	@RequestMapping("/view")
	public String view(HttpServletRequest request, Integer id) {
		FdMemberAppointment fdMemberAppointment = fdMemberAppointmentService.get(id);
		request.setAttribute("fdMemberAppointment", fdMemberAppointment);
		return "/fdmemberappointment/fdMemberAppointmentView";
	}

	/**
	 * 跳转到FdMemberAppointment修改页面
	 * 
	 * @return
	 */
	@RequestMapping("/editPage")
	public String editPage(HttpServletRequest request, Integer id) {
		FdMemberAppointment fdMemberAppointment = fdMemberAppointmentService.get(id);
		request.setAttribute("fdMemberAppointment", fdMemberAppointment);
		return "/fdmemberappointment/fdMemberAppointmentEdit";
	}

	/**
	 * 修改FdMemberAppointment
	 * 
	 * @param fdMemberAppointment
	 * @return
	 */
	@RequestMapping("/edit")
	@ResponseBody
	public Json edit(FdMemberAppointment fdMemberAppointment) {
		Json j = new Json();		
		fdMemberAppointmentService.edit(fdMemberAppointment);
		j.setSuccess(true);
		j.setMsg("编辑成功！");		
		return j;
	}

	/**
	 * 删除FdMemberAppointment
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public Json delete(Integer id) {
		Json j = new Json();
		fdMemberAppointmentService.delete(id);
		j.setMsg("删除成功！");
		j.setSuccess(true);
		return j;
	}

}
