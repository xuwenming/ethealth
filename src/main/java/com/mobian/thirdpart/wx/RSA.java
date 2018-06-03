package com.mobian.thirdpart.wx;

import com.mobian.listener.Application;

import java.util.*;

/**
 * Created by wenming on 2018/6/2.
 */
public class RSA {

    public static String CreateNoncestr() {
        String chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        String res = "";
        for (int i = 0; i < 16; i++) {
            Random rd = new Random();
            res += chars.charAt(rd.nextInt(chars.length() - 1));
        }
        return res;
    }

    public static String createSign(String characterEncoding,SortedMap<Object,Object> parameters){
        StringBuffer sb = new StringBuffer();
        Set es = parameters.entrySet();
        Iterator it = es.iterator();
        while(it.hasNext()) {
            Map.Entry entry = (Map.Entry)it.next();
            String k = (String)entry.getKey();
            Object v = entry.getValue();
            if(null != v && !"".equals(v)
                    && !"sign".equals(k) && !"key".equals(k)) {
                sb.append(k + "=" + v + "&");
            }
        }
		sb.append("key=33097ad18741352fe3ded9acd004c58b");
        String sign = MD5Util.MD5Encode(sb.toString(), characterEncoding).toUpperCase();

        return sign;
    }

    public static void main(String[] args) {
//        String url = "https://fraud.mch.weixin.qq.com/risk/getpublickey";
//        SortedMap<Object,Object> parameters = new TreeMap<Object,Object>();
//        parameters.put("mch_id", "1493767442");
//        parameters.put("nonce_str", CreateNoncestr());
//        parameters.put("sign_type", "MD5");
//        parameters.put("sign", createSign("UTF-8", parameters));
//
//        String request = HttpUtil.httpsRequestSSL(url, PayCommonUtil.getRequestXml(parameters));
//        System.out.println(request);

        System.out.println(PayCommonUtil.getRSA("徐文明"));
    }
}
