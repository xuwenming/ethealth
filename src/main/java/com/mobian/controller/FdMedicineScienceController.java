package com.mobian.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mobian.pageModel.Colum;
import com.mobian.pageModel.FdMedicineScience;
import com.mobian.pageModel.DataGrid;
import com.mobian.pageModel.Json;
import com.mobian.pageModel.PageHelper;
import com.mobian.service.FdMedicineScienceServiceI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

/**
 * FdMedicineScience管理控制器
 * 
 * @author John
 * 
 */
@Controller
@RequestMapping("/fdMedicineScienceController")
public class FdMedicineScienceController extends BaseController {

	@Autowired
	private FdMedicineScienceServiceI fdMedicineScienceService;


	/**
	 * 跳转到FdMedicineScience管理页面
	 * 
	 * @return
	 */
	@RequestMapping("/manager")
	public String manager(HttpServletRequest request) {
		return "/fdmedicinescience/fdMedicineScience";
	}

	/**
	 * 获取FdMedicineScience数据表格
	 * 
	 * @param user
	 * @return
	 */
	@RequestMapping("/dataGrid")
	@ResponseBody
	public DataGrid dataGrid(FdMedicineScience fdMedicineScience, PageHelper ph) {
		return fdMedicineScienceService.dataGrid(fdMedicineScience, ph);
	}
	/**
	 * 获取FdMedicineScience数据表格excel
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
	public void download(FdMedicineScience fdMedicineScience, PageHelper ph,String downloadFields,HttpServletResponse response) throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException, IOException{
		DataGrid dg = dataGrid(fdMedicineScience,ph);		
		downloadFields = downloadFields.replace("&quot;", "\"");
		downloadFields = downloadFields.substring(1,downloadFields.length()-1);
		List<Colum> colums = JSON.parseArray(downloadFields, Colum.class);
		downloadTable(colums, dg, response);
	}
	/**
	 * 跳转到添加FdMedicineScience页面
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/addPage")
	public String addPage(HttpServletRequest request) {
		FdMedicineScience fdMedicineScience = new FdMedicineScience();
		return "/fdmedicinescience/fdMedicineScienceAdd";
	}

	/**
	 * 添加FdMedicineScience
	 * 
	 * @return
	 */
	@RequestMapping("/add")
	@ResponseBody
	public Json add(FdMedicineScience fdMedicineScience) {
		Json j = new Json();		
		fdMedicineScienceService.add(fdMedicineScience);
		j.setSuccess(true);
		j.setMsg("添加成功！");		
		return j;
	}

	/**
	 * 跳转到FdMedicineScience查看页面
	 * 
	 * @return
	 */
	@RequestMapping("/view")
	public String view(HttpServletRequest request, Integer id) {
		FdMedicineScience fdMedicineScience = fdMedicineScienceService.get(id);
		request.setAttribute("fdMedicineScience", fdMedicineScience);
		return "/fdmedicinescience/fdMedicineScienceView";
	}

	/**
	 * 跳转到FdMedicineScience修改页面
	 * 
	 * @return
	 */
	@RequestMapping("/editPage")
	public String editPage(HttpServletRequest request, Integer id) {
		FdMedicineScience fdMedicineScience = fdMedicineScienceService.get(id);
		request.setAttribute("fdMedicineScience", fdMedicineScience);
		return "/fdmedicinescience/fdMedicineScienceEdit";
	}

	/**
	 * 修改FdMedicineScience
	 * 
	 * @param fdMedicineScience
	 * @return
	 */
	@RequestMapping("/edit")
	@ResponseBody
	public Json edit(FdMedicineScience fdMedicineScience) {
		Json j = new Json();		
		fdMedicineScienceService.edit(fdMedicineScience);
		j.setSuccess(true);
		j.setMsg("编辑成功！");		
		return j;
	}

	/**
	 * 删除FdMedicineScience
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public Json delete(Integer id) {
		Json j = new Json();
		fdMedicineScienceService.delete(id);
		j.setMsg("删除成功！");
		j.setSuccess(true);
		return j;
	}

}
