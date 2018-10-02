package com.service.impl;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.beans.BaseDict;
import com.mapper.BaseDictDao;
import com.service.BaseDictService;
@Service
@Transactional
public class BaseDictServiceImpl implements BaseDictService{
	@Autowired
	private BaseDictDao baseDictDao;
	@Override
	public List<BaseDict> queryBaseDictList(String duct_id) {
		return baseDictDao.queryBaseDictList(duct_id);
	}

	

}
