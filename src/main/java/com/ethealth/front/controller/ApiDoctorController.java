package com.ethealth.front.controller;

import com.mobian.absx.F;
import com.mobian.controller.BaseController;
import com.mobian.exception.ServiceException;
import com.mobian.listener.Application;
import com.mobian.pageModel.*;
import com.mobian.service.*;
import com.mobian.util.Constants;
import com.mobian.util.DateUtil;
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

	@Autowired
	private FdAccountServiceI fdAccountService;

	@Autowired
	private FdMemberAppointmentCommentServiceI fdMemberAppointmentCommentService;

	@Autowired
	private FdMemberServiceI fdMemberService;

	@Autowired
	private FdMemberAppointmentServiceI fdMemberAppointmentService;

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


	/**
	 * 获取账户信息接口
	 */
	@RequestMapping("/getAccount")
	@ResponseBody
	public Json getAccount(HttpServletRequest request) {
		Json j = new Json();
		try{
			SessionInfo s = getSessionInfo(request);
			FdAccount account = fdAccountService.get(Long.valueOf(s.getId()));
			j.setObj(account != null ? account : new FdAccount());
			j.setSuccess(true);
			j.setMsg("获取成功！");
		} catch (ServiceException e) {
			j.setObj(e.getMessage());
			logger.error("获取账户信息接口异常", e);
		} catch(Exception e) {
			j.setMsg(Application.getString(EX_0001));
			logger.error("获取账户信息接口异常", e);
		}

		return j;
	}

	/**
	 * 修改账户信息接口
	 */
	@RequestMapping("/updateAccount")
	@ResponseBody
	public Json updateAccount(FdAccount account, HttpServletRequest request) {
		Json j = new Json();
		try{
			SessionInfo s = getSessionInfo(request);

			account.setUserId(Long.valueOf(s.getId()));

			FdAccount fdAccount = fdAccountService.get(Long.valueOf(s.getId()));
			if(fdAccount == null) {
				fdAccountService.add(account);
			} else {
				fdAccountService.edit(account);
			}

			j.setSuccess(true);
			j.setMsg("修改成功！");
		} catch (ServiceException e) {
			j.setObj(e.getMessage());
			logger.error("修改账户信息接口异常", e);
		} catch(Exception e) {
			j.setMsg(Application.getString(EX_0001));
			logger.error("修改账户信息接口异常", e);
		}

		return j;
	}

	/**
	 * 获取我的评价
	 */
	@RequestMapping("/myComments")
	@ResponseBody
	public Json myComments(FdMemberAppointmentComment comment, PageHelper ph, HttpServletRequest request) {
		Json j = new Json();
		try{
			SessionInfo s = getSessionInfo(request);
			comment.setDoctorId(Integer.valueOf(s.getId()));
			comment.setIsAdmin(2);
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
			j.setMsg("获取成功！");
		} catch (ServiceException e) {
			j.setObj(e.getMessage());
			logger.error("获取我的评价接口异常", e);
		}catch(Exception e){
			j.setMsg(Application.getString(EX_0001));
			logger.error("获取我的评价接口异常", e);
		}

		return j;
	}

	/**
	 * 获取我的患者
	 */
	@RequestMapping("/myPatients")
	@ResponseBody
	public Json myPatients(PageHelper ph, HttpServletRequest request) {
		Json j = new Json();
		try{
			SessionInfo s = getSessionInfo(request);
			if(ph.getRows() == 0 || ph.getRows() > 50) {
				ph.setRows(10);
			}

			j.setObj(fdMemberDoctorService.patientDataGrid(Integer.valueOf(s.getId()), ph));
			j.setSuccess(true);
			j.setMsg("获取成功！");
		} catch (ServiceException e) {
			j.setObj(e.getMessage());
			logger.error("获取我的患者接口异常", e);
		}catch(Exception e){
			j.setMsg(Application.getString(EX_0001));
			logger.error("获取我的患者接口异常", e);
		}

		return j;
	}

	/**
	 * 获取我的患者详情
	 */
	@RequestMapping("/myPatientDetail")
	@ResponseBody
	public Json myPatientDetail(Integer userId, HttpServletRequest request) {
		Json j = new Json();
		try{
			SessionInfo s = getSessionInfo(request);
			Map<String, Object> obj = new HashMap<String, Object>();
			obj.put("patient", fdMemberService.getDetail(userId));

			FdMemberAppointment appointment = new FdMemberAppointment();
			appointment.setDoctorId(Integer.valueOf(s.getId()));
			appointment.setUserId(userId);
			appointment.setStatus("1");
			PageHelper ph = new PageHelper();
			ph.setSort("createTime");
			ph.setOrder("desc");
			ph.setPage(1);
			ph.setRows(50);
			ph.setHiddenTotal(true);
			List<FdMemberAppointment> appointments = fdMemberAppointmentService.dataGrid(appointment, ph).getRows();
			obj.put("appointments", appointments);

			j.setObj(obj);
			j.setSuccess(true);
			j.setMsg("获取成功！");
		} catch (ServiceException e) {
			j.setObj(e.getMessage());
			logger.error("获取我的患者详情接口异常", e);
		}catch(Exception e){
			j.setMsg(Application.getString(EX_0001));
			logger.error("获取我的患者详情接口异常", e);
		}

		return j;
	}

}
