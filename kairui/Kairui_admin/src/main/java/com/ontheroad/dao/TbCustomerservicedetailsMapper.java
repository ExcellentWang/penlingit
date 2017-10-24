package com.ontheroad.dao;

import com.ontheroad.entity.TbCustomerservicedetails;
import com.ontheroad.entity.TbCustomerservicedetailsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TbCustomerservicedetailsMapper {
    int countByExample(TbCustomerservicedetailsExample example);

    int deleteByExample(TbCustomerservicedetailsExample example);

    int deleteByPrimaryKey(Integer servicedetailId);

    int insert(TbCustomerservicedetails record);

    int insertSelective(TbCustomerservicedetails record);

    List<TbCustomerservicedetails> selectByExample(TbCustomerservicedetailsExample example);

    TbCustomerservicedetails selectByPrimaryKey(Integer servicedetailId);

    int updateByExampleSelective(@Param("record") TbCustomerservicedetails record, @Param("example") TbCustomerservicedetailsExample example);

    int updateByExample(@Param("record") TbCustomerservicedetails record, @Param("example") TbCustomerservicedetailsExample example);

    int updateByPrimaryKeySelective(TbCustomerservicedetails record);

    int updateByPrimaryKey(TbCustomerservicedetails record);
}