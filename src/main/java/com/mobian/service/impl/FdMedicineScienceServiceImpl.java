package com.mobian.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.mobian.absx.F;
import com.mobian.dao.FdMedicineScienceDaoI;
import com.mobian.model.TfdMedicineScience;
import com.mobian.pageModel.FdMedicineScience;
import com.mobian.pageModel.DataGrid;
import com.mobian.pageModel.PageHelper;
import com.mobian.service.FdMedicineScienceServiceI;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mobian.util.MyBeanUtils;

@Service
public class FdMedicineScienceServiceImpl extends BaseServiceImpl<FdMedicineScience> implements FdMedicineScienceServiceI {

	@Autowired
	private FdMedicineScienceDaoI fdMedicineScienceDao;

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

}
