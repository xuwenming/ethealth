package com.mobian.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mobian.pageModel.Colum;
import com.mobian.pageModel.FdDoctorTime;
import com.mobian.pageModel.DataGrid;
import com.mobian.pageModel.Json;
import com.mobian.pageModel.PageHelper;
import com.mobian.service.FdDoctorTimeServiceI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

/**
 * FdDoctorTime管理控制器
 * 
 * @author John
 * 
 */
@Controller
@RequestMapping("/fdDoctorTimeController")
public class FdDoctorTimeController extends BaseController {

	@Autowired
	private FdDoctorTimeServiceI fdDoctorTimeService;


	/**
	 * 跳转到FdDoctorTime管理页面
	 * 
	 * @return
	 */
	@RequestMapping("/manager")
	public String manager(HttpServletRequest request) {
		return "/fddoctortime/fdDoctorTime";
	}

	/**
	 * 获取FdDoctorTime数据表格
	 * 
	 * @param user
	 * @return
	 */
	@RequestMapping("/dataGrid")
	@ResponseBody
	public DataGrid dataGrid(FdDoctorTime fdDoctorTime, PageHelper ph) {
		return fdDoctorTimeService.dataGrid(fdDoctorTime, ph);
	}
	/**
	 * 获取FdDoctorTime数据表格excel
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
	public void download(FdDoctorTime fdDoctorTime, PageHelper ph,String downloadFields,HttpServletResponse response) throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException, IOException{
		DataGrid dg = dataGrid(fdDoctorTime,ph);		
		downloadFields = downloadFields.replace("&quot;", "\"");
		downloadFields = downloadFields.substring(1,downloadFields.length()-1);
		List<Colum> colums = JSON.parseArray(downloadFields, Colum.class);
		downloadTable(colums, dg, response);
	}
	/**
	 * 跳转到添加FdDoctorTime页面
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/addPage")
	public String addPage(HttpServletRequest request) {
		FdDoctorTime fdDoctorTime = new FdDoctorTime();
		return "/fddoctortime/fdDoctorTimeAdd";
	}

	/**
	 * 添加FdDoctorTime
	 * 
	 * @return
	 */
	@RequestMapping("/add")
	@ResponseBody
	public Json add(FdDoctorTime fdDoctorTime) {
		Json j = new Json();		
		fdDoctorTimeService.add(fdDoctorTime);
		j.setSuccess(true);
		j.setMsg("添加成功！");		
		return j;
	}

	/**
	 * 跳转到FdDoctorTime查看页面
	 * 
	 * @return
	 */
	@RequestMapping("/view")
	public String view(HttpServletRequest request, Integer id) {
		FdDoctorTime fdDoctorTime = fdDoctorTimeService.get(id);
		request.setAttribute("fdDoctorTime", fdDoctorTime);
		return "/fddoctortime/fdDoctorTimeView";
	}

	/**
	 * 跳转到FdDoctorTime修改页面
	 * 
	 * @return
	 */
	@RequestMapping("/editPage")
	public String editPage(HttpServletRequest request, Integer id) {
		FdDoctorTime fdDoctorTime = fdDoctorTimeService.get(id);
		request.setAttribute("fdDoctorTime", fdDoctorTime);
		return "/fddoctortime/fdDoctorTimeEdit";
	}

	/**
	 * 修改FdDoctorTime
	 * 
	 * @param fdDoctorTime
	 * @return
	 */
	@RequestMapping("/edit")
	@ResponseBody
	public Json edit(FdDoctorTime fdDoctorTime) {
		Json j = new Json();		
		fdDoctorTimeService.edit(fdDoctorTime);
		j.setSuccess(true);
		j.setMsg("编辑成功！");		
		return j;
	}

	/**
	 * 删除FdDoctorTime
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public Json delete(Integer id) {
		Json j = new Json();
		fdDoctorTimeService.delete(id);
		j.setMsg("删除成功！");
		j.setSuccess(true);
		return j;
	}

}
