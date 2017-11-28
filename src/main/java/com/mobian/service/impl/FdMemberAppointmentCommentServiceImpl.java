package com.mobian.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.mobian.absx.F;
import com.mobian.dao.FdMemberAppointmentCommentDaoI;
import com.mobian.model.TfdMemberAppointmentComment;
import com.mobian.pageModel.FdMemberAppointmentComment;
import com.mobian.pageModel.DataGrid;
import com.mobian.pageModel.PageHelper;
import com.mobian.service.FdMemberAppointmentCommentServiceI;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mobian.util.MyBeanUtils;

@Service
public class FdMemberAppointmentCommentServiceImpl extends BaseServiceImpl<FdMemberAppointmentComment> implements FdMemberAppointmentCommentServiceI {

	@Autowired
	private FdMemberAppointmentCommentDaoI fdMemberAppointmentCommentDao;

	@Override
	public DataGrid dataGrid(FdMemberAppointmentComment fdMemberAppointmentComment, PageHelper ph) {
		List<FdMemberAppointmentComment> ol = new ArrayList<FdMemberAppointmentComment>();
		String hql = " from TfdMemberAppointmentComment t ";
		DataGrid dg = dataGridQuery(hql, ph, fdMemberAppointmentComment, fdMemberAppointmentCommentDao);
		@SuppressWarnings("unchecked")
		List<TfdMemberAppointmentComment> l = dg.getRows();
		if (l != null && l.size() > 0) {
			for (TfdMemberAppointmentComment t : l) {
				FdMemberAppointmentComment o = new FdMemberAppointmentComment();
				BeanUtils.copyProperties(t, o);
				ol.add(o);
			}
		}
		dg.setRows(ol);
		return dg;
	}
	

	protected String whereHql(FdMemberAppointmentComment fdMemberAppointmentComment, Map<String, Object> params) {
		String whereHql = "";	
		if (fdMemberAppointmentComment != null) {
			whereHql += " where t.status = 0 ";
			if (!F.empty(fdMemberAppointmentComment.getDoctorId())) {
				whereHql += " and t.doctorId = :doctorId";
				params.put("doctorId", fdMemberAppointmentComment.getDoctorId());
			}		
			if (!F.empty(fdMemberAppointmentComment.getAppointmentId())) {
				whereHql += " and t.appointmentId = :appointmentId";
				params.put("appointmentId", fdMemberAppointmentComment.getAppointmentId());
			}		
			if (!F.empty(fdMemberAppointmentComment.getUserId())) {
				whereHql += " and t.userId = :userId";
				params.put("userId", fdMemberAppointmentComment.getUserId());
			}		
			if (!F.empty(fdMemberAppointmentComment.getCreateTime())) {
				whereHql += " and t.createTime = :createTime";
				params.put("createTime", fdMemberAppointmentComment.getCreateTime());
			}		
			if (!F.empty(fdMemberAppointmentComment.getUpdateTime())) {
				whereHql += " and t.updateTime = :updateTime";
				params.put("updateTime", fdMemberAppointmentComment.getUpdateTime());
			}		
			if (!F.empty(fdMemberAppointmentComment.getStatus())) {
				whereHql += " and t.status = :status";
				params.put("status", fdMemberAppointmentComment.getStatus());
			}
			if (!F.empty(fdMemberAppointmentComment.getDisease())) {
				whereHql += " and t.disease = :disease";
				params.put("disease", fdMemberAppointmentComment.getDisease());
			}		
			if (!F.empty(fdMemberAppointmentComment.getObjective())) {
				whereHql += " and t.objective = :objective";
				params.put("objective", fdMemberAppointmentComment.getObjective());
			}		
			if (!F.empty(fdMemberAppointmentComment.getObjectiveOther())) {
				whereHql += " and t.objectiveOther = :objectiveOther";
				params.put("objectiveOther", fdMemberAppointmentComment.getObjectiveOther());
			}		
			if (!F.empty(fdMemberAppointmentComment.getTherapy())) {
				whereHql += " and t.therapy = :therapy";
				params.put("therapy", fdMemberAppointmentComment.getTherapy());
			}		
			if (!F.empty(fdMemberAppointmentComment.getTherapyOther())) {
				whereHql += " and t.therapyOther = :therapyOther";
				params.put("therapyOther", fdMemberAppointmentComment.getTherapyOther());
			}		
			if (!F.empty(fdMemberAppointmentComment.getComment())) {
				whereHql += " and t.comment = :comment";
				params.put("comment", fdMemberAppointmentComment.getComment());
			}		
		}	
		return whereHql;
	}

	@Override
	public void add(FdMemberAppointmentComment fdMemberAppointmentComment) {
		TfdMemberAppointmentComment t = new TfdMemberAppointmentComment();
		BeanUtils.copyProperties(fdMemberAppointmentComment, t);
		//t.setId(jb.absx.UUID.uuid());
		fdMemberAppointmentCommentDao.save(t);
	}

	@Override
	public FdMemberAppointmentComment get(Integer id) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		TfdMemberAppointmentComment t = fdMemberAppointmentCommentDao.get("from TfdMemberAppointmentComment t  where t.id = :id", params);
		FdMemberAppointmentComment o = new FdMemberAppointmentComment();
		BeanUtils.copyProperties(t, o);
		return o;
	}

	@Override
	public void edit(FdMemberAppointmentComment fdMemberAppointmentComment) {
		TfdMemberAppointmentComment t = fdMemberAppointmentCommentDao.get(TfdMemberAppointmentComment.class, fdMemberAppointmentComment.getId());
		if (t != null) {
			MyBeanUtils.copyProperties(fdMemberAppointmentComment, t, new String[] { "id" , "addtime", "isdeleted","updatetime" },true);
		}
	}

	@Override
	public void delete(Integer id) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		fdMemberAppointmentCommentDao.executeHql("update TfdMemberAppointmentComment t set t.isdeleted = 1 where t.id = :id",params);
		//fdMemberAppointmentCommentDao.delete(fdMemberAppointmentCommentDao.get(TfdMemberAppointmentComment.class, id));
	}

}
