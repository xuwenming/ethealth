package com.mobian.service.impl;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;

import com.mobian.absx.F;
import com.mobian.dao.FdWithdrawLogDaoI;
import com.mobian.exception.ServiceException;
import com.mobian.model.TfdWithdrawLog;
import com.mobian.pageModel.*;
import com.mobian.service.*;

import com.mobian.thirdpart.wx.HttpUtil;
import com.mobian.thirdpart.wx.PayCommonUtil;
import com.mobian.thirdpart.wx.WeixinUtil;
import com.mobian.thirdpart.wx.XMLUtil;
import com.mobian.util.Constants;
import com.mobian.util.DateUtil;
import com.mobian.util.IpUtil;
import org.apache.commons.collections.CollectionUtils;
import org.jdom.JDOMException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mobian.util.MyBeanUtils;

import javax.servlet.http.HttpServletRequest;

@Service
public class FdWithdrawLogServiceImpl extends BaseServiceImpl<FdWithdrawLog> implements FdWithdrawLogServiceI {

	@Autowired
	private FdWithdrawLogDaoI fdWithdrawLogDao;

	@Autowired
	private FdCustomerServiceI fdCustomerService;

	@Autowired
	private FdBalanceLogServiceI fdBalanceLogService;

	@Autowired
	private FdMemberServiceI fdMemberService;

	@Autowired
	private FdMessageServiceI fdMessageService;

	@Override
	public DataGrid dataGrid(FdWithdrawLog fdWithdrawLog, PageHelper ph) {
		List<FdWithdrawLog> ol = new ArrayList<FdWithdrawLog>();
		String hql = " from TfdWithdrawLog t ";
		DataGrid dg = dataGridQuery(hql, ph, fdWithdrawLog, fdWithdrawLogDao);
		@SuppressWarnings("unchecked")
		List<TfdWithdrawLog> l = dg.getRows();
		if (l != null && l.size() > 0) {
			for (TfdWithdrawLog t : l) {
				FdWithdrawLog o = new FdWithdrawLog();
				BeanUtils.copyProperties(t, o);
				ol.add(o);
			}
		}
		dg.setRows(ol);
		return dg;
	}
	

	protected String whereHql(FdWithdrawLog fdWithdrawLog, Map<String, Object> params) {
		String whereHql = "";	
		if (fdWithdrawLog != null) {
			whereHql += " where t.status = 0 ";
			if (!F.empty(fdWithdrawLog.getCreateTime())) {
				whereHql += " and to_days(str_to_date(from_unixtime(t.createTime/1000, '%Y-%m-%d'), '%Y-%m-%d')) = to_days(now())";
			}
			if (!F.empty(fdWithdrawLog.getCreateTimeStart())) {
				whereHql += " and t.createTime >= :createTimeStart";
				params.put("createTimeStart", fdWithdrawLog.getCreateTimeStart());
			}
			if (!F.empty(fdWithdrawLog.getCreateTimeEnd())) {
				whereHql += " and t.createTime <= :createTimeEnd";
				params.put("createTimeEnd", fdWithdrawLog.getCreateTimeEnd());
			}
			if (!F.empty(fdWithdrawLog.getUpdateTime())) {
				whereHql += " and t.updateTime = :updateTime";
				params.put("updateTime", fdWithdrawLog.getUpdateTime());
			}		
			if (!F.empty(fdWithdrawLog.getStatus())) {
				whereHql += " and t.status = :status";
				params.put("status", fdWithdrawLog.getStatus());
			}		
			if (!F.empty(fdWithdrawLog.getWithdrawNo())) {
				whereHql += " and t.withdrawNo like :withdrawNo";
				params.put("withdrawNo", "%" + fdWithdrawLog.getWithdrawNo() + "%");
			}		
			if (!F.empty(fdWithdrawLog.getAmount())) {
				whereHql += " and t.amount = :amount";
				params.put("amount", fdWithdrawLog.getAmount());
			}		
			if (!F.empty(fdWithdrawLog.getUserId())) {
				whereHql += " and t.userId = :userId";
				params.put("userId", fdWithdrawLog.getUserId());
			}		
			if (!F.empty(fdWithdrawLog.getContent())) {
				whereHql += " and t.content = :content";
				params.put("content", fdWithdrawLog.getContent());
			}		
			if (!F.empty(fdWithdrawLog.getBankAccount())) {
				whereHql += " and t.bankAccount = :bankAccount";
				params.put("bankAccount", fdWithdrawLog.getBankAccount());
			}		
			if (!F.empty(fdWithdrawLog.getBankCode())) {
				whereHql += " and t.bankCode = :bankCode";
				params.put("bankCode", fdWithdrawLog.getBankCode());
			}		
			if (!F.empty(fdWithdrawLog.getBankName())) {
				whereHql += " and t.bankName = :bankName";
				params.put("bankName", fdWithdrawLog.getBankName());
			}		
			if (!F.empty(fdWithdrawLog.getBankCard())) {
				whereHql += " and t.bankCard = :bankCard";
				params.put("bankCard", fdWithdrawLog.getBankCard());
			}		
			if (!F.empty(fdWithdrawLog.getHandleStatus())) {
				whereHql += " and t.handleStatus in :handleStatus";
				params.put("handleStatus", fdWithdrawLog.getHandleStatus().split(","));
			}		
			if (!F.empty(fdWithdrawLog.getHandleLoginId())) {
				whereHql += " and t.handleLoginId = :handleLoginId";
				params.put("handleLoginId", fdWithdrawLog.getHandleLoginId());
			}		
			if (!F.empty(fdWithdrawLog.getHandleRemark())) {
				whereHql += " and t.handleRemark = :handleRemark";
				params.put("handleRemark", fdWithdrawLog.getHandleRemark());
			}		
			if (!F.empty(fdWithdrawLog.getPaymentNo())) {
				whereHql += " and t.paymentNo = :paymentNo";
				params.put("paymentNo", fdWithdrawLog.getPaymentNo());
			}		
			if (!F.empty(fdWithdrawLog.getRefType())) {
				whereHql += " and t.refType = :refType";
				params.put("refType", fdWithdrawLog.getRefType());
			}		
			if (!F.empty(fdWithdrawLog.getApplyLoginIp())) {
				whereHql += " and t.applyLoginIp = :applyLoginIp";
				params.put("applyLoginIp", fdWithdrawLog.getApplyLoginIp());
			}		
		}	
		return whereHql;
	}

	@Override
	public void add(FdWithdrawLog fdWithdrawLog) {
		TfdWithdrawLog t = new TfdWithdrawLog();
		BeanUtils.copyProperties(fdWithdrawLog, t);
		fdWithdrawLogDao.save(t);
		fdWithdrawLog.setId(t.getId());
	}

	@Override
	public FdWithdrawLog get(Integer id) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		TfdWithdrawLog t = fdWithdrawLogDao.get("from TfdWithdrawLog t  where t.id = :id", params);
		FdWithdrawLog o = new FdWithdrawLog();
		BeanUtils.copyProperties(t, o);
		return o;
	}

	@Override
	public void edit(FdWithdrawLog fdWithdrawLog) {
		TfdWithdrawLog t = fdWithdrawLogDao.get(TfdWithdrawLog.class, fdWithdrawLog.getId());
		if (t != null) {
			MyBeanUtils.copyProperties(fdWithdrawLog, t, new String[] { "id" , "addtime", "isdeleted","updatetime" },true);
		}
	}

	@Override
	public void delete(Integer id) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		fdWithdrawLogDao.executeHql("update TfdWithdrawLog t set t.isdeleted = 1 where t.id = :id",params);
		//fdWithdrawLogDao.delete(fdWithdrawLogDao.get(TfdWithdrawLog.class, id));
	}

	@Override
	public List<FdWithdrawLog> query(FdWithdrawLog log) {
		List<FdWithdrawLog> ol = new ArrayList<FdWithdrawLog>();
		String hql = " from TfdWithdrawLog t ";
		@SuppressWarnings("unchecked")
		List<TfdWithdrawLog> l = query(hql, log, fdWithdrawLogDao, "createTime", "desc");
		if (l != null && l.size() > 0) {
			for (TfdWithdrawLog t : l) {
				FdWithdrawLog o = new FdWithdrawLog();
				BeanUtils.copyProperties(t, o);
				ol.add(o);
			}
		}
		return ol;
	}

	@Override
	public FdWithdrawLog get(FdWithdrawLog log) {
		String hql = " from TfdWithdrawLog t ";
		@SuppressWarnings("unchecked")
		List<TfdWithdrawLog> l = query(hql, log, fdWithdrawLogDao, "createTime", "desc");
		FdWithdrawLog o = null;
		if (CollectionUtils.isNotEmpty(l)) {
			o = new FdWithdrawLog();
			BeanUtils.copyProperties(l.get(0), o);
		}
		return o;
	}

	@Override
	public void editAudit(FdWithdrawLog fdWithdrawLog, String loginId, HttpServletRequest request) {
		FdWithdrawLog withdrawLog = get(fdWithdrawLog.getId());

		//通过
		if ("HS02".equals(fdWithdrawLog.getHandleStatus())) {
			//1.  判断是否合规
//			FdCustomer customer = fdCustomerService.get(Long.valueOf(withdrawLog.getUserId()));
//			if (new BigDecimal(customer.getBalance().toString()).multiply(new BigDecimal(100)).intValue() < withdrawLog.getAmount()) throw new ServiceException("余额不足");
			//2. 参数填充
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("amount", withdrawLog.getAmount());
			params.put("partner_trade_no", withdrawLog.getWithdrawNo());
			params.put("enc_bank_no", withdrawLog.getBankCard());
			params.put("enc_true_name", withdrawLog.getBankAccount());
			params.put("bank_code", withdrawLog.getBankCode().replace("BC", ""));

			try {
				//3. 扣款
				String requestXml = PayCommonUtil.requestTransfersXML(params);
				System.out.println("~~~~~~~~~~~~微信企业付款接口请求参数requestXml:" + requestXml);
				String result = HttpUtil.httpsRequestSSL(WeixinUtil.PAY_BANK_URL, requestXml);
				System.out.println("~~~~~~~~~~~~微信企业付款接口返回结果result:" + result);

				Map<String, String> resultMap = XMLUtil.doXMLParse(result);

				if (!F.empty(resultMap.get("result_code")) && resultMap.get("result_code").toString().equalsIgnoreCase("SUCCESS")) {
					//4. 编辑提现申请记录
					fdWithdrawLog.setHandleLoginId(loginId);
					fdWithdrawLog.setHandleTime(new Date());
					fdWithdrawLog.setPaymentNo(resultMap.get("payment_no").toString());
					if(resultMap.get("cmms_amt") != null)
						fdWithdrawLog.setCmmsAmt(Integer.valueOf(resultMap.get("cmms_amt")));
					edit(fdWithdrawLog);

					// 添加用户消息
					FdMember user = fdMemberService.get(Integer.valueOf(withdrawLog.getUserId()));
					FdMessage message = new FdMessage();
					message.setTitle("提现审核通过");
					Calendar c = Calendar.getInstance();
					c.setTimeInMillis(withdrawLog.getCreateTime());
					String content = "尊敬的用户您好，您的提现申请已审核通过!" +
							"\n申请时间：" + DateUtil.format(c.getTime(), Constants.DATE_FORMAT) +
							"\n提现单号：" + withdrawLog.getWithdrawNo() +
							"\n提现金额：" + BigDecimal.valueOf(withdrawLog.getAmount()).divide(new BigDecimal(100)) + "元" +
							"\n手续费：" + BigDecimal.valueOf(withdrawLog.getServiceAmt()).divide(new BigDecimal(100)) + "元" +
							"\n实际到账：" + BigDecimal.valueOf(withdrawLog.getAmount()).divide(new BigDecimal(100)) + "元" +
							"\n银行：" + withdrawLog.getBankCodeZh() +
							"\n开户行支行：" + withdrawLog.getBankName() +
							"\n银行卡号：" + withdrawLog.getBankCard() +
							"\n开户人姓名：" + withdrawLog.getBankAccount() +
							"\n\n银行到账存在延时，有任何疑问可通过客户端直接联系我们，谢谢！";
					message.setContent(content);
					message.setUserId(Integer.valueOf(withdrawLog.getUserId()));
					message.setMtype("MT02");
					message.setIsRead(false);
					message.setAlias("0_" + user.getMobile());
					message.setPushMessage(new PushMessage("M401", "您在医家盟申请的提现已审核通过!"));
					fdMessageService.addAndPushMessage(message);
				} else {
					fdWithdrawLog.setHandleStatus("HS01");
					fdWithdrawLog.setHandleLoginId(loginId);
					fdWithdrawLog.setHandleRemark("提现失败" + resultMap.get("err_code_des"));
					edit(fdWithdrawLog);
				}
			} catch (Exception e) {
				fdWithdrawLog.setHandleStatus("HS01");
				fdWithdrawLog.setHandleLoginId(loginId);
				fdWithdrawLog.setHandleRemark("提现失败--接口异常");
				edit(fdWithdrawLog);
			}
		}
		// 拒绝
		else if ("HS03".equals(fdWithdrawLog.getHandleStatus())) {

//			if("HS02".equals(withdrawLog.getHandleStatus())) {
//				Map<String, String> map = queryStatus(withdrawLog.getWithdrawNo());
//				if("PROCESSING".equals(map.get("status"))) {
//					throw new ServiceException("提现正在处理中，无法拒绝！");
//				} else if("SUCCESS".equals(map.get("status"))) {
//					throw new ServiceException("提现已处理成功，无法拒绝！");
//				}
//			}

			fdWithdrawLog.setHandleLoginId(loginId);
			fdWithdrawLog.setHandleTime(new Date());
			edit(fdWithdrawLog);

			// 余额退回
			FdBalanceLog balanceLog = new FdBalanceLog();
			balanceLog.setUserId(Long.valueOf(withdrawLog.getUserId()));
			balanceLog.setRefType("BBT009");
			balanceLog.setRefId(withdrawLog.getId() + "");
			balanceLog.setAmount(BigDecimal.valueOf(withdrawLog.getAmount() + withdrawLog.getServiceAmt()).divide(new BigDecimal(100)).floatValue());
			balanceLog.setStatus(false);
			balanceLog.setNote("提现失败，余额退回");
			fdBalanceLogService.addLogAndUpdateBalance(balanceLog);

			// 添加用户消息
			FdMember user = fdMemberService.get(Integer.valueOf(withdrawLog.getUserId()));
			FdMessage message = new FdMessage();
			message.setTitle("提现审核拒绝");
			Calendar c = Calendar.getInstance();
			c.setTimeInMillis(withdrawLog.getCreateTime());
			String content = "尊敬的用户您好，您的提现申请审核不通过!" +
					"\n申请时间：" + DateUtil.format(c.getTime(), Constants.DATE_FORMAT) +
					"\n提现单号：" + withdrawLog.getWithdrawNo() +
					"\n提现金额：" + BigDecimal.valueOf(withdrawLog.getAmount()).divide(new BigDecimal(100)) + "元" +
					"\n手续费：" + BigDecimal.valueOf(withdrawLog.getServiceAmt()).divide(new BigDecimal(100)) + "元" +
					"\n实际退额：" + BigDecimal.valueOf(withdrawLog.getAmount() + withdrawLog.getServiceAmt()).divide(new BigDecimal(100)) + "元" +
					"\n银行：" + withdrawLog.getBankCodeZh() +
					"\n开户行支行：" + withdrawLog.getBankName() +
					"\n银行卡号：" + withdrawLog.getBankCard() +
					"\n开户人姓名：" + withdrawLog.getBankAccount() +
					"\n原因：" + fdWithdrawLog.getHandleRemark() +
					"\n\n钱款已退回余额，有任何疑问可通过客户端直接联系我们，谢谢！";
			message.setContent(content);
			message.setUserId(Integer.valueOf(withdrawLog.getUserId()));
			message.setMtype("MT02");
			message.setIsRead(false);
			message.setAlias("0_" + user.getMobile());
			message.setPushMessage(new PushMessage("M401", "您在医家盟申请的提现审核不通过!"));
			fdMessageService.addAndPushMessage(message);
		}
	}

	@Override
	public void addAndBalance(FdWithdrawLog withdrawLog) {
		add(withdrawLog);

		// 扣除钱包余额
		FdBalanceLog balanceLog = new FdBalanceLog();
		balanceLog.setUserId(Long.valueOf(withdrawLog.getUserId()));
		balanceLog.setRefType(withdrawLog.getRefType());
		balanceLog.setRefId(withdrawLog.getId() + "");

		int amount = withdrawLog.getAmount();
		if(withdrawLog.getServiceAmt() != null) {
			amount += withdrawLog.getServiceAmt();
		}
		balanceLog.setAmount(-BigDecimal.valueOf(amount).divide(new BigDecimal(100)).floatValue());
		balanceLog.setStatus(false);
		balanceLog.setNote("提现扣款");
		fdBalanceLogService.addLogAndUpdateBalance(balanceLog);
	}

	@Override
	public Map<String, String> queryStatus(String withdrawNo) {
		Map<String, String> result = new HashMap<String, String>();

		try {
			String requestXml = PayCommonUtil.requestQueryBankXML(withdrawNo);
			Map<String, String> resultMap = XMLUtil.doXMLParse(HttpUtil.httpsRequestSSL(WeixinUtil.QUERY_BANK_URL, requestXml));
			if (!F.empty(resultMap.get("result_code")) && resultMap.get("result_code").toString().equalsIgnoreCase("SUCCESS")) {
				result.put("status", resultMap.get("status"));
				result.put("reason", resultMap.get("reason"));
				return result;
			}

		} catch (JDOMException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

}
