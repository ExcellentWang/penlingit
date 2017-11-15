package com.ontheroad.api.response;

import com.ontheroad.sport.model.SportModel;

import java.util.List;

/**
 * Created by kedong on 2017/6/28 0028.
 */
public class SportResponse extends Response {

    private static final long serialVersionUID = -4884642875685776740L;

    public SportResponse() {
        super();
    }

    public SportResponse(List<SportModel> sports) {
        super();
        this.sports = sports;
    }

    private List<SportModel> sports;

    public List<SportModel> getSports() {
        return sports;
    }

    public void setSports(List<SportModel> sports) {
        this.sports = sports;
    }
}
