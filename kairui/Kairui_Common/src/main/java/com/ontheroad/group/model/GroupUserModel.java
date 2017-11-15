package com.ontheroad.group.model;

import java.io.Serializable;
import org.mybatis.annotation.Table;


/**
 * GroupUser对象定义
 * 
 * 工具自动生成代码
 * 
 * @author Admin
 *
 */
@Table(pkId={"id"} ,tabName = "group_user")
public class GroupUserModel implements Serializable {

    /** uid */
	private static final long serialVersionUID = 1L;
    /**  */
    private Integer	id;
    /** 用户ID */
    private Integer	userId;
    /** 分组ID */
    private Integer	groupId;
	
	
	/** 取得 */
	public Integer getId() {
		return id;
	}
	
	/** 设置 */
	public void setId(Integer id) {
		this.id = id;
	}
	/** 取得用户ID */
	public Integer getUserId() {
		return userId;
	}
	
	/** 设置用户ID */
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	/** 取得分组ID */
	public Integer getGroupId() {
		return groupId;
	}
	
	/** 设置分组ID */
	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}


}
