package com.mobian.service.impl;

import com.mobian.absx.F;
import com.mobian.dao.FdMemberConsultationExpireDaoI;
import com.mobian.model.TfdMemberConsultationExpire;
import com.mobian.pageModel.DataGrid;
import com.mobian.pageModel.FdMemberConsultationExpire;
import com.mobian.pageModel.PageHelper;
import com.mobian.service.FdMemberConsultationExpireServiceI;
import com.mobian.util.MyBeanUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class FdMemberConsultationExpireServiceImpl extends BaseServiceImpl<FdMemberConsultationExpire> implements FdMemberConsultationExpireServiceI {

	@Autowired
	private FdMemberConsultationExpireDaoI fdMemberConsultationExpireDao;

	@Override
	public DataGrid dataGrid(FdMemberConsultationExpire fdMemberConsultationExpire, PageHelper ph) {
		List<FdMemberConsultationExpire> ol = new ArrayList<FdMemberConsultationExpire>();
		String hql = " from TfdMemberConsultationExpire t ";
		DataGrid dg = dataGridQuery(hql, ph, fdMemberConsultationExpire, fdMemberConsultationExpireDao);
		@SuppressWarnings("unchecked")
		List<TfdMemberConsultationExpire> l = dg.getRows();
		if (l != null && l.size() > 0) {
			for (TfdMemberConsultationExpire t : l) {
				FdMemberConsultationExpire o = new FdMemberConsultationExpire();
				BeanUtils.copyProperties(t, o);
				ol.add(o);
			}
		}
		dg.setRows(ol);
		return dg;
	}
	

	protected String whereHql(FdMemberConsultationExpire fdMemberConsultationExpire, Map<String, Object> params) {
		String whereHql = "";	
		if (fdMemberConsultationExpire != null) {
			whereHql += " where t.status = 0 ";
			if (!F.empty(fdMemberConsultationExpire.getUserId())) {
				whereHql += " and t.userId = :userId";
				params.put("userId", fdMemberConsultationExpire.getUserId());
			}		
			if (!F.empty(fdMemberConsultationExpire.getDoctorId())) {
				whereHql += " and t.doctorId = :doctorId";
				params.put("doctorId", fdMemberConsultationExpire.getDoctorId());
			}		
			if (!F.empty(fdMemberConsultationExpire.getCreateBy())) {
				whereHql += " and t.createBy = :createBy";
				params.put("createBy", fdMemberConsultationExpire.getCreateBy());
			}		
			if (!F.empty(fdMemberConsultationExpire.getCreateTime())) {
				whereHql += " and t.createTime = :createTime";
				params.put("createTime", fdMemberConsultationExpire.getCreateTime());
			}		
			if (!F.empty(fdMemberConsultationExpire.getUpdateBy())) {
				whereHql += " and t.updateBy = :updateBy";
				params.put("updateBy", fdMemberConsultationExpire.getUpdateBy());
			}		
			if (!F.empty(fdMemberConsultationExpire.getUpdateTime())) {
				whereHql += " and t.updateTime = :updateTime";
				params.put("updateTime", fdMemberConsultationExpire.getUpdateTime());
			}		
			if (!F.empty(fdMemberConsultationExpire.getStatus())) {
				whereHql += " and t.status = :status";
				params.put("status", fdMemberConsultationExpire.getStatus());
			}		
		}	
		return whereHql;
	}

	@Override
	public void add(FdMemberConsultationExpire fdMemberConsultationExpire) {
		TfdMemberConsultationExpire t = new TfdMemberConsultationExpire();
		BeanUtils.copyProperties(fdMemberConsultationExpire, t);
		//t.setId(jb.absx.UUID.uuid());
		fdMemberConsultationExpireDao.save(t);
	}

	@Override
	public FdMemberConsultationExpire get(Integer id) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		TfdMemberConsultationExpire t = fdMemberConsultationExpireDao.get("from TfdMemberConsultationExpire t  where t.id = :id", params);
		FdMemberConsultationExpire o = new FdMemberConsultationExpire();
		BeanUtils.copyProperties(t, o);
		return o;
	}

	@Override
	public void edit(FdMemberConsultationExpire fdMemberConsultationExpire) {
		TfdMemberConsultationExpire t = fdMemberConsultationExpireDao.get(TfdMemberConsultationExpire.class, fdMemberConsultationExpire.getId());
		if (t != null) {
			MyBeanUtils.copyProperties(fdMemberConsultationExpire, t, new String[] { "id" , "addtime", "isdeleted","updatetime" },true);
		}
	}

	@Override
	public void delete(Integer id) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		fdMemberConsultationExpireDao.executeHql("update TfdMemberConsultationExpire t set t.isdeleted = 1 where t.id = :id",params);
		//fdMemberConsultationExpireDao.delete(fdMemberConsultationExpireDao.get(TfdMemberConsultationExpire.class, id));
	}

	@Override
	public FdMemberConsultationExpire getByUserIdAndDoctorId(Integer userId, Integer doctorId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userId", userId);
		params.put("doctorId", doctorId);
		TfdMemberConsultationExpire t = fdMemberConsultationExpireDao.get("from TfdMemberConsultationExpire t  where t.status = 0 and t.userId = :userId and t.doctorId = :doctorId ", params);
		if(t != null) {
			FdMemberConsultationExpire o = new FdMemberConsultationExpire();
			BeanUtils.copyProperties(t, o);
			return o;
		}
		return null;
	}

}
