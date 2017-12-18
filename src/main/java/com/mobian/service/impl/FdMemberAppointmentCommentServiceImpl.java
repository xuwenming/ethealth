package com.mobian.service.impl;

import com.mobian.absx.F;
import com.mobian.concurrent.CacheKey;
import com.mobian.concurrent.CompletionService;
import com.mobian.concurrent.Task;
import com.mobian.dao.FdMemberAppointmentCommentDaoI;
import com.mobian.model.TfdMemberAppointmentComment;
import com.mobian.pageModel.DataGrid;
import com.mobian.pageModel.FdCustomer;
import com.mobian.pageModel.FdMemberAppointmentComment;
import com.mobian.pageModel.PageHelper;
import com.mobian.service.FdCustomerServiceI;
import com.mobian.service.FdMemberAppointmentCommentServiceI;
import com.mobian.util.MyBeanUtils;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class FdMemberAppointmentCommentServiceImpl extends BaseServiceImpl<FdMemberAppointmentComment> implements FdMemberAppointmentCommentServiceI {

	@Autowired
	private FdMemberAppointmentCommentDaoI fdMemberAppointmentCommentDao;

	@Autowired
	private FdCustomerServiceI fdCustomerService;

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
		if(F.empty(fdMemberAppointmentComment.getStatus())) t.setStatus("0");
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

	@Override
	public List<FdMemberAppointmentComment> query(FdMemberAppointmentComment comment) {
		List<FdMemberAppointmentComment> ol = new ArrayList<FdMemberAppointmentComment>();
		String hql = " from TfdMemberAppointmentComment t ";
		Map<String, Object> params = new HashMap<String, Object>();
		String where = whereHql(comment, params);
		List<TfdMemberAppointmentComment> l = fdMemberAppointmentCommentDao.find(hql + where, params);
		if (l != null && l.size() > 0) {
			for (TfdMemberAppointmentComment t : l) {
				FdMemberAppointmentComment o = new FdMemberAppointmentComment();
				BeanUtils.copyProperties(t, o);
				ol.add(o);
			}
		}
		return ol;
	}

	@Override
	public Object dataGridComplex(FdMemberAppointmentComment comment, PageHelper ph) {
		DataGrid dg = dataGrid(comment, ph);
		List<FdMemberAppointmentComment> list = dg.getRows();
		if(CollectionUtils.isNotEmpty(list)) {
			CompletionService completionService = CompletionFactory.initCompletion();
			for(FdMemberAppointmentComment o : list) {

				completionService.submit(new Task<FdMemberAppointmentComment, String>(new CacheKey("fdCustomer", o.getUserId() + ""), o) {
					@Override
					public String call() throws Exception {
						String str = "";
						FdCustomer customer = fdCustomerService.get(Long.valueOf(getD().getUserId()));
						if(customer != null) {
							if(!F.empty(customer.getRealName())) {
								if(!F.empty(getD().getIsAdmin()) && getD().getIsAdmin() == 2) {
									str = customer.getRealName();
								} else {
									str = customer.getRealName().substring(0, 1) + "**";
								}
							} else {
								if(!F.empty(getD().getIsAdmin()) && getD().getIsAdmin() == 2) {
									str = customer.getPhone();
								} else {
									str = customer.getPhone().substring(0, 3) + "****" + customer.getPhone().substring(customer.getPhone().length() - 4);
								}
							}
						}
						return str;
					}

					protected void set(FdMemberAppointmentComment d, String v) {
						d.setUserName(v);
					}
				});

			}
			completionService.sync();
		}

		return dg;
	}

}
