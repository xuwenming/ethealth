package com.mobian.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.mobian.absx.F;
import com.mobian.dao.FdMemberDoctorLevelDaoI;
import com.mobian.model.TfdMemberDoctorLevel;
import com.mobian.pageModel.FdMemberDoctorLevel;
import com.mobian.pageModel.DataGrid;
import com.mobian.pageModel.PageHelper;
import com.mobian.service.FdMemberDoctorLevelServiceI;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mobian.util.MyBeanUtils;

@Service
public class FdMemberDoctorLevelServiceImpl extends BaseServiceImpl<FdMemberDoctorLevel> implements FdMemberDoctorLevelServiceI {

	@Autowired
	private FdMemberDoctorLevelDaoI fdMemberDoctorLevelDao;

	@Override
	public DataGrid dataGrid(FdMemberDoctorLevel fdMemberDoctorLevel, PageHelper ph) {
		List<FdMemberDoctorLevel> ol = new ArrayList<FdMemberDoctorLevel>();
		String hql = " from TfdMemberDoctorLevel t ";
		DataGrid dg = dataGridQuery(hql, ph, fdMemberDoctorLevel, fdMemberDoctorLevelDao);
		@SuppressWarnings("unchecked")
		List<TfdMemberDoctorLevel> l = dg.getRows();
		if (l != null && l.size() > 0) {
			for (TfdMemberDoctorLevel t : l) {
				FdMemberDoctorLevel o = new FdMemberDoctorLevel();
				BeanUtils.copyProperties(t, o);
				ol.add(o);
			}
		}
		dg.setRows(ol);
		return dg;
	}
	

	protected String whereHql(FdMemberDoctorLevel fdMemberDoctorLevel, Map<String, Object> params) {
		String whereHql = "";	
		if (fdMemberDoctorLevel != null) {
			whereHql += " where t.status = 0 ";
			if (!F.empty(fdMemberDoctorLevel.getName())) {
				whereHql += " and t.name = :name";
				params.put("name", fdMemberDoctorLevel.getName());
			}		
			if (!F.empty(fdMemberDoctorLevel.getRemark())) {
				whereHql += " and t.remark = :remark";
				params.put("remark", fdMemberDoctorLevel.getRemark());
			}		
			if (!F.empty(fdMemberDoctorLevel.getCreateBy())) {
				whereHql += " and t.createBy = :createBy";
				params.put("createBy", fdMemberDoctorLevel.getCreateBy());
			}		
			if (!F.empty(fdMemberDoctorLevel.getCreateTime())) {
				whereHql += " and t.createTime = :createTime";
				params.put("createTime", fdMemberDoctorLevel.getCreateTime());
			}		
			if (!F.empty(fdMemberDoctorLevel.getUpdateBy())) {
				whereHql += " and t.updateBy = :updateBy";
				params.put("updateBy", fdMemberDoctorLevel.getUpdateBy());
			}		
			if (!F.empty(fdMemberDoctorLevel.getUpdateTime())) {
				whereHql += " and t.updateTime = :updateTime";
				params.put("updateTime", fdMemberDoctorLevel.getUpdateTime());
			}		
			if (!F.empty(fdMemberDoctorLevel.getStatus())) {
				whereHql += " and t.status = :status";
				params.put("status", fdMemberDoctorLevel.getStatus());
			}		
		}	
		return whereHql;
	}

	@Override
	public void add(FdMemberDoctorLevel fdMemberDoctorLevel) {
		TfdMemberDoctorLevel t = new TfdMemberDoctorLevel();
		BeanUtils.copyProperties(fdMemberDoctorLevel, t);
		//t.setId(jb.absx.UUID.uuid());
		fdMemberDoctorLevelDao.save(t);
	}

	@Override
	public FdMemberDoctorLevel get(Integer id) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		TfdMemberDoctorLevel t = fdMemberDoctorLevelDao.get("from TfdMemberDoctorLevel t  where t.id = :id", params);
		FdMemberDoctorLevel o = new FdMemberDoctorLevel();
		BeanUtils.copyProperties(t, o);
		return o;
	}

	@Override
	public void edit(FdMemberDoctorLevel fdMemberDoctorLevel) {
		TfdMemberDoctorLevel t = fdMemberDoctorLevelDao.get(TfdMemberDoctorLevel.class, fdMemberDoctorLevel.getId());
		if (t != null) {
			MyBeanUtils.copyProperties(fdMemberDoctorLevel, t, new String[] { "id" , "addtime", "isdeleted","updatetime" },true);
		}
	}

	@Override
	public void delete(Integer id) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		fdMemberDoctorLevelDao.executeHql("update TfdMemberDoctorLevel t set t.isdeleted = 1 where t.id = :id",params);
		//fdMemberDoctorLevelDao.delete(fdMemberDoctorLevelDao.get(TfdMemberDoctorLevel.class, id));
	}

}
