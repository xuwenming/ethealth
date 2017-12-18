package com.mobian.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.mobian.absx.F;
import com.mobian.dao.FdCashLogDaoI;
import com.mobian.model.TfdCashLog;
import com.mobian.pageModel.FdCashLog;
import com.mobian.pageModel.DataGrid;
import com.mobian.pageModel.PageHelper;
import com.mobian.service.FdCashLogServiceI;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mobian.util.MyBeanUtils;

@Service
public class FdCashLogServiceImpl extends BaseServiceImpl<FdCashLog> implements FdCashLogServiceI {

	@Autowired
	private FdCashLogDaoI fdCashLogDao;

	@Override
	public DataGrid dataGrid(FdCashLog fdCashLog, PageHelper ph) {
		List<FdCashLog> ol = new ArrayList<FdCashLog>();
		String hql = " from TfdCashLog t ";
		DataGrid dg = dataGridQuery(hql, ph, fdCashLog, fdCashLogDao);
		@SuppressWarnings("unchecked")
		List<TfdCashLog> l = dg.getRows();
		if (l != null && l.size() > 0) {
			for (TfdCashLog t : l) {
				FdCashLog o = new FdCashLog();
				BeanUtils.copyProperties(t, o);
				ol.add(o);
			}
		}
		dg.setRows(ol);
		return dg;
	}
	

	protected String whereHql(FdCashLog fdCashLog, Map<String, Object> params) {
		String whereHql = "";	
		if (fdCashLog != null) {
			whereHql += " where t.status = 0 ";
			if (!F.empty(fdCashLog.getStatus())) {
				whereHql += " and t.status = :status";
				params.put("status", fdCashLog.getStatus());
			}		
			if (!F.empty(fdCashLog.getCreateTime())) {
				whereHql += " and t.createTime = :createTime";
				params.put("createTime", fdCashLog.getCreateTime());
			}		
			if (!F.empty(fdCashLog.getUpdateTime())) {
				whereHql += " and t.updateTime = :updateTime";
				params.put("updateTime", fdCashLog.getUpdateTime());
			}		
			if (!F.empty(fdCashLog.getHandleStatus())) {
				whereHql += " and t.handleStatus = :handleStatus";
				params.put("handleStatus", fdCashLog.getHandleStatus());
			}		
			if (!F.empty(fdCashLog.getHandleLoginId())) {
				whereHql += " and t.handleLoginId = :handleLoginId";
				params.put("handleLoginId", fdCashLog.getHandleLoginId());
			}		
			if (!F.empty(fdCashLog.getHandleRemark())) {
				whereHql += " and t.handleRemark = :handleRemark";
				params.put("handleRemark", fdCashLog.getHandleRemark());
			}		
			if (!F.empty(fdCashLog.getUserId())) {
				whereHql += " and t.userId = :userId";
				params.put("userId", fdCashLog.getUserId());
			}		
			if (!F.empty(fdCashLog.getAmount())) {
				whereHql += " and t.amount = :amount";
				params.put("amount", fdCashLog.getAmount());
			}		
			if (!F.empty(fdCashLog.getRefType())) {
				whereHql += " and t.refType = :refType";
				params.put("refType", fdCashLog.getRefType());
			}		
			if (!F.empty(fdCashLog.getContent())) {
				whereHql += " and t.content = :content";
				params.put("content", fdCashLog.getContent());
			}		
			if (!F.empty(fdCashLog.getBankAccount())) {
				whereHql += " and t.bankAccount = :bankAccount";
				params.put("bankAccount", fdCashLog.getBankAccount());
			}		
			if (!F.empty(fdCashLog.getBankPhone())) {
				whereHql += " and t.bankPhone = :bankPhone";
				params.put("bankPhone", fdCashLog.getBankPhone());
			}		
			if (!F.empty(fdCashLog.getBankIdNo())) {
				whereHql += " and t.bankIdNo = :bankIdNo";
				params.put("bankIdNo", fdCashLog.getBankIdNo());
			}		
			if (!F.empty(fdCashLog.getBankName())) {
				whereHql += " and t.bankName = :bankName";
				params.put("bankName", fdCashLog.getBankName());
			}		
			if (!F.empty(fdCashLog.getBankCard())) {
				whereHql += " and t.bankCard = :bankCard";
				params.put("bankCard", fdCashLog.getBankCard());
			}		
			if (!F.empty(fdCashLog.getAlipay())) {
				whereHql += " and t.alipay = :alipay";
				params.put("alipay", fdCashLog.getAlipay());
			}		
		}	
		return whereHql;
	}

	@Override
	public void add(FdCashLog fdCashLog) {
		TfdCashLog t = new TfdCashLog();
		BeanUtils.copyProperties(fdCashLog, t);
		//t.setId(jb.absx.UUID.uuid());
		fdCashLogDao.save(t);
	}

	@Override
	public FdCashLog get(Integer id) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		TfdCashLog t = fdCashLogDao.get("from TfdCashLog t  where t.id = :id", params);
		FdCashLog o = new FdCashLog();
		BeanUtils.copyProperties(t, o);
		return o;
	}

	@Override
	public void edit(FdCashLog fdCashLog) {
		TfdCashLog t = fdCashLogDao.get(TfdCashLog.class, fdCashLog.getId());
		if (t != null) {
			MyBeanUtils.copyProperties(fdCashLog, t, new String[] { "id" , "addtime", "isdeleted","updatetime" },true);
		}
	}

	@Override
	public void delete(Integer id) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		fdCashLogDao.executeHql("update TfdCashLog t set t.isdeleted = 1 where t.id = :id",params);
		//fdCashLogDao.delete(fdCashLogDao.get(TfdCashLog.class, id));
	}

}
