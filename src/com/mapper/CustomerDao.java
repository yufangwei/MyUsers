package com.mapper;

import java.util.List;

import com.beans.Customer;
import com.beans.QueryVo;


public interface CustomerDao {
	
	//符合条件的客户结果集
	public List<Customer> queryCustomerList(QueryVo queryvo);

	//符合条件的所有条数
	public Integer countCustomer(QueryVo queryvo);
	
	//查询要修改的用户信息
	public Customer queryCustomerOne(Integer id);
	
	//修改用户信息
	public void updateCustomerById(Customer customer);
	
	//删除用户信息
	public void deleteCustomerById(Integer id);
	
	//添加用户信息
	public void addCustomerMessage(Customer customer);
}
