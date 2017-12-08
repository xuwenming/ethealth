package com.ethealth.front.controller;

import com.mobian.absx.F;
import com.mobian.absx.UUID;
import com.mobian.controller.BaseController;
import com.mobian.exception.ServiceException;
import com.mobian.interceptors.TokenManage;
import com.mobian.listener.Application;
import com.mobian.pageModel.*;
import com.mobian.service.FdMemberDoctorShServiceI;
import com.mobian.service.FdMemberServiceI;
import com.mobian.service.impl.RedisUserServiceImpl;
import com.mobian.thirdpart.easemob.HuanxinUtil;
import com.mobian.thirdpart.yunpian.YunpianUtil;
import com.mobian.util.MD5Util;
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
import java.util.List;

/**
* Created by guxin on 2017/4/22.
*
* 用户相关接口
*/
@Controller
@RequestMapping("/api/member")
public class ApiUserController extends BaseController {

    public static final String HEAD_IMAGE = "mmopen";

    @Autowired
    private FdMemberServiceI fdMemberService;

    @Autowired
    private TokenManage tokenManage;

    @Autowired
    private RedisUserServiceImpl redisUserService;

    @Autowired
    private FdMemberDoctorShServiceI fdMemberDoctorShService;

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
                member.setStatusArr("1,2,3");
                FdMember o = fdMemberService.get(member);
                if(o == null) {
                    j.setMsg("账号名或密码错误！");
                    return j;
                }

                if(!o.getHxStatus()) {
                    String hxPass = o.getHxPassword();
                    if(F.empty(hxPass)) {
                        hxPass = UUID.uuid();
                        o.setHxPassword(hxPass);
                    }
                    if(!F.empty(HuanxinUtil.createUser(member.getIsAdmin() + "-" + username, hxPass))) {
                        o.setHxStatus(true);
                    } else {
                        o.setHxStatus(false);
                    }

                    fdMemberService.edit(o);
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
     * 退出登录接口
     */
    @RequestMapping("/logout")
    @ResponseBody
    public Json logout(HttpServletRequest request) {
        Json j = new Json();
        try{
            SessionInfo s = getSessionInfo(request);
            tokenManage.destroyTokenByMbUserId(s.getId());
            j.setSuccess(true);
            j.setMsg("退出成功！");
        } catch (ServiceException e) {
            j.setSuccess(true);
            j.setMsg("退出成功！");
        }catch(Exception e){
            j.setMsg(Application.getString(EX_0001));
            logger.error("用户退出登录接口异常", e);
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

            if(F.empty(member.getMobile())) member.setMobile(username);
            if(F.empty(member.getIsAdmin())) member.setIsAdmin(0); // 患者
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
                boolean hasPhone = fdMemberService.checkUsername(username, member.getIsAdmin());
                if(hasPhone) {
                    j.setMsg("手机号码已绑定！");
                    return j;
                }
            }

            // 对用户密码MD5处理
            member.setPassword(MD5Util.encryptPassword(password));

            if(member.getIsAdmin() == 0) {
                String uuid = UUID.uuid();
                member.setHxPassword(uuid);
                if(!F.empty(HuanxinUtil.createUser(member.getIsAdmin() + "-" + username, uuid))) {
                    member.setHxStatus(true);
                } else {
                    member.setHxStatus(false);
                }
            } else if(member.getIsAdmin() == 2) {
                member.setStatus(-1);
            }

            fdMemberService.addMember(member);

            if(member.getIsAdmin() == 0) {
                String tokenId = tokenManage.buildToken(member.getId().toString(), member.getUsername(), null);
                member.setTokenId(tokenId);
            }

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
     * 提交审核接口
     */
    @RequestMapping("/addDoctorInfo")
    @ResponseBody
    public Json addDoctorInfo(FdMemberDoctorSh sh) {
        Json j = new Json();
        try {
            sh.setStatus("1"); // 审核中
            fdMemberDoctorShService.addOrUpdateMemberDoctorSh(sh);

            FdMember member = fdMemberService.get(sh.getId());
            if(!member.getHxStatus()) {
                String hxPass = member.getHxPassword();
                if(F.empty(hxPass)) {
                    hxPass = UUID.uuid();
                    member.setHxPassword(hxPass);
                }
                if(!F.empty(HuanxinUtil.createUser(member.getIsAdmin() + "-" + member.getUsername(), hxPass))) {
                    member.setHxStatus(true);
                } else {
                    member.setHxStatus(false);
                }

                fdMemberService.edit(member);
            }

            String tokenId = tokenManage.buildToken(member.getId().toString(), member.getUsername(), null);
            member.setTokenId(tokenId);
            j.setObj(member);
            j.setSuccess(true);
            j.setMsg("提交成功！");
            return j;
        }catch(Exception e){
            j.setMsg(Application.getString(EX_0001));
            logger.error("提交审核接口异常", e);
        }

        return j;
    }

    /**
     * 医生端账号审核（非开发接口）
     */
    @RequestMapping("/editAudit")
    @ResponseBody
    public Json editAudit(FdMemberDoctorSh sh) {
        Json j = new Json();
        try {
            FdMember member = fdMemberService.get(sh.getId());
            if(member.getStatus() == -1) {
                j.setMsg("用户不存在");
                return j;
            }
            fdMemberDoctorShService.editAudit(sh);
            j.setSuccess(true);
            j.setMsg("编辑成功！");
        }catch(Exception e){
            j.setMsg(Application.getString(EX_0001));
            logger.error("医生端账号审核异常", e);
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
            if(F.empty(vcode)) {
                j.setMsg("验证码不能为空！");
                return j;
            }
            String oldCode = redisUserService.getValidateCode(username);
            if(F.empty(oldCode)) {
                j.setMsg("验证码已过期！");
                return j;
            }
            if(!oldCode.equals(vcode)) {
                j.setMsg("验证码错误！");
                return j;
            }

            FdMember q = new FdMember();
            q.setUsername(username);
            Integer isAdmin = member.getIsAdmin();
            q.setIsAdmin(isAdmin == null ? 0 : isAdmin);
            q.setStatusArr("1,2,3");
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
     * 修改、补充用户信息接口
     */
    @RequestMapping("/edit")
    @ResponseBody
    public Json edit(FdMember member, HttpServletRequest request, @RequestParam(required = false) MultipartFile headImageFile) {
        Json j = new Json();
        try{
            SessionInfo s = getSessionInfo(request);
            member.setId(Integer.valueOf(s.getId()));

            // 如果修改了用户密码，对用户密码MD5处理
            String password = member.getPassword();
            if(!F.empty(password)) {
                member.setPassword(MD5Util.encryptPassword(password));
            }

            member.setHeadImage(uploadFile(HEAD_IMAGE, headImageFile));
            fdMemberService.editMember(member);
            j.setSuccess(true);
            j.setMsg("修改成功！");
        } catch (ServiceException e) {
            j.setObj(e.getMessage());
            logger.error("修改、补充用户信息接口异常", e);
        } catch(Exception e) {
            j.setMsg(Application.getString(EX_0001));
            logger.error("修改、补充用户信息接口异常", e);
        }

        return j;
    }

    /**
     * 获取用户信息接口
     */
    @RequestMapping("/get")
    @ResponseBody
    public Json get(HttpServletRequest request) {
        Json j = new Json();
        try{
            SessionInfo s = getSessionInfo(request);
            j.setObj(fdMemberService.getDetail(Integer.valueOf(s.getId())));
            j.setSuccess(true);
            j.setMsg("获取成功！");
        } catch (ServiceException e) {
            j.setObj(e.getMessage());
            logger.error("获取用户信息接口异常", e);
        } catch(Exception e) {
            j.setMsg(Application.getString(EX_0001));
            logger.error("获取用户信息接口异常", e);
        }

        return j;
    }

    /**
     * 根据环信账号获取用户信息
     */
    @RequestMapping("/getByHx")
    @ResponseBody
    public Json getByHx(String hxAccounts) {
        Json j = new Json();
        try{
            j.setObj(fdMemberService.getByMobiles(hxAccounts));
            j.setSuccess(true);
            j.setMsg("获取成功！");
        } catch(Exception e) {
            j.setMsg(Application.getString(EX_0001));
            logger.error("获取用户信息接口异常", e);
        }

        return j;
    }


}
