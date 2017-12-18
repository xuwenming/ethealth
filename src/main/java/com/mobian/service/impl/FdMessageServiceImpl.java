package com.mobian.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.mobian.absx.F;
import com.mobian.dao.FdMessageDaoI;
import com.mobian.model.TfdMessage;
import com.mobian.pageModel.FdMessage;
import com.mobian.pageModel.DataGrid;
import com.mobian.pageModel.PageHelper;
import com.mobian.service.FdMessageServiceI;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mobian.util.MyBeanUtils;

@Service
public class FdMessageServiceImpl extends BaseServiceImpl<FdMessage> implements FdMessageServiceI {

	@Autowired
	private FdMessageDaoI fdMessageDao;

	@Override
	public DataGrid dataGrid(FdMessage fdMessage, PageHelper ph) {
		List<FdMessage> ol = new ArrayList<FdMessage>();
		String hql = " from TfdMessage t ";
		DataGrid dg = dataGridQuery(hql, ph, fdMessage, fdMessageDao);
		@SuppressWarnings("unchecked")
		List<TfdMessage> l = dg.getRows();
		if (l != null && l.size() > 0) {
			for (TfdMessage t : l) {
				FdMessage o = new FdMessage();
				BeanUtils.copyProperties(t, o);
				ol.add(o);
			}
		}
		dg.setRows(ol);
		return dg;
	}
	

	protected String whereHql(FdMessage fdMessage, Map<String, Object> params) {
		String whereHql = "";	
		if (fdMessage != null) {
			whereHql += " where t.status = 0 ";
			if (!F.empty(fdMessage.getTitle())) {
				whereHql += " and t.title = :title";
				params.put("title", fdMessage.getTitle());
			}		
			if (!F.empty(fdMessage.getContent())) {
				whereHql += " and t.content = :content";
				params.put("content", fdMessage.getContent());
			}		
			if (!F.empty(fdMessage.getCreateBy())) {
				whereHql += " and t.createBy = :createBy";
				params.put("createBy", fdMessage.getCreateBy());
			}		
			if (!F.empty(fdMessage.getCreateTime())) {
				whereHql += " and t.createTime = :createTime";
				params.put("createTime", fdMessage.getCreateTime());
			}		
			if (!F.empty(fdMessage.getUpdateBy())) {
				whereHql += " and t.updateBy = :updateBy";
				params.put("updateBy", fdMessage.getUpdateBy());
			}		
			if (!F.empty(fdMessage.getUpdateTime())) {
				whereHql += " and t.updateTime = :updateTime";
				params.put("updateTime", fdMessage.getUpdateTime());
			}		
			if (!F.empty(fdMessage.getStatus())) {
				whereHql += " and t.status = :status";
				params.put("status", fdMessage.getStatus());
			}		
			if (!F.empty(fdMessage.getUserId())) {
				whereHql += " and t.userId = :userId";
				params.put("userId", fdMessage.getUserId());
			}		
			if (!F.empty(fdMessage.getMtype())) {
				whereHql += " and t.mtype = :mtype";
				params.put("mtype", fdMessage.getMtype());
			}		
			if (!F.empty(fdMessage.getIsRead())) {
				whereHql += " and t.isRead = :isRead";
				params.put("isRead", fdMessage.getIsRead());
			}		
			if (!F.empty(fdMessage.getUrl())) {
				whereHql += " and t.url = :url";
				params.put("url", fdMessage.getUrl());
			}		
		}	
		return whereHql;
	}

	@Override
	public void add(FdMessage fdMessage) {
		TfdMessage t = new TfdMessage();
		BeanUtils.copyProperties(fdMessage, t);
		//t.setId(jb.absx.UUID.uuid());
		fdMessageDao.save(t);
	}

	@Override
	public FdMessage get(Integer id) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		TfdMessage t = fdMessageDao.get("from TfdMessage t  where t.id = :id", params);
		FdMessage o = new FdMessage();
		BeanUtils.copyProperties(t, o);
		return o;
	}

	@Override
	public void edit(FdMessage fdMessage) {
		TfdMessage t = fdMessageDao.get(TfdMessage.class, fdMessage.getId());
		if (t != null) {
			MyBeanUtils.copyProperties(fdMessage, t, new String[] { "id" , "addtime", "isdeleted","updatetime" },true);
		}
	}

	@Override
	public void delete(Integer id) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		fdMessageDao.executeHql("update TfdMessage t set t.isdeleted = 1 where t.id = :id",params);
		//fdMessageDao.delete(fdMessageDao.get(TfdMessage.class, id));
	}

}
