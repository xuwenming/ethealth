package com.mobian.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.mobian.absx.F;
import com.mobian.dao.FdFeedbackDaoI;
import com.mobian.model.TfdFeedback;
import com.mobian.pageModel.FdFeedback;
import com.mobian.pageModel.DataGrid;
import com.mobian.pageModel.PageHelper;
import com.mobian.service.FdFeedbackServiceI;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mobian.util.MyBeanUtils;

@Service
public class FdFeedbackServiceImpl extends BaseServiceImpl<FdFeedback> implements FdFeedbackServiceI {

	@Autowired
	private FdFeedbackDaoI fdFeedbackDao;

	@Override
	public DataGrid dataGrid(FdFeedback fdFeedback, PageHelper ph) {
		List<FdFeedback> ol = new ArrayList<FdFeedback>();
		String hql = " from TfdFeedback t ";
		DataGrid dg = dataGridQuery(hql, ph, fdFeedback, fdFeedbackDao);
		@SuppressWarnings("unchecked")
		List<TfdFeedback> l = dg.getRows();
		if (l != null && l.size() > 0) {
			for (TfdFeedback t : l) {
				FdFeedback o = new FdFeedback();
				BeanUtils.copyProperties(t, o);
				ol.add(o);
			}
		}
		dg.setRows(ol);
		return dg;
	}
	

	protected String whereHql(FdFeedback fdFeedback, Map<String, Object> params) {
		String whereHql = "";	
		if (fdFeedback != null) {
			whereHql += " where t.status = 0 ";
			if (!F.empty(fdFeedback.getContactWay())) {
				whereHql += " and t.contactWay = :contactWay";
				params.put("contactWay", fdFeedback.getContactWay());
			}		
			if (!F.empty(fdFeedback.getContent())) {
				whereHql += " and t.content = :content";
				params.put("content", fdFeedback.getContent());
			}		
			if (!F.empty(fdFeedback.getCreateBy())) {
				whereHql += " and t.createBy = :createBy";
				params.put("createBy", fdFeedback.getCreateBy());
			}		
			if (!F.empty(fdFeedback.getCreateTime())) {
				whereHql += " and t.createTime = :createTime";
				params.put("createTime", fdFeedback.getCreateTime());
			}		
			if (!F.empty(fdFeedback.getUpdateBy())) {
				whereHql += " and t.updateBy = :updateBy";
				params.put("updateBy", fdFeedback.getUpdateBy());
			}		
			if (!F.empty(fdFeedback.getUpdateTime())) {
				whereHql += " and t.updateTime = :updateTime";
				params.put("updateTime", fdFeedback.getUpdateTime());
			}		
			if (!F.empty(fdFeedback.getStatus())) {
				whereHql += " and t.status = :status";
				params.put("status", fdFeedback.getStatus());
			}		
		}	
		return whereHql;
	}

	@Override
	public void add(FdFeedback fdFeedback) {
		TfdFeedback t = new TfdFeedback();
		BeanUtils.copyProperties(fdFeedback, t);
		if(F.empty(t.getStatus())) t.setStatus("0");
		fdFeedbackDao.save(t);
	}

	@Override
	public FdFeedback get(Integer id) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		TfdFeedback t = fdFeedbackDao.get("from TfdFeedback t  where t.id = :id", params);
		FdFeedback o = new FdFeedback();
		BeanUtils.copyProperties(t, o);
		return o;
	}

	@Override
	public void edit(FdFeedback fdFeedback) {
		TfdFeedback t = fdFeedbackDao.get(TfdFeedback.class, fdFeedback.getId());
		if (t != null) {
			MyBeanUtils.copyProperties(fdFeedback, t, new String[] { "id" , "addtime", "isdeleted","updatetime" },true);
		}
	}

	@Override
	public void delete(Integer id) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		fdFeedbackDao.executeHql("update TfdFeedback t set t.isdeleted = 1 where t.id = :id",params);
		//fdFeedbackDao.delete(fdFeedbackDao.get(TfdFeedback.class, id));
	}

}
