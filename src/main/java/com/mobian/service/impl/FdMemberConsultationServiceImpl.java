package com.mobian.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.mobian.absx.F;
import com.mobian.dao.FdMemberConsultationDaoI;
import com.mobian.model.TfdMemberConsultation;
import com.mobian.pageModel.FdMemberConsultation;
import com.mobian.pageModel.DataGrid;
import com.mobian.pageModel.PageHelper;
import com.mobian.service.FdMemberConsultationServiceI;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mobian.util.MyBeanUtils;

@Service
public class FdMemberConsultationServiceImpl extends BaseServiceImpl<FdMemberConsultation> implements FdMemberConsultationServiceI {

	@Autowired
	private FdMemberConsultationDaoI fdMemberConsultationDao;

	@Override
	public DataGrid dataGrid(FdMemberConsultation fdMemberConsultation, PageHelper ph) {
		List<FdMemberConsultation> ol = new ArrayList<FdMemberConsultation>();
		String hql = " from TfdMemberConsultation t ";
		DataGrid dg = dataGridQuery(hql, ph, fdMemberConsultation, fdMemberConsultationDao);
		@SuppressWarnings("unchecked")
		List<TfdMemberConsultation> l = dg.getRows();
		if (l != null && l.size() > 0) {
			for (TfdMemberConsultation t : l) {
				FdMemberConsultation o = new FdMemberConsultation();
				BeanUtils.copyProperties(t, o);
				ol.add(o);
			}
		}
		dg.setRows(ol);
		return dg;
	}
	

	protected String whereHql(FdMemberConsultation fdMemberConsultation, Map<String, Object> params) {
		String whereHql = "";	
		if (fdMemberConsultation != null) {
			whereHql += " where t.status = 1 ";
			if (!F.empty(fdMemberConsultation.getUserId())) {
				whereHql += " and t.userId = :userId";
				params.put("userId", fdMemberConsultation.getUserId());
			}		
			if (!F.empty(fdMemberConsultation.getDoctorId())) {
				whereHql += " and t.doctorId = :doctorId";
				params.put("doctorId", fdMemberConsultation.getDoctorId());
			}		
			if (!F.empty(fdMemberConsultation.getConsultationMessage())) {
				whereHql += " and t.consultationMessage = :consultationMessage";
				params.put("consultationMessage", fdMemberConsultation.getConsultationMessage());
			}		
			if (!F.empty(fdMemberConsultation.getCreateBy())) {
				whereHql += " and t.createBy = :createBy";
				params.put("createBy", fdMemberConsultation.getCreateBy());
			}		
			if (!F.empty(fdMemberConsultation.getCreateTime())) {
				whereHql += " and t.createTime = :createTime";
				params.put("createTime", fdMemberConsultation.getCreateTime());
			}		
			if (!F.empty(fdMemberConsultation.getUpdateBy())) {
				whereHql += " and t.updateBy = :updateBy";
				params.put("updateBy", fdMemberConsultation.getUpdateBy());
			}		
			if (!F.empty(fdMemberConsultation.getUpdateTime())) {
				whereHql += " and t.updateTime = :updateTime";
				params.put("updateTime", fdMemberConsultation.getUpdateTime());
			}		
			if (!F.empty(fdMemberConsultation.getStatus())) {
				whereHql += " and t.status = :status";
				params.put("status", fdMemberConsultation.getStatus());
			}		
			if (!F.empty(fdMemberConsultation.getCouponId())) {
				whereHql += " and t.couponId = :couponId";
				params.put("couponId", fdMemberConsultation.getCouponId());
			}		
			if (!F.empty(fdMemberConsultation.getPics())) {
				whereHql += " and t.pics = :pics";
				params.put("pics", fdMemberConsultation.getPics());
			}		
			if (!F.empty(fdMemberConsultation.getFile())) {
				whereHql += " and t.file = :file";
				params.put("file", fdMemberConsultation.getFile());
			}		
			if (!F.empty(fdMemberConsultation.getConsultationStatus())) {
				whereHql += " and t.consultationStatus = :consultationStatus";
				params.put("consultationStatus", fdMemberConsultation.getConsultationStatus());
			}		
			if (!F.empty(fdMemberConsultation.getDoctorReply())) {
				whereHql += " and t.doctorReply = :doctorReply";
				params.put("doctorReply", fdMemberConsultation.getDoctorReply());
			}		
		}	
		return whereHql;
	}

	@Override
	public void add(FdMemberConsultation fdMemberConsultation) {
		TfdMemberConsultation t = new TfdMemberConsultation();
		BeanUtils.copyProperties(fdMemberConsultation, t);
		//t.setId(jb.absx.UUID.uuid());
		fdMemberConsultationDao.save(t);
	}

	@Override
	public FdMemberConsultation get(Integer id) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		TfdMemberConsultation t = fdMemberConsultationDao.get("from TfdMemberConsultation t  where t.id = :id", params);
		FdMemberConsultation o = new FdMemberConsultation();
		BeanUtils.copyProperties(t, o);
		return o;
	}

	@Override
	public void edit(FdMemberConsultation fdMemberConsultation) {
		TfdMemberConsultation t = fdMemberConsultationDao.get(TfdMemberConsultation.class, fdMemberConsultation.getId());
		if (t != null) {
			MyBeanUtils.copyProperties(fdMemberConsultation, t, new String[] { "id" , "addtime", "isdeleted","updatetime" },true);
		}
	}

	@Override
	public void delete(Integer id) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		fdMemberConsultationDao.executeHql("update TfdMemberConsultation t set t.isdeleted = 1 where t.id = :id",params);
		//fdMemberConsultationDao.delete(fdMemberConsultationDao.get(TfdMemberConsultation.class, id));
	}

}
