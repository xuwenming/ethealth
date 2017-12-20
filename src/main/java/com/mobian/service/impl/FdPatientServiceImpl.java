package com.mobian.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.mobian.absx.F;
import com.mobian.dao.FdPatientDaoI;
import com.mobian.model.TfdPatient;
import com.mobian.pageModel.FdPatient;
import com.mobian.pageModel.DataGrid;
import com.mobian.pageModel.PageHelper;
import com.mobian.service.FdPatientServiceI;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mobian.util.MyBeanUtils;

@Service
public class FdPatientServiceImpl extends BaseServiceImpl<FdPatient> implements FdPatientServiceI {

	@Autowired
	private FdPatientDaoI fdPatientDao;

	@Override
	public DataGrid dataGrid(FdPatient fdPatient, PageHelper ph) {
		List<FdPatient> ol = new ArrayList<FdPatient>();
		String hql = " from TfdPatient t ";
		DataGrid dg = dataGridQuery(hql, ph, fdPatient, fdPatientDao);
		@SuppressWarnings("unchecked")
		List<TfdPatient> l = dg.getRows();
		if (l != null && l.size() > 0) {
			for (TfdPatient t : l) {
				FdPatient o = new FdPatient();
				BeanUtils.copyProperties(t, o);
				ol.add(o);
			}
		}
		dg.setRows(ol);
		return dg;
	}
	

	protected String whereHql(FdPatient fdPatient, Map<String, Object> params) {
		String whereHql = "";	
		if (fdPatient != null) {
			whereHql += " where t.status = 0 ";
			if (!F.empty(fdPatient.getCreateTime())) {
				whereHql += " and t.createTime = :createTime";
				params.put("createTime", fdPatient.getCreateTime());
			}		
			if (!F.empty(fdPatient.getUpdateTime())) {
				whereHql += " and t.updateTime = :updateTime";
				params.put("updateTime", fdPatient.getUpdateTime());
			}		
			if (!F.empty(fdPatient.getStatus())) {
				whereHql += " and t.status = :status";
				params.put("status", fdPatient.getStatus());
			}		
			if (!F.empty(fdPatient.getRealName())) {
				whereHql += " and t.realName = :realName";
				params.put("realName", fdPatient.getRealName());
			}		
			if (!F.empty(fdPatient.getSex())) {
				whereHql += " and t.sex = :sex";
				params.put("sex", fdPatient.getSex());
			}		
			if (!F.empty(fdPatient.getBirthday())) {
				whereHql += " and t.birthday = :birthday";
				params.put("birthday", fdPatient.getBirthday());
			}		
			if (!F.empty(fdPatient.getRelation())) {
				whereHql += " and t.relation = :relation";
				params.put("relation", fdPatient.getRelation());
			}		
		}	
		return whereHql;
	}

	@Override
	public void add(FdPatient fdPatient) {
		TfdPatient t = new TfdPatient();
		BeanUtils.copyProperties(fdPatient, t);
		//t.setId(jb.absx.UUID.uuid());
		fdPatientDao.save(t);
	}

	@Override
	public FdPatient get(Integer userId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userId", userId);
		TfdPatient t = fdPatientDao.get("from TfdPatient t  where t.status = 0 and t.userId = :userId", params);
		if(t != null) {
			FdPatient o = new FdPatient();
			BeanUtils.copyProperties(t, o);
			return o;
		}

		return null;
	}

	@Override
	public void edit(FdPatient fdPatient) {
		TfdPatient t = fdPatientDao.get(TfdPatient.class, fdPatient.getUserId());
		if (t != null) {
			MyBeanUtils.copyProperties(fdPatient, t, new String[] { "id" , "addtime", "isdeleted","updatetime" },true);
		}
	}

	@Override
	public void delete(Integer id) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		fdPatientDao.executeHql("update TfdPatient t set t.isdeleted = 1 where t.id = :id",params);
		//fdPatientDao.delete(fdPatientDao.get(TfdPatient.class, id));
	}

}
