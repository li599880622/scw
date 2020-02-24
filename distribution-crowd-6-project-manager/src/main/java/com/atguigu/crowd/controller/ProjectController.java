package com.atguigu.crowd.controller;

import com.alibaba.fastjson.JSON;
import com.atguigu.crowd.api.DataBaseOperationRemoteService;
import com.atguigu.crowd.api.RedisOperationRemoteService;
import com.atguigu.crowd.entity.MemberConfirmInfoVO;
import com.atguigu.crowd.entity.ProjectVO;
import com.atguigu.crowd.entity.ResultEntity;
import com.atguigu.crowd.entity.ReturnVO;
import com.atguigu.crowd.util.CrowdConstant;
import com.atguigu.crowd.util.CrowdUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ProjectController {
    @Autowired
    private DataBaseOperationRemoteService dataBaseOperationRemoteService;
    @Autowired
    private RedisOperationRemoteService redisOperationRemoteService;

    /**
     * 初始化项目创建操作
     *
     * @param memberSignToken 登录token
     * @return 结果对象
     */
    @RequestMapping("/project/manager/initCreation")
    public ResultEntity<ProjectVO> initCreation(@RequestParam("memberSignToken") String memberSignToken) {
        //1.检查是否登录,也就是memberToken是否有效
        ResultEntity<String> resultEntity = redisOperationRemoteService.retrieveStringValueByStringKey(memberSignToken);
        if (ResultEntity.FAILED.equals(resultEntity.getResult())) {
            return ResultEntity.failed(resultEntity.getMessage());
        }
        String memberId = resultEntity.getData();
        if (memberId == null) {
            return ResultEntity.failed(CrowdConstant.MESSAGE_ACCESS_DENIED);
        }
        // 2.创建空ProjectVO对象备用
        ProjectVO projectVO = new ProjectVO();

        // 3.将memberSignToken存入ProjectVO对象
        projectVO.setMemberSignToken(memberSignToken);

        // 4.将projectTempToken存入projectVO对象
        String projectTempToken = CrowdUtils.generateRedisKeyByPrefix(CrowdConstant.REDIS_PROJECT_TEMP_TOKEN_PREFIX);
        projectVO.setProjectTempToken(projectTempToken);

        // 5.将ProjectVO对象转换成JSON
        String jsonString = JSON.toJSONString(projectVO);

        // 6.将projectTempToken和jsonString作为key、value存入Redis
        // 虽然是临时数据，但是不能指定一个固定的过期时间，在用户操作完成时删除
        redisOperationRemoteService.saveNormalStringKeyValue(projectTempToken, jsonString, -1);

        return ResultEntity.successWithData(projectVO);
    }

    /**
     * 上传项目头图
     *
     * @param memberSignToken   登录token
     * @param projectTempToken  项目token
     * @param headerPicturePath 头图路径
     * @return 结果对象
     */
    @RequestMapping("/project/manager/save/head/picture/path")
    public ResultEntity<String> saveHeadPicturePath(
            @RequestParam("memberSignToken") String memberSignToken,
            @RequestParam("projectTempToken") String projectTempToken,
            @RequestParam("headerPicturePath") String headerPicturePath
    ) {

        // 检查是否登录，也就是检查memberSignToken是否有效
        ResultEntity<String> resultEntity = redisOperationRemoteService.retrieveStringValueByStringKey(memberSignToken);
        if (ResultEntity.FAILED.equals(resultEntity.getResult())) {
            return ResultEntity.failed(resultEntity.getMessage());
        }

        // project-manager工程访问Redis查询ProjectVO对象
        ResultEntity<String> resultEntityForGetValue = redisOperationRemoteService.retrieveStringValueByStringKey(projectTempToken);
        //判断从Redis中查询查询ProjectVO对象是否失败
        if (ResultEntity.FAILED.equals(resultEntityForGetValue.getResult())) {
            return ResultEntity.failed(resultEntityForGetValue.getMessage());
        }

        // 从Redis查询到JSON字符串
        String projectVOJSON = resultEntityForGetValue.getData();

        // 将JSON字符串还原成ProjectVO对象
        ProjectVO projectVO = JSON.parseObject(projectVOJSON, ProjectVO.class);

        // 将图片路径存入ProjectVO对象
        projectVO.setHeaderPicturePath(headerPicturePath);

        // 将ProjectVO对象转换为JSON字符串
        String jsonString = JSON.toJSONString(projectVO);

        // 将JSON字符串重新存入Redis
        return redisOperationRemoteService.saveNormalStringKeyValue(projectTempToken, jsonString, -1);

    }

    /**
     * 上传项目详情图片
     *
     * @param memberSignToken       登录token
     * @param projectTempToken      项目token
     * @param detailPicturePathList 详情图片路径集合
     * @return 结果对象
     */
    @RequestMapping("/project/manager/save/detail/picture/path/list")
    public ResultEntity<String> saveDetailPicturePathList(
            @RequestParam("memberSignToken") String memberSignToken,
            @RequestParam("projectTempToken") String projectTempToken,
            @RequestParam("detailPicturePathList") List<String> detailPicturePathList
    ) {

        // 检查是否登录，也就是检查memberSignToken是否有效
        ResultEntity<String> resultEntity = redisOperationRemoteService.retrieveStringValueByStringKey(memberSignToken);
        if (ResultEntity.FAILED.equals(resultEntity.getResult())) {
            return ResultEntity.failed(resultEntity.getMessage());
        }

        // project-manager工程访问Redis查询ProjectVO对象
        ResultEntity<String> resultEntityForGetValue = redisOperationRemoteService.retrieveStringValueByStringKey(projectTempToken);
        //判断从Redis中查询查询ProjectVO对象是否失败
        if (ResultEntity.FAILED.equals(resultEntityForGetValue.getResult())) {
            return ResultEntity.failed(resultEntityForGetValue.getMessage());
        }

        // 从Redis查询到JSON字符串
        String projectVOJSON = resultEntityForGetValue.getData();

        // 将JSON字符串还原成ProjectVO对象
        ProjectVO projectVO = JSON.parseObject(projectVOJSON, ProjectVO.class);

        // 将图片路径存入ProjectVO对象
        projectVO.setDetailPicturePathList(detailPicturePathList);

        // 将ProjectVO对象转换为JSON字符串
        String jsonString = JSON.toJSONString(projectVO);

        // 将JSON字符串重新存入Redis
        return redisOperationRemoteService.saveNormalStringKeyValue(projectTempToken, jsonString, -1);

    }

    /**
     * 保存项目信息
     *
     * @param projectVOFront 项目信息
     * @return 结果对象
     */
    @RequestMapping("/project/manager/save/project/information")
    public ResultEntity<String> saveProjectInformation(@RequestBody ProjectVO projectVOFront) {

        // 获取memberSignToken
        String memberSignToken = projectVOFront.getMemberSignToken();

        // 检查是否登录，也就是检查memberSignToken是否有效
        ResultEntity<String> resultEntity = redisOperationRemoteService.retrieveStringValueByStringKey(memberSignToken);
        //判断从Redis中查询memberSignToken是否失败
        if (ResultEntity.FAILED.equals(resultEntity.getResult())) {
            return ResultEntity.failed(resultEntity.getMessage());
        }

        // 从projectVOFront中获取projectTempToken
        String projectTempToken = projectVOFront.getProjectTempToken();

        // project-manager工程访问Redis查询ProjectVO对象
        ResultEntity<String> resultEntityForGetValue = redisOperationRemoteService.retrieveStringValueByStringKey(projectTempToken);
        //判断从Redis中查询projectTempToken是否失败
        if (ResultEntity.FAILED.equals(resultEntityForGetValue.getResult())) {
            return ResultEntity.failed(resultEntityForGetValue.getMessage());
        }

        // 从Redis查询到JSON字符串
        String projectVOJSON = resultEntityForGetValue.getData();

        // 将JSON字符串还原成ProjectVO对象
        ProjectVO projectVOBehind = JSON.parseObject(projectVOJSON, ProjectVO.class);
       /* //传入项目头图路径
        projectVOFront.setHeaderPicturePath(projectVOBehind.getHeaderPicturePath());
        //传入项目详细信息图 路径
        projectVOFront.setDetailPicturePathList(projectVOBehind.getDetailPicturePathList());*/

        // 将projectVOFront对象中的属性复制到projectVOBehind对象
        BeanUtils.copyProperties(projectVOFront, projectVOBehind);

        // 将projectVOBehind对象转换为JSON字符串
        String jsonString = JSON.toJSONString(projectVOBehind);

        // 将JSON字符串重新存入Redis
        return redisOperationRemoteService.saveNormalStringKeyValue(projectTempToken, jsonString, -1);

    }

    /**
     * 保存回报信息
     *
     * @param returnVO 回报对象
     * @return 结果对象
     */
    @RequestMapping("/save/return/vo")
    public ResultEntity<String> saveReturnVO(@RequestBody ReturnVO returnVO) {

        // 1.检查memberSignToken确认用户是否登录
        String memberSignToken = returnVO.getMemberSignToken();
        // 从Redis中查询memberSignToken
        ResultEntity<String> retrieveTokenResultEntity = redisOperationRemoteService.retrieveStringValueByStringKey(memberSignToken);
        // 判断memberSignToken是否查询失败
        if (ResultEntity.FAILED.equals(retrieveTokenResultEntity.getResult())) {
            return ResultEntity.failed(retrieveTokenResultEntity.getMessage());
        }

        String memberId = retrieveTokenResultEntity.getData();
        //判断根据memberSignToken查询对应的memberid是否存在，为null则说明不存在
        if (memberId == null) {
            return ResultEntity.failed(CrowdConstant.MESSAGE_LOGIN_NEEDED);
        }

        // 2.根据projectTempToken查询ProjectVO对象
        String projectTempToken = returnVO.getProjectTempToken();

        ResultEntity<String> retrieveStringValueResultEntity = redisOperationRemoteService.retrieveStringValueByStringKey(projectTempToken);
        // 判断projectTempToken从Redis中查询是否出错
        if (ResultEntity.FAILED.equals(retrieveStringValueResultEntity.getResult())) {
            return retrieveStringValueResultEntity;
        }

        String json = retrieveStringValueResultEntity.getData();
        //判断查出来的projectPO 对象的json字符串是否为有效字符
        if (!CrowdUtils.strEffectiveCheck(json)) {
            return ResultEntity.failed(CrowdConstant.MESSAGE_PROJECT_NOT_FOUND_FROM_CACHE);
        }

        // 3.将JSON字符串还原成ProjectVO对象
        ProjectVO projectVO = JSON.parseObject(json, ProjectVO.class);

        // 4.获取之前存储过的ReturnVO的List
        List<ReturnVO> returnVOList = projectVO.getReturnVOList();

        // 5.如果returnVOList不是有效集合，则进行初始化
        if (!CrowdUtils.collectionEffectiveCheck(returnVOList)) {

            // ①创建集合对象
            returnVOList = new ArrayList<>();

            // ②将集合对象存入projectVO
            projectVO.setReturnVOList(returnVOList);
        }

        // 6.将当前ReturnVO存入returnVOList
        returnVOList.add(returnVO);

        // 7.将ProjectVO转换为JSON字符串
        String newJsonProject = JSON.toJSONString(projectVO);

        // 8.存入Redis
        return redisOperationRemoteService.saveNormalStringKeyValue(projectTempToken, newJsonProject, -1);

    }

    /**
     * 保存确认信息
     *
     * @param memberConfirmInfoVO 确认信息对象
     * @return 结果对象
     */
    @RequestMapping("/save/member/confirm/info/vo")
    public ResultEntity<String> saveMemberConfirmInfoVO(@RequestBody MemberConfirmInfoVO memberConfirmInfoVO) {

        // 1.检查memberSignToken确认用户是否登录
        String memberSignToken = memberConfirmInfoVO.getMemberSignToken();

        ResultEntity<String> retrieveTokenResultEntity = redisOperationRemoteService.retrieveStringValueByStringKey(memberSignToken);

        if (ResultEntity.FAILED.equals(retrieveTokenResultEntity.getResult())) {
            return ResultEntity.failed(retrieveTokenResultEntity.getMessage());
        }

        String memberId = retrieveTokenResultEntity.getData();

        if (memberId == null) {
            return ResultEntity.failed(CrowdConstant.MESSAGE_LOGIN_NEEDED);
        }

        // 2.根据projectTempToken查询ProjectVO对象
        String projectTempToken = memberConfirmInfoVO.getProjectTempToken();

        ResultEntity<String> retrieveStringValueResultEntity = redisOperationRemoteService.retrieveStringValueByStringKey(projectTempToken);

        if (ResultEntity.FAILED.equals(retrieveStringValueResultEntity.getResult())) {
            return retrieveStringValueResultEntity;
        }

        String json = retrieveStringValueResultEntity.getData();

        if (!CrowdUtils.strEffectiveCheck(json)) {
            return ResultEntity.failed(CrowdConstant.MESSAGE_PROJECT_NOT_FOUND_FROM_CACHE);
        }

        // 3.将JSON字符串还原成ProjectVO对象
        ProjectVO projectVO = JSON.parseObject(json, ProjectVO.class);

        // 4.存入memberConfirmInfoVO对象
        projectVO.setMemberConfirmInfoVO(memberConfirmInfoVO);

        // 5.将新ProjectVO对象重新转换成JSON数据
        String newJsonProject = JSON.toJSONString(projectVO);

        // 6.存入Redis
        return redisOperationRemoteService.saveNormalStringKeyValue(projectTempToken, newJsonProject, -1);
    }

    /**
     * 将相关项目信息存入数据库进行持久化
     *
     * @param memberSignToken  登录token
     * @param projectTempToken 项目token
     * @return 结果对象
     */
    @RequestMapping("/project/manager/save/whole/project")
    public ResultEntity<String> saveWholeProject(
            @RequestParam("memberSignToken") String memberSignToken,
            @RequestParam("projectTempToken") String projectTempToken) {

        // 检查是否登录，也就是检查memberSignToken是否有效
        ResultEntity<String> resultEntity = redisOperationRemoteService.retrieveStringValueByStringKey(memberSignToken);
        if (ResultEntity.FAILED.equals(resultEntity.getResult())) {
            return ResultEntity.failed(resultEntity.getMessage());
        }

        String memberId = resultEntity.getData();

        if (memberId == null) {
            return ResultEntity.failed(CrowdConstant.MESSAGE_ACCESS_DENIED);
        }

        // project-manager工程访问Redis查询ProjectVO对象
        ResultEntity<String> resultEntityForGetValue = redisOperationRemoteService.retrieveStringValueByStringKey(projectTempToken);

        if (ResultEntity.FAILED.equals(resultEntityForGetValue.getResult())) {
            return ResultEntity.failed(resultEntityForGetValue.getMessage());
        }

        // 从Redis查询到JSON字符串
        String projectVOJSON = resultEntityForGetValue.getData();

        // 将JSON字符串还原成ProjectVO对象
        ProjectVO projectVO = JSON.parseObject(projectVOJSON, ProjectVO.class);

        // 执行保存
        ResultEntity<String> resultEntityForSave = dataBaseOperationRemoteService.saveProjectRemote(projectVO, memberId);

        if (ResultEntity.FAILED.equals(resultEntityForSave.getResult())) {
            return resultEntityForSave;
        }

        // 删除Redis中的临时数据
        return redisOperationRemoteService.removeByKey(projectTempToken);
    }
}
