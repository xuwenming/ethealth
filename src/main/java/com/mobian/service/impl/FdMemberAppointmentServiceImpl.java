package com.mobian.service.impl;

import com.mobian.absx.F;
import com.mobian.dao.FdMemberAppointmentDaoI;
import com.mobian.model.TfdMemberAppointment;
import com.mobian.pageModel.*;
import com.mobian.service.*;
import com.mobian.util.Constants;
import com.mobian.util.DateUtil;
import com.mobian.util.MyBeanUtils;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class FdMemberAppointmentServiceImpl extends BaseServiceImpl<FdMemberAppointment> implements FdMemberAppointmentServiceI {

	@Autowired
	private FdMemberAppointmentDaoI fdMemberAppointmentDao;

	@Autowired
	private FdMemberServiceI fdMemberService;

	@Autowired
	private FdMessageServiceI fdMessageService;

	@Autowired
	private FdBalanceLogServiceI fdBalanceLogService;

	@Autowired
	private FdPaymentBaseServiceI fdPaymentBaseService;

	@Override
	public DataGrid dataGrid(FdMemberAppointment fdMemberAppointment, PageHelper ph) {
		List<FdMemberAppointment> ol = new ArrayList<FdMemberAppointment>();
		String hql = " from TfdMemberAppointment t ";
		DataGrid dg = dataGridQuery(hql, ph, fdMemberAppointment, fdMemberAppointmentDao);
		@SuppressWarnings("unchecked")
		List<TfdMemberAppointment> l = dg.getRows();
		if (l != null && l.size() > 0) {
			for (TfdMemberAppointment t : l) {
				FdMemberAppointment o = new FdMemberAppointment();
				BeanUtils.copyProperties(t, o);
				ol.add(o);
			}
		}
		dg.setRows(ol);
		return dg;
	}
	

	protected String whereHql(FdMemberAppointment fdMemberAppointment, Map<String, Object> params) {
		String whereHql = "";	
		if (fdMemberAppointment != null) {
			whereHql += " where 1 = 1 ";
			if (!F.empty(fdMemberAppointment.getTime())) {
				whereHql += " and t.time = :time";
				params.put("time", fdMemberAppointment.getTime());
			}		
			if (!F.empty(fdMemberAppointment.getUserId())) {
				whereHql += " and t.userId = :userId";
				params.put("userId", fdMemberAppointment.getUserId());
			}		
			if (!F.empty(fdMemberAppointment.getDoctorId())) {
				whereHql += " and t.doctorId = :doctorId";
				params.put("doctorId", fdMemberAppointment.getDoctorId());
			}
			if (!F.empty(fdMemberAppointment.getAppointmentNo())) {
				whereHql += " and t.appointmentNo like :appointmentNo";
				params.put("appointmentNo",  "%" + fdMemberAppointment.getAppointmentNo() + "%");
			}
			if (!F.empty(fdMemberAppointment.getAppointTime())) {
				whereHql += " and t.appointTime like :appointTime";
				params.put("appointTime", fdMemberAppointment.getAppointTime() + "%");
			}		
			if (!F.empty(fdMemberAppointment.getSecondTime())) {
				whereHql += " and t.secondTime = :secondTime";
				params.put("secondTime", fdMemberAppointment.getSecondTime());
			}		
			if (!F.empty(fdMemberAppointment.getAppointName())) {
				whereHql += " and t.appointName = :appointName";
				params.put("appointName", fdMemberAppointment.getAppointName());
			}		
			if (!F.empty(fdMemberAppointment.getLinkName())) {
				whereHql += " and t.linkName = :linkName";
				params.put("linkName", fdMemberAppointment.getLinkName());
			}		
			if (!F.empty(fdMemberAppointment.getLinkWay())) {
				whereHql += " and t.linkWay = :linkWay";
				params.put("linkWay", fdMemberAppointment.getLinkWay());
			}		
			if (!F.empty(fdMemberAppointment.getAppointMessage())) {
				whereHql += " and t.appointMessage = :appointMessage";
				params.put("appointMessage", fdMemberAppointment.getAppointMessage());
			}		
			if (!F.empty(fdMemberAppointment.getCreateBy())) {
				whereHql += " and t.createBy = :createBy";
				params.put("createBy", fdMemberAppointment.getCreateBy());
			}		
			if (!F.empty(fdMemberAppointment.getCreateTime())) {
				whereHql += " and t.createTime = :createTime";
				params.put("createTime", fdMemberAppointment.getCreateTime());
			}		
			if (!F.empty(fdMemberAppointment.getUpdateBy())) {
				whereHql += " and t.updateBy = :updateBy";
				params.put("updateBy", fdMemberAppointment.getUpdateBy());
			}		
			if (!F.empty(fdMemberAppointment.getUpdateTime())) {
				whereHql += " and t.updateTime = :updateTime";
				params.put("updateTime", fdMemberAppointment.getUpdateTime());
			}		
			if (!F.empty(fdMemberAppointment.getStatus())) {
				whereHql += " and t.status = :status";
				params.put("status", fdMemberAppointment.getStatus());
				if(fdMemberAppointment.getIsShowWx() == null || !fdMemberAppointment.getIsShowWx()) {
					whereHql += " and t.sourse = 'AS02' ";
				}
			} else {
				if(fdMemberAppointment.getIsShowWx() != null && fdMemberAppointment.getIsShowWx()) {
					whereHql += " and (t.sourse = 'AS02' or (t.sourse = 'AS01' and t.status = '1')) ";
				} else {
					whereHql += " and t.sourse = 'AS02' ";
				}

			}
			if (!F.empty(fdMemberAppointment.getCouponId())) {
				whereHql += " and t.couponId = :couponId";
				params.put("couponId", fdMemberAppointment.getCouponId());
			}		
			if (!F.empty(fdMemberAppointment.getPics())) {
				whereHql += " and t.pics = :pics";
				params.put("pics", fdMemberAppointment.getPics());
			}		
			if (!F.empty(fdMemberAppointment.getAppointStatus())) {
				whereHql += " and t.appointStatus in (:appointStatus)";
				params.put("appointStatus", fdMemberAppointment.getAppointStatus().split(","));
			}		
			if (!F.empty(fdMemberAppointment.getAdjustment())) {
				whereHql += " and t.adjustment = :adjustment";
				params.put("adjustment", fdMemberAppointment.getAdjustment());
			}		
			if (!F.empty(fdMemberAppointment.getRefuseReason())) {
				whereHql += " and t.refuseReason = :refuseReason";
				params.put("refuseReason", fdMemberAppointment.getRefuseReason());
			}		
			if (!F.empty(fdMemberAppointment.getConfirmTime())) {
				whereHql += " and t.confirmTime = :confirmTime";
				params.put("confirmTime", fdMemberAppointment.getConfirmTime());
			}		
			if (!F.empty(fdMemberAppointment.getFirstConfirm())) {
				whereHql += " and t.firstConfirm = :firstConfirm";
				params.put("firstConfirm", fdMemberAppointment.getFirstConfirm());
			}		
			if (!F.empty(fdMemberAppointment.getFile())) {
				whereHql += " and t.file = :file";
				params.put("file", fdMemberAppointment.getFile());
			}
			if(!F.empty(fdMemberAppointment.getQuery())) {
				whereHql += " and (t.appointName like :query or t.linkName like :query or t.linkWay like :query) ";
				params.put("query", "%" + fdMemberAppointment.getQuery() + "%");
			}
		}
		return whereHql;
	}

	@Override
	public void add(FdMemberAppointment fdMemberAppointment) {
		TfdMemberAppointment t = new TfdMemberAppointment();
		BeanUtils.copyProperties(fdMemberAppointment, t);
		//t.setId(jb.absx.UUID.uuid());
		fdMemberAppointmentDao.save(t);
		fdMemberAppointment.setId(t.getId());
	}

	@Override
	public FdMemberAppointment get(Integer id) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		TfdMemberAppointment t = fdMemberAppointmentDao.get("from TfdMemberAppointment t  where t.id = :id", params);
		FdMemberAppointment o = new FdMemberAppointment();
		BeanUtils.copyProperties(t, o);
		return o;
	}

	@Override
	public FdMemberAppointment get(FdMemberAppointment fdMemberAppointment) {
		String hql = " from TfdMemberAppointment t ";
		@SuppressWarnings("unchecked")
		List<TfdMemberAppointment> l = query(hql, fdMemberAppointment, fdMemberAppointmentDao);
		FdMemberAppointment o = null;
		if (CollectionUtils.isNotEmpty(l)) {
			o = new FdMemberAppointment();
			BeanUtils.copyProperties(l.get(0), o);
		}
		return o;
	}

	@Override
	public FdMemberAppointment getByAppointmentNo(String appointmentNo) {
		FdMemberAppointment o = null;
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("appointmentNo", appointmentNo);
		TfdMemberAppointment t = fdMemberAppointmentDao.get("from TfdMemberAppointment t  where t.appointmentNo = :appointmentNo", params);
		if(t != null) {
			o = new FdMemberAppointment();
			BeanUtils.copyProperties(t, o);
		}
		return o;
	}

	@Override
	public void updatePaySuccess(FdMemberAppointment appointment) {
		edit(appointment);

		// 添加用户消息
		FdMember user = fdMemberService.getDetail(appointment.getUserId());
		FdMember doctor = fdMemberService.getDetail(appointment.getDoctorId());

		FdMessage message = new FdMessage();
		message.setTitle("加号提醒");
		String content = "尊敬的医生您好，手机号" + user.getMobile() + "用户成功支付了您的加号服务!" +
				"\n患者姓名：" + appointment.getAppointName() +
				"\n预约时间：" + appointment.getAppointTime() +
				"\n预约地点：" + appointment.getAppointAddress() +
				"\n请您及时回复确认。";
		message.setContent(content);
		message.setUserId(appointment.getDoctorId());
		message.setMtype("MT02");
		message.setIsRead(false);
		message.setAlias("2_" + doctor.getMobile());
		message.setPushMessage(new PushMessage("M101", "手机号" + user.getMobile() + "用户成功支付了您的加号服务!，预约时间：" + appointment.getAppointTime()));
		fdMessageService.addAndPushMessage(message);

		message = new FdMessage();
		message.setTitle("预约支付成功");
		content = "尊敬的用户您好，您已成功支付了" + doctor.getCustomer().getRealName() + "医生的预约服务！" +
				"\n患者姓名：" + appointment.getAppointName() +
				"\n预约时间：" + appointment.getAppointTime() +
				"\n预约地点：" + appointment.getAppointAddress() +
				"\n请耐心等待医生确认。";
		message.setContent(content);
		message.setUserId(appointment.getUserId());
		message.setMtype("MT02");
		message.setIsRead(false);
		message.setAlias("0_" + user.getMobile());
		fdMessageService.add(message);
	}

	@Override
	public void editAppointment(FdMemberAppointment appointment) {
		FdMemberAppointment o = get(appointment.getId());
		if("1".equals(appointment.getAppointStatus())) {
			appointment.setConfirmTime(o.getAppointTime());
		}
		edit(appointment);

		if("1".equals(o.getStatus()) && "3".equals(appointment.getAppointStatus())) {
			// 拒绝 退款
			FdPaymentBase paymentBase = new FdPaymentBase();
			paymentBase.setRefId(appointment.getId() + "");
			paymentBase.setUserId(o.getUserId());
			paymentBase.setOrderNo(o.getAppointmentNo());
			fdPaymentBaseService.refund(paymentBase, "预约拒绝");

			FdMember user = fdMemberService.getDetail(o.getUserId());
			FdMember doctor = fdMemberService.getDetail(o.getDoctorId());
			FdMessage message = new FdMessage();
			message.setTitle("预约拒绝提醒");
			String content = "尊敬的用户您好，" + doctor.getCustomer().getRealName() + "医生拒绝了您的预约！" +
					"\n患者姓名：" + o.getAppointName() +
					"\n预约时间：" + o.getAppointTime() +
					"\n预约地点：" + o.getAppointAddress() +
					"\n如有问题请及时联系客服，谢谢！";
			message.setContent(content);
			message.setUserId(o.getUserId());
			message.setMtype("MT02");
			message.setIsRead(false);
			message.setAlias("0_" + user.getMobile());
			message.setPushMessage(new PushMessage("M103", doctor.getCustomer().getRealName() + "医生拒绝了您的预约，预约时间：" + o.getAppointTime()));
			fdMessageService.addAndPushMessage(message);

		} else if("1".equals(appointment.getAppointStatus())) {
			// 医生确认余额到账
			FdBalanceLog balanceLog = new FdBalanceLog();
			balanceLog.setUserId(o.getDoctorId().longValue());
			balanceLog.setRefType("BBT003");
			balanceLog.setRefId(appointment.getId() + "");
			balanceLog.setAmount(BigDecimal.valueOf(o.getAmount()).divide(new BigDecimal(100)).floatValue());
			balanceLog.setStatus(false);
			fdBalanceLogService.addLogAndUpdateBalance(balanceLog);

			FdMember user = fdMemberService.getDetail(o.getUserId());
			FdMember doctor = fdMemberService.getDetail(o.getDoctorId());
			FdMessage message = new FdMessage();
			message.setTitle("预约确认提醒");
			String content = "尊敬的用户您好，" + doctor.getCustomer().getRealName() + "医生确认了您的预约！" +
					"\n患者姓名：" + o.getAppointName() +
					"\n预约时间：" + o.getAppointTime() +
					"\n预约地点：" + o.getAppointAddress() +
					"\n请按照预约信息准时到达，超过时间约定本次预约将作废处理。如有问题请及时联系客服，谢谢！";
			message.setContent(content);
			message.setUserId(o.getUserId());
			message.setMtype("MT02");
			message.setIsRead(false);
			message.setAlias("0_" + user.getMobile());
			message.setPushMessage(new PushMessage("M102", doctor.getCustomer().getRealName() + "医生确认了您的预约，预约时间：" + o.getAppointTime()));
			fdMessageService.addAndPushMessage(message);
		}
	}

	@Override
	public void edit(FdMemberAppointment fdMemberAppointment) {
		TfdMemberAppointment t = fdMemberAppointmentDao.get(TfdMemberAppointment.class, fdMemberAppointment.getId());
		if (t != null) {
			MyBeanUtils.copyProperties(fdMemberAppointment, t, new String[] { "id" , "addtime", "isdeleted","updatetime" },true);
		}
	}

	@Override
	public void delete(Integer id) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		fdMemberAppointmentDao.executeHql("update TfdMemberAppointment t set t.isdeleted = 1 where t.id = :id",params);
		//fdMemberAppointmentDao.delete(fdMemberAppointmentDao.get(TfdMemberAppointment.class, id));
	}

}
