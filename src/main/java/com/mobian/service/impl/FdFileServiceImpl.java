package com.mobian.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.mobian.absx.F;
import com.mobian.dao.FdFileDaoI;
import com.mobian.model.TfdFile;
import com.mobian.pageModel.FdFile;
import com.mobian.pageModel.DataGrid;
import com.mobian.pageModel.PageHelper;
import com.mobian.service.FdFileServiceI;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mobian.util.MyBeanUtils;

@Service
public class FdFileServiceImpl extends BaseServiceImpl<FdFile> implements FdFileServiceI {

	@Autowired
	private FdFileDaoI fdFileDao;

	@Override
	public DataGrid dataGrid(FdFile fdFile, PageHelper ph) {
		List<FdFile> ol = new ArrayList<FdFile>();
		String hql = " from TfdFile t ";
		DataGrid dg = dataGridQuery(hql, ph, fdFile, fdFileDao);
		@SuppressWarnings("unchecked")
		List<TfdFile> l = dg.getRows();
		if (l != null && l.size() > 0) {
			for (TfdFile t : l) {
				FdFile o = new FdFile();
				BeanUtils.copyProperties(t, o);
				ol.add(o);
			}
		}
		dg.setRows(ol);
		return dg;
	}
	

	protected String whereHql(FdFile fdFile, Map<String, Object> params) {
		String whereHql = "";	
		if (fdFile != null) {
			whereHql += " where 1=1 ";
			if (!F.empty(fdFile.getName())) {
				whereHql += " and t.name = :name";
				params.put("name", fdFile.getName());
			}		
			if (!F.empty(fdFile.getSavename())) {
				whereHql += " and t.savename = :savename";
				params.put("savename", fdFile.getSavename());
			}		
			if (!F.empty(fdFile.getSavepath())) {
				whereHql += " and t.savepath = :savepath";
				params.put("savepath", fdFile.getSavepath());
			}		
			if (!F.empty(fdFile.getExt())) {
				whereHql += " and t.ext = :ext";
				params.put("ext", fdFile.getExt());
			}		
			if (!F.empty(fdFile.getMime())) {
				whereHql += " and t.mime = :mime";
				params.put("mime", fdFile.getMime());
			}		
			if (!F.empty(fdFile.getSize())) {
				whereHql += " and t.size = :size";
				params.put("size", fdFile.getSize());
			}		
			if (!F.empty(fdFile.getMd5())) {
				whereHql += " and t.md5 = :md5";
				params.put("md5", fdFile.getMd5());
			}		
			if (!F.empty(fdFile.getSha1())) {
				whereHql += " and t.sha1 = :sha1";
				params.put("sha1", fdFile.getSha1());
			}		
			if (!F.empty(fdFile.getLocation())) {
				whereHql += " and t.location = :location";
				params.put("location", fdFile.getLocation());
			}		
			if (!F.empty(fdFile.getUrl())) {
				whereHql += " and t.url = :url";
				params.put("url", fdFile.getUrl());
			}		
			if (!F.empty(fdFile.getCreateTime())) {
				whereHql += " and t.createTime = :createTime";
				params.put("createTime", fdFile.getCreateTime());
			}		
		}	
		return whereHql;
	}

	@Override
	public void add(FdFile fdFile) {
		TfdFile t = new TfdFile();
		BeanUtils.copyProperties(fdFile, t);
		//t.setId(jb.absx.UUID.uuid());
		fdFileDao.save(t);
	}

	@Override
	public FdFile get(Integer id) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		TfdFile t = fdFileDao.get("from TfdFile t  where t.id = :id", params);
		FdFile o = new FdFile();
		BeanUtils.copyProperties(t, o);
		return o;
	}

	@Override
	public void edit(FdFile fdFile) {
		TfdFile t = fdFileDao.get(TfdFile.class, fdFile.getId());
		if (t != null) {
			MyBeanUtils.copyProperties(fdFile, t, new String[] { "id" , "addtime", "isdeleted","updatetime" },true);
		}
	}

	@Override
	public void delete(Integer id) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		fdFileDao.executeHql("update TfdFile t set t.isdeleted = 1 where t.id = :id",params);
		//fdFileDao.delete(fdFileDao.get(TfdFile.class, id));
	}

}
