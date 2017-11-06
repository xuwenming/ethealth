package com.mobian.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.mobian.absx.F;
import com.mobian.dao.FdPictureDaoI;
import com.mobian.model.TfdPicture;
import com.mobian.pageModel.FdPicture;
import com.mobian.pageModel.DataGrid;
import com.mobian.pageModel.PageHelper;
import com.mobian.service.FdPictureServiceI;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mobian.util.MyBeanUtils;

@Service
public class FdPictureServiceImpl extends BaseServiceImpl<FdPicture> implements FdPictureServiceI {

	@Autowired
	private FdPictureDaoI fdPictureDao;

	@Override
	public DataGrid dataGrid(FdPicture fdPicture, PageHelper ph) {
		List<FdPicture> ol = new ArrayList<FdPicture>();
		String hql = " from TfdPicture t ";
		DataGrid dg = dataGridQuery(hql, ph, fdPicture, fdPictureDao);
		@SuppressWarnings("unchecked")
		List<TfdPicture> l = dg.getRows();
		if (l != null && l.size() > 0) {
			for (TfdPicture t : l) {
				FdPicture o = new FdPicture();
				BeanUtils.copyProperties(t, o);
				ol.add(o);
			}
		}
		dg.setRows(ol);
		return dg;
	}
	

	protected String whereHql(FdPicture fdPicture, Map<String, Object> params) {
		String whereHql = "";	
		if (fdPicture != null) {
			whereHql += " where t.status = 1 ";
			if (!F.empty(fdPicture.getPath())) {
				whereHql += " and t.path = :path";
				params.put("path", fdPicture.getPath());
			}		
			if (!F.empty(fdPicture.getUrl())) {
				whereHql += " and t.url = :url";
				params.put("url", fdPicture.getUrl());
			}		
			if (!F.empty(fdPicture.getMd5())) {
				whereHql += " and t.md5 = :md5";
				params.put("md5", fdPicture.getMd5());
			}		
			if (!F.empty(fdPicture.getSha1())) {
				whereHql += " and t.sha1 = :sha1";
				params.put("sha1", fdPicture.getSha1());
			}		
			if (!F.empty(fdPicture.getStatus())) {
				whereHql += " and t.status = :status";
				params.put("status", fdPicture.getStatus());
			}		
			if (!F.empty(fdPicture.getCreateTime())) {
				whereHql += " and t.createTime = :createTime";
				params.put("createTime", fdPicture.getCreateTime());
			}		
			if (!F.empty(fdPicture.getType())) {
				whereHql += " and t.type = :type";
				params.put("type", fdPicture.getType());
			}		
			if (!F.empty(fdPicture.getSourceId())) {
				whereHql += " and t.sourceId = :sourceId";
				params.put("sourceId", fdPicture.getSourceId());
			}		
		}	
		return whereHql;
	}

	@Override
	public void add(FdPicture fdPicture) {
		TfdPicture t = new TfdPicture();
		BeanUtils.copyProperties(fdPicture, t);
		//t.setId(jb.absx.UUID.uuid());
		fdPictureDao.save(t);
	}

	@Override
	public FdPicture get(Integer id) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		TfdPicture t = fdPictureDao.get("from TfdPicture t  where t.id = :id", params);
		FdPicture o = new FdPicture();
		BeanUtils.copyProperties(t, o);
		return o;
	}

	@Override
	public void edit(FdPicture fdPicture) {
		TfdPicture t = fdPictureDao.get(TfdPicture.class, fdPicture.getId());
		if (t != null) {
			MyBeanUtils.copyProperties(fdPicture, t, new String[] { "id" , "addtime", "isdeleted","updatetime" },true);
		}
	}

	@Override
	public void delete(Integer id) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		fdPictureDao.executeHql("update TfdPicture t set t.isdeleted = 1 where t.id = :id",params);
		//fdPictureDao.delete(fdPictureDao.get(TfdPicture.class, id));
	}

}
