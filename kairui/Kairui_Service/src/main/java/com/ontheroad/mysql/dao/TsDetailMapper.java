package com.ontheroad.mysql.dao;

import com.ontheroad.mysql.entity.TsDetail;
import com.ontheroad.mysql.entity.TsDetailExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TsDetailMapper {
    int countByExample(TsDetailExample example);

    int deleteByExample(TsDetailExample example);

    int deleteByPrimaryKey(Integer detailId);

    int insert(TsDetail record);

    int insertSelective(TsDetail record);

    List<TsDetail> selectByExample(TsDetailExample example);

    TsDetail selectByPrimaryKey(Integer detailId);

    int updateByExampleSelective(@Param("record") TsDetail record, @Param("example") TsDetailExample example);

    int updateByExample(@Param("record") TsDetail record, @Param("example") TsDetailExample example);

    int updateByPrimaryKeySelective(TsDetail record);

    int updateByPrimaryKey(TsDetail record);
}