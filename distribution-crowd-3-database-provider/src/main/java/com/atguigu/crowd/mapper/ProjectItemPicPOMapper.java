package com.atguigu.crowd.mapper;

import com.atguigu.crowd.entity.ProjectItemPicPO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProjectItemPicPOMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ProjectItemPicPO record);

    int insertSelective(ProjectItemPicPO record);

    ProjectItemPicPO selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ProjectItemPicPO record);

    int updateByPrimaryKey(ProjectItemPicPO record);

    /**
     *  保存详情图片跟项目id
     * @param projectItemPicPOList 项目详情图片集合
     */
    void insertBatch(@Param("projectItemPicPOList") List<ProjectItemPicPO> projectItemPicPOList);
}