package com.mobian.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mobian.concurrent.CacheKey;
import com.mobian.concurrent.CompletionService;
import com.mobian.concurrent.Task;
import com.mobian.pageModel.*;
import com.mobian.service.FdMemberConsultationExpireServiceI;
import com.mobian.service.FdMemberConsultationOrderServiceI;

import com.mobian.service.FdMemberServiceI;
import com.mobian.service.impl.CompletionFactory;
import org.apache.commons.collections.CollectionUtils;
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

	@Autowired
	private FdMemberServiceI fdMemberService;

	@Autowired
	private FdMemberConsultationExpireServiceI fdMemberConsultationExpireService;


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
	 * @param
	 * @return
	 */
	@RequestMapping("/dataGrid")
	@ResponseBody
	public DataGrid dataGrid(FdMemberConsultationOrder fdMemberConsultationOrder, PageHelper ph) {
		DataGrid dg = fdMemberConsultationOrderService.dataGrid(fdMemberConsultationOrder, ph);
		List<FdMemberConsultationOrder> list = dg.getRows();
		if(CollectionUtils.isNotEmpty(list)) {
			CompletionService completionService = CompletionFactory.initCompletion();
			for(FdMemberConsultationOrder order : list) {
				completionService.submit(new Task<FdMemberConsultationOrder, FdMember>(new CacheKey("fdMember", order.getUserId() + ""), order) {
					@Override
					public FdMember call() throws Exception {
						return fdMemberService.getSimple(getD().getUserId());
					}

					protected void set(FdMemberConsultationOrder d, FdMember v) {
						if(v != null) {
							d.setUserName(v.getCustomer().getRealName());
							d.setUserMobile(v.getMobile());
						}
					}
				});
				completionService.submit(new Task<FdMemberConsultationOrder, FdMember>(new CacheKey("fdMember", order.getDoctorId() + ""), order) {
					@Override
					public FdMember call() throws Exception {
						return fdMemberService.getSimple(getD().getDoctorId());
					}

					protected void set(FdMemberConsultationOrder d, FdMember v) {
						if(v != null) {
							d.setDoctorName(v.getCustomer().getRealName());
							d.setDoctorMobile(v.getMobile());
						}
					}
				});

				completionService.submit(new Task<FdMemberConsultationOrder, FdMemberConsultationExpire>(order) {
					@Override
					public FdMemberConsultationExpire call() throws Exception {
						FdMemberConsultationExpire expire = fdMemberConsultationExpireService.getByUserIdAndDoctorId(getD().getUserId(), getD().getDoctorId());
						return expire;
					}

					protected void set(FdMemberConsultationOrder d, FdMemberConsultationExpire v) {
						if(v != null) {
							d.setExpireDate(v.getExpireDate());
						}
					}
				});
			}
			completionService.sync();
		}
		return dg;
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
