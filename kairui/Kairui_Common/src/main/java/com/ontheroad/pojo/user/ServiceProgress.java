package com.ontheroad.pojo.user;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

public class ServiceProgress implements Serializable {
    private static final long serialVersionUID = 1L;

    @Getter @Setter private int id;
    @Getter @Setter private int customer_id;
    @Getter @Setter private String progress;
    @Getter @Setter Date create_at;
}
