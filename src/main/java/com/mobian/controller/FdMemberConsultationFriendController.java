package com.mobian.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mobian.pageModel.Colum;
import com.mobian.pageModel.FdMemberConsultationFriend;
import com.mobian.pageModel.DataGrid;
import com.mobian.pageModel.Json;
import com.mobian.pageModel.PageHelper;
import com.mobian.service.FdMemberConsultationFriendServiceI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

/**
 * FdMemberConsultationFriend管理控制器
 * 
 * @author John
 * 
 */
@Controller
@RequestMapping("/fdMemberConsultationFriendController")
public class FdMemberConsultationFriendController extends BaseController {

	@Autowired
	private FdMemberConsultationFriendServiceI fdMemberConsultationFriendService;


	/**
	 * 跳转到FdMemberConsultationFriend管理页面
	 * 
	 * @return
	 */
	@RequestMapping("/manager")
	public String manager(HttpServletRequest request) {
		return "/fdmemberconsultationfriend/fdMemberConsultationFriend";
	}

	/**
	 * 获取FdMemberConsultationFriend数据表格
	 * 
	 * @param user
	 * @return
	 */
	@RequestMapping("/dataGrid")
	@ResponseBody
	public DataGrid dataGrid(FdMemberConsultationFriend fdMemberConsultationFriend, PageHelper ph) {
		return fdMemberConsultationFriendService.dataGrid(fdMemberConsultationFriend, ph);
	}
	/**
	 * 获取FdMemberConsultationFriend数据表格excel
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
	public void download(FdMemberConsultationFriend fdMemberConsultationFriend, PageHelper ph,String downloadFields,HttpServletResponse response) throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException, IOException{
		DataGrid dg = dataGrid(fdMemberConsultationFriend,ph);		
		downloadFields = downloadFields.replace("&quot;", "\"");
		downloadFields = downloadFields.substring(1,downloadFields.length()-1);
		List<Colum> colums = JSON.parseArray(downloadFields, Colum.class);
		downloadTable(colums, dg, response);
	}
	/**
	 * 跳转到添加FdMemberConsultationFriend页面
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/addPage")
	public String addPage(HttpServletRequest request) {
		FdMemberConsultationFriend fdMemberConsultationFriend = new FdMemberConsultationFriend();
		return "/fdmemberconsultationfriend/fdMemberConsultationFriendAdd";
	}

	/**
	 * 添加FdMemberConsultationFriend
	 * 
	 * @return
	 */
	@RequestMapping("/add")
	@ResponseBody
	public Json add(FdMemberConsultationFriend fdMemberConsultationFriend) {
		Json j = new Json();		
		fdMemberConsultationFriendService.add(fdMemberConsultationFriend);
		j.setSuccess(true);
		j.setMsg("添加成功！");		
		return j;
	}

	/**
	 * 跳转到FdMemberConsultationFriend查看页面
	 * 
	 * @return
	 */
	@RequestMapping("/view")
	public String view(HttpServletRequest request, Integer id) {
		FdMemberConsultationFriend fdMemberConsultationFriend = fdMemberConsultationFriendService.get(id);
		request.setAttribute("fdMemberConsultationFriend", fdMemberConsultationFriend);
		return "/fdmemberconsultationfriend/fdMemberConsultationFriendView";
	}

	/**
	 * 跳转到FdMemberConsultationFriend修改页面
	 * 
	 * @return
	 */
	@RequestMapping("/editPage")
	public String editPage(HttpServletRequest request, Integer id) {
		FdMemberConsultationFriend fdMemberConsultationFriend = fdMemberConsultationFriendService.get(id);
		request.setAttribute("fdMemberConsultationFriend", fdMemberConsultationFriend);
		return "/fdmemberconsultationfriend/fdMemberConsultationFriendEdit";
	}

	/**
	 * 修改FdMemberConsultationFriend
	 * 
	 * @param fdMemberConsultationFriend
	 * @return
	 */
	@RequestMapping("/edit")
	@ResponseBody
	public Json edit(FdMemberConsultationFriend fdMemberConsultationFriend) {
		Json j = new Json();		
		fdMemberConsultationFriendService.edit(fdMemberConsultationFriend);
		j.setSuccess(true);
		j.setMsg("编辑成功！");		
		return j;
	}

	/**
	 * 删除FdMemberConsultationFriend
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public Json delete(Integer id) {
		Json j = new Json();
		fdMemberConsultationFriendService.delete(id);
		j.setMsg("删除成功！");
		j.setSuccess(true);
		return j;
	}

}
