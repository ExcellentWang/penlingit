package com.ontheroad.api.request;

import com.ontheroad.api.validate.BaseGroup;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created by kedong on 2017/6/27 0027.
 */
public class SprotRequest extends Request {

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
    @NotNull(groups = {BaseGroup.class})
    private Integer type;

    /**
     * 运动持续时间
     */
    @NotNull(groups = {BaseGroup.class})
    private Long duration;

    /**
     * 运动结束时间
     */
    @NotNull(groups = {BaseGroup.class})
    private Date endTime;

    /**
     * 公里数
     */
    @NotNull(groups = {BaseGroup.class})
    private Double kilometre;


    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Long getDuration() {
        return duration;
    }

    public void setDuration(Long duration) {
        this.duration = duration;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Double getKilometre() {
        return kilometre;
    }

    public void setKilometre(Double kilometre) {
        this.kilometre = kilometre;
    }
}
