package com.kairui.test;

import java.io.Serializable;

/**
 * Created by kedong on 2017/6/21 0021.
 */
public class BaseBean implements Serializable {

    private static final long serialVersionUID = 1224511591219165767L;
    private Integer userId;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
