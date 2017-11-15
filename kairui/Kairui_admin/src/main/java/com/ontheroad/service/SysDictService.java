package com.ontheroad.service;

import java.util.Map;

import org.springframework.remoting.service.annotation.RemoteService;

import com.ontheroad.entity.SysDict;

import cn.modoumama.service.base.BaseService;

public interface SysDictService extends BaseService<SysDict,Long>{
	
	/**
	 * 根据字典名称获得字典明细键值对
	 * @FunName getDetailNameMap
	 * @param dictName 字典名称
	 * @return 字典明细Map {[name,value],[name,value]}
	 * @author dingyang
	 * @Create Date 2015年9月22日
	 */
	public Map<String, String> getDetailNameMap(String dictName);//得到明细map<name,value>
	
	/**
	 * 根据字典名称获得字典明细键值对
	 * @FunName getDetailNameMap
	 * @param dictName 字典名称
	 * @return 字典明细Map {[value,name],[value,name]}
	 * @author dingyang
	 * @Create Date 2015年9月22日
	 */
	public Map<String, String> getDetailValueMap(String dictName);//得到明细map<value,name>
	
	/**
	 * 根据字典名称和明细名称获得明细值
	 * @FunName getDetailValue
	 * @param dictName 字典名称
	 * @param detailName 明细名称
	 * @return String 明细值
	 * @author dingyang
	 * @Create Date 2015年9月22日
	 */
	public String getDetailValue(String dictName,String detailName);//得到明细value
}
