package com.mobian.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mobian.pageModel.Colum;
import com.mobian.pageModel.FdMemberDoctorSh;
import com.mobian.pageModel.DataGrid;
import com.mobian.pageModel.Json;
import com.mobian.pageModel.PageHelper;
import com.mobian.service.FdMemberDoctorShServiceI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

/**
 * FdMemberDoctorSh管理控制器
 * 
 * @author John
 * 
 */
@Controller
@RequestMapping("/fdMemberDoctorShController")
public class FdMemberDoctorShController extends BaseController {

	@Autowired
	private FdMemberDoctorShServiceI fdMemberDoctorShService;


	/**
	 * 跳转到FdMemberDoctorSh管理页面
	 * 
	 * @return
	 */
	@RequestMapping("/manager")
	public String manager(HttpServletRequest request) {
		return "/fdmemberdoctorsh/fdMemberDoctorSh";
	}

	/**
	 * 获取FdMemberDoctorSh数据表格
	 * 
	 * @param user
	 * @return
	 */
	@RequestMapping("/dataGrid")
	@ResponseBody
	public DataGrid dataGrid(FdMemberDoctorSh fdMemberDoctorSh, PageHelper ph) {
		return fdMemberDoctorShService.dataGrid(fdMemberDoctorSh, ph);
	}
	/**
	 * 获取FdMemberDoctorSh数据表格excel
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
	public void download(FdMemberDoctorSh fdMemberDoctorSh, PageHelper ph,String downloadFields,HttpServletResponse response) throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException, IOException{
		DataGrid dg = dataGrid(fdMemberDoctorSh,ph);		
		downloadFields = downloadFields.replace("&quot;", "\"");
		downloadFields = downloadFields.substring(1,downloadFields.length()-1);
		List<Colum> colums = JSON.parseArray(downloadFields, Colum.class);
		downloadTable(colums, dg, response);
	}
	/**
	 * 跳转到添加FdMemberDoctorSh页面
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/addPage")
	public String addPage(HttpServletRequest request) {
		FdMemberDoctorSh fdMemberDoctorSh = new FdMemberDoctorSh();
		return "/fdmemberdoctorsh/fdMemberDoctorShAdd";
	}

	/**
	 * 添加FdMemberDoctorSh
	 * 
	 * @return
	 */
	@RequestMapping("/add")
	@ResponseBody
	public Json add(FdMemberDoctorSh fdMemberDoctorSh) {
		Json j = new Json();		
		fdMemberDoctorShService.add(fdMemberDoctorSh);
		j.setSuccess(true);
		j.setMsg("添加成功！");		
		return j;
	}

	/**
	 * 跳转到FdMemberDoctorSh查看页面
	 * 
	 * @return
	 */
	@RequestMapping("/view")
	public String view(HttpServletRequest request, Integer id) {
		FdMemberDoctorSh fdMemberDoctorSh = fdMemberDoctorShService.get(id);
		request.setAttribute("fdMemberDoctorSh", fdMemberDoctorSh);
		return "/fdmemberdoctorsh/fdMemberDoctorShView";
	}

	/**
	 * 跳转到FdMemberDoctorSh修改页面
	 * 
	 * @return
	 */
	@RequestMapping("/editPage")
	public String editPage(HttpServletRequest request, Integer id) {
		FdMemberDoctorSh fdMemberDoctorSh = fdMemberDoctorShService.get(id);
		request.setAttribute("fdMemberDoctorSh", fdMemberDoctorSh);
		return "/fdmemberdoctorsh/fdMemberDoctorShEdit";
	}

	/**
	 * 修改FdMemberDoctorSh
	 * 
	 * @param fdMemberDoctorSh
	 * @return
	 */
	@RequestMapping("/edit")
	@ResponseBody
	public Json edit(FdMemberDoctorSh fdMemberDoctorSh) {
		Json j = new Json();		
		fdMemberDoctorShService.edit(fdMemberDoctorSh);
		j.setSuccess(true);
		j.setMsg("编辑成功！");		
		return j;
	}

	/**
	 * 删除FdMemberDoctorSh
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public Json delete(Integer id) {
		Json j = new Json();
		fdMemberDoctorShService.delete(id);
		j.setMsg("删除成功！");
		j.setSuccess(true);
		return j;
	}

}
