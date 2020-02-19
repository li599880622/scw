package com.atguigu.crowd.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 登录成功对象
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberSignSuccessVO {

    private String username;
    private String email;
    private String token;
}
