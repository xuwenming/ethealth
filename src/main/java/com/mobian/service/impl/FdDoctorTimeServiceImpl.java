package com.mobian.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.mobian.absx.F;
import com.mobian.dao.FdDoctorTimeDaoI;
import com.mobian.model.TfdDoctorTime;
import com.mobian.pageModel.FdDoctorTime;
import com.mobian.pageModel.DataGrid;
import com.mobian.pageModel.PageHelper;
import com.mobian.service.FdDoctorTimeServiceI;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mobian.util.MyBeanUtils;

@Service
public class FdDoctorTimeServiceImpl extends BaseServiceImpl<FdDoctorTime> implements FdDoctorTimeServiceI {

	@Autowired
	private FdDoctorTimeDaoI fdDoctorTimeDao;

	@Override
	public DataGrid dataGrid(FdDoctorTime fdDoctorTime, PageHelper ph) {
		List<FdDoctorTime> ol = new ArrayList<FdDoctorTime>();
		String hql = " from TfdDoctorTime t ";
		DataGrid dg = dataGridQuery(hql, ph, fdDoctorTime, fdDoctorTimeDao);
		@SuppressWarnings("unchecked")
		List<TfdDoctorTime> l = dg.getRows();
		if (l != null && l.size() > 0) {
			for (TfdDoctorTime t : l) {
				FdDoctorTime o = new FdDoctorTime();
				BeanUtils.copyProperties(t, o);
				ol.add(o);
			}
		}
		dg.setRows(ol);
		return dg;
	}
	

	protected String whereHql(FdDoctorTime fdDoctorTime, Map<String, Object> params) {
		String whereHql = "";	
		if (fdDoctorTime != null) {
			whereHql += " where t.status = 0 ";
			if (!F.empty(fdDoctorTime.getUserId())) {
				whereHql += " and t.userId = :userId";
				params.put("userId", fdDoctorTime.getUserId());
			}		
			if (!F.empty(fdDoctorTime.getWeek())) {
				whereHql += " and t.week = :week";
				params.put("week", fdDoctorTime.getWeek());
			}		
			if (!F.empty(fdDoctorTime.getTime())) {
				whereHql += " and t.time = :time";
				params.put("time", fdDoctorTime.getTime());
			}		
			if (!F.empty(fdDoctorTime.getVisitType())) {
				whereHql += " and t.visitType = :visitType";
				params.put("visitType", fdDoctorTime.getVisitType());
			}		
			if (!F.empty(fdDoctorTime.getAddress())) {
				whereHql += " and t.address = :address";
				params.put("address", fdDoctorTime.getAddress());
			}		
			if (!F.empty(fdDoctorTime.getNumber())) {
				whereHql += " and t.number = :number";
				params.put("number", fdDoctorTime.getNumber());
			}		
			if (!F.empty(fdDoctorTime.getCreateBy())) {
				whereHql += " and t.createBy = :createBy";
				params.put("createBy", fdDoctorTime.getCreateBy());
			}		
			if (!F.empty(fdDoctorTime.getCreateTime())) {
				whereHql += " and t.createTime = :createTime";
				params.put("createTime", fdDoctorTime.getCreateTime());
			}		
			if (!F.empty(fdDoctorTime.getUpdateBy())) {
				whereHql += " and t.updateBy = :updateBy";
				params.put("updateBy", fdDoctorTime.getUpdateBy());
			}		
			if (!F.empty(fdDoctorTime.getUpdateTime())) {
				whereHql += " and t.updateTime = :updateTime";
				params.put("updateTime", fdDoctorTime.getUpdateTime());
			}		
			if (!F.empty(fdDoctorTime.getStatus())) {
				whereHql += " and t.status = :status";
				params.put("status", fdDoctorTime.getStatus());
			}		
		}	
		return whereHql;
	}

	@Override
	public void add(FdDoctorTime fdDoctorTime) {
		TfdDoctorTime t = new TfdDoctorTime();
		BeanUtils.copyProperties(fdDoctorTime, t);
		//t.setId(jb.absx.UUID.uuid());
		fdDoctorTimeDao.save(t);
	}

	@Override
	public FdDoctorTime get(Integer id) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		TfdDoctorTime t = fdDoctorTimeDao.get("from TfdDoctorTime t  where t.id = :id", params);
		FdDoctorTime o = new FdDoctorTime();
		BeanUtils.copyProperties(t, o);
		return o;
	}

	@Override
	public void edit(FdDoctorTime fdDoctorTime) {
		TfdDoctorTime t = fdDoctorTimeDao.get(TfdDoctorTime.class, fdDoctorTime.getId());
		if (t != null) {
			MyBeanUtils.copyProperties(fdDoctorTime, t, new String[] { "id" , "addtime", "isdeleted","updatetime" },true);
		}
	}

	@Override
	public void delete(Integer id) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		fdDoctorTimeDao.executeHql("update TfdDoctorTime t set t.isdeleted = 1 where t.id = :id",params);
		//fdDoctorTimeDao.delete(fdDoctorTimeDao.get(TfdDoctorTime.class, id));
	}

}
