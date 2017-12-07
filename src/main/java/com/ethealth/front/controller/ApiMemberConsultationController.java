package com.ethealth.front.controller;

import com.mobian.absx.F;
import com.mobian.concurrent.CacheKey;
import com.mobian.concurrent.CompletionService;
import com.mobian.concurrent.Task;
import com.mobian.controller.BaseController;
import com.mobian.exception.ServiceException;
import com.mobian.listener.Application;
import com.mobian.pageModel.*;
import com.mobian.service.*;
import com.mobian.service.impl.CompletionFactory;
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

	@Autowired
	private FdMemberConsultationFriendServiceI fdMemberConsultationFriendService;

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
			obj.put("doctorTimes", fdDoctorTimeService.getGroupTimesByDoctorId(doctorId));

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

			Double fee = Double.valueOf(Application.getString("UC002", "99"));
			long totalFee = new BigDecimal(fee.toString()).multiply(new BigDecimal(100)).longValue();

			FdMemberConsultationOrder existOrder = new FdMemberConsultationOrder();
			existOrder.setUserId(Integer.valueOf(s.getId()));
			existOrder.setDoctorId(consultationOrder.getDoctorId());
			existOrder.setStatus("1");
			existOrder = fdMemberConsultationOrderService.get(existOrder);

			if(existOrder == null) {
				consultationOrder.setUserId(Integer.valueOf(s.getId()));
				consultationOrder.setStatus("1");
				consultationOrder.setOrderNo(Util.CreateNo("Z"));
				consultationOrder.setAmount(totalFee);

				fdMemberConsultationOrderService.add(consultationOrder);

				obj.put("orderNo", consultationOrder.getOrderNo());
			} else {
				if(totalFee != existOrder.getAmount()) {
					existOrder.setAmount(totalFee);
					fdMemberConsultationOrderService.edit(existOrder);
				}
				obj.put("orderNo", existOrder.getOrderNo());
			}

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
	 * 获取我的咨询
	 */
	@RequestMapping("/getMyConsultations")
	@ResponseBody
	public Json getMyConsultations(FdMemberConsultationFriend consultationFriend, PageHelper ph, Integer isAdmin, HttpServletRequest request) {
		Json j = new Json();
		try{
			SessionInfo s = getSessionInfo(request);

			if(ph.getRows() == 0 || ph.getRows() > 50) {
				ph.setRows(10);
			}
			if(F.empty(ph.getSort())) {
				ph.setSort("lastTime");
			}
			if(F.empty(ph.getOrder())) {
				ph.setOrder("desc");
			}

			isAdmin = F.empty(isAdmin) ? 0 : isAdmin;
			consultationFriend.setIsAdmin(isAdmin);
			if(isAdmin == 0) {
				consultationFriend.setUserId(Integer.valueOf(s.getId()));
			} else {
				consultationFriend.setDoctorId(Integer.valueOf(s.getId()));
			}
			DataGrid dg = fdMemberConsultationFriendService.dataGridComplex(consultationFriend, ph);
			List<FdMemberConsultationFriend> list = dg.getRows();
			if(CollectionUtils.isNotEmpty(list)) {
				CompletionService completionService = CompletionFactory.initCompletion();
				for(FdMemberConsultationFriend o : list) {
					completionService.submit(new Task<FdMemberConsultationFriend, FdMemberConsultationExpire>(o) {
						@Override
						public FdMemberConsultationExpire call() throws Exception {
							FdMemberConsultationExpire expire = fdMemberConsultationExpireService.getByUserIdAndDoctorId(getD().getUserId(), getD().getDoctorId());
							return expire;
						}

						protected void set(FdMemberConsultationFriend d, FdMemberConsultationExpire v) {
							if(v != null) {
								if(v.getExpireDate().before(new Date())) {
									d.setExpire(v);
									d.setIsConsultation(true);
								} else {
									d.setIsConsultation(false);
								}
							} else {
								d.setIsConsultation(false);
							}
						}
					});

				}
				completionService.sync();
			}
			j.setObj(dg);
			j.setSuccess(true);
			j.setMsg("获取我的咨询列表成功！");

		} catch (ServiceException e) {
			j.setObj(e.getMessage());
			logger.error("获取我的咨询列表接口异常", e);
		}catch(Exception e){
			j.setMsg(Application.getString(EX_0001));
			logger.error("获取我的咨询列表接口异常", e);
		}

		return j;
	}

	/**
	 * 获取我的咨询
	 */
	@RequestMapping("/doctor/consultations")
	@ResponseBody
	public Json consultations(FdMemberConsultationFriend consultationFriend, PageHelper ph, HttpServletRequest request) {

		return getMyConsultations(consultationFriend, ph, 2, request);
	}

	/**
	 * 上传最新聊天咨询接口
	 */
	@RequestMapping("/updateNewestConsultation")
	@ResponseBody
	public Json updateNewestConsultation(FdMemberConsultationFriend consultationFriend, HttpServletRequest request) {
		Json j = new Json();
		try{
			SessionInfo s = getSessionInfo(request);
			if(!F.empty(s.getId())) {
				Integer userId = 0, doctorId = 0;
				if(consultationFriend.getSenderType() == 1) { // 患者发来的消息
					userId = Integer.valueOf(s.getId());
					doctorId = consultationFriend.getReceiverId();
				} else { // 医生发来的消息
					doctorId = Integer.valueOf(s.getId());
					userId = consultationFriend.getReceiverId();
				}
				consultationFriend.setUserId(userId);
				consultationFriend.setDoctorId(doctorId);
				consultationFriend.setLastTime(new Date());
				consultationFriend.setStatus("0");
				FdMemberConsultationFriend friend = fdMemberConsultationFriendService.getByUserIdAndDoctorId(userId, doctorId);
				if(friend == null) {
					fdMemberConsultationFriendService.add(consultationFriend);
				} else {
					consultationFriend.setId(friend.getId());
					fdMemberConsultationFriendService.edit(consultationFriend);
				}

				// TODO 广播消息 刷新首页的就诊动态
				j.setSuccess(true);
				j.setMsg("更新成功！");
			}

		} catch (ServiceException e) {
			j.setObj(e.getMessage());
			logger.error("上传最新咨询接口异常", e);
		}catch(Exception e){
			j.setMsg(Application.getString(EX_0001));
			logger.error("上传最新咨询接口异常", e);
		}

		return j;
	}

}
