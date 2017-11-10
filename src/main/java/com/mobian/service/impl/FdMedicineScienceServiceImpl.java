package com.mobian.service.impl;

import com.mobian.absx.F;
import com.mobian.concurrent.CacheKey;
import com.mobian.concurrent.CompletionService;
import com.mobian.concurrent.Task;
import com.mobian.dao.FdMedicineScienceDaoI;
import com.mobian.model.TfdMedicineScience;
import com.mobian.pageModel.DataGrid;
import com.mobian.pageModel.FdMedicineScience;
import com.mobian.pageModel.FdPicture;
import com.mobian.pageModel.PageHelper;
import com.mobian.service.FdMedicineScienceServiceI;
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
public class FdMedicineScienceServiceImpl extends BaseServiceImpl<FdMedicineScience> implements FdMedicineScienceServiceI {

	@Autowired
	private FdMedicineScienceDaoI fdMedicineScienceDao;

	@Autowired
	private FdPictureServiceI fdPictureService;

	@Override
	public DataGrid dataGrid(FdMedicineScience fdMedicineScience, PageHelper ph) {
		List<FdMedicineScience> ol = new ArrayList<FdMedicineScience>();
		String hql = " from TfdMedicineScience t ";
		DataGrid dg = dataGridQuery(hql, ph, fdMedicineScience, fdMedicineScienceDao);
		@SuppressWarnings("unchecked")
		List<TfdMedicineScience> l = dg.getRows();
		if (l != null && l.size() > 0) {
			for (TfdMedicineScience t : l) {
				FdMedicineScience o = new FdMedicineScience();
				BeanUtils.copyProperties(t, o);
				ol.add(o);
			}
		}
		dg.setRows(ol);
		return dg;
	}
	

	protected String whereHql(FdMedicineScience fdMedicineScience, Map<String, Object> params) {
		String whereHql = "";	
		if (fdMedicineScience != null) {
			whereHql += " where t.status = 1 ";
			if (!F.empty(fdMedicineScience.getTitle())) {
				whereHql += " and t.title = :title";
				params.put("title", fdMedicineScience.getTitle());
			}		
			if (!F.empty(fdMedicineScience.getView())) {
				whereHql += " and t.view = :view";
				params.put("view", fdMedicineScience.getView());
			}		
			if (!F.empty(fdMedicineScience.getComment())) {
				whereHql += " and t.comment = :comment";
				params.put("comment", fdMedicineScience.getComment());
			}		
			if (!F.empty(fdMedicineScience.getCreateTime())) {
				whereHql += " and t.createTime = :createTime";
				params.put("createTime", fdMedicineScience.getCreateTime());
			}		
			if (!F.empty(fdMedicineScience.getUpdateTime())) {
				whereHql += " and t.updateTime = :updateTime";
				params.put("updateTime", fdMedicineScience.getUpdateTime());
			}		
			if (!F.empty(fdMedicineScience.getIsUp())) {
				whereHql += " and t.isUp = :isUp";
				params.put("isUp", fdMedicineScience.getIsUp());
			}		
			if (!F.empty(fdMedicineScience.getStatus())) {
				whereHql += " and t.status = :status";
				params.put("status", fdMedicineScience.getStatus());
			}		
			if (!F.empty(fdMedicineScience.getPic())) {
				whereHql += " and t.pic = :pic";
				params.put("pic", fdMedicineScience.getPic());
			}		
			if (!F.empty(fdMedicineScience.getContent())) {
				whereHql += " and t.content = :content";
				params.put("content", fdMedicineScience.getContent());
			}		
			if (!F.empty(fdMedicineScience.getDesc())) {
				whereHql += " and t.desc = :desc";
				params.put("desc", fdMedicineScience.getDesc());
			}		
			if (!F.empty(fdMedicineScience.getFile())) {
				whereHql += " and t.file = :file";
				params.put("file", fdMedicineScience.getFile());
			}

			if(!F.empty(fdMedicineScience.getKey())) {
				whereHql += " and (t.title like :key or t.desc like :key or t.content like :key)";
				params.put("key", "%" + fdMedicineScience.getKey() + "%");
			}
		}	
		return whereHql;
	}

	@Override
	public void add(FdMedicineScience fdMedicineScience) {
		TfdMedicineScience t = new TfdMedicineScience();
		BeanUtils.copyProperties(fdMedicineScience, t);
		//t.setId(jb.absx.UUID.uuid());
//		t.setIsdeleted(false);
		fdMedicineScienceDao.save(t);
	}

	@Override
	public FdMedicineScience get(Integer id) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		TfdMedicineScience t = fdMedicineScienceDao.get("from TfdMedicineScience t  where t.id = :id", params);
		FdMedicineScience o = new FdMedicineScience();
		BeanUtils.copyProperties(t, o);
		return o;
	}

	@Override
	public void edit(FdMedicineScience fdMedicineScience) {
		TfdMedicineScience t = fdMedicineScienceDao.get(TfdMedicineScience.class, fdMedicineScience.getId());
		if (t != null) {
			MyBeanUtils.copyProperties(fdMedicineScience, t, new String[] { "id" , "addtime", "isdeleted","updatetime" },true);
		}
	}

	@Override
	public void delete(Integer id) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		fdMedicineScienceDao.executeHql("update TfdMedicineScience t set t.isdeleted = 1 where t.id = :id",params);
		//fdMedicineScienceDao.delete(fdMedicineScienceDao.get(TfdMedicineScience.class, id));
	}

	@Override
	public DataGrid dataGridComplex(FdMedicineScience medicineScience, PageHelper ph) {
		if(ph.getRows() == 0 || ph.getRows() > 50) {
			ph.setRows(10);
		}
		if(F.empty(ph.getSort())) {
			ph.setSort("createTime");
		}
		if(F.empty(ph.getOrder())) {
			ph.setOrder("desc");
		}

		if(F.empty(medicineScience.getIsUp())) medicineScience.setIsUp(1); // 上架
		DataGrid dg = dataGrid(medicineScience, ph);
		List<FdMedicineScience> ol = dg.getRows();
		if(CollectionUtils.isNotEmpty(ol)) {
			CompletionService completionService = CompletionFactory.initCompletion();
			for (FdMedicineScience o : ol) {
				o.setContent(null); // 内容不返回，返回url
				if(!F.empty(o.getPic()))
					completionService.submit(new Task<FdMedicineScience, String>(new CacheKey("fdPic", o.getPic()), o) {
						@Override
						public String call() throws Exception {
							FdPicture pic = fdPictureService.get(Integer.valueOf(getD().getPic()));
							if(pic != null) return PathUtil.getPicPath(pic.getPath());
							return null;
						}

						protected void set(FdMedicineScience d, String v) {
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
