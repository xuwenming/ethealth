package com.mobian.service.impl;

import com.alibaba.fastjson.JSON;
import com.alipay.api.domain.AlipayTradeRefundModel;
import com.alipay.api.request.AlipayTradeRefundRequest;
import com.alipay.api.response.AlipayTradeRefundResponse;
import com.mobian.absx.F;
import com.mobian.dao.FdPaymentBaseDaoI;
import com.mobian.listener.Application;
import com.mobian.model.TfdPaymentBase;
import com.mobian.pageModel.*;
import com.mobian.service.*;
import com.mobian.thirdpart.alipay.AlipayUtil;
import com.mobian.thirdpart.wx.HttpUtil;
import com.mobian.thirdpart.wx.PayCommonUtil;
import com.mobian.thirdpart.wx.WeixinUtil;
import com.mobian.thirdpart.wx.XMLUtil;
import com.mobian.util.MyBeanUtils;
import com.mobian.util.Util;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

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
		t.setId(com.mobian.absx.UUID.uuid());
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
			if(F.empty(payment.getStatus())) payment.setStatus("2");
			if(!F.empty(payment.getType())) paymentQ.setType(payment.getType());
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
			paymentLog.setType(paymentQ.getType());
			fdPaymentLogService.edit(paymentLog);
		}
		Integer userId = null, doctorId = null, refId = null;
		String refType = null;
		if(transformFlag) {
			BigDecimal amount = BigDecimal.ZERO;
			if(payment.getOrderNo().startsWith("Y")) {
				FdMemberAppointment appointment = fdMemberAppointmentService.getByAppointmentNo(payment.getOrderNo());
				if(appointment != null) {
					appointment.setStatus("1");
					fdMemberAppointmentService.updatePaySuccess(appointment);

					userId = appointment.getUserId();
//					doctorId = appointment.getDoctorId(); 医生同意充值医生余额
					refType = "BBT003";
					refId = appointment.getId();
					amount = BigDecimal.valueOf(paymentQ.getPrice()).divide(new BigDecimal(100));
				}

			} else if(payment.getOrderNo().startsWith("Z")) {
				FdMemberConsultationOrder consultationOrder = fdMemberConsultationOrderService.getByOrderNo(payment.getOrderNo());
				if(consultationOrder != null) {
					fdMemberConsultationOrderService.updatePaySuccess(consultationOrder);
					userId = consultationOrder.getUserId();
					doctorId = consultationOrder.getDoctorId();
					refType = "BBT004";
					refId = consultationOrder.getId();
					BigDecimal pre = new BigDecimal(Application.getString("UC004", "100"));
					amount = new BigDecimal(BigDecimal.valueOf(paymentQ.getPrice()).divide(new BigDecimal(100)).toString()).multiply(pre).divide(new BigDecimal(100), 2, BigDecimal.ROUND_HALF_UP);
				}
			}

			// 充值医生余额
			if(doctorId != null) {
				FdBalanceLog balanceLog = new FdBalanceLog();
				balanceLog.setUserId(doctorId.longValue());
				balanceLog.setRefType(refType);
				balanceLog.setRefId(refId.toString());
				balanceLog.setAmount(amount.floatValue());
				balanceLog.setStatus(false);
				balanceLog.setNote("订单实收金额【"+BigDecimal.valueOf(paymentQ.getPrice()).divide(new BigDecimal(100))+"元】");
				fdBalanceLogService.addLogAndUpdateBalance(balanceLog);
			}

			// 余额支付,扣除患者余额
			if("balance".equals(paymentQ.getType()) && userId != null) {
				FdBalanceLog balanceLog = new FdBalanceLog();
				balanceLog.setUserId(userId.longValue());
				balanceLog.setRefType(refType);
				balanceLog.setRefId(refId.toString());
				balanceLog.setAmount(-BigDecimal.valueOf(paymentQ.getPrice()).divide(new BigDecimal(100)).floatValue());
				balanceLog.setStatus(false);
				fdBalanceLogService.addLogAndUpdateBalance(balanceLog);
			}

		}
	}

	@Override
	public void refund(FdPaymentBase paymentBase, String desc) {
		try {
			Integer userId = paymentBase.getUserId(); // 付款人
			String refId = paymentBase.getRefId();
			paymentBase = getByOrderNo(paymentBase.getOrderNo());
			if(paymentBase != null) {
				boolean flag = true;
				String refund_no = Util.CreateNo("R"), refund_id = null;
				if("wechat".equals(paymentBase.getType())) {
					Map<String, Object> params = new HashMap<String, Object>();
					params.put("total_fee", paymentBase.getPrice());
					params.put("refund_fee", paymentBase.getPrice());
					params.put("trade_no", paymentBase.getOrderNo());
					params.put("refund_no", refund_no);
					String requestXml = PayCommonUtil.requestRefundXML(params);
					System.out.println("~~~~~~~~~~~~微信退款接口请求参数requestXml:" + requestXml);
					String result = HttpUtil.httpsRequestSSL(WeixinUtil.REFUND_URL, requestXml);
					System.out.println("~~~~~~~~~~~~微信退款接口返回结果result:" + result);
					Map<String, String> resultMap = XMLUtil.doXMLParse(result);
					if (resultMap == null || F.empty(resultMap.get("result_code")) || !resultMap.get("result_code").toString().equalsIgnoreCase("SUCCESS")) {
						flag = false;
					} else {
						refund_id = resultMap.get("refund_id");
					}
				} else if("alipay".equals(paymentBase.getType())) {
					AlipayTradeRefundRequest request = new AlipayTradeRefundRequest();
					AlipayTradeRefundModel model = new AlipayTradeRefundModel();
					model.setOutTradeNo(paymentBase.getOrderNo());
					model.setRefundAmount(BigDecimal.valueOf(paymentBase.getPrice()).divide(new BigDecimal(100)).toString());
					model.setOutRequestNo(refund_no);
					request.setBizModel(model);
					AlipayTradeRefundResponse response = AlipayUtil.alipayClient.execute(request);
					System.out.println("~~~~~~~~~~支付宝退款结果response:" + JSON.toJSONString(response));
					if(!response.isSuccess()) {
						flag = false;
					}

				} else if("balance".equals(paymentBase.getType())) {
					FdBalanceLog balanceLog = new FdBalanceLog();
					balanceLog.setUserId(userId.longValue());
					balanceLog.setRefType("BBT005");
					balanceLog.setRefId(refId);
					balanceLog.setAmount(BigDecimal.valueOf(paymentBase.getPrice()).divide(new BigDecimal(100)).floatValue());
					balanceLog.setStatus(false);
					balanceLog.setNote(desc);
					fdBalanceLogService.addLogAndUpdateBalance(balanceLog);
				}

				if(flag) {
					FdPaymentLog paymentLog = fdPaymentLogService.getByPaymentId(paymentBase.getId());
					if(paymentLog != null) {
						paymentLog.setRefundNo(refund_no);
						paymentLog.setRefRefundNo(refund_id);
						paymentLog.setRefundFee(paymentBase.getPrice().longValue());
						paymentLog.setRefundDate(new Date());
						fdPaymentLogService.edit(paymentLog);
					}

					// 更新预约退款状态
					FdMemberAppointment appointment = new FdMemberAppointment();
					appointment.setId(Integer.valueOf(refId));
					appointment.setIsRefund(true);
					fdMemberAppointmentService.edit(appointment);
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
