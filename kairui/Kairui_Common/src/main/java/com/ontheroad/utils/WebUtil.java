package com.ontheroad.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.serializer.SimpleDateFormatSerializer;

import org.apache.commons.lang.StringUtils;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import java.text.ParseException;
import java.util.Date;
import java.util.Map;

/**
 * web工具类
 *
 * @author shawn.du
 */
public class WebUtil {
    /**
     * 成功标识
     */
    public static final int OK = 10000;
    //错误标识
    public static final int ERROR = 20000;
    //错误标识,需要前端用户确认
    public static final int CONFIRM = 20001;
    public static final int TIMEOUT = 30000;

    public static final String CONTEXT_TYPE = "application/json; charset=utf-8";

    public static final String JSON_RESULT_MESSAGE = "message";
    public static final String JSON_RESULT_STATUS_CODE = "code";
    public static final String JSON_RESULT_STATUS_CODE_TWO = "status";
    public static final String JSON_RESULT_DATA = "data";
    public static final String JSON_RESULT_TOTAL = "totalItem";

    public static final String DATE_FMT = "yyyy-MM-dd";

    /**
     * 生成一个失败的JSON对象
     *
     * @param message 失败消息
     * @return String
     */
    public static String getFailureJson(String message) {
        JSONObject resultJson = new JSONObject();
        resultJson.put(JSON_RESULT_MESSAGE, message);
        resultJson.put(JSON_RESULT_STATUS_CODE, ERROR);
        return resultJson.toJSONString();
    }

    public static String getFailureJson(String message, int code) {
        JSONObject resultJson = new JSONObject();
        resultJson.put(JSON_RESULT_MESSAGE, message);
        resultJson.put(JSON_RESULT_STATUS_CODE, code);
        return resultJson.toJSONString();
    }

    public static String getSuccessJson(String message, int code, String status) {
        JSONObject resultJson = new JSONObject();
        resultJson.put(JSON_RESULT_MESSAGE, message);
        resultJson.put(JSON_RESULT_STATUS_CODE, code);
        resultJson.put(JSON_RESULT_STATUS_CODE_TWO, status);
        return resultJson.toJSONString();
    }

    /**
     * 生成一个成功的json
     *
     * @return
     */
    public static String getSuccessJson() {
        return getSuccessJson(null, null, null);
    }

    public static <T> String getSuccessJson(Object data) {
        return getSuccessJson(null, data, null);
    }

    public static <T> String getSuccessJson(Object data, String dateFmt) {
        return getSuccessJson(null, data, null, dateFmt);
    }

    public static String getSuccessJson(String message, Object data, Integer totalItem) {
        return getSuccessJson(message, data, totalItem, null);
    }
    public static String getSuccessJson(String message, Object data, Integer totalItem, String dateFmt){
    	return getSuccessJson(message,  data,  totalItem,  dateFmt,null);
    }
    public static String getSuccessJson(String message, Object data, Integer totalItem, String dateFmt,Map<String, Object> dp) {
        JSONObject resultJson = new JSONObject();
        resultJson.put(JSON_RESULT_MESSAGE, message);
        resultJson.put(JSON_RESULT_STATUS_CODE, OK);
        resultJson.put(JSON_RESULT_DATA, data);
        resultJson.put(JSON_RESULT_TOTAL, totalItem);
        resultJson.put("dp", dp);
        if (data == null) return resultJson.toJSONString();

        if (dateFmt == null) {
            return JSON.toJSONString(resultJson, SerializerFeature.WriteDateUseDateFormat, SerializerFeature.DisableCircularReferenceDetect);
        }

        SerializeConfig mapping = new SerializeConfig();
        mapping.put(Date.class, new SimpleDateFormatSerializer(dateFmt)); //yyyy-MM-dd
        return JSON.toJSONString(resultJson, mapping, SerializerFeature.WriteDateUseDateFormat, SerializerFeature.DisableCircularReferenceDetect);
    }

    public static Integer getInteger(ServletRequest request, String paraName) {
        String value = request.getParameter(paraName);
        if (StringUtils.isEmpty(value)) return null;

        try {
            return Integer.valueOf(value);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    public static Long getLong(ServletRequest request, String paraName) {
        String value = request.getParameter(paraName);
        if (StringUtils.isEmpty(value)) return null;

        try {
            return Long.valueOf(value);
        } catch (NumberFormatException e) {
            return null;
        }
    }


    public static String subZeroAndDot(String s) {
        if (s.indexOf(".") > 0) {
            s = s.replaceAll("0+?$", "");//去掉多余的0
            s = s.replaceAll("[.]$", "");//如最后一位是.则去掉
        }
        return s;
    }

    public static Builder newBuilder() {
        return Builder.newInstance();
    }

    public static class Builder {
        private String message;
        private Object data;
        private Integer totalItem;
        private String dateFmt;
        private int code;

        private Builder() {
        }

        private static Builder newInstance() {
            return new Builder();
        }

        public Builder message(String message) {
            this.message = message;
            return this;
        }

        public Builder data(Object data) {
            this.data = data;
            return this;
        }

        public Builder totalItem(Integer totalItem) {
            this.totalItem = totalItem;
            return this;
        }

        public Builder dataFmt(String dataFmt) {
            this.dateFmt = dataFmt;
            return this;
        }


        public Builder code(int code) {
            this.code = code;
            return this;
        }

        public String getSuccessJson() {
            return WebUtil.getSuccessJson(message, data, totalItem, dateFmt);
        }

        public String getFailureJson() {
            if (code == 0) {
                code = ERROR;
            }
            return WebUtil.getFailureJson(message, code);
        }
    }
    
}
