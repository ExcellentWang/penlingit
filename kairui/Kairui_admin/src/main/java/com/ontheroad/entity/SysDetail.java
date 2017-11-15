package com.ontheroad.entity;

import org.mybatis.annotation.Table;

import java.io.Serializable;
import java.util.Date;

@Table(pkId = "detailId",  tabName = "t_s_detail")
public class SysDetail implements Serializable{
	private static final long serialVersionUID = -1511138067533995101L;

	//字典明细ID
	private Long detailId; 

	//字典ID
	private Long dictId; 

	//字典字段名称
	private String detailName; 

	//
	private String detailValue; 

	//描述
	private String detailDesc; 

	//状态
	private Integer detailStatus; 

	//创建时间
	private Date createTime; 

	//
	private Date updateTime; 

	public Long  getDetailId(){
		return this.detailId;
	}

	public void setDetailId(Long detailId){
		this.detailId=detailId;
	}

	public Long  getDictId(){
		return this.dictId;
	}

	public void setDictId(Long dictId){
		this.dictId=dictId;
	}

	public String  getDetailName(){
		return this.detailName;
	}

	public void setDetailName(String detailName){
		this.detailName = detailName == null ? null : detailName.trim();
	}

	public String  getDetailValue(){
		return this.detailValue;
	}

	public void setDetailValue(String detailValue){
		this.detailValue = detailValue == null ? null : detailValue.trim();
	}

	public String  getDetailDesc(){
		return this.detailDesc;
	}

	public void setDetailDesc(String detailDesc){
		this.detailDesc = detailDesc == null ? null : detailDesc.trim();
	}

	public Integer  getDetailStatus(){
		return this.detailStatus;
	}

	public void setDetailStatus(Integer detailStatus){
		this.detailStatus=detailStatus;
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
