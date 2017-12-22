package com.mobian.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.mobian.absx.F;
import com.mobian.dao.FdMessageReadLogDaoI;
import com.mobian.model.TfdMessageReadLog;
import com.mobian.pageModel.FdMessageReadLog;
import com.mobian.pageModel.DataGrid;
import com.mobian.pageModel.PageHelper;
import com.mobian.service.FdMessageReadLogServiceI;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mobian.util.MyBeanUtils;

@Service
public class FdMessageReadLogServiceImpl extends BaseServiceImpl<FdMessageReadLog> implements FdMessageReadLogServiceI {

	@Autowired
	private FdMessageReadLogDaoI fdMessageReadLogDao;

	@Override
	public DataGrid dataGrid(FdMessageReadLog fdMessageReadLog, PageHelper ph) {
		List<FdMessageReadLog> ol = new ArrayList<FdMessageReadLog>();
		String hql = " from TfdMessageReadLog t ";
		DataGrid dg = dataGridQuery(hql, ph, fdMessageReadLog, fdMessageReadLogDao);
		@SuppressWarnings("unchecked")
		List<TfdMessageReadLog> l = dg.getRows();
		if (l != null && l.size() > 0) {
			for (TfdMessageReadLog t : l) {
				FdMessageReadLog o = new FdMessageReadLog();
				BeanUtils.copyProperties(t, o);
				ol.add(o);
			}
		}
		dg.setRows(ol);
		return dg;
	}
	

	protected String whereHql(FdMessageReadLog fdMessageReadLog, Map<String, Object> params) {
		String whereHql = "";	
		if (fdMessageReadLog != null) {
			whereHql += " where t.status = 0 ";
			if (!F.empty(fdMessageReadLog.getCreateTime())) {
				whereHql += " and t.createTime = :createTime";
				params.put("createTime", fdMessageReadLog.getCreateTime());
			}		
			if (!F.empty(fdMessageReadLog.getUpdateTime())) {
				whereHql += " and t.updateTime = :updateTime";
				params.put("updateTime", fdMessageReadLog.getUpdateTime());
			}		
			if (!F.empty(fdMessageReadLog.getStatus())) {
				whereHql += " and t.status = :status";
				params.put("status", fdMessageReadLog.getStatus());
			}		
			if (!F.empty(fdMessageReadLog.getUserId())) {
				whereHql += " and t.userId = :userId";
				params.put("userId", fdMessageReadLog.getUserId());
			}		
			if (!F.empty(fdMessageReadLog.getMessageId())) {
				whereHql += " and t.messageId = :messageId";
				params.put("messageId", fdMessageReadLog.getMessageId());
			}		
		}	
		return whereHql;
	}

	@Override
	public void add(FdMessageReadLog fdMessageReadLog) {
		TfdMessageReadLog t = new TfdMessageReadLog();
		BeanUtils.copyProperties(fdMessageReadLog, t);
		//t.setId(jb.absx.UUID.uuid());
		fdMessageReadLogDao.save(t);
	}

	@Override
	public FdMessageReadLog get(Integer id) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		TfdMessageReadLog t = fdMessageReadLogDao.get("from TfdMessageReadLog t  where t.id = :id", params);
		FdMessageReadLog o = new FdMessageReadLog();
		BeanUtils.copyProperties(t, o);
		return o;
	}

	@Override
	public void edit(FdMessageReadLog fdMessageReadLog) {
		TfdMessageReadLog t = fdMessageReadLogDao.get(TfdMessageReadLog.class, fdMessageReadLog.getId());
		if (t != null) {
			MyBeanUtils.copyProperties(fdMessageReadLog, t, new String[] { "id" , "addtime", "isdeleted","updatetime" },true);
		}
	}

	@Override
	public void delete(Integer id) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		fdMessageReadLogDao.executeHql("update TfdMessageReadLog t set t.isdeleted = 1 where t.id = :id",params);
		//fdMessageReadLogDao.delete(fdMessageReadLogDao.get(TfdMessageReadLog.class, id));
	}

	@Override
	public FdMessageReadLog get(Integer messageId, Integer userId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("messageId", messageId);
		params.put("userId", userId);
		TfdMessageReadLog t = fdMessageReadLogDao.get("from TfdMessageReadLog t  where t.messageId = :messageId and t.userId = :userId", params);
		if(t == null) return null;
		FdMessageReadLog o = new FdMessageReadLog();
		BeanUtils.copyProperties(t, o);
		return o;
	}

}
