package com.mobian.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mobian.pageModel.Colum;
import com.mobian.pageModel.FdBanner;
import com.mobian.pageModel.DataGrid;
import com.mobian.pageModel.Json;
import com.mobian.pageModel.PageHelper;
import com.mobian.service.FdBannerServiceI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

/**
 * FdBanner管理控制器
 * 
 * @author John
 * 
 */
@Controller
@RequestMapping("/fdBannerController")
public class FdBannerController extends BaseController {

	@Autowired
	private FdBannerServiceI fdBannerService;


	/**
	 * 跳转到FdBanner管理页面
	 * 
	 * @return
	 */
	@RequestMapping("/manager")
	public String manager(HttpServletRequest request) {
		return "/fdbanner/fdBanner";
	}

	/**
	 * 获取FdBanner数据表格
	 * 
	 * @param user
	 * @return
	 */
	@RequestMapping("/dataGrid")
	@ResponseBody
	public DataGrid dataGrid(FdBanner fdBanner, PageHelper ph) {
		return fdBannerService.dataGrid(fdBanner, ph);
	}
	/**
	 * 获取FdBanner数据表格excel
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
	public void download(FdBanner fdBanner, PageHelper ph,String downloadFields,HttpServletResponse response) throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException, IOException{
		DataGrid dg = dataGrid(fdBanner,ph);		
		downloadFields = downloadFields.replace("&quot;", "\"");
		downloadFields = downloadFields.substring(1,downloadFields.length()-1);
		List<Colum> colums = JSON.parseArray(downloadFields, Colum.class);
		downloadTable(colums, dg, response);
	}
	/**
	 * 跳转到添加FdBanner页面
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/addPage")
	public String addPage(HttpServletRequest request) {
		FdBanner fdBanner = new FdBanner();
		return "/fdbanner/fdBannerAdd";
	}

	/**
	 * 添加FdBanner
	 * 
	 * @return
	 */
	@RequestMapping("/add")
	@ResponseBody
	public Json add(FdBanner fdBanner) {
		Json j = new Json();		
		fdBannerService.add(fdBanner);
		j.setSuccess(true);
		j.setMsg("添加成功！");		
		return j;
	}

	/**
	 * 跳转到FdBanner查看页面
	 * 
	 * @return
	 */
	@RequestMapping("/view")
	public String view(HttpServletRequest request, Integer id) {
		FdBanner fdBanner = fdBannerService.get(id);
		request.setAttribute("fdBanner", fdBanner);
		return "/fdbanner/fdBannerView";
	}

	/**
	 * 跳转到FdBanner修改页面
	 * 
	 * @return
	 */
	@RequestMapping("/editPage")
	public String editPage(HttpServletRequest request, Integer id) {
		FdBanner fdBanner = fdBannerService.get(id);
		request.setAttribute("fdBanner", fdBanner);
		return "/fdbanner/fdBannerEdit";
	}

	/**
	 * 修改FdBanner
	 * 
	 * @param fdBanner
	 * @return
	 */
	@RequestMapping("/edit")
	@ResponseBody
	public Json edit(FdBanner fdBanner) {
		Json j = new Json();		
		fdBannerService.edit(fdBanner);
		j.setSuccess(true);
		j.setMsg("编辑成功！");		
		return j;
	}

	/**
	 * 删除FdBanner
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public Json delete(Integer id) {
		Json j = new Json();
		fdBannerService.delete(id);
		j.setMsg("删除成功！");
		j.setSuccess(true);
		return j;
	}

}
