package com.ontheroad.group.model;

import java.util.Date;
import java.io.Serializable;
import org.mybatis.annotation.Table;


/**
 * Group对象定义
 * 
 * 工具自动生成代码
 * 
 * @author Admin
 *
 */
@Table(pkId={"id"} ,tabName = "group")
public class GroupModel implements Serializable {

    /** uid */
	private static final long serialVersionUID = 1L;
    /**  */
    private Integer	id;
    /** 分组名称 */
    private String	name;
    /** 分组类型
1：运动组 */
    private Integer	type;
    /** 分组的管理员 */
    private Integer	adminId;
    /** 创建时间 */
    private Date	createTime;
	
	
	/** 取得 */
	public Integer getId() {
		return id;
	}
	
	/** 设置 */
	public void setId(Integer id) {
		this.id = id;
	}
	/** 取得分组名称 */
	public String getName() {
		return name;
	}
	
	/** 设置分组名称 */
	public void setName(String name) {
		this.name = name;
	}
	/** 取得分组类型
1：运动组 */
	public Integer getType() {
		return type;
	}
	
	/** 设置分组类型
1：运动组 */
	public void setType(Integer type) {
		this.type = type;
	}
	/** 取得分组的管理员 */
	public Integer getAdminId() {
		return adminId;
	}
	
	/** 设置分组的管理员 */
	public void setAdminId(Integer adminId) {
		this.adminId = adminId;
	}
	/** 取得创建时间 */
	public Date getCreateTime() {
		return createTime;
	}
	
	/** 设置创建时间 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}


}
