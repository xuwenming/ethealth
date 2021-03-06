package com.mobian.service.impl;

import com.mobian.absx.F;
import com.mobian.dao.FdMemberDaoI;
import com.mobian.listener.Application;
import com.mobian.model.TfdMember;
import com.mobian.pageModel.*;
import com.mobian.service.*;
import com.mobian.thirdpart.easemob.HuanxinUtil;
import com.mobian.util.*;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

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

	@Autowired
	private FdHospitalServiceI fdHospitalService;

	@Autowired
	private FdMemberDoctorShServiceI fdMemberDoctorShService;

	@Autowired
	private FdHospitalDeptServiceI fdHospitalDeptService;

	@Autowired
	private FdPatientServiceI fdPatientService;

	@Autowired
	private FdMessageServiceI fdMessageService;

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

	@Override
	public DataGrid dataGridComplex(FdMember fdMember, PageHelper ph) {
		DataGrid dg = dataGrid(fdMember, ph);
		List<FdMember> list = dg.getRows();
		if(CollectionUtils.isNotEmpty(list)) {
			for(FdMember m : list) {
				m.setCustomer(fdCustomerService.get(m.getId().longValue()));
			}
		}
		return dg;
	}

	protected String whereHql(FdMember fdMember, Map<String, Object> params) {
		String whereHql = "";	
		if (fdMember != null) {
			whereHql += " where 1 = 1 ";
			if (!F.empty(fdMember.getStatus())) {
				whereHql += " and t.status = :status";
				params.put("status", fdMember.getStatus());
			} else {
				whereHql += " and t.status = 1";
			}
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
			if (fdMember.getIsAdmin() != null) {
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

			if(!F.empty(fdMember.getQ())) {
				whereHql += " and (t.mobile like :q or exists (select 1 from TfdCustomer c where c.userId = t.id and c.realName like :q))";
				params.put("q", "%" + fdMember.getQ() + "%");
			}
		}	
		return whereHql;
	}

	@Override
	public void add(FdMember fdMember) {
		TfdMember t = new TfdMember();
		BeanUtils.copyProperties(fdMember, t);
		if(F.empty(fdMember.getStatus())) t.setStatus(1);
		t.setRegTime(new Date().getTime());
		fdMemberDao.save(t);
		fdMember.setId(t.getId());
	}

	@Override
	public void addMember(FdMember member) {
		if(member.getIsAdmin() == 2) {
			FdMember m = new FdMember();
			m.setUsername(member.getUsername());
			m.setIsAdmin(member.getIsAdmin());
			m.setStatusArr("-1");
			m = get(m);
			if(m == null) {
				add(member);
			} else {
				member.setId(m.getId());
			}
		} else {
			add(member);
			FdCustomer customer = new FdCustomer();
			customer.setUserId(member.getId().longValue());
			customer.setPhone(member.getMobile());
			fdCustomerService.add(customer);

			FdMessage message = new FdMessage();
			message.setTitle("注册成功");
			message.setContent("尊敬的用户您好，您已经注册成功，可以开始使用客户端的全部功能。\n有任何疑问可通过客户端直接联系我们，谢谢！");
			message.setUserId(member.getId());
			message.setMtype("MT02");
			message.setIsRead(false);
			message.setAlias("0-" + member.getMobile());
		}

//		if(member.getIsAdmin() == 2) {
//			FdMemberDoctor doctor = new FdMemberDoctor();
//			doctor.setId(member.getId());
//			fdMemberDoctorService.add(doctor);
//		}
	}

	@Override
	public FdMember getDetail(Integer id) {
		FdMember member = get(id);
		fillSimpleDoctorInfo(member);

		// 获取未读消息数量
		int count = fdMessageService.getUnreadMsgCount(member.getId(), member.getIsAdmin());
		member.setUnreadMsgCount(count);
		return member;
	}

	@Override
	public FdMember getSimple(Integer id) {
		FdMember member = get(id);
//		String picUrl = member.getHeadImage();
//		if(F.empty(picUrl)) {
//			if(!F.empty(member.getPic())) {
//				FdPicture pic = fdPictureService.get(Integer.valueOf(member.getPic()));
//				if(pic != null) picUrl = PathUtil.getPicPath(pic.getPath());
//			}
//		}
//		member.setPicUrl(picUrl);
		member.setCustomer(fdCustomerService.get(member.getId().longValue()));
		return member;
	}

	@Override
	public List<FdMember> getByMobiles(String mobiles) {
		List<FdMember> ol = new ArrayList<FdMember>();
		Map<String, Object> params = new HashMap<String, Object>();
		String patientMobiles = "", doctorMobiles = "";
		for(String mobile : mobiles.split(",")) {
			if("admin".equals(mobile)) continue;

			String[] arr = mobile.split("-");
			if(Integer.valueOf(arr[0]) == 0) {
				patientMobiles += F.empty(patientMobiles) ? arr[1] : "," + arr[1];
			} else {
				doctorMobiles += F.empty(doctorMobiles) ? arr[1] : "," + arr[1];
			}
 		}
		List<TfdMember> l = new ArrayList<TfdMember>();
		if(!F.empty(patientMobiles)) {
			params.put("mobiles", patientMobiles.split(","));
			params.put("isAdmin", 0);
			l.addAll(fdMemberDao.find("from TfdMember t where t.status = 1 and t.mobile in (:mobiles) and t.isAdmin = :isAdmin ", params));
		}
		if(!F.empty(doctorMobiles)) {
			params.put("mobiles", doctorMobiles.split(","));
			params.put("isAdmin", 2);
			l.addAll(fdMemberDao.find("from TfdMember t where t.status = 1 and t.mobile in (:mobiles) and t.isAdmin = :isAdmin ", params));
		}

		if(CollectionUtils.isNotEmpty(l)) {
			for(TfdMember t : l) {
				FdMember o = new FdMember();
				BeanUtils.copyProperties(t, o);
				fillSimpleDoctorInfo(o);
				ol.add(o);
			}
		}
		return ol;
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
			if(!F.empty(fdMember.getMobile()) && t.getUsername().equals(t.getMobile())) {
				fdMember.setUsername(fdMember.getMobile());
			}
			MyBeanUtils.copyProperties(fdMember, t, new String[] { "id" , "addtime", "isdeleted","updatetime" },true);
		}
	}


	@Override
	public void editMember(FdMember member) {
		if(!F.empty(member.getMobile())) {
			FdMember old = get(member.getId());
			// 删除环信账号
			HuanxinUtil.delUser(old.getIsAdmin() + "-" + old.getMobile());
			member.setHxStatus(false);
		}
		this.edit(member);
		if(!F.empty(member.getRealName()) || !F.empty(member.getSex()) || !F.empty(member.getBirthday()) || !F.empty(member.getMobile())) {
			FdCustomer customer = new FdCustomer();
			customer.setUserId(member.getId().longValue());
			if(!F.empty(member.getBirthday())) {
				customer.setBirthday(DateUtil.parse(member.getBirthday(), Constants.DATE_FORMAT_YMD).getTime());
			}
			if(!F.empty(member.getRealName())) customer.setRealName(member.getRealName());
			if(!F.empty(member.getSex())) customer.setSex(member.getSex());
			if(!F.empty(member.getMobile())) customer.setPhone(member.getMobile());
			fdCustomerService.edit(customer);
		}

		if(!F.empty(member.getSpeciality()) || !F.empty(member.getIntroduce())) {
			FdMemberDoctor doctor = new FdMemberDoctor();
			doctor.setId(member.getId());
			doctor.setSpeciality(member.getSpeciality());
			doctor.setIntroduce(member.getIntroduce());
			fdMemberDoctorService.edit(doctor);
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
	public List<FdMember> queryAllByDelHxAccount() {
		int max = Integer.valueOf(Application.getString(HuanxinUtil.MAX_ACCOUNT_NUM, "90"));

		List<FdMember> ol = new ArrayList<FdMember>();
		String hql = " from TfdMember t where t.hxStatus = 1 ";
		Long count = fdMemberDao.count("select count(*) " + hql);
		if(count != null && count > max) {
			List<TfdMember> l = fdMemberDao.findBy(hql + " order by t.lastLoginTime desc ", max);
			if (l != null && l.size() > 0) {
				for (TfdMember t : l) {
					FdMember o = new FdMember();
					BeanUtils.copyProperties(t, o);
					ol.add(o);
				}
			}
		}
		return ol;
	}

	@Override
	public List<FdMember> queryMembersByGroupId(Integer groupId) {
		List<FdMember> ol = new ArrayList<FdMember>();
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("groupId", groupId);
		params.put("isAdmin", 2);
		List<TfdMember> l = fdMemberDao.find(" select t from TfdMember t, TfdMemberDoctor d where t.status = 1 and t.id = d.id and t.isAdmin = :isAdmin and d.groupId = :groupId ", params);
		if (l != null && l.size() > 0) {
			for (TfdMember t : l) {
				FdMember o = new FdMember();
				BeanUtils.copyProperties(t, o);
				fillSimpleDoctorInfo(o, false);
				ol.add(o);
			}
		}
		return ol;
	}

	@Override
	public boolean checkUsername(String username, Integer isAdmin) {
		if (!F.empty(username)) {
			List<TfdMember> l = fdMemberDao.find("from TfdMember t where t.status <> -1 and t.isAdmin = " + isAdmin + " and (t.username='" + username + "' or t.mobile='" + username + "') ", 1, 1);
			if (CollectionUtils.isEmpty(l)) {
				return false;
			}
		}
		return true;
	}

	@Override
	public FdMember get(FdMember fdMember) {
		String whereHql = "";
		if (fdMember != null) {
			whereHql += " where 1 = 1 ";
			Map<String, Object> params = new HashMap<String, Object>();
			if (!F.empty(fdMember.getStatusArr())) {
				whereHql += " and t.status in (:status)";
				Integer[] status = new Integer[fdMember.getStatusArr().split(",").length];
				for(int i=0; i<fdMember.getStatusArr().split(",").length; i++) {
					status[i] = Integer.valueOf(fdMember.getStatusArr().split(",")[i]);
				}
				params.put("status", status);
			} else {
				whereHql += " and t.status = 1";
			}
			if (!F.empty(fdMember.getId())) {
				whereHql += " and t.id = :id";
				params.put("id", fdMember.getId());
			}
			if (!F.empty(fdMember.getUsername())) {
				whereHql += " and (t.username = :username or t.mobile = :username) ";
				params.put("username", fdMember.getUsername());
			}

			if(!F.empty(fdMember.getPassword())) {
				whereHql += " and t.password = :password ";
				params.put("password", MD5Util.encryptPassword(fdMember.getPassword()));
			}

			if (fdMember.getIsAdmin() != null) {
				whereHql += " and t.isAdmin = :isAdmin";
				params.put("isAdmin", fdMember.getIsAdmin());
			}

			TfdMember t = fdMemberDao.get("from TfdMember t" + whereHql, params);
			if (t != null && t.getId() != null) {
				FdMember o = new FdMember();
				BeanUtils.copyProperties(t, o);
				return o;
			}
		}
		return null;
	}

	private void fillSimpleDoctorInfo(FdMember member, boolean status) {
		String picUrl = member.getHeadImage();
		if(F.empty(picUrl)) {
			if(!F.empty(member.getPic()) && !F.empty(member.getPic())) {
				FdPicture pic = fdPictureService.get(Integer.valueOf(member.getPic()));
				if(pic != null) picUrl = PathUtil.getPicPath(pic.getPath());
			}
		}
		member.setPicUrl(picUrl);

		member.setCustomer(fdCustomerService.get(member.getId().longValue()));
		member.setPatient(fdPatientService.get(member.getId()));
		FdMemberDoctor doctor = fdMemberDoctorService.get(member.getId());

		if(doctor != null) {
			FdMemberDoctorSh sh = fdMemberDoctorShService.get(doctor.getId(), 2);
			if(sh != null && "1".equals(sh.getStatus()) && status) { // 待审核
				member.setStatus(2);
				if(!F.empty(sh.getHospital())) {
					FdHospital hospital = fdHospitalService.get(sh.getHospital());
					doctor.setHospital(sh.getHospital());
					doctor.setHospitalName(hospital.getHospitalName());
				}
				if(!F.empty(sh.getLevel())) {
					FdMemberDoctorLevel level = fdMemberDoctorLevelService.get(sh.getLevel());
					doctor.setLevel(sh.getLevel());
					doctor.setLevelName(level.getName());
				}

				if(!F.empty(sh.getDepartment())) {
					FdHospitalDept dept = fdHospitalDeptService.get(sh.getDepartment());
					doctor.setDepartment(sh.getDepartment());
					doctor.setDepartmentName(dept.getName());
				}

				if(!F.empty(sh.getRealName())) member.getCustomer().setRealName(sh.getRealName());
				if(!F.empty(sh.getSex())) member.getCustomer().setSex(sh.getSex());
				if(!F.empty(sh.getBirthday())) member.getCustomer().setBirthday(sh.getBirthday());
				if(!F.empty(sh.getEmail())) member.setEmail(sh.getEmail());
				if(!F.empty(sh.getSpeciality())) doctor.setSpeciality(sh.getSpeciality());
				if(!F.empty(sh.getIntroduce())) doctor.setIntroduce(sh.getIntroduce());
				if(!F.empty(sh.getPics())) member.setPicUrl(sh.getPics());

			} else {
				if(!F.empty(doctor.getHospital())) {
					FdHospital hospital = fdHospitalService.get(doctor.getHospital());
					doctor.setHospitalName(hospital.getHospitalName());
				}
				if(!F.empty(doctor.getLevel())) {
					FdMemberDoctorLevel level = fdMemberDoctorLevelService.get(doctor.getLevel());
					doctor.setLevelName(level.getName());
				}

				if(F.empty(doctor.getDepartmentName())) {
					if(!F.empty(doctor.getDepartment())) {
						FdHospitalDept dept = fdHospitalDeptService.get(doctor.getDepartment());
						doctor.setDepartmentName(dept.getName());
					}
				}
			}

			member.setMemberDoctor(doctor);

		}
	}

	private void fillSimpleDoctorInfo(FdMember member) {
		fillSimpleDoctorInfo(member, true);
	}

}
