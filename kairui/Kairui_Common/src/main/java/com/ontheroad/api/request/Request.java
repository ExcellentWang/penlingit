package com.ontheroad.api.request;

import com.ontheroad.api.validate.BaseGroup;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/6/7 0007.
 */
public class Request implements Serializable {

    private static final long serialVersionUID = -8774762814544518225L;

    /**
     * 用户Id
     */
    @NotNull(groups = {BaseGroup.class})
    private Integer userId;

    /**
     * 客户端IP
     */
    private String ip;

    /**
     * 参数params
     */
    private Map<String, String> headerMap = new HashMap<>();

    /**
     * 当前页
     */
    private Integer pageNum = 1;

    /**
     * 每页显示多少条
     */
    private Integer pageSize = 20;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Map<String, String> getHeaderMap() {
        return headerMap;
    }

    public void setHeaderMap(Map<String, String> headerMap) {
        this.headerMap = headerMap;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
