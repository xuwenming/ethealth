package com.mobian.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.mobian.absx.F;
import com.mobian.dao.FdMemberDaoI;
import com.mobian.model.TfdMember;
import com.mobian.pageModel.*;
import com.mobian.service.*;

import com.mobian.util.PathUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mobian.util.MyBeanUtils;

@Service
public class FdMemberServiceImpl extends BaseServiceImpl<FdMember> implements FdMemberServiceI {

	@Autowired
	private FdMemberDaoI fdMemberDao;

	@Autowired
	private FdMemberDoctorServiceI fdMemberDoctorService;

	@Autowired
	private FdCustomerServiceI fdCustomerService;

	@Autowired
	private FdPictureServiceI fdPictureService;

	@Autowired
	private FdMemberDoctorLevelServiceI fdMemberDoctorLevelService;

	@Override
	public DataGrid dataGrid(FdMember fdMember, PageHelper ph) {
		List<FdMember> ol = new ArrayList<FdMember>();
		String hql = " from TfdMember t ";
		DataGrid dg = dataGridQuery(hql, ph, fdMember, fdMemberDao);
		@SuppressWarnings("unchecked")
		List<TfdMember> l = dg.getRows();
		if (l != null && l.size() > 0) {
			for (TfdMember t : l) {
				FdMember o = new FdMember();
				BeanUtils.copyProperties(t, o);
				ol.add(o);
			}
		}
		dg.setRows(ol);
		return dg;
	}
	

	protected String whereHql(FdMember fdMember, Map<String, Object> params) {
		String whereHql = "";	
		if (fdMember != null) {
			whereHql += " where t.status = 1 ";
			if (!F.empty(fdMember.getUsername())) {
				whereHql += " and t.username = :username";
				params.put("username", fdMember.getUsername());
			}		
			if (!F.empty(fdMember.getPassword())) {
				whereHql += " and t.password = :password";
				params.put("password", fdMember.getPassword());
			}		
			if (!F.empty(fdMember.getScore())) {
				whereHql += " and t.score = :score";
				params.put("score", fdMember.getScore());
			}		
			if (!F.empty(fdMember.getEmail())) {
				whereHql += " and t.email = :email";
				params.put("email", fdMember.getEmail());
			}		
			if (!F.empty(fdMember.getLogin())) {
				whereHql += " and t.login = :login";
				params.put("login", fdMember.getLogin());
			}		
			if (!F.empty(fdMember.getMobile())) {
				whereHql += " and t.mobile = :mobile";
				params.put("mobile", fdMember.getMobile());
			}		
			if (!F.empty(fdMember.getRegTime())) {
				whereHql += " and t.regTime = :regTime";
				params.put("regTime", fdMember.getRegTime());
			}		
			if (!F.empty(fdMember.getRegIp())) {
				whereHql += " and t.regIp = :regIp";
				params.put("regIp", fdMember.getRegIp());
			}		
			if (!F.empty(fdMember.getLastLoginTime())) {
				whereHql += " and t.lastLoginTime = :lastLoginTime";
				params.put("lastLoginTime", fdMember.getLastLoginTime());
			}		
			if (!F.empty(fdMember.getLastLoginIp())) {
				whereHql += " and t.lastLoginIp = :lastLoginIp";
				params.put("lastLoginIp", fdMember.getLastLoginIp());
			}		
			if (!F.empty(fdMember.getUpdateTime())) {
				whereHql += " and t.updateTime = :updateTime";
				params.put("updateTime", fdMember.getUpdateTime());
			}		
			if (!F.empty(fdMember.getStatus())) {
				whereHql += " and t.status = :status";
				params.put("status", fdMember.getStatus());
			}		
			if (!F.empty(fdMember.getIsAdmin())) {
				whereHql += " and t.isAdmin = :isAdmin";
				params.put("isAdmin", fdMember.getIsAdmin());
			}		
			if (!F.empty(fdMember.getGroupid())) {
				whereHql += " and t.groupid = :groupid";
				params.put("groupid", fdMember.getGroupid());
			}		
			if (!F.empty(fdMember.getAmount())) {
				whereHql += " and t.amount = :amount";
				params.put("amount", fdMember.getAmount());
			}		
			if (!F.empty(fdMember.getModelid())) {
				whereHql += " and t.modelid = :modelid";
				params.put("modelid", fdMember.getModelid());
			}		
			if (!F.empty(fdMember.getMessage())) {
				whereHql += " and t.message = :message";
				params.put("message", fdMember.getMessage());
			}		
			if (!F.empty(fdMember.getPic())) {
				whereHql += " and t.pic = :pic";
				params.put("pic", fdMember.getPic());
			}		
			if (!F.empty(fdMember.getDoctorId())) {
				whereHql += " and t.doctorId = :doctorId";
				params.put("doctorId", fdMember.getDoctorId());
			}		
			if (!F.empty(fdMember.getGroupId())) {
				whereHql += " and t.groupId = :groupId";
				params.put("groupId", fdMember.getGroupId());
			}		
			if (!F.empty(fdMember.getVipEndTime())) {
				whereHql += " and t.vipEndTime = :vipEndTime";
				params.put("vipEndTime", fdMember.getVipEndTime());
			}		
		}	
		return whereHql;
	}

	@Override
	public void add(FdMember fdMember) {
		TfdMember t = new TfdMember();
		BeanUtils.copyProperties(fdMember, t);
		//t.setId(jb.absx.UUID.uuid());
		fdMemberDao.save(t);
	}

	@Override
	public FdMember get(Integer id) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		TfdMember t = fdMemberDao.get("from TfdMember t  where t.id = :id", params);
		FdMember o = new FdMember();
		BeanUtils.copyProperties(t, o);
		return o;
	}

	@Override
	public void edit(FdMember fdMember) {
		TfdMember t = fdMemberDao.get(TfdMember.class, fdMember.getId());
		if (t != null) {
			MyBeanUtils.copyProperties(fdMember, t, new String[] { "id" , "addtime", "isdeleted","updatetime" },true);
		}
	}

	@Override
	public void delete(Integer id) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		fdMemberDao.executeHql("update TfdMember t set t.isdeleted = 1 where t.id = :id",params);
		//fdMemberDao.delete(fdMemberDao.get(TfdMember.class, id));
	}

	@Override
	public List<FdMember> queryMembersByGroupId(Integer groupId) {
		List<FdMember> ol = new ArrayList<FdMember>();
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("groupId", groupId);
		params.put("isAdmin", 2);
		List<TfdMember> l = fdMemberDao.find(" from TfdMember t where t.status = 1 and t.isAdmin = :isAdmin and t.groupId = :groupId ", params);
		if (l != null && l.size() > 0) {
			for (TfdMember t : l) {
				FdMember o = new FdMember();
				BeanUtils.copyProperties(t, o);
				fillSimpleDoctorInfo(o);
				ol.add(o);
			}
		}
		return ol;
	}

	private void fillSimpleDoctorInfo(FdMember member) {
		if(!F.empty(member.getPic())) {
			FdPicture pic = fdPictureService.get(Integer.valueOf(member.getPic()));
			if(pic != null) member.setPicUrl(PathUtil.getPicPath(pic.getPath()));
		}
		member.setCustomer(fdCustomerService.get(member.getId().longValue()));
		FdMemberDoctor doctor = fdMemberDoctorService.get(member.getId());
		if(!F.empty(doctor.getLevel())) {
			FdMemberDoctorLevel level = fdMemberDoctorLevelService.get(doctor.getLevel());
			doctor.setLevelName(level.getName());
		}
		member.setMemberDoctor(doctor);
	}

}