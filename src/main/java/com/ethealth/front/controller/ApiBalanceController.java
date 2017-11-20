package com.ethealth.front.controller;

import com.mobian.absx.F;
import com.mobian.controller.BaseController;
import com.mobian.exception.ServiceException;
import com.mobian.listener.Application;
import com.mobian.pageModel.FdBalanceLog;
import com.mobian.pageModel.Json;
import com.mobian.pageModel.PageHelper;
import com.mobian.pageModel.SessionInfo;
import com.mobian.service.FdBalanceLogServiceI;
import com.mobian.util.Constants;
import com.mobian.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Calendar;
import java.util.Date;

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

            Calendar now = Calendar.getInstance();
            if(F.empty(date)) {
                date = now.get(Calendar.YEAR) + "-" + (now.get(Calendar.MONTH) + 1) ;
            }
            Date createDateStart = DateUtil.parse(date, Constants.DATE_FORMAT_YM);
            balanceLog.setCreateTimeStart(createDateStart.getTime());
            now.setTime(createDateStart);
            now.add(Calendar.MONTH, 1);
            balanceLog.setCreateTimeEnd(now.getTimeInMillis());

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
}
