package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.entity.Bulletin;
import com.entity.CheckOrders;
import com.entity.CusAddress;
import com.entity.CusInfo;
import com.entity.CusMan;
import com.entity.GoodsInfo;
import com.entity.Orders;
import com.jspsmart.upload.File;
import com.jspsmart.upload.Files;
import com.jspsmart.upload.SmartUpload;
import com.jspsmart.upload.SmartUploadException;
import com.userdao.UserDao;
import com.util.PageUtil;

public class MyServlet extends HttpServlet {
	UserDao ud = new UserDao();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		String op = req.getParameter("op");
		if(op.equals("login")){
			login(req,resp);
		}if(op.equals("billList")){
			billList(req,resp);
		}if(op.equals("updateOrders")){
			updateOrders(req,resp);
		}if(op.equals("updateOneOrders")){
			updateOneOrders(req,resp);
		}if(op.equals("checkOrders")){
			checkOrders(req,resp);
		}if(op.equals("delOrders")){
			delOrders(req,resp);
		}if(op.equals("addOrdersOne")){
			addOrdersOne(req,resp);
		}if(op.equals("addOrdersTwo")){
			addOrdersTwo(req,resp);
		}if(op.equals("notice")){
			notice(req,resp);
		}if(op.equals("checkNotice")){
			checkNotice(req,resp);
		}if(op.equals("updateNotice")){
			updateNotice(req,resp);
		}if(op.equals("updateOneBbs")){
			updateOneNotice(req,resp);
		}if(op.equals("addBbs")){
			addBbs(req,resp);
		}if(op.equals("addBbsTwo")){
			addBbsTwo(req,resp);
		}if(op.equals("delBbs")){
			delBbs(req,resp);
		}if(op.equals("cusMan")){
			cusMan(req,resp);
		}if(op.equals("checkCus")){
			checkCus(req,resp);
		}if(op.equals("addCus")){
			addCus(req,resp);
		}if(op.equals("addCusTwo")){
			addCusTwo(req,resp);
		}if(op.equals("yzname")){
			ajaxName(req,resp);
		}if(op.equals("yzpwd")){
			ajaxPwd(req,resp);
		}if(op.equals("reyzpwd")){
			ajaxRePwd(req,resp);
		}if(op.equals("yzEmail")){
			ajaxEmail(req,resp);
		}if(op.equals("yzTelPhone")){
			ajaxTelPhone(req,resp);
		}if(op.equals("yzMovePhone")){
			ajaxMovePhone(req,resp);
		}if(op.equals("yzAddress")){
			ajaxAddress(req,resp);
		}if(op.equals("updateCus")){
			updateCus(req,resp);
		}if(op.equals("updateCusTwo")){
			updateCusTwo(req,resp);
		}if(op.equals("delCus")){
			delCus(req,resp);
		}if(op.equals("goodsMan")){
			goodsMan(req,resp);
		}if(op.equals("addGoods")){
			addGoods(req,resp);
		}if(op.equals("addGoodsTwo")){
			addGoodsTwo(req,resp);
		}if(op.equals("addGoodsThree")){
			addGoodsThree(req,resp);
		}if(op.equals("fff")){
			checkGoods(req,resp);
		}if(op.equals("delGoods")){
			delGoods(req,resp);
		}if(op.equals("changePassword")){
			changePwd(req,resp);
		}if(op.equals("ajaxChangePwd")){
			ajaxChangePwd(req,resp);
		}if(op.equals("changePwdTwo")){
			changePwdTwo(req,resp);
		}
	}
	
		


	//商品管理的方法，查询所有的商品
	private void goodsMan(HttpServletRequest req, HttpServletResponse resp) {
		int pageOn = Integer.parseInt(req.getParameter("pageOn"));
		String userName = req.getParameter("userName");
		String word = req.getParameter("word");
		int pageSize = 2;
		PageUtil<GoodsInfo> pageGi = ud.checkGoods(pageOn, pageSize, word);
		req.setAttribute("userName", userName);
		req.setAttribute("word", word);
		req.setAttribute("pageGi", pageGi);
		try {
			req.getRequestDispatcher("goodsView.jsp").forward(req, resp);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	//验证登录的方法
	private void login(HttpServletRequest req, HttpServletResponse resp) {
		String userName = req.getParameter("userName");
		String password = req.getParameter("password");
		Object[]obj = {userName,password};
		boolean b = ud.yzLogin(obj);
		if(b){
			try {
				req.setAttribute("userName", userName);
				req.getRequestDispatcher("index.jsp").forward(req, resp);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}else{
			try {
				req.getRequestDispatcher("fail.jsp").forward(req, resp);
			} catch (Exception e) {
				// TODO: handle exception
			}
			
		}
	}
	

	//分页查询所有订单信息的方法
	private void billList(HttpServletRequest req, HttpServletResponse resp) {
		String userName = req.getParameter("userName");
		String goodsName = req.getParameter("goodsName");
		String cusSid = req.getParameter("cusName");
		String quOrders = req.getParameter("quOrders");
		int pageOn = Integer.parseInt(req.getParameter("pageOn"));
		int pageSize = 2;
		int qo;
		if(quOrders!=null&&quOrders!=""){
		qo = quOrders.equals("已确认")?1:0;
		}else{
			qo=3;
		}
		int sid = 0;
		PageUtil<Orders> pageOd=null;
		if(cusSid!=null&&cusSid!=""){
			sid = Integer.parseInt(cusSid);
			Object [] obj={sid,goodsName,qo};
			pageOd=ud.billListAll(pageOn, pageSize, obj);
		}else{
			pageOd =ud.billListAll(pageOn, pageSize, null);
		}
		req.setAttribute("userName", userName);
		req.setAttribute("goodsName", goodsName);
		req.setAttribute("cusName", cusSid);
		req.setAttribute("quOrders", quOrders);
		req.setAttribute("ypage", pageOd);
		try {
			req.getRequestDispatcher("billList.jsp").forward(req, resp);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	//修改订单的方法
		private void updateOrders(HttpServletRequest req, HttpServletResponse resp) {
			String userName =req.getParameter("userName");
			int cusId = Integer.parseInt(req.getParameter("cusId"));
			String goodsId = req.getParameter("goodsId");
			String ordersId = req.getParameter("ordersId");
			CusAddress ca = ud.cusAddr(cusId);
			req.setAttribute("userName", userName);
			req.setAttribute("ca", ca);
			req.setAttribute("cusId",cusId );
			req.setAttribute("goodsId", goodsId);
			req.setAttribute("ordersId", ordersId);
			try {
				req.getRequestDispatcher("billUpdate.jsp").forward(req, resp);
			} catch (Exception e) {
				// TODO: handle exception
			}
			
		}
		
		//提交修改后订单的数据
		private void updateOneOrders(HttpServletRequest req, HttpServletResponse resp) {
			String userName = req.getParameter("userName");
			int cusId = Integer.parseInt(req.getParameter("cusId"));
			int  goodsId = Integer.parseInt(req.getParameter("goodsId"));
			int orderId = Integer.parseInt(req.getParameter("orderId"));
			int newGoodsId = Integer.parseInt(req.getParameter("newGoodsId"));
			int newGoodsNum = Integer.parseInt(req.getParameter("newGoodsNum"));
			String newName = req.getParameter("newName");
			String newTelPhone = req.getParameter("newTelPhone");
			String newMovePhone = req.getParameter("newMovePhone");
			String newAddress = req.getParameter("newAddress");
			Object[]obj = {newName,newTelPhone,newMovePhone,newAddress,cusId};
			ud.updateCusAdd(obj);
			int pay =req.getParameter("pay").equals("已确认")?1:0;
			Object[]obj1 = {pay,orderId};
			ud.updateOrder(obj1);
			Object[]obj2 = {newGoodsId,newGoodsNum,goodsId,orderId};
			int n = ud.updateOrdersGoods(obj2);
			if(n>0){
				System.out.println("修改成功！");
				try {
					req.setAttribute("userName", userName);
					req.getRequestDispatcher("Succ.jsp").forward(req, resp);
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		}
		
		
		
		//查看单条订单详细信息的方法
		private void checkOrders(HttpServletRequest req, HttpServletResponse resp) {
			String userName = req.getParameter("userName");
			int goodsId = Integer.parseInt(req.getParameter("goodsId"));
			int orderId = Integer.parseInt(req.getParameter("orderId"));
			Object []obj = {orderId,goodsId};
			CheckOrders co = ud.checkOrders(obj);
			req.setAttribute("userName", userName);
			req.setAttribute("co", co);
			try {
				req.getRequestDispatcher("providerView.jsp").forward(req, resp);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}

		

		//删除订单的方法，先删除商品信息表
		private void delOrders(HttpServletRequest req, HttpServletResponse resp) {
			String userName = req.getParameter("userName");
			int orderId = Integer.parseInt(req.getParameter("ordersId"));
			int goodsId = Integer.parseInt(req.getParameter("goodsId"));
			Object[]obj = {orderId,goodsId};
			int n = ud.delOrders(obj);
			if(n>0){
				System.out.println("删除成功！");
				try {
					req.setAttribute("userName", userName);
					req.getRequestDispatcher("Succ.jsp").forward(req, resp);
				} catch (Exception e) {
					// TODO: handle exception
				}
			
			}
		}
		

		//添加订单的方法的第一步
		private void addOrdersOne(HttpServletRequest req, HttpServletResponse resp) {
			try {
				String userName = req.getParameter("userName");
				req.setAttribute("userName", userName);
				req.getRequestDispatcher("billAdd.jsp").forward(req, resp);
			} catch (Exception e) {
				// TODO: handle exception
			}
			
		}
		
		//添加订单的第二步
		private void addOrdersTwo(HttpServletRequest req, HttpServletResponse resp) {
			String userName = req.getParameter("userName");
			int cusId = Integer.parseInt(req.getParameter("cusId"));//客户编号
			String cusName = req.getParameter("cusName");//收货人姓名
			String telPhone = req.getParameter("telPhone");//收货人电话
			String movePhone = req.getParameter("movePhone");//收货人移动电话
			String address = req.getParameter("address");//收货地址
			int goodsId = Integer.parseInt(req.getParameter("goodsId"));//商品编号
			int goodsNum = Integer.parseInt(req.getParameter("goodsNum"));//商品数量
			int stutas = req.getParameter("zhifu").equals("已确认")?1:0;//是否付款
			
			Object []obj = {cusId,cusName,telPhone,movePhone,address,};
			int n = ud.addOrdersOne(obj, cusId);
			Object []obj1 = {cusId,stutas};
			int k = ud.addOrderInfo(obj1);
			Object[]obj2 = {goodsId,goodsNum};
			int g = ud.OrderGoodsInfo(obj2);
			if(k>0&&g>0){
				try {
					req.setAttribute("userName", userName);
					req.getRequestDispatcher("Succ.jsp").forward(req, resp);
				} catch (Exception e) {
					// TODO: handle exception
				}
				
			}
		}
		
		

		//公告管理的方法
		private void notice(HttpServletRequest req, HttpServletResponse resp) {
			String userName = req.getParameter("userName");
			String word = req.getParameter("word");
			int pageOn = Integer.parseInt(req.getParameter("pageOn"));
			int pageSize = 2;
			 PageUtil<Bulletin> pageBt = ud.getBbsAll(pageSize,pageOn,word);
			 req.setAttribute("noticePage", pageBt);
			 req.setAttribute("userName", userName);
			 req.setAttribute("word", word);
			 try {
				req.getRequestDispatcher("providerList.jsp").forward(req, resp);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		
		
		//查看单条公告的方法
		private void checkNotice(HttpServletRequest req, HttpServletResponse resp) {
			String userName = req.getParameter("userName");
			int noticeId = Integer.parseInt(req.getParameter("noticeId"));
			Bulletin bt = ud.checkNotice(noticeId);
			req.setAttribute("bt", bt);
			req.setAttribute("userName", userName);
			try {
				req.getRequestDispatcher("bbSView.jsp").forward(req, resp);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		
		//修改公告的方法（第一步）带参数跳转到修改界面
		private void updateNotice(HttpServletRequest req, HttpServletResponse resp) {
			String userName = req.getParameter("userName");
			String noticeId = req.getParameter("noticeId");
			String title = req.getParameter("title");
			String author = req.getParameter("author");
			String createTime = req.getParameter("createTime");
			Bulletin bt = new Bulletin();
			bt.setCreateTime(createTime);
			bt.setId(Integer.parseInt(noticeId));
			bt.setTitle(title);
			bt.setUsername(author);
			req.setAttribute("userName", userName);
			req.setAttribute("bt", bt);
			try {
				req.getRequestDispatcher("updateBbs.jsp").forward(req, resp);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}

		
		//修改公告第二步，保存修改后的数据
		private void updateOneNotice(HttpServletRequest req, HttpServletResponse resp) {
			String userName = req.getParameter("userName");
			int BbsId  = Integer.parseInt(req.getParameter("BbsId"));
			String newTitle = req.getParameter("newTitle");
			String newContext = req.getParameter("newContext");
			String newAuthor = req.getParameter("newAuthor");
			int userId = ud.findId(newAuthor);
			Object[]obj = {newTitle,newContext,userId,BbsId};
			int n = ud.updateBbs(obj);
			req.setAttribute("userName", userName);
			if(n>0){
				System.out.println("添加公告成功！");
				try {
					req.getRequestDispatcher("Succ.jsp").forward(req, resp);
				} catch (Exception e) {
					// TODO: handle exception
				}
				
			}else{
				try {
					req.getRequestDispatcher("fail.jsp").forward(req, resp);
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		}
		

		//添加公告的方法
		private void addBbs(HttpServletRequest req, HttpServletResponse resp) {
			String userName = req.getParameter("userName");
			req.setAttribute("userName", userName);
			try {
				req.getRequestDispatcher("BbsAdd.jsp").forward(req, resp);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		

		//添加用户的第二部
		private void addBbsTwo(HttpServletRequest req, HttpServletResponse resp) {
			String userName = req.getParameter("userName");
			String title = req.getParameter("title");
			String content = req.getParameter("content");
			int id = ud.findId(userName);
			Object[]obj =  {title,content,id};
			int n = ud.addBbs(obj);
			req.setAttribute("userName", userName);
			if(n>0){
				System.out.println("添加公告成功！");
				try {
					req.getRequestDispatcher("Succ.jsp").forward(req, resp);
				} catch (Exception e) {
					// TODO: handle exception
				}
			}else{
				try {
					req.getRequestDispatcher("fail.jsp").forward(req, resp);
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
			
		}
		


		//多选删除公告的方法
		private void delBbs(HttpServletRequest req, HttpServletResponse resp) {
			String str = req.getParameter("str");
			ud.delMore(str);
			notice(req,resp);
		}
		

		//用户管理的方法
		private void cusMan(HttpServletRequest req, HttpServletResponse resp) {
			String userName = req.getParameter("userName");
			int pageOn = Integer.parseInt(req.getParameter("pageOn"));
			String word = req.getParameter("word");
			int pageSize = 2;
			PageUtil<CusInfo> pageUt = ud.cusMan(pageOn, pageSize, word);
			req.setAttribute("word", word);
			req.setAttribute("userName", userName);
			req.setAttribute("pageUt", pageUt);
			try {
				req.getRequestDispatcher("userList.jsp").forward(req, resp);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		

		//查看单条客户数据的方法
		private void checkCus(HttpServletRequest req, HttpServletResponse resp) {
			String word = req.getParameter("word");
			int id = Integer.parseInt(req.getParameter("id"));
			System.out.println(id);
			String userName = req.getParameter("userName");
			CusInfo ci = ud.cusMan(id);
			CusAddress ca = ud.cusAdreessMan(id);
			req.setAttribute("ci", ci);
			req.setAttribute("ca", ca);
			req.setAttribute("userName", userName);
			req.setAttribute("word", word);
			try {
				req.getRequestDispatcher("userView.jsp").forward(req, resp);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		

		//添加客户的方法(第一步：转到添加用户的界面)
		private void addCus(HttpServletRequest req, HttpServletResponse resp) {
			String word = req.getParameter("word");
			String userName = req.getParameter("userName");
			req.setAttribute("word", word);
			req.setAttribute("userName", userName);
			try {
				req.getRequestDispatcher("userAdd.jsp").forward(req, resp);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	

		//验证注册名字的方法
		private void ajaxName(HttpServletRequest req, HttpServletResponse resp) {
			resp.setCharacterEncoding("utf-8");
			String name = req.getParameter("name");
			int i;
			ArrayList<String> list = ud.getAjax();
			PrintWriter out;
			try {
				out = resp.getWriter();
				if(name==null||name==""){
					out.print("不能为空");
				}else{
					for(i=0;i<list.size();i++){
						if(list.get(i).equals(name)){
							out.print("已存在");
							break;
						}
					}
					if(i==list.size()){
						out.print("不存在");
					}
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		//ajax验证密码的方法
		private void ajaxPwd(HttpServletRequest req, HttpServletResponse resp) {
			resp.setCharacterEncoding("utf-8");
			String pwd = req.getParameter("pwd");
			String pattern1 ="^[a-zA-Z][a-zA-Z0-9_]{4,15}$";
			Pattern pattern = Pattern.compile(pattern1);
			Matcher mat = pattern.matcher(pwd);
			PrintWriter out;
			try {
				out = resp.getWriter();
				if(!mat.find()){
					out.print("字母开头，允许5-16字节，允许字母数字下划线");
				}
			} catch (Exception e) {
				// TODO: handle exception
			}	
		}
		

		//验证再次输入密码的方法
		private void ajaxRePwd(HttpServletRequest req, HttpServletResponse resp) {
			resp.setCharacterEncoding("utf-8");
			String pwd = req.getParameter("pwd");
			String rePwd = req.getParameter("rePwd");
			PrintWriter out ;
			try {
				out = resp.getWriter();
				if(!pwd.equals(rePwd)){
					out.print("重复输入密码错误");
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		

		//ajax验证邮箱的方法
		private void ajaxEmail(HttpServletRequest req, HttpServletResponse resp) {
			resp.setCharacterEncoding("utf-8");
			String email = req.getParameter("email");
			String pattern1 ="^[a-zA-Z0-9_\\.-]+@([a-zA-Z0-9-]+\\.)+[a-zA-Z0-9]{2,4}$";
			Pattern pattern = Pattern.compile(pattern1);
			Matcher mat = pattern.matcher(email);
			PrintWriter out;
			try {
				out = resp.getWriter();
				if(email==null||email==""||!mat.find()){
					out.print("邮箱格式不正确");
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
			
		}
		

					//ajax验证手机号码的方法
		private void ajaxTelPhone(HttpServletRequest req, HttpServletResponse resp) {
			resp.setCharacterEncoding("utf-8");
			String telPhone = req.getParameter("telPhone");
			String pattern1 ="^[1][3,4,5,7,8][0-9]{9}$";
			Pattern pattern = Pattern.compile(pattern1);
			Matcher mat = pattern.matcher(telPhone);
			PrintWriter out;
			try {
				out = resp.getWriter();
				if(telPhone==null||telPhone==""||!mat.find()){
					out.print("手机格式不正确");
				}
			} catch (Exception e) {
						
			}
					
		}
		
		//ajax移动电话号码的方法
		private void ajaxMovePhone(HttpServletRequest req, HttpServletResponse resp) {
			resp.setCharacterEncoding("utf-8");
			String movePhone = req.getParameter("movePhone");
			String pattern1 ="^[1][3,4,5,7,8][0-9]{9}$";
			Pattern pattern = Pattern.compile(pattern1);
			Matcher mat = pattern.matcher(movePhone);
			PrintWriter out;
			try {
				out = resp.getWriter();
				if(movePhone==null||movePhone==""||!mat.find()){
					out.print("移动电话格式不正确");
				}
			} catch (Exception e) {
						
			}
					
		}
		
		
		//ajax住址的方法
		private void ajaxAddress(HttpServletRequest req, HttpServletResponse resp) {
			resp.setCharacterEncoding("utf-8");
			String address = req.getParameter("address");
			String pattern1 ="\\w*[\u4e00-\u9fa5]+\\w*";
			Pattern pattern = Pattern.compile(pattern1);
			Matcher mat = pattern.matcher(address);
			PrintWriter out;
			try {
				out = resp.getWriter();
				if(address==null||address==""||!mat.find()){
					out.print("住址格式不正确");
				}
			} catch (Exception e) {
						
			}
					
		}
		
		
		//添加用户的第二步：保存用户数据

		private void addCusTwo(HttpServletRequest req, HttpServletResponse resp) {
			String userName = req.getParameter("userName");
			String word = req.getParameter("word");
			String name = req.getParameter("name");
			String pwd = req.getParameter("pwd");
			String rePwd = req.getParameter("rePwd");
			String email = req.getParameter("email");
			String telPhone = req.getParameter("telPhone");
			String movePhone = req.getParameter("movePhone");
			String address = req.getParameter("address");
			String userlei = req.getParameter("userlei");
			//验证所提交的用户名
			int i;
			int a = 1;
			ArrayList<String> list = ud.getAjax();
				if(name==null||name==""){
					a=2;
				}else{
					for(i=0;i<list.size();i++){
						if(list.get(i).equals(name)){
						a = 2;
							break;
						}
					}
					if(i==list.size()){
						a = 1;
					}
				}
				//验证所提交的密码
				int b = 1;
				String pattern1 ="^[a-zA-Z][a-zA-Z0-9_]{4,15}$";
				Pattern pattern = Pattern.compile(pattern1);
				Matcher mat = pattern.matcher(pwd);
					if(!mat.find()){
						b = 2;
					}else{
						if(pwd.equals(rePwd)){
							b = 1;
						}
					}
					//验证邮箱
					int c = 1;
				String pattern2 ="^[a-zA-Z0-9_\\.-]+@([a-zA-Z0-9-]+\\.)+[a-zA-Z0-9]{2,4}$";
				Pattern pattern3 = Pattern.compile(pattern2);
				Matcher mat1 = pattern3.matcher(email);
				if(email==null||email==""||!mat1.find()){
					b = 2;
				}
				//验证手机号码TelPhone
				int d = 1;
				String pattern4 ="^[1][3,4,5,7,8][0-9]{9}$";
				Pattern pattern5 = Pattern.compile(pattern4);
				Matcher mat2 = pattern5.matcher(telPhone);
				if(telPhone==null||telPhone==""||!mat2.find()){
					d = 2;
				}
				
				//验证移动电话
				int e = 1;
				String pattern6 ="^[1][3,4,5,7,8][0-9]{9}$";
				Pattern pattern7 = Pattern.compile(pattern6);
				Matcher mat3 = pattern7.matcher(movePhone);
				if(movePhone==null||movePhone==""||!mat3.find()){
					d = 2;
				}
				
				//验证地址
				int f = 1;
				String pattern8 ="\\w*[\u4e00-\u9fa5]+\\w*";
				Pattern pattern9 = Pattern.compile(pattern8);
				Matcher mat4 = pattern9.matcher(address);
				if(address==null||address==""||!mat4.find()){
					d = 2;
				}
				req.setAttribute("userName", userName);
				req.setAttribute("word", word);
				if(a==1&&b==1&&c==1&&d==1&&e==1&&f==1&&userlei!=null&&userlei!=""){
					if(userlei.equals("user")){
						Object[]obj={name,pwd};
						int n = ud.addUser(obj);
						System.out.println(n+000);
						if(n>0){
							System.out.println("添加管理员成功！");
							try {
								req.getRequestDispatcher("Succ.jsp").forward(req, resp);
							} catch (Exception e2) {
								// TODO: handle exception
							}
						}
					}
					if(userlei.equals("cus")){
						int id = ud.lastCusId();
						Object[]obj={name,pwd,email};
						int k = ud.addCus(obj);
						Object[]obj1={id,name,telPhone,movePhone,address};
						int j = ud.addAddressTwo(obj1);
						if(k>0&&j>0){
							System.out.println("添加客户成功！");					
							try {
								req.getRequestDispatcher("Succ.jsp").forward(req, resp);
							} catch (Exception e2) {
								// TODO: handle exception
							}
						}
					}
				}
		}
		

		//修改用户信息的方法
		private void updateCus(HttpServletRequest req, HttpServletResponse resp) {
			String userName = req.getParameter("userName");
			String word = req.getParameter("word");
			int id = Integer.parseInt(req.getParameter("id"));
			CusInfo ci = ud.cusMan(id);
			CusAddress ca = ud.cusAdreessMan(id);
			req.setAttribute("userName", userName);
			req.setAttribute("word", word);
			req.setAttribute("ci", ci);
			req.setAttribute("ca", ca);
			try {
				req.getRequestDispatcher("userUpdate.jsp").forward(req, resp);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		
		

		//修改用户信息的第二步，上传用户修改后的信息
		private void updateCusTwo(HttpServletRequest req, HttpServletResponse resp) {
			String userName = req.getParameter("userName");
			String word = req.getParameter("word");
			int id = Integer.parseInt(req.getParameter("id"));
			String cusName = req.getParameter("cusName");
			String cusPwd = req.getParameter("cusPwd");
			String email = req.getParameter("email");
			String shouHuoName = req.getParameter("shouHuoName");
			String telPhone = req.getParameter("telPhone");
			String movePhone = req.getParameter("movePhone");
			String address = req.getParameter("address");
			Object []obj1={cusName,cusPwd,email,id};
			Object []obj2={shouHuoName,telPhone,movePhone,address,id};
			int n = ud.updateCusTwo(obj1);
			int k = ud.updateAddTwo(obj2);
			req.setAttribute("userName", userName);
			req.setAttribute("word", word);
				try {
					if(k>0&&n>0){
						System.out.println("修改成功！");
						req.getRequestDispatcher("Succ.jsp").forward(req, resp);
					}else{
						System.out.println("修改是失败！");
						req.getRequestDispatcher("fail.jsp").forward(req, resp);
					}
				} catch (Exception e) {
					// TODO: handle exception
				}
		}
		
		//删除客户信息的方法
		private void delCus(HttpServletRequest req, HttpServletResponse resp) {
			String word = req.getParameter("word");
			String userName = req.getParameter("userName");
			int id = Integer.parseInt(req.getParameter("id"));
			ArrayList<Integer>list = ud.getOrderId(id);
			ud.delCusOrder(list);
			ud.delCusOrders(id);
			ud.delAddress(id);
			int n = ud.delCusMan(id);
			req.setAttribute("word", word);
			req.setAttribute("userName", userName);
			try {
				if(n>0){
					System.out.println("删除成功！");
					req.getRequestDispatcher("Succ.jsp").forward(req, resp);
				}else{
					System.out.println("删除失败！");
					req.getRequestDispatcher("fail.jsp").forward(req, resp);
				}
			} catch (Exception o) {
				// TODO: handle exception
			}
			
		}
		

		//上传添加商品的方法，跳转到商品添加页面
		private void addGoods(HttpServletRequest req, HttpServletResponse resp) {
	 		String userName = req.getParameter("userName");
			req.setAttribute("userName", userName);
			try {
				req.getRequestDispatcher("goodsAdd.jsp").forward(req, resp);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		

		//上传添加商品的第二步
		private void addGoodsTwo(HttpServletRequest req, HttpServletResponse resp) {
			String word = req.getParameter("word");
			String userName = req.getParameter("userName");
			String goodsType = req.getParameter("goodsType");
			String goodsName = req.getParameter("goodsName");
			double goodsPrice = Double.parseDouble(req.getParameter("goodsPrice"));
			String discount = req.getParameter("discount");
			String isNew = req.getParameter("isNew");
			String isRecommend = req.getParameter("isRecommend");
			String status = req.getParameter("status");
			String remark = req.getParameter("remark");
				int isNewId = isNew.equals("是") ? 0:1;
				int isRecommendId = isRecommend.equals("是")?0:1;
				int statusId = status.equals("是")?0:1;
				req.setAttribute("userName", userName);
				req.setAttribute("word", word);
				GoodsInfo gi = new GoodsInfo();
				gi.setGoodsName(goodsName);
				gi.setIsNew(isNewId);
				gi.setIsRecommend(isRecommendId);
				gi.setPrice(goodsPrice);
				gi.setStatus(statusId);
				gi.setRemark(remark);
				gi.setTypeName(goodsType);
				req.setAttribute("gi", gi);
			try {
				req.getRequestDispatcher("setPhoto.jsp").forward(req, resp);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		

		//添加商品的第三步，上传图片
		private void addGoodsThree(HttpServletRequest req, HttpServletResponse resp) {
			String userName = req.getParameter("userName");
			String goodsType = req.getParameter("goodsType");
			String goodsName = req.getParameter("goodsName");
			String isNew = req.getParameter("isNew");
			String isRecommend = req.getParameter("isRecommend");
			String status = req.getParameter("status");
			String remark = req.getParameter("remark");
			String price = req.getParameter("price");
			int goodsTypeId = 0;
			System.out.println(goodsType);
			if(goodsType.equals("饮食")){
				goodsTypeId=1;
			}if(goodsType.equals("家电")){
				goodsTypeId=2;
			}if(goodsType.equals("汽车")){
				goodsTypeId=3;
			}if(goodsType.equals("手机")){
				goodsTypeId =4;
			}if(goodsType.equals("床上用品")){
				goodsTypeId = 5;
			}
			System.out.println(goodsTypeId);
			//创建上传对象
			SmartUpload su = new SmartUpload();
			String path1 = "";
			//设置不允许上传的文件类型
			try {
				//设置初始化状态
				su.initialize(getServletConfig(), req, resp);
				//设置中文乱码
				su.setCharset("utf-8");
				su.setDeniedFilesList("txt");
				//限制上传的大小，字节b
				su.setMaxFileSize(1024*1024);
				//上传临时文件
				su.upload();
			} catch (Exception e) {
				// TODO: handle exception
			}
			//获取临时文件夹里面的文件
			Files files = su.getFiles();
			String pa="";
			for(int i=0;i<files.getCount();i++){
				//获取具体的文件
				File f = files.getFile(i);
				//判断文件是否为空
				if(!f.isMissing()){
					//获取文件名称
					String fileName = f.getFileName();
					long time = System.currentTimeMillis();
					//获取上传路径
					path1 = req.getRealPath("loade/"+time+fileName);
					pa = time+fileName;
					//保存
					try {
						f.saveAs(path1);
					}  catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			
		}
			Object[]obj = {goodsTypeId,goodsName,price,isNew,isRecommend,status,pa,remark};
			int n = ud.addGoods(obj);
			if(n>0){
				req.setAttribute("userName", userName);
				System.out.println("添加商品成功！");
				try {
					req.getRequestDispatcher("Succ.jsp").forward(req, resp);
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
	}

		//查看单个商品的方法
		private void checkGoods(HttpServletRequest req, HttpServletResponse resp) {
			String userName = req.getParameter("userName");
			int id = Integer.parseInt(req.getParameter("id"));
			String photo = req.getParameter("photo");
			req.setAttribute("userName", userName);
			req.setAttribute("id", id);
			req.setAttribute("photo", photo);
			try {
				req.getRequestDispatcher("shwoOneGoods.jsp").forward(req, resp);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		

		//删除商品的方法
		private void delGoods(HttpServletRequest req, HttpServletResponse resp) {
			String userName = req.getParameter("userName");
			int id = Integer.parseInt(req.getParameter("id"));
			int n = ud.delGoodsOne(id);
			if(n>0){
				System.out.println("删除成功！");
				req.setAttribute("userName", userName);
				try {
					req.getRequestDispatcher("Succ.jsp").forward(req, resp);
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
			
		}
		


		//修改密码的方法
		private void changePwd(HttpServletRequest req, HttpServletResponse resp) {
			String userName = req.getParameter("userName");
			String userPwd = ud.changePwd(userName);
			req.setAttribute("userPwd", userPwd);
			req.setAttribute("userName", userName);
			try {
				req.getRequestDispatcher("password.jsp").forward(req, resp);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		

		//验证新、旧密码的ajax
		private void ajaxChangePwd(HttpServletRequest req, HttpServletResponse resp) {
			resp.setCharacterEncoding("utf-8");
			String newPassword = req.getParameter("newPassword");
			String reNewPassword = req.getParameter("reNewPassword");
			PrintWriter out ;
			try {
				out = resp.getWriter();
				if(!newPassword.equals(reNewPassword)||newPassword==null||newPassword==""||newPassword.length()<6){
					out.print("重复输入密码错误");
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		
		//修改密码的第二步方法
		private void changePwdTwo(HttpServletRequest req, HttpServletResponse resp) {
			String userName = req.getParameter("userName");
			String userPwd = req.getParameter("userPwd");
			String oldPassword = req.getParameter("oldPassword");
			String newPassword = req.getParameter("newPassword");
			String reNewPassword = req.getParameter("reNewPassword");
			int n = 0;
			req.setAttribute("userName", userName);
			if(userPwd.equals(oldPassword)&&reNewPassword.equals(newPassword)){
				Object[]obj = {newPassword,userName};
				 n = ud.changePwdTwo(obj);
			}
			try {
				if(n>0){
					System.out.println("修改成功！");
					req.getRequestDispatcher("Succ.jsp").forward(req, resp);
				}else{
					System.out.println("修改失败！");
					req.getRequestDispatcher("fail.jsp").forward(req, resp);
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
			
		}
}
