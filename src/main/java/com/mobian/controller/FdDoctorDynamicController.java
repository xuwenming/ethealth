package com.mobian.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mobian.pageModel.Colum;
import com.mobian.pageModel.FdDoctorDynamic;
import com.mobian.pageModel.DataGrid;
import com.mobian.pageModel.Json;
import com.mobian.pageModel.PageHelper;
import com.mobian.service.FdDoctorDynamicServiceI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

/**
 * FdDoctorDynamic管理控制器
 * 
 * @author John
 * 
 */
@Controller
@RequestMapping("/fdDoctorDynamicController")
public class FdDoctorDynamicController extends BaseController {

	@Autowired
	private FdDoctorDynamicServiceI fdDoctorDynamicService;


	/**
	 * 跳转到FdDoctorDynamic管理页面
	 * 
	 * @return
	 */
	@RequestMapping("/manager")
	public String manager(HttpServletRequest request) {
		return "/fddoctordynamic/fdDoctorDynamic";
	}

	/**
	 * 获取FdDoctorDynamic数据表格
	 * 
	 * @param user
	 * @return
	 */
	@RequestMapping("/dataGrid")
	@ResponseBody
	public DataGrid dataGrid(FdDoctorDynamic fdDoctorDynamic, PageHelper ph) {
		return fdDoctorDynamicService.dataGrid(fdDoctorDynamic, ph);
	}
	/**
	 * 获取FdDoctorDynamic数据表格excel
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
	public void download(FdDoctorDynamic fdDoctorDynamic, PageHelper ph,String downloadFields,HttpServletResponse response) throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException, IOException{
		DataGrid dg = dataGrid(fdDoctorDynamic,ph);		
		downloadFields = downloadFields.replace("&quot;", "\"");
		downloadFields = downloadFields.substring(1,downloadFields.length()-1);
		List<Colum> colums = JSON.parseArray(downloadFields, Colum.class);
		downloadTable(colums, dg, response);
	}
	/**
	 * 跳转到添加FdDoctorDynamic页面
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/addPage")
	public String addPage(HttpServletRequest request) {
		FdDoctorDynamic fdDoctorDynamic = new FdDoctorDynamic();
		return "/fddoctordynamic/fdDoctorDynamicAdd";
	}

	/**
	 * 添加FdDoctorDynamic
	 * 
	 * @return
	 */
	@RequestMapping("/add")
	@ResponseBody
	public Json add(FdDoctorDynamic fdDoctorDynamic) {
		Json j = new Json();		
		fdDoctorDynamicService.add(fdDoctorDynamic);
		j.setSuccess(true);
		j.setMsg("添加成功！");		
		return j;
	}

	/**
	 * 跳转到FdDoctorDynamic查看页面
	 * 
	 * @return
	 */
	@RequestMapping("/view")
	public String view(HttpServletRequest request, Integer id) {
		FdDoctorDynamic fdDoctorDynamic = fdDoctorDynamicService.get(id);
		request.setAttribute("fdDoctorDynamic", fdDoctorDynamic);
		return "/fddoctordynamic/fdDoctorDynamicView";
	}

	/**
	 * 跳转到FdDoctorDynamic修改页面
	 * 
	 * @return
	 */
	@RequestMapping("/editPage")
	public String editPage(HttpServletRequest request, Integer id) {
		FdDoctorDynamic fdDoctorDynamic = fdDoctorDynamicService.get(id);
		request.setAttribute("fdDoctorDynamic", fdDoctorDynamic);
		return "/fddoctordynamic/fdDoctorDynamicEdit";
	}

	/**
	 * 修改FdDoctorDynamic
	 * 
	 * @param fdDoctorDynamic
	 * @return
	 */
	@RequestMapping("/edit")
	@ResponseBody
	public Json edit(FdDoctorDynamic fdDoctorDynamic) {
		Json j = new Json();		
		fdDoctorDynamicService.edit(fdDoctorDynamic);
		j.setSuccess(true);
		j.setMsg("编辑成功！");		
		return j;
	}

	/**
	 * 删除FdDoctorDynamic
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public Json delete(Integer id) {
		Json j = new Json();
		fdDoctorDynamicService.delete(id);
		j.setMsg("删除成功！");
		j.setSuccess(true);
		return j;
	}

}
