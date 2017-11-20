package com.mobian.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.mobian.absx.F;
import com.mobian.dao.FdBalanceLogDaoI;
import com.mobian.model.TfdBalanceLog;
import com.mobian.pageModel.FdBalanceLog;
import com.mobian.pageModel.DataGrid;
import com.mobian.pageModel.PageHelper;
import com.mobian.service.FdBalanceLogServiceI;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mobian.util.MyBeanUtils;

@Service
public class FdBalanceLogServiceImpl extends BaseServiceImpl<FdBalanceLog> implements FdBalanceLogServiceI {

	@Autowired
	private FdBalanceLogDaoI fdBalanceLogDao;

	@Override
	public DataGrid dataGrid(FdBalanceLog fdBalanceLog, PageHelper ph) {
		List<FdBalanceLog> ol = new ArrayList<FdBalanceLog>();
		String hql = " from TfdBalanceLog t ";
		DataGrid dg = dataGridQuery(hql, ph, fdBalanceLog, fdBalanceLogDao);
		@SuppressWarnings("unchecked")
		List<TfdBalanceLog> l = dg.getRows();
		if (l != null && l.size() > 0) {
			for (TfdBalanceLog t : l) {
				FdBalanceLog o = new FdBalanceLog();
				BeanUtils.copyProperties(t, o);
				ol.add(o);
			}
		}
		dg.setRows(ol);
		return dg;
	}
	

	protected String whereHql(FdBalanceLog fdBalanceLog, Map<String, Object> params) {
		String whereHql = "";	
		if (fdBalanceLog != null) {
			whereHql += " where t.status = 0 ";
			if (!F.empty(fdBalanceLog.getAdminId())) {
				whereHql += " and t.adminId = :adminId";
				params.put("adminId", fdBalanceLog.getAdminId());
			}		
			if (!F.empty(fdBalanceLog.getUserId())) {
				whereHql += " and t.userId = :userId";
				params.put("userId", fdBalanceLog.getUserId());
			}		
			if (!F.empty(fdBalanceLog.getBalanceNo())) {
				whereHql += " and t.balanceNo = :balanceNo";
				params.put("balanceNo", fdBalanceLog.getBalanceNo());
			}		
			if (!F.empty(fdBalanceLog.getRefId())) {
				whereHql += " and t.refId = :refId";
				params.put("refId", fdBalanceLog.getRefId());
			}		
			if (!F.empty(fdBalanceLog.getRefType())) {
				whereHql += " and t.refType = :refType";
				params.put("refType", fdBalanceLog.getRefType());
			}		
			if (!F.empty(fdBalanceLog.getCreateTime())) {
				whereHql += " and t.createTime = :createTime";
				params.put("createTime", fdBalanceLog.getCreateTime());
			}		
			if (!F.empty(fdBalanceLog.getUpdateTime())) {
				whereHql += " and t.updateTime = :updateTime";
				params.put("updateTime", fdBalanceLog.getUpdateTime());
			}
			if (!F.empty(fdBalanceLog.getNote())) {
				whereHql += " and t.note = :note";
				params.put("note", fdBalanceLog.getNote());
			}		
			if (!F.empty(fdBalanceLog.getStatus())) {
				whereHql += " and t.status = :status";
				params.put("status", fdBalanceLog.getStatus());
			}		
		}	
		return whereHql;
	}

	@Override
	public void add(FdBalanceLog fdBalanceLog) {
		TfdBalanceLog t = new TfdBalanceLog();
		BeanUtils.copyProperties(fdBalanceLog, t);
		//t.setId(jb.absx.UUID.uuid());
		fdBalanceLogDao.save(t);
	}

	@Override
	public FdBalanceLog get(Integer id) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		TfdBalanceLog t = fdBalanceLogDao.get("from TfdBalanceLog t  where t.id = :id", params);
		FdBalanceLog o = new FdBalanceLog();
		BeanUtils.copyProperties(t, o);
		return o;
	}

	@Override
	public void edit(FdBalanceLog fdBalanceLog) {
		TfdBalanceLog t = fdBalanceLogDao.get(TfdBalanceLog.class, fdBalanceLog.getId());
		if (t != null) {
			MyBeanUtils.copyProperties(fdBalanceLog, t, new String[] { "id" , "addtime", "isdeleted","updatetime" },true);
		}
	}

	@Override
	public void delete(Integer id) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		fdBalanceLogDao.executeHql("update TfdBalanceLog t set t.isdeleted = 1 where t.id = :id",params);
		//fdBalanceLogDao.delete(fdBalanceLogDao.get(TfdBalanceLog.class, id));
	}

}
