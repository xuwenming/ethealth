package com.ethealth.front.controller;

import com.alipay.api.domain.AlipayTradeAppPayModel;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradeAppPayRequest;
import com.alipay.api.response.AlipayTradeAppPayResponse;
import com.mobian.controller.BaseController;
import com.mobian.exception.ServiceException;
import com.mobian.listener.Application;
import com.mobian.pageModel.Json;
import com.mobian.pageModel.SessionInfo;
import com.mobian.thirdpart.alipay.AlipayUtil;
import com.mobian.thirdpart.wx.HttpUtil;
import com.mobian.thirdpart.wx.PayCommonUtil;
import com.mobian.thirdpart.wx.WeixinUtil;
import com.mobian.thirdpart.wx.XMLUtil;
import com.mobian.util.IpUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.*;

/**
 * 支付接口
 * 
 * @author John
 * 
 */
@Controller
@RequestMapping("/api/pay")
public class ApiPayController extends BaseController {

	/**
	 * 微信支付
	 */
	@RequestMapping("/wxpay")
	@ResponseBody
	public Json wxpay(HttpServletRequest request) {
		Json j = new Json();
		try{
			String requestXml = wxRequestXML("", "", 0, request);
			System.out.println("~~~~~~~~~~~~微信支付接口请求参数requestXml:" + requestXml);
			String result = HttpUtil.httpsRequest(WeixinUtil.UNIFIED_ORDER_URL, "POST", requestXml);
			System.out.println("~~~~~~~~~~~~微信支付接口返回结果result:" + result);
			Map<String, String> resultMap = XMLUtil.doXMLParse(result);
			if(resultMap.get("return_code").toString().equalsIgnoreCase("SUCCESS") && resultMap.get("result_code").toString().equalsIgnoreCase("SUCCESS")) {
				j.setObj(returnJsonStr(resultMap));
				j.setSuccess(true);
				j.setMsg("微信统一下单成功！");
			} else {
				j.setSuccess(true);
				j.setMsg(resultMap.get("return_msg"));
			}

		} catch (ServiceException e) {
			j.setObj(e.getMessage());
			logger.error("微信统一下单接口异常", e);
		}catch(Exception e){
			j.setMsg(Application.getString(EX_0001));
			logger.error("微信统一下单接口异常", e);
		}

		return j;
	}

	/**
	 * 支付宝支付
	 */
	@RequestMapping("/alipay")
	@ResponseBody
	public Json alipay(HttpServletRequest request) {
		Json j = new Json();
		try{
			AlipayTradeAppPayRequest alipayRequest = new AlipayTradeAppPayRequest();
			AlipayTradeAppPayModel model = new AlipayTradeAppPayModel();
			model.setSubject("");
			model.setOutTradeNo("");
			model.setTimeoutExpress("30m");
			model.setTotalAmount(""); // 单位元
			model.setProductCode("QUICK_MSECURITY_PAY");
			alipayRequest.setBizModel(model);
			alipayRequest.setNotifyUrl(AlipayUtil.NOTIFY_URL);

			AlipayTradeAppPayResponse response = AlipayUtil.alipayClient.sdkExecute(alipayRequest);
			j.setObj(response.getBody());
			j.setSuccess(true);
			j.setMsg("支付宝统一下单成功！");
		} catch (ServiceException e) {
			j.setObj(e.getMessage());
			logger.error("支付宝统一下单接口异常", e);
		}catch(Exception e){
			j.setMsg(Application.getString(EX_0001));
			logger.error("支付宝统一下单接口异常", e);
		}

		return j;
	}

	/**
	 * 余额支付
	 *
	 * @param
	 * @return
	 */
	@RequestMapping("/balancePay")
	@ResponseBody
	public Json balancePay(String vcode, HttpServletRequest request) {
		Json j = new Json();
		try{
			SessionInfo s = getSessionInfo(request);

			j.success();
			j.setMsg("余额支付成功！");
		}catch(Exception e){
			j.setMsg(Application.getString(EX_0001));
			logger.error("余额支付接口异常", e);
		}
		return j;
	}

	@RequestMapping("/wxpay/notify")
	public synchronized void wxpay_notify(HttpServletRequest request, HttpServletResponse response) throws Exception {
		InputStream inStream = request.getInputStream();
		ByteArrayOutputStream outSteam = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		int len = 0;
		while ((len = inStream.read(buffer)) != -1) {
			outSteam.write(buffer, 0, len);
		}
		System.out.println("~~~~~~~~~~~~~~~~付款成功~~~~~~~~~");
		outSteam.close();
		inStream.close();
		String result  = new String(outSteam.toByteArray(),"utf-8");//获取微信调用我们notify_url的返回信息
		Map<Object, Object> map = XMLUtil.doXMLParse(result);
		for(Object keyValue : map.keySet()){
			System.out.println(keyValue+"="+map.get(keyValue));
		}

		// 对数据库的操作
		String orderNo = map.get("out_trade_no").toString();
		String transaction_id = map.get("transaction_id").toString(); // 微信支付订单号


		response.getWriter().write(PayCommonUtil.setXML("SUCCESS", ""));   //告诉微信服务器，我收到信息了，不要在调用回调action了
		System.out.println("-------------"+PayCommonUtil.setXML("SUCCESS", ""));
	}

	@RequestMapping("/alipay/notify")
	public synchronized void alipay_notify(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map requestParams = request.getParameterMap();
		System.out.println("支付宝支付结果通知"+requestParams.toString());
		//获取支付宝POST过来反馈信息
		Map<String,String> params = new HashMap<String,String>();

		for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
			String name = (String) iter.next();
			String[] values = (String[]) requestParams.get(name);
			String valueStr = "";
			for (int i = 0; i < values.length; i++) {
				valueStr = (i == values.length - 1) ? valueStr + values[i]
						: valueStr + values[i] + ",";
			}
			params.put(name, valueStr);
		}

		boolean verify_result = AlipaySignature.rsaCheckV1(params, AlipayUtil.ALIPAY_PUBLIC_KEY,
				AlipayUtil.CHARSET, AlipayUtil.SIGN_TYPE);
		if(verify_result) {
			if ("TRADE_SUCCESS".equals(params.get("trade_status"))) {

			}
		}
		response.getWriter().println("success");
	}


	/**
	 * 微信支付请求参数
	 * @return
	 */
	private String wxRequestXML(String orderNo, String body, Integer amount, HttpServletRequest request) {
		try {
			SortedMap<Object,Object> parameters = new TreeMap<Object,Object>();
			// 公众账号ID 必填
			parameters.put("appid", Application.getString(WeixinUtil.APPID));
			// 附加数据 不是必填
			parameters.put("attach", orderNo);
			// 商品描述  必填
			parameters.put("body", body);
			// 商户号 必填
			parameters.put("mch_id", Application.getString(WeixinUtil.MCH_ID));
			// 随机字符串  必填 不长于32位
			parameters.put("nonce_str", WeixinUtil.CreateNoncestr());
			// 通知地址  必填
			parameters.put("notify_url", Application.getString(WeixinUtil.NOTIFY_URL));

			// 商户订单号  必填
			parameters.put("out_trade_no", orderNo);
			// 终端IP  必填
			parameters.put("spbill_create_ip", IpUtil.getIp(request));
			// 总金额  必填（单位为分必须为整数）
			parameters.put("total_fee", amount + "");
			// 交易类型  必填
			parameters.put("trade_type", "APP");

			// 签名 必填
			String sign = PayCommonUtil.createSign("UTF-8", parameters);
			parameters.put("sign", sign);

			return PayCommonUtil.getRequestXml(parameters);
		} catch(Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	private SortedMap<Object,Object> returnJsonStr(Map<String, String> resultMap) {
		Date nowDate = new Date();
		SortedMap<Object,Object> params = new TreeMap<Object,Object>();
		// 公众账号ID
		params.put("appid", resultMap.get("appid"));
		// 时间戳
		params.put("timeStamp", Long.toString(nowDate.getTime()));
		// 随机串
		params.put("nonceStr", WeixinUtil.CreateNoncestr());
		// 商品包信息
		params.put("package", "Sign=WXPay");
		// 商户号
		params.put("partnerid", resultMap.get("mch_id"));
		// 预支付交易会话ID
		params.put("prepayid", resultMap.get("prepay_id"));
		// 微信签名
		String sign = PayCommonUtil.createSign("UTF-8", params);
		// paySign的生成规则和Sign的生成规则一致
		params.put("sign", sign);

		return params;
	}

}
