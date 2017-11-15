package com.ontheroad.api.request;

import javax.validation.constraints.NotNull;

/**
 * Created by kedong on 2017/7/4 0004.
 */
public class GroupRequest extends Request {
    private static final long serialVersionUID = 1849816716654989605L;

    public interface createGroup {
    }

    // 分组类型 1：运动组
    @NotNull(groups = createGroup.class)
    private Integer type;

    // 需要添加的好友id列表、或需要踢出分组的好友id列表
    private Integer[] friendIds;

    // 分组名称
    private String groupName;

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer[] getFriendIds() {
        return friendIds;
    }

    public void setFriendIds(Integer[] friendIds) {
        this.friendIds = friendIds;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
}
