package com.ethealth.front.controller;

import com.mobian.controller.BaseController;
import com.mobian.exception.ServiceException;
import com.mobian.listener.Application;
import com.mobian.pageModel.FdFeedback;
import com.mobian.pageModel.Json;
import com.mobian.pageModel.SessionInfo;
import com.mobian.service.FdFeedbackServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * 意见反馈接口
 * 
 * @author John
 * 
 */
@Controller
@RequestMapping("/api/feedback")
public class ApiFeedbackController extends BaseController {

	@Autowired
	private FdFeedbackServiceI fdFeedbackService;

	/**
	 * 添加意见反馈接口
	 */
	@RequestMapping("/add")
	@ResponseBody
	public Json add(FdFeedback fdFeedback, HttpServletRequest request) {
		Json j = new Json();
		try{
			SessionInfo s = getSessionInfo(request);
			fdFeedback.setCreateBy(Integer.valueOf(s.getId()));
			fdFeedbackService.add(fdFeedback);
			j.setSuccess(true);
			j.setMsg("添加成功！");
		} catch (ServiceException e) {
			j.setObj(e.getMessage());
			logger.error("添加意见反馈接口异常", e);
		}catch(Exception e){
			j.setMsg(Application.getString(EX_0001));
			logger.error("添加意见反馈接口异常", e);
		}

		return j;
	}

}
