package com.mobian.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mobian.pageModel.Colum;
import com.mobian.pageModel.FdDoctorCloseTime;
import com.mobian.pageModel.DataGrid;
import com.mobian.pageModel.Json;
import com.mobian.pageModel.PageHelper;
import com.mobian.service.FdDoctorCloseTimeServiceI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

/**
 * FdDoctorCloseTime管理控制器
 * 
 * @author John
 * 
 */
@Controller
@RequestMapping("/fdDoctorCloseTimeController")
public class FdDoctorCloseTimeController extends BaseController {

	@Autowired
	private FdDoctorCloseTimeServiceI fdDoctorCloseTimeService;


	/**
	 * 跳转到FdDoctorCloseTime管理页面
	 * 
	 * @return
	 */
	@RequestMapping("/manager")
	public String manager(HttpServletRequest request) {
		return "/fddoctorclosetime/fdDoctorCloseTime";
	}

	/**
	 * 获取FdDoctorCloseTime数据表格
	 * 
	 * @param user
	 * @return
	 */
	@RequestMapping("/dataGrid")
	@ResponseBody
	public DataGrid dataGrid(FdDoctorCloseTime fdDoctorCloseTime, PageHelper ph) {
		return fdDoctorCloseTimeService.dataGrid(fdDoctorCloseTime, ph);
	}
	/**
	 * 获取FdDoctorCloseTime数据表格excel
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
	public void download(FdDoctorCloseTime fdDoctorCloseTime, PageHelper ph,String downloadFields,HttpServletResponse response) throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException, IOException{
		DataGrid dg = dataGrid(fdDoctorCloseTime,ph);		
		downloadFields = downloadFields.replace("&quot;", "\"");
		downloadFields = downloadFields.substring(1,downloadFields.length()-1);
		List<Colum> colums = JSON.parseArray(downloadFields, Colum.class);
		downloadTable(colums, dg, response);
	}
	/**
	 * 跳转到添加FdDoctorCloseTime页面
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/addPage")
	public String addPage(HttpServletRequest request) {
		FdDoctorCloseTime fdDoctorCloseTime = new FdDoctorCloseTime();
		return "/fddoctorclosetime/fdDoctorCloseTimeAdd";
	}

	/**
	 * 添加FdDoctorCloseTime
	 * 
	 * @return
	 */
	@RequestMapping("/add")
	@ResponseBody
	public Json add(FdDoctorCloseTime fdDoctorCloseTime) {
		Json j = new Json();		
		fdDoctorCloseTimeService.add(fdDoctorCloseTime);
		j.setSuccess(true);
		j.setMsg("添加成功！");		
		return j;
	}

	/**
	 * 跳转到FdDoctorCloseTime查看页面
	 * 
	 * @return
	 */
	@RequestMapping("/view")
	public String view(HttpServletRequest request, Integer id) {
		FdDoctorCloseTime fdDoctorCloseTime = fdDoctorCloseTimeService.get(id);
		request.setAttribute("fdDoctorCloseTime", fdDoctorCloseTime);
		return "/fddoctorclosetime/fdDoctorCloseTimeView";
	}

	/**
	 * 跳转到FdDoctorCloseTime修改页面
	 * 
	 * @return
	 */
	@RequestMapping("/editPage")
	public String editPage(HttpServletRequest request, Integer id) {
		FdDoctorCloseTime fdDoctorCloseTime = fdDoctorCloseTimeService.get(id);
		request.setAttribute("fdDoctorCloseTime", fdDoctorCloseTime);
		return "/fddoctorclosetime/fdDoctorCloseTimeEdit";
	}

	/**
	 * 修改FdDoctorCloseTime
	 * 
	 * @param fdDoctorCloseTime
	 * @return
	 */
	@RequestMapping("/edit")
	@ResponseBody
	public Json edit(FdDoctorCloseTime fdDoctorCloseTime) {
		Json j = new Json();		
		fdDoctorCloseTimeService.edit(fdDoctorCloseTime);
		j.setSuccess(true);
		j.setMsg("编辑成功！");		
		return j;
	}

	/**
	 * 删除FdDoctorCloseTime
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public Json delete(Integer id) {
		Json j = new Json();
		fdDoctorCloseTimeService.delete(id);
		j.setMsg("删除成功！");
		j.setSuccess(true);
		return j;
	}

}
