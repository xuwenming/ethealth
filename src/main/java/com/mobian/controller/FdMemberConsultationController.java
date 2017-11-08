package com.mobian.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mobian.pageModel.Colum;
import com.mobian.pageModel.FdMemberConsultation;
import com.mobian.pageModel.DataGrid;
import com.mobian.pageModel.Json;
import com.mobian.pageModel.PageHelper;
import com.mobian.service.FdMemberConsultationServiceI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

/**
 * FdMemberConsultation管理控制器
 * 
 * @author John
 * 
 */
@Controller
@RequestMapping("/fdMemberConsultationController")
public class FdMemberConsultationController extends BaseController {

	@Autowired
	private FdMemberConsultationServiceI fdMemberConsultationService;


	/**
	 * 跳转到FdMemberConsultation管理页面
	 * 
	 * @return
	 */
	@RequestMapping("/manager")
	public String manager(HttpServletRequest request) {
		return "/fdmemberconsultation/fdMemberConsultation";
	}

	/**
	 * 获取FdMemberConsultation数据表格
	 * 
	 * @param user
	 * @return
	 */
	@RequestMapping("/dataGrid")
	@ResponseBody
	public DataGrid dataGrid(FdMemberConsultation fdMemberConsultation, PageHelper ph) {
		return fdMemberConsultationService.dataGrid(fdMemberConsultation, ph);
	}
	/**
	 * 获取FdMemberConsultation数据表格excel
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
	public void download(FdMemberConsultation fdMemberConsultation, PageHelper ph,String downloadFields,HttpServletResponse response) throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException, IOException{
		DataGrid dg = dataGrid(fdMemberConsultation,ph);		
		downloadFields = downloadFields.replace("&quot;", "\"");
		downloadFields = downloadFields.substring(1,downloadFields.length()-1);
		List<Colum> colums = JSON.parseArray(downloadFields, Colum.class);
		downloadTable(colums, dg, response);
	}
	/**
	 * 跳转到添加FdMemberConsultation页面
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/addPage")
	public String addPage(HttpServletRequest request) {
		FdMemberConsultation fdMemberConsultation = new FdMemberConsultation();
		return "/fdmemberconsultation/fdMemberConsultationAdd";
	}

	/**
	 * 添加FdMemberConsultation
	 * 
	 * @return
	 */
	@RequestMapping("/add")
	@ResponseBody
	public Json add(FdMemberConsultation fdMemberConsultation) {
		Json j = new Json();		
		fdMemberConsultationService.add(fdMemberConsultation);
		j.setSuccess(true);
		j.setMsg("添加成功！");		
		return j;
	}

	/**
	 * 跳转到FdMemberConsultation查看页面
	 * 
	 * @return
	 */
	@RequestMapping("/view")
	public String view(HttpServletRequest request, Integer id) {
		FdMemberConsultation fdMemberConsultation = fdMemberConsultationService.get(id);
		request.setAttribute("fdMemberConsultation", fdMemberConsultation);
		return "/fdmemberconsultation/fdMemberConsultationView";
	}

	/**
	 * 跳转到FdMemberConsultation修改页面
	 * 
	 * @return
	 */
	@RequestMapping("/editPage")
	public String editPage(HttpServletRequest request, Integer id) {
		FdMemberConsultation fdMemberConsultation = fdMemberConsultationService.get(id);
		request.setAttribute("fdMemberConsultation", fdMemberConsultation);
		return "/fdmemberconsultation/fdMemberConsultationEdit";
	}

	/**
	 * 修改FdMemberConsultation
	 * 
	 * @param fdMemberConsultation
	 * @return
	 */
	@RequestMapping("/edit")
	@ResponseBody
	public Json edit(FdMemberConsultation fdMemberConsultation) {
		Json j = new Json();		
		fdMemberConsultationService.edit(fdMemberConsultation);
		j.setSuccess(true);
		j.setMsg("编辑成功！");		
		return j;
	}

	/**
	 * 删除FdMemberConsultation
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public Json delete(Integer id) {
		Json j = new Json();
		fdMemberConsultationService.delete(id);
		j.setMsg("删除成功！");
		j.setSuccess(true);
		return j;
	}

}
