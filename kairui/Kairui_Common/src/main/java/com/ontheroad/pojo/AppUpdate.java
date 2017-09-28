package com.ontheroad.pojo;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

public class AppUpdate implements Serializable {
    private static final long serialVersionUID = 1L;

    @Getter @Setter private int latestVersion;
    @Getter @Setter private String apkUrl;
    @Getter @Setter private String apkRemark;
    @Getter @Setter private boolean isForceUpdate;
}
