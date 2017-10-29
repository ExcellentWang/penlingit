package com.ontheroad.dao;

import com.ontheroad.entity.TsRoleUser;
import com.ontheroad.entity.TsRoleUserExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TsRoleUserMapper {
    int countByExample(TsRoleUserExample example);

    int deleteByExample(TsRoleUserExample example);

    int deleteByPrimaryKey(Integer roleUserId);

    int insert(TsRoleUser record);

    int insertSelective(TsRoleUser record);

    List<TsRoleUser> selectByExample(TsRoleUserExample example);

    TsRoleUser selectByPrimaryKey(Integer roleUserId);

    int updateByExampleSelective(@Param("record") TsRoleUser record, @Param("example") TsRoleUserExample example);

    int updateByExample(@Param("record") TsRoleUser record, @Param("example") TsRoleUserExample example);

    int updateByPrimaryKeySelective(TsRoleUser record);

    int updateByPrimaryKey(TsRoleUser record);
}