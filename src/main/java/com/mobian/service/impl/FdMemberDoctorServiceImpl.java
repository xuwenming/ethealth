package com.mobian.service.impl;

import com.mobian.absx.F;
import com.mobian.concurrent.CacheKey;
import com.mobian.concurrent.CompletionService;
import com.mobian.concurrent.Task;
import com.mobian.dao.BaseDaoI;
import com.mobian.dao.FdMemberDoctorDaoI;
import com.mobian.listener.Application;
import com.mobian.model.TfdMemberDoctor;
import com.mobian.pageModel.*;
import com.mobian.service.*;
import com.mobian.util.MyBeanUtils;
import com.mobian.util.PathUtil;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.*;

@Service
public class FdMemberDoctorServiceImpl extends BaseServiceImpl<FdMemberDoctor> implements FdMemberDoctorServiceI {

	@Autowired
	private FdMemberDoctorDaoI fdMemberDoctorDao;

	@Autowired
	private FdCustomerServiceI fdCustomerService;

	@Autowired
	private FdPictureServiceI fdPictureService;

	@Autowired
	private FdMemberServiceI fdMemberService;

	@Autowired
	private FdHospitalDeptServiceI fdHospitalDeptService;

	@Autowired
	private FdMemberDoctorLevelServiceI fdMemberDoctorLevelService;

	@Autowired
	private FdHospitalServiceI fdHospitalService;

	@Autowired
	private FdDoctorGroupServiceI fdDoctorGroupService;

	@Autowired
	private FdMemberDoctorShServiceI fdMemberDoctorShService;

	@Override
	public DataGrid dataGrid(FdMemberDoctor fdMemberDoctor, PageHelper ph) {
		List<FdMemberDoctor> ol = new ArrayList<FdMemberDoctor>();
		String hql = "select t from TfdMemberDoctor t, TfdMember m, TfdCustomer c, TfdHospital h, TfdHospitalDept d ";
		DataGrid dg = dataGridQuery(hql, ph, fdMemberDoctor, fdMemberDoctorDao);
		@SuppressWarnings("unchecked")
		List<TfdMemberDoctor> l = dg.getRows();
		if (l != null && l.size() > 0) {
			for (TfdMemberDoctor t : l) {
				FdMemberDoctor o = new FdMemberDoctor();
				BeanUtils.copyProperties(t, o);
				ol.add(o);
			}
		}
		dg.setRows(ol);
		return dg;
	}

	protected DataGrid dataGridQuery(String hql,PageHelper ph,FdMemberDoctor t,BaseDaoI dao){
		DataGrid dg = new DataGrid();
		Map<String, Object> params = new HashMap<String, Object>();
		String where = whereHql(t, params);
		List<TfdMemberDoctor> l = dao.find(hql  + where + orderHql(ph), params, ph.getPage(), ph.getRows());
		if(!ph.isHiddenTotal())
			dg.setTotal(dao.count("select count(*) " + hql.substring(hql.indexOf("from")) + where, params));
		dg.setRows(l);
		return dg;
	}
	

	protected String whereHql(FdMemberDoctor fdMemberDoctor, Map<String, Object> params) {
		String whereHql = "";	
		if (fdMemberDoctor != null) {
			whereHql += " where t.id = m.id and t.id = c.userId and t.hospital = h.id and t.department = d.id ";
			if(F.empty(fdMemberDoctor.getStatus())) {
				whereHql += " and m.status = 1 ";
			} else {
				whereHql += " and m.status in (1,2,3) ";
			}
			if (fdMemberDoctor.getIsBest() != null) {
				whereHql += " and t.isBest = :isBest";
				params.put("isBest", fdMemberDoctor.getIsBest());
			}
			if (!F.empty(fdMemberDoctor.getLevel())) {
				whereHql += " and t.level = :level";
				params.put("level", fdMemberDoctor.getLevel());
			}		
			if (!F.empty(fdMemberDoctor.getHospital())) {
				whereHql += " and t.hospital = :hospital";
				params.put("hospital", fdMemberDoctor.getHospital());
			}		
			if (!F.empty(fdMemberDoctor.getDepartment())) {
				whereHql += " and t.department = :department";
				params.put("department", fdMemberDoctor.getDepartment());
			}		
			if (!F.empty(fdMemberDoctor.getEducation())) {
				whereHql += " and t.education = :education";
				params.put("education", fdMemberDoctor.getEducation());
			}		
			if (!F.empty(fdMemberDoctor.getConsultingHour())) {
				whereHql += " and t.consultingHour = :consultingHour";
				params.put("consultingHour", fdMemberDoctor.getConsultingHour());
			}		
			if (!F.empty(fdMemberDoctor.getSpecialHour())) {
				whereHql += " and t.specialHour = :specialHour";
				params.put("specialHour", fdMemberDoctor.getSpecialHour());
			}		
			if (!F.empty(fdMemberDoctor.getSpeciality())) {
				whereHql += " and t.speciality = :speciality";
				params.put("speciality", fdMemberDoctor.getSpeciality());
			}		
			if (!F.empty(fdMemberDoctor.getIntroduce())) {
				whereHql += " and t.introduce = :introduce";
				params.put("introduce", fdMemberDoctor.getIntroduce());
			}		
			if (!F.empty(fdMemberDoctor.getPics())) {
				whereHql += " and t.pics = :pics";
				params.put("pics", fdMemberDoctor.getPics());
			}		
			if (!F.empty(fdMemberDoctor.getCreateBy())) {
				whereHql += " and t.createBy = :createBy";
				params.put("createBy", fdMemberDoctor.getCreateBy());
			}		
			if (!F.empty(fdMemberDoctor.getUpdateBy())) {
				whereHql += " and t.updateBy = :updateBy";
				params.put("updateBy", fdMemberDoctor.getUpdateBy());
			}		
			if (!F.empty(fdMemberDoctor.getGroupId())) {
				whereHql += " and t.groupId = :groupId";
				params.put("groupId", fdMemberDoctor.getGroupId());
			}
			if(!F.empty(fdMemberDoctor.getUsername())) {
				whereHql += " and c.realName like :realName";
				params.put("realName", "%" + fdMemberDoctor.getUsername() + "%");
			}
			if(!F.empty(fdMemberDoctor.getMobile())) {
				whereHql += " and m.mobile like :mobile";
				params.put("mobile", "%" + fdMemberDoctor.getMobile() + "%");
			}
			if(!F.empty(fdMemberDoctor.getKey())) {
				whereHql += " and (t.speciality like :key or c.realName like :key or h.hospitalName like :key or d.name like :key) ";
				params.put("key", "%" + fdMemberDoctor.getKey() + "%");
			}
		}	
		return whereHql;
	}

	@Override
	public void add(FdMemberDoctor fdMemberDoctor) {
		TfdMemberDoctor t = new TfdMemberDoctor();
		BeanUtils.copyProperties(fdMemberDoctor, t);
		//t.setId(jb.absx.UUID.uuid());
		if(F.empty(fdMemberDoctor.getSeq())) t.setSeq(0);
		t.setCreateTime(new Date());
		t.setUpdateTime(t.getCreateTime());
		fdMemberDoctorDao.save(t);
	}

	@Override
	public FdMemberDoctor get(Integer id) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		TfdMemberDoctor t = fdMemberDoctorDao.get("from TfdMemberDoctor t  where t.id = :id", params);
		if(t != null) {
			FdMemberDoctor o = new FdMemberDoctor();
			BeanUtils.copyProperties(t, o);
			return o;
		}

		return null;
	}

	@Override
	public void edit(FdMemberDoctor fdMemberDoctor) {
		TfdMemberDoctor t = fdMemberDoctorDao.get(TfdMemberDoctor.class, fdMemberDoctor.getId());
		if (t != null) {
			MyBeanUtils.copyProperties(fdMemberDoctor, t, new String[] { "id" , "addtime", "isdeleted","updatetime" },true);
		}
	}

	@Override
	public void delete(Integer id) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		fdMemberDoctorDao.executeHql("update TfdMemberDoctor t set t.isdeleted = 1 where t.id = :id",params);
		//fdMemberDoctorDao.delete(fdMemberDoctorDao.get(TfdMemberDoctor.class, id));
	}

	@Override
	public DataGrid dataGridComplex(FdMemberDoctor fdMemberDoctor, PageHelper ph) {

		return dataGridComplex(fdMemberDoctor, ph, false);
	}

	@Override
	public DataGrid dataGridMoreComplex(FdMemberDoctor fdMemberDoctor, PageHelper ph) {
		return dataGridComplex(fdMemberDoctor, ph, true);
	}

	private DataGrid dataGridComplex(FdMemberDoctor doctor, PageHelper ph, boolean isMoreComplex) {
		DataGrid dg = dataGrid(doctor, ph);
		List<FdMemberDoctor> ol = dg.getRows();
		if(CollectionUtils.isNotEmpty(ol)) {
			final CompletionService completionService = CompletionFactory.initCompletion();
			for(FdMemberDoctor o : ol) {
				// 设置头像
				completionService.submit(new Task<FdMemberDoctor, FdMember>(o) {
					@Override
					public FdMember call() throws Exception {
						FdMember member = fdMemberService.get(getD().getId());
						String picUrl = member.getHeadImage();
						if(F.empty(picUrl)) {
							FdPicture pic = fdPictureService.get(Integer.valueOf(member.getPic()));
							if(pic != null) picUrl = PathUtil.getPicPath(pic.getPath());
						}
						member.setPicUrl(picUrl);
						return member;
					}

					protected void set(FdMemberDoctor d, FdMember v) {
						if(v != null) {
							d.setPicUrl(v.getPicUrl());
							d.setStatus(v.getStatus() + "");
							d.setMobile(v.getMobile());
							d.setUsername(v.getUsername());
						}
					}
				});

				// 设置姓名信息
				completionService.submit(new Task<FdMemberDoctor, FdCustomer>(o) {
					@Override
					public FdCustomer call() throws Exception {
						return fdCustomerService.get(getD().getId().longValue());
					}

					protected void set(FdMemberDoctor d, FdCustomer v) {
						d.setCustomer(v);
					}
				});

				// 设置科室名称
				if(F.empty(o.getDepartmentName()))
					completionService.submit(new Task<FdMemberDoctor, String>(o) {
						@Override
						public String call() throws Exception {
							FdHospitalDept dept = fdHospitalDeptService.get(getD().getDepartment());
							return dept == null ? null : dept.getName();
						}

						protected void set(FdMemberDoctor d, String v) {
							if(!F.empty(v))
								d.setDepartmentName(v);
	//						else
	//							completionService.submit(new Task<FdMemberDoctor, String>(new CacheKey("fdMemberDoctorSh", d.getId() + ""), d) {
	//								@Override
	//								public String call() throws Exception {
	//									FdMemberDoctorSh sh = fdMemberDoctorShService.get(getD().getId());
	//									return sh == null ? null : sh.getDepartmentName();
	//								}
	//
	//								protected void set(FdMemberDoctor d, String v) {
	//									if(!F.empty(v))
	//										d.setDepartmentName(v);
	//
	//								}
	//							});
						}
					});


				if(isMoreComplex) {
					// 设置医生职称
					completionService.submit(new Task<FdMemberDoctor, String>(new CacheKey("fdLevel", o.getLevel() + ""), o) {
						@Override
						public String call() throws Exception {
							FdMemberDoctorLevel level = fdMemberDoctorLevelService.get(getD().getLevel());
							return level == null ? null : level.getName();
						}

						protected void set(FdMemberDoctor d, String v) {
							if(!F.empty(v))
								d.setLevelName(v);
						}
					});

					// 设置医院名称
					completionService.submit(new Task<FdMemberDoctor, String>(new CacheKey("fdHospital", o.getHospital() + ""), o) {
						@Override
						public String call() throws Exception {
							FdHospital hospital = fdHospitalService.get(getD().getHospital());
							return hospital == null ? null : hospital.getHospitalName();
						}

						protected void set(FdMemberDoctor d, String v) {
							if(!F.empty(v))
								d.setHospitalName(v);
//							else
//								completionService.submit(new Task<FdMemberDoctor, String>(new CacheKey("fdMemberDoctorSh", d.getId() + ""), d) {
//									@Override
//									public String call() throws Exception {
//										FdMemberDoctorSh sh = fdMemberDoctorShService.get(getD().getId());
//										return sh == null ? null : sh.getHospitalName();
//									}
//
//									protected void set(FdMemberDoctor d, String v) {
//										if (!F.empty(v))
//											d.setHospitalName(v);
//
//									}
//								});
						}
					});

					// 设置团队
					completionService.submit(new Task<FdMemberDoctor, String>(new CacheKey("fdDoctorGroup", o.getGroupId() + ""), o) {
						@Override
						public String call() throws Exception {
							FdDoctorGroup group = fdDoctorGroupService.get(getD().getGroupId());
							return group == null ? null : group.getGroupName();
						}

						protected void set(FdMemberDoctor d, String v) {
							if(!F.empty(v))
								d.setGroupName(v);
						}
					});

					// TODO 设置评分 设置门诊是否可预约
				}
			}

			completionService.sync();
		}
		return dg;

	}


	@Override
	public FdMemberDoctor getDetail(Integer id) {
		FdMemberDoctor doctor = get(id);

		FdMember member = fdMemberService.get(doctor.getId());
		String picUrl = member.getHeadImage();
		if(F.empty(picUrl)) {
			FdPicture pic = fdPictureService.get(Integer.valueOf(member.getPic()));
			if(pic != null) picUrl = PathUtil.getPicPath(pic.getPath());
		}
		doctor.setPicUrl(picUrl);
		doctor.setMobile(member.getMobile());
		doctor.setEmail(member.getEmail());
		doctor.setUsername(member.getUsername());

		doctor.setCustomer(fdCustomerService.get(doctor.getId().longValue()));

		if(F.empty(doctor.getDepartmentName())) {
			FdHospitalDept dept = fdHospitalDeptService.get(doctor.getDepartment());
			if(dept != null) doctor.setDepartmentName(dept.getName());
		}

		FdMemberDoctorLevel level = fdMemberDoctorLevelService.get(doctor.getLevel());
		if(level != null) doctor.setLevelName(level.getName());

		FdHospital hospital = fdHospitalService.get(doctor.getHospital());
		if(hospital != null) doctor.setHospitalName(hospital.getHospitalName());

		FdDoctorGroup group = fdDoctorGroupService.get(doctor.getGroupId());
		if(group != null) {
			doctor.setGroupName(group.getGroupName());
			if(!F.empty(group.getLeader()) && group.getLeader().intValue() == id.intValue()) {
				doctor.setLeader(true);
			}
		}

		return doctor;
	}

	@Override
	public DataGrid patientDataGrid(Integer doctorId, String name, PageHelper ph) {
		DataGrid dg = new DataGrid();
		List<FdMember> ol = new ArrayList<FdMember>();
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("doctorId", doctorId);
		String where = "";
		if(!F.empty(name)) {
			where = " where t.realName like :realName ";
			params.put("realName", "%" + name + "%");
		}
		int showWx = Integer.valueOf(Application.getString("SV600", "0"));
		String ow = "";
		if(showWx == 0) {
			ow = " and a.sourse = 'AS02' ";
		}
		String sql = "select DISTINCT t.user_id userId from "
				+ " (select a.user_id, a.create_time, a.appoint_name realName from fd_member_appointment a where a.status = 1 and a.doctor_id = :doctorId " + ow
				+ " UNION "
				+ " select c.user_id, c.create_time, u.real_name realName from fd_member_consultation_friend c left join fd_customer u on u.user_id = c.user_id where c.status = 0 and c.doctor_id = :doctorId) t "
				+ where;
		List<Map> l = fdMemberDoctorDao.findBySql2Map(sql + " order by t.create_time desc ", params, ph.getPage(), ph.getRows());
		if(CollectionUtils.isNotEmpty(l)) {
			CompletionService completionService = CompletionFactory.initCompletion();
			for(Map m : l) {
				final Integer userId = (Integer)m.get("userId");
				completionService.submit(new Task<List<FdMember>, FdMember>(new CacheKey("fdMember", userId + ""), ol) {
					@Override
					public FdMember call() throws Exception {
						return fdMemberService.getDetail(userId);
					}

					protected void set(List<FdMember> d, FdMember v) {
						d.add(v);
					}
				});
			}
			completionService.sync();
			dg.setRows(ol);

			BigInteger count = fdMemberDoctorDao.countBySql("select count(DISTINCT t.user_id) " + sql.substring(sql.indexOf("from")), params);
			dg.setTotal(count == null ? 0 : count.longValue());
		}
		return dg;
	}

}
