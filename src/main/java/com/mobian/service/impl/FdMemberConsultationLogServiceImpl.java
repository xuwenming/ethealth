package com.mobian.service.impl;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.mobian.absx.F;
import com.mobian.dao.FdMemberConsultationLogDaoI;
import com.mobian.model.TfdMemberConsultationLog;
import com.mobian.pageModel.FdMemberConsultationLog;
import com.mobian.pageModel.DataGrid;
import com.mobian.pageModel.PageHelper;
import com.mobian.service.FdMemberConsultationLogServiceI;

import com.mobian.util.Constants;
import com.mobian.util.DateUtil;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mobian.util.MyBeanUtils;

@Service
public class FdMemberConsultationLogServiceImpl extends BaseServiceImpl<FdMemberConsultationLog> implements FdMemberConsultationLogServiceI {

	@Autowired
	private FdMemberConsultationLogDaoI fdMemberConsultationLogDao;

	@Override
	public DataGrid dataGrid(FdMemberConsultationLog fdMemberConsultationLog, PageHelper ph) {
		List<FdMemberConsultationLog> ol = new ArrayList<FdMemberConsultationLog>();
		String hql = " from TfdMemberConsultationLog t ";
		DataGrid dg = dataGridQuery(hql, ph, fdMemberConsultationLog, fdMemberConsultationLogDao);
		@SuppressWarnings("unchecked")
		List<TfdMemberConsultationLog> l = dg.getRows();
		if (l != null && l.size() > 0) {
			for (TfdMemberConsultationLog t : l) {
				FdMemberConsultationLog o = new FdMemberConsultationLog();
				BeanUtils.copyProperties(t, o);
				ol.add(o);
			}
		}
		dg.setRows(ol);
		return dg;
	}
	

	protected String whereHql(FdMemberConsultationLog fdMemberConsultationLog, Map<String, Object> params) {
		String whereHql = "";	
		if (fdMemberConsultationLog != null) {
			whereHql += " where t.status = 0 ";
			if (!F.empty(fdMemberConsultationLog.getMtype())) {
				whereHql += " and t.mtype = :mtype";
				params.put("mtype", fdMemberConsultationLog.getMtype());
			}		
			if (!F.empty(fdMemberConsultationLog.getFromUserId())) {
				whereHql += " and (t.fromUserId = :fromUserId or t.toUserId = :fromUserId)";
				params.put("fromUserId", fdMemberConsultationLog.getFromUserId());
			}		
			if (!F.empty(fdMemberConsultationLog.getToUserId())) {
				whereHql += " and (t.toUserId = :toUserId or t.fromUserId = :toUserId)";
				params.put("toUserId", fdMemberConsultationLog.getToUserId());
			}		
			if (!F.empty(fdMemberConsultationLog.getCreateTime())) {
				whereHql += " and t.createTime = :createTime";
				params.put("createTime", fdMemberConsultationLog.getCreateTime());
			}
			if (!F.empty(fdMemberConsultationLog.getUpdateTime())) {
				whereHql += " and t.updateTime = :updateTime";
				params.put("updateTime", fdMemberConsultationLog.getUpdateTime());
			}		
			if (!F.empty(fdMemberConsultationLog.getStatus())) {
				whereHql += " and t.status = :status";
				params.put("status", fdMemberConsultationLog.getStatus());
			}		
			if (!F.empty(fdMemberConsultationLog.getContent())) {
				whereHql += " and t.content = :content";
				params.put("content", fdMemberConsultationLog.getContent());
			}		
			if (!F.empty(fdMemberConsultationLog.getSenderType())) {
				whereHql += " and t.senderType = :senderType";
				params.put("senderType", fdMemberConsultationLog.getSenderType());
			}		
		}	
		return whereHql;
	}

	@Override
	public void add(FdMemberConsultationLog fdMemberConsultationLog) {
		TfdMemberConsultationLog t = new TfdMemberConsultationLog();
		BeanUtils.copyProperties(fdMemberConsultationLog, t);
		//t.setId(jb.absx.UUID.uuid());
		fdMemberConsultationLogDao.save(t);
	}

	@Override
	public FdMemberConsultationLog get(Integer id) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		TfdMemberConsultationLog t = fdMemberConsultationLogDao.get("from TfdMemberConsultationLog t  where t.id = :id", params);
		FdMemberConsultationLog o = new FdMemberConsultationLog();
		BeanUtils.copyProperties(t, o);
		return o;
	}

	@Override
	public void edit(FdMemberConsultationLog fdMemberConsultationLog) {
		TfdMemberConsultationLog t = fdMemberConsultationLogDao.get(TfdMemberConsultationLog.class, fdMemberConsultationLog.getId());
		if (t != null) {
			MyBeanUtils.copyProperties(fdMemberConsultationLog, t, new String[] { "id" , "addtime", "isdeleted","updatetime" },true);
		}
	}

	@Override
	public void delete(Integer id) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		fdMemberConsultationLogDao.executeHql("update TfdMemberConsultationLog t set t.status = 1 where t.id = :id",params);
		//fdMemberConsultationLogDao.delete(fdMemberConsultationLogDao.get(TfdMemberConsultationLog.class, id));
	}

	@Override
	public Map<String, Object> statistics(FdMemberConsultationLog log) {
		Map<String, Object> result = new HashMap<String, Object>();
		String sql = " select from_unixtime(create_time/1000, '%Y年%m月%d日') consultationTime, count(*) count from fd_member_consultation_log ";

		Map<String, Object> params = new HashMap<String, Object>();
		String where = " where 1 = 1 ";
		if (!F.empty(log.getToUserId())) {
			where += " and to_user_id = :toUserId";
			params.put("toUserId", log.getToUserId());
		}
		if (!F.empty(log.getCreateTimeStart())) {
			where += " and create_time >= :createTimeStart";
			params.put("createTimeStart", log.getCreateTimeStart());
		}
		if (!F.empty(log.getCreateTimeEnd())) {
			where += " and create_time <= :createTimeEnd";
			params.put("createTimeEnd", log.getCreateTimeEnd());
		}

		List<Map> list = fdMemberConsultationLogDao.findBySql2Map(sql + where + " group by consultationTime order by consultationTime asc ", params);
		int total = 0;
		if(CollectionUtils.isNotEmpty(list)) {
			for(Map map : list) {
				Integer count = ((BigInteger)map.get("count")).intValue();
				total += (count == null ? 0 : count);
			}
		}
		result.put("total", total);
		result.put("data", list);

		return result;
	}

}
