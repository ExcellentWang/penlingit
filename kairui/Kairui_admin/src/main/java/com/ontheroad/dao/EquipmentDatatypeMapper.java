package com.ontheroad.dao;

import com.ontheroad.entity.EquipmentDatatype;
import com.ontheroad.entity.EquipmentDatatypeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EquipmentDatatypeMapper {
    int countByExample(EquipmentDatatypeExample example);

    int deleteByExample(EquipmentDatatypeExample example);

    int deleteByPrimaryKey(Integer equipmentType);

    int insert(EquipmentDatatype record);

    int insertSelective(EquipmentDatatype record);

    List<EquipmentDatatype> selectByExample(EquipmentDatatypeExample example);

    EquipmentDatatype selectByPrimaryKey(Integer equipmentType);

    int updateByExampleSelective(@Param("record") EquipmentDatatype record, @Param("example") EquipmentDatatypeExample example);

    int updateByExample(@Param("record") EquipmentDatatype record, @Param("example") EquipmentDatatypeExample example);

    int updateByPrimaryKeySelective(EquipmentDatatype record);

    int updateByPrimaryKey(EquipmentDatatype record);
}