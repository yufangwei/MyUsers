package com.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.beans.SysUser;
import com.mapper.SysUserDao;
import com.service.SysUserService;

@Service
@Transactional
public class SysUserServiceImpl implements SysUserService {
	@Resource SysUserDao sysUserDao;
	@Override
	public SysUser querySysUserMessage(SysUser su) {
		return sysUserDao.querySysUserMessage(su);
	}

	
}
