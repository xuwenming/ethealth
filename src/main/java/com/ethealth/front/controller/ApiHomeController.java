package com.ethealth.front.controller;

import com.mobian.absx.F;
import com.mobian.concurrent.CacheKey;
import com.mobian.concurrent.CompletionService;
import com.mobian.concurrent.Task;
import com.mobian.controller.BaseController;
import com.mobian.listener.Application;
import com.mobian.pageModel.*;
import com.mobian.service.FdBannerServiceI;
import com.mobian.service.FdDoctorGroupServiceI;
import com.mobian.service.FdPictureServiceI;
import com.mobian.service.impl.CompletionFactory;
import com.mobian.util.PathUtil;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 首页接口
 * 
 * @author John
 * 
 */
@Controller
@RequestMapping("/api/home")
public class ApiHomeController extends BaseController {

	@Autowired
	private FdBannerServiceI fdBannerService;

	@Autowired
	private FdDoctorGroupServiceI fdDoctorGroupService;

	/**
	 * 获取首页数据接口
	 */
	@RequestMapping("/home")
	@ResponseBody
	public Json home() {
		Json j = new Json();
		try{
			Map<String, Object> obj = new HashMap<String, Object>();

			// 轮播图
			FdBanner banner = new FdBanner();
			PageHelper ph = new PageHelper();
			banner.setSource("1");
			ph.setHiddenTotal(true);
			obj.put("banners", fdBannerService.dataGridComplex(banner, ph).getRows());

			// 专家团队
			ph = new PageHelper();
			ph.setHiddenTotal(true);
			obj.put("doctorGroups", fdDoctorGroupService.dataGridComplex(new FdDoctorGroup(), ph).getRows());

			// TODO 就诊动态
			obj.put("consultationDynamic", new ArrayList<Object>());


			j.setSuccess(true);
			j.setMsg("获取首页数据成功！");
			j.setObj(obj);
		}catch(Exception e){
			j.setMsg(Application.getString(EX_0001));
			logger.error("获取首页数据接口异常", e);
		}

		return j;
	}

	/**
	 * 获取banner列表接口
	 */
	@RequestMapping("/list")
	@ResponseBody
	public Json list(FdBanner banner, PageHelper ph) {
		Json j = new Json();
		try{
			ph.setHiddenTotal(true);
			j.setObj(fdBannerService.dataGridComplex(banner, ph).getRows());

			j.setSuccess(true);
			j.setMsg("获取banner列表成功！");

		}catch(Exception e){
			j.setMsg(Application.getString(EX_0001));
			logger.error("获取banner列表接口异常", e);
		}

		return j;
	}

}
