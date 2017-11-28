package com.mobian.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.mobian.absx.F;
import com.mobian.dao.FdPaymentBaseDaoI;
import com.mobian.model.TfdPaymentBase;
import com.mobian.pageModel.*;
import com.mobian.service.*;

import com.mobian.util.Util;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mobian.util.MyBeanUtils;

@Service
public class FdPaymentBaseServiceImpl extends BaseServiceImpl<FdPaymentBase> implements FdPaymentBaseServiceI {

	@Autowired
	private FdPaymentBaseDaoI fdPaymentBaseDao;

	@Autowired
	private FdPaymentLogServiceI fdPaymentLogService;

	@Autowired
	private FdMemberAppointmentServiceI fdMemberAppointmentService;

	@Autowired
	private FdMemberConsultationOrderServiceI fdMemberConsultationOrderService;

	@Autowired
	private FdCustomerServiceI fdCustomerService;

	@Autowired
	private FdBalanceLogServiceI fdBalanceLogService;

	@Override
	public DataGrid dataGrid(FdPaymentBase fdPaymentBase, PageHelper ph) {
		List<FdPaymentBase> ol = new ArrayList<FdPaymentBase>();
		String hql = " from TfdPaymentBase t ";
		DataGrid dg = dataGridQuery(hql, ph, fdPaymentBase, fdPaymentBaseDao);
		@SuppressWarnings("unchecked")
		List<TfdPaymentBase> l = dg.getRows();
		if (l != null && l.size() > 0) {
			for (TfdPaymentBase t : l) {
				FdPaymentBase o = new FdPaymentBase();
				BeanUtils.copyProperties(t, o);
				ol.add(o);
			}
		}
		dg.setRows(ol);
		return dg;
	}
	

	protected String whereHql(FdPaymentBase fdPaymentBase, Map<String, Object> params) {
		String whereHql = "";	
		if (fdPaymentBase != null) {
			whereHql += " where 1=1 ";
			if (!F.empty(fdPaymentBase.getOrderNo())) {
				whereHql += " and t.orderNo = :orderNo";
				params.put("orderNo", fdPaymentBase.getOrderNo());
			}		
			if (!F.empty(fdPaymentBase.getType())) {
				whereHql += " and t.type = :type";
				params.put("type", fdPaymentBase.getType());
			}		
			if (!F.empty(fdPaymentBase.getPrice())) {
				whereHql += " and t.price = :price";
				params.put("price", fdPaymentBase.getPrice());
			}		
			if (!F.empty(fdPaymentBase.getStatus())) {
				whereHql += " and t.status = :status";
				params.put("status", fdPaymentBase.getStatus());
			}		
			if (!F.empty(fdPaymentBase.getRemark())) {
				whereHql += " and t.remark = :remark";
				params.put("remark", fdPaymentBase.getRemark());
			}		
		}	
		return whereHql;
	}

	@Override
	public void add(FdPaymentBase fdPaymentBase) {
		TfdPaymentBase t = new TfdPaymentBase();
		BeanUtils.copyProperties(fdPaymentBase, t);
		t.setCreateDate(new Date());
		t.setUpdateDate(new Date());
		fdPaymentBaseDao.save(t);
		fdPaymentBase.setId(t.getId());
	}

	@Override
	public FdPaymentBase get(Integer id) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		TfdPaymentBase t = fdPaymentBaseDao.get("from TfdPaymentBase t  where t.id = :id", params);
		FdPaymentBase o = new FdPaymentBase();
		BeanUtils.copyProperties(t, o);
		return o;
	}

	@Override
	public void edit(FdPaymentBase fdPaymentBase) {
		TfdPaymentBase t = fdPaymentBaseDao.get(TfdPaymentBase.class, fdPaymentBase.getId());
		if (t != null) {
			MyBeanUtils.copyProperties(fdPaymentBase, t, new String[] { "id" , "addtime", "isdeleted","updatetime" },true);
		}
	}

	@Override
	public void delete(Integer id) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		fdPaymentBaseDao.executeHql("update TfdPaymentBase t set t.isdeleted = 1 where t.id = :id",params);
		//fdPaymentBaseDao.delete(fdPaymentBaseDao.get(TfdPaymentBase.class, id));
	}

	@Override
	public FdPaymentBase getByOrderNo(String orderNo) {
		FdPaymentBase o = null;
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("orderNo", orderNo);
		TfdPaymentBase t = fdPaymentBaseDao.get("from TfdPaymentBase t  where t.orderNo = :orderNo", params);
		if(t != null) {
			o = new FdPaymentBase();
			BeanUtils.copyProperties(t, o);
		}

		return o;
	}

	@Override
	public void addOrUpdate(FdPaymentBase payment) {
		FdPaymentBase paymentQ = getByOrderNo(payment.getOrderNo());
		boolean transformFlag = true;
		if(paymentQ == null) {
			payment.setStatus("2");
			add(payment);
			paymentQ = payment;
		} else {
			if("2".equals(paymentQ.getStatus())) transformFlag = false; // 防止重复支付
			payment.setId(paymentQ.getId());
			payment.setUpdateDate(new Date());
			edit(payment);
		}

		FdPaymentLog paymentLog = fdPaymentLogService.getByPaymentId(paymentQ.getId());
		if(paymentLog == null) {
			paymentLog = new FdPaymentLog();
			paymentLog.setPaymentId(paymentQ.getId());
			paymentLog.setType(paymentQ.getType());
			paymentLog.setStatus(payment.getStatus());
			paymentLog.setTotalFee(paymentQ.getPrice().longValue());
			paymentLog.setRefId(payment.getRefId());
			fdPaymentLogService.add(paymentLog);
		} else {
			paymentLog.setStatus(payment.getStatus());
			paymentLog.setRefId(payment.getRefId());
			fdPaymentLogService.edit(paymentLog);
		}
		Integer userId = null, doctorId = null, refId = null;
		String refType = null;
		if(transformFlag) {
			if(payment.getOrderNo().startsWith("Y")) {
				FdMemberAppointment appointment = fdMemberAppointmentService.getByAppointmentNo(payment.getOrderNo());
				if(appointment != null) {
					appointment.setStatus("1");
					fdMemberAppointmentService.edit(appointment);

					userId = appointment.getUserId();
					doctorId = appointment.getDoctorId();
					refType = "BBT003";
					refId = appointment.getId();
				}

			} else if(payment.getOrderNo().startsWith("Z")) {
				FdMemberConsultationOrder consultationOrder = fdMemberConsultationOrderService.getByOrderNo(payment.getOrderNo());
				if(consultationOrder != null) {
					fdMemberConsultationOrderService.updatePaySuccess(consultationOrder);
					userId = consultationOrder.getUserId();
					doctorId = consultationOrder.getDoctorId();
					refType = "BBT004";
					refId = consultationOrder.getId();
				}
			}

			// 充值医生余额
			if(doctorId != null) {
				FdBalanceLog balanceLog = new FdBalanceLog();
				balanceLog.setUserId(doctorId.longValue());
				balanceLog.setRefType(refType);
				balanceLog.setRefId(refId.toString());
				balanceLog.setAmount(BigDecimal.valueOf(payment.getPrice()).divide(new BigDecimal(100)).floatValue());
				fdBalanceLogService.updateLogAndBalance(balanceLog);
			}

			// 余额支付,扣除患者余额
			if("balance".equals(paymentQ.getType()) && userId != null) {
				FdBalanceLog balanceLog = new FdBalanceLog();
				balanceLog.setUserId(userId.longValue());
				balanceLog.setRefType(refType);
				balanceLog.setRefId(refId.toString());
				balanceLog.setAmount(-BigDecimal.valueOf(payment.getPrice()).divide(new BigDecimal(100)).floatValue());
				fdBalanceLogService.updateLogAndBalance(balanceLog);
			}

		}
	}

}
