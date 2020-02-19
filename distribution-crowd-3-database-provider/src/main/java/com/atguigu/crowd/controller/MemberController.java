package com.atguigu.crowd.controller;

import com.atguigu.crowd.entity.MemberPO;
import com.atguigu.crowd.entity.ResultEntity;
import com.atguigu.crowd.service.MemberService;
import com.atguigu.crowd.util.CrowdConstant;
import com.atguigu.crowd.util.CrowdUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MemberController {
    @Autowired
    private MemberService memberService;

    /**
     * 根据账号查询账号的数量
     *
     * @param loginAcct 登录账号
     * @return 返回该账号的数量
     */
    @RequestMapping("/retrieve/loign/acct/count")
    public ResultEntity<Integer> retrieveLoignAcctCount(@RequestParam("loginAcct") String loginAcct) {
        if (!CrowdUtils.strEffectiveCheck(loginAcct)) {
            return ResultEntity.failed(CrowdConstant.MESSAGE_LOGINACCT_INVALID);
        }

        try {
            int count = memberService.getLoginAcctCount(loginAcct);

            return ResultEntity.successWithData(count);

        } catch (Exception e) {
            e.printStackTrace();

            return ResultEntity.failed(e.getMessage());
        }
    }

    /**
     * 保存注册信息
     *
     * @param memberPO PO对象
     * @return 返回结果对象
     */
    @RequestMapping("/save/member/remote")
    public ResultEntity<String> saveMemberRemote(@RequestBody MemberPO memberPO) {
        try {
            //执行保存
            memberService.saveMemberPO(memberPO);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultEntity.failed(e.getMessage());
        }
        return ResultEntity.successNoData();
    }

    /**
     * 根据根据账号查询 MemberPO 对象的信息
     *
     * @param loginAcct 账号
     * @return 结果对象
     */
    @RequestMapping("/retrieve/member/by/login/acct")
    ResultEntity<MemberPO> retrieveMemberByLoginAcct(@RequestParam("loginAcct") String loginAcct){
        MemberPO memberPO = null;

        try {
            memberPO = memberService.getMemberByLoginAcct(loginAcct);
        } catch (Exception e) {
            e.printStackTrace();

            return ResultEntity.failed(e.getMessage());
        }

        return ResultEntity.successWithData(memberPO);
    }
}
