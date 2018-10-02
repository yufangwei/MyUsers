package com.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.beans.Customer;
import com.beans.QueryVo;
import com.mapper.CustomerDao;
import com.service.CustomerService;

import cn.itcast.common.utils.Page;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {
	@Resource
	private CustomerDao customerDao;

	@Override
	public Page<Customer> selectPageByQueryVo(QueryVo vo) {
		Page<Customer> page = new Page<Customer>();
		page.setSize(5);
		if(vo!=null) {
			if(vo.getPage()!=null) {
				page.setPage(vo.getPage());
				vo.setStartRow((vo.getPage()-1)*vo.getSize());
			}if(vo.getCustIndustry()!=null&&!vo.getCustIndustry().trim().equals("")) {
				vo.setCustIndustry(vo.getCustIndustry().trim());
			}if(vo.getCustSource()!=null&&!vo.getCustSource().trim().equals("")) {
				vo.setCustSource(vo.getCustSource().trim());
			}if(vo.getCustLevel()!=null&&!vo.getCustLevel().equals("")) {
				vo.setCustLevel(vo.getCustLevel().trim());
			}if(vo.getCustName()!=null&&!vo.getCustName().equals("")) {
				vo.setCustName(vo.getCustName().trim());
			}
		}
		page.setTotal(customerDao.countCustomer(vo));
		List<Customer> list = customerDao.queryCustomerList(vo);
		for(int i=0;i<list.size();i++){
			if(list.get(i).getCust_source().equals("6")){
				list.get(i).setCust_source("电话营销");
			}if(list.get(i).getCust_source().equals("7")){
				list.get(i).setCust_source("网络营销");
			}
			if(list.get(i).getCust_industry().equals("1")){
				list.get(i).setCust_industry("教育培训");
			}if(list.get(i).getCust_industry().equals("2")){
				list.get(i).setCust_industry("电子商务");
			}if(list.get(i).getCust_industry().equals("3")) {
				list.get(i).setCust_industry("对外贸易");
			}if(list.get(i).getCust_industry().equals("4")) {
				list.get(i).setCust_industry("酒店旅游");
			}if(list.get(i).getCust_industry().equals("5")) {
				list.get(i).setCust_industry("房地产");
			}
			if(list.get(i).getCust_level().equals("22")){
				list.get(i).setCust_level("普通客户");
			}if(list.get(i).getCust_level().equals("23")){
				list.get(i).setCust_level("vip客户");
			}
		}
		page.setRows(list);
		return page;
	}

	@Override
	public Customer queryCustomerOne(Integer id) {
		
		return customerDao.queryCustomerOne(id);
	}

	@Override
	public void updateCustomerById(Customer customer) {
		customerDao.updateCustomerById(customer);
		
	}

	@Override
	public void deleteCustomerById(Integer id) {
		customerDao.deleteCustomerById(id);
		
	}

	@Override
	public void addCustomerMessage(Customer customer) {
		customerDao.addCustomerMessage(customer);
		
	}


	


}
