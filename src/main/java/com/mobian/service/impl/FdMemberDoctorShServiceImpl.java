package com.mobian.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.mobian.absx.F;
import com.mobian.dao.FdMemberDoctorShDaoI;
import com.mobian.exception.ServiceException;
import com.mobian.model.TfdMemberDoctorSh;
import com.mobian.pageModel.*;
import com.mobian.service.*;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mobian.util.MyBeanUtils;

@Service
public class FdMemberDoctorShServiceImpl extends BaseServiceImpl<FdMemberDoctorSh> implements FdMemberDoctorShServiceI {

	@Autowired
	private FdMemberDoctorShDaoI fdMemberDoctorShDao;

	@Autowired
	private FdMemberServiceI fdMemberService;

	@Autowired
	private FdCustomerServiceI fdCustomerService;

	@Autowired
	private FdMemberDoctorServiceI fdMemberDoctorService;

	@Autowired
	private FdMessageServiceI fdMessageService;

	@Override
	public DataGrid dataGrid(FdMemberDoctorSh fdMemberDoctorSh, PageHelper ph) {
		List<FdMemberDoctorSh> ol = new ArrayList<FdMemberDoctorSh>();
		String hql = " from TfdMemberDoctorSh t ";
		DataGrid dg = dataGridQuery(hql, ph, fdMemberDoctorSh, fdMemberDoctorShDao);
		@SuppressWarnings("unchecked")
		List<TfdMemberDoctorSh> l = dg.getRows();
		if (l != null && l.size() > 0) {
			for (TfdMemberDoctorSh t : l) {
				FdMemberDoctorSh o = new FdMemberDoctorSh();
				BeanUtils.copyProperties(t, o);
				ol.add(o);
			}
		}
		dg.setRows(ol);
		return dg;
	}
	

	protected String whereHql(FdMemberDoctorSh fdMemberDoctorSh, Map<String, Object> params) {
		String whereHql = "";	
		if (fdMemberDoctorSh != null) {
			whereHql += " where 1=1 ";
			if (!F.empty(fdMemberDoctorSh.getLevel())) {
				whereHql += " and t.level = :level";
				params.put("level", fdMemberDoctorSh.getLevel());
			}		
			if (!F.empty(fdMemberDoctorSh.getHospital())) {
				whereHql += " and t.hospital = :hospital";
				params.put("hospital", fdMemberDoctorSh.getHospital());
			}		
			if (!F.empty(fdMemberDoctorSh.getDepartment())) {
				whereHql += " and t.department = :department";
				params.put("department", fdMemberDoctorSh.getDepartment());
			}		
			if (!F.empty(fdMemberDoctorSh.getEducation())) {
				whereHql += " and t.education = :education";
				params.put("education", fdMemberDoctorSh.getEducation());
			}		
			if (!F.empty(fdMemberDoctorSh.getConsultingHour())) {
				whereHql += " and t.consultingHour = :consultingHour";
				params.put("consultingHour", fdMemberDoctorSh.getConsultingHour());
			}		
			if (!F.empty(fdMemberDoctorSh.getSpecialHour())) {
				whereHql += " and t.specialHour = :specialHour";
				params.put("specialHour", fdMemberDoctorSh.getSpecialHour());
			}		
			if (!F.empty(fdMemberDoctorSh.getSpeciality())) {
				whereHql += " and t.speciality = :speciality";
				params.put("speciality", fdMemberDoctorSh.getSpeciality());
			}		
			if (!F.empty(fdMemberDoctorSh.getIntroduce())) {
				whereHql += " and t.introduce = :introduce";
				params.put("introduce", fdMemberDoctorSh.getIntroduce());
			}		
			if (!F.empty(fdMemberDoctorSh.getPics())) {
				whereHql += " and t.pics = :pics";
				params.put("pics", fdMemberDoctorSh.getPics());
			}		
			if (!F.empty(fdMemberDoctorSh.getCreateBy())) {
				whereHql += " and t.createBy = :createBy";
				params.put("createBy", fdMemberDoctorSh.getCreateBy());
			}		
			if (!F.empty(fdMemberDoctorSh.getUpdateBy())) {
				whereHql += " and t.updateBy = :updateBy";
				params.put("updateBy", fdMemberDoctorSh.getUpdateBy());
			}		
			if (!F.empty(fdMemberDoctorSh.getStatus())) {
				whereHql += " and t.status = :status";
				params.put("status", fdMemberDoctorSh.getStatus());
			}		
			if (!F.empty(fdMemberDoctorSh.getReason())) {
				whereHql += " and t.reason = :reason";
				params.put("reason", fdMemberDoctorSh.getReason());
			}		
			if (!F.empty(fdMemberDoctorSh.getRealName())) {
				whereHql += " and t.realName like :realName";
				params.put("realName", "%" + fdMemberDoctorSh.getRealName() + "%");
			}		
			if (!F.empty(fdMemberDoctorSh.getSex())) {
				whereHql += " and t.sex = :sex";
				params.put("sex", fdMemberDoctorSh.getSex());
			}		
			if (!F.empty(fdMemberDoctorSh.getBirthday())) {
				whereHql += " and t.birthday = :birthday";
				params.put("birthday", fdMemberDoctorSh.getBirthday());
			}		
			if (!F.empty(fdMemberDoctorSh.getGroupId())) {
				whereHql += " and t.groupId = :groupId";
				params.put("groupId", fdMemberDoctorSh.getGroupId());
			}
			if (!F.empty(fdMemberDoctorSh.getAuditType())) {
				whereHql += " and t.auditType = :auditType";
				params.put("auditType", fdMemberDoctorSh.getAuditType());
			}

			if(!F.empty(fdMemberDoctorSh.getMobile())) {
				whereHql += " and exists (select 1 from TfdMember m where m.id = t.id and m.mobile like :mobile)";
				params.put("mobile", "%" + fdMemberDoctorSh.getMobile() + "%");
			} else {
				whereHql += " and exists (select 1 from TfdMember m where m.id = t.id and m.status <> -1)";
			}

		}	
		return whereHql;
	}

	@Override
	public void add(FdMemberDoctorSh fdMemberDoctorSh) {
		TfdMemberDoctorSh t = new TfdMemberDoctorSh();
		BeanUtils.copyProperties(fdMemberDoctorSh, t);
		t.setCreateTime(new Date());
		t.setUpdateTime(t.getCreateTime());
		//t.setId(jb.absx.UUID.uuid());
		fdMemberDoctorShDao.save(t);
	}

	@Override
	public FdMemberDoctorSh get(Integer id) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		TfdMemberDoctorSh t = fdMemberDoctorShDao.get("from TfdMemberDoctorSh t  where t.id = :id", params);
		if(t != null) {
			FdMemberDoctorSh o = new FdMemberDoctorSh();
			BeanUtils.copyProperties(t, o);
			return o;
		}

		return null;
	}

	public FdMemberDoctorSh get(Integer id, Integer auditType) {

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		params.put("auditType", auditType == null ? 2 : auditType);
		TfdMemberDoctorSh t = fdMemberDoctorShDao.get("from TfdMemberDoctorSh t  where t.id = :id and t.auditType = :auditType", params);
		if(t != null) {
			FdMemberDoctorSh o = new FdMemberDoctorSh();
			BeanUtils.copyProperties(t, o);
			return o;
		}

		return null;
	}

	@Override
	public void edit(FdMemberDoctorSh fdMemberDoctorSh) {
		if(fdMemberDoctorSh.getStatus() == null || "1".equals(fdMemberDoctorSh.getStatus()))
			fdMemberDoctorSh.setUpdateTime(new Date());
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", fdMemberDoctorSh.getId());
		params.put("auditType", fdMemberDoctorSh.getAuditType());
		TfdMemberDoctorSh t = fdMemberDoctorShDao.get("from TfdMemberDoctorSh t  where t.id = :id and t.auditType = :auditType", params);
		if (t != null) {
			MyBeanUtils.copyProperties(fdMemberDoctorSh, t, new String[] { "id" , "addtime", "isdeleted","updatetime" },true);
		}
	}

	@Override
	public void delete(Integer id) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		fdMemberDoctorShDao.executeHql("update TfdMemberDoctorSh t set t.isdeleted = 1 where t.id = :id",params);
		//fdMemberDoctorShDao.delete(fdMemberDoctorShDao.get(TfdMemberDoctorSh.class, id));
	}

	@Override
	public void addOrUpdateMemberDoctorSh(FdMemberDoctorSh sh) {
		FdMemberDoctorSh o = get(sh.getId(), sh.getAuditType());
		if(o == null) {
			add(sh);
		} else {
			edit(sh);
			if(F.empty(sh.getPics())) sh.setPics(o.getPics());
		}

		if(sh.getAuditType() == 1) {
			FdMember member = new FdMember();
			member.setId(sh.getId());
			member.setStatus(2);
			fdMemberService.edit(member);
		}
	}

	@Override
	public void editAudit(FdMemberDoctorSh fdMemberDoctorSh) {
		edit(fdMemberDoctorSh);

		fdMemberDoctorSh = get(fdMemberDoctorSh.getId(), fdMemberDoctorSh.getAuditType());

		FdMember member = fdMemberService.get(fdMemberDoctorSh.getId());
		if("2".equals(fdMemberDoctorSh.getStatus())) {
			member.setStatus(1);
			member.setEmail(fdMemberDoctorSh.getEmail());
			member.setHeadImage(fdMemberDoctorSh.getPics());
			fdMemberService.edit(member);

			FdCustomer customer = fdCustomerService.get(member.getId().longValue());
			if(customer == null) {
				customer = new FdCustomer();
				customer.setUserId(member.getId().longValue());
				customer.setRealName(fdMemberDoctorSh.getRealName());
				customer.setPhone(member.getMobile());
				customer.setBirthday(fdMemberDoctorSh.getBirthday());
				customer.setSex(fdMemberDoctorSh.getSex());
				fdCustomerService.add(customer);
			} else {
				customer.setRealName(fdMemberDoctorSh.getRealName());
				customer.setPhone(member.getMobile());
				customer.setBirthday(fdMemberDoctorSh.getBirthday());
				customer.setSex(fdMemberDoctorSh.getSex());
				fdCustomerService.edit(customer);
			}

			FdMemberDoctor doctor = fdMemberDoctorService.get(member.getId());
			if(doctor == null) {
				doctor = new FdMemberDoctor();
				doctor.setId(member.getId());
				doctor.setHospital(fdMemberDoctorSh.getHospital());
				doctor.setDepartment(fdMemberDoctorSh.getDepartment());
				doctor.setLevel(fdMemberDoctorSh.getLevel());
				doctor.setSpeciality(fdMemberDoctorSh.getSpeciality());
				doctor.setIntroduce(fdMemberDoctorSh.getIntroduce());
				fdMemberDoctorService.add(doctor);
			} else {
				doctor.setHospital(fdMemberDoctorSh.getHospital());
				doctor.setDepartment(fdMemberDoctorSh.getDepartment());
				doctor.setLevel(fdMemberDoctorSh.getLevel());
				doctor.setSpeciality(fdMemberDoctorSh.getSpeciality());
				doctor.setIntroduce(fdMemberDoctorSh.getIntroduce());
				fdMemberDoctorService.edit(doctor);
			}

			if(fdMemberDoctorSh.getAuditType() == 1) {
				FdMessage message = new FdMessage();
				message.setTitle("注册审核通过");
				message.setContent("尊敬的医生您好，您已经注册成功，可以开始使用客户端的全部功能。\n有任何疑问可通过客户端直接联系我们，谢谢！");
				message.setUserId(member.getId());
				message.setMtype("MT02");
				message.setIsRead(false);
				message.setAlias("2_" + member.getMobile());
				message.setPushMessage(new PushMessage("M001", "尊敬的" + fdMemberDoctorSh.getRealName() + "医生您好，恭喜您在医家盟注册医生账号审核通过！", 2));
				fdMessageService.addAndPushMessage(message);
			} else {
				FdMessage message = new FdMessage();
				message.setTitle("编辑审核通过");
				message.setContent("尊敬的医生您好，您的个人信息修改审核通过。\n有任何疑问可通过客户端直接联系我们，谢谢！");
				message.setUserId(member.getId());
				message.setMtype("MT02");
				message.setIsRead(false);
				message.setAlias("2_" + member.getMobile());
				message.setPushMessage(new PushMessage("M002", "尊敬的" + fdMemberDoctorSh.getRealName() + "医生您好，恭喜您在医家盟修改医生信息审核通过！", 2));
				fdMessageService.addAndPushMessage(message);
			}
		} else {
			member.setStatus(3);
			fdMemberService.edit(member);

			if(fdMemberDoctorSh.getAuditType() == 2) {
				FdMessage message = new FdMessage();
				message.setTitle("编辑审核失败");
				message.setContent("尊敬的医生您好，您的个人信息修改审核失败。\n有任何疑问可通过客户端直接联系我们，谢谢！");
				message.setUserId(member.getId());
				message.setMtype("MT02");
				message.setIsRead(false);
				message.setAlias("2_" + member.getMobile());
				message.setPushMessage(new PushMessage("M002", "尊敬的医生您好，您在医家盟修改医生信息审核失败，非常抱歉！", 0));
				fdMessageService.addAndPushMessage(message);
			}
		}
	}

}
