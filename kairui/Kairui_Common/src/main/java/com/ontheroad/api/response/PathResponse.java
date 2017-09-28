package com.ontheroad.api.response;

import com.ontheroad.path.model.PathUserModel;

import java.util.List;

/**
 * Created by kedong on 2017/6/29 0029.
 */
public class PathResponse extends Response {

    private static final long serialVersionUID = 7876859486772470266L;

    private List<PathUserModel> pathList;


    public PathResponse() {
        super();
    }

    public PathResponse(List<PathUserModel> pathList) {
        super();
        this.pathList = pathList;
    }

    public List<PathUserModel> getPathList() {
        return pathList;
    }

    public void setPathList(List<PathUserModel> pathList) {
        this.pathList = pathList;
    }
}
