package com.atguigu.crowd.service;

import com.atguigu.crowd.entity.MemberPO;

public interface MemberService {
    /**
     * 获取登录账号在数据库存在的数量
     * @param loginAcct 登录账号
     * @return  在数据中存在的数量
     */
    int getLoginAcctCount(String loginAcct);

    void saveMemberPO(MemberPO memberPO);
}
