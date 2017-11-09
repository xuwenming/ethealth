package com.ethealth.front.controller;

import com.mobian.absx.F;
import com.mobian.controller.BaseController;
import com.mobian.interceptors.TokenManage;
import com.mobian.listener.Application;
import com.mobian.pageModel.BaseData;
import com.mobian.pageModel.FdMember;
import com.mobian.pageModel.Json;
import com.mobian.pageModel.SessionInfo;
import com.mobian.service.FdMemberServiceI;
import com.mobian.service.impl.RedisUserServiceImpl;
import com.mobian.thirdpart.yunpian.YunpianUtil;
import com.mobian.util.MD5Util;
import com.mobian.util.Util;
import com.yunpian.sdk.model.Result;
import com.yunpian.sdk.model.SmsSingleSend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
* Created by guxin on 2017/4/22.
*
* 用户相关接口
*/
@Controller
@RequestMapping("/api/member")
public class ApiUserController extends BaseController {

    @Autowired
    private FdMemberServiceI fdMemberService;

    @Autowired
    private TokenManage tokenManage;

    @Autowired
    private RedisUserServiceImpl redisUserService;

    /**
     * 登录接口
     */
    @RequestMapping("/login")
    @ResponseBody
    public Json login(FdMember member) {
        Json j = new Json();
        try{
            String username = member.getUsername();
            String password = member.getPassword();
            if(!F.empty(username) && !F.empty(password)) {
                FdMember o = fdMemberService.get(member);
                if(o == null) {
                    j.setMsg("账号名或密码错误！");
                    return j;
                }

                String tokenId = tokenManage.buildToken(o.getId().toString(), o.getUsername(), null);
                o.setTokenId(tokenId);
                o.setUsername(username);
                j.setSuccess(true);
                j.setMsg("登陆成功！");
                j.setObj(o);
                return j;
            }
            j.setMsg("账号密码不能为空！");
        }catch(Exception e){
            j.setMsg(Application.getString(EX_0001));
            logger.error("用户登录接口异常", e);
        }

        return j;
    }

    /**
     * 获取短信验证码
     */
    @RequestMapping("/getVCode")
    @ResponseBody
    public Json getVCode(String mobile) {
        Json j = new Json();
        try{
            if(F.empty(mobile)) {
                j.setMsg("手机号码不能为空！");
                return j;
            }
            if(!Util.isMobilePhone(mobile)) {
                j.setMsg("手机号码格式不正确！");
                return j;
            }
//            String oldCode = redisUserService.getValidateCode(mobile);
//            if(!F.empty(oldCode)) {
//                j.setMsg("访问过于频繁，请秒后重试！");
//                return j;
//            }
            String code = Util.CreateNonceNumstr(6); // 生成短信验证码
            BaseData bd = Application.get(YunpianUtil.VCODE_100);
            Result<SmsSingleSend> result = YunpianUtil.single_send(mobile, bd.getDescription().replace("#code#", code));
            if(result.getCode() == 8 || result.getCode() == 22 || result.getCode() == 33) {
                j.setMsg("访问过于频繁，请秒后重试！");
                return j;
            }

            if(result.getCode() == 0) {
                redisUserService.setValidateCode(mobile, code, 600L); // 10分钟
                j.setSuccess(true);
                j.setMsg("获取短信验证码成功！");
                return j;
            }
            j.setMsg("获取短信验证码失败！");
        }catch(Exception e){
            j.setMsg(Application.getString(EX_0001));
            logger.error("获取短信验证码接口异常", e);
        }

        return j;
    }

    /**
     * 用户注册接口
     */
    @RequestMapping("/register")
    @ResponseBody
    public Json register(FdMember member, String vcode) {
        Json j = new Json();
        try{
            String password = member.getPassword();
            String username = member.getUsername();
            if(F.empty(password)) {
                j.setMsg("密码不能为空！");
                return j;
            }
            if(F.empty(username)) {
                j.setMsg("手机号不能为空！");
                return j;
            }
            if(!F.empty(username)) {
                if(!Util.isMobilePhone(username)) {
                    j.setMsg("手机号码格式不正确！");
                    return j;
                }
//                if(F.empty(vcode)) {
//                    j.setMsg("验证码不能为空！");
//                    return j;
//                }
//                String oldCode = redisUserService.getValidateCode(username);
//                if(F.empty(oldCode)) {
//                    j.setMsg("验证码已过期！");
//                    return j;
//                }
//                if(!oldCode.equals(vcode)) {
//                    j.setMsg("验证码错误！");
//                    return j;
//                }
                //验证手机号码是否已绑定
                boolean hasPhone = fdMemberService.checkUsername(username);
                if(hasPhone) {
                    j.setMsg("手机号码已绑定！");
                    return j;
                }
            }

            // 对用户密码MD5处理
            member.setPassword(MD5Util.encryptPassword(password));

            if(F.empty(member.getMobile())) member.setMobile(username);
            if(F.empty(member.getIsAdmin())) member.setIsAdmin(0); // 患者
            fdMemberService.add(member);

            String tokenId = tokenManage.buildToken(member.getId().toString(), member.getUsername(), null);
            member.setTokenId(tokenId);
            j.setSuccess(true);
            j.setMsg("注册成功！");
            j.setObj(member);
            return j;
        }catch(Exception e){
            j.setMsg(Application.getString(EX_0001));
            logger.error("用户注册接口异常", e);
        }

        return j;
    }

    /**
     * 找回密码
     */
    @RequestMapping("/forgetPwd")
    @ResponseBody
    public Json forgetPwd(FdMember member, String vcode) {
        Json j = new Json();
        try{
            String password = member.getPassword();
            String username = member.getUsername();
            if(F.empty(password)) {
                j.setMsg("密码不能为空！");
                return j;
            }
            if(F.empty(username)) {
                j.setMsg("手机号不能为空！");
                return j;
            }

            if(!Util.isMobilePhone(username)) {
                j.setMsg("手机号码格式不正确！");
                return j;
            }
//            if(F.empty(vcode)) {
//                j.setMsg("验证码不能为空！");
//                return j;
//            }
//            String oldCode = redisUserService.getValidateCode(username);
//            if(F.empty(oldCode)) {
//                j.setMsg("验证码已过期！");
//                return j;
//            }
//            if(!oldCode.equals(vcode)) {
//                j.setMsg("验证码错误！");
//                return j;
//            }

            FdMember q = new FdMember();
            q.setUsername(username);
            Integer isAdmin = member.getIsAdmin();
            q.setIsAdmin(isAdmin == null ? 0 : isAdmin);
            member = fdMemberService.get(q);
            if(member == null) {
                j.setMsg("手机号码未注册！");
                return j;
            }

            // 对用户密码MD5处理
            member.setPassword(MD5Util.encryptPassword(password));
            fdMemberService.edit(member);
            j.setSuccess(true);
            j.setMsg("密码修改成功！");
            return j;
        }catch(Exception e){
            j.setMsg(Application.getString(EX_0001));
            logger.error("找回密码接口异常", e);
        }

        return j;
    }

    /**
     * 修改密码
     */
    @RequestMapping("/updatePwd")
    @ResponseBody
    public Json updatePwd(FdMember member, String oldPass, HttpServletRequest request) {
        Json j = new Json();
        try{
            SessionInfo s = getSessionInfo(request);

            FdMember m = fdMemberService.get(Integer.valueOf(s.getId()));
            if(!MD5Util.encryptPassword(oldPass).equals(m.getPassword())) {
                j.setMsg("旧密码不正确！");
                return j;
            }
            String password = member.getPassword();
            if(F.empty(password)) {
                j.setMsg("密码不能为空！");
                return j;
            }

            // 对用户密码MD5处理
            member.setPassword(MD5Util.encryptPassword(password));
            fdMemberService.edit(member);
            j.setSuccess(true);
            j.setMsg("密码修改成功！");
            return j;
        }catch(Exception e){
            j.setMsg(Application.getString(EX_0001));
            logger.error("修改密码接口异常", e);
        }

        return j;
    }


}
