package com.mobian.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mobian.pageModel.Colum;
import com.mobian.pageModel.FdMemberDoctorLevel;
import com.mobian.pageModel.DataGrid;
import com.mobian.pageModel.Json;
import com.mobian.pageModel.PageHelper;
import com.mobian.service.FdMemberDoctorLevelServiceI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

/**
 * FdMemberDoctorLevel管理控制器
 * 
 * @author John
 * 
 */
@Controller
@RequestMapping("/fdMemberDoctorLevelController")
public class FdMemberDoctorLevelController extends BaseController {

	@Autowired
	private FdMemberDoctorLevelServiceI fdMemberDoctorLevelService;


	/**
	 * 跳转到FdMemberDoctorLevel管理页面
	 * 
	 * @return
	 */
	@RequestMapping("/manager")
	public String manager(HttpServletRequest request) {
		return "/fdmemberdoctorlevel/fdMemberDoctorLevel";
	}

	/**
	 * 获取FdMemberDoctorLevel数据表格
	 * 
	 * @param user
	 * @return
	 */
	@RequestMapping("/dataGrid")
	@ResponseBody
	public DataGrid dataGrid(FdMemberDoctorLevel fdMemberDoctorLevel, PageHelper ph) {
		return fdMemberDoctorLevelService.dataGrid(fdMemberDoctorLevel, ph);
	}
	/**
	 * 获取FdMemberDoctorLevel数据表格excel
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
	public void download(FdMemberDoctorLevel fdMemberDoctorLevel, PageHelper ph,String downloadFields,HttpServletResponse response) throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException, IOException{
		DataGrid dg = dataGrid(fdMemberDoctorLevel,ph);		
		downloadFields = downloadFields.replace("&quot;", "\"");
		downloadFields = downloadFields.substring(1,downloadFields.length()-1);
		List<Colum> colums = JSON.parseArray(downloadFields, Colum.class);
		downloadTable(colums, dg, response);
	}
	/**
	 * 跳转到添加FdMemberDoctorLevel页面
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/addPage")
	public String addPage(HttpServletRequest request) {
		FdMemberDoctorLevel fdMemberDoctorLevel = new FdMemberDoctorLevel();
		return "/fdmemberdoctorlevel/fdMemberDoctorLevelAdd";
	}

	/**
	 * 添加FdMemberDoctorLevel
	 * 
	 * @return
	 */
	@RequestMapping("/add")
	@ResponseBody
	public Json add(FdMemberDoctorLevel fdMemberDoctorLevel) {
		Json j = new Json();		
		fdMemberDoctorLevelService.add(fdMemberDoctorLevel);
		j.setSuccess(true);
		j.setMsg("添加成功！");		
		return j;
	}

	/**
	 * 跳转到FdMemberDoctorLevel查看页面
	 * 
	 * @return
	 */
	@RequestMapping("/view")
	public String view(HttpServletRequest request, Integer id) {
		FdMemberDoctorLevel fdMemberDoctorLevel = fdMemberDoctorLevelService.get(id);
		request.setAttribute("fdMemberDoctorLevel", fdMemberDoctorLevel);
		return "/fdmemberdoctorlevel/fdMemberDoctorLevelView";
	}

	/**
	 * 跳转到FdMemberDoctorLevel修改页面
	 * 
	 * @return
	 */
	@RequestMapping("/editPage")
	public String editPage(HttpServletRequest request, Integer id) {
		FdMemberDoctorLevel fdMemberDoctorLevel = fdMemberDoctorLevelService.get(id);
		request.setAttribute("fdMemberDoctorLevel", fdMemberDoctorLevel);
		return "/fdmemberdoctorlevel/fdMemberDoctorLevelEdit";
	}

	/**
	 * 修改FdMemberDoctorLevel
	 * 
	 * @param fdMemberDoctorLevel
	 * @return
	 */
	@RequestMapping("/edit")
	@ResponseBody
	public Json edit(FdMemberDoctorLevel fdMemberDoctorLevel) {
		Json j = new Json();		
		fdMemberDoctorLevelService.edit(fdMemberDoctorLevel);
		j.setSuccess(true);
		j.setMsg("编辑成功！");		
		return j;
	}

	/**
	 * 删除FdMemberDoctorLevel
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public Json delete(Integer id) {
		Json j = new Json();
		fdMemberDoctorLevelService.delete(id);
		j.setMsg("删除成功！");
		j.setSuccess(true);
		return j;
	}

}
