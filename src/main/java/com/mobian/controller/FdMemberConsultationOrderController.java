package com.mobian.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mobian.pageModel.Colum;
import com.mobian.pageModel.FdMemberConsultationOrder;
import com.mobian.pageModel.DataGrid;
import com.mobian.pageModel.Json;
import com.mobian.pageModel.PageHelper;
import com.mobian.service.FdMemberConsultationOrderServiceI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

/**
 * FdMemberConsultationOrder管理控制器
 * 
 * @author John
 * 
 */
@Controller
@RequestMapping("/fdMemberConsultationOrderController")
public class FdMemberConsultationOrderController extends BaseController {

	@Autowired
	private FdMemberConsultationOrderServiceI fdMemberConsultationOrderService;


	/**
	 * 跳转到FdMemberConsultationOrder管理页面
	 * 
	 * @return
	 */
	@RequestMapping("/manager")
	public String manager(HttpServletRequest request) {
		return "/fdmemberconsultationorder/fdMemberConsultationOrder";
	}

	/**
	 * 获取FdMemberConsultationOrder数据表格
	 * 
	 * @param user
	 * @return
	 */
	@RequestMapping("/dataGrid")
	@ResponseBody
	public DataGrid dataGrid(FdMemberConsultationOrder fdMemberConsultationOrder, PageHelper ph) {
		return fdMemberConsultationOrderService.dataGrid(fdMemberConsultationOrder, ph);
	}
	/**
	 * 获取FdMemberConsultationOrder数据表格excel
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
	public void download(FdMemberConsultationOrder fdMemberConsultationOrder, PageHelper ph,String downloadFields,HttpServletResponse response) throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException, IOException{
		DataGrid dg = dataGrid(fdMemberConsultationOrder,ph);		
		downloadFields = downloadFields.replace("&quot;", "\"");
		downloadFields = downloadFields.substring(1,downloadFields.length()-1);
		List<Colum> colums = JSON.parseArray(downloadFields, Colum.class);
		downloadTable(colums, dg, response);
	}
	/**
	 * 跳转到添加FdMemberConsultationOrder页面
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/addPage")
	public String addPage(HttpServletRequest request) {
		FdMemberConsultationOrder fdMemberConsultationOrder = new FdMemberConsultationOrder();
		return "/fdmemberconsultationorder/fdMemberConsultationOrderAdd";
	}

	/**
	 * 添加FdMemberConsultationOrder
	 * 
	 * @return
	 */
	@RequestMapping("/add")
	@ResponseBody
	public Json add(FdMemberConsultationOrder fdMemberConsultationOrder) {
		Json j = new Json();		
		fdMemberConsultationOrderService.add(fdMemberConsultationOrder);
		j.setSuccess(true);
		j.setMsg("添加成功！");		
		return j;
	}

	/**
	 * 跳转到FdMemberConsultationOrder查看页面
	 * 
	 * @return
	 */
	@RequestMapping("/view")
	public String view(HttpServletRequest request, Integer id) {
		FdMemberConsultationOrder fdMemberConsultationOrder = fdMemberConsultationOrderService.get(id);
		request.setAttribute("fdMemberConsultationOrder", fdMemberConsultationOrder);
		return "/fdmemberconsultationorder/fdMemberConsultationOrderView";
	}

	/**
	 * 跳转到FdMemberConsultationOrder修改页面
	 * 
	 * @return
	 */
	@RequestMapping("/editPage")
	public String editPage(HttpServletRequest request, Integer id) {
		FdMemberConsultationOrder fdMemberConsultationOrder = fdMemberConsultationOrderService.get(id);
		request.setAttribute("fdMemberConsultationOrder", fdMemberConsultationOrder);
		return "/fdmemberconsultationorder/fdMemberConsultationOrderEdit";
	}

	/**
	 * 修改FdMemberConsultationOrder
	 * 
	 * @param fdMemberConsultationOrder
	 * @return
	 */
	@RequestMapping("/edit")
	@ResponseBody
	public Json edit(FdMemberConsultationOrder fdMemberConsultationOrder) {
		Json j = new Json();		
		fdMemberConsultationOrderService.edit(fdMemberConsultationOrder);
		j.setSuccess(true);
		j.setMsg("编辑成功！");		
		return j;
	}

	/**
	 * 删除FdMemberConsultationOrder
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public Json delete(Integer id) {
		Json j = new Json();
		fdMemberConsultationOrderService.delete(id);
		j.setMsg("删除成功！");
		j.setSuccess(true);
		return j;
	}

}
