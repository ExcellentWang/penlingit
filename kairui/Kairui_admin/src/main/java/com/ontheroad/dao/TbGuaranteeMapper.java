package com.ontheroad.dao;

import com.ontheroad.dto.TbGuranteeDto;
import com.ontheroad.entity.TbGuarantee;
import com.ontheroad.entity.TbGuaranteeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TbGuaranteeMapper {
    int countByExample(TbGuaranteeExample example);

    int deleteByExample(TbGuaranteeExample example);

    int deleteByPrimaryKey(Integer guaranteeId);

    int insert(TbGuarantee record);

    int insertSelective(TbGuarantee record);

    List<TbGuarantee> selectByExample(TbGuaranteeExample example);

    TbGuarantee selectByPrimaryKey(Integer guaranteeId);

    int updateByExampleSelective(@Param("record") TbGuarantee record, @Param("example") TbGuaranteeExample example);

    int updateByExample(@Param("record") TbGuarantee record, @Param("example") TbGuaranteeExample example);

    int updateByPrimaryKeySelective(TbGuarantee record);

    int updateByPrimaryKey(TbGuarantee record);

	List<TbGuranteeDto> selectByExample2(TbGuranteeDto t);
}