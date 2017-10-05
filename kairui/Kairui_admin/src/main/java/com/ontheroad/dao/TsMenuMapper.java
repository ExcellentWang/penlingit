package com.ontheroad.dao;

import com.ontheroad.entity.TsMenu;
import com.ontheroad.entity.TsMenuExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TsMenuMapper {
    int countByExample(TsMenuExample example);

    int deleteByExample(TsMenuExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TsMenu record);

    int insertSelective(TsMenu record);

    List<TsMenu> selectByExample(TsMenuExample example);

    TsMenu selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TsMenu record, @Param("example") TsMenuExample example);

    int updateByExample(@Param("record") TsMenu record, @Param("example") TsMenuExample example);

    int updateByPrimaryKeySelective(TsMenu record);

    int updateByPrimaryKey(TsMenu record);
    
    List<TsMenu> getMenusUser(Integer userId);
}