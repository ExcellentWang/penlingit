package com.ontheroad.mysql.dao;

import com.ontheroad.mysql.entity.TbEula;
import com.ontheroad.mysql.entity.TbEulaExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TbEulaMapper {
    int countByExample(TbEulaExample example);

    int deleteByExample(TbEulaExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TbEula record);

    int insertSelective(TbEula record);

    List<TbEula> selectByExample(TbEulaExample example);

    TbEula selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TbEula record, @Param("example") TbEulaExample example);

    int updateByExample(@Param("record") TbEula record, @Param("example") TbEulaExample example);

    int updateByPrimaryKeySelective(TbEula record);

    int updateByPrimaryKey(TbEula record);
}