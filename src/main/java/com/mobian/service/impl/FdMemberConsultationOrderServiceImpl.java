package com.mobian.service.impl;

import com.mobian.absx.F;
import com.mobian.dao.FdMemberConsultationOrderDaoI;
import com.mobian.listener.Application;
import com.mobian.model.TfdMemberConsultationOrder;
import com.mobian.pageModel.*;
import com.mobian.service.*;
import com.mobian.thirdpart.easemob.HuanxinUtil;
import com.mobian.util.Constants;
import com.mobian.util.DateUtil;
import com.mobian.util.MyBeanUtils;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class FdMemberConsultationOrderServiceImpl extends BaseServiceImpl<FdMemberConsultationOrder> implements FdMemberConsultationOrderServiceI {

	@Autowired
	private FdMemberConsultationOrderDaoI fdMemberConsultationOrderDao;

	@Autowired
	private FdMemberConsultationExpireServiceI fdMemberConsultationExpireService;

	@Autowired
	private FdMemberConsultationFriendServiceI fdMemberConsultationFriendService;

	@Autowired
	private BasedataServiceI basedataService;

	@Autowired
	private FdMemberServiceI fdMemberService;

	@Autowired
	private FdMessageServiceI fdMessageService;

	@Override
	public DataGrid dataGrid(FdMemberConsultationOrder fdMemberConsultationOrder, PageHelper ph) {
		List<FdMemberConsultationOrder> ol = new ArrayList<FdMemberConsultationOrder>();
		String hql = " from TfdMemberConsultationOrder t ";
		DataGrid dg = dataGridQuery(hql, ph, fdMemberConsultationOrder, fdMemberConsultationOrderDao);
		@SuppressWarnings("unchecked")
		List<TfdMemberConsultationOrder> l = dg.getRows();
		if (l != null && l.size() > 0) {
			for (TfdMemberConsultationOrder t : l) {
				FdMemberConsultationOrder o = new FdMemberConsultationOrder();
				BeanUtils.copyProperties(t, o);
				ol.add(o);
			}
		}
		dg.setRows(ol);
		return dg;
	}
	

	protected String whereHql(FdMemberConsultationOrder fdMemberConsultationOrder, Map<String, Object> params) {
		String whereHql = "";	
		if (fdMemberConsultationOrder != null) {
			whereHql += " where 1=1 ";
			if (!F.empty(fdMemberConsultationOrder.getUserId())) {
				whereHql += " and t.userId = :userId";
				params.put("userId", fdMemberConsultationOrder.getUserId());
			}		
			if (!F.empty(fdMemberConsultationOrder.getDoctorId())) {
				whereHql += " and t.doctorId = :doctorId";
				params.put("doctorId", fdMemberConsultationOrder.getDoctorId());
			}		
			if (!F.empty(fdMemberConsultationOrder.getOrderNo())) {
				whereHql += " and t.orderNo = :orderNo";
				params.put("orderNo", fdMemberConsultationOrder.getOrderNo());
			}		
			if (!F.empty(fdMemberConsultationOrder.getCreateBy())) {
				whereHql += " and t.createBy = :createBy";
				params.put("createBy", fdMemberConsultationOrder.getCreateBy());
			}		
			if (!F.empty(fdMemberConsultationOrder.getCreateTime())) {
				whereHql += " and t.createTime = :createTime";
				params.put("createTime", fdMemberConsultationOrder.getCreateTime());
			}		
			if (!F.empty(fdMemberConsultationOrder.getUpdateBy())) {
				whereHql += " and t.updateBy = :updateBy";
				params.put("updateBy", fdMemberConsultationOrder.getUpdateBy());
			}		
			if (!F.empty(fdMemberConsultationOrder.getUpdateTime())) {
				whereHql += " and t.updateTime = :updateTime";
				params.put("updateTime", fdMemberConsultationOrder.getUpdateTime());
			}		
			if (!F.empty(fdMemberConsultationOrder.getStatus())) {
				whereHql += " and t.status = :status";
				params.put("status", fdMemberConsultationOrder.getStatus());
			}		
		}	
		return whereHql;
	}

	@Override
	public void add(FdMemberConsultationOrder fdMemberConsultationOrder) {
		TfdMemberConsultationOrder t = new TfdMemberConsultationOrder();
		BeanUtils.copyProperties(fdMemberConsultationOrder, t);
		//t.setId(jb.absx.UUID.uuid());
		fdMemberConsultationOrderDao.save(t);
	}

	@Override
	public FdMemberConsultationOrder get(Integer id) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		TfdMemberConsultationOrder t = fdMemberConsultationOrderDao.get("from TfdMemberConsultationOrder t  where t.id = :id", params);
		FdMemberConsultationOrder o = new FdMemberConsultationOrder();
		BeanUtils.copyProperties(t, o);
		return o;
	}

	@Override
	public void edit(FdMemberConsultationOrder fdMemberConsultationOrder) {
		TfdMemberConsultationOrder t = fdMemberConsultationOrderDao.get(TfdMemberConsultationOrder.class, fdMemberConsultationOrder.getId());
		if (t != null) {
			MyBeanUtils.copyProperties(fdMemberConsultationOrder, t, new String[] { "id" , "addtime", "isdeleted","updatetime" },true);
		}
	}

	@Override
	public void delete(Integer id) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		fdMemberConsultationOrderDao.executeHql("update TfdMemberConsultationOrder t set t.isdeleted = 1 where t.id = :id",params);
		//fdMemberConsultationOrderDao.delete(fdMemberConsultationOrderDao.get(TfdMemberConsultationOrder.class, id));
	}

	@Override
	public FdMemberConsultationOrder get(FdMemberConsultationOrder consultationOrder) {
		String hql = " from TfdMemberConsultationOrder t ";
		@SuppressWarnings("unchecked")
		List<TfdMemberConsultationOrder> l = query(hql, consultationOrder, fdMemberConsultationOrderDao);
		FdMemberConsultationOrder o = null;
		if (CollectionUtils.isNotEmpty(l)) {
			o = new FdMemberConsultationOrder();
			BeanUtils.copyProperties(l.get(0), o);
		}
		return o;
	}

	@Override
	public void updatePaySuccess(FdMemberConsultationOrder consultationOrder) {

		consultationOrder.setStatus("0");
		edit(consultationOrder);

		Calendar c = Calendar.getInstance();
		FdMemberConsultationExpire expire = fdMemberConsultationExpireService.getByUserIdAndDoctorId(consultationOrder.getUserId(), consultationOrder.getDoctorId());
		if(expire == null) {
			expire = new FdMemberConsultationExpire();
			expire.setUserId(consultationOrder.getUserId());
			expire.setDoctorId(consultationOrder.getDoctorId());

			c.add(Calendar.DAY_OF_MONTH, Integer.valueOf(Application.getString("SV300", "7")));
			expire.setExpireDate(c.getTime());
			fdMemberConsultationExpireService.add(expire);
		} else {
			if(expire.getExpireDate().getTime() > new Date().getTime()) {
				c.setTime(expire.getExpireDate());
			}
			c.add(Calendar.DAY_OF_MONTH, Integer.valueOf(Application.getString("SV300", "7")));
			expire.setExpireDate(c.getTime());
			fdMemberConsultationExpireService.edit(expire);
		}


		FdMember user = fdMemberService.getDetail(consultationOrder.getUserId());
		FdMember doctor = fdMemberService.getDetail(consultationOrder.getDoctorId());
		FdMemberConsultationFriend friend = fdMemberConsultationFriendService.getByUserIdAndDoctorId(consultationOrder.getUserId(), consultationOrder.getDoctorId());
		if(friend == null) {
			Random random = new Random();
			BaseData baseData = new BaseData();
			baseData.setBasetypeCode("WL");
			List<BaseData> bds = basedataService.getBaseDatas(baseData);
			String msg;
			if(CollectionUtils.isNotEmpty(bds)) {
				baseData = bds.get(random.nextInt(bds.size()));
				msg = baseData.getName();
			} else {
				msg = "您好，请问有什么需要咨询的吗？";
			}

//			HuanxinUtil.addFriend(user.getIsAdmin() + "-" + user.getMobile(), doctor.getIsAdmin() + "-" + doctor.getMobile());

			HuanxinUtil.sendTxtMessage(doctor.getIsAdmin() + "-" + doctor.getMobile(), user.getIsAdmin() + "-" + user.getMobile(),  msg);

			friend = new FdMemberConsultationFriend();
			friend.setUserId(consultationOrder.getUserId());
			friend.setDoctorId(consultationOrder.getDoctorId());
			friend.setLastTime(new Date());
			friend.setLastContent(msg);
			friend.setSenderType(1);
			fdMemberConsultationFriendService.add(friend);
		} else {
			friend.setStatus("0");
			friend.setLastTime(new Date());
			fdMemberConsultationFriendService.edit(friend);
		}

		// 添加用户消息
		FdMessage message = new FdMessage();
		message.setTitle("咨询提醒");
		message.setContent("尊敬的医生您好，手机号" + user.getMobile() + "用户成功支付了您的咨询服务，请注意查收咨询消息！");
		message.setUserId(consultationOrder.getDoctorId());
		message.setMtype("MT02");
		message.setIsRead(false);
		message.setAlias("2_" + doctor.getMobile());
		message.setPushMessage(new PushMessage("M201", "手机号" + user.getMobile() + "用户成功支付了您的咨询服务!"));
		fdMessageService.addAndPushMessage(message);

		message = new FdMessage();
		message.setTitle("咨询支付成功");
		message.setContent("尊敬的用户您好，您已成功支付了" + doctor.getCustomer().getRealName() + "医生的咨询服务，咨询截止时间为：" + DateUtil.format(expire.getExpireDate(), Constants.DATE_FORMAT) + "，在此期间如有任何疑问都可咨询医生，感谢您的支持！");
		message.setUserId(consultationOrder.getUserId());
		message.setMtype("MT02");
		message.setIsRead(false);
		message.setAlias("0_" + user.getMobile());
		fdMessageService.add(message);
	}

	@Override
	public FdMemberConsultationOrder getByOrderNo(String orderNo) {
		FdMemberConsultationOrder o = null;
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("orderNo", orderNo);
		TfdMemberConsultationOrder t = fdMemberConsultationOrderDao.get("from TfdMemberConsultationOrder t  where t.orderNo = :orderNo", params);
		if(t != null) {
			o = new FdMemberConsultationOrder();
			BeanUtils.copyProperties(t, o);
		}

		return o;
	}


}
