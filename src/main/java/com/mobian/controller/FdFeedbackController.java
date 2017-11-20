package com.mobian.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mobian.pageModel.Colum;
import com.mobian.pageModel.FdFeedback;
import com.mobian.pageModel.DataGrid;
import com.mobian.pageModel.Json;
import com.mobian.pageModel.PageHelper;
import com.mobian.service.FdFeedbackServiceI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

/**
 * FdFeedback管理控制器
 * 
 * @author John
 * 
 */
@Controller
@RequestMapping("/fdFeedbackController")
public class FdFeedbackController extends BaseController {

	@Autowired
	private FdFeedbackServiceI fdFeedbackService;


	/**
	 * 跳转到FdFeedback管理页面
	 * 
	 * @return
	 */
	@RequestMapping("/manager")
	public String manager(HttpServletRequest request) {
		return "/fdfeedback/fdFeedback";
	}

	/**
	 * 获取FdFeedback数据表格
	 * 
	 * @param user
	 * @return
	 */
	@RequestMapping("/dataGrid")
	@ResponseBody
	public DataGrid dataGrid(FdFeedback fdFeedback, PageHelper ph) {
		return fdFeedbackService.dataGrid(fdFeedback, ph);
	}
	/**
	 * 获取FdFeedback数据表格excel
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
	public void download(FdFeedback fdFeedback, PageHelper ph,String downloadFields,HttpServletResponse response) throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException, IOException{
		DataGrid dg = dataGrid(fdFeedback,ph);		
		downloadFields = downloadFields.replace("&quot;", "\"");
		downloadFields = downloadFields.substring(1,downloadFields.length()-1);
		List<Colum> colums = JSON.parseArray(downloadFields, Colum.class);
		downloadTable(colums, dg, response);
	}
	/**
	 * 跳转到添加FdFeedback页面
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/addPage")
	public String addPage(HttpServletRequest request) {
		FdFeedback fdFeedback = new FdFeedback();
		return "/fdfeedback/fdFeedbackAdd";
	}

	/**
	 * 添加FdFeedback
	 * 
	 * @return
	 */
	@RequestMapping("/add")
	@ResponseBody
	public Json add(FdFeedback fdFeedback) {
		Json j = new Json();		
		fdFeedbackService.add(fdFeedback);
		j.setSuccess(true);
		j.setMsg("添加成功！");		
		return j;
	}

	/**
	 * 跳转到FdFeedback查看页面
	 * 
	 * @return
	 */
	@RequestMapping("/view")
	public String view(HttpServletRequest request, Integer id) {
		FdFeedback fdFeedback = fdFeedbackService.get(id);
		request.setAttribute("fdFeedback", fdFeedback);
		return "/fdfeedback/fdFeedbackView";
	}

	/**
	 * 跳转到FdFeedback修改页面
	 * 
	 * @return
	 */
	@RequestMapping("/editPage")
	public String editPage(HttpServletRequest request, Integer id) {
		FdFeedback fdFeedback = fdFeedbackService.get(id);
		request.setAttribute("fdFeedback", fdFeedback);
		return "/fdfeedback/fdFeedbackEdit";
	}

	/**
	 * 修改FdFeedback
	 * 
	 * @param fdFeedback
	 * @return
	 */
	@RequestMapping("/edit")
	@ResponseBody
	public Json edit(FdFeedback fdFeedback) {
		Json j = new Json();		
		fdFeedbackService.edit(fdFeedback);
		j.setSuccess(true);
		j.setMsg("编辑成功！");		
		return j;
	}

	/**
	 * 删除FdFeedback
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public Json delete(Integer id) {
		Json j = new Json();
		fdFeedbackService.delete(id);
		j.setMsg("删除成功！");
		j.setSuccess(true);
		return j;
	}

}
