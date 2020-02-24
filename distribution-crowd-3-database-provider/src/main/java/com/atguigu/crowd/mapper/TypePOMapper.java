package com.atguigu.crowd.mapper;

import com.atguigu.crowd.entity.TypePO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TypePOMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TypePO record);

    int insertSelective(TypePO record);

    TypePO selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TypePO record);

    int updateByPrimaryKey(TypePO record);

    /**
     * 向中间表t_project_type插入 项目id跟类型id的对应关系
     * @param projectId 项目id
     * @param typeIdList 类型id集合
     */
    void insertRelationshipBatch(@Param("projectId") Integer projectId, @Param("typeIdList") List<Integer> typeIdList);
}