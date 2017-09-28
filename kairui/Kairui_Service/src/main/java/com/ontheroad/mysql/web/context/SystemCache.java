/*package com.ontheroad.mysql.web.context;

import java.util.Hashtable;
import java.util.Map;

import com.ontheroad.mysql.system.service.DictCache;
import org.apache.log4j.Logger;

import com.ontheroad.system.entity.SysDetail;

*//**
 * 系统缓存操作类
 *//*
public class SystemCache {
	private static Logger log = Logger.getLogger(SystemCache.class);
	public static final String CACHE_DICT = "CACHE_DICT";
	public static final String CACHE_MENU = "CACHE_MENU";
	public static final String CACHE_DIM_AREA = "CACHE_DIM_AREA";
	public static final String CACHE_DIM_AREA_HASH = "CACHE_DIM_AREA_HASH";
	//缓存数据字典
	public static void cacheAllDict(){
		log.debug("method: cacheAllDict() ");
		OscacheFactory oscacheFactory = OscacheFactory.getInstance();
		Hashtable<String, Map<String, SysDetail>> dict = DictCache.getInstance().loadData();
		oscacheFactory.putObject(CACHE_DICT, dict);
	}
	
	//重新缓存数据字典
	public static void reCacheDict() {
		log.debug("method: reCacheDict() ");
		OscacheFactory.getInstance().removeObject(CACHE_DICT);
		cacheAllDict();
	}
	
	  

}
*/