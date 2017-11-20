package com.ethealth.front.controller;

import com.mobian.absx.F;
import com.mobian.controller.BaseController;
import com.mobian.exception.ServiceException;
import com.mobian.listener.Application;
import com.mobian.pageModel.*;
import com.mobian.service.FdDoctorTimeServiceI;
import com.mobian.service.FdMemberConsultationExpireServiceI;
import com.mobian.service.FdMemberConsultationOrderServiceI;
import com.mobian.service.FdMemberDoctorServiceI;
import com.mobian.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.*;

/**
 * 专家咨询接口
 * 
 * @author John
 * 
 */
@Controller
@RequestMapping("/api/consultation")
public class ApiMemberConsultationController extends BaseController {

	@Autowired
	private FdMemberDoctorServiceI fdMemberDoctorService;

	@Autowired
	private FdDoctorTimeServiceI fdDoctorTimeService;

	@Autowired
	private FdMemberConsultationExpireServiceI fdMemberConsultationExpireService;

	@Autowired
	private FdMemberConsultationOrderServiceI fdMemberConsultationOrderService;

	/**
	 * 获取专家咨询信息接口
	 */
	@RequestMapping("/getInfo")
	@ResponseBody
	public Json getInfo(Integer doctorId, HttpServletRequest request) {
		Json j = new Json();
		try{
			SessionInfo s = getSessionInfo(request);

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

			// 获取咨询有效期
			FdMemberConsultationExpire expire = fdMemberConsultationExpireService.getByUserIdAndDoctorId(Integer.valueOf(s.getId()), doctorId);
			if(expire != null) {
				if(expire.getExpireDate().before(new Date())) {
					obj.put("isConsultation", true);
					obj.put("expire", expire);
				} else {
					obj.put("isConsultation", false);
				}
			} else {
				obj.put("isConsultation", false);
			}

			j.setObj(obj);
			j.setSuccess(true);
			j.setMsg("获取专家咨询信息成功！");
		} catch (ServiceException e) {
			j.setObj(e.getMessage());
			logger.error("获取专家咨询信息接口异常", e);
		}catch(Exception e){
			j.setMsg(Application.getString(EX_0001));
			logger.error("获取专家预约信息接口异常", e);
		}

		return j;
	}

	/**
	 * 咨询下单接口
	 */
	@RequestMapping("/add")
	@ResponseBody
	public Json add(FdMemberConsultationOrder consultationOrder, HttpServletRequest request) {
		Json j = new Json();
		try{
			Map<String, Object> obj = new HashMap<String, Object>();
			if(F.empty(consultationOrder.getDoctorId())) {
				j.setMsg("专家Id不能为空！");
				return j;
			}
			SessionInfo s = getSessionInfo(request);
			FdMemberConsultationOrder existOrder = new FdMemberConsultationOrder();
			existOrder.setUserId(Integer.valueOf(s.getId()));
			existOrder.setDoctorId(consultationOrder.getDoctorId());
			existOrder.setStatus("1");
			existOrder = fdMemberConsultationOrderService.get(existOrder);

			if(existOrder == null) {
				consultationOrder.setUserId(Integer.valueOf(s.getId()));
				consultationOrder.setStatus("1");
				consultationOrder.setOrderNo(Util.CreateNo("Z"));

				fdMemberConsultationOrderService.add(consultationOrder);

				obj.put("orderNo", consultationOrder.getOrderNo());
			} else {
				obj.put("orderNo", existOrder.getOrderNo());
			}

			double fee = Double.valueOf(Application.getString("UC002", "99"));
			obj.put("totalFee", BigDecimal.valueOf(fee).multiply(new BigDecimal(100)).longValue());

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

}
