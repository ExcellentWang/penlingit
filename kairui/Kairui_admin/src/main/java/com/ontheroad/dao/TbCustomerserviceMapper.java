package com.ontheroad.dao;

import com.ontheroad.dto.TbCustomerserviceDto;
import com.ontheroad.entity.TbCustomerservice;
import com.ontheroad.entity.TbCustomerserviceExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TbCustomerserviceMapper {
    int countByExample(TbCustomerserviceExample example);

    int deleteByExample(TbCustomerserviceExample example);

    int deleteByPrimaryKey(Integer customerId);

    int insert(TbCustomerservice record);

    int insertSelective(TbCustomerservice record);

    List<TbCustomerservice> selectByExample(TbCustomerserviceExample example);

    TbCustomerservice selectByPrimaryKey(Integer customerId);

    int updateByExampleSelective(@Param("record") TbCustomerservice record, @Param("example") TbCustomerserviceExample example);

    int updateByExample(@Param("record") TbCustomerservice record, @Param("example") TbCustomerserviceExample example);

    int updateByPrimaryKeySelective(TbCustomerservice record);

    int updateByPrimaryKey(TbCustomerservice record);
    
    List<TbCustomerserviceDto> countByExample2(TbCustomerserviceDto t);
}