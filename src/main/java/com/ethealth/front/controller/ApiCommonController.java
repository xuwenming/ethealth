package com.ethealth.front.controller;

import com.mobian.absx.F;
import com.mobian.controller.BaseController;
import com.mobian.exception.ServiceException;
import com.mobian.interceptors.TokenManage;
import com.mobian.listener.Application;
import com.mobian.pageModel.*;
import com.mobian.service.*;
import com.mobian.service.impl.RedisUserServiceImpl;
import com.mobian.thirdpart.wx.SignUtil;
import com.mobian.thirdpart.wx.WeixinUtil;
import com.mobian.thirdpart.yunpian.YunpianUtil;
import com.mobian.util.HttpUtil;
import com.mobian.util.ImageUtils;
import com.mobian.util.Util;
import com.yunpian.sdk.model.Result;
import com.yunpian.sdk.model.SmsSingleSend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 公共模块接口
 * 
 * @author John
 * 
 */
@Controller
@RequestMapping("/api/apiCommon")
public class ApiCommonController extends BaseController {


	@Autowired
	private TokenManage tokenManage;


	@Autowired
	private BugServiceI bugService;

	@Autowired
	private FdMedicinePracticeServiceI fdMedicinePracticeService;

	@Autowired
	private FdMedicineScienceServiceI fdMedicineScienceService;

	@Autowired
	private RedisUserServiceImpl redisUserService;

	@Autowired
	private FdMemberDoctorLevelServiceI fdMemberDoctorLevelService;

	@Autowired
	private FdDoctorOpinionServiceI fdDoctorOpinionService;
	
	/**
	 * 生成html
	 * @return
	 */
	@RequestMapping("/html")
	public void html(String type,Integer id,HttpServletResponse response) {
		PrintWriter out = null;
		String content = "";
		try{
			response.setContentType("text/html");  
			response.setCharacterEncoding("UTF-8");

			if("BT01".equals(type)) {
				content = fdMedicinePracticeService.get(id).getContent();
			} else if("BT02".equals(type)) {
				content = fdMedicineScienceService.get(id).getContent();
			}

			content = ImageUtils.replaceHtmlTag(content, "img", "src", "src=\"", "\"", null);

			out = response.getWriter();
			out.write("<html><head>");
			out.write("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no\">");
			out.write("<style type=\"text/css\">");
			out.write("body {font-family:\"微软雅黑\";background-color:#f8f7f5;}");
			out.write("ul,ol,li{padding:0; margin:0;}");
			out.write("img{border:0; line-height:0; width: 100%;}");
			out.write("ol,ul {list-style:none;}");
			out.write("a { color: #000; text-decoration: none; outline: none;}");
			out.write("a img { border: none; }");
			out.write("</style></head><body>");
			out.write(content);
			out.write("</body></html>");
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(out!=null){
				out.flush();
				out.close();
			}
		}	
	}

	/**
	 * 获取短信验证码(钱包支付、提现)
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getVCode")
	public Json getVCode(HttpServletRequest request) {
		Json j = new Json();
		try {
			SessionInfo s = getSessionInfo(request);
			String code = Util.CreateNonceNumstr(6); // 生成短信验证码
			Result<SmsSingleSend> result = YunpianUtil.single_send(s.getName(), Application.getDescString(YunpianUtil.VCODE_100).replace("#code#", code));
			if(result.getCode() == 8 || result.getCode() == 22 || result.getCode() == 33) {
				j.setMsg("访问过于频繁，请秒后重试！");
				return j;
			}

			if(result.getCode() == 0) {
				redisUserService.setValidateCode(s.getName(), code, 600L); // 10分钟
				j.setSuccess(true);
				j.setMsg("获取短信验证码成功！");
				return j;
			}
			j.setMsg("获取短信验证码失败！");
		} catch (ServiceException e) {
			j.setObj(e.getMessage());
			logger.error("获取短信验证码接口异常", e);
		} catch (Exception e) {
			j.setMsg(Application.getString(EX_0001));
			logger.error("获取短信验证码接口异常", e);
		}
		return j;
	}

	/**
	 * 获取职称列表
	 * @param
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getLevels")
	public Json getLevels() {
		Json j = new Json();
		try {
			PageHelper ph = new PageHelper();
			ph.setHiddenTotal(true);
			j.setObj(fdMemberDoctorLevelService.dataGrid(new FdMemberDoctorLevel(), ph).getRows());
			j.setSuccess(true);
			j.setMsg("获取成功！");

		} catch (Exception e) {
			j.setMsg(Application.getString(EX_0001));
			logger.error("获取职称列表接口异常", e);
		}
		return j;
	}
	
	/**
	 * 
	 * @param
	 * @param
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/error")
	public Json error() {
		Json j = new Json();
		j.setObj("token_expire");
		j.setSuccess(false);
		j.setMsg("token过期，请重新登录！");
		return j;
	}

	/**
	 * 微信JS-SDK权限签名
	 * @param signUrl
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/wxJSSign")
	public Json wxJSSign(String signUrl) {
		Json j = new Json();
		try {
			Map<String, String> signMap = SignUtil.sign(signUrl.split("#")[0]);
			signMap.put("appId", Application.getString(WeixinUtil.APPID));
			j.success();
			j.setObj(signMap);
			j.setMsg("微信JS-SDK权限签名成功！");
		}catch(Exception e){
			j.setMsg(Application.getString(EX_0001));
			logger.error("微信JS-SDK权限签名接口异常", e);
		}

		return j;
	}

	@ResponseBody
	@RequestMapping("/validToken")
	public Json validToken() {
		Json j = new Json();
		j.success();
		j.setMsg("token有效！");
		return j;
	}
	
	/**
	 * 分享统一入口
	 * @param
	 * @param
	 * @return
	 * eg: http://localhost:8083/api/apiCommon/share?id=379&type=BT01
	 */
	@RequestMapping("/share")
	public String share(Integer id,String type,HttpServletRequest request) {
		String title = "";
		String author = "医家盟";
		String content = "";
		Date date = null;
		Calendar c = Calendar.getInstance();
		if("BT01".equals(type)) {
			FdMedicinePractice t = fdMedicinePracticeService.get(id);
			content = t.getContent();
			content = ImageUtils.replaceHtmlTag(content, "img", "src", "src=\"", "\"", null);
			title = t.getTitle();
			c.setTimeInMillis(t.getCreateTime());
			date = c.getTime();
		} else if("BT02".equals(type)) {
			FdMedicineScience t = fdMedicineScienceService.get(id);
			content = t.getContent();
			content = ImageUtils.replaceHtmlTag(content, "img", "src", "src=\"", "\"", null);
			title = t.getTitle();
			c.setTimeInMillis(t.getCreateTime());
			date = c.getTime();
		} else if("BT03".equals(type)) {
			FdDoctorOpinion t = fdDoctorOpinionService.getDetail(id);
			if(!F.empty(t.getFileToImgs())) {
				String[] imgs = t.getFileToImgs().split(",");
				for(String img : imgs) {
					content += "<img src=\""+img+"\" />";
				}
			}
			title = t.getTitle();
			c.setTimeInMillis(t.getCreateTime());
			date = c.getTime();
			author = t.getUserName();
		} else if("BT04".equals(type)) {
			return "/appshare/download";
		}

		request.setAttribute("title", title);
		request.setAttribute("content", content);
		request.setAttribute("date", date);
		request.setAttribute("author", author);
		return "/appshare/share";
	}
	
	/**
	 * app错误日志上传
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/upload_errorlog")
	public Json uploadErrorlog(Bug bug, @RequestParam MultipartFile logFile, HttpServletRequest request) {
		Json j = new Json();
		try{
			SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
			Calendar calendar = Calendar.getInstance();  
			String dirName = "errorlog/" + calendar.get(Calendar.YEAR) + "/" + (calendar.get(Calendar.MONTH)+1) + "/" + calendar.get(Calendar.DAY_OF_MONTH);
			bug.setFilePath(uploadFile(request, dirName, logFile, format.format(calendar.getTime())));
			bug.setTypeId("0"); // 错误
			bug.setId(UUID.randomUUID().toString());
			bugService.add(bug);
			j.success();
		}catch(Exception e){
			j.fail();
			e.printStackTrace();
		}		
		return j;
	}
	
	/**
	 * 检查更新
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/checkUpdate")
	public Json checkUpdate(String versionNo, Integer isAdmin) {
		Json j = new Json();
		try{
			Map<String, Object> result = new HashMap<String, Object>();
			result.put("updateMark", false);

			if(isAdmin == null || isAdmin == 0) {
				String version = Application.getString("VM01", "1.0.0");
				if(F.empty(versionNo) || !versionNo.equals(version)) {
					result.put("updateMark", true);
					result.put("version", version); // 最新版本号
					result.put("filePath", Application.getDescString("APP02")); // 更新地址
					BaseData bd = Application.get("VM04");
					if(bd != null) {
						result.put("downloadPath", bd.getIcon()); // app下载地址
					}

					result.put("isForce", Application.getString("VM03", "0")); // 是否强制
					result.put("updateLog", Application.getDescString("VM02")); // 更新日志
				}
			} else {
				String version = Application.getString("VM11", "1.0.0");
				if(F.empty(versionNo) || !versionNo.equals(version)) {
					result.put("updateMark", true);
					result.put("version", version); // 最新版本号
					result.put("filePath", Application.getDescString("APP04")); // 更新地址
					BaseData bd = Application.get("VM14");
					if(bd != null) {
						result.put("downloadPath", bd.getIcon()); // app下载地址
					}
					result.put("isForce", Application.getString("VM13", "0")); // 是否强制
					result.put("updateLog", Application.getDescString("VM12")); // 更新日志
				}
			}

			j.setObj(result);
			j.success();
		}catch(Exception e){
			j.fail();
			e.printStackTrace();
		}		
		return j;
	}
	
	
	@ResponseBody
	@RequestMapping("/getIQiYiMP4Url")
	public Json getIQiYiMP4Url(String src, HttpServletRequest request) {
		Json j = new Json();
		try{
			j.setObj(HttpUtil.httpRequest(src, "GET", null));
			j.success();
		}catch(Exception e){
			j.fail();
			e.printStackTrace();
		}		
		return j;
	}

	@ResponseBody
	@RequestMapping("/upload")
	public Json upload(String bizType, @RequestParam(required = false) MultipartFile[] iconFiles, HttpServletRequest request) {
		Json j = new Json();
		try {
			List<String> iconList = new ArrayList<String>();
			for (MultipartFile iconFile : iconFiles) {
				String icon = uploadFile(request, bizType, iconFile);
				iconList.add(icon);
			}
			j.setSuccess(true);
			j.setObj(iconList);
		} catch (Exception e) {
			j.setMsg(e.getMessage());
		}
		return j;
	}
}
