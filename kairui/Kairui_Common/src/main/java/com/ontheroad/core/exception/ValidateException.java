package com.ontheroad.core.exception;

import com.ontheroad.api.ErrDetailInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/6/8 0008.
 */
public class ValidateException extends Exception {
    private static final long serialVersionUID = -6326008389898936025L;
    private static final String MESSAGE = "参数不合法";

    public ValidateException() {
        super(MESSAGE);
    }

    public ValidateException(List<ErrDetailInfo> infos) {
        super(MESSAGE);
        this.infos = infos;
    }

    private List<ErrDetailInfo> infos = new ArrayList<>();

    public List<ErrDetailInfo> getInfos() {
        return infos;
    }

    public void setInfos(List<ErrDetailInfo> infos) {
        this.infos = infos;
    }
}


