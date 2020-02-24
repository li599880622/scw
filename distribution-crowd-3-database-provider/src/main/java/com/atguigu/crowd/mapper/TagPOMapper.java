package com.atguigu.crowd.mapper;

import com.atguigu.crowd.entity.TagPO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TagPOMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TagPO record);

    int insertSelective(TagPO record);

    TagPO selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TagPO record);

    int updateByPrimaryKey(TagPO record);

    /**
     * 向表t_project_tag项目id跟标签id的关系
     * @param projectId 项目id
     * @param tagIdList 标签id集合
     */
    void insertRelationshipBatch(@Param("projectId") Integer projectId, @Param("tagIdList") List<Integer> tagIdList);
}