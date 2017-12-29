package com.ethealth.front.controller;

import com.mobian.absx.F;
import com.mobian.concurrent.CacheKey;
import com.mobian.concurrent.CompletionService;
import com.mobian.concurrent.Task;
import com.mobian.controller.BaseController;
import com.mobian.exception.ServiceException;
import com.mobian.listener.Application;
import com.mobian.pageModel.*;
import com.mobian.service.FdBalanceLogServiceI;
import com.mobian.service.FdMemberServiceI;
import com.mobian.service.FdMessageReadLogServiceI;
import com.mobian.service.FdMessageServiceI;
import com.mobian.service.impl.CompletionFactory;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
* Created by xuwm on 2017/5/17.
*
* 消息
*/
@Controller
@RequestMapping("/api/message")
public class ApiMessageController extends BaseController {

    @Autowired
    private FdMessageServiceI fdMessageService;

    @Autowired
    private FdMessageReadLogServiceI fdMessageReadLogService;

    @Autowired
    private FdMemberServiceI fdMemberService;

    /**
     * 获取消息列表接口
     */
    @RequestMapping("/dataGrid")
    @ResponseBody
    public Json dataGrid(FdMessage fdMessage, PageHelper ph, HttpServletRequest request) {
        Json j = new Json();
        try{
            SessionInfo s = getSessionInfo(request);
            FdMember member = fdMemberService.get(Integer.valueOf(s.getId()));
            if(ph.getRows() == 0 || ph.getRows() > 50) {
                ph.setRows(10);
            }
            if(F.empty(ph.getSort())) {
                ph.setSort("createTime");
            }
            if(F.empty(ph.getOrder())) {
                ph.setOrder("desc");
            }
            fdMessage.setUserId(Integer.valueOf(s.getId()));
            if(member.getIsAdmin() == 0) {
                fdMessage.setConsumerType(1);
            } else if(member.getIsAdmin() == 2){
                fdMessage.setConsumerType(2);
            }
            fdMessage.setStatus("ST01");
            DataGrid dg = fdMessageService.dataGrid(fdMessage, ph);
            List<FdMessage> list = dg.getRows();
            if(CollectionUtils.isNotEmpty(list)) {
                CompletionService completionService = CompletionFactory.initCompletion();
                final Integer userId = Integer.valueOf(s.getId());
                for(FdMessage message : list) {
                    if(!"MT02".equals(message.getMtype())) {
                        completionService.submit(new Task<FdMessage, Boolean>(message) {
                            @Override
                            public Boolean call() throws Exception {
                                FdMessageReadLog log = fdMessageReadLogService.get(getD().getId(), userId);
                                return log == null ? false : true;
                            }

                            protected void set(FdMessage d, Boolean v) {
                                d.setIsRead(v);
                            }
                        });
                    }
                }
                completionService.sync();
            }
            j.setObj(dg);
            j.setSuccess(true);
            j.setMsg("获取消息列表成功！");

        } catch (ServiceException e) {
            j.setObj(e.getMessage());
            logger.error("获取消息列表接口异常", e);
        }catch(Exception e){
            j.setMsg(Application.getString(EX_0001));
            logger.error("获取消息列表接口异常", e);
        }

        return j;
    }

    /**
     * 获取消息详情接口
     */
    @RequestMapping("/detail")
    @ResponseBody
    public Json detail(Integer id, HttpServletRequest request) {
        Json j = new Json();
        try{
            SessionInfo s = getSessionInfo(request);
            FdMessage message = fdMessageService.get(id);
            if("MT02".equals(message.getMtype())) {
                if(!message.getIsRead()) {
                    message.setIsRead(true);
                    fdMessageService.edit(message);
                }
            } else {
                FdMessageReadLog log = fdMessageReadLogService.get(id, Integer.valueOf(s.getId()));
                if(log == null) {
                    log = new FdMessageReadLog();
                    log.setUserId(Integer.valueOf(s.getId()));
                    log.setMessageId(id);

                    fdMessageReadLogService.add(log);
                }
            }
            j.setObj(message);
            j.setSuccess(true);
            j.setMsg("获取消息详情表成功！");

        } catch (ServiceException e) {
            j.setObj(e.getMessage());
            logger.error("获取消息详情接口异常", e);
        }catch(Exception e){
            j.setMsg(Application.getString(EX_0001));
            logger.error("获取消息详情接口异常", e);
        }

        return j;
    }
}
