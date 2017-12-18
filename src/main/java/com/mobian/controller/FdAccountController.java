package com.mobian.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mobian.pageModel.Colum;
import com.mobian.pageModel.FdAccount;
import com.mobian.pageModel.DataGrid;
import com.mobian.pageModel.Json;
import com.mobian.pageModel.PageHelper;
import com.mobian.service.FdAccountServiceI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

/**
 * FdAccount管理控制器
 * 
 * @author John
 * 
 */
@Controller
@RequestMapping("/fdAccountController")
public class FdAccountController extends BaseController {

	@Autowired
	private FdAccountServiceI fdAccountService;


	/**
	 * 跳转到FdAccount管理页面
	 * 
	 * @return
	 */
	@RequestMapping("/manager")
	public String manager(HttpServletRequest request) {
		return "/fdaccount/fdAccount";
	}

	/**
	 * 获取FdAccount数据表格
	 * 
	 * @param user
	 * @return
	 */
	@RequestMapping("/dataGrid")
	@ResponseBody
	public DataGrid dataGrid(FdAccount fdAccount, PageHelper ph) {
		return fdAccountService.dataGrid(fdAccount, ph);
	}
	/**
	 * 获取FdAccount数据表格excel
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
	public void download(FdAccount fdAccount, PageHelper ph,String downloadFields,HttpServletResponse response) throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException, IOException{
		DataGrid dg = dataGrid(fdAccount,ph);		
		downloadFields = downloadFields.replace("&quot;", "\"");
		downloadFields = downloadFields.substring(1,downloadFields.length()-1);
		List<Colum> colums = JSON.parseArray(downloadFields, Colum.class);
		downloadTable(colums, dg, response);
	}
	/**
	 * 跳转到添加FdAccount页面
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/addPage")
	public String addPage(HttpServletRequest request) {
		FdAccount fdAccount = new FdAccount();
		return "/fdaccount/fdAccountAdd";
	}

	/**
	 * 添加FdAccount
	 * 
	 * @return
	 */
	@RequestMapping("/add")
	@ResponseBody
	public Json add(FdAccount fdAccount) {
		Json j = new Json();		
		fdAccountService.add(fdAccount);
		j.setSuccess(true);
		j.setMsg("添加成功！");		
		return j;
	}

	/**
	 * 跳转到FdAccount查看页面
	 * 
	 * @return
	 */
	@RequestMapping("/view")
	public String view(HttpServletRequest request, Long userId) {
		FdAccount fdAccount = fdAccountService.get(userId);
		request.setAttribute("fdAccount", fdAccount);
		return "/fdaccount/fdAccountView";
	}

	/**
	 * 跳转到FdAccount修改页面
	 * 
	 * @return
	 */
	@RequestMapping("/editPage")
	public String editPage(HttpServletRequest request, Long id) {
		FdAccount fdAccount = fdAccountService.get(id);
		request.setAttribute("fdAccount", fdAccount);
		return "/fdaccount/fdAccountEdit";
	}

	/**
	 * 修改FdAccount
	 * 
	 * @param fdAccount
	 * @return
	 */
	@RequestMapping("/edit")
	@ResponseBody
	public Json edit(FdAccount fdAccount) {
		Json j = new Json();		
		fdAccountService.edit(fdAccount);
		j.setSuccess(true);
		j.setMsg("编辑成功！");		
		return j;
	}

	/**
	 * 删除FdAccount
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public Json delete(Integer id) {
		Json j = new Json();
		fdAccountService.delete(id);
		j.setMsg("删除成功！");
		j.setSuccess(true);
		return j;
	}

}
