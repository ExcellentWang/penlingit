package com.ontheroad.api.response;

import com.ontheroad.api.ErrDetailInfo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by kedong on 2017/6/7 0007.
 */
public class Response implements Serializable {

    private static final long serialVersionUID = -946099361373352229L;

    public static final String MSG_SUCCESS = "success";
    public static final String MSG_API_NOT_EXIST = "接口不存在";
    public static final String MSG_ERROR = "服务器异常";
    public static final String MSG_VALIDATE_ERROR = "参数错误";
    public static final String MSG_INVALID_SIGN = "签名sign错误";
    public static final String MSG_NOT_LOGIN = "您还未登录，请先登录";
    public static final String MSG_NOT_HTTPS = "不安全的请求";

    // 成功
    public static final Integer CODE_SUCCESS = 0;
    // 业务失败
    public static final Integer CODE_FAILED = 1;
    // 异常
    public static final Integer CODE_ERROR = 2;
    // 参数错误
    public static final Integer CODE_VALIDATE_ERROR = 3;
    // sign不对
    public static final Integer CODE_INVALID_SIGN = 4;
    // 未登录
    public static final Integer CODE_NOT_LOGIN = 5;
    // 未登录
    public static final Integer CODE_NOT_HTTPS = 6;

    /**
     * 结果码
     */
    private Integer code;

    /**
     * 结果信息
     */
    private String msg;

    /**
     * 结果数据
     */
    private Map<String, Object> resultMap = new HashMap<>();

    /**
     * 错误信息列表
     */
    private List<ErrDetailInfo> extra = new ArrayList<>();

    public Response() {
        this.code = CODE_SUCCESS;
        this.msg = MSG_SUCCESS;
    }

    public Response(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<ErrDetailInfo> getExtra() {
        return extra;
    }

    public void setExtra(List<ErrDetailInfo> extra) {
        this.extra = extra;
    }

    public Map<String, Object> getResultMap() {
        return resultMap;
    }

    public void setResultMap(Map<String, Object> resultMap) {
        this.resultMap = resultMap;
    }

    public void addErrDetailInfo(Integer code, String desc, String pkInfo) {
        ErrDetailInfo errDetailInfo = new ErrDetailInfo();
        errDetailInfo.setErrorCode(code);
        errDetailInfo.setErrorDes(desc);
        errDetailInfo.setPkInfo(pkInfo);
        extra.add(errDetailInfo);
    }

    public void addResultData(String key, Object value) {
        resultMap.put(key, value);
    }

    public void addResultMap(Map<String, Object> result) {
        resultMap.putAll(result);
    }


    public static Response SUCCESS() {
        return new Response();
    }

    public static Response SUCCESS(String msg) {
        return new Response(CODE_SUCCESS, msg);
    }

    public static Response FAILED(String msg) {
        return new Response(CODE_FAILED, msg);
    }

    public static Object ERROR() {
        return new Response(CODE_ERROR, MSG_ERROR);
    }

    public static Response ERROR(String msg) {
        return new Response(CODE_ERROR, msg);
    }

    public static Response VALIDATE_ERROR() {
        return new Response(CODE_VALIDATE_ERROR, MSG_VALIDATE_ERROR);
    }

    public static Response INVALID_SIGN() {
        return new Response(CODE_INVALID_SIGN, MSG_INVALID_SIGN);
    }

    public static Response NOT_LOGIN() {
        return new Response(CODE_NOT_LOGIN, MSG_NOT_LOGIN);
    }

    public static Response NOT_HTTPS() {
        return new Response(CODE_NOT_HTTPS, MSG_NOT_HTTPS);
    }

    public void addErrDetailInfo(List<ErrDetailInfo> infos) {
        if (infos != null && infos.size() > 0) {
            extra.addAll(infos);
        }
    }


}
