package com.ontheroad.utils;

import java.util.HashMap;
import java.util.Map;

import com.ontheroad.pojo.Constant.BaseConstant;

public class MapUtil {
	// 返回前端状态
	public static final int appUserSuccessStatus = 0; // 成功
	public static final int appUserFaileStatus = 1; // 失败
	public static final int appUserErrorStatus = 2; // 系统出错
	
    /**
     * 生成一个失败的对象
     *
     * @param message 失败消息
     * @return String
     */
    public static Map<Object,Object> getFailureJson(String message) {
    	Map<Object,Object> map = new HashMap<Object,Object>(); 
    	map.put("code", BaseConstant.appUserErrorStatus);
 		map.put("msg", message);
 		return map;
    }
    

    /**
     * 生成一个成功的对象
     * @param data
     * @return
     */
    public static Map<Object,Object> getSuccessJson(Object data) {
    	Map<Object,Object> map = new HashMap<Object,Object>(); 
    	map.put("data", data);
		map.put("code", BaseConstant.appUserSuccessStatus);
		map.put("msg", "成功");
		map.put("totalItem",null );
 		return map;
    }
    
    /**
     * 生成一个成功的对象
     * @param data
     * @return
     */
    public static Map<Object,Object> getSuccessJson() {
    	Map<Object,Object> map = new HashMap<Object,Object>(); 
		map.put("code", BaseConstant.appUserSuccessStatus);
		map.put("msg", "成功");
		map.put("totalItem",null );
 		return map;
    }
   /**
    * 生成一个分页成功的对象
    * @param data
    * @return
    */
    public static Map<Object,Object> getSuccessJson(Object data,Integer totalItem) {
    	Map<Object,Object> map = new HashMap<Object,Object>(); 
    	map.put("data", data);
		map.put("code", BaseConstant.appUserSuccessStatus);
		map.put("msg", "成功");
		map.put("totalItem",totalItem );
 		return map;
    }
}
