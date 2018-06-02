package com.ethealth.front.controller;

import com.mobian.absx.F;
import com.mobian.controller.BaseController;
import com.mobian.exception.ServiceException;
import com.mobian.listener.Application;
import com.mobian.pageModel.*;
import com.mobian.service.FdBalanceLogServiceI;
import com.mobian.service.FdCustomerServiceI;
import com.mobian.service.FdWithdrawLogServiceI;
import com.mobian.service.impl.RedisUserServiceImpl;
import com.mobian.util.IpUtil;
import com.mobian.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* Created by xuwm on 2017/5/17.
*
* 钱包
*/
@Controller
@RequestMapping("/api/balance")
public class ApiBalanceController extends BaseController {

    @Autowired
    private FdBalanceLogServiceI fdBalanceLogService;

    @Autowired
    private FdWithdrawLogServiceI fdWithdrawLogService;

    @Autowired
    private RedisUserServiceImpl redisUserService;

    @Autowired
    private FdCustomerServiceI fdCustomerService;

    /**
     * 获取钱包明细接口
     */
    @RequestMapping("/dataGrid")
    @ResponseBody
    public Json dataGrid(FdBalanceLog balanceLog, String date, PageHelper ph, HttpServletRequest request) {
        Json j = new Json();
        try{
            SessionInfo s = getSessionInfo(request);
            if(ph.getRows() == 0 || ph.getRows() > 50) {
                ph.setRows(10);
            }
            if(F.empty(ph.getSort())) {
                ph.setSort("createTime");
            }
            if(F.empty(ph.getOrder())) {
                ph.setOrder("desc");
            }

//            Calendar now = Calendar.getInstance();
//            if(F.empty(date)) {
//                date = now.get(Calendar.YEAR) + "-" + (now.get(Calendar.MONTH) + 1) ;
//            }
//            Date createDateStart = DateUtil.parse(date, Constants.DATE_FORMAT_YM);
//            balanceLog.setCreateTimeStart(createDateStart.getTime());
//            now.setTime(createDateStart);
//            now.add(Calendar.MONTH, 1);
//            balanceLog.setCreateTimeEnd(now.getTimeInMillis());

            balanceLog.setUserId(Long.valueOf(s.getId()));

            j.setObj(fdBalanceLogService.dataGrid(balanceLog, ph));
            j.setSuccess(true);
            j.setMsg("获取钱包明细成功！");

        } catch (ServiceException e) {
            j.setObj(e.getMessage());
            logger.error("获取钱包明细接口异常", e);
        }catch(Exception e){
            j.setMsg(Application.getString(EX_0001));
            logger.error("获取钱包明细接口异常", e);
        }

        return j;
    }

    /**
     * 获取钱包明细接口
     */
    @RequestMapping("/dataGridAll")
    @ResponseBody
    public Json dataGridAll(FdBalanceLog balanceLog, String date, PageHelper ph, HttpServletRequest request) {
        Json j = new Json();
        try{
            Map<String, Object> obj = new HashMap<String, Object>();
            SessionInfo s = getSessionInfo(request);
            if(ph.getRows() == 0 || ph.getRows() > 50) {
                ph.setRows(10);
            }
            if(F.empty(ph.getSort())) {
                ph.setSort("createTime");
            }
            if(F.empty(ph.getOrder())) {
                ph.setOrder("desc");
            }

            if(ph.getPage() == 0 || ph.getPage() == 1) {
                FdWithdrawLog log = new FdWithdrawLog();
                log.setUserId(s.getId());
                log.setHandleStatus("HS01,HS03");

                obj.put("withdrawLogs", fdWithdrawLogService.query(log));
            }

            balanceLog.setUserId(Long.valueOf(s.getId()));
            obj.put("balanceLogs", fdBalanceLogService.dataGrid(balanceLog, ph));

            j.setObj(obj);
            j.setSuccess(true);
            j.setMsg("获取钱包明细成功！");

        } catch (ServiceException e) {
            j.setObj(e.getMessage());
            logger.error("获取钱包明细接口异常", e);
        }catch(Exception e){
            j.setMsg(Application.getString(EX_0001));
            logger.error("获取钱包明细接口异常", e);
        }

        return j;
    }

    /**
     * 获取最近一次提现记录
     * @param request
     * @param
     * @return
     */
    @ResponseBody
    @RequestMapping("/getLastWithdraw")
    public Json getLastWithdraw(HttpServletRequest request){
        Json j = new Json();
        try{
            SessionInfo s = getSessionInfo(request);

            FdWithdrawLog log = new FdWithdrawLog();
            log.setUserId(s.getId());
            FdWithdrawLog withdrawLog = fdWithdrawLogService.get(log);
            j.setObj(withdrawLog);
            j.success();
            j.setMsg("申请成功");

        } catch (ServiceException e) {
            j.setObj(e.getMessage());
            logger.error("申请提现接口异常", e);
        }catch(Exception e){
            j.setMsg(Application.getString(EX_0001));
            logger.error("申请提现接口异常", e);
        }
        return j;
    }

    /**
     * 申请提现
     * @param request
     * @param
     * @return
     */
    @ResponseBody
    @RequestMapping("/applyWithdraw")
    public Json applyWithdraw(HttpServletRequest request, FdWithdrawLog withdrawLog, String vcode){
        Json j = new Json();
        try{
            SessionInfo s = getSessionInfo(request);

            if(F.empty(withdrawLog.getBankCode()) || F.empty(withdrawLog.getBankName())
                    || F.empty(withdrawLog.getBankCard()) || F.empty(withdrawLog.getBankAccount())) {
                j.setMsg("提现申请资料不全");
                return j;
            }

            String refType = F.empty(withdrawLog.getRefType()) ? "BBT007" : withdrawLog.getRefType();
            FdWithdrawLog log = new FdWithdrawLog();
            log.setUserId(s.getId());
            log.setRefType(refType);
            log.setCreateTime(new Date().getTime());
            List<FdWithdrawLog> logs = fdWithdrawLogService.query(log);
            int cashNum = Integer.valueOf(Application.getString("WD01", "1"));
            if(logs.size() >= cashNum) {
                j.setMsg("今日申请次数已用完，请明日再来");
                return j;
            }

            if(F.empty(withdrawLog.getAmount()) || withdrawLog.getAmount() < 100) {
                j.setMsg("单次提现不能低于1元");
                return j;
            }

            long max = new BigDecimal(Double.valueOf(Application.getString("WD02", "1000")).toString()).multiply(new BigDecimal(100)).longValue();
            if(withdrawLog.getAmount() > max*100) {
                j.setMsg("单次提现不得超过额度" + max + "元");
                return j;
            }

            // 短信验证
            boolean flag = false;
            if(!"000000".equals(vcode)) {
                flag = true;
            } else {
                if("0".equals(Application.getString("SV800", "0"))) {
                    flag = true;
                }
            }
            if(flag) {
                String oldCode = redisUserService.getValidateCode(s.getName());
                if (F.empty(oldCode)) {
                    j.setMsg("验证码已过期！");
                    return j;
                }
                if (!oldCode.equals(vcode)) {
                    j.setMsg("验证码错误！");
                    return j;
                }
            }

            FdCustomer customer = fdCustomerService.get(Long.valueOf(s.getId()));
            if(customer == null || new BigDecimal(customer.getBalance().toString()).multiply(new BigDecimal(100)).intValue() < withdrawLog.getAmount()) {
                j.setMsg("余额不足！");
                return j;
            }

            withdrawLog.setUserId(s.getId());
            withdrawLog.setApplyLoginIp(IpUtil.getIp(request));
            withdrawLog.setRefType(refType);
            withdrawLog.setWithdrawNo(Util.CreateNo("T"));
            withdrawLog.setHandleStatus("HS01");
            fdWithdrawLogService.add(withdrawLog);
            j.success();
            j.setMsg("申请成功");

        } catch (ServiceException e) {
            j.setObj(e.getMessage());
            logger.error("申请提现接口异常", e);
        }catch(Exception e){
            j.setMsg(Application.getString(EX_0001));
            logger.error("申请提现接口异常", e);
        }
        return j;
    }
}
