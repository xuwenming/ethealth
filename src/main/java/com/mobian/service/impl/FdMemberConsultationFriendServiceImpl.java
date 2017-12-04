package com.mobian.service.impl;

import com.mobian.absx.F;
import com.mobian.concurrent.CompletionService;
import com.mobian.concurrent.Task;
import com.mobian.dao.FdMemberConsultationFriendDaoI;
import com.mobian.model.TfdMemberConsultationFriend;
import com.mobian.pageModel.DataGrid;
import com.mobian.pageModel.FdMember;
import com.mobian.pageModel.FdMemberConsultationFriend;
import com.mobian.pageModel.PageHelper;
import com.mobian.service.FdMemberConsultationFriendServiceI;
import com.mobian.service.FdMemberServiceI;
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
public class FdMemberConsultationFriendServiceImpl extends BaseServiceImpl<FdMemberConsultationFriend> implements FdMemberConsultationFriendServiceI {

	@Autowired
	private FdMemberConsultationFriendDaoI fdMemberConsultationFriendDao;


	@Autowired
	private FdMemberServiceI fdMemberService;

	@Override
	public DataGrid dataGrid(FdMemberConsultationFriend fdMemberConsultationFriend, PageHelper ph) {
		List<FdMemberConsultationFriend> ol = new ArrayList<FdMemberConsultationFriend>();
		String hql = " from TfdMemberConsultationFriend t ";
		DataGrid dg = dataGridQuery(hql, ph, fdMemberConsultationFriend, fdMemberConsultationFriendDao);
		@SuppressWarnings("unchecked")
		List<TfdMemberConsultationFriend> l = dg.getRows();
		if (l != null && l.size() > 0) {
			for (TfdMemberConsultationFriend t : l) {
				FdMemberConsultationFriend o = new FdMemberConsultationFriend();
				BeanUtils.copyProperties(t, o);
				ol.add(o);
			}
		}
		dg.setRows(ol);
		return dg;
	}
	

	protected String whereHql(FdMemberConsultationFriend fdMemberConsultationFriend, Map<String, Object> params) {
		String whereHql = "";	
		if (fdMemberConsultationFriend != null) {
			whereHql += " where t.status = 0 ";
			if (!F.empty(fdMemberConsultationFriend.getUserId())) {
				whereHql += " and t.userId = :userId";
				params.put("userId", fdMemberConsultationFriend.getUserId());
			}		
			if (!F.empty(fdMemberConsultationFriend.getDoctorId())) {
				whereHql += " and t.doctorId = :doctorId";
				params.put("doctorId", fdMemberConsultationFriend.getDoctorId());
			}		
			if (!F.empty(fdMemberConsultationFriend.getCreateTime())) {
				whereHql += " and t.createTime = :createTime";
				params.put("createTime", fdMemberConsultationFriend.getCreateTime());
			}		
			if (!F.empty(fdMemberConsultationFriend.getStatus())) {
				whereHql += " and t.status = :status";
				params.put("status", fdMemberConsultationFriend.getStatus());
			}		
			if (!F.empty(fdMemberConsultationFriend.getLastContent())) {
				whereHql += " and t.lastContent = :lastContent";
				params.put("lastContent", fdMemberConsultationFriend.getLastContent());
			}		
		}	
		return whereHql;
	}

	@Override
	public void add(FdMemberConsultationFriend fdMemberConsultationFriend) {
		TfdMemberConsultationFriend t = new TfdMemberConsultationFriend();
		BeanUtils.copyProperties(fdMemberConsultationFriend, t);
		//t.setId(jb.absx.UUID.uuid());
		fdMemberConsultationFriendDao.save(t);
	}

	@Override
	public FdMemberConsultationFriend get(Integer id) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		TfdMemberConsultationFriend t = fdMemberConsultationFriendDao.get("from TfdMemberConsultationFriend t  where t.id = :id", params);
		FdMemberConsultationFriend o = new FdMemberConsultationFriend();
		BeanUtils.copyProperties(t, o);
		return o;
	}

	@Override
	public void edit(FdMemberConsultationFriend fdMemberConsultationFriend) {
		TfdMemberConsultationFriend t = fdMemberConsultationFriendDao.get(TfdMemberConsultationFriend.class, fdMemberConsultationFriend.getId());
		if (t != null) {
			MyBeanUtils.copyProperties(fdMemberConsultationFriend, t, new String[] { "id" , "addtime", "isdeleted","updatetime" },true);
		}
	}

	@Override
	public void delete(Integer id) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		fdMemberConsultationFriendDao.executeHql("update TfdMemberConsultationFriend t set t.isdeleted = 1 where t.id = :id",params);
		//fdMemberConsultationFriendDao.delete(fdMemberConsultationFriendDao.get(TfdMemberConsultationFriend.class, id));
	}

	@Override
	public FdMemberConsultationFriend getByUserIdAndDoctorId(Integer userId, Integer doctorId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userId", userId);
		params.put("doctorId", doctorId);
		TfdMemberConsultationFriend t = fdMemberConsultationFriendDao.get("from TfdMemberConsultationFriend t  where t.userId = :userId and t.doctorId = :doctorId ", params);
		if(t != null) {
			FdMemberConsultationFriend o = new FdMemberConsultationFriend();
			BeanUtils.copyProperties(t, o);
			return o;
		}
		return null;
	}

	@Override
	public DataGrid dataGridComplex(FdMemberConsultationFriend consultationFriend, PageHelper ph) {
		DataGrid dg = dataGrid(consultationFriend, ph);
		List<FdMemberConsultationFriend> list = dg.getRows();
		if(CollectionUtils.isNotEmpty(list)) {
			CompletionService completionService = CompletionFactory.initCompletion();
			for(FdMemberConsultationFriend o : list) {
				if(consultationFriend.getIsAdmin() == 0)
					completionService.submit(new Task<FdMemberConsultationFriend, FdMember>(o) {
						@Override
						public FdMember call() throws Exception {
							return fdMemberService.getDetail(getD().getDoctorId());
						}

						protected void set(FdMemberConsultationFriend d, FdMember v) {
							d.setMember(v);
						}
					});
				else
					completionService.submit(new Task<FdMemberConsultationFriend, FdMember>(o) {
						@Override
						public FdMember call() throws Exception {
							return fdMemberService.getDetail(getD().getUserId());
						}

						protected void set(FdMemberConsultationFriend d, FdMember v) {
							d.setMember(v);
						}
					});

			}
			completionService.sync();
		}

		return dg;
	}

}
