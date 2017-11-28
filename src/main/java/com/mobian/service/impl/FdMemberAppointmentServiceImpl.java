package com.mobian.service.impl;

import com.mobian.absx.F;
import com.mobian.dao.FdMemberAppointmentDaoI;
import com.mobian.model.TfdMemberAppointment;
import com.mobian.pageModel.DataGrid;
import com.mobian.pageModel.FdMemberAppointment;
import com.mobian.pageModel.PageHelper;
import com.mobian.service.FdMemberAppointmentServiceI;
import com.mobian.util.MyBeanUtils;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class FdMemberAppointmentServiceImpl extends BaseServiceImpl<FdMemberAppointment> implements FdMemberAppointmentServiceI {

	@Autowired
	private FdMemberAppointmentDaoI fdMemberAppointmentDao;

	@Override
	public DataGrid dataGrid(FdMemberAppointment fdMemberAppointment, PageHelper ph) {
		List<FdMemberAppointment> ol = new ArrayList<FdMemberAppointment>();
		String hql = " from TfdMemberAppointment t ";
		DataGrid dg = dataGridQuery(hql, ph, fdMemberAppointment, fdMemberAppointmentDao);
		@SuppressWarnings("unchecked")
		List<TfdMemberAppointment> l = dg.getRows();
		if (l != null && l.size() > 0) {
			for (TfdMemberAppointment t : l) {
				FdMemberAppointment o = new FdMemberAppointment();
				BeanUtils.copyProperties(t, o);
				ol.add(o);
			}
		}
		dg.setRows(ol);
		return dg;
	}
	

	protected String whereHql(FdMemberAppointment fdMemberAppointment, Map<String, Object> params) {
		String whereHql = "";	
		if (fdMemberAppointment != null) {
			whereHql += " where 1 = 1 ";
			if (!F.empty(fdMemberAppointment.getTime())) {
				whereHql += " and t.time = :time";
				params.put("time", fdMemberAppointment.getTime());
			}		
			if (!F.empty(fdMemberAppointment.getUserId())) {
				whereHql += " and t.userId = :userId";
				params.put("userId", fdMemberAppointment.getUserId());
			}		
			if (!F.empty(fdMemberAppointment.getDoctorId())) {
				whereHql += " and t.doctorId = :doctorId";
				params.put("doctorId", fdMemberAppointment.getDoctorId());
			}		
			if (!F.empty(fdMemberAppointment.getAppointTime())) {
				whereHql += " and t.appointTime = :appointTime";
				params.put("appointTime", fdMemberAppointment.getAppointTime());
			}		
			if (!F.empty(fdMemberAppointment.getSecondTime())) {
				whereHql += " and t.secondTime = :secondTime";
				params.put("secondTime", fdMemberAppointment.getSecondTime());
			}		
			if (!F.empty(fdMemberAppointment.getAppointName())) {
				whereHql += " and t.appointName = :appointName";
				params.put("appointName", fdMemberAppointment.getAppointName());
			}		
			if (!F.empty(fdMemberAppointment.getLinkName())) {
				whereHql += " and t.linkName = :linkName";
				params.put("linkName", fdMemberAppointment.getLinkName());
			}		
			if (!F.empty(fdMemberAppointment.getLinkWay())) {
				whereHql += " and t.linkWay = :linkWay";
				params.put("linkWay", fdMemberAppointment.getLinkWay());
			}		
			if (!F.empty(fdMemberAppointment.getAppointMessage())) {
				whereHql += " and t.appointMessage = :appointMessage";
				params.put("appointMessage", fdMemberAppointment.getAppointMessage());
			}		
			if (!F.empty(fdMemberAppointment.getCreateBy())) {
				whereHql += " and t.createBy = :createBy";
				params.put("createBy", fdMemberAppointment.getCreateBy());
			}		
			if (!F.empty(fdMemberAppointment.getCreateTime())) {
				whereHql += " and t.createTime = :createTime";
				params.put("createTime", fdMemberAppointment.getCreateTime());
			}		
			if (!F.empty(fdMemberAppointment.getUpdateBy())) {
				whereHql += " and t.updateBy = :updateBy";
				params.put("updateBy", fdMemberAppointment.getUpdateBy());
			}		
			if (!F.empty(fdMemberAppointment.getUpdateTime())) {
				whereHql += " and t.updateTime = :updateTime";
				params.put("updateTime", fdMemberAppointment.getUpdateTime());
			}		
			if (!F.empty(fdMemberAppointment.getStatus())) {
				whereHql += " and t.status = :status";
				params.put("status", fdMemberAppointment.getStatus());
			} else {
				whereHql += " and (t.sourse = 'AS02' or t.sourse = 'AS01' and t.status = '1') ";
			}
			if (!F.empty(fdMemberAppointment.getCouponId())) {
				whereHql += " and t.couponId = :couponId";
				params.put("couponId", fdMemberAppointment.getCouponId());
			}		
			if (!F.empty(fdMemberAppointment.getPics())) {
				whereHql += " and t.pics = :pics";
				params.put("pics", fdMemberAppointment.getPics());
			}		
			if (!F.empty(fdMemberAppointment.getAppointStatus())) {
				whereHql += " and t.appointStatus in (:appointStatus)";
				params.put("appointStatus", fdMemberAppointment.getAppointStatus().split(","));
			}		
			if (!F.empty(fdMemberAppointment.getAdjustment())) {
				whereHql += " and t.adjustment = :adjustment";
				params.put("adjustment", fdMemberAppointment.getAdjustment());
			}		
			if (!F.empty(fdMemberAppointment.getRefuseReason())) {
				whereHql += " and t.refuseReason = :refuseReason";
				params.put("refuseReason", fdMemberAppointment.getRefuseReason());
			}		
			if (!F.empty(fdMemberAppointment.getConfirmTime())) {
				whereHql += " and t.confirmTime = :confirmTime";
				params.put("confirmTime", fdMemberAppointment.getConfirmTime());
			}		
			if (!F.empty(fdMemberAppointment.getFirstConfirm())) {
				whereHql += " and t.firstConfirm = :firstConfirm";
				params.put("firstConfirm", fdMemberAppointment.getFirstConfirm());
			}		
			if (!F.empty(fdMemberAppointment.getFile())) {
				whereHql += " and t.file = :file";
				params.put("file", fdMemberAppointment.getFile());
			}		
		}	
		return whereHql;
	}

	@Override
	public void add(FdMemberAppointment fdMemberAppointment) {
		TfdMemberAppointment t = new TfdMemberAppointment();
		BeanUtils.copyProperties(fdMemberAppointment, t);
		//t.setId(jb.absx.UUID.uuid());
		fdMemberAppointmentDao.save(t);
		fdMemberAppointment.setId(t.getId());
	}

	@Override
	public FdMemberAppointment get(Integer id) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		TfdMemberAppointment t = fdMemberAppointmentDao.get("from TfdMemberAppointment t  where t.id = :id", params);
		FdMemberAppointment o = new FdMemberAppointment();
		BeanUtils.copyProperties(t, o);
		return o;
	}

	@Override
	public FdMemberAppointment get(FdMemberAppointment fdMemberAppointment) {
		String hql = " from TfdMemberAppointment t ";
		@SuppressWarnings("unchecked")
		List<TfdMemberAppointment> l = query(hql, fdMemberAppointment, fdMemberAppointmentDao);
		FdMemberAppointment o = null;
		if (CollectionUtils.isNotEmpty(l)) {
			o = new FdMemberAppointment();
			BeanUtils.copyProperties(l.get(0), o);
		}
		return o;
	}

	@Override
	public FdMemberAppointment getByAppointmentNo(String appointmentNo) {
		FdMemberAppointment o = null;
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("appointmentNo", appointmentNo);
		TfdMemberAppointment t = fdMemberAppointmentDao.get("from TfdMemberAppointment t  where t.appointmentNo = :appointmentNo", params);
		if(t != null) {
			o = new FdMemberAppointment();
			BeanUtils.copyProperties(t, o);
		}
		return o;
	}

	@Override
	public void edit(FdMemberAppointment fdMemberAppointment) {
		TfdMemberAppointment t = fdMemberAppointmentDao.get(TfdMemberAppointment.class, fdMemberAppointment.getId());
		if (t != null) {
			MyBeanUtils.copyProperties(fdMemberAppointment, t, new String[] { "id" , "addtime", "isdeleted","updatetime" },true);
		}
	}

	@Override
	public void delete(Integer id) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		fdMemberAppointmentDao.executeHql("update TfdMemberAppointment t set t.isdeleted = 1 where t.id = :id",params);
		//fdMemberAppointmentDao.delete(fdMemberAppointmentDao.get(TfdMemberAppointment.class, id));
	}

}
