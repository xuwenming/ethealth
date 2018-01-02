package com.mobian.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.mobian.absx.F;
import com.mobian.dao.FdHospitalDeptDaoI;
import com.mobian.model.TfdHospitalDept;
import com.mobian.pageModel.FdHospitalDept;
import com.mobian.pageModel.DataGrid;
import com.mobian.pageModel.PageHelper;
import com.mobian.service.FdHospitalDeptServiceI;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mobian.util.MyBeanUtils;

@Service
public class FdHospitalDeptServiceImpl extends BaseServiceImpl<FdHospitalDept> implements FdHospitalDeptServiceI {

	@Autowired
	private FdHospitalDeptDaoI fdHospitalDeptDao;

	@Override
	public DataGrid dataGrid(FdHospitalDept fdHospitalDept, PageHelper ph) {
		List<FdHospitalDept> ol = new ArrayList<FdHospitalDept>();
		String hql = " from TfdHospitalDept t ";
		DataGrid dg = dataGridQuery(hql, ph, fdHospitalDept, fdHospitalDeptDao);
		@SuppressWarnings("unchecked")
		List<TfdHospitalDept> l = dg.getRows();
		if (l != null && l.size() > 0) {
			for (TfdHospitalDept t : l) {
				FdHospitalDept o = new FdHospitalDept();
				BeanUtils.copyProperties(t, o);
				ol.add(o);
			}
		}
		dg.setRows(ol);
		return dg;
	}
	

	protected String whereHql(FdHospitalDept fdHospitalDept, Map<String, Object> params) {
		String whereHql = "";	
		if (fdHospitalDept != null) {
			whereHql += " where t.status = 0 ";
			if (!F.empty(fdHospitalDept.getHospitalId())) {
				whereHql += " and t.hospitalId = :hospitalId";
				params.put("hospitalId", fdHospitalDept.getHospitalId());
			}		
			if (!F.empty(fdHospitalDept.getName())) {
				whereHql += " and t.name = :name";
				params.put("name", fdHospitalDept.getName());
			}		
			if (!F.empty(fdHospitalDept.getCreateBy())) {
				whereHql += " and t.createBy = :createBy";
				params.put("createBy", fdHospitalDept.getCreateBy());
			}		
			if (!F.empty(fdHospitalDept.getCreateTime())) {
				whereHql += " and t.createTime = :createTime";
				params.put("createTime", fdHospitalDept.getCreateTime());
			}		
			if (!F.empty(fdHospitalDept.getUpdateBy())) {
				whereHql += " and t.updateBy = :updateBy";
				params.put("updateBy", fdHospitalDept.getUpdateBy());
			}		
			if (!F.empty(fdHospitalDept.getUpdateTime())) {
				whereHql += " and t.updateTime = :updateTime";
				params.put("updateTime", fdHospitalDept.getUpdateTime());
			}		
			if (!F.empty(fdHospitalDept.getStatus())) {
				whereHql += " and t.status = :status";
				params.put("status", fdHospitalDept.getStatus());
			}		
		}	
		return whereHql;
	}

	@Override
	public void add(FdHospitalDept fdHospitalDept) {
		TfdHospitalDept t = new TfdHospitalDept();
		BeanUtils.copyProperties(fdHospitalDept, t);
		//t.setId(jb.absx.UUID.uuid());
		fdHospitalDeptDao.save(t);
	}

	@Override
	public FdHospitalDept get(Integer id) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		TfdHospitalDept t = fdHospitalDeptDao.get("from TfdHospitalDept t  where t.id = :id", params);
		if(t == null) return null;
		FdHospitalDept o = new FdHospitalDept();
		BeanUtils.copyProperties(t, o);
		return o;
	}

	@Override
	public void edit(FdHospitalDept fdHospitalDept) {
		TfdHospitalDept t = fdHospitalDeptDao.get(TfdHospitalDept.class, fdHospitalDept.getId());
		if (t != null) {
			MyBeanUtils.copyProperties(fdHospitalDept, t, new String[] { "id" , "addtime", "isdeleted","updatetime" },true);
		}
	}

	@Override
	public void delete(Integer id) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		fdHospitalDeptDao.executeHql("update TfdHospitalDept t set t.isdeleted = 1 where t.id = :id",params);
		//fdHospitalDeptDao.delete(fdHospitalDeptDao.get(TfdHospitalDept.class, id));
	}

}
