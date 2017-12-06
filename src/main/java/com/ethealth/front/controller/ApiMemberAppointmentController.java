package com.ethealth.front.controller;

import com.mobian.absx.F;
import com.mobian.concurrent.CacheKey;
import com.mobian.concurrent.CompletionService;
import com.mobian.concurrent.Task;
import com.mobian.controller.BaseController;
import com.mobian.enums.EnumConstants;
import com.mobian.exception.ServiceException;
import com.mobian.listener.Application;
import com.mobian.pageModel.*;
import com.mobian.service.*;
import com.mobian.service.impl.CompletionFactory;
import com.mobian.util.Constants;
import com.mobian.util.DateUtil;
import com.mobian.util.Util;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.*;

/**
 * 专家预约接口
 * 
 * @author John
 * 
 */
@Controller
@RequestMapping("/api/appointment")
public class ApiMemberAppointmentController extends BaseController {

	@Autowired
	private FdMemberDoctorServiceI fdMemberDoctorService;

	@Autowired
	private FdDoctorTimeServiceI fdDoctorTimeService;

	@Autowired
	private FdMemberAppointmentServiceI fdMemberAppointmentService;

	@Autowired
	private FdCustomerServiceI fdCustomerService;

	@Autowired
	private FdMemberAppointmentCommentServiceI fdMemberAppointmentCommentService;

	/**
	 * 获取专家预约信息接口
	 */
	@RequestMapping("/getInfo")
	@ResponseBody
	public Json getInfo(Integer doctorId) {
		Json j = new Json();
		try{
			Map<String, Object> obj = new HashMap<String, Object>();
			obj.put("doctor", fdMemberDoctorService.getDetail(doctorId)); // 专家信息

			// 获取接诊时间/预约时间
			FdDoctorTime doctorTime = new FdDoctorTime();
			doctorTime.setUserId(doctorId);
			PageHelper ph = new PageHelper();
			ph.setSort("week");
			ph.setOrder("asc");
			ph.setHiddenTotal(true);
			List<FdDoctorTime> doctorTimes = fdDoctorTimeService.dataGrid(doctorTime, ph).getRows();

			Map<Integer, FdDoctorTime> doctorTimeMap = new HashMap<Integer, FdDoctorTime>();
			Map<String, String> dtMap = new HashMap<String, String>();
			for(FdDoctorTime dt : doctorTimes) {
				if(!F.empty(dt.getAddress())) {
					if(dtMap.get(dt.getAddress()) != null) {
						dtMap.put(dt.getAddress(), dtMap.get(dt.getAddress()) + "、" + dt.getWeekZh() + dt.getTimeZh());
					} else {
						dtMap.put(dt.getAddress(), dt.getWeekZh() + dt.getTimeZh());
					}
				}

				doctorTimeMap.put(dt.getWeek(), dt);
			}

			List<String> dts = new ArrayList<String>();
			for(String key : dtMap.keySet()) {
				dts.add(dtMap.get(key) + key);
			}
			obj.put("doctorTimes", dts);

			List<Map<String, Object>> times = new ArrayList<Map<String, Object>>();
			Calendar c = Calendar.getInstance();
			c.add(Calendar.DAY_OF_MONTH, 2); // 两天后
			for(int i=1; i<=30; i++) {
				Map<String, Object> timeM = new HashMap<String, Object>();
				c.add(Calendar.DAY_OF_MONTH, 1);
				int week = c.get(Calendar.DAY_OF_WEEK);
				week = week == 1 ? 7 : week - 1;

				if(doctorTimeMap.containsKey(week)) {
					FdDoctorTime dt = doctorTimeMap.get(week);
					String date = DateUtil.format(c.getTime(), Constants.DATE_FORMAT_YMD);
					timeM.put("date", date);
					timeM.put("week", dt.getWeek());
					timeM.put("time", dt.getTime());
					timeM.put("weekZh", dt.getWeekZh());
					timeM.put("timeZh", dt.getTimeZh());

					// 获取预约余号
					FdMemberAppointment appointment = new FdMemberAppointment();
					appointment.setDoctorId(doctorId);
					appointment.setAppointStatus("1,2");
					appointment.setConfirmTime(date);
					appointment.setTime(dt.getTime());
					ph = new PageHelper();
					ph.setHiddenTotal(true);
					List<FdMemberAppointment> appointments = fdMemberAppointmentService.dataGrid(appointment, ph).getRows();
					int remainNum = dt.getNumber() - appointments.size();
					timeM.put("remainNum", remainNum < 0 ? 0 : remainNum);

					times.add(timeM);
				}
			}
			obj.put("times", times);

			j.setObj(obj);
			j.setSuccess(true);
			j.setMsg("获取专家预约信息成功！");
		}catch(Exception e){
			j.setMsg(Application.getString(EX_0001));
			logger.error("获取专家预约信息接口异常", e);
		}

		return j;
	}

	/**
	 * 预约下单接口
	 */
	@RequestMapping("/add")
	@ResponseBody
	public Json add(FdMemberAppointment appointment, HttpServletRequest request) {
		Json j = new Json();
		try{
			Map<String, Object> obj = new HashMap<String, Object>();
			if(F.empty(appointment.getDoctorId())) {
				j.setMsg("专家Id不能为空！");
				return j;
			}
			if(F.empty(appointment.getAppointTime()) || F.empty(appointment.getTime())) {
				j.setMsg("预约时间不能为空！");
				return j;
			}
			SessionInfo s = getSessionInfo(request);
			FdMemberAppointment exist = new FdMemberAppointment();
			exist.setUserId(Integer.valueOf(s.getId()));
			exist.setDoctorId(appointment.getDoctorId());
			exist.setStatus("0");
			exist.setAppointStatus("0");
			exist = fdMemberAppointmentService.get(exist);
			if(exist != null) {
				obj.put("appointmentNo", -1);
				j.setMsg("您尚有未支付的预约订单，请前往我的预约支付或取消！");
				j.setObj(obj);
				return j;
			}

			exist = new FdMemberAppointment();
			exist.setUserId(Integer.valueOf(s.getId()));
			exist.setDoctorId(appointment.getDoctorId());
			exist.setStatus("1");
			exist.setAppointTime(appointment.getAppointTime() + EnumConstants.TIME.getCnName(appointment.getTime()));
			exist = fdMemberAppointmentService.get(exist);
			if(exist != null && !"3".equals(exist.getAppointStatus())) {
				obj.put("appointmentNo", -2);
				j.setMsg("您在" + exist.getAppointTime()+"已预约，请勿重复预约！");
				j.setObj(obj);
				return j;
			}

			Double fee = Double.valueOf(Application.getString("UC001", "99"));
			long totalFee = new BigDecimal(fee.toString()).multiply(new BigDecimal(100)).longValue();

			FdCustomer customer = fdCustomerService.get(Long.valueOf(s.getId()));
			appointment.setUserId(Integer.valueOf(s.getId()));
			appointment.setAppointName(customer.getRealName());
			appointment.setLinkName(customer.getRealName());
			appointment.setLinkWay(s.getName());
			appointment.setCreateBy(appointment.getUserId());
			appointment.setStatus("0"); // 未支付默认删除
			appointment.setSourse("AS02");
			appointment.setAppointTime(appointment.getAppointTime() + EnumConstants.TIME.getCnName(appointment.getTime()));
			appointment.setSecondTime(appointment.getAppointTime());

			appointment.setAppointmentNo(Util.CreateNo("Y"));
			appointment.setAmount(totalFee);

			fdMemberAppointmentService.add(appointment);

			obj.put("appointmentNo", appointment.getAppointmentNo());
			obj.put("totalFee", totalFee);

			j.setObj(obj);
			j.setSuccess(true);
			j.setMsg("下单成功！");

		} catch (ServiceException e) {
			j.setObj(e.getMessage());
			logger.error("预约下单接口异常", e);
		}catch(Exception e){
			j.setMsg(Application.getString(EX_0001));
			logger.error("预约下单接口异常", e);
		}

		return j;
	}

	/**
	 * 获取我的预约
	 */
	@RequestMapping("/getMyAppointments")
	@ResponseBody
	public Json getMyAppointments(FdMemberAppointment appointment, PageHelper ph, HttpServletRequest request) {
		Json j = new Json();
		try{
			SessionInfo s = getSessionInfo(request);

			if(ph.getRows() == 0 || ph.getRows() > 50) {
				ph.setRows(10);
			}
			if(F.empty(ph.getSort())) {
				ph.setSort("createTime");
			}
			if(F.empty(ph.getOrder())) {
				ph.setOrder("desc");
			}

			appointment.setUserId(Integer.valueOf(s.getId()));
			DataGrid dg = fdMemberAppointmentService.dataGrid(appointment, ph);
			List<FdMemberAppointment> list = dg.getRows();
			if(CollectionUtils.isNotEmpty(list)) {
				CompletionService completionService = CompletionFactory.initCompletion();
				for(FdMemberAppointment o : list) {
					completionService.submit(new Task<FdMemberAppointment, FdMemberDoctor>(new CacheKey("fdMemberDoctor", o.getDoctorId() + ""), o) {
						@Override
						public FdMemberDoctor call() throws Exception {
							return fdMemberDoctorService.getDetail(getD().getDoctorId());
						}

						protected void set(FdMemberAppointment d, FdMemberDoctor v) {
							d.setDoctor(v);
						}
					});
					if("2".equals(o.getAppointStatus())) {
						completionService.submit(new Task<FdMemberAppointment, Boolean>(o) {
							@Override
							public Boolean call() throws Exception {
								FdMemberAppointmentComment comment = new FdMemberAppointmentComment();
								comment.setAppointmentId(getD().getId());
								comment.setUserId(getD().getUserId());
								List<FdMemberAppointmentComment> comments = fdMemberAppointmentCommentService.query(comment);
								return CollectionUtils.isNotEmpty(comments) ? true : false;
							}

							protected void set(FdMemberAppointment d, Boolean v) {
								d.setIsCommented(v);
							}
						});
					}
				}
				completionService.sync();
			}
			j.setObj(dg);
			j.setSuccess(true);
			j.setMsg("获取我的预约成功！");

		} catch (ServiceException e) {
			j.setObj(e.getMessage());
			logger.error("获取我的预约接口异常", e);
		}catch(Exception e){
			j.setMsg(Application.getString(EX_0001));
			logger.error("获取我的预约接口异常", e);
		}

		return j;
	}

	/**
	 * 获取预约详情
	 */
	@RequestMapping("/getAppointmentDetail")
	@ResponseBody
	public Json getAppointmentDetail(Integer id, HttpServletRequest request) {
		Json j = new Json();
		try{
			SessionInfo s = getSessionInfo(request);
			if(!F.empty(s.getId())) {
				FdMemberAppointment appointment = fdMemberAppointmentService.get(id);
				FdMemberDoctor doctor = fdMemberDoctorService.getDetail(appointment.getDoctorId());
				doctor.setDoctorTimes(fdDoctorTimeService.getGroupTimesByDoctorId(doctor.getId()));
				appointment.setDoctor(doctor);
				appointment.setIsCommented(false);
				if("2".equals(appointment.getAppointStatus())) {
					FdMemberAppointmentComment comment = new FdMemberAppointmentComment();
					comment.setAppointmentId(appointment.getId());
					comment.setUserId(appointment.getUserId());
					List<FdMemberAppointmentComment> comments = fdMemberAppointmentCommentService.query(comment);
					if(CollectionUtils.isNotEmpty(comments)) {
						appointment.setIsCommented(true);
						appointment.setComment(comments.get(0));
					}
				}
				j.setObj(appointment);
				j.setSuccess(true);
				j.setMsg("获取预约详情成功！");
			}

		} catch (ServiceException e) {
			j.setObj(e.getMessage());
			logger.error("获取预约详情接口异常", e);
		}catch(Exception e){
			j.setMsg(Application.getString(EX_0001));
			logger.error("获取预约详情接口异常", e);
		}

		return j;
	}

	/**
	 * 更新预约状态
	 */
	@RequestMapping("/updateAppointmentStatus")
	@ResponseBody
	public Json updateAppointmentStatus(FdMemberAppointment appointment, HttpServletRequest request) {
		Json j = new Json();
		try{
			SessionInfo s = getSessionInfo(request);
			if(!F.empty(s.getId())) {
				FdMemberAppointment o = fdMemberAppointmentService.get(appointment.getId());
				if("1".equals(appointment.getAppointStatus())) {
					appointment.setConfirmTime(o.getAppointTime());
				}
				fdMemberAppointmentService.edit(appointment);

				j.setSuccess(true);
				j.setMsg("更新成功！");
			}

		} catch (ServiceException e) {
			j.setObj(e.getMessage());
			logger.error("更新预约状态接口异常", e);
		}catch(Exception e){
			j.setMsg(Application.getString(EX_0001));
			logger.error("更新预约状态接口异常", e);
		}

		return j;
	}

	/**
	 * 预约评价接口
	 */
	@RequestMapping("/addComment")
	@ResponseBody
	public Json addComment(FdMemberAppointmentComment comment, HttpServletRequest request) {
		Json j = new Json();
		try{
			SessionInfo s = getSessionInfo(request);
			FdMemberAppointment appointment = fdMemberAppointmentService.get(comment.getAppointmentId());

			FdMemberAppointmentComment exist = new FdMemberAppointmentComment();
			exist.setAppointmentId(comment.getAppointmentId());
			exist.setUserId(Integer.valueOf(s.getId()));
			if(CollectionUtils.isNotEmpty(fdMemberAppointmentCommentService.query(exist))) {
				j.setMsg("评价失败，重复评价！");
				return j;
			}

			comment.setDoctorId(appointment.getDoctorId());
			comment.setUserId(Integer.valueOf(s.getId()));

			fdMemberAppointmentCommentService.add(comment);
			j.setSuccess(true);
			j.setMsg("评价成功！");

		} catch (ServiceException e) {
			j.setObj(e.getMessage());
			logger.error("预约评价接口异常", e);
		}catch(Exception e){
			j.setMsg(Application.getString(EX_0001));
			logger.error("预约评价接口异常", e);
		}

		return j;
	}

}
