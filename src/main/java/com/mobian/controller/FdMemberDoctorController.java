package com.mobian.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mobian.pageModel.Colum;
import com.mobian.pageModel.FdMemberDoctor;
import com.mobian.pageModel.DataGrid;
import com.mobian.pageModel.Json;
import com.mobian.pageModel.PageHelper;
import com.mobian.service.FdMemberDoctorServiceI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

/**
 * FdMemberDoctor管理控制器
 * 
 * @author John
 * 
 */
@Controller
@RequestMapping("/fdMemberDoctorController")
public class FdMemberDoctorController extends BaseController {

	@Autowired
	private FdMemberDoctorServiceI fdMemberDoctorService;


	/**
	 * 跳转到FdMemberDoctor管理页面
	 * 
	 * @return
	 */
	@RequestMapping("/manager")
	public String manager(HttpServletRequest request) {
		return "/fdmemberdoctor/fdMemberDoctor";
	}

	/**
	 * 获取FdMemberDoctor数据表格
	 * 
	 * @param user
	 * @return
	 */
	@RequestMapping("/dataGrid")
	@ResponseBody
	public DataGrid dataGrid(FdMemberDoctor fdMemberDoctor, PageHelper ph) {
		if("seq".equals(ph.getSort())) {
			ph.setSort("isBest desc, t.seq");
		}
		fdMemberDoctor.setStatus("1,2,3");
		return fdMemberDoctorService.dataGridMoreComplex(fdMemberDoctor, ph);
	}
	/**
	 * 获取FdMemberDoctor数据表格excel
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
	public void download(FdMemberDoctor fdMemberDoctor, PageHelper ph,String downloadFields,HttpServletResponse response) throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException, IOException{
		DataGrid dg = dataGrid(fdMemberDoctor,ph);		
		downloadFields = downloadFields.replace("&quot;", "\"");
		downloadFields = downloadFields.substring(1,downloadFields.length()-1);
		List<Colum> colums = JSON.parseArray(downloadFields, Colum.class);
		downloadTable(colums, dg, response);
	}
	/**
	 * 跳转到添加FdMemberDoctor页面
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/addPage")
	public String addPage(HttpServletRequest request) {
		FdMemberDoctor fdMemberDoctor = new FdMemberDoctor();
		return "/fdmemberdoctor/fdMemberDoctorAdd";
	}

	/**
	 * 添加FdMemberDoctor
	 * 
	 * @return
	 */
	@RequestMapping("/add")
	@ResponseBody
	public Json add(FdMemberDoctor fdMemberDoctor) {
		Json j = new Json();		
		fdMemberDoctorService.add(fdMemberDoctor);
		j.setSuccess(true);
		j.setMsg("添加成功！");		
		return j;
	}

	/**
	 * 跳转到FdMemberDoctor查看页面
	 * 
	 * @return
	 */
	@RequestMapping("/view")
	public String view(HttpServletRequest request, Integer id) {
		FdMemberDoctor fdMemberDoctor = fdMemberDoctorService.get(id);
		request.setAttribute("fdMemberDoctor", fdMemberDoctor);
		return "/fdmemberdoctor/fdMemberDoctorView";
	}

	/**
	 * 跳转到FdMemberDoctor修改页面
	 * 
	 * @return
	 */
	@RequestMapping("/editPage")
	public String editPage(HttpServletRequest request, Integer id) {
		FdMemberDoctor fdMemberDoctor = fdMemberDoctorService.get(id);
		request.setAttribute("fdMemberDoctor", fdMemberDoctor);
		return "/fdmemberdoctor/fdMemberDoctorEdit";
	}

	/**
	 * 修改FdMemberDoctor
	 * 
	 * @param fdMemberDoctor
	 * @return
	 */
	@RequestMapping("/edit")
	@ResponseBody
	public Json edit(FdMemberDoctor fdMemberDoctor) {
		Json j = new Json();		
		fdMemberDoctorService.edit(fdMemberDoctor);
		j.setSuccess(true);
		j.setMsg("编辑成功！");		
		return j;
	}

	/**
	 * 删除FdMemberDoctor
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public Json delete(Integer id) {
		Json j = new Json();
		fdMemberDoctorService.delete(id);
		j.setMsg("删除成功！");
		j.setSuccess(true);
		return j;
	}

}
