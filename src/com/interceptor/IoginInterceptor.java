package com.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class IoginInterceptor implements HandlerInterceptor{

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object arg2, Exception arg3)
			throws Exception {
	
		
		
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg2) throws Exception {
		// TODO Auto-generated method stub
		//false代表拦截，不往下执行
		//true代表 不拦截，往下执行
		String pathName = request.getRequestURI();
		//跳转页面位登录页面，可以往下执行
		if(pathName.indexOf("login.action")>0) {
			return true;
		}
		if(pathName.indexOf("verifyUser.action")>0) {
			return true;
		}
		//session里面有值，说明已经登录，可以通过拦截器
		HttpSession session = request.getSession();
		String name = (String)session.getAttribute("name");
		if(name!=null&&!name.trim().equals("")) {
			return true;
		}
		
		//其他情形跳转回登录页面,并进行拦截，不往下执行
		response.sendRedirect(request.getContextPath()+"/customer/login.action");
		return false;
	}

	
}
