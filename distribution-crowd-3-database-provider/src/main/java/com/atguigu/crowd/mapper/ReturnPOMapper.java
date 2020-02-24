package com.atguigu.crowd.mapper;

import com.atguigu.crowd.entity.ReturnPO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ReturnPOMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ReturnPO record);

    int insertSelective(ReturnPO record);

    ReturnPO selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ReturnPO record);

    int updateByPrimaryKey(ReturnPO record);

    /**
     * 向t_return表中插入回报信息
     * @param returnPOList 回报信息集合
     */
    void insertBatch(@Param("returnPOList") List<ReturnPO> returnPOList);
}