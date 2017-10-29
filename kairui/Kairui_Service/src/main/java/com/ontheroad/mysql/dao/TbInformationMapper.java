package com.ontheroad.mysql.dao;

import com.ontheroad.mysql.entity.TbInformation;
import com.ontheroad.mysql.entity.TbInformationExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TbInformationMapper {
    int countByExample(TbInformationExample example);

    int deleteByExample(TbInformationExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TbInformation record);

    int insertSelective(TbInformation record);

    List<TbInformation> selectByExample(TbInformationExample example);

    TbInformation selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TbInformation record, @Param("example") TbInformationExample example);

    int updateByExample(@Param("record") TbInformation record, @Param("example") TbInformationExample example);

    int updateByPrimaryKeySelective(TbInformation record);

    int updateByPrimaryKey(TbInformation record);
}