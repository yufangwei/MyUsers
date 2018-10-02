<%@page import="com.entity.GoodsInfo"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'setPhoto.jsp' starting page</title>
    
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
  	GoodsInfo gi = (GoodsInfo)request.getAttribute("gi");
  	String userName = request.getAttribute("userName").toString();
  	String word = request.getAttribute("word").toString();
   %>
  <body>
    <form action="user?op=addGoodsThree&userName=<%=userName%>&goodsType=<%=gi.getTypeName()%>&goodsName=<%=gi.getGoodsName()%>&isNew=<%=gi.getIsNew()
     %>&isRecommend=<%=gi.getIsRecommend()%>&price=<%=gi.getPrice() %>&status=<%=gi.getStatus()
     %>&remark=<%=gi.getRemark() %>" method="post" enctype="multipart/form-data">
    <input type="file" name="file"/>
	<input type="submit" value="上传" />    
    </form>
  </body>
</html>
