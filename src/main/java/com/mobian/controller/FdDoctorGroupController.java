package com.mobian.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mobian.pageModel.Colum;
import com.mobian.pageModel.FdDoctorGroup;
import com.mobian.pageModel.DataGrid;
import com.mobian.pageModel.Json;
import com.mobian.pageModel.PageHelper;
import com.mobian.service.FdDoctorGroupServiceI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

/**
 * FdDoctorGroup管理控制器
 * 
 * @author John
 * 
 */
@Controller
@RequestMapping("/fdDoctorGroupController")
public class FdDoctorGroupController extends BaseController {

	@Autowired
	private FdDoctorGroupServiceI fdDoctorGroupService;


	/**
	 * 跳转到FdDoctorGroup管理页面
	 * 
	 * @return
	 */
	@RequestMapping("/manager")
	public String manager(HttpServletRequest request) {
		return "/fddoctorgroup/fdDoctorGroup";
	}

	/**
	 * 获取FdDoctorGroup数据表格
	 * 
	 * @param user
	 * @return
	 */
	@RequestMapping("/dataGrid")
	@ResponseBody
	public DataGrid dataGrid(FdDoctorGroup fdDoctorGroup, PageHelper ph) {
		return fdDoctorGroupService.dataGrid(fdDoctorGroup, ph);
	}
	/**
	 * 获取FdDoctorGroup数据表格excel
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
	public void download(FdDoctorGroup fdDoctorGroup, PageHelper ph,String downloadFields,HttpServletResponse response) throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException, IOException{
		DataGrid dg = dataGrid(fdDoctorGroup,ph);		
		downloadFields = downloadFields.replace("&quot;", "\"");
		downloadFields = downloadFields.substring(1,downloadFields.length()-1);
		List<Colum> colums = JSON.parseArray(downloadFields, Colum.class);
		downloadTable(colums, dg, response);
	}
	/**
	 * 跳转到添加FdDoctorGroup页面
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/addPage")
	public String addPage(HttpServletRequest request) {
		FdDoctorGroup fdDoctorGroup = new FdDoctorGroup();
		return "/fddoctorgroup/fdDoctorGroupAdd";
	}

	/**
	 * 添加FdDoctorGroup
	 * 
	 * @return
	 */
	@RequestMapping("/add")
	@ResponseBody
	public Json add(FdDoctorGroup fdDoctorGroup) {
		Json j = new Json();		
		fdDoctorGroupService.add(fdDoctorGroup);
		j.setSuccess(true);
		j.setMsg("添加成功！");		
		return j;
	}

	/**
	 * 跳转到FdDoctorGroup查看页面
	 * 
	 * @return
	 */
	@RequestMapping("/view")
	public String view(HttpServletRequest request, Integer id) {
		FdDoctorGroup fdDoctorGroup = fdDoctorGroupService.get(id);
		request.setAttribute("fdDoctorGroup", fdDoctorGroup);
		return "/fddoctorgroup/fdDoctorGroupView";
	}

	/**
	 * 跳转到FdDoctorGroup修改页面
	 * 
	 * @return
	 */
	@RequestMapping("/editPage")
	public String editPage(HttpServletRequest request, Integer id) {
		FdDoctorGroup fdDoctorGroup = fdDoctorGroupService.get(id);
		request.setAttribute("fdDoctorGroup", fdDoctorGroup);
		return "/fddoctorgroup/fdDoctorGroupEdit";
	}

	/**
	 * 修改FdDoctorGroup
	 * 
	 * @param fdDoctorGroup
	 * @return
	 */
	@RequestMapping("/edit")
	@ResponseBody
	public Json edit(FdDoctorGroup fdDoctorGroup) {
		Json j = new Json();		
		fdDoctorGroupService.edit(fdDoctorGroup);
		j.setSuccess(true);
		j.setMsg("编辑成功！");		
		return j;
	}

	/**
	 * 删除FdDoctorGroup
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public Json delete(Integer id) {
		Json j = new Json();
		fdDoctorGroupService.delete(id);
		j.setMsg("删除成功！");
		j.setSuccess(true);
		return j;
	}

}
