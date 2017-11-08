package com.mobian.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mobian.pageModel.Colum;
import com.mobian.pageModel.FdDoctorOpinion;
import com.mobian.pageModel.DataGrid;
import com.mobian.pageModel.Json;
import com.mobian.pageModel.PageHelper;
import com.mobian.service.FdDoctorOpinionServiceI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

/**
 * FdDoctorOpinion管理控制器
 * 
 * @author John
 * 
 */
@Controller
@RequestMapping("/fdDoctorOpinionController")
public class FdDoctorOpinionController extends BaseController {

	@Autowired
	private FdDoctorOpinionServiceI fdDoctorOpinionService;


	/**
	 * 跳转到FdDoctorOpinion管理页面
	 * 
	 * @return
	 */
	@RequestMapping("/manager")
	public String manager(HttpServletRequest request) {
		return "/fddoctoropinion/fdDoctorOpinion";
	}

	/**
	 * 获取FdDoctorOpinion数据表格
	 * 
	 * @param user
	 * @return
	 */
	@RequestMapping("/dataGrid")
	@ResponseBody
	public DataGrid dataGrid(FdDoctorOpinion fdDoctorOpinion, PageHelper ph) {
		return fdDoctorOpinionService.dataGrid(fdDoctorOpinion, ph);
	}
	/**
	 * 获取FdDoctorOpinion数据表格excel
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
	public void download(FdDoctorOpinion fdDoctorOpinion, PageHelper ph,String downloadFields,HttpServletResponse response) throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException, IOException{
		DataGrid dg = dataGrid(fdDoctorOpinion,ph);		
		downloadFields = downloadFields.replace("&quot;", "\"");
		downloadFields = downloadFields.substring(1,downloadFields.length()-1);
		List<Colum> colums = JSON.parseArray(downloadFields, Colum.class);
		downloadTable(colums, dg, response);
	}
	/**
	 * 跳转到添加FdDoctorOpinion页面
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/addPage")
	public String addPage(HttpServletRequest request) {
		FdDoctorOpinion fdDoctorOpinion = new FdDoctorOpinion();
		return "/fddoctoropinion/fdDoctorOpinionAdd";
	}

	/**
	 * 添加FdDoctorOpinion
	 * 
	 * @return
	 */
	@RequestMapping("/add")
	@ResponseBody
	public Json add(FdDoctorOpinion fdDoctorOpinion) {
		Json j = new Json();		
		fdDoctorOpinionService.add(fdDoctorOpinion);
		j.setSuccess(true);
		j.setMsg("添加成功！");		
		return j;
	}

	/**
	 * 跳转到FdDoctorOpinion查看页面
	 * 
	 * @return
	 */
	@RequestMapping("/view")
	public String view(HttpServletRequest request, Integer id) {
		FdDoctorOpinion fdDoctorOpinion = fdDoctorOpinionService.get(id);
		request.setAttribute("fdDoctorOpinion", fdDoctorOpinion);
		return "/fddoctoropinion/fdDoctorOpinionView";
	}

	/**
	 * 跳转到FdDoctorOpinion修改页面
	 * 
	 * @return
	 */
	@RequestMapping("/editPage")
	public String editPage(HttpServletRequest request, Integer id) {
		FdDoctorOpinion fdDoctorOpinion = fdDoctorOpinionService.get(id);
		request.setAttribute("fdDoctorOpinion", fdDoctorOpinion);
		return "/fddoctoropinion/fdDoctorOpinionEdit";
	}

	/**
	 * 修改FdDoctorOpinion
	 * 
	 * @param fdDoctorOpinion
	 * @return
	 */
	@RequestMapping("/edit")
	@ResponseBody
	public Json edit(FdDoctorOpinion fdDoctorOpinion) {
		Json j = new Json();		
		fdDoctorOpinionService.edit(fdDoctorOpinion);
		j.setSuccess(true);
		j.setMsg("编辑成功！");		
		return j;
	}

	/**
	 * 删除FdDoctorOpinion
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public Json delete(Integer id) {
		Json j = new Json();
		fdDoctorOpinionService.delete(id);
		j.setMsg("删除成功！");
		j.setSuccess(true);
		return j;
	}

}
