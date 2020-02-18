package com.atguigu.crowd.controller;

import com.atguigu.crowd.api.DataBaseOperationRemoteService;
import com.atguigu.crowd.api.RedisOperationRemoteService;
import com.atguigu.crowd.entity.MemberPO;
import com.atguigu.crowd.entity.MemberVO;
import com.atguigu.crowd.entity.ResultEntity;
import com.atguigu.crowd.util.CrowdConstant;
import com.atguigu.crowd.util.CrowdUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
public class MemberController {
    @Autowired
    private RedisOperationRemoteService redisRemoteService;

    @Autowired
    private DataBaseOperationRemoteService dataBaseOperationRemoteService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Value("${crowd.short.message.appCode}")
    private String appcode;

    /**
     * 发送手机验证码
     *
     * @param phoneNum 手机号
     * @return 返回验证码发送成功或者失败
     */
    @RequestMapping("/member/manager/send/code")
    public ResultEntity<String> sendCode(@RequestParam("phoneNum") String phoneNum) {

        if (!CrowdUtils.strEffectiveCheck(phoneNum)) {
            return ResultEntity.failed(CrowdConstant.MESSAGE_PHONE_NUM_INVALID);
        }

        // 1.生成验证码
        int length = 4;
        String randomCode = CrowdUtils.randomCode(length);

        // 2.保存到Redis
        Integer timeoutMinute = 15;

        String normalKey = CrowdConstant.REDIS_RANDOM_CODE_PREFIX + phoneNum;

        ResultEntity<String> resultEntity = redisRemoteService.saveNormalStringKeyValue(normalKey, randomCode, timeoutMinute);

        if (ResultEntity.FAILED.equals(resultEntity.getResult())) {
            return resultEntity;
        }

        // 3.发送验证码到用户手机
        try {
            CrowdUtils.sendShortMessage(appcode, randomCode, phoneNum);

            return ResultEntity.successNoData();
        } catch (Exception e) {
            e.printStackTrace();

            return ResultEntity.failed(e.getMessage());
        }

    }

    /**
     * 注册
     * @param memberVO  注册表单的VO的对象
     * @return 返回注册成功还是失败
     */
    @RequestMapping("/member/register")
    public ResultEntity<String> register(@RequestBody MemberVO memberVO) {
        // 1.获取验证码数据并进行有效性检测
        String randomCode = memberVO.getRandomCode();
        //判断字符串是否有效
        if (!CrowdUtils.strEffectiveCheck(randomCode)) {
            return ResultEntity.failed(CrowdConstant.MESSAGE_CODE_INVALID);
        }
        // 2.获取手机号数据并进行有效性检测
        String phoneNum = memberVO.getPhoneNum();
        if (!CrowdUtils.strEffectiveCheck(phoneNum)) {
            return ResultEntity.failed(CrowdConstant.MESSAGE_PHONENUM_INVALID);
        }
        // 3.拼接Redis存储验证码的KEY
        String randomCodeKey = CrowdConstant.REDIS_RANDOM_CODE_PREFIX + phoneNum;
        // 4.远程调用redis-provider的方法查询对应验证码
        ResultEntity<String> randomCodeRemoteResultEntity = redisRemoteService.retrieveStringValueByStringKey(randomCodeKey);
        //判断key是否key存入Redis时是否失败
        if (ResultEntity.FAILED.equals(randomCodeRemoteResultEntity.getResult())) {
            return randomCodeRemoteResultEntity;
        }
        //5.检查远程获取的验证码是否存在
        String randomCodeRemote = randomCodeRemoteResultEntity.getData();

        if (!CrowdUtils.strEffectiveCheck(randomCodeRemote)) {
            return ResultEntity.failed(CrowdConstant.MESSAGE_RANDOM_CODE_OUT_OF_DATE);
        }
        // 6.将“表单验证码”和“Redis验证码”进行比较
        if (!Objects.equals(randomCode, randomCodeRemote)) {
            return ResultEntity.failed(CrowdConstant.MESSAGE_RANDOM_CODE_NOT_MATCH);
        }
        // 7.检测登录账号是否被占用
        String loginacct = memberVO.getLoginacct();
        ResultEntity<Integer> loignAcctCountResultEntity = dataBaseOperationRemoteService.retrieveLoignAcctCount(loginacct);

        if (ResultEntity.FAILED.equals(loignAcctCountResultEntity.getResult())) {
            return randomCodeRemoteResultEntity;
        }
        Integer loignAcctCount = loignAcctCountResultEntity.getData();

        if (loignAcctCount > 0) {
            return ResultEntity.failed(CrowdConstant.MESSAGE_LOGIN_ACCT_ALREADY_IN_USE);
        }
        // 8.加密
        String userpswd = memberVO.getUserpswd();
        //判断密码是否为空或者null
        if (!CrowdUtils.strEffectiveCheck(userpswd)) {
            return ResultEntity.failed(CrowdConstant.MESSAGE_PASSWORD_INVALID);
        }

        userpswd = bCryptPasswordEncoder.encode(userpswd);

        // 9.将VO对象转换为PO对象
        MemberPO memberPO = new MemberPO();
        BeanUtils.copyProperties(memberVO, memberPO);

        // 10.执行保存操作
        ResultEntity<String> saveMemberRemoteResultEntity = dataBaseOperationRemoteService.saveMemberRemote(memberPO);

        return saveMemberRemoteResultEntity;
    }

}
