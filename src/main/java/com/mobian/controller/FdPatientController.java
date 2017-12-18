package com.mobian.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mobian.pageModel.Colum;
import com.mobian.pageModel.FdPatient;
import com.mobian.pageModel.DataGrid;
import com.mobian.pageModel.Json;
import com.mobian.pageModel.PageHelper;
import com.mobian.service.FdPatientServiceI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

/**
 * FdPatient管理控制器
 * 
 * @author John
 * 
 */
@Controller
@RequestMapping("/fdPatientController")
public class FdPatientController extends BaseController {

	@Autowired
	private FdPatientServiceI fdPatientService;


	/**
	 * 跳转到FdPatient管理页面
	 * 
	 * @return
	 */
	@RequestMapping("/manager")
	public String manager(HttpServletRequest request) {
		return "/fdpatient/fdPatient";
	}

	/**
	 * 获取FdPatient数据表格
	 * 
	 * @param user
	 * @return
	 */
	@RequestMapping("/dataGrid")
	@ResponseBody
	public DataGrid dataGrid(FdPatient fdPatient, PageHelper ph) {
		return fdPatientService.dataGrid(fdPatient, ph);
	}
	/**
	 * 获取FdPatient数据表格excel
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
	public void download(FdPatient fdPatient, PageHelper ph,String downloadFields,HttpServletResponse response) throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException, IOException{
		DataGrid dg = dataGrid(fdPatient,ph);		
		downloadFields = downloadFields.replace("&quot;", "\"");
		downloadFields = downloadFields.substring(1,downloadFields.length()-1);
		List<Colum> colums = JSON.parseArray(downloadFields, Colum.class);
		downloadTable(colums, dg, response);
	}
	/**
	 * 跳转到添加FdPatient页面
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/addPage")
	public String addPage(HttpServletRequest request) {
		FdPatient fdPatient = new FdPatient();
		return "/fdpatient/fdPatientAdd";
	}

	/**
	 * 添加FdPatient
	 * 
	 * @return
	 */
	@RequestMapping("/add")
	@ResponseBody
	public Json add(FdPatient fdPatient) {
		Json j = new Json();		
		fdPatientService.add(fdPatient);
		j.setSuccess(true);
		j.setMsg("添加成功！");		
		return j;
	}

	/**
	 * 跳转到FdPatient查看页面
	 * 
	 * @return
	 */
	@RequestMapping("/view")
	public String view(HttpServletRequest request, Integer id) {
		FdPatient fdPatient = fdPatientService.get(id);
		request.setAttribute("fdPatient", fdPatient);
		return "/fdpatient/fdPatientView";
	}

	/**
	 * 跳转到FdPatient修改页面
	 * 
	 * @return
	 */
	@RequestMapping("/editPage")
	public String editPage(HttpServletRequest request, Integer id) {
		FdPatient fdPatient = fdPatientService.get(id);
		request.setAttribute("fdPatient", fdPatient);
		return "/fdpatient/fdPatientEdit";
	}

	/**
	 * 修改FdPatient
	 * 
	 * @param fdPatient
	 * @return
	 */
	@RequestMapping("/edit")
	@ResponseBody
	public Json edit(FdPatient fdPatient) {
		Json j = new Json();		
		fdPatientService.edit(fdPatient);
		j.setSuccess(true);
		j.setMsg("编辑成功！");		
		return j;
	}

	/**
	 * 删除FdPatient
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public Json delete(Integer id) {
		Json j = new Json();
		fdPatientService.delete(id);
		j.setMsg("删除成功！");
		j.setSuccess(true);
		return j;
	}

}
