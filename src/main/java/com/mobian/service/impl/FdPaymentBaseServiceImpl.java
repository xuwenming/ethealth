package com.mobian.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.mobian.absx.F;
import com.mobian.dao.FdPaymentBaseDaoI;
import com.mobian.model.TfdPaymentBase;
import com.mobian.pageModel.FdPaymentBase;
import com.mobian.pageModel.DataGrid;
import com.mobian.pageModel.PageHelper;
import com.mobian.service.FdPaymentBaseServiceI;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mobian.util.MyBeanUtils;

@Service
public class FdPaymentBaseServiceImpl extends BaseServiceImpl<FdPaymentBase> implements FdPaymentBaseServiceI {

	@Autowired
	private FdPaymentBaseDaoI fdPaymentBaseDao;

	@Override
	public DataGrid dataGrid(FdPaymentBase fdPaymentBase, PageHelper ph) {
		List<FdPaymentBase> ol = new ArrayList<FdPaymentBase>();
		String hql = " from TfdPaymentBase t ";
		DataGrid dg = dataGridQuery(hql, ph, fdPaymentBase, fdPaymentBaseDao);
		@SuppressWarnings("unchecked")
		List<TfdPaymentBase> l = dg.getRows();
		if (l != null && l.size() > 0) {
			for (TfdPaymentBase t : l) {
				FdPaymentBase o = new FdPaymentBase();
				BeanUtils.copyProperties(t, o);
				ol.add(o);
			}
		}
		dg.setRows(ol);
		return dg;
	}
	

	protected String whereHql(FdPaymentBase fdPaymentBase, Map<String, Object> params) {
		String whereHql = "";	
		if (fdPaymentBase != null) {
			whereHql += " where 1=1 ";
			if (!F.empty(fdPaymentBase.getOrderNo())) {
				whereHql += " and t.orderNo = :orderNo";
				params.put("orderNo", fdPaymentBase.getOrderNo());
			}		
			if (!F.empty(fdPaymentBase.getType())) {
				whereHql += " and t.type = :type";
				params.put("type", fdPaymentBase.getType());
			}		
			if (!F.empty(fdPaymentBase.getPrice())) {
				whereHql += " and t.price = :price";
				params.put("price", fdPaymentBase.getPrice());
			}		
			if (!F.empty(fdPaymentBase.getStatus())) {
				whereHql += " and t.status = :status";
				params.put("status", fdPaymentBase.getStatus());
			}		
			if (!F.empty(fdPaymentBase.getRemark())) {
				whereHql += " and t.remark = :remark";
				params.put("remark", fdPaymentBase.getRemark());
			}		
		}	
		return whereHql;
	}

	@Override
	public void add(FdPaymentBase fdPaymentBase) {
		TfdPaymentBase t = new TfdPaymentBase();
		BeanUtils.copyProperties(fdPaymentBase, t);
		//t.setId(jb.absx.UUID.uuid());
		fdPaymentBaseDao.save(t);
	}

	@Override
	public FdPaymentBase get(Integer id) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		TfdPaymentBase t = fdPaymentBaseDao.get("from TfdPaymentBase t  where t.id = :id", params);
		FdPaymentBase o = new FdPaymentBase();
		BeanUtils.copyProperties(t, o);
		return o;
	}

	@Override
	public void edit(FdPaymentBase fdPaymentBase) {
		TfdPaymentBase t = fdPaymentBaseDao.get(TfdPaymentBase.class, fdPaymentBase.getId());
		if (t != null) {
			MyBeanUtils.copyProperties(fdPaymentBase, t, new String[] { "id" , "addtime", "isdeleted","updatetime" },true);
		}
	}

	@Override
	public void delete(Integer id) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		fdPaymentBaseDao.executeHql("update TfdPaymentBase t set t.isdeleted = 1 where t.id = :id",params);
		//fdPaymentBaseDao.delete(fdPaymentBaseDao.get(TfdPaymentBase.class, id));
	}

}
