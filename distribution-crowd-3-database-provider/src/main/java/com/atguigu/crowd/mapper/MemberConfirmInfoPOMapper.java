package com.atguigu.crowd.mapper;

import com.atguigu.crowd.entity.MemberConfirmInfoPO;

public interface MemberConfirmInfoPOMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MemberConfirmInfoPO record);

    int insertSelective(MemberConfirmInfoPO record);

    MemberConfirmInfoPO selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MemberConfirmInfoPO record);

    int updateByPrimaryKey(MemberConfirmInfoPO record);
}