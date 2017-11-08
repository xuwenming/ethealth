package com.mobian.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.mobian.absx.F;
import com.mobian.dao.FdDoctorGroupDaoI;
import com.mobian.model.TfdDoctorGroup;
import com.mobian.pageModel.FdDoctorGroup;
import com.mobian.pageModel.DataGrid;
import com.mobian.pageModel.PageHelper;
import com.mobian.service.FdDoctorGroupServiceI;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mobian.util.MyBeanUtils;

@Service
public class FdDoctorGroupServiceImpl extends BaseServiceImpl<FdDoctorGroup> implements FdDoctorGroupServiceI {

	@Autowired
	private FdDoctorGroupDaoI fdDoctorGroupDao;

	@Override
	public DataGrid dataGrid(FdDoctorGroup fdDoctorGroup, PageHelper ph) {
		List<FdDoctorGroup> ol = new ArrayList<FdDoctorGroup>();
		String hql = " from TfdDoctorGroup t ";
		DataGrid dg = dataGridQuery(hql, ph, fdDoctorGroup, fdDoctorGroupDao);
		@SuppressWarnings("unchecked")
		List<TfdDoctorGroup> l = dg.getRows();
		if (l != null && l.size() > 0) {
			for (TfdDoctorGroup t : l) {
				FdDoctorGroup o = new FdDoctorGroup();
				BeanUtils.copyProperties(t, o);
				ol.add(o);
			}
		}
		dg.setRows(ol);
		return dg;
	}
	

	protected String whereHql(FdDoctorGroup fdDoctorGroup, Map<String, Object> params) {
		String whereHql = "";	
		if (fdDoctorGroup != null) {
			whereHql += " where t.status = 0 ";
			if (!F.empty(fdDoctorGroup.getHospitalId())) {
				whereHql += " and t.hospitalId = :hospitalId";
				params.put("hospitalId", fdDoctorGroup.getHospitalId());
			}		
			if (!F.empty(fdDoctorGroup.getDeptId())) {
				whereHql += " and t.deptId = :deptId";
				params.put("deptId", fdDoctorGroup.getDeptId());
			}		
			if (!F.empty(fdDoctorGroup.getGroupName())) {
				whereHql += " and t.groupName = :groupName";
				params.put("groupName", fdDoctorGroup.getGroupName());
			}		
			if (!F.empty(fdDoctorGroup.getIntroduce())) {
				whereHql += " and t.introduce = :introduce";
				params.put("introduce", fdDoctorGroup.getIntroduce());
			}		
			if (!F.empty(fdDoctorGroup.getPics())) {
				whereHql += " and t.pics = :pics";
				params.put("pics", fdDoctorGroup.getPics());
			}		
			if (!F.empty(fdDoctorGroup.getCreateBy())) {
				whereHql += " and t.createBy = :createBy";
				params.put("createBy", fdDoctorGroup.getCreateBy());
			}		
			if (!F.empty(fdDoctorGroup.getCreateTime())) {
				whereHql += " and t.createTime = :createTime";
				params.put("createTime", fdDoctorGroup.getCreateTime());
			}		
			if (!F.empty(fdDoctorGroup.getUpdateBy())) {
				whereHql += " and t.updateBy = :updateBy";
				params.put("updateBy", fdDoctorGroup.getUpdateBy());
			}		
			if (!F.empty(fdDoctorGroup.getUpdateTime())) {
				whereHql += " and t.updateTime = :updateTime";
				params.put("updateTime", fdDoctorGroup.getUpdateTime());
			}		
			if (!F.empty(fdDoctorGroup.getStatus())) {
				whereHql += " and t.status = :status";
				params.put("status", fdDoctorGroup.getStatus());
			}		
			if (!F.empty(fdDoctorGroup.getLeader())) {
				whereHql += " and t.leader = :leader";
				params.put("leader", fdDoctorGroup.getLeader());
			}		
		}	
		return whereHql;
	}

	@Override
	public void add(FdDoctorGroup fdDoctorGroup) {
		TfdDoctorGroup t = new TfdDoctorGroup();
		BeanUtils.copyProperties(fdDoctorGroup, t);
		//t.setId(jb.absx.UUID.uuid());
		fdDoctorGroupDao.save(t);
	}

	@Override
	public FdDoctorGroup get(Integer id) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		TfdDoctorGroup t = fdDoctorGroupDao.get("from TfdDoctorGroup t  where t.id = :id", params);
		FdDoctorGroup o = new FdDoctorGroup();
		BeanUtils.copyProperties(t, o);
		return o;
	}

	@Override
	public void edit(FdDoctorGroup fdDoctorGroup) {
		TfdDoctorGroup t = fdDoctorGroupDao.get(TfdDoctorGroup.class, fdDoctorGroup.getId());
		if (t != null) {
			MyBeanUtils.copyProperties(fdDoctorGroup, t, new String[] { "id" , "addtime", "isdeleted","updatetime" },true);
		}
	}

	@Override
	public void delete(Integer id) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		fdDoctorGroupDao.executeHql("update TfdDoctorGroup t set t.isdeleted = 1 where t.id = :id",params);
		//fdDoctorGroupDao.delete(fdDoctorGroupDao.get(TfdDoctorGroup.class, id));
	}

}
