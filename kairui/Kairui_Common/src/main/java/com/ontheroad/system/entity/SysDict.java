package com.ontheroad.system.entity;

import org.mybatis.annotation.Table;

import java.io.Serializable;
import java.util.Date;

@Table(pkId = "dictId", tabName = "t_s_dict")
public class SysDict implements Serializable{

	private static final long serialVersionUID = -6501133005725185907L;

	//字典ID
	private Long dictId; 

	//字典名称
	private String dictName; 

	//描述
	private String dictDesc; 

	//状态
	private Integer dictStatus; 

	//创建时间
	private Date createTime; 

	//更新时间
	private Date updateTime; 

	public Long  getDictId(){
		return this.dictId;
	}

	public void setDictId(Long dictId){
		this.dictId=dictId;
	}

	public String  getDictName(){
		return this.dictName;
	}

	public void setDictName(String dictName){
		this.dictName = dictName == null ? null : dictName.trim();
	}

	public String  getDictDesc(){
		return this.dictDesc;
	}

	public void setDictDesc(String dictDesc){
		this.dictDesc = dictDesc == null ? null : dictDesc.trim();
	}

	public Integer  getDictStatus(){
		return this.dictStatus;
	}

	public void setDictStatus(Integer dictStatus){
		this.dictStatus=dictStatus;
	}

	public Date  getCreateTime(){
		return this.createTime;
	}

	public void setCreateTime(Date createTime){
		this.createTime=createTime;
	}

	public Date  getUpdateTime(){
		return this.updateTime;
	}

	public void setUpdateTime(Date updateTime){
		this.updateTime=updateTime;
	}
}
