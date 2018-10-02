package com.service;

import com.beans.Customer;
import com.beans.QueryVo;

import cn.itcast.common.utils.Page;

public interface CustomerService {

	// 通过四个条件 查询分页对象
	public Page<Customer> selectPageByQueryVo(QueryVo vo);
		
	//查询要修改的用户的信息
	public Customer queryCustomerOne(Integer id);
	
	//修改用户信息
	public void updateCustomerById(Customer customer);
	
	//删除用户信息
	public void deleteCustomerById(Integer id);
	
	//添加用户信息
	public void addCustomerMessage(Customer customer);
}
