package com.mobian.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mobian.pageModel.Colum;
import com.mobian.pageModel.FdPaymentBase;
import com.mobian.pageModel.DataGrid;
import com.mobian.pageModel.Json;
import com.mobian.pageModel.PageHelper;
import com.mobian.service.FdPaymentBaseServiceI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

/**
 * FdPaymentBase管理控制器
 * 
 * @author John
 * 
 */
@Controller
@RequestMapping("/fdPaymentBaseController")
public class FdPaymentBaseController extends BaseController {

	@Autowired
	private FdPaymentBaseServiceI fdPaymentBaseService;


	/**
	 * 跳转到FdPaymentBase管理页面
	 * 
	 * @return
	 */
	@RequestMapping("/manager")
	public String manager(HttpServletRequest request) {
		return "/fdpaymentbase/fdPaymentBase";
	}

	/**
	 * 获取FdPaymentBase数据表格
	 * 
	 * @param user
	 * @return
	 */
	@RequestMapping("/dataGrid")
	@ResponseBody
	public DataGrid dataGrid(FdPaymentBase fdPaymentBase, PageHelper ph) {
		return fdPaymentBaseService.dataGrid(fdPaymentBase, ph);
	}
	/**
	 * 获取FdPaymentBase数据表格excel
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
	public void download(FdPaymentBase fdPaymentBase, PageHelper ph,String downloadFields,HttpServletResponse response) throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException, IOException{
		DataGrid dg = dataGrid(fdPaymentBase,ph);		
		downloadFields = downloadFields.replace("&quot;", "\"");
		downloadFields = downloadFields.substring(1,downloadFields.length()-1);
		List<Colum> colums = JSON.parseArray(downloadFields, Colum.class);
		downloadTable(colums, dg, response);
	}
	/**
	 * 跳转到添加FdPaymentBase页面
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/addPage")
	public String addPage(HttpServletRequest request) {
		FdPaymentBase fdPaymentBase = new FdPaymentBase();
		return "/fdpaymentbase/fdPaymentBaseAdd";
	}

	/**
	 * 添加FdPaymentBase
	 * 
	 * @return
	 */
	@RequestMapping("/add")
	@ResponseBody
	public Json add(FdPaymentBase fdPaymentBase) {
		Json j = new Json();		
		fdPaymentBaseService.add(fdPaymentBase);
		j.setSuccess(true);
		j.setMsg("添加成功！");		
		return j;
	}

	/**
	 * 跳转到FdPaymentBase查看页面
	 * 
	 * @return
	 */
	@RequestMapping("/view")
	public String view(HttpServletRequest request, Integer id) {
		FdPaymentBase fdPaymentBase = fdPaymentBaseService.get(id);
		request.setAttribute("fdPaymentBase", fdPaymentBase);
		return "/fdpaymentbase/fdPaymentBaseView";
	}

	/**
	 * 跳转到FdPaymentBase修改页面
	 * 
	 * @return
	 */
	@RequestMapping("/editPage")
	public String editPage(HttpServletRequest request, Integer id) {
		FdPaymentBase fdPaymentBase = fdPaymentBaseService.get(id);
		request.setAttribute("fdPaymentBase", fdPaymentBase);
		return "/fdpaymentbase/fdPaymentBaseEdit";
	}

	/**
	 * 修改FdPaymentBase
	 * 
	 * @param fdPaymentBase
	 * @return
	 */
	@RequestMapping("/edit")
	@ResponseBody
	public Json edit(FdPaymentBase fdPaymentBase) {
		Json j = new Json();		
		fdPaymentBaseService.edit(fdPaymentBase);
		j.setSuccess(true);
		j.setMsg("编辑成功！");		
		return j;
	}

	/**
	 * 删除FdPaymentBase
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public Json delete(Integer id) {
		Json j = new Json();
		fdPaymentBaseService.delete(id);
		j.setMsg("删除成功！");
		j.setSuccess(true);
		return j;
	}

}
