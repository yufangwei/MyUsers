<!DOCTYPE html>
<%@page import="com.entity.CusAddress"%>
<%@page import="com.entity.CusInfo"%>
<%@page import="com.entity.CusMan"%>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>超市账单管理系统</title>
    <link rel="stylesheet" href="css/public.css"/>
    <link rel="stylesheet" href="css/style.css"/>
</head>
<%
	String userName = request.getAttribute("userName").toString();
	CusInfo ci = (CusInfo)request.getAttribute("ci");
	CusAddress ca = (CusAddress)request.getAttribute("ca");
	String word = request.getAttribute("word").toString();
 %>
<body>
<!--头部-->
<header class="publicHeader">
    <h1>超市账单管理系统</h1>

    <div class="publicHeaderR">
        <p><span>下午好！</span><span style="color: #fff21b"><%=userName %></span> , 欢迎你！</p>
        <a href="login.jsp">退出</a>
    </div>
</header>
<!--时间-->
<section class="publicTime">
    <span id="time">2015年1月1日 11:11  星期一</span>
    <a href="#">温馨提示：为了能正常浏览，请使用高版本浏览器！（IE10+）</a>
</section>
<!--主体内容-->
<section class="publicMian ">
    <div class="left">
        <h2 class="leftH2"><span class="span1"></span>功能列表 <span></span></h2>
        <nav>
            <ul class="list">
                <li><a href="user?op=billList&pageOn=1&userName=<%=userName %>&goodsName=&cusName=&quOrders=">账单管理</a></li>
                <li ><a href="user?op=notice&userName=<%=userName%>&pageOn=1&word=">公告管理</a></li>
                <li id="active"><a href="user?op=cusMan&userName=<%=userName%>&pageOn=1&word=">用户管理</a></li>
                <li><a href="user?op=goodsMan&userName=<%=userName%>&pageOn=1&word=">商品管理</a></li>
                <li><a href="user?op=changePassword&userName=<%=userName %>">密码修改</a></li>
                <li><a href="login.jsp">退出系统</a></li>
            </ul>
        </nav>
    </div>
    <div class="right">
        <div class="location">
            <strong>你现在所在的位置是:</strong>
            <span>用户管理页面 >> 用户信息查看页面</span>
        </div>
        <div class="providerView">
            <p><strong>用户编号：</strong><span><%=ci.getId() %></span></p>
            <p><strong>用户名称：</strong><span><%=ci.getName() %></span></p>
            <p><strong>用户密码：</strong><span><%=ci.getPwd() %></span></p>
            <p><strong>用户邮箱：</strong><span><%=ci.getEmail() %></span></p>
            <p><strong>注册时间：</strong><span><%=ci.getRegisterTime() %></span></p>
          	<%
          	 	if(ca.getAddress()!=null){
          	 	%>
          	<p><strong>收货人名：</strong><span><%=ca.getName()%></span></p>
            <p><strong>用户电话：</strong><span><%=ca.getPhone() %></span></p>
            <p><strong>移动电话：</strong><span><%=ca.getTelPhone() %></span></p>
            <p><strong>用户地址：</strong><span><%=ca.getAddress() %></span></p>
          	 <% 	}else{
          	%>
          	<p><!-- <strong>用户地址:</strong> --><span>该用户还没有添加收货地址！</span></p>
          	 <% 	}
          	 %>
           

            <a href="user?op=cusMan&userName=<%=userName%>&pageOn=1&word=">返回</a>
        </div>
    </div>
</section>
<footer class="footer">
</footer>
<script src="js/time.js"></script>

</body>
</html>