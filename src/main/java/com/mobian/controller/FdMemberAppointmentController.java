package com.mobian.controller;

import com.alibaba.fastjson.JSON;
import com.mobian.concurrent.CacheKey;
import com.mobian.concurrent.CompletionService;
import com.mobian.concurrent.Task;
import com.mobian.pageModel.*;
import com.mobian.service.FdMemberAppointmentServiceI;
import com.mobian.service.FdMemberServiceI;
import com.mobian.service.impl.CompletionFactory;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 * FdMemberAppointment管理控制器
 * 
 * @author John
 * 
 */
@Controller
@RequestMapping("/fdMemberAppointmentController")
public class FdMemberAppointmentController extends BaseController {

	@Autowired
	private FdMemberAppointmentServiceI fdMemberAppointmentService;

	@Autowired
	private FdMemberServiceI fdMemberService;


	/**
	 * 跳转到FdMemberAppointment管理页面
	 * 
	 * @return
	 */
	@RequestMapping("/manager")
	public String manager(HttpServletRequest request) {
		return "/fdmemberappointment/fdMemberAppointment";
	}

	/**
	 * 获取FdMemberAppointment数据表格
	 * 
	 * @param
	 * @return
	 */
	@RequestMapping("/dataGrid")
	@ResponseBody
	public DataGrid dataGrid(FdMemberAppointment fdMemberAppointment, PageHelper ph) {
		fdMemberAppointment.setIsShowWx(true);
		if("-1".equals(fdMemberAppointment.getAppointStatus())) { // 取消
			fdMemberAppointment.setStatus("0");
			fdMemberAppointment.setAppointStatus("3");
		} else if("3".equals(fdMemberAppointment.getAppointStatus())) { // 拒绝
			fdMemberAppointment.setStatus("1");
		}
		if(fdMemberAppointment.getCreateTimeStartDate() != null) fdMemberAppointment.setCreateTimeStart(fdMemberAppointment.getCreateTimeStartDate().getTime());
		if(fdMemberAppointment.getCreateTimeEndDate() != null) fdMemberAppointment.setCreateTimeEnd(fdMemberAppointment.getCreateTimeEndDate().getTime());
		DataGrid dg = fdMemberAppointmentService.dataGrid(fdMemberAppointment, ph);
		List<FdMemberAppointment> list = dg.getRows();
		if(CollectionUtils.isNotEmpty(list)) {
			CompletionService completionService = CompletionFactory.initCompletion();
			for(FdMemberAppointment order : list) {
				if(order.getAmount() == null) order.setAmount(0L);
				completionService.submit(new Task<FdMemberAppointment, FdMember>(new CacheKey("fdMember", order.getUserId() + ""), order) {
					@Override
					public FdMember call() throws Exception {
						return fdMemberService.getSimple(getD().getUserId());
					}

					protected void set(FdMemberAppointment d, FdMember v) {
						if(v != null) {
							d.setUserName(v.getCustomer().getRealName());
							d.setUserMobile(v.getMobile());
						}
					}
				});
				completionService.submit(new Task<FdMemberAppointment, FdMember>(new CacheKey("fdMember", order.getDoctorId() + ""), order) {
					@Override
					public FdMember call() throws Exception {
						return fdMemberService.getSimple(getD().getDoctorId());
					}

					protected void set(FdMemberAppointment d, FdMember v) {
						if(v != null) {
							d.setDoctorName(v.getCustomer().getRealName());
							d.setDoctorMobile(v.getMobile());
						}
					}
				});
			}
			completionService.sync();
		}
		return dg;
	}
	/**
	 * 获取FdMemberAppointment数据表格excel
	 * 
	 * @param
	 * @return
	 * @throws NoSuchMethodException 
	 * @throws SecurityException 
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws IllegalArgumentException 
	 * @throws IOException 
	 */
	@RequestMapping("/download")
	public void download(FdMemberAppointment fdMemberAppointment, PageHelper ph,String downloadFields,HttpServletResponse response) throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException, IOException{
		DataGrid dg = dataGrid(fdMemberAppointment,ph);		
		downloadFields = downloadFields.replace("&quot;", "\"");
		downloadFields = downloadFields.substring(1,downloadFields.length()-1);
		List<Colum> colums = JSON.parseArray(downloadFields, Colum.class);
		downloadTable(colums, dg, response);
	}
	/**
	 * 跳转到添加FdMemberAppointment页面
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/addPage")
	public String addPage(HttpServletRequest request) {
		FdMemberAppointment fdMemberAppointment = new FdMemberAppointment();
		return "/fdmemberappointment/fdMemberAppointmentAdd";
	}

	/**
	 * 添加FdMemberAppointment
	 * 
	 * @return
	 */
	@RequestMapping("/add")
	@ResponseBody
	public Json add(FdMemberAppointment fdMemberAppointment) {
		Json j = new Json();		
		fdMemberAppointmentService.add(fdMemberAppointment);
		j.setSuccess(true);
		j.setMsg("添加成功！");		
		return j;
	}

	/**
	 * 跳转到FdMemberAppointment查看页面
	 * 
	 * @return
	 */
	@RequestMapping("/view")
	public String view(HttpServletRequest request, Integer id) {
		FdMemberAppointment fdMemberAppointment = fdMemberAppointmentService.get(id);
		request.setAttribute("fdMemberAppointment", fdMemberAppointment);
		return "/fdmemberappointment/fdMemberAppointmentView";
	}

	/**
	 * 跳转到FdMemberAppointment修改页面
	 * 
	 * @return
	 */
	@RequestMapping("/editPage")
	public String editPage(HttpServletRequest request, Integer id) {
		FdMemberAppointment fdMemberAppointment = fdMemberAppointmentService.get(id);
		request.setAttribute("fdMemberAppointment", fdMemberAppointment);
		return "/fdmemberappointment/fdMemberAppointmentEdit";
	}

	/**
	 * 修改FdMemberAppointment
	 * 
	 * @param fdMemberAppointment
	 * @return
	 */
	@RequestMapping("/edit")
	@ResponseBody
	public Json edit(FdMemberAppointment fdMemberAppointment) {
		Json j = new Json();		
		fdMemberAppointmentService.edit(fdMemberAppointment);
		j.setSuccess(true);
		j.setMsg("编辑成功！");		
		return j;
	}

	/**
	 * 删除FdMemberAppointment
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public Json delete(Integer id) {
		Json j = new Json();
		fdMemberAppointmentService.delete(id);
		j.setMsg("删除成功！");
		j.setSuccess(true);
		return j;
	}

}
