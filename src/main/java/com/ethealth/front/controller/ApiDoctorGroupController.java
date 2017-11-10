package com.ethealth.front.controller;

import com.mobian.absx.F;
import com.mobian.controller.BaseController;
import com.mobian.listener.Application;
import com.mobian.pageModel.FdDoctorGroup;
import com.mobian.pageModel.FdPicture;
import com.mobian.pageModel.Json;
import com.mobian.pageModel.PageHelper;
import com.mobian.service.FdDoctorGroupServiceI;
import com.mobian.service.FdMemberServiceI;
import com.mobian.service.FdPictureServiceI;
import com.mobian.util.PathUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 专家团队接口
 * 
 * @author John
 * 
 */
@Controller
@RequestMapping("/api/doctorGroup")
public class ApiDoctorGroupController extends BaseController {

	@Autowired
	private FdDoctorGroupServiceI fdDoctorGroupService;

	@Autowired
	private FdPictureServiceI fdPictureService;

	@Autowired
	private FdMemberServiceI fdMemberService;

	/**
	 * 获取专家团队列表接口
	 */
	@RequestMapping("/dataGrid")
	@ResponseBody
	public Json dataGrid(FdDoctorGroup group, PageHelper ph) {
		Json j = new Json();
		try{
			j.setObj(fdDoctorGroupService.dataGridComplex(group, ph));
			j.setSuccess(true);
			j.setMsg("获取专家团队列表成功！");
		}catch(Exception e){
			j.setMsg(Application.getString(EX_0001));
			logger.error("获取专家团队列表接口异常", e);
		}

		return j;
	}

	/**
	 * 获取专家团队详情
	 */
	@RequestMapping("/detail")
	@ResponseBody
	public Json detail(Integer id) {
		Json j = new Json();
		try{

			FdDoctorGroup group = fdDoctorGroupService.get(id);
			if(!F.empty(group.getPics())) {
				FdPicture pic = fdPictureService.get(Integer.valueOf(group.getPics()));
				if(pic != null) group.setPicUrl(PathUtil.getPicPath(pic.getPath()));
			}
			// 获取团队成员
			group.setMembers(fdMemberService.queryMembersByGroupId(group.getId()));
			j.setSuccess(true);
			j.setMsg("获取专家团队详情成功！");
			j.setObj(group);
		}catch(Exception e){
			j.setMsg(Application.getString(EX_0001));
			logger.error("获取专家团队详情接口异常", e);
		}

		return j;
	}

}
