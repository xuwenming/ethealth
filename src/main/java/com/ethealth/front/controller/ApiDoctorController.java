package com.ethealth.front.controller;

import com.mobian.controller.BaseController;
import com.mobian.exception.ServiceException;
import com.mobian.listener.Application;
import com.mobian.pageModel.BaseData;
import com.mobian.pageModel.FdMemberDoctor;
import com.mobian.pageModel.Json;
import com.mobian.pageModel.SessionInfo;
import com.mobian.service.BasedataServiceI;
import com.mobian.service.FdMemberDoctorServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
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
@RequestMapping("/api/doctor")
public class ApiDoctorController extends BaseController {

	@Autowired
	private FdMemberDoctorServiceI fdMemberDoctorService;

	/**
	 * 获取业务设置信息
	 */
	@RequestMapping("/getConfig")
	@ResponseBody
	public Json getConfig(HttpServletRequest request) {
		Json j = new Json();
		try{
			SessionInfo s = getSessionInfo(request);
			Map<String, Object> obj = new HashMap<String, Object>();
			FdMemberDoctor doctor = fdMemberDoctorService.get(Integer.valueOf(s.getId()));
			obj.put("acceptAppointment", doctor.getAcceptAppointment());
			obj.put("acceptConsultation", doctor.getAcceptConsultation());
			obj.put("appointmentFee", Application.getString("UC001", "99"));
			obj.put("consultationFee", Application.getString("UC002", "99"));

			j.setObj(obj);
			j.setSuccess(true);
			j.setMsg("获取成功！");

		} catch (ServiceException e) {
			j.setObj(e.getMessage());
			logger.error("获取业务设置信息接口异常", e);
		} catch(Exception e){
			j.setMsg(Application.getString(EX_0001));
			logger.error("获取业务设置信息接口异常", e);
		}

		return j;
	}

	/**
	 * 更新业务设置
	 */
	@RequestMapping("/updateConfig")
	@ResponseBody
	public Json updateConfig(FdMemberDoctor doctor, HttpServletRequest request) {
		Json j = new Json();
		try{
			SessionInfo s = getSessionInfo(request);
			doctor.setId(Integer.valueOf(s.getId()));
			fdMemberDoctorService.edit(doctor);

			j.setSuccess(true);
			j.setMsg("更新成功！");

		} catch (ServiceException e) {
			j.setObj(e.getMessage());
			logger.error("更新业务设置接口异常", e);
		} catch(Exception e){
			j.setMsg(Application.getString(EX_0001));
			logger.error("更新业务设置接口异常", e);
		}

		return j;
	}
}
