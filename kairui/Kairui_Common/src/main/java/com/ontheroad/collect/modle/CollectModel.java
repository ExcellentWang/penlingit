package com.ontheroad.collect.modle;

import java.io.Serializable;
import org.mybatis.annotation.Table;


/**
 * Collect对象定义
 * 
 * 工具自动生成代码
 * 
 * @author Admin
 *
 */
@Table(pkId={"id"} ,tabName = "collect")
public class CollectModel implements Serializable {

    /** uid */
	private static final long serialVersionUID = 1L;
    /**  */
    private Integer	id;
    /**  */
    private Integer	userid;
    /** 类型（1：轨迹） */
    private Integer	type;
    /**  */
    private Integer	pathid;
	
	
	/** 取得 */
	public Integer getId() {
		return id;
	}
	
	/** 设置 */
	public void setId(Integer id) {
		this.id = id;
	}
	/** 取得 */
	public Integer getUserid() {
		return userid;
	}
	
	/** 设置 */
	public void setUserid(Integer userid) {
		this.userid = userid;
	}
	/** 取得类型（1：轨迹） */
	public Integer getType() {
		return type;
	}
	
	/** 设置类型（1：轨迹） */
	public void setType(Integer type) {
		this.type = type;
	}
	/** 取得 */
	public Integer getPathid() {
		return pathid;
	}
	
	/** 设置 */
	public void setPathid(Integer pathid) {
		this.pathid = pathid;
	}


}
