package com.mobian.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.mobian.absx.F;
import com.mobian.dao.FdMemberDoctorDaoI;
import com.mobian.model.TfdMemberDoctor;
import com.mobian.pageModel.FdMemberDoctor;
import com.mobian.pageModel.DataGrid;
import com.mobian.pageModel.PageHelper;
import com.mobian.service.FdMemberDoctorServiceI;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mobian.util.MyBeanUtils;

@Service
public class FdMemberDoctorServiceImpl extends BaseServiceImpl<FdMemberDoctor> implements FdMemberDoctorServiceI {

	@Autowired
	private FdMemberDoctorDaoI fdMemberDoctorDao;

	@Override
	public DataGrid dataGrid(FdMemberDoctor fdMemberDoctor, PageHelper ph) {
		List<FdMemberDoctor> ol = new ArrayList<FdMemberDoctor>();
		String hql = " from TfdMemberDoctor t ";
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
	

	protected String whereHql(FdMemberDoctor fdMemberDoctor, Map<String, Object> params) {
		String whereHql = "";	
		if (fdMemberDoctor != null) {
			whereHql += " where 1=1 ";
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
		}	
		return whereHql;
	}

	@Override
	public void add(FdMemberDoctor fdMemberDoctor) {
		TfdMemberDoctor t = new TfdMemberDoctor();
		BeanUtils.copyProperties(fdMemberDoctor, t);
		//t.setId(jb.absx.UUID.uuid());
		fdMemberDoctorDao.save(t);
	}

	@Override
	public FdMemberDoctor get(Integer id) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		TfdMemberDoctor t = fdMemberDoctorDao.get("from TfdMemberDoctor t  where t.id = :id", params);
		FdMemberDoctor o = new FdMemberDoctor();
		BeanUtils.copyProperties(t, o);
		return o;
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

}
