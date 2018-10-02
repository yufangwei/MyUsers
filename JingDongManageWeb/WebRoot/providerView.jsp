<!DOCTYPE html>
<%@page import="com.entity.CheckOrders"%>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>超市账单管理系统</title>
    <link rel="stylesheet" href="css/public.css"/>
    <link rel="stylesheet" href="css/style.css"/>
</head>
<%
	String userName = request.getAttribute("userName").toString();
	CheckOrders co = (CheckOrders)request.getAttribute("co");
 %>
<body>
<!--头部-->
<header class="publicHeader">
    <h1>超市账单管理系统</h1>

    <div class="publicHeaderR">
        <p><span>下午好！</span><span style="color: #fff21b"> <%=userName %></span> , 欢迎你！</p>
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
                <li id="active"><a href="user?op=notice&userName=<%=userName%>&pageOn=1&word=">公告管理</a></li>
                <li><a href="user?op=cusMan&userName=<%=userName%>&pageOn=1&word=">用户管理</a></li>
                <li><a href="user?op=goodsMan&userName=<%=userName%>&pageOn=1&word=">商品管理</a></li>
                <li><a href="user?op=changePassword&userName=<%=userName %>">密码修改</a></li>
                <li><a href="login.jsp">退出系统</a></li>
            </ul>
        </nav>
    </div>
    <div class="right">
        <div class="location">
            <strong>你现在所在的位置是:</strong>
            <span>订单管理页面 >> 信息查看</span>
        </div>
        <div class="providerView">
            <p><strong>订单编码：</strong><span><%=co.getOrderId() %></span></p>
            <p><strong>商品编码：</strong><span><%=co.getGoodsId() %></span></p>
            <p><strong>商品类型：</strong><span><%=co.getTypeName() %></span></p>
            <p><strong>商品名称：</strong><span><%=co.getGoodsName() %></span></p>
            <p><strong>商品单价：</strong><span><%=co.getPrice() %></span></p>
            <p><strong>商品数量：</strong><span><%=co.getQuantity() %></span></p>
            <p><strong>客户姓名：</strong><span><%=co.getName() %></span></p>
            <p><strong>客户电话：</strong><span><%=co.getTelPhone() %></span></p>
             <p><strong>客户地址：</strong><span><%=co.getAddress() %></span></p>
            <p><strong>是否付款：</strong><span><%=co.getStatus() %></span></p>
            <p><strong>下单时间：</strong><span><%=co.getOrderTime() %></span></p>

            <a href="user?op=billList&pageOn=1&userName=<%=userName %>&goodsName=&cusName=&quOrders=">返回</a>
        </div>
    </div>
</section>
<footer class="footer">
</footer>
<script src="js/time.js"></script>

</body>
</html>