
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'addGoodsSucc.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head> 
  
  <%
  		
  	String userName = request.getAttribute("userName").toString();
			 
   %>
  <body>
  <style>
  	#i1{
  		width: 100px;
  		height: 83px;
  		background-image: url("img/chenggong.jpg");
  		position: relative;
  		left: 250px;
  		top: 140px;
  	}
  	#i9{
		width:40px;
  		height:40px;
  		background-image:url("img/home.png");
		position: relative;
		left: 5px;
		top: 3px;
	}
	#i10{
		position: relative;
		top: -40px;
		left: 60px;
	}
	#i11{
		width: 150px;
		height: 50px;
		background-color: yellow;
		position: relative;
		top: 10px;
		left: 50px;
	}
  </style>
    <a href="user?op=billList&pageOn=1&userName=<%=userName %>&goodsName=&cusName=&quOrders=">
  	<div id="i11">
 	 <div id="i9">
  	
 	 </div><p id="i10" >返回账单管理</p>
 	</div>
  </a>
    <div id="i1">
    </div>

  </body>
</html>
