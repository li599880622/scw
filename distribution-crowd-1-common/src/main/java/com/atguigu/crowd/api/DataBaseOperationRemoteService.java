package com.atguigu.crowd.api;

import com.atguigu.crowd.entity.MemberPO;
import com.atguigu.crowd.entity.ResultEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("database-provider")
public interface DataBaseOperationRemoteService {
    /**
     * 根据账号查询账号的数量
     *
     * @param loginAcct 登录账号
     * @return 返回该账号的数量
     */
    @RequestMapping("/retrieve/loign/acct/count")
    ResultEntity<Integer> retrieveLoignAcctCount(@RequestParam("loginAcct") String loginAcct);

    /**
     * 保存注册信息
     *
     * @param memberPO
     * @return
     */
    @RequestMapping("/save/member/remote")
    ResultEntity<String> saveMemberRemote(@RequestBody MemberPO memberPO);
}
