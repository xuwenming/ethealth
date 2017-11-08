package com.mobian.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.mobian.absx.F;
import com.mobian.dao.FdDoctorDynamicDaoI;
import com.mobian.model.TfdDoctorDynamic;
import com.mobian.pageModel.FdDoctorDynamic;
import com.mobian.pageModel.DataGrid;
import com.mobian.pageModel.PageHelper;
import com.mobian.service.FdDoctorDynamicServiceI;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mobian.util.MyBeanUtils;

@Service
public class FdDoctorDynamicServiceImpl extends BaseServiceImpl<FdDoctorDynamic> implements FdDoctorDynamicServiceI {

	@Autowired
	private FdDoctorDynamicDaoI fdDoctorDynamicDao;

	@Override
	public DataGrid dataGrid(FdDoctorDynamic fdDoctorDynamic, PageHelper ph) {
		List<FdDoctorDynamic> ol = new ArrayList<FdDoctorDynamic>();
		String hql = " from TfdDoctorDynamic t ";
		DataGrid dg = dataGridQuery(hql, ph, fdDoctorDynamic, fdDoctorDynamicDao);
		@SuppressWarnings("unchecked")
		List<TfdDoctorDynamic> l = dg.getRows();
		if (l != null && l.size() > 0) {
			for (TfdDoctorDynamic t : l) {
				FdDoctorDynamic o = new FdDoctorDynamic();
				BeanUtils.copyProperties(t, o);
				ol.add(o);
			}
		}
		dg.setRows(ol);
		return dg;
	}
	

	protected String whereHql(FdDoctorDynamic fdDoctorDynamic, Map<String, Object> params) {
		String whereHql = "";	
		if (fdDoctorDynamic != null) {
			whereHql += " where t.status = 1 ";
			if (!F.empty(fdDoctorDynamic.getUserId())) {
				whereHql += " and t.userId = :userId";
				params.put("userId", fdDoctorDynamic.getUserId());
			}		
			if (!F.empty(fdDoctorDynamic.getCreateTime())) {
				whereHql += " and t.createTime = :createTime";
				params.put("createTime", fdDoctorDynamic.getCreateTime());
			}		
			if (!F.empty(fdDoctorDynamic.getUpdateTime())) {
				whereHql += " and t.updateTime = :updateTime";
				params.put("updateTime", fdDoctorDynamic.getUpdateTime());
			}		
			if (!F.empty(fdDoctorDynamic.getStatus())) {
				whereHql += " and t.status = :status";
				params.put("status", fdDoctorDynamic.getStatus());
			}		
			if (!F.empty(fdDoctorDynamic.getContent())) {
				whereHql += " and t.content = :content";
				params.put("content", fdDoctorDynamic.getContent());
			}		
		}	
		return whereHql;
	}

	@Override
	public void add(FdDoctorDynamic fdDoctorDynamic) {
		TfdDoctorDynamic t = new TfdDoctorDynamic();
		BeanUtils.copyProperties(fdDoctorDynamic, t);
		//t.setId(jb.absx.UUID.uuid());
		fdDoctorDynamicDao.save(t);
	}

	@Override
	public FdDoctorDynamic get(Integer id) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		TfdDoctorDynamic t = fdDoctorDynamicDao.get("from TfdDoctorDynamic t  where t.id = :id", params);
		FdDoctorDynamic o = new FdDoctorDynamic();
		BeanUtils.copyProperties(t, o);
		return o;
	}

	@Override
	public void edit(FdDoctorDynamic fdDoctorDynamic) {
		TfdDoctorDynamic t = fdDoctorDynamicDao.get(TfdDoctorDynamic.class, fdDoctorDynamic.getId());
		if (t != null) {
			MyBeanUtils.copyProperties(fdDoctorDynamic, t, new String[] { "id" , "addtime", "isdeleted","updatetime" },true);
		}
	}

	@Override
	public void delete(Integer id) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		fdDoctorDynamicDao.executeHql("update TfdDoctorDynamic t set t.isdeleted = 1 where t.id = :id",params);
		//fdDoctorDynamicDao.delete(fdDoctorDynamicDao.get(TfdDoctorDynamic.class, id));
	}

}
