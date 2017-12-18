package com.mobian.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.mobian.absx.F;
import com.mobian.dao.FdAccountDaoI;
import com.mobian.model.TfdAccount;
import com.mobian.pageModel.FdAccount;
import com.mobian.pageModel.DataGrid;
import com.mobian.pageModel.PageHelper;
import com.mobian.service.FdAccountServiceI;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mobian.util.MyBeanUtils;

@Service
public class FdAccountServiceImpl extends BaseServiceImpl<FdAccount> implements FdAccountServiceI {

	@Autowired
	private FdAccountDaoI fdAccountDao;

	@Override
	public DataGrid dataGrid(FdAccount fdAccount, PageHelper ph) {
		List<FdAccount> ol = new ArrayList<FdAccount>();
		String hql = " from TfdAccount t ";
		DataGrid dg = dataGridQuery(hql, ph, fdAccount, fdAccountDao);
		@SuppressWarnings("unchecked")
		List<TfdAccount> l = dg.getRows();
		if (l != null && l.size() > 0) {
			for (TfdAccount t : l) {
				FdAccount o = new FdAccount();
				BeanUtils.copyProperties(t, o);
				ol.add(o);
			}
		}
		dg.setRows(ol);
		return dg;
	}
	

	protected String whereHql(FdAccount fdAccount, Map<String, Object> params) {
		String whereHql = "";	
		if (fdAccount != null) {
			whereHql += " where t.status = 0 ";
			if (!F.empty(fdAccount.getBankAccount())) {
				whereHql += " and t.bankAccount = :bankAccount";
				params.put("bankAccount", fdAccount.getBankAccount());
			}		
			if (!F.empty(fdAccount.getBankPhone())) {
				whereHql += " and t.bankPhone = :bankPhone";
				params.put("bankPhone", fdAccount.getBankPhone());
			}		
			if (!F.empty(fdAccount.getBankIdNo())) {
				whereHql += " and t.bankIdNo = :bankIdNo";
				params.put("bankIdNo", fdAccount.getBankIdNo());
			}		
			if (!F.empty(fdAccount.getBankName())) {
				whereHql += " and t.bankName = :bankName";
				params.put("bankName", fdAccount.getBankName());
			}		
			if (!F.empty(fdAccount.getBankCard())) {
				whereHql += " and t.bankCard = :bankCard";
				params.put("bankCard", fdAccount.getBankCard());
			}		
			if (!F.empty(fdAccount.getAlipay())) {
				whereHql += " and t.alipay = :alipay";
				params.put("alipay", fdAccount.getAlipay());
			}		
		}	
		return whereHql;
	}

	@Override
	public void add(FdAccount fdAccount) {
		TfdAccount t = new TfdAccount();
		BeanUtils.copyProperties(fdAccount, t);
		//t.setId(jb.absx.UUID.uuid());
		fdAccountDao.save(t);
	}

	@Override
	public FdAccount get(Long userId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userId", userId);
		TfdAccount t = fdAccountDao.get("from TfdAccount t  where t.userId = :userId", params);
		if(t == null) return null;
		FdAccount o = new FdAccount();
		BeanUtils.copyProperties(t, o);
		return o;
	}

	@Override
	public void edit(FdAccount fdAccount) {
		TfdAccount t = fdAccountDao.get(TfdAccount.class, fdAccount.getUserId());
		if (t != null) {
			MyBeanUtils.copyProperties(fdAccount, t, new String[] { "id" , "addtime", "isdeleted","updatetime" },true);
		}
	}

	@Override
	public void delete(Integer id) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		fdAccountDao.executeHql("update TfdAccount t set t.isdeleted = 1 where t.id = :id",params);
		//fdAccountDao.delete(fdAccountDao.get(TfdAccount.class, id));
	}

}
