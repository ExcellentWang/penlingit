package com.ontheroad.core.util;

import java.io.Serializable;
import java.util.List;

public class PageData<T>  implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -919322234651720398L;
	
	private int totalPage;
	private int total;
	private int currentPage;
	private int pageSize;
   
    private List<T>  dataList;

    public PageData(int total,int currentPage,int pageSize,List<T>  dataList){
    	this.currentPage = currentPage;
    	this.dataList = dataList;
    	this.total = total;
    	this.pageSize = pageSize;
    	this.totalPage = total%pageSize==0 ?  total/pageSize  : ( total/pageSize + 1) ;
    }
    
	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public List<T> getDataList() {
		return dataList;
	}

	public void setDataList(List<T> dataList) {
		this.dataList = dataList;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
    
    
}
