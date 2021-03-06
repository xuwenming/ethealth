package com.mobian.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mobian.pageModel.Colum;
import com.mobian.pageModel.FdMemberConsultationExpire;
import com.mobian.pageModel.DataGrid;
import com.mobian.pageModel.Json;
import com.mobian.pageModel.PageHelper;
import com.mobian.service.FdMemberConsultationExpireServiceI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

/**
 * FdMemberConsultationExpire管理控制器
 * 
 * @author John
 * 
 */
@Controller
@RequestMapping("/fdMemberConsultationExpireController")
public class FdMemberConsultationExpireController extends BaseController {

	@Autowired
	private FdMemberConsultationExpireServiceI fdMemberConsultationExpireService;


	/**
	 * 跳转到FdMemberConsultationExpire管理页面
	 * 
	 * @return
	 */
	@RequestMapping("/manager")
	public String manager(HttpServletRequest request) {
		return "/fdmemberconsultationexpire/fdMemberConsultationExpire";
	}

	/**
	 * 获取FdMemberConsultationExpire数据表格
	 * 
	 * @param user
	 * @return
	 */
	@RequestMapping("/dataGrid")
	@ResponseBody
	public DataGrid dataGrid(FdMemberConsultationExpire fdMemberConsultationExpire, PageHelper ph) {
		return fdMemberConsultationExpireService.dataGrid(fdMemberConsultationExpire, ph);
	}
	/**
	 * 获取FdMemberConsultationExpire数据表格excel
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
	public void download(FdMemberConsultationExpire fdMemberConsultationExpire, PageHelper ph,String downloadFields,HttpServletResponse response) throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException, IOException{
		DataGrid dg = dataGrid(fdMemberConsultationExpire,ph);		
		downloadFields = downloadFields.replace("&quot;", "\"");
		downloadFields = downloadFields.substring(1,downloadFields.length()-1);
		List<Colum> colums = JSON.parseArray(downloadFields, Colum.class);
		downloadTable(colums, dg, response);
	}
	/**
	 * 跳转到添加FdMemberConsultationExpire页面
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/addPage")
	public String addPage(HttpServletRequest request) {
		FdMemberConsultationExpire fdMemberConsultationExpire = new FdMemberConsultationExpire();
		return "/fdmemberconsultationexpire/fdMemberConsultationExpireAdd";
	}

	/**
	 * 添加FdMemberConsultationExpire
	 * 
	 * @return
	 */
	@RequestMapping("/add")
	@ResponseBody
	public Json add(FdMemberConsultationExpire fdMemberConsultationExpire) {
		Json j = new Json();		
		fdMemberConsultationExpireService.add(fdMemberConsultationExpire);
		j.setSuccess(true);
		j.setMsg("添加成功！");		
		return j;
	}

	/**
	 * 跳转到FdMemberConsultationExpire查看页面
	 * 
	 * @return
	 */
	@RequestMapping("/view")
	public String view(HttpServletRequest request, Integer id) {
		FdMemberConsultationExpire fdMemberConsultationExpire = fdMemberConsultationExpireService.get(id);
		request.setAttribute("fdMemberConsultationExpire", fdMemberConsultationExpire);
		return "/fdmemberconsultationexpire/fdMemberConsultationExpireView";
	}

	/**
	 * 跳转到FdMemberConsultationExpire修改页面
	 * 
	 * @return
	 */
	@RequestMapping("/editPage")
	public String editPage(HttpServletRequest request, Integer id) {
		FdMemberConsultationExpire fdMemberConsultationExpire = fdMemberConsultationExpireService.get(id);
		request.setAttribute("fdMemberConsultationExpire", fdMemberConsultationExpire);
		return "/fdmemberconsultationexpire/fdMemberConsultationExpireEdit";
	}

	/**
	 * 修改FdMemberConsultationExpire
	 * 
	 * @param fdMemberConsultationExpire
	 * @return
	 */
	@RequestMapping("/edit")
	@ResponseBody
	public Json edit(FdMemberConsultationExpire fdMemberConsultationExpire) {
		Json j = new Json();		
		fdMemberConsultationExpireService.edit(fdMemberConsultationExpire);
		j.setSuccess(true);
		j.setMsg("编辑成功！");		
		return j;
	}

	/**
	 * 删除FdMemberConsultationExpire
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public Json delete(Integer id) {
		Json j = new Json();
		fdMemberConsultationExpireService.delete(id);
		j.setMsg("删除成功！");
		j.setSuccess(true);
		return j;
	}

}
