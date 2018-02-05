package com.mobian.service.impl;

import com.mobian.absx.F;
import com.mobian.interceptors.TokenManage;
import com.mobian.listener.Application;
import com.mobian.pageModel.FdMember;
import com.mobian.service.FdMemberServiceI;
import com.mobian.service.TaskServiceI;
import com.mobian.thirdpart.easemob.HuanxinUtil;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* Created by john on 16/10/16.
*/
@Service
public class TaskServiceImpl implements TaskServiceI {

    @Autowired
    private TokenManage tokenManage;

    @Autowired
    private FdMemberServiceI fdMemberService;

    @Override
    public void deleteHxAccount() {
        try {
            int status = Integer.valueOf(Application.getString(HuanxinUtil.OPEN_STATUS, "1"));
            if(status == 1) {
                List<FdMember> memberList = fdMemberService.queryAllByDelHxAccount();
                if(CollectionUtils.isNotEmpty(memberList)) {
                    for(FdMember member : memberList) {
                        // 删除环信账号
                        HuanxinUtil.delUser(member.getIsAdmin() + "-" + member.getMobile());
                        // 更新环信状态
                        FdMember o = new FdMember();
                        o.setId(member.getId());
                        o.setHxStatus(false);
                        fdMemberService.edit(o);

                        // 清除当前用户的登录token
                        tokenManage.destroyTokenByMbUserId(member.getId() + "");
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
