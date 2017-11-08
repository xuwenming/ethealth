package com.mobian.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.mobian.absx.F;
import com.mobian.dao.FdDoctorOpinionDaoI;
import com.mobian.model.TfdDoctorOpinion;
import com.mobian.pageModel.FdDoctorOpinion;
import com.mobian.pageModel.DataGrid;
import com.mobian.pageModel.PageHelper;
import com.mobian.service.FdDoctorOpinionServiceI;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mobian.util.MyBeanUtils;

@Service
public class FdDoctorOpinionServiceImpl extends BaseServiceImpl<FdDoctorOpinion> implements FdDoctorOpinionServiceI {

	@Autowired
	private FdDoctorOpinionDaoI fdDoctorOpinionDao;

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

}
