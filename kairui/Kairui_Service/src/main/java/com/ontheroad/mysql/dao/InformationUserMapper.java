package com.ontheroad.mysql.dao;

import com.ontheroad.mysql.entity.InformationUser;
import com.ontheroad.mysql.entity.InformationUserExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface InformationUserMapper {
    int countByExample(InformationUserExample example);

    int deleteByExample(InformationUserExample example);

    int deleteByPrimaryKey(Long id);

    int insert(InformationUser record);

    int insertSelective(InformationUser record);

    List<InformationUser> selectByExample(InformationUserExample example);

    InformationUser selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") InformationUser record, @Param("example") InformationUserExample example);

    int updateByExample(@Param("record") InformationUser record, @Param("example") InformationUserExample example);

    int updateByPrimaryKeySelective(InformationUser record);

    int updateByPrimaryKey(InformationUser record);
}