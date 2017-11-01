package com.ontheroad.mysql.entity;

import java.io.Serializable;
import java.util.Date;

public class TsDetail implements Serializable{
    /**
     * 明细ID
     */
    private Integer detailId;

    /**
     * 对应字典ID
     */
    private Integer dictId;

    /**
     * 明细名称
     */
    private String detailName;

    /**
     * 明细值
     */
    private String detailValue;

    /**
     * 明细描述
     */
    private String detailDesc;

    /**
     * 1启用，2禁用
     */
    private Integer detailStatus;

    /**
     * 
     */
    private Date createTime;

    /**
     * 
     */
    private Date updateTime;

    /**
     * 获取明细ID
     *
     * @cgw 2017-11-01 22:19:47
     */
    public Integer getDetailId() {
        return detailId;
    }

    /**
     * 设置明细ID
     *
     * @param detailId the value for t_s_detail.detail_id
     *
     * @cgw 2017-11-01 22:19:47
     */
    public void setDetailId(Integer detailId) {
        this.detailId = detailId;
    }

    /**
     * 获取对应字典ID
     *
     * @cgw 2017-11-01 22:19:47
     */
    public Integer getDictId() {
        return dictId;
    }

    /**
     * 设置对应字典ID
     *
     * @param dictId the value for t_s_detail.dict_id
     *
     * @cgw 2017-11-01 22:19:47
     */
    public void setDictId(Integer dictId) {
        this.dictId = dictId;
    }

    /**
     * 获取明细名称
     *
     * @cgw 2017-11-01 22:19:47
     */
    public String getDetailName() {
        return detailName;
    }

    /**
     * 设置明细名称
     *
     * @param detailName the value for t_s_detail.detail_name
     *
     * @cgw 2017-11-01 22:19:47
     */
    public void setDetailName(String detailName) {
        this.detailName = detailName == null ? null : detailName.trim();
    }

    /**
     * 获取明细值
     *
     * @cgw 2017-11-01 22:19:47
     */
    public String getDetailValue() {
        return detailValue;
    }

    /**
     * 设置明细值
     *
     * @param detailValue the value for t_s_detail.detail_value
     *
     * @cgw 2017-11-01 22:19:47
     */
    public void setDetailValue(String detailValue) {
        this.detailValue = detailValue == null ? null : detailValue.trim();
    }

    /**
     * 获取明细描述
     *
     * @cgw 2017-11-01 22:19:47
     */
    public String getDetailDesc() {
        return detailDesc;
    }

    /**
     * 设置明细描述
     *
     * @param detailDesc the value for t_s_detail.detail_desc
     *
     * @cgw 2017-11-01 22:19:47
     */
    public void setDetailDesc(String detailDesc) {
        this.detailDesc = detailDesc == null ? null : detailDesc.trim();
    }

    /**
     * 获取1启用，2禁用
     *
     * @cgw 2017-11-01 22:19:47
     */
    public Integer getDetailStatus() {
        return detailStatus;
    }

    /**
     * 设置1启用，2禁用
     *
     * @param detailStatus the value for t_s_detail.detail_status
     *
     * @cgw 2017-11-01 22:19:47
     */
    public void setDetailStatus(Integer detailStatus) {
        this.detailStatus = detailStatus;
    }

    /**
     * 获取
     *
     * @cgw 2017-11-01 22:19:47
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置
     *
     * @param createTime the value for t_s_detail.create_time
     *
     * @cgw 2017-11-01 22:19:47
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取
     *
     * @cgw 2017-11-01 22:19:47
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置
     *
     * @param updateTime the value for t_s_detail.update_time
     *
     * @cgw 2017-11-01 22:19:47
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}