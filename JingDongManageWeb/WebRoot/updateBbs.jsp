<!DOCTYPE html>
<%@page import="com.entity.Bulletin"%>
<%@page import="com.entity.CusAddress"%>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>超市账单管理系统</title>
    <link rel="stylesheet" href="css/public.css"/>
    <link rel="stylesheet" href="css/style.css"/>
</head>

<%
	String userName = request.getAttribute("userName").toString();
	Bulletin bt = (Bulletin)request.getAttribute("bt");

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
                <li id="active"><a href="user?op=billList&pageOn=1&userName=<%=userName %>&goodsName=&cusName=&quOrders=">账单管理</a></li>
                <li><a href="user?op=notice&userName=<%=userName%>&pageOn=1&word=">公告管理</a></li>
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
            <span>公告管理页面 >> 公告修改页面</span>
        </div>
        <div class="providerAdd">
            <form action="user?op=updateOneBbs&userName=<%=userName%>&BbsId=<%=bt.getId()%>" method="post" >
                <!--div的class 为error是验证错误，ok是验证成功-->
                <div class="">
                    <label for="providerId">公告编码：</label>
                    <input type="text" name="bbsId" id="bbsId" value="<%=bt.getId()%>" disabled="disabled"/>
                    <span>*</span>
                </div>
                <div>
                    <label for="providerName">新的标题：</label>
                    <input type="text" name="newTitle" id="newTitle" placeholder="<%=bt.getTitle()%>"/>
                    <span >*</span>
                </div>
                <div>
                    <label for="people">新的内容：</label>
                    <input type="text" name="newContext" id="newContext" style="width:200px;height:100px"/>
                    <span>*</span>

                </div>    
                 <div>
                    <label for="people">新的作者：</label>
                    <input type="text" name="newAuthor" id="newAuthor" placeholder="<%=bt.getUsername() %>"/>
                    <span>*</span>

                </div> 
               <div class="providerAddBtn">
                    <!--<a href="#">保存</a>-->
                    <!--<a href="billList.html">返回</a>-->
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