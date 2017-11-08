package com.mobian.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.mobian.absx.F;
import com.mobian.dao.FdBannerDaoI;
import com.mobian.model.TfdBanner;
import com.mobian.pageModel.FdBanner;
import com.mobian.pageModel.DataGrid;
import com.mobian.pageModel.PageHelper;
import com.mobian.service.FdBannerServiceI;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mobian.util.MyBeanUtils;

@Service
public class FdBannerServiceImpl extends BaseServiceImpl<FdBanner> implements FdBannerServiceI {

	@Autowired
	private FdBannerDaoI fdBannerDao;

	@Override
	public DataGrid dataGrid(FdBanner fdBanner, PageHelper ph) {
		List<FdBanner> ol = new ArrayList<FdBanner>();
		String hql = " from TfdBanner t ";
		DataGrid dg = dataGridQuery(hql, ph, fdBanner, fdBannerDao);
		@SuppressWarnings("unchecked")
		List<TfdBanner> l = dg.getRows();
		if (l != null && l.size() > 0) {
			for (TfdBanner t : l) {
				FdBanner o = new FdBanner();
				BeanUtils.copyProperties(t, o);
				ol.add(o);
			}
		}
		dg.setRows(ol);
		return dg;
	}
	

	protected String whereHql(FdBanner fdBanner, Map<String, Object> params) {
		String whereHql = "";	
		if (fdBanner != null) {
			whereHql += " where t.status = 0 ";
			if (!F.empty(fdBanner.getTitle())) {
				whereHql += " and t.title = :title";
				params.put("title", fdBanner.getTitle());
			}		
			if (!F.empty(fdBanner.getLink())) {
				whereHql += " and t.link = :link";
				params.put("link", fdBanner.getLink());
			}		
			if (!F.empty(fdBanner.getNum())) {
				whereHql += " and t.num = :num";
				params.put("num", fdBanner.getNum());
			}		
			if (!F.empty(fdBanner.getPic())) {
				whereHql += " and t.pic = :pic";
				params.put("pic", fdBanner.getPic());
			}		
			if (!F.empty(fdBanner.getCreateBy())) {
				whereHql += " and t.createBy = :createBy";
				params.put("createBy", fdBanner.getCreateBy());
			}		
			if (!F.empty(fdBanner.getCreateTime())) {
				whereHql += " and t.createTime = :createTime";
				params.put("createTime", fdBanner.getCreateTime());
			}		
			if (!F.empty(fdBanner.getUpdateBy())) {
				whereHql += " and t.updateBy = :updateBy";
				params.put("updateBy", fdBanner.getUpdateBy());
			}		
			if (!F.empty(fdBanner.getUpdateTime())) {
				whereHql += " and t.updateTime = :updateTime";
				params.put("updateTime", fdBanner.getUpdateTime());
			}		
			if (!F.empty(fdBanner.getStatus())) {
				whereHql += " and t.status = :status";
				params.put("status", fdBanner.getStatus());
			}		
			if (!F.empty(fdBanner.getSource())) {
				whereHql += " and t.source = :source";
				params.put("source", fdBanner.getSource());
			}		
		}	
		return whereHql;
	}

	@Override
	public void add(FdBanner fdBanner) {
		TfdBanner t = new TfdBanner();
		BeanUtils.copyProperties(fdBanner, t);
		//t.setId(jb.absx.UUID.uuid());
		fdBannerDao.save(t);
	}

	@Override
	public FdBanner get(Integer id) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		TfdBanner t = fdBannerDao.get("from TfdBanner t  where t.id = :id", params);
		FdBanner o = new FdBanner();
		BeanUtils.copyProperties(t, o);
		return o;
	}

	@Override
	public void edit(FdBanner fdBanner) {
		TfdBanner t = fdBannerDao.get(TfdBanner.class, fdBanner.getId());
		if (t != null) {
			MyBeanUtils.copyProperties(fdBanner, t, new String[] { "id" , "addtime", "isdeleted","updatetime" },true);
		}
	}

	@Override
	public void delete(Integer id) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		fdBannerDao.executeHql("update TfdBanner t set t.isdeleted = 1 where t.id = :id",params);
		//fdBannerDao.delete(fdBannerDao.get(TfdBanner.class, id));
	}

}
