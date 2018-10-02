package com.mapper;

import com.beans.SysUser;

public interface SysUserDao {
	
	//查询登录用户的信息
	public SysUser querySysUserMessage(SysUser su);
}
