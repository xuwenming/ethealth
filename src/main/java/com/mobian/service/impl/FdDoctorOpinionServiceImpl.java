package com.mobian.service.impl;

import com.mobian.absx.F;
import com.mobian.concurrent.CacheKey;
import com.mobian.concurrent.CompletionService;
import com.mobian.concurrent.Task;
import com.mobian.dao.FdDoctorOpinionDaoI;
import com.mobian.model.TfdDoctorOpinion;
import com.mobian.pageModel.*;
import com.mobian.service.FdCustomerServiceI;
import com.mobian.service.FdDoctorOpinionServiceI;
import com.mobian.service.FdPictureServiceI;
import com.mobian.util.MyBeanUtils;
import com.mobian.util.PathUtil;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class FdDoctorOpinionServiceImpl extends BaseServiceImpl<FdDoctorOpinion> implements FdDoctorOpinionServiceI {

	@Autowired
	private FdDoctorOpinionDaoI fdDoctorOpinionDao;

	@Autowired
	private FdPictureServiceI fdPictureService;

	@Autowired
	private FdCustomerServiceI fdCustomerService;

	@Override
	public DataGrid dataGrid(FdDoctorOpinion fdDoctorOpinion, PageHelper ph) {
		List<FdDoctorOpinion> ol = new ArrayList<FdDoctorOpinion>();
		String hql = " from TfdDoctorOpinion t ";
		DataGrid dg = dataGridQuery(hql, ph, fdDoctorOpinion, fdDoctorOpinionDao);
		@SuppressWarnings("unchecked")
		List<TfdDoctorOpinion> l = dg.getRows();
		if (l != null && l.size() > 0) {
			for (TfdDoctorOpinion t : l) {
				FdDoctorOpinion o = new FdDoctorOpinion();
				BeanUtils.copyProperties(t, o);
				ol.add(o);
			}
		}
		dg.setRows(ol);
		return dg;
	}
	

	protected String whereHql(FdDoctorOpinion fdDoctorOpinion, Map<String, Object> params) {
		String whereHql = "";	
		if (fdDoctorOpinion != null) {
			whereHql += " where t.status = 1 ";
			if (!F.empty(fdDoctorOpinion.getUserId())) {
				whereHql += " and t.userId = :userId";
				params.put("userId", fdDoctorOpinion.getUserId());
			}		
			if (!F.empty(fdDoctorOpinion.getTitle())) {
				whereHql += " and t.title = :title";
				params.put("title", fdDoctorOpinion.getTitle());
			}		
			if (!F.empty(fdDoctorOpinion.getView())) {
				whereHql += " and t.view = :view";
				params.put("view", fdDoctorOpinion.getView());
			}		
			if (!F.empty(fdDoctorOpinion.getComment())) {
				whereHql += " and t.comment = :comment";
				params.put("comment", fdDoctorOpinion.getComment());
			}		
			if (!F.empty(fdDoctorOpinion.getCreateTime())) {
				whereHql += " and t.createTime = :createTime";
				params.put("createTime", fdDoctorOpinion.getCreateTime());
			}		
			if (!F.empty(fdDoctorOpinion.getUpdateTime())) {
				whereHql += " and t.updateTime = :updateTime";
				params.put("updateTime", fdDoctorOpinion.getUpdateTime());
			}		
			if (!F.empty(fdDoctorOpinion.getVerifyStatus())) {
				whereHql += " and t.verifyStatus = :verifyStatus";
				params.put("verifyStatus", fdDoctorOpinion.getVerifyStatus());
			}		
			if (!F.empty(fdDoctorOpinion.getIsUp())) {
				whereHql += " and t.isUp = :isUp";
				params.put("isUp", fdDoctorOpinion.getIsUp());
			}		
			if (!F.empty(fdDoctorOpinion.getStatus())) {
				whereHql += " and t.status = :status";
				params.put("status", fdDoctorOpinion.getStatus());
			}		
			if (!F.empty(fdDoctorOpinion.getPic())) {
				whereHql += " and t.pic = :pic";
				params.put("pic", fdDoctorOpinion.getPic());
			}		
			if (!F.empty(fdDoctorOpinion.getContent())) {
				whereHql += " and t.content = :content";
				params.put("content", fdDoctorOpinion.getContent());
			}		
			if (!F.empty(fdDoctorOpinion.getBrief())) {
				whereHql += " and t.brief = :brief";
				params.put("brief", fdDoctorOpinion.getBrief());
			}		
			if (!F.empty(fdDoctorOpinion.getFile())) {
				whereHql += " and t.file = :file";
				params.put("file", fdDoctorOpinion.getFile());
			}

			if(!F.empty(fdDoctorOpinion.getKey())) {
				whereHql += " and (t.title like :key or t.content like :key)";
				params.put("key", "%" + fdDoctorOpinion.getKey() + "%");
			}
		}	
		return whereHql;
	}

	@Override
	public void add(FdDoctorOpinion fdDoctorOpinion) {
		TfdDoctorOpinion t = new TfdDoctorOpinion();
		BeanUtils.copyProperties(fdDoctorOpinion, t);
		//t.setId(jb.absx.UUID.uuid());
		fdDoctorOpinionDao.save(t);
	}

	@Override
	public FdDoctorOpinion get(Integer id) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		TfdDoctorOpinion t = fdDoctorOpinionDao.get("from TfdDoctorOpinion t  where t.id = :id", params);
		FdDoctorOpinion o = new FdDoctorOpinion();
		BeanUtils.copyProperties(t, o);
		return o;
	}

	@Override
	public void edit(FdDoctorOpinion fdDoctorOpinion) {
		TfdDoctorOpinion t = fdDoctorOpinionDao.get(TfdDoctorOpinion.class, fdDoctorOpinion.getId());
		if (t != null) {
			MyBeanUtils.copyProperties(fdDoctorOpinion, t, new String[] { "id" , "addtime", "isdeleted","updatetime" },true);
		}
	}

	@Override
	public void delete(Integer id) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		fdDoctorOpinionDao.executeHql("update TfdDoctorOpinion t set t.isdeleted = 1 where t.id = :id",params);
		//fdDoctorOpinionDao.delete(fdDoctorOpinionDao.get(TfdDoctorOpinion.class, id));
	}

	@Override
	public DataGrid dataGridComplex(FdDoctorOpinion doctorOpinion, PageHelper ph) {
		if(ph.getRows() == 0 || ph.getRows() > 50) {
			ph.setRows(10);
		}
		if(F.empty(ph.getSort())) {
			ph.setSort("createTime");
		}
		if(F.empty(ph.getOrder())) {
			ph.setOrder("desc");
		}

		if(F.empty(doctorOpinion.getIsUp())) doctorOpinion.setIsUp(1); // 上架
		if(F.empty(doctorOpinion.getVerifyStatus())) doctorOpinion.setVerifyStatus(1); // 审核通过
		DataGrid dg = dataGrid(doctorOpinion, ph);
		List<FdDoctorOpinion> ol = dg.getRows();
		if(CollectionUtils.isNotEmpty(ol)) {
			CompletionService completionService = CompletionFactory.initCompletion();
			for (FdDoctorOpinion o : ol) {
				o.setContent(null); // 内容不返回，返回url
				if(!F.empty(o.getPic()))
					completionService.submit(new Task<FdDoctorOpinion, String>(new CacheKey("fdPic", o.getPic()), o) {
						@Override
						public String call() throws Exception {
							FdPicture pic = fdPictureService.get(Integer.valueOf(getD().getPic()));
							if(pic != null) return PathUtil.getPicPath(pic.getPath());
							return null;
						}

						protected void set(FdDoctorOpinion d, String v) {
							if(!F.empty(v)) {
								d.setPicUrl(v);
							}
						}
					});
				if(!F.empty(o.getUserId()))
					completionService.submit(new Task<FdDoctorOpinion, String>(new CacheKey("fdCustomer", o.getUserId() + ""), o) {
						@Override
						public String call() throws Exception {
							FdCustomer customer = fdCustomerService.get(getD().getUserId().longValue());
							if(customer != null) return customer.getRealName();
							return null;
						}

						protected void set(FdDoctorOpinion d, String v) {
							if(!F.empty(v)) {
								d.setUserName(v);
							}
						}
					});
			}
			completionService.sync();
		}
		return dg;
	}

}
