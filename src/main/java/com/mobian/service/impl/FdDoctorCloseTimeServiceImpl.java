package com.mobian.service.impl;

import java.util.*;

import com.mobian.absx.F;
import com.mobian.dao.FdDoctorCloseTimeDaoI;
import com.mobian.enums.EnumConstants;
import com.mobian.model.TfdDoctorCloseTime;
import com.mobian.pageModel.FdDoctorCloseTime;
import com.mobian.pageModel.DataGrid;
import com.mobian.pageModel.PageHelper;
import com.mobian.service.FdDoctorCloseTimeServiceI;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mobian.util.MyBeanUtils;

@Service
public class FdDoctorCloseTimeServiceImpl extends BaseServiceImpl<FdDoctorCloseTime> implements FdDoctorCloseTimeServiceI {

	@Autowired
	private FdDoctorCloseTimeDaoI fdDoctorCloseTimeDao;

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

}
