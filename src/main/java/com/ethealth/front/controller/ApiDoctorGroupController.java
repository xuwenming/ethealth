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
import com.mobian.service.FdMemberServiceI;
import com.mobian.service.FdPictureServiceI;
import com.mobian.service.impl.CompletionFactory;
import com.mobian.util.PathUtil;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

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
			if(F.empty(ph.getSort())) {
				ph.setSort("createTime");
			}
			if(F.empty(ph.getOrder())) {
				ph.setOrder("desc");
			}
			DataGrid dg = fdDoctorGroupService.dataGrid(group, ph);
			List<FdDoctorGroup> doctorGroups = dg.getRows();
			if(CollectionUtils.isNotEmpty(doctorGroups)) {
				CompletionService completionService = CompletionFactory.initCompletion();
				for (FdDoctorGroup o : doctorGroups) {
					if(!F.empty(o.getPics()))
						completionService.submit(new Task<FdDoctorGroup, String>(new CacheKey("fdPic", o.getPics() + ""), o) {
							@Override
							public String call() throws Exception {
								FdPicture pic = fdPictureService.get(Integer.valueOf(getD().getPics()));
								if(pic != null) return PathUtil.getPicPath(pic.getPath());
								return null;
							}

							protected void set(FdDoctorGroup d, String v) {
								if(!F.empty(v)) {
									d.setPicUrl(v);
								}
							}
						});
				}
				completionService.sync();
			}
			j.setSuccess(true);
			j.setMsg("获取专家团队列表成功！");
			j.setObj(dg);
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
