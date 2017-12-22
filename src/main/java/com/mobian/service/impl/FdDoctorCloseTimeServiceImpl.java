package com.mobian.service.impl;

import java.math.BigDecimal;
import java.util.*;

import com.mobian.absx.F;
import com.mobian.dao.FdDoctorCloseTimeDaoI;
import com.mobian.enums.EnumConstants;
import com.mobian.model.TfdDoctorCloseTime;
import com.mobian.pageModel.*;
import com.mobian.service.*;

import com.mobian.util.Constants;
import com.mobian.util.DateUtil;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mobian.util.MyBeanUtils;

@Service
public class FdDoctorCloseTimeServiceImpl extends BaseServiceImpl<FdDoctorCloseTime> implements FdDoctorCloseTimeServiceI {

	@Autowired
	private FdDoctorCloseTimeDaoI fdDoctorCloseTimeDao;

	@Autowired
	private FdMemberAppointmentServiceI fdMemberAppointmentService;

	@Autowired
	private FdPaymentBaseServiceI fdPaymentBaseService;

	@Autowired
	private FdBalanceLogServiceI fdBalanceLogService;

	@Autowired
	private FdMemberServiceI fdMemberService;

	@Autowired
	private FdMessageServiceI fdMessageService;

	@Override
	public DataGrid dataGrid(FdDoctorCloseTime fdDoctorCloseTime, PageHelper ph) {
		List<FdDoctorCloseTime> ol = new ArrayList<FdDoctorCloseTime>();
		String hql = " from TfdDoctorCloseTime t ";
		DataGrid dg = dataGridQuery(hql, ph, fdDoctorCloseTime, fdDoctorCloseTimeDao);
		@SuppressWarnings("unchecked")
		List<TfdDoctorCloseTime> l = dg.getRows();
		if (l != null && l.size() > 0) {
			Calendar c = Calendar.getInstance();
			for (TfdDoctorCloseTime t : l) {
				FdDoctorCloseTime o = new FdDoctorCloseTime();
				BeanUtils.copyProperties(t, o);
				c.setTime(t.getCloseDate());
				int w = c.get(Calendar.DAY_OF_WEEK);
				w = w == 1 ? 7 : w - 1;
				o.setWeek(EnumConstants.WEEK.getCnName(w));
				ol.add(o);
			}
		}
		dg.setRows(ol);
		return dg;
	}
	

	protected String whereHql(FdDoctorCloseTime fdDoctorCloseTime, Map<String, Object> params) {
		String whereHql = "";	
		if (fdDoctorCloseTime != null) {
			whereHql += " where t.status = 0 ";
			if (!F.empty(fdDoctorCloseTime.getDoctorId())) {
				whereHql += " and t.doctorId = :doctorId";
				params.put("doctorId", fdDoctorCloseTime.getDoctorId());
			}		
			if (!F.empty(fdDoctorCloseTime.getCreateTime())) {
				whereHql += " and t.createTime = :createTime";
				params.put("createTime", fdDoctorCloseTime.getCreateTime());
			}		
			if (!F.empty(fdDoctorCloseTime.getUpdateTime())) {
				whereHql += " and t.updateTime = :updateTime";
				params.put("updateTime", fdDoctorCloseTime.getUpdateTime());
			}		
			if (!F.empty(fdDoctorCloseTime.getStatus())) {
				whereHql += " and t.status = :status";
				params.put("status", fdDoctorCloseTime.getStatus());
			}
			if (fdDoctorCloseTime.getCloseDate() != null) {
				whereHql += " and t.closeDate = :closeDate";
				params.put("closeDate", fdDoctorCloseTime.getCloseDate());
			}
			if (fdDoctorCloseTime.getTime() != null) {
				whereHql += " and t.time = :time";
				params.put("time", fdDoctorCloseTime.getTime());
			}
			if(fdDoctorCloseTime.getTimes() != null) {
				whereHql += " and t.time in (:times)";
				params.put("times", fdDoctorCloseTime.getTimes());
			}
		}	
		return whereHql;
	}

	@Override
	public void add(FdDoctorCloseTime fdDoctorCloseTime) {
		TfdDoctorCloseTime t = new TfdDoctorCloseTime();
		BeanUtils.copyProperties(fdDoctorCloseTime, t);
		//t.setId(jb.absx.UUID.uuid());
		fdDoctorCloseTimeDao.save(t);
	}

	@Override
	public FdDoctorCloseTime get(Integer id) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		TfdDoctorCloseTime t = fdDoctorCloseTimeDao.get("from TfdDoctorCloseTime t  where t.id = :id", params);
		FdDoctorCloseTime o = new FdDoctorCloseTime();
		BeanUtils.copyProperties(t, o);
		return o;
	}

	@Override
	public void edit(FdDoctorCloseTime fdDoctorCloseTime) {
		TfdDoctorCloseTime t = fdDoctorCloseTimeDao.get(TfdDoctorCloseTime.class, fdDoctorCloseTime.getId());
		if (t != null) {
			MyBeanUtils.copyProperties(fdDoctorCloseTime, t, new String[] { "id" , "addtime", "isdeleted","updatetime" },true);
		}
	}

	@Override
	public void delete(Integer id) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		fdDoctorCloseTimeDao.executeHql("update TfdDoctorCloseTime t set t.status = 1 where t.id = :id",params);
		//fdDoctorCloseTimeDao.delete(fdDoctorCloseTimeDao.get(TfdDoctorCloseTime.class, id));
	}

	@Override
	public void addCloseTime(FdDoctorCloseTime closeTime) {
		add(closeTime);

		// 预约退款
		FdMemberAppointment appointment = new FdMemberAppointment();
		appointment.setDoctorId(closeTime.getDoctorId());
		appointment.setStatus("1");
		appointment.setAppointStatus("0,1");
		appointment.setAppointTime(DateUtil.format(closeTime.getCloseDate(), Constants.DATE_FORMAT_YMD));
		if(closeTime.getTime() != 0) appointment.setTime(closeTime.getTime());
		PageHelper ph = new PageHelper();
		ph.setHiddenTotal(true);
		List<FdMemberAppointment> appointments = fdMemberAppointmentService.dataGrid(appointment, ph).getRows();
		if(CollectionUtils.isNotEmpty(appointments)) {
			Calendar c = Calendar.getInstance();
			c.setTime(closeTime.getCloseDate());
			int w = c.get(Calendar.DAY_OF_WEEK);
			w = w == 1 ? 7 : w - 1;
			String time = DateUtil.format(closeTime.getCloseDate(), Constants.DATE_FORMAT_YMD) + EnumConstants.WEEK.getCnName(w);
			if(closeTime.getTime() == 0) time += "全天";
			else if(closeTime.getTime() == 1) time += "上午";
			else if(closeTime.getTime() == 2) time += "下午";
			else if(closeTime.getTime() == 3) time += "夜班";
			for(FdMemberAppointment a : appointments) {

				// 退款
				FdPaymentBase paymentBase = new FdPaymentBase();
				paymentBase.setRefId(a.getId() + "");
				paymentBase.setUserId(a.getUserId());
				paymentBase.setOrderNo(a.getAppointmentNo());
				fdPaymentBaseService.refund(paymentBase, "医生停诊");

				// 医生确认扣除医生余额
				if("1".equals(a.getAppointStatus())) {
					FdBalanceLog balanceLog = new FdBalanceLog();
					balanceLog.setUserId(a.getDoctorId().longValue());
					balanceLog.setRefType("BBT006");
					balanceLog.setRefId(a.getId() + "");
					balanceLog.setAmount(-BigDecimal.valueOf(a.getAmount()).divide(new BigDecimal(100)).floatValue());
					balanceLog.setStatus(false);
					fdBalanceLogService.addLogAndUpdateBalance(balanceLog);
				}

				// 修改预约状态
				a.setAppointStatus("3");
				a.setRefuseReason("医生停诊");
				fdMemberAppointmentService.edit(a);

				FdMember user = fdMemberService.getDetail(a.getUserId());
				FdMember doctor = fdMemberService.getDetail(a.getDoctorId());
				FdMessage message = new FdMessage();
				message.setTitle("预约停诊提醒");

				String content = "尊敬的用户您好，" + time + doctor.getCustomer().getRealName() + "医生发布了停诊，您的预约已被取消，支付钱款已原路退回，给您带来的不便深表歉意！";
				message.setContent(content);
				message.setUserId(a.getUserId());
				message.setMtype("MT02");
				message.setIsRead(false);
				message.setAlias("0-" + user.getMobile());
				fdMessageService.addAndPushMessage(message);
			}
		}
	}

}
