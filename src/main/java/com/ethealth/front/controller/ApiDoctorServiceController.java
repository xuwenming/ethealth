package com.ethealth.front.controller;

import com.mobian.controller.BaseController;
import com.mobian.listener.Application;
import com.mobian.pageModel.*;
import com.mobian.service.FdDoctorGroupServiceI;
import com.mobian.service.FdHospitalDeptServiceI;
import com.mobian.service.FdMemberDoctorServiceI;
import com.mobian.service.FdMemberServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
	private FdMemberServiceI fdMemberService;

	@Autowired
	private FdHospitalDeptServiceI fdHospitalDeptService;

	@Autowired
	private FdMemberDoctorServiceI fdMemberDoctorService;

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

			// 专家团队 // TODO 优秀团队
			ph = new PageHelper();
			ph.setHiddenTotal(true);
			List<FdDoctorGroup> doctorGroups = fdDoctorGroupService.dataGridComplex(new FdDoctorGroup(), ph).getRows();
			obj.put("bestDoctorGroups", doctorGroups);
			obj.put("doctorGroups", doctorGroups);

			// 著名专家
			ph = new PageHelper();
			ph.setSort("sort");
			ph.setOrder("desc");
			ph.setHiddenTotal(true);
			ph.setPage(1);
			ph.setRows(5);
			List<FdMemberDoctor> bestDoctors = fdMemberDoctorService.dataGridComplex(new FdMemberDoctor(), ph).getRows();
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
			ph = new PageHelper();
			if(doctor.getIsBest() != null && doctor.getIsBest()) { // 著名专家
				ph.setSort("sort");
				ph.setOrder("desc");
			} else {

			}

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
			FdMemberDoctor doctor = fdMemberDoctorService.getDetail(id);
			j.setObj(doctor);
			j.setSuccess(true);
			j.setMsg("获取专家详情成功！");
		}catch(Exception e){
			j.setMsg(Application.getString(EX_0001));
			logger.error("获取专家详情接口异常", e);
		}

		return j;
	}

}
