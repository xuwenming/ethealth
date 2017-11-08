package com.mobian.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.mobian.absx.F;
import com.mobian.dao.FdHospitalDaoI;
import com.mobian.model.TfdHospital;
import com.mobian.pageModel.FdHospital;
import com.mobian.pageModel.DataGrid;
import com.mobian.pageModel.PageHelper;
import com.mobian.service.FdHospitalServiceI;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mobian.util.MyBeanUtils;

@Service
public class FdHospitalServiceImpl extends BaseServiceImpl<FdHospital> implements FdHospitalServiceI {

	@Autowired
	private FdHospitalDaoI fdHospitalDao;

	@Override
	public DataGrid dataGrid(FdHospital fdHospital, PageHelper ph) {
		List<FdHospital> ol = new ArrayList<FdHospital>();
		String hql = " from TfdHospital t ";
		DataGrid dg = dataGridQuery(hql, ph, fdHospital, fdHospitalDao);
		@SuppressWarnings("unchecked")
		List<TfdHospital> l = dg.getRows();
		if (l != null && l.size() > 0) {
			for (TfdHospital t : l) {
				FdHospital o = new FdHospital();
				BeanUtils.copyProperties(t, o);
				ol.add(o);
			}
		}
		dg.setRows(ol);
		return dg;
	}
	

	protected String whereHql(FdHospital fdHospital, Map<String, Object> params) {
		String whereHql = "";	
		if (fdHospital != null) {
			whereHql += " where t.status = 0 ";
			if (!F.empty(fdHospital.getHospitalName())) {
				whereHql += " and t.hospitalName = :hospitalName";
				params.put("hospitalName", fdHospital.getHospitalName());
			}		
			if (!F.empty(fdHospital.getHospitalLevel())) {
				whereHql += " and t.hospitalLevel = :hospitalLevel";
				params.put("hospitalLevel", fdHospital.getHospitalLevel());
			}		
			if (!F.empty(fdHospital.getIntroduce())) {
				whereHql += " and t.introduce = :introduce";
				params.put("introduce", fdHospital.getIntroduce());
			}		
			if (!F.empty(fdHospital.getProvince())) {
				whereHql += " and t.province = :province";
				params.put("province", fdHospital.getProvince());
			}		
			if (!F.empty(fdHospital.getCity())) {
				whereHql += " and t.city = :city";
				params.put("city", fdHospital.getCity());
			}		
			if (!F.empty(fdHospital.getCounty())) {
				whereHql += " and t.county = :county";
				params.put("county", fdHospital.getCounty());
			}		
			if (!F.empty(fdHospital.getPics())) {
				whereHql += " and t.pics = :pics";
				params.put("pics", fdHospital.getPics());
			}		
			if (!F.empty(fdHospital.getCreateBy())) {
				whereHql += " and t.createBy = :createBy";
				params.put("createBy", fdHospital.getCreateBy());
			}		
			if (!F.empty(fdHospital.getCreateTime())) {
				whereHql += " and t.createTime = :createTime";
				params.put("createTime", fdHospital.getCreateTime());
			}		
			if (!F.empty(fdHospital.getUpdateBy())) {
				whereHql += " and t.updateBy = :updateBy";
				params.put("updateBy", fdHospital.getUpdateBy());
			}		
			if (!F.empty(fdHospital.getUpdateTime())) {
				whereHql += " and t.updateTime = :updateTime";
				params.put("updateTime", fdHospital.getUpdateTime());
			}		
			if (!F.empty(fdHospital.getStatus())) {
				whereHql += " and t.status = :status";
				params.put("status", fdHospital.getStatus());
			}		
		}	
		return whereHql;
	}

	@Override
	public void add(FdHospital fdHospital) {
		TfdHospital t = new TfdHospital();
		BeanUtils.copyProperties(fdHospital, t);
		//t.setId(jb.absx.UUID.uuid());
		fdHospitalDao.save(t);
	}

	@Override
	public FdHospital get(Integer id) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		TfdHospital t = fdHospitalDao.get("from TfdHospital t  where t.id = :id", params);
		FdHospital o = new FdHospital();
		BeanUtils.copyProperties(t, o);
		return o;
	}

	@Override
	public void edit(FdHospital fdHospital) {
		TfdHospital t = fdHospitalDao.get(TfdHospital.class, fdHospital.getId());
		if (t != null) {
			MyBeanUtils.copyProperties(fdHospital, t, new String[] { "id" , "addtime", "isdeleted","updatetime" },true);
		}
	}

	@Override
	public void delete(Integer id) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		fdHospitalDao.executeHql("update TfdHospital t set t.isdeleted = 1 where t.id = :id",params);
		//fdHospitalDao.delete(fdHospitalDao.get(TfdHospital.class, id));
	}

}
