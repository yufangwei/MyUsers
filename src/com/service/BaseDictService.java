package com.service;

import java.util.List;

import com.beans.BaseDict;

public interface BaseDictService {


	//查询符合条件的客户来源、客户行业、客户级别
		public List<BaseDict> queryBaseDictList(String duct_id);

}
