package com.mobian.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.mobian.absx.F;
import com.mobian.dao.FdCustomerDaoI;
import com.mobian.model.TfdCustomer;
import com.mobian.pageModel.FdCustomer;
import com.mobian.pageModel.DataGrid;
import com.mobian.pageModel.PageHelper;
import com.mobian.service.FdCustomerServiceI;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mobian.util.MyBeanUtils;

@Service
public class FdCustomerServiceImpl extends BaseServiceImpl<FdCustomer> implements FdCustomerServiceI {

	@Autowired
	private FdCustomerDaoI fdCustomerDao;

	@Override
	public DataGrid dataGrid(FdCustomer fdCustomer, PageHelper ph) {
		List<FdCustomer> ol = new ArrayList<FdCustomer>();
		String hql = " from TfdCustomer t ";
		DataGrid dg = dataGridQuery(hql, ph, fdCustomer, fdCustomerDao);
		@SuppressWarnings("unchecked")
		List<TfdCustomer> l = dg.getRows();
		if (l != null && l.size() > 0) {
			for (TfdCustomer t : l) {
				FdCustomer o = new FdCustomer();
				BeanUtils.copyProperties(t, o);
				ol.add(o);
			}
		}
		dg.setRows(ol);
		return dg;
	}
	

	protected String whereHql(FdCustomer fdCustomer, Map<String, Object> params) {
		String whereHql = "";	
		if (fdCustomer != null) {
			whereHql += " where 1=1 ";
			if (!F.empty(fdCustomer.getRealName())) {
				whereHql += " and t.realName = :realName";
				params.put("realName", fdCustomer.getRealName());
			}		
			if (!F.empty(fdCustomer.getPhone())) {
				whereHql += " and t.phone = :phone";
				params.put("phone", fdCustomer.getPhone());
			}		
			if (!F.empty(fdCustomer.getProvince())) {
				whereHql += " and t.province = :province";
				params.put("province", fdCustomer.getProvince());
			}		
			if (!F.empty(fdCustomer.getCity())) {
				whereHql += " and t.city = :city";
				params.put("city", fdCustomer.getCity());
			}		
			if (!F.empty(fdCustomer.getCounty())) {
				whereHql += " and t.county = :county";
				params.put("county", fdCustomer.getCounty());
			}		
			if (!F.empty(fdCustomer.getAddr())) {
				whereHql += " and t.addr = :addr";
				params.put("addr", fdCustomer.getAddr());
			}		
			if (!F.empty(fdCustomer.getQq())) {
				whereHql += " and t.qq = :qq";
				params.put("qq", fdCustomer.getQq());
			}		
			if (!F.empty(fdCustomer.getSex())) {
				whereHql += " and t.sex = :sex";
				params.put("sex", fdCustomer.getSex());
			}		
			if (!F.empty(fdCustomer.getBirthday())) {
				whereHql += " and t.birthday = :birthday";
				params.put("birthday", fdCustomer.getBirthday());
			}		
			if (!F.empty(fdCustomer.getGroupId())) {
				whereHql += " and t.groupId = :groupId";
				params.put("groupId", fdCustomer.getGroupId());
			}		
			if (!F.empty(fdCustomer.getPoint())) {
				whereHql += " and t.point = :point";
				params.put("point", fdCustomer.getPoint());
			}		
			if (!F.empty(fdCustomer.getMessageIds())) {
				whereHql += " and t.messageIds = :messageIds";
				params.put("messageIds", fdCustomer.getMessageIds());
			}		
			if (!F.empty(fdCustomer.getProp())) {
				whereHql += " and t.prop = :prop";
				params.put("prop", fdCustomer.getProp());
			}
			if (!F.empty(fdCustomer.getCustom())) {
				whereHql += " and t.custom = :custom";
				params.put("custom", fdCustomer.getCustom());
			}		
			if (!F.empty(fdCustomer.getCheckinTime())) {
				whereHql += " and t.checkinTime = :checkinTime";
				params.put("checkinTime", fdCustomer.getCheckinTime());
			}		
			if (!F.empty(fdCustomer.getNickName())) {
				whereHql += " and t.nickName = :nickName";
				params.put("nickName", fdCustomer.getNickName());
			}		
		}	
		return whereHql;
	}

	@Override
	public void add(FdCustomer fdCustomer) {
		TfdCustomer t = new TfdCustomer();
		BeanUtils.copyProperties(fdCustomer, t);
		//t.setId(jb.absx.UUID.uuid());
		fdCustomerDao.save(t);
	}

	@Override
	public FdCustomer get(Long userId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userId", userId);
		TfdCustomer t = fdCustomerDao.get("from TfdCustomer t  where t.userId = :userId", params);
		if(t != null) {
			FdCustomer o = new FdCustomer();
			BeanUtils.copyProperties(t, o);
			return o;
		}

		return null;
	}

	@Override
	public void edit(FdCustomer fdCustomer) {
		TfdCustomer t = fdCustomerDao.get(TfdCustomer.class, fdCustomer.getUserId());
		if (t != null) {
			MyBeanUtils.copyProperties(fdCustomer, t, new String[] { "id" , "addtime", "isdeleted","updatetime" },true);
		}
	}

	@Override
	public void delete(Integer id) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		fdCustomerDao.executeHql("update TfdCustomer t set t.isdeleted = 1 where t.id = :id",params);
		//fdCustomerDao.delete(fdCustomerDao.get(TfdCustomer.class, id));
	}

}
