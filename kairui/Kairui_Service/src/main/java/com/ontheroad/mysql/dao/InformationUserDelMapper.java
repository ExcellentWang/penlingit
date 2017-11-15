package com.ontheroad.mysql.dao;

import com.ontheroad.mysql.entity.InformationUserDel;
import com.ontheroad.mysql.entity.InformationUserDelExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface InformationUserDelMapper {
    int countByExample(InformationUserDelExample example);

    int deleteByExample(InformationUserDelExample example);

    int deleteByPrimaryKey(Long id);

    int insert(InformationUserDel record);

    int insertSelective(InformationUserDel record);

    List<InformationUserDel> selectByExample(InformationUserDelExample example);

    InformationUserDel selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") InformationUserDel record, @Param("example") InformationUserDelExample example);

    int updateByExample(@Param("record") InformationUserDel record, @Param("example") InformationUserDelExample example);

    int updateByPrimaryKeySelective(InformationUserDel record);

    int updateByPrimaryKey(InformationUserDel record);
}