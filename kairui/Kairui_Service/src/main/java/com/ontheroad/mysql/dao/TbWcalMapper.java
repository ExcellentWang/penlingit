package com.ontheroad.mysql.dao;

import com.ontheroad.mysql.entity.TbWcal;
import com.ontheroad.mysql.entity.TbWcalExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TbWcalMapper {
    int countByExample(TbWcalExample example);

    int deleteByExample(TbWcalExample example);

    int deleteByPrimaryKey(Long id);

    int insert(TbWcal record);

    int insertSelective(TbWcal record);

    List<TbWcal> selectByExample(TbWcalExample example);

    TbWcal selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") TbWcal record, @Param("example") TbWcalExample example);

    int updateByExample(@Param("record") TbWcal record, @Param("example") TbWcalExample example);

    int updateByPrimaryKeySelective(TbWcal record);

    int updateByPrimaryKey(TbWcal record);
}