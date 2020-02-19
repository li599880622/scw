package com.atguigu.crowd.service.imp;

import com.atguigu.crowd.entity.MemberPO;
import com.atguigu.crowd.mapper.MemberPOMapper;
import com.atguigu.crowd.service.MemberService;
import com.atguigu.crowd.util.CrowdUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class MemberServiceImpl implements MemberService {
    @Autowired
    private MemberPOMapper memberPOMapper;

    @Override
    public int getLoginAcctCount(String loginAcct) {

        return memberPOMapper.findByLoginAcct(loginAcct);
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    @Override
    public void saveMemberPO(MemberPO memberPO) {
        memberPOMapper.insert(memberPO);
    }

    @Override
    public MemberPO getMemberByLoginAcct(String loginAcct) {
        MemberPO memberPO = new MemberPO(loginAcct);
        List<MemberPO> memberPOS = memberPOMapper.queryAll(memberPO);
        // 判断查询结果集合的有效性
        if (CrowdUtils.collectionEffectiveCheck(memberPOS)) {

            // 如果查询结果集合有效，则返回第一个元素
            return memberPOS.get(0);
        }

        return null;
    }

}
