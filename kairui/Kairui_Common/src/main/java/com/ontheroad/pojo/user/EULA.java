package com.ontheroad.pojo.user;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

public class EULA implements Serializable {
    private static final long serialVersionUID = 1L;

    @Getter @Setter private int id;
    @Getter @Setter private String title;      // 标题
    @Getter @Setter private String content;    // 内容
    @Getter @Setter private String createTime; // 创建时间
    @Getter @Setter private String address;    // html地址
}
