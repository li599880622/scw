package com.atguigu.crowd.service.imp;

import com.atguigu.crowd.entity.MemberPO;
import com.atguigu.crowd.mapper.MemberPOMapper;
import com.atguigu.crowd.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class MemberServiceImpl implements MemberService {
    @Autowired
    private MemberPOMapper memberPOMapper;

    @Override
    public int getLoginAcctCount(String loginAcct) {

       // int byLoginAcct = ;
        return memberPOMapper.findByLoginAcct(loginAcct);
    }

    @Transactional(readOnly = false,propagation = Propagation.REQUIRES_NEW,rollbackFor = Exception.class)
    @Override
    public void saveMemberPO(MemberPO memberPO) {
        memberPOMapper.insert(memberPO);
    }

}
