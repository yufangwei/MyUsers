package com.controller;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.beans.BaseDict;
import com.beans.Customer;
import com.beans.QueryVo;
import com.beans.SysUser;
import com.service.BaseDictService;
import com.service.CustomerService;
import com.service.SysUserService;

import cn.itcast.common.utils.JudgeToken;
import cn.itcast.common.utils.Page;
import cn.itcast.common.utils.TokenProccessor;
/**
 * 客户管理
 * @author Administrator
 *	private String custName;
	private String custSource;
	private String custIndustry;
	private String custLevel;
 */
@Controller
@RequestMapping("customer")
public class CustomerController {	
	@Resource CustomerService customerService;
	@Resource BaseDictService baseDictService;
	@Resource SysUserService sysUserService;
	
	//首页分页的方法
	@RequestMapping("list.action")
	public String list(QueryVo vo,HttpServletRequest request) {
		List<BaseDict> fromType = baseDictService.queryBaseDictList("002");
		List<BaseDict> industryType = baseDictService.queryBaseDictList("001");
		List<BaseDict> levelType = baseDictService.queryBaseDictList("006");
		
		request.setAttribute("fromType", fromType);
		request.setAttribute("industryType", industryType);
		request.setAttribute("levelType", levelType);
		Page<Customer> page = customerService.selectPageByQueryVo(vo);
		
		request.setAttribute("custName", vo.getCustName());
		request.setAttribute("custSource", vo.getCustSource());
		request.setAttribute("custIndustry", vo.getCustIndustry());
		request.setAttribute("custLevel", vo.getCustLevel());
		request.setAttribute("page", page);
		
		return "customer";
	}
	
	//修改客户前的查看单个客户信息
	@RequestMapping("edit.action")
	public @ResponseBody 
	Customer updateCustomer(Integer id) {
		if(id==0) {
			return null;
		}else {
			return customerService.queryCustomerOne(id);		
		}
		
	}
	
	//访问登录页
	@RequestMapping("login.action")
	public String login(HttpServletRequest request) {
		String token = TokenProccessor.getInstance().makeToken();//创建令牌
		System.out.println("在FormServlet中生成的token："+token);
		request.getSession().setAttribute("token", token);  //在服务器使用session保存token(令牌)
		return "index";
	}
	
	//用户登录
	@RequestMapping(value="verifyUser.action",method={RequestMethod.POST,RequestMethod.GET})
	public String verifyUser(SysUser su,HttpServletRequest request) throws InterruptedException {
		JudgeToken jt = new JudgeToken();
		HttpSession session = request.getSession();
		Thread.sleep(3*1000);
		boolean b = jt.isRepeatSubmit(request);//判断用户是否是重复提交
		if(b==true) {
			 System.out.println("请不要重复提交");
			 session.removeAttribute("token");//移除session中的token
		}else {
			System.out.println("处理用户提交请求！！");
			SysUser sysUser = sysUserService.querySysUserMessage(su);	
			if(sysUser!=null) {
				String name = sysUser.getUser_name();
				session.setAttribute("name", name);
				session.setMaxInactiveInterval(3*60);
				session.removeAttribute("token");//移除session中的token
				return "redirect:/customer/list.action";
			}
		}	
		String name = (String)session.getAttribute("name");
		if(name!=null&&!name.trim().equals("")) {
			return "redirect:/customer/list.action";
		}
		return null;
	}
	
	//修改用户的方法
	@RequestMapping("update.action")
	public @ResponseBody 
	String updateCustomerMessage(Customer customer) {
		customerService.updateCustomerById(customer);
		return "ok";
	}
	
	//删除用户的方法
	@RequestMapping("delete.action")
	public @ResponseBody
	String deleteCustomerMessage(Integer id) {
		customerService.deleteCustomerById(id);
		return "ok";
	}
	
	//添加用户的方法
	@RequestMapping("add.action")
	public @ResponseBody 
	String addCustomerMessage(Customer customer) {
		customer.setCust_createtime(new Date());
		customerService.addCustomerMessage(customer);
		return "ok";
	}
	
	//退出登录的方法
	@RequestMapping("logOut.action")
	public String logOut(HttpServletRequest request) {
		request.getSession().invalidate();
		String token = TokenProccessor.getInstance().makeToken();//创建令牌
		System.out.println("在FormServlet中生成的token："+token);
		request.getSession().setAttribute("token", token);  //在服务器使用session保存token(令牌)
		return "index";
	}
}
 