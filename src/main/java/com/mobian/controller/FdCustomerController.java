package com.mobian.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mobian.pageModel.Colum;
import com.mobian.pageModel.FdCustomer;
import com.mobian.pageModel.DataGrid;
import com.mobian.pageModel.Json;
import com.mobian.pageModel.PageHelper;
import com.mobian.service.FdCustomerServiceI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

/**
 * FdCustomer管理控制器
 * 
 * @author John
 * 
 */
@Controller
@RequestMapping("/fdCustomerController")
public class FdCustomerController extends BaseController {

	@Autowired
	private FdCustomerServiceI fdCustomerService;


	/**
	 * 跳转到FdCustomer管理页面
	 * 
	 * @return
	 */
	@RequestMapping("/manager")
	public String manager(HttpServletRequest request) {
		return "/fdcustomer/fdCustomer";
	}

	/**
	 * 获取FdCustomer数据表格
	 * 
	 * @param user
	 * @return
	 */
	@RequestMapping("/dataGrid")
	@ResponseBody
	public DataGrid dataGrid(FdCustomer fdCustomer, PageHelper ph) {
		return fdCustomerService.dataGrid(fdCustomer, ph);
	}
	/**
	 * 获取FdCustomer数据表格excel
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
	public void download(FdCustomer fdCustomer, PageHelper ph,String downloadFields,HttpServletResponse response) throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException, IOException{
		DataGrid dg = dataGrid(fdCustomer,ph);		
		downloadFields = downloadFields.replace("&quot;", "\"");
		downloadFields = downloadFields.substring(1,downloadFields.length()-1);
		List<Colum> colums = JSON.parseArray(downloadFields, Colum.class);
		downloadTable(colums, dg, response);
	}
	/**
	 * 跳转到添加FdCustomer页面
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/addPage")
	public String addPage(HttpServletRequest request) {
		FdCustomer fdCustomer = new FdCustomer();
		return "/fdcustomer/fdCustomerAdd";
	}

	/**
	 * 添加FdCustomer
	 * 
	 * @return
	 */
	@RequestMapping("/add")
	@ResponseBody
	public Json add(FdCustomer fdCustomer) {
		Json j = new Json();		
		fdCustomerService.add(fdCustomer);
		j.setSuccess(true);
		j.setMsg("添加成功！");		
		return j;
	}

	/**
	 * 跳转到FdCustomer查看页面
	 * 
	 * @return
	 */
	@RequestMapping("/view")
	public String view(HttpServletRequest request, Long id) {
		FdCustomer fdCustomer = fdCustomerService.get(id);
		request.setAttribute("fdCustomer", fdCustomer);
		return "/fdcustomer/fdCustomerView";
	}

	/**
	 * 跳转到FdCustomer修改页面
	 * 
	 * @return
	 */
	@RequestMapping("/editPage")
	public String editPage(HttpServletRequest request, Long id) {
		FdCustomer fdCustomer = fdCustomerService.get(id);
		request.setAttribute("fdCustomer", fdCustomer);
		return "/fdcustomer/fdCustomerEdit";
	}

	/**
	 * 修改FdCustomer
	 * 
	 * @param fdCustomer
	 * @return
	 */
	@RequestMapping("/edit")
	@ResponseBody
	public Json edit(FdCustomer fdCustomer) {
		Json j = new Json();		
		fdCustomerService.edit(fdCustomer);
		j.setSuccess(true);
		j.setMsg("编辑成功！");		
		return j;
	}

	/**
	 * 删除FdCustomer
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public Json delete(Integer id) {
		Json j = new Json();
		fdCustomerService.delete(id);
		j.setMsg("删除成功！");
		j.setSuccess(true);
		return j;
	}

}
