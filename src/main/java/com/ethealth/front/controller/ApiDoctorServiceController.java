package com.ethealth.front.controller;

import com.mobian.absx.F;
import com.mobian.controller.BaseController;
import com.mobian.listener.Application;
import com.mobian.pageModel.*;
import com.mobian.service.*;
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
 * 专家服务接口
 * 
 * @author John
 * 
 */
@Controller
@RequestMapping("/api/doctorService")
public class ApiDoctorServiceController extends BaseController {

	@Autowired
	private FdDoctorGroupServiceI fdDoctorGroupService;

	@Autowired
	private FdHospitalServiceI fdHospitalService;

	@Autowired
	private FdHospitalDeptServiceI fdHospitalDeptService;

	@Autowired
	private FdMemberDoctorServiceI fdMemberDoctorService;

	@Autowired
	private FdDoctorTimeServiceI fdDoctorTimeService;

	@Autowired
	private FdMemberAppointmentCommentServiceI fdMemberAppointmentCommentService;

	@Autowired
	private FdMemberConsultationFriendServiceI fdMemberConsultationFriendService;

	@Autowired
	private FdDoctorOpinionServiceI fdDoctorOpinionService;

	/**
	 * 获取专家服务数据接口
	 */
	@RequestMapping("/dataList")
	@ResponseBody
	public Json dataList() {
		Json j = new Json();
		try{
			Map<String, Object> obj = new HashMap<String, Object>();

			// 科室列表
			PageHelper ph = new PageHelper();
			ph.setHiddenTotal(true);
			List<FdHospitalDept> hospitalDepts = fdHospitalDeptService.dataGrid(new FdHospitalDept(), ph).getRows();
			obj.put("hospitalDepts", hospitalDepts);

			// 专家团队
			ph = new PageHelper();
			ph.setSort("isBest desc, t.seq");
			ph.setOrder("asc");
			ph.setHiddenTotal(true);
			FdDoctorGroup group = new FdDoctorGroup();
			List<FdDoctorGroup> doctorGroups = fdDoctorGroupService.dataGridComplex(group, ph).getRows();
			obj.put("doctorGroups", doctorGroups);

			// 优秀团队
			List<FdDoctorGroup> bestDoctorGroups = new ArrayList<FdDoctorGroup>();
			if(CollectionUtils.isNotEmpty(doctorGroups)) {
				for(FdDoctorGroup g : doctorGroups) {
					if(g.getIsBest() != null && g.getIsBest()) {
						bestDoctorGroups.add(g);
					}
				}
			}
			obj.put("bestDoctorGroups", bestDoctorGroups);

			// 著名专家
			ph = new PageHelper();
			ph.setSort("isBest desc, t.seq");
			ph.setOrder("asc");
			ph.setHiddenTotal(true);
			ph.setPage(1);
			ph.setRows(5);
			FdMemberDoctor doctor = new FdMemberDoctor();
			doctor.setIsBest(true);
			List<FdMemberDoctor> bestDoctors = fdMemberDoctorService.dataGridComplex(doctor, ph).getRows();
			obj.put("bestDoctors", bestDoctors);

			j.setObj(obj);
			j.setSuccess(true);
			j.setMsg("获取专家服务数据成功！");
		}catch(Exception e){
			j.setMsg(Application.getString(EX_0001));
			logger.error("获取专家服务数据接口异常", e);
		}

		return j;
	}

	/**
	 * 获取专家列表接口
	 */
	@RequestMapping("/doctorDataGrid")
	@ResponseBody
	public Json doctorDataGrid(FdMemberDoctor doctor, PageHelper ph) {
		Json j = new Json();
		try{
			ph.setSort("isBest desc, t.seq");
			ph.setOrder("asc");

			j.setObj(fdMemberDoctorService.dataGridMoreComplex(doctor, ph));
			j.setSuccess(true);
			j.setMsg("获取专家列表成功！");
		}catch(Exception e){
			j.setMsg(Application.getString(EX_0001));
			logger.error("获取专家列表接口异常", e);
		}

		return j;
	}

	/**
	 * 获取专家详情接口
	 */
	@RequestMapping("/getDoctorDetail")
	@ResponseBody
	public Json getDoctorDetail(Integer id) {
		Json j = new Json();
		try{
			Map<String, Object> obj = new HashMap<String, Object>();
			FdMemberDoctor doctor = fdMemberDoctorService.getDetail(id);
			doctor.setDoctorTimes(fdDoctorTimeService.getGroupTimesByDoctorId(id));
			obj.put("doctor", doctor);

			// 获取评价
			FdMemberAppointmentComment comment = new FdMemberAppointmentComment();
			comment.setDoctorId(id);
			PageHelper ph = new PageHelper();
			ph.setPage(1);
			ph.setRows(2);
			ph.setSort("createTime");
			ph.setOrder("desc");
			ph.setHiddenTotal(true);
			obj.put("comments", fdMemberAppointmentCommentService.dataGridComplex(comment, ph));

			// 在线咨询
			FdMemberConsultationFriend friend = new FdMemberConsultationFriend();
			ph = new PageHelper();
			ph.setPage(1);
			ph.setRows(2);
			ph.setSort("lastTime");
			ph.setOrder("desc");
			friend.setIsAdmin(2);
			friend.setDoctorId(id);
			obj.put("consultations", fdMemberConsultationFriendService.dataGridComplex(friend, ph));

			// 专家笔谈
			FdDoctorOpinion doctorOpinion = new FdDoctorOpinion();
			ph = new PageHelper();
			ph.setPage(1);
			ph.setRows(2);
			ph.setHiddenTotal(true);
			doctorOpinion.setUserId(id);
			obj.put("doctorOpinions", fdDoctorOpinionService.dataGridComplex(doctorOpinion, ph));

			j.setObj(obj);
			j.setSuccess(true);
			j.setMsg("获取专家详情成功！");
		}catch(Exception e){
			j.setMsg(Application.getString(EX_0001));
			logger.error("获取专家详情接口异常", e);
		}

		return j;
	}

	/**
	 * 获取更多患者评价接口
	 */
	@RequestMapping("/comments")
	@ResponseBody
	public Json comments(FdMemberAppointmentComment comment, PageHelper ph) {
		Json j = new Json();
		try{
			if(ph.getRows() == 0 || ph.getRows() > 50) {
				ph.setRows(10);
			}
			if(F.empty(ph.getSort())) {
				ph.setSort("createTime");
			}
			if(F.empty(ph.getOrder())) {
				ph.setOrder("desc");
			}

			j.setObj(fdMemberAppointmentCommentService.dataGridComplex(comment, ph));
			j.setSuccess(true);
			j.setMsg("获取专家列表成功！");
		}catch(Exception e){
			j.setMsg(Application.getString(EX_0001));
			logger.error("获取专家列表接口异常", e);
		}

		return j;
	}

	/**
	 * 获取更多咨询接口
	 */
	@RequestMapping("/consultations")
	@ResponseBody
	public Json consultations(FdMemberConsultationFriend friend, PageHelper ph) {
		Json j = new Json();
		try{
			if(ph.getRows() == 0 || ph.getRows() > 50) {
				ph.setRows(10);
			}
			if(F.empty(ph.getSort())) {
				ph.setSort("lastTime");
			}
			if(F.empty(ph.getOrder())) {
				ph.setOrder("desc");
			}

			friend.setIsAdmin(2);
			j.setObj(fdMemberConsultationFriendService.dataGridComplex(friend, ph));
			j.setSuccess(true);
			j.setMsg("获取成功！");
		}catch(Exception e){
			j.setMsg(Application.getString(EX_0001));
			logger.error("获取更多咨询接口异常", e);
		}

		return j;
	}

	/**
	 * 获取医院列表接口
	 */
	@RequestMapping("/hospitalList")
	@ResponseBody
	public Json hospitalList(FdHospital hospital, PageHelper ph) {
		Json j = new Json();
		try{
			ph.setHiddenTotal(true);
			List<FdHospital> hospitals = fdHospitalService.dataGrid(hospital, ph).getRows();
			j.setObj(hospitals);
			j.setSuccess(true);
			j.setMsg("获取医院列表成功！");
		}catch(Exception e){
			j.setMsg(Application.getString(EX_0001));
			logger.error("获取医院列表接口异常", e);
		}

		return j;
	}

	/**
	 * 获取科室列表接口
	 */
	@RequestMapping("/departmentList")
	@ResponseBody
	public Json departmentList(FdHospitalDept hospitalDept, PageHelper ph) {
		Json j = new Json();
		try{
			ph.setHiddenTotal(true);
			List<FdHospitalDept> hospitals = fdHospitalDeptService.dataGrid(hospitalDept, ph).getRows();
			j.setObj(hospitals);
			j.setSuccess(true);
			j.setMsg("获取医院列表成功！");
		}catch(Exception e){
			j.setMsg(Application.getString(EX_0001));
			logger.error("获取医院列表接口异常", e);
		}

		return j;
	}

}
