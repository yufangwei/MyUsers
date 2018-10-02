package com.util;

import java.util.ArrayList;

/*@分页工具包
 * @分页的设置
 * 总页数（总条数/每页条数）
 * 每页的条数的数据显示
 * 设置每页条数
 * 
 */



public class PageUtil<T> {
	//设置当前页
	private int pageOn; 
	//每页显示条数
	private int pageSize;
	//总条数
	private int pageCount;
	//用于显示每页所有数据的数组
	private ArrayList<T>list;
	
	public int getPageOn() {
		return pageOn;
	}
	public void setPageOn(int pageOn) {
		this.pageOn = pageOn;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getPageCount() {
		return pageCount;
	}
	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}
	public ArrayList<T> getList() {
		return list;
	}
	public void setList(ArrayList<T> list) {
		this.list = list;
	}
	//首页的方法
	public int getPageFir(){
		return 1;
	}
	//上一页的方法
	public int getPageUp(){
		int page = 0;
		if(pageOn>1){
			 page = pageOn-1;
		}else{
			page =1;
		}
		return page;
	}
	//总页数的方法
	public int totalPage(){
		int topage = 0;
		if(pageCount%pageSize==0){
			topage = pageCount/pageSize;
		}else{
			topage = pageCount/pageSize +1;
		}
		return topage;
	}
	//下一页的方法
	public int getPageDown(){
		int page = 0;
		if(pageOn==totalPage()){
			page = totalPage();
		}else{
			page = pageOn + 1;
		}
		return page;
	}
	//尾页的方法
	public int lastPage(){
		return totalPage();
	}
	
}
