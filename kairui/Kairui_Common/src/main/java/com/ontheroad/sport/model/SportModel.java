package com.ontheroad.sport.model;

import org.mybatis.annotation.Table;

import java.io.Serializable;
import java.util.Date;


/**
 * Sport对象定义
 * <p>
 * 工具自动生成代码
 *
 * @author kedong
 */
@Table(pkId = {"id"}, tabName = "sport")
public class SportModel implements Serializable {

    /**
     * uid
     */
    private static final long serialVersionUID = 1L;
    /**  */
    private Integer id;
    /**  */
    private Integer userid;
    /**
     * 运动类型
     * 1：骑行
     * 2：跑步
     * 3：徒步
     * 4：滑雪
     * 5：游泳
     * 6：室内训练
     * 7：自由行
     */
    private Integer type;
    /**
     * 运动持续时间
     */
    private Long duration;
    /**
     * 运动结束时间
     */
    private Date endTime;
    /**
     * 公里数
     */
    private Double kilometre;


    /**
     * 取得
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 取得
     */
    public Integer getUserid() {
        return userid;
    }

    /**
     * 设置
     */
    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    /**
     * 取得运动类型
     * 1：骑行
     * 2：跑步
     * 3：徒步
     * 4：滑雪
     * 5：游泳
     * 6：室内训练
     * 7：自由行
     */
    public Integer getType() {
        return type;
    }

    /**
     * 设置运动类型
     * 1：骑行
     * 2：跑步
     * 3：徒步
     * 4：滑雪
     * 5：游泳
     * 6：室内训练
     * 7：自由行
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * 取得运动持续时间
     */
    public Long getDuration() {
        return duration;
    }

    /**
     * 设置运动持续时间
     */
    public void setDuration(Long duration) {
        this.duration = duration;
    }

    /**
     * 取得运动结束时间
     */
    public Date getEndTime() {
        return endTime;
    }

    /**
     * 设置运动结束时间
     */
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    /**
     * 取得公里数
     */
    public Double getKilometre() {
        return kilometre;
    }

    /**
     * 设置公里数
     */
    public void setKilometre(Double kilometre) {
        this.kilometre = kilometre;
    }


}
