package com.atguigu.crowd.util;

import java.util.HashMap;
import java.util.Map;

public class CrowdConstant {
    /**
     * code的前缀
     */
    public static final String REDIS_RANDOM_CODE_PREFIX = "RANDOM_CODE_";
    /**
     * token的前缀
     */
    public static final String REDIS_MEMBER_SING_TOKEN_PREFIX = "SIGNED_MEMBER_";
    /**
     * 项目进度存入Redis的前缀
     */
    public static final Object REDIS_PROJECT_TEMP_TOKEN_PREFIX = "PROJECT_TEMP_TOKEN_";

    public static final String ATTR_NAME_MESSAGE = "MESSAGE";
    public static final String ATTR_NAME_LOGIN_ADMIN = "LOGIN-ADMIN";
    public static final String ATTR_NAME_PAGE_INFO = "PAGE-INFO";

    public static final String MESSAGE_LOGIN_FAILED = "登录账号或密码不正确！请核对后再登录！";
    public static final String MESSAGE_CODE_INVALID = "明文不是有效字符串，请核对后再操作！";
    public static final String MESSAGE_ACCESS_DENIED = "请登录后再操作！";
    public static final String MESSAGE_LOGIN_ACCT_ALREADY_IN_USE = "登录账号被占用，请重新设定！";

    public static final Map<String, String> EXCEPTION_MESSAGE_MAP = new HashMap<>();
    public static final String MESSAGE_RANDOM_CODE_LENGTH_INVALID = "验证码长度不合法！";

    public static final String MESSAGE_REDIS_KEY_OR_VALUE_INVALID = "待存入Redis的key或value不是有效字符串！";

    public static final String MESSAGE_REDIS_KEY_TIME_OUT_INVALID = "抱歉！不接受0或null值，请明确表达您的意图：是否设置过期时间。";

    public static final String MESSAGE_PHONE_NUM_INVALID = "手机号不符合要求！";

    public static final String MESSAGE_LOGINACCT_INVALID = "登录账号字符串无效！";

    public static final String MESSAGE_CODE_NOT_MATCH = "验证码不匹配！";

    public static final String MESSAGE_CODE_NOT_EXISTS = "验证码不存在或已过期！";

    public static final String MESSAGE_PHONENUM_INVALID = "请输入正确的手机号!";

    public static final String MESSAGE_RANDOM_CODE_OUT_OF_DATE = "验证码已过期!";

    public static final String MESSAGE_RANDOM_CODE_NOT_MATCH = "验证码错误!";

    public static final String MESSAGE_PASSWORD_INVALID = "密码不能为空或者null";

    public static final String MESSAGE_LOGIN_NEEDED = "请登录后再操作!";
    public static final String MESSAGE_PROJECT_NOT_FOUND_FROM_CACHE = "项目信息不存在,请重新操作!";
    public static final String ATTR_NAME_LOGIN_MEMBER = "LOGIN-MEMBER";
    public static final String ATTR_NAME_REQUEST_MESSAGE = "MESSAGE";
    public static final String ATTR_NAME_INITED_PROJECT = "INITED";


    static {
        EXCEPTION_MESSAGE_MAP.put("java.lang.ArithmeticException", "系统在进行数学运算时发生错误");
        EXCEPTION_MESSAGE_MAP.put("java.lang.RuntimeException", "系统在运行时发生错误");
        EXCEPTION_MESSAGE_MAP.put("com.atguigu.crowd.funding.exception.LoginException", "登录过程中运行错误");
        EXCEPTION_MESSAGE_MAP.put("org.springframework.security.access.AccessDeniedException", "尊敬的用户，您现在不具备访问当前功能的权限！请联系超级管理员。");
    }

}
