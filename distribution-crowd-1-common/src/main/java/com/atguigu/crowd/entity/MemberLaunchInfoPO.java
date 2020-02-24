package com.atguigu.crowd.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class MemberLaunchInfoPO {
    private Integer id;
    //会员id
    private Integer memberid;
    //简单介绍
    private String descriptionSimple;
    //详细介绍
    private String descriptionDetail;
    //电话
    private String phoneNum;
    //客服电话
    private String serviceNum;

}