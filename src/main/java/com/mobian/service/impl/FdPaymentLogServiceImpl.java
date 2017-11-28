package com.mobian.service.impl;

import com.mobian.absx.F;
import com.mobian.dao.FdPaymentLogDaoI;
import com.mobian.model.TfdPaymentLog;
import com.mobian.pageModel.DataGrid;
import com.mobian.pageModel.FdPaymentLog;
import com.mobian.pageModel.PageHelper;
import com.mobian.service.FdPaymentLogServiceI;
import com.mobian.util.MyBeanUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class FdPaymentLogServiceImpl extends BaseServiceImpl<FdPaymentLog> implements FdPaymentLogServiceI {

	@Autowired
	private FdPaymentLogDaoI fdPaymentLogDao;

	@Override
	public DataGrid dataGrid(FdPaymentLog fdPaymentLog, PageHelper ph) {
		List<FdPaymentLog> ol = new ArrayList<FdPaymentLog>();
		String hql = " from TfdPaymentLog t ";
		DataGrid dg = dataGridQuery(hql, ph, fdPaymentLog, fdPaymentLogDao);
		@SuppressWarnings("unchecked")
		List<TfdPaymentLog> l = dg.getRows();
		if (l != null && l.size() > 0) {
			for (TfdPaymentLog t : l) {
				FdPaymentLog o = new FdPaymentLog();
				BeanUtils.copyProperties(t, o);
				ol.add(o);
			}
		}
		dg.setRows(ol);
		return dg;
	}
	

	protected String whereHql(FdPaymentLog fdPaymentLog, Map<String, Object> params) {
		String whereHql = "";	
		if (fdPaymentLog != null) {
			whereHql += " where 1=1 ";
			if (!F.empty(fdPaymentLog.getPaymentId())) {
				whereHql += " and t.paymentId = :paymentId";
				params.put("paymentId", fdPaymentLog.getPaymentId());
			}		
			if (!F.empty(fdPaymentLog.getType())) {
				whereHql += " and t.type = :type";
				params.put("type", fdPaymentLog.getType());
			}		
			if (!F.empty(fdPaymentLog.getStatus())) {
				whereHql += " and t.status = :status";
				params.put("status", fdPaymentLog.getStatus());
			}		
			if (!F.empty(fdPaymentLog.getTotalFee())) {
				whereHql += " and t.totalFee = :totalFee";
				params.put("totalFee", fdPaymentLog.getTotalFee());
			}		
			if (!F.empty(fdPaymentLog.getRefId())) {
				whereHql += " and t.refId = :refId";
				params.put("refId", fdPaymentLog.getRefId());
			}		
			if (!F.empty(fdPaymentLog.getRefundNo())) {
				whereHql += " and t.refundNo = :refundNo";
				params.put("refundNo", fdPaymentLog.getRefundNo());
			}		
			if (!F.empty(fdPaymentLog.getRefRefundNo())) {
				whereHql += " and t.refRefundNo = :refRefundNo";
				params.put("refRefundNo", fdPaymentLog.getRefRefundNo());
			}		
			if (!F.empty(fdPaymentLog.getRefundFee())) {
				whereHql += " and t.refundFee = :refundFee";
				params.put("refundFee", fdPaymentLog.getRefundFee());
			}		
			if (!F.empty(fdPaymentLog.getRemark())) {
				whereHql += " and t.remark = :remark";
				params.put("remark", fdPaymentLog.getRemark());
			}		
		}	
		return whereHql;
	}

	@Override
	public void add(FdPaymentLog fdPaymentLog) {
		TfdPaymentLog t = new TfdPaymentLog();
		BeanUtils.copyProperties(fdPaymentLog, t);
		t.setId(com.mobian.absx.UUID.uuid());
		t.setCreateDate(new Date());
		fdPaymentLogDao.save(t);
	}

	@Override
	public FdPaymentLog get(Integer id) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		TfdPaymentLog t = fdPaymentLogDao.get("from TfdPaymentLog t  where t.id = :id", params);
		FdPaymentLog o = new FdPaymentLog();
		BeanUtils.copyProperties(t, o);
		return o;
	}

	@Override
	public void edit(FdPaymentLog fdPaymentLog) {
		TfdPaymentLog t = fdPaymentLogDao.get(TfdPaymentLog.class, fdPaymentLog.getId());
		if (t != null) {
			MyBeanUtils.copyProperties(fdPaymentLog, t, new String[] { "id" , "addtime", "isdeleted","updatetime" },true);
		}
	}

	@Override
	public void delete(Integer id) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		fdPaymentLogDao.executeHql("update TfdPaymentLog t set t.isdeleted = 1 where t.id = :id",params);
		//fdPaymentLogDao.delete(fdPaymentLogDao.get(TfdPaymentLog.class, id));
	}

	@Override
	public FdPaymentLog getByPaymentId(String paymentId) {
		FdPaymentLog o = null;
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("paymentId", paymentId);
		TfdPaymentLog t = fdPaymentLogDao.get("from TfdPaymentLog t  where t.paymentId = :paymentId", params);
		if(t != null) {
			o = new FdPaymentLog();
			BeanUtils.copyProperties(t, o);
		}
		return o;
	}

}
