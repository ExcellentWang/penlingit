package com.ontheroad.core.util;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 
  * @ClassName: SerialNoUtil
  * @Description: 流水工具类
  * @author 马正正
  * @date 2015年10月23日
 */
public class SerialNoUtil {
	private static Map<String, Map<String, AtomicInteger>> holder=new HashMap<String, Map<String,AtomicInteger>>(2);
	private static SimpleDateFormat fmDate=new SimpleDateFormat("yyMMdd");
	private static SimpleDateFormat fmTime=new SimpleDateFormat("yyMMddHHmmss");
	private static DecimalFormat fmDecimal=new DecimalFormat("000000");
	
	/**
	 * 
	  * @Description: 获取流水
	  * @param key		区分不同业务
	  * @param append	前后缀字符串
	  * @return
	  * @author 马正正
	  * @date 2015年10月23日
	 */
	public static String getSerialNo(String key,String... append){
		if(holder.get(key)==null){
			synchronized (holder) {
				if(holder.get(key)==null){
					//双锁检测与无序性ConcurrentHashMap
					holder.put(key, new HashMap<String, AtomicInteger>(2));
				}
			}
		}
		
		Map<String, AtomicInteger> keyMap=holder.get(key);
		
		Calendar cal=Calendar.getInstance();
		String dStr=fmDate.format(cal.getTime());
		String tStr=fmTime.format(cal.getTime());
		
		if(keyMap.get(dStr)==null){
			synchronized (keyMap) {
				if(keyMap.get(dStr)==null){
					Set<String> keys=keyMap.keySet();
					cal.add(Calendar.DATE, -1);
					for(String k:keys){
						if(key.compareTo(fmDate.format(cal.getTime()))<0){
							keyMap.remove(k);
						}
					}
					
					//双锁检测与无序性ConcurrentHashMap
					keyMap.put(dStr, new AtomicInteger(0));
				}
			}
		}
		
		String pre="";
		String suf="";
		if(append!=null){
			if(append.length>0){
				pre=append[0];
			}
			if(append.length>1){
				suf=append[1];
			}
		}
		
		return pre+tStr+fmDecimal.format(keyMap.get(dStr).addAndGet(1))+suf;
	}
}
