package com.atguigu.crowd.mapper;

import com.atguigu.crowd.entity.MemberLaunchInfoPO;

public interface MemberLaunchInfoPOMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MemberLaunchInfoPO record);

    int insertSelective(MemberLaunchInfoPO record);

    MemberLaunchInfoPO selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MemberLaunchInfoPO record);

    int updateByPrimaryKey(MemberLaunchInfoPO record);
}