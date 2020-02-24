package com.atguigu.crowd.api;

import com.atguigu.crowd.entity.MemberPO;
import com.atguigu.crowd.entity.ProjectVO;
import com.atguigu.crowd.entity.ResultEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
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
     * 保存信息
     *
     * @param memberPO 数据库对象
     * @return  结果对象
     */
    @RequestMapping("/save/member/remote")
    ResultEntity<String> saveMemberRemote(@RequestBody MemberPO memberPO);

    /**
     * 根据根据账号查询 MemberPO 对象的信息
     * @param loginAcct 账号
     * @return 结果对象
     */
    @RequestMapping("/retrieve/member/by/login/acct")
    ResultEntity<MemberPO> retrieveMemberByLoginAcct(@RequestParam("loginAcct") String loginAcct);

    /**
     * 持久化项目信息
     * @param projectVO  项目信息
     * @param memberId  用户id
     * @return 结果对象
     */
    @RequestMapping("/save/project/remote/{memberId}")
    ResultEntity<String> saveProjectRemote(@RequestBody ProjectVO projectVO, @PathVariable("memberId") String memberId);
}
