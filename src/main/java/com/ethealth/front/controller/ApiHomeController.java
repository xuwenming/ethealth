package com.ethealth.front.controller;

import com.mobian.absx.F;
import com.mobian.concurrent.CacheKey;
import com.mobian.concurrent.CompletionService;
import com.mobian.concurrent.Task;
import com.mobian.controller.BaseController;
import com.mobian.enums.EnumConstants;
import com.mobian.listener.Application;
import com.mobian.pageModel.*;
import com.mobian.service.*;
import com.mobian.service.impl.CompletionFactory;
import com.mobian.util.Constants;
import com.mobian.util.DateUtil;
import com.mobian.util.PathUtil;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

/**
 * 首页接口
 * 
 * @author John
 * 
 */
@Controller
@RequestMapping("/api/home")
public class ApiHomeController extends BaseController {

	@Autowired
	private FdBannerServiceI fdBannerService;

	@Autowired
	private FdDoctorGroupServiceI fdDoctorGroupService;

	@Autowired
	private FdMemberConsultationFriendServiceI fdMemberConsultationFriendService;

	@Autowired
	private FdCustomerServiceI fdCustomerService;

	@Autowired
	private FdMemberServiceI fdMemberService;

	@Autowired
	private FdMessageServiceI fdMessageService;

	@Autowired
	private FdDoctorCloseTimeServiceI fdDoctorCloseTimeService;

	/**
	 * 获取首页数据接口
	 */
	@RequestMapping("/home")
	@ResponseBody
	public Json home() {
		Json j = new Json();
		try{
			Map<String, Object> obj = new HashMap<String, Object>();

			// 轮播图
			FdBanner banner = new FdBanner();
			PageHelper ph = new PageHelper();
			banner.setSource("1");
			ph.setHiddenTotal(true);
			obj.put("banners", fdBannerService.dataGridComplex(banner, ph).getRows());

			// 专家团队
			ph = new PageHelper();
			ph.setHiddenTotal(true);
			obj.put("doctorGroups", fdDoctorGroupService.dataGridComplex(new FdDoctorGroup(), ph).getRows());

			// 就诊动态
			ph = new PageHelper();
			ph.setPage(1);
			ph.setRows(5);
			ph.setHiddenTotal(true);
			ph.setSort("lastTime");
			ph.setOrder("desc");
			FdMemberConsultationFriend friend = new FdMemberConsultationFriend();
			friend.setIsAdmin(0);
			List<FdMemberConsultationFriend> friends = fdMemberConsultationFriendService.dataGridComplex(friend, ph).getRows();
			if(CollectionUtils.isNotEmpty(friends)) {
				CompletionService completionService = CompletionFactory.initCompletion();
				for(FdMemberConsultationFriend f : friends) {
					completionService.submit(new Task<FdMemberConsultationFriend, String>(new CacheKey("fdCustomer", f.getUserId() + ""), f) {
						@Override
						public String call() throws Exception {
							String str = "";
							FdCustomer customer = fdCustomerService.get(Long.valueOf(getD().getUserId()));
							if(customer != null) {
								if(!F.empty(customer.getRealName())) {
									str = customer.getRealName().substring(0, 1) + "**";
								} else {
									str = customer.getPhone().substring(0, 3) + "****" + customer.getPhone().substring(customer.getPhone().length() - 4);
								}
							}
							return str;
						}

						protected void set(FdMemberConsultationFriend d, String v) {
							d.setUserName(v);
						}
					});
					completionService.submit(new Task<FdMemberConsultationFriend, String>(new CacheKey("fdMember", f.getUserId() + ""), f) {
						@Override
						public String call() throws Exception {
							return fdMemberService.get(getD().getUserId()).getMobile();
						}

						protected void set(FdMemberConsultationFriend d, String v) {
							d.setUserMobile(v);
						}
					});
				}
				completionService.sync();
			}
			obj.put("consultationDynamic", friends);

			// 滚动消息
			List<FdDynamicMessage> messageDynamics = new ArrayList<FdDynamicMessage>();

			// 系统消息
			FdMessage fdMessage = new FdMessage();
			fdMessage.setConsumerType(1);
			fdMessage.setStatus("ST01");
			fdMessage.setIsPushed(true);
			fdMessage.setMtype("MT01,MT03");
			ph = new PageHelper();
			ph.setPage(1);
			ph.setRows(5);
			ph.setHiddenTotal(true);
			ph.setSort("createTime");
			ph.setOrder("desc");
			List<FdMessage> messages = fdMessageService.dataGrid(fdMessage, ph).getRows();
			if(CollectionUtils.isNotEmpty(messages)) {
				for(FdMessage message : messages) {
					FdDynamicMessage d = new FdDynamicMessage();
					d.setCreateTime(message.getCreateTime());
					d.setContent(message.getPushContent());
					messageDynamics.add(d);
				}
			}
			// 停诊通知
			ph = new PageHelper();
			ph.setPage(1);
			ph.setRows(5);
			ph.setHiddenTotal(true);
			ph.setSort("createTime");
			ph.setOrder("desc");
			List<FdDoctorCloseTime> closeTimes = fdDoctorCloseTimeService.dataGrid(new FdDoctorCloseTime(), ph).getRows();
			if(CollectionUtils.isNotEmpty(closeTimes)) {
				for(FdDoctorCloseTime closeTime : closeTimes) {
					String time = DateUtil.format(closeTime.getCloseDate(), Constants.DATE_FORMAT_YMD) + closeTime.getWeek();
					if(closeTime.getTime() == 0) time += "全天";
					else if(closeTime.getTime() == 1) time += "上午";
					else if(closeTime.getTime() == 2) time += "下午";
					else if(closeTime.getTime() == 3) time += "夜班";
					FdDynamicMessage d = new FdDynamicMessage();
					d.setCreateTime(closeTime.getCreateTime());
					FdMember member = fdMemberService.getSimple(closeTime.getDoctorId());
					d.setContent("停诊通知：" + member.getCustomer().getRealName() + "医生与" + time + "发布了停诊");
					messageDynamics.add(d);
				}
			}

			Collections.sort(messageDynamics, new Comparator<FdDynamicMessage>() {
				public int compare(FdDynamicMessage arg0, FdDynamicMessage arg1) {
					return arg1.getCreateTime().compareTo(arg0.getCreateTime());
				}
			});

			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~:" + messageDynamics.size());
			obj.put("messageDynamic", messageDynamics);


			j.setSuccess(true);
			j.setMsg("获取首页数据成功！");
			j.setObj(obj);
		}catch(Exception e){
			j.setMsg(Application.getString(EX_0001));
			logger.error("获取首页数据接口异常", e);
		}

		return j;
	}

	/**
	 * 获取banner列表接口
	 */
	@RequestMapping("/banners")
	@ResponseBody
	public Json banners(FdBanner banner, PageHelper ph) {
		Json j = new Json();
		try{
			// TODO 先使用患者轮播图
			if("6".equals(banner.getSource()))
				banner.setSource("1");
			ph.setHiddenTotal(true);
			j.setObj(fdBannerService.dataGridComplex(banner, ph).getRows());

			j.setSuccess(true);
			j.setMsg("获取banner列表成功！");

		}catch(Exception e){
			j.setMsg(Application.getString(EX_0001));
			logger.error("获取banner列表接口异常", e);
		}

		return j;
	}

}
