package com.mobian.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mobian.pageModel.Colum;
import com.mobian.pageModel.FdMemberAppointmentComment;
import com.mobian.pageModel.DataGrid;
import com.mobian.pageModel.Json;
import com.mobian.pageModel.PageHelper;
import com.mobian.service.FdMemberAppointmentCommentServiceI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

/**
 * FdMemberAppointmentComment管理控制器
 * 
 * @author John
 * 
 */
@Controller
@RequestMapping("/fdMemberAppointmentCommentController")
public class FdMemberAppointmentCommentController extends BaseController {

	@Autowired
	private FdMemberAppointmentCommentServiceI fdMemberAppointmentCommentService;


	/**
	 * 跳转到FdMemberAppointmentComment管理页面
	 * 
	 * @return
	 */
	@RequestMapping("/manager")
	public String manager(HttpServletRequest request) {
		return "/fdmemberappointmentcomment/fdMemberAppointmentComment";
	}

	/**
	 * 获取FdMemberAppointmentComment数据表格
	 * 
	 * @param user
	 * @return
	 */
	@RequestMapping("/dataGrid")
	@ResponseBody
	public DataGrid dataGrid(FdMemberAppointmentComment fdMemberAppointmentComment, PageHelper ph) {
		return fdMemberAppointmentCommentService.dataGrid(fdMemberAppointmentComment, ph);
	}
	/**
	 * 获取FdMemberAppointmentComment数据表格excel
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
	public void download(FdMemberAppointmentComment fdMemberAppointmentComment, PageHelper ph,String downloadFields,HttpServletResponse response) throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException, IOException{
		DataGrid dg = dataGrid(fdMemberAppointmentComment,ph);		
		downloadFields = downloadFields.replace("&quot;", "\"");
		downloadFields = downloadFields.substring(1,downloadFields.length()-1);
		List<Colum> colums = JSON.parseArray(downloadFields, Colum.class);
		downloadTable(colums, dg, response);
	}
	/**
	 * 跳转到添加FdMemberAppointmentComment页面
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/addPage")
	public String addPage(HttpServletRequest request) {
		FdMemberAppointmentComment fdMemberAppointmentComment = new FdMemberAppointmentComment();
		return "/fdmemberappointmentcomment/fdMemberAppointmentCommentAdd";
	}

	/**
	 * 添加FdMemberAppointmentComment
	 * 
	 * @return
	 */
	@RequestMapping("/add")
	@ResponseBody
	public Json add(FdMemberAppointmentComment fdMemberAppointmentComment) {
		Json j = new Json();		
		fdMemberAppointmentCommentService.add(fdMemberAppointmentComment);
		j.setSuccess(true);
		j.setMsg("添加成功！");		
		return j;
	}

	/**
	 * 跳转到FdMemberAppointmentComment查看页面
	 * 
	 * @return
	 */
	@RequestMapping("/view")
	public String view(HttpServletRequest request, Integer id) {
		FdMemberAppointmentComment fdMemberAppointmentComment = fdMemberAppointmentCommentService.get(id);
		request.setAttribute("fdMemberAppointmentComment", fdMemberAppointmentComment);
		return "/fdmemberappointmentcomment/fdMemberAppointmentCommentView";
	}

	/**
	 * 跳转到FdMemberAppointmentComment修改页面
	 * 
	 * @return
	 */
	@RequestMapping("/editPage")
	public String editPage(HttpServletRequest request, Integer id) {
		FdMemberAppointmentComment fdMemberAppointmentComment = fdMemberAppointmentCommentService.get(id);
		request.setAttribute("fdMemberAppointmentComment", fdMemberAppointmentComment);
		return "/fdmemberappointmentcomment/fdMemberAppointmentCommentEdit";
	}

	/**
	 * 修改FdMemberAppointmentComment
	 * 
	 * @param fdMemberAppointmentComment
	 * @return
	 */
	@RequestMapping("/edit")
	@ResponseBody
	public Json edit(FdMemberAppointmentComment fdMemberAppointmentComment) {
		Json j = new Json();		
		fdMemberAppointmentCommentService.edit(fdMemberAppointmentComment);
		j.setSuccess(true);
		j.setMsg("编辑成功！");		
		return j;
	}

	/**
	 * 删除FdMemberAppointmentComment
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public Json delete(Integer id) {
		Json j = new Json();
		fdMemberAppointmentCommentService.delete(id);
		j.setMsg("删除成功！");
		j.setSuccess(true);
		return j;
	}

}
