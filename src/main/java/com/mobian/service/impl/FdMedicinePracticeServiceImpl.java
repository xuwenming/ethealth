package com.mobian.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.mobian.absx.F;
import com.mobian.concurrent.CacheKey;
import com.mobian.concurrent.CompletionService;
import com.mobian.concurrent.Task;
import com.mobian.dao.FdMedicinePracticeDaoI;
import com.mobian.model.TfdMedicinePractice;
import com.mobian.pageModel.FdMedicinePractice;
import com.mobian.pageModel.DataGrid;
import com.mobian.pageModel.FdPicture;
import com.mobian.pageModel.PageHelper;
import com.mobian.service.FdMedicinePracticeServiceI;

import com.mobian.service.FdPictureServiceI;
import com.mobian.util.PathUtil;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mobian.util.MyBeanUtils;

@Service
public class FdMedicinePracticeServiceImpl extends BaseServiceImpl<FdMedicinePractice> implements FdMedicinePracticeServiceI {

	@Autowired
	private FdMedicinePracticeDaoI fdMedicinePracticeDao;

	@Autowired
	private FdPictureServiceI fdPictureService;

	@Override
	public DataGrid dataGrid(FdMedicinePractice fdMedicinePractice, PageHelper ph) {
		List<FdMedicinePractice> ol = new ArrayList<FdMedicinePractice>();
		String hql = " from TfdMedicinePractice t ";
		DataGrid dg = dataGridQuery(hql, ph, fdMedicinePractice, fdMedicinePracticeDao);
		@SuppressWarnings("unchecked")
		List<TfdMedicinePractice> l = dg.getRows();
		if (l != null && l.size() > 0) {
			for (TfdMedicinePractice t : l) {
				FdMedicinePractice o = new FdMedicinePractice();
				BeanUtils.copyProperties(t, o);
				ol.add(o);
			}
		}
		dg.setRows(ol);
		return dg;
	}
	

	protected String whereHql(FdMedicinePractice fdMedicinePractice, Map<String, Object> params) {
		String whereHql = "";	
		if (fdMedicinePractice != null) {
			whereHql += " where t.status = 1 ";
			if (!F.empty(fdMedicinePractice.getTitle())) {
				whereHql += " and t.title = :title";
				params.put("title", fdMedicinePractice.getTitle());
			}		
			if (!F.empty(fdMedicinePractice.getView())) {
				whereHql += " and t.view = :view";
				params.put("view", fdMedicinePractice.getView());
			}		
			if (!F.empty(fdMedicinePractice.getComment())) {
				whereHql += " and t.comment = :comment";
				params.put("comment", fdMedicinePractice.getComment());
			}		
			if (!F.empty(fdMedicinePractice.getCreateTime())) {
				whereHql += " and t.createTime = :createTime";
				params.put("createTime", fdMedicinePractice.getCreateTime());
			}		
			if (!F.empty(fdMedicinePractice.getUpdateTime())) {
				whereHql += " and t.updateTime = :updateTime";
				params.put("updateTime", fdMedicinePractice.getUpdateTime());
			}		
			if (!F.empty(fdMedicinePractice.getIsUp())) {
				whereHql += " and t.isUp = :isUp";
				params.put("isUp", fdMedicinePractice.getIsUp());
			}		
			if (!F.empty(fdMedicinePractice.getStatus())) {
				whereHql += " and t.status = :status";
				params.put("status", fdMedicinePractice.getStatus());
			}		
			if (!F.empty(fdMedicinePractice.getPic())) {
				whereHql += " and t.pic = :pic";
				params.put("pic", fdMedicinePractice.getPic());
			}		
			if (!F.empty(fdMedicinePractice.getContent())) {
				whereHql += " and t.content = :content";
				params.put("content", fdMedicinePractice.getContent());
			}		
			if (!F.empty(fdMedicinePractice.getDesc())) {
				whereHql += " and t.desc = :desc";
				params.put("desc", fdMedicinePractice.getDesc());
			}		
			if (!F.empty(fdMedicinePractice.getFile())) {
				whereHql += " and t.file = :file";
				params.put("file", fdMedicinePractice.getFile());
			}
			if(!F.empty(fdMedicinePractice.getKey())) {
				whereHql += " and (t.title like :key or t.desc like :key or t.content like :key)";
				params.put("key", "%" + fdMedicinePractice.getKey() + "%");
			}
		}	
		return whereHql;
	}

	@Override
	public void add(FdMedicinePractice fdMedicinePractice) {
		TfdMedicinePractice t = new TfdMedicinePractice();
		BeanUtils.copyProperties(fdMedicinePractice, t);
		//t.setId(jb.absx.UUID.uuid());
//		t.setIsdeleted(false);
		fdMedicinePracticeDao.save(t);
	}

	@Override
	public FdMedicinePractice get(Integer id) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		TfdMedicinePractice t = fdMedicinePracticeDao.get("from TfdMedicinePractice t  where t.id = :id", params);
		FdMedicinePractice o = new FdMedicinePractice();
		BeanUtils.copyProperties(t, o);
		return o;
	}

	@Override
	public void edit(FdMedicinePractice fdMedicinePractice) {
		TfdMedicinePractice t = fdMedicinePracticeDao.get(TfdMedicinePractice.class, fdMedicinePractice.getId());
		if (t != null) {
			MyBeanUtils.copyProperties(fdMedicinePractice, t, new String[] { "id" , "addtime", "isdeleted","updatetime" },true);
		}
	}

	@Override
	public void delete(Integer id) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		fdMedicinePracticeDao.executeHql("update TfdMedicinePractice t set t.isdeleted = 1 where t.id = :id",params);
		//fdMedicinePracticeDao.delete(fdMedicinePracticeDao.get(TfdMedicinePractice.class, id));
	}

	@Override
	public DataGrid dataGridComplex(FdMedicinePractice medicinePractice, PageHelper ph) {
		if(ph.getRows() == 0 || ph.getRows() > 50) {
			ph.setRows(10);
		}
		if(F.empty(ph.getSort())) {
			ph.setSort("createTime");
		}
		if(F.empty(ph.getOrder())) {
			ph.setOrder("desc");
		}
		if(F.empty(medicinePractice.getIsUp())) medicinePractice.setIsUp(1); // 上架
		DataGrid dg = dataGrid(medicinePractice, ph);
		List<FdMedicinePractice> ol = dg.getRows();
		if(CollectionUtils.isNotEmpty(ol)) {
			CompletionService completionService = CompletionFactory.initCompletion();
			for (FdMedicinePractice o : ol) {
				o.setContent(null); // 内容不返回，返回url
				if(!F.empty(o.getPic()))
					completionService.submit(new Task<FdMedicinePractice, String>(new CacheKey("fdPic", o.getPic()), o) {
						@Override
						public String call() throws Exception {
							FdPicture pic = fdPictureService.get(Integer.valueOf(getD().getPic()));
							if(pic != null) return PathUtil.getPicPath(pic.getPath());
							return null;
						}

						protected void set(FdMedicinePractice d, String v) {
							if(!F.empty(v)) {
								d.setPicUrl(v);
							}
						}
					});
			}
			completionService.sync();
		}
		return dg;
	}

}
