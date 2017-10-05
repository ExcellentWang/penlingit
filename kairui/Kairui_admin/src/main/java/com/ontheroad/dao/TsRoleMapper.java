package com.ontheroad.dao;

import com.ontheroad.entity.TsRole;
import com.ontheroad.entity.TsRoleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TsRoleMapper {
    int countByExample(TsRoleExample example);

    int deleteByExample(TsRoleExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TsRole record);

    int insertSelective(TsRole record);

    List<TsRole> selectByExample(TsRoleExample example);

    TsRole selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TsRole record, @Param("example") TsRoleExample example);

    int updateByExample(@Param("record") TsRole record, @Param("example") TsRoleExample example);

    int updateByPrimaryKeySelective(TsRole record);

    int updateByPrimaryKey(TsRole record);
}