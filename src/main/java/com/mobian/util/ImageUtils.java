package com.mobian.util;

import com.mobian.listener.Application;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class ImageUtils {


    public static String replaceHtmlTag(String str, String tag, String tagAttrib, String startTag, String endTag, String realPath) {
        try {
            String regxpForTag = "<\\s*" + tag + "\\s+([^>]*)\\s*" ;
            String regxpForTagAttrib = tagAttrib + "=\\s*\"([^\"]+)\"" ;
            Pattern patternForTag = Pattern.compile (regxpForTag, Pattern.CASE_INSENSITIVE);
            Pattern patternForAttrib = Pattern.compile (regxpForTagAttrib, Pattern.CASE_INSENSITIVE);
            Matcher matcherForTag = patternForTag.matcher(str);
            StringBuffer sb = new StringBuffer();
            boolean result = matcherForTag.find();
            while (result) {
                StringBuffer sbreplace = new StringBuffer("<" + tag + " ");
                Matcher matcherForAttrib = patternForAttrib.matcher(matcherForTag.group(1));
                if (matcherForAttrib.find()) {
                    String attributeStr = matcherForAttrib.group(1);
                    if (attributeStr.indexOf(Application.getString("SV200")) == -1) {
                        matcherForAttrib.appendReplacement(sbreplace, startTag + PathUtil.getPicPath(attributeStr) + endTag);
                    }
                }
                matcherForAttrib.appendTail(sbreplace);
                matcherForTag.appendReplacement(sb, sbreplace.toString());
                result = matcherForTag.find();
            }
            matcherForTag.appendTail(sb);
            return sb.toString();
        } catch (Exception e) {
            String error = String.format("replaceHtmlTag失败：%s", e);
            System.out.println(error);
        }
        return str;
    }



}
