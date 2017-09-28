package com.ontheroad.core.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.SerializeWriter;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.serializer.ValueFilter;

import java.util.*;

public class JSONUtils {
	

	private static ValueFilter valueFilter = new FastJsonValueFilter();
	private static final SerializerFeature[] features = {
		SerializerFeature.WriteDateUseDateFormat,
		SerializerFeature.WriteMapNullValue,// 输出空置字段
		SerializerFeature.WriteNullListAsEmpty,// list字段如果为null，输出为[]，而不是null
		//SerializerFeature.WriteNullNumberAsZero,// 数值字段如果为null，输出为0，而不是null
		//SerializerFeature.WriteNullBooleanAsFalse,// Boolean字段如果为null，输出为false，而不是null
		SerializerFeature.WriteNullStringAsEmpty // 字符类型字段如果为null，输出为""，而不是null
	};
	
	public static String toJson(Object target) {
		return JSON.toJSONString(target,valueFilter,features);
	}
	
	
	/**
	 * 自定义时间格式
	 * 
	 */
	public static String toJson(Object target, String dateFormat) {
		
		
		SerializeWriter out = new SerializeWriter();

        try {
            JSONSerializer serializer = new JSONSerializer(out);
            for (com.alibaba.fastjson.serializer.SerializerFeature feature : features) {
                serializer.config(feature, true);
            }

            serializer.config(SerializerFeature.WriteDateUseDateFormat, true);
            
            if (dateFormat != null) {
                serializer.setDateFormat(dateFormat);
            }
            
            serializer.getValueFilters().add(valueFilter);
            
            serializer.write(target);

            return out.toString();
        } finally {
            out.close();
        }
        
	}
	
	public static List<String> toCombo(Map<String,String> map){
		List<String>list = new ArrayList<String>();
		if(map != null){
			for (Map.Entry<String,String> entry : map.entrySet()) {  
				String str="{\"text\":\""+entry.getValue()+"\",\"value\":\""+entry.getKey()+"\"}";
				list.add(str);
			}  
		}
		return list;
	}
	
	public static <T> T fromJson(String json, Class<T> clazz) {
		if (StringUtils.isEmpty(json)) {
			return null;
		}
		return JSON.parseObject(json, clazz);
	}
	
	public static final <T> List<T> parseArray(String text, Class<T> clazz){
		return JSON.parseArray(text, clazz);
	}
	/**
	  * 
	   * @Description: 将map转换为json字符串
	   * TODO(这里描述这个方法的执行流程 – 可选)
	   * @param map
	   * @return
	   * @author Feng Chao
	   * @date 2015-8-4
	  */
	  public static String mapToJson(Map<Object, Object> map) {
	        Set<Object> keys = map.keySet();
	        String key = "";
	        String value = "";
	        StringBuffer jsonBuffer = new StringBuffer();
	        jsonBuffer.append("{");
	        for (Iterator<Object> it = keys.iterator(); it.hasNext();) {
	            key = (String) it.next();
	            value = (String)map.get(key);
	            jsonBuffer.append(key + ":" +"\""+ value+"\"");
	            if (it.hasNext()) {
	                jsonBuffer.append(",");
	            }
	        }
	        jsonBuffer.append("}");
	        return jsonBuffer.toString();
	    }
}
