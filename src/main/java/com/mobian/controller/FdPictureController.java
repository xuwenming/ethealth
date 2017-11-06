package com.mobian.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mobian.pageModel.Colum;
import com.mobian.pageModel.FdPicture;
import com.mobian.pageModel.DataGrid;
import com.mobian.pageModel.Json;
import com.mobian.pageModel.PageHelper;
import com.mobian.service.FdPictureServiceI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

/**
 * FdPicture管理控制器
 * 
 * @author John
 * 
 */
@Controller
@RequestMapping("/fdPictureController")
public class FdPictureController extends BaseController {

	@Autowired
	private FdPictureServiceI fdPictureService;


	/**
	 * 跳转到FdPicture管理页面
	 * 
	 * @return
	 */
	@RequestMapping("/manager")
	public String manager(HttpServletRequest request) {
		return "/fdpicture/fdPicture";
	}

	/**
	 * 获取FdPicture数据表格
	 * 
	 * @param user
	 * @return
	 */
	@RequestMapping("/dataGrid")
	@ResponseBody
	public DataGrid dataGrid(FdPicture fdPicture, PageHelper ph) {
		return fdPictureService.dataGrid(fdPicture, ph);
	}
	/**
	 * 获取FdPicture数据表格excel
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
	public void download(FdPicture fdPicture, PageHelper ph,String downloadFields,HttpServletResponse response) throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException, IOException{
		DataGrid dg = dataGrid(fdPicture,ph);		
		downloadFields = downloadFields.replace("&quot;", "\"");
		downloadFields = downloadFields.substring(1,downloadFields.length()-1);
		List<Colum> colums = JSON.parseArray(downloadFields, Colum.class);
		downloadTable(colums, dg, response);
	}
	/**
	 * 跳转到添加FdPicture页面
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/addPage")
	public String addPage(HttpServletRequest request) {
		FdPicture fdPicture = new FdPicture();
		return "/fdpicture/fdPictureAdd";
	}

	/**
	 * 添加FdPicture
	 * 
	 * @return
	 */
	@RequestMapping("/add")
	@ResponseBody
	public Json add(FdPicture fdPicture) {
		Json j = new Json();		
		fdPictureService.add(fdPicture);
		j.setSuccess(true);
		j.setMsg("添加成功！");		
		return j;
	}

	/**
	 * 跳转到FdPicture查看页面
	 * 
	 * @return
	 */
	@RequestMapping("/view")
	public String view(HttpServletRequest request, Integer id) {
		FdPicture fdPicture = fdPictureService.get(id);
		request.setAttribute("fdPicture", fdPicture);
		return "/fdpicture/fdPictureView";
	}

	/**
	 * 跳转到FdPicture修改页面
	 * 
	 * @return
	 */
	@RequestMapping("/editPage")
	public String editPage(HttpServletRequest request, Integer id) {
		FdPicture fdPicture = fdPictureService.get(id);
		request.setAttribute("fdPicture", fdPicture);
		return "/fdpicture/fdPictureEdit";
	}

	/**
	 * 修改FdPicture
	 * 
	 * @param fdPicture
	 * @return
	 */
	@RequestMapping("/edit")
	@ResponseBody
	public Json edit(FdPicture fdPicture) {
		Json j = new Json();		
		fdPictureService.edit(fdPicture);
		j.setSuccess(true);
		j.setMsg("编辑成功！");		
		return j;
	}

	/**
	 * 删除FdPicture
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public Json delete(Integer id) {
		Json j = new Json();
		fdPictureService.delete(id);
		j.setMsg("删除成功！");
		j.setSuccess(true);
		return j;
	}

}
