package com.ontheroad.dao;

import com.ontheroad.entity.GuaranteeType;
import com.ontheroad.entity.GuaranteeTypeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface GuaranteeTypeMapper {
    int countByExample(GuaranteeTypeExample example);

    int deleteByExample(GuaranteeTypeExample example);

    int deleteByPrimaryKey(Long id);

    int insert(GuaranteeType record);

    int insertSelective(GuaranteeType record);

    List<GuaranteeType> selectByExample(GuaranteeTypeExample example);

    GuaranteeType selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") GuaranteeType record, @Param("example") GuaranteeTypeExample example);

    int updateByExample(@Param("record") GuaranteeType record, @Param("example") GuaranteeTypeExample example);

    int updateByPrimaryKeySelective(GuaranteeType record);

    int updateByPrimaryKey(GuaranteeType record);
}