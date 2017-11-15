package com.ontheroad.api.request;

import com.ontheroad.api.validate.BaseGroup;

import javax.validation.constraints.NotNull;

/**
 * Created by kedong on 2017/7/2 0002.
 */
public class ConcernRequest extends Request {

    private static final long serialVersionUID = 8468581477899456978L;

    @NotNull(groups = BaseGroup.class)
    private Integer freindId;

    public Integer getFreindId() {
        return freindId;
    }

    public void setFreindId(Integer freindId) {
        this.freindId = freindId;
    }
}
