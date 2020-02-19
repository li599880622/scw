package com.atguigu.crowd.service;

import com.atguigu.crowd.entity.MemberPO;

public interface MemberService {
    /**
     * 获取登录账号在数据库存在的数量
     * @param loginAcct 登录账号
     * @return  在数据中存在的数量
     */
    int getLoginAcctCount(String loginAcct);

    /**
     * 保存memberPO对象
     * @param memberPO 实体对象
     */
    void saveMemberPO(MemberPO memberPO);

    /**
     * 根据账号loginAcct查询MemberPO对象
     * @param loginAcct 账号
     * @return 查询MemberPO对象
     */
    MemberPO getMemberByLoginAcct(String loginAcct);
}
