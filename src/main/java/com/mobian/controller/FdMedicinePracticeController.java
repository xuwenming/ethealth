package com.mobian.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mobian.pageModel.Colum;
import com.mobian.pageModel.FdMedicinePractice;
import com.mobian.pageModel.DataGrid;
import com.mobian.pageModel.Json;
import com.mobian.pageModel.PageHelper;
import com.mobian.service.FdMedicinePracticeServiceI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

/**
 * FdMedicinePractice管理控制器
 * 
 * @author John
 * 
 */
@Controller
@RequestMapping("/fdMedicinePracticeController")
public class FdMedicinePracticeController extends BaseController {

	@Autowired
	private FdMedicinePracticeServiceI fdMedicinePracticeService;


	/**
	 * 跳转到FdMedicinePractice管理页面
	 * 
	 * @return
	 */
	@RequestMapping("/manager")
	public String manager(HttpServletRequest request) {
		return "/fdmedicinepractice/fdMedicinePractice";
	}

	/**
	 * 获取FdMedicinePractice数据表格
	 * 
	 * @param user
	 * @return
	 */
	@RequestMapping("/dataGrid")
	@ResponseBody
	public DataGrid dataGrid(FdMedicinePractice fdMedicinePractice, PageHelper ph) {
		return fdMedicinePracticeService.dataGrid(fdMedicinePractice, ph);
	}
	/**
	 * 获取FdMedicinePractice数据表格excel
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
	public void download(FdMedicinePractice fdMedicinePractice, PageHelper ph,String downloadFields,HttpServletResponse response) throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException, IOException{
		DataGrid dg = dataGrid(fdMedicinePractice,ph);		
		downloadFields = downloadFields.replace("&quot;", "\"");
		downloadFields = downloadFields.substring(1,downloadFields.length()-1);
		List<Colum> colums = JSON.parseArray(downloadFields, Colum.class);
		downloadTable(colums, dg, response);
	}
	/**
	 * 跳转到添加FdMedicinePractice页面
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/addPage")
	public String addPage(HttpServletRequest request) {
		FdMedicinePractice fdMedicinePractice = new FdMedicinePractice();
		return "/fdmedicinepractice/fdMedicinePracticeAdd";
	}

	/**
	 * 添加FdMedicinePractice
	 * 
	 * @return
	 */
	@RequestMapping("/add")
	@ResponseBody
	public Json add(FdMedicinePractice fdMedicinePractice) {
		Json j = new Json();		
		fdMedicinePracticeService.add(fdMedicinePractice);
		j.setSuccess(true);
		j.setMsg("添加成功！");		
		return j;
	}

	/**
	 * 跳转到FdMedicinePractice查看页面
	 * 
	 * @return
	 */
	@RequestMapping("/view")
	public String view(HttpServletRequest request, Integer id) {
		FdMedicinePractice fdMedicinePractice = fdMedicinePracticeService.get(id);
		request.setAttribute("fdMedicinePractice", fdMedicinePractice);
		return "/fdmedicinepractice/fdMedicinePracticeView";
	}

	/**
	 * 跳转到FdMedicinePractice修改页面
	 * 
	 * @return
	 */
	@RequestMapping("/editPage")
	public String editPage(HttpServletRequest request, Integer id) {
		FdMedicinePractice fdMedicinePractice = fdMedicinePracticeService.get(id);
		request.setAttribute("fdMedicinePractice", fdMedicinePractice);
		return "/fdmedicinepractice/fdMedicinePracticeEdit";
	}

	/**
	 * 修改FdMedicinePractice
	 * 
	 * @param fdMedicinePractice
	 * @return
	 */
	@RequestMapping("/edit")
	@ResponseBody
	public Json edit(FdMedicinePractice fdMedicinePractice) {
		Json j = new Json();		
		fdMedicinePracticeService.edit(fdMedicinePractice);
		j.setSuccess(true);
		j.setMsg("编辑成功！");		
		return j;
	}

	/**
	 * 删除FdMedicinePractice
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public Json delete(Integer id) {
		Json j = new Json();
		fdMedicinePracticeService.delete(id);
		j.setMsg("删除成功！");
		j.setSuccess(true);
		return j;
	}

}
