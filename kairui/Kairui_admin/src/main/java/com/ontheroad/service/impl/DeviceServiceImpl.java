package com.ontheroad.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ontheroad.dao.TbEquipmentMapper;
import com.ontheroad.dao.equipmentDatatypeMapper;
import com.ontheroad.entity.TbEquipment;
import com.ontheroad.entity.TbEquipmentExample;
import com.ontheroad.entity.equipmentDatatype;
import com.ontheroad.entity.equipmentDatatypeExample;
import com.ontheroad.service.IDeviceService;
import com.ontheroad.utils.MapUtil;
import com.ontheroad.utils.WebUtil;

@Service
public class DeviceServiceImpl implements IDeviceService{
	@Autowired
	private equipmentDatatypeMapper equipmentDatatypeMapper;
	@Autowired
	private TbEquipmentMapper tbEquipmentMapper;
	@Override
	public void addDeviceType(equipmentDatatype equipmentDatatype) {
		if(equipmentDatatype.getEquipmentType()==null){
			equipmentDatatypeMapper.insertSelective(equipmentDatatype);
		}else{
			equipmentDatatypeMapper.updateByPrimaryKey(equipmentDatatype);
		}
	}
	@Override
	public List<equipmentDatatype> selectAllDeviceType() {
		equipmentDatatypeExample example=new equipmentDatatypeExample();
		return equipmentDatatypeMapper.selectByExample(example);
	}
	@Override
	public Map<Object, Object> delDeviceType(Integer equipmentType) {
		//验证是否已存在该设备类型的设备
		TbEquipmentExample example=new TbEquipmentExample();
		example.createCriteria().andTypeEqualTo("0"+equipmentType);
		List<TbEquipment> ls=tbEquipmentMapper.selectByExample(example);
		if(ls.size()>0){
			return MapUtil.getFailureJson("该设备类型已存在入库的设备，不能删除！");
		}
		equipmentDatatypeMapper.deleteByPrimaryKey(equipmentType);
		return MapUtil.getSuccessJson();
	}
	@Override
	public equipmentDatatype selectDeviceTypeId(Integer equipmentType) {
		equipmentDatatypeExample example=new equipmentDatatypeExample();
		example.createCriteria().andEquipmentTypeEqualTo(equipmentType);
		List<equipmentDatatype> ls= equipmentDatatypeMapper.selectByExample(example);
		return ls.get(0);
	}
	@Override
	public Integer allSize() {
		TbEquipmentExample example=new TbEquipmentExample();
		return tbEquipmentMapper.selectByExample(example).size();
	}
	
}
