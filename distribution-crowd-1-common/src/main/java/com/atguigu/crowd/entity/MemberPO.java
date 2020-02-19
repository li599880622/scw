package com.atguigu.crowd.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * (MemberPO)实体类
 *
 * @author makejava
 * @since 2020-02-15 21:40:06
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberPO implements Serializable {
    private static final long serialVersionUID = 703823684140080294L;
    
    private Integer id;
    
    private String loginacct;
    
    private String userpswd;
    
    private String username;
    
    private String email;
    /**
    * 实名认证状态 0 - 未实名认证， 1 - 实名认证申请中， 2 - 已实名认证
    */
    private Object authstatus;
    /**
    *  0 - 个人， 1 - 企业
    */
    private Object usertype;
    
    private String realname;
    
    private String cardnum;
    /**
    * 0 - 企业， 1 - 个体， 2 - 个人， 3 - 政府
    */
    private Object accttype;

    public MemberPO(String loginAcct) {
        this.loginacct = loginAcct;
    }
}