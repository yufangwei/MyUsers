<!DOCTYPE html>
<%@page import="com.entity.CusAddress"%>
<%@page import="com.entity.CusInfo"%>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>超市账单管理系统</title>
    <link rel="stylesheet" href="css/public.css"/>
    <link rel="stylesheet" href="css/style.css"/>
</head>
<%
	String userName = request.getAttribute("userName").toString();
	String word = request.getAttribute("word").toString();
	CusInfo ci = (CusInfo)request.getAttribute("ci");
	CusAddress ca = (CusAddress)request.getAttribute("ca");
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
            <span>用户管理页面 >> 用户修改页面</span>
        </div>
        <div class="providerAdd">
            <form action="user?op=updateCusTwo&userName=<%=userName%>&word=<%=word%>&id=<%=ci.getId() %>" method="post">
                <!--div的class 为error是验证错误，ok是验证成功-->
                 <div>
                    <label for="userName">用户编号：</label>
                    <input type="text" name="id" id="id"  value="<%=ci.getId()%>" disabled="disabled"/>
                   <span >*</span>
                </div>
                <div>
                    <label for="userName">用户名称：</label>
                    <input type="text" name="cusName" id="cusName" placeholder="<%=ci.getName()%>"/>
                   <span >*</span>
                </div>

               <div>
                    <label for="userName">用户密码：</label>
                    <input type="text" name="cusPwd" id="cusPwd" placeholder="<%=ci.getPwd()%>"/>
                   <span >*</span>
                </div>
                <div>
                    <label for="data">用户邮箱：</label>
                    <input type="text" name="email" id="email" placeholder="<%=ci.getEmail()%>"/>
                    <span >*</span>
                </div>
                <div>
                    <label for="userphone">收货人名称：</label>
                    <input type="text" name="shouHuoName" id="shouHuoName" placeholder="<%=ca.getName()%>"/>
                    <span >*</span>
                </div>
                <div>
                    <label for="userAddress">手机电话：</label>
                    <input type="text" name="telPhone" id="telPhone" placeholder="<%=ca.getPhone()%>"/>
                </div>
                 <div>
                    <label for="userAddress">移动电话：</label>
                    <input type="text" name="movePhone" id="movePhone" placeholder="<%=ca.getTelPhone()%>"/>
                </div>
                 <div>
                    <label for="userAddress">收货地址：</label>
                    <input type="text" name="address" id="address" placeholder="<%=ca.getAddress()%>"/>
                </div>
                <div class="providerAddBtn">
                    <!--<a href="#">保存</a>-->
                    <!--<a href="userList.html">返回</a>-->
                    <input type="submit" value="保存"/>
                    <input type="button" value="返回" onclick="history.back(-1)"/>
                </div>
            </form>
        </div>

    </div>
</section>
<footer class="footer">
</footer>
<script src="js/time.js"></script>

</body>
</html>