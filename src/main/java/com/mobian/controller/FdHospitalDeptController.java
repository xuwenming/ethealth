package com.mobian.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mobian.pageModel.Colum;
import com.mobian.pageModel.FdHospitalDept;
import com.mobian.pageModel.DataGrid;
import com.mobian.pageModel.Json;
import com.mobian.pageModel.PageHelper;
import com.mobian.service.FdHospitalDeptServiceI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import org.springframework.web.multipart.MultipartFile;

/**
 * FdHospitalDept管理控制器
 * 
 * @author John
 * 
 */
@Controller
@RequestMapping("/fdHospitalDeptController")
public class FdHospitalDeptController extends BaseController {

	public static final String HOSPITAL_DEPT = "hospitalDept";

	@Autowired
	private FdHospitalDeptServiceI fdHospitalDeptService;


	/**
	 * 跳转到FdHospitalDept管理页面
	 * 
	 * @return
	 */
	@RequestMapping("/manager")
	public String manager(HttpServletRequest request) {
		return "/fdhospitaldept/fdHospitalDept";
	}

	/**
	 * 获取FdHospitalDept数据表格
	 * 
	 * @param user
	 * @return
	 */
	@RequestMapping("/dataGrid")
	@ResponseBody
	public DataGrid dataGrid(FdHospitalDept fdHospitalDept, PageHelper ph) {
		return fdHospitalDeptService.dataGrid(fdHospitalDept, ph);
	}
	/**
	 * 获取FdHospitalDept数据表格excel
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
	public void download(FdHospitalDept fdHospitalDept, PageHelper ph,String downloadFields,HttpServletResponse response) throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException, IOException{
		DataGrid dg = dataGrid(fdHospitalDept,ph);		
		downloadFields = downloadFields.replace("&quot;", "\"");
		downloadFields = downloadFields.substring(1,downloadFields.length()-1);
		List<Colum> colums = JSON.parseArray(downloadFields, Colum.class);
		downloadTable(colums, dg, response);
	}
	/**
	 * 跳转到添加FdHospitalDept页面
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/addPage")
	public String addPage(HttpServletRequest request) {
		FdHospitalDept fdHospitalDept = new FdHospitalDept();
		return "/fdhospitaldept/fdHospitalDeptAdd";
	}

	/**
	 * 添加FdHospitalDept
	 * 
	 * @return
	 */
	@RequestMapping("/add")
	@ResponseBody
	public Json add(FdHospitalDept fdHospitalDept, @RequestParam(required = false) MultipartFile iconFile) {
		Json j = new Json();
		fdHospitalDept.setIcon(uploadFile(HOSPITAL_DEPT, iconFile));
		fdHospitalDeptService.add(fdHospitalDept);
		j.setSuccess(true);
		j.setMsg("添加成功！");		
		return j;
	}

	/**
	 * 跳转到FdHospitalDept查看页面
	 * 
	 * @return
	 */
	@RequestMapping("/view")
	public String view(HttpServletRequest request, Integer id) {
		FdHospitalDept fdHospitalDept = fdHospitalDeptService.get(id);
		request.setAttribute("fdHospitalDept", fdHospitalDept);
		return "/fdhospitaldept/fdHospitalDeptView";
	}

	/**
	 * 跳转到FdHospitalDept修改页面
	 * 
	 * @return
	 */
	@RequestMapping("/editPage")
	public String editPage(HttpServletRequest request, Integer id) {
		FdHospitalDept fdHospitalDept = fdHospitalDeptService.get(id);
		request.setAttribute("fdHospitalDept", fdHospitalDept);
		return "/fdhospitaldept/fdHospitalDeptEdit";
	}

	/**
	 * 修改FdHospitalDept
	 * 
	 * @param fdHospitalDept
	 * @return
	 */
	@RequestMapping("/edit")
	@ResponseBody
	public Json edit(FdHospitalDept fdHospitalDept, @RequestParam MultipartFile iconFile) {
		Json j = new Json();
		fdHospitalDept.setIcon(uploadFile(HOSPITAL_DEPT, iconFile));
		fdHospitalDeptService.edit(fdHospitalDept);
		j.setSuccess(true);
		j.setMsg("编辑成功！");		
		return j;
	}

	/**
	 * 删除FdHospitalDept
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public Json delete(Integer id) {
		Json j = new Json();
		fdHospitalDeptService.delete(id);
		j.setMsg("删除成功！");
		j.setSuccess(true);
		return j;
	}

}
