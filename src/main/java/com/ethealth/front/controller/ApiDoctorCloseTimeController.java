package com.ethealth.front.controller;

import com.mobian.absx.F;
import com.mobian.concurrent.CompletionService;
import com.mobian.concurrent.Task;
import com.mobian.controller.BaseController;
import com.mobian.exception.ServiceException;
import com.mobian.listener.Application;
import com.mobian.pageModel.*;
import com.mobian.service.*;
import com.mobian.service.impl.CompletionFactory;
import com.mobian.util.Constants;
import com.mobian.util.DateUtil;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 首页接口
 * 
 * @author John
 * 
 */
@Controller
@RequestMapping("/api/doctorCloseTime")
public class ApiDoctorCloseTimeController extends BaseController {

	@Autowired
	private FdDoctorCloseTimeServiceI fdDoctorCloseTimeService;

	/**
	 * 获取停诊列表接口
	 */
	@RequestMapping("/dataGrid")
	@ResponseBody
	public Json dataGrid(FdDoctorCloseTime closeTime, PageHelper ph, HttpServletRequest request) {
		Json j = new Json();
		try{
			SessionInfo s = getSessionInfo(request);
			if(ph.getRows() == 0 || ph.getRows() > 50) {
				ph.setRows(10);
			}
			closeTime.setDoctorId(Integer.valueOf(s.getId()));
			j.setObj(fdDoctorCloseTimeService.dataGrid(closeTime, ph));
			j.setSuccess(true);
			j.setMsg("获取列表成功！");

		} catch (ServiceException e) {
			j.setObj(e.getMessage());
			logger.error(" 获取停诊列表接口异常", e);
		} catch(Exception e){
			j.setMsg(Application.getString(EX_0001));
			logger.error(" 获取停诊列表接口异常", e);
		}

		return j;
	}

	/**
	 * 添加停诊时间
	 */
	@RequestMapping("/add")
	@ResponseBody
	public Json add(FdDoctorCloseTime closeTime, String closeDateStr, HttpServletRequest request) {
		Json j = new Json();
		try{
			if(F.empty(closeDateStr)) {
				j.setMsg("请选择停诊时间");
				return j;
			}
			SessionInfo s = getSessionInfo(request);
			Integer time = closeTime.getTime();
			closeTime.setCloseDate(DateUtil.parse(closeDateStr, Constants.DATE_FORMAT_YMD));
			closeTime.setDoctorId(Integer.valueOf(s.getId()));
			closeTime.setTime(time == null ? 0 : time);
			PageHelper ph = new PageHelper();
			ph.setHiddenTotal(true);
			List<FdDoctorCloseTime> times = fdDoctorCloseTimeService.dataGrid(closeTime, ph).getRows();
			if(CollectionUtils.isNotEmpty(times)) {
				j.setMsg("添加失败，停诊时间重复");
				return j;
			}

			fdDoctorCloseTimeService.addCloseTime(closeTime);



			j.setSuccess(true);
			j.setMsg("添加成功！");

		} catch (ServiceException e) {
			j.setObj(e.getMessage());
			logger.error("添加停诊时间接口异常", e);
		} catch(Exception e){
			j.setMsg(Application.getString(EX_0001));
			logger.error("添加停诊时间接口异常", e);
		}

		return j;
	}

	/**
	 * 删除停诊时间
	 */
	@RequestMapping("/del")
	@ResponseBody
	public Json del(Integer id, HttpServletRequest request) {
		Json j = new Json();
		try{
			fdDoctorCloseTimeService.delete(id);

			j.setSuccess(true);
			j.setMsg("删除成功！");

		} catch (ServiceException e) {
			j.setObj(e.getMessage());
			logger.error("删除停诊时间接口异常", e);
		} catch(Exception e){
			j.setMsg(Application.getString(EX_0001));
			logger.error("删除停诊时间接口异常", e);
		}

		return j;
	}

}
