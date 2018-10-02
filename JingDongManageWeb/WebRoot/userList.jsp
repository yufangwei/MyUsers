<!DOCTYPE html>
<%@page import="java.util.ArrayList"%>
<%@page import="com.entity.CusInfo"%>
<%@page import="com.util.PageUtil"%>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>超市账单管理系统</title>
    <link rel="stylesheet" href="css/public.css"/>
    <link rel="stylesheet" href="css/style.css"/>
</head>
<%
	String word = request.getAttribute("word").toString();
	String userName = request.getAttribute("userName").toString();
	PageUtil<CusInfo>pageUt = (PageUtil<CusInfo>)request.getAttribute("pageUt");
	ArrayList<CusInfo> list = pageUt.getList();
 %>
<body>
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
                    <li><a href="user?op=notice&userName=<%=userName%>&pageOn=1&word=">公告管理</a></li>
                    <li  id="active"><a href="user?op=cusMan&userName=<%=userName%>&pageOn=1&word=">用户管理</a></li>
                     <li><a href="user?op=goodsMan&userName=<%=userName%>&pageOn=1&word=">商品管理</a></li>
                    <li><a href="user?op=changePassword&userName=<%=userName %>">密码修改</a></li>
                    <li><a href="login.jsp">退出系统</a></li>
                </ul>
            </nav>
        </div>
        <div class="right">
            <div class="location">
                <strong>你现在所在的位置是:</strong>
                <span>用户管理页面</span>
            </div>
            <div class="search">
                <span>用户名：</span>
                <input type="text" name="cusName" id="cusName" placeholder="<%=word %>"/>
                <input type="button" value="查询" onclick="findCus(<%=userName%>)"/>
                <a href="user?op=addCus&userName=<%=userName%>&word=<%=word%>">添加用户</a>
            </div>
            <!--用户-->
            <table class="providerTable" cellpadding="0" cellspacing="0">
                <tr class="firstTr">
                    <th width="10%">用户编码</th>
                    <th width="20%">用户名称</th>
                    <th width="10%">用户密码</th>
                    <th width="10%">用户邮箱</th>
                    <th width="20%">注册时间</th>
                    <th width="30%">操作</th>
                </tr>
                <%
                	if(list!=null){
                		for(CusInfo ci:list){
                		%>
                	<tr>
                	<td><%=ci.getId() %></td>
                	<td><%=ci.getName() %></td>
                	<td><%=ci.getPwd() %></td>
                	<td><%=ci.getEmail() %></td>
                	<td><%=ci.getRegisterTime() %></td>
                	<td> 
                	<a href="user?op=checkCus&userName=<%=userName%>&id=<%=ci.getId() %>&word=<%=word %>"><img src="img/read.png" alt="查看" title="查看"/></a>
                	<a href="user?op=updateCus&word=<%=word %>&userName=<%=userName %>&id=<%=ci.getId() %>"><img src="img/xiugai.png" alt="修改" title="修改"/></a>
                	<a href="user?op=delCus&word=<%=word %>&userName=<%=userName %>&id=<%=ci.getId() %>"><img src="img/schu.png" alt="删除" title="删除"/></a>
                	</td>
                	</tr>
                <% 		}
                	}
                 %>
             <%--   <%
                	for(CusInfo ci:list)
                %>		
               <%  		{
                %>
                 <tr>
                    <td><%=ci.getId() %></td>
                    <td><%=ci.getName() %></td>
                    <td><%=ci.getPwd() %></td>
                    <td><%=ci.getEmail() %></td>
                    <td><%=ci.getRegisterTime() %></td>
                    <td>
                        <a href="user?op=checkCus&userName=<%=userName%>&id=<%=ci.getId()%>&word=<%=word%>"><img src="img/read.png" alt="查看" title="查看"/></a>
                        <a href="user?op=updateCus&word=<%word %>&userName=<%=userName %>&id=<%=ci.getId()%>"><img src="img/xiugai.png" alt="修改" title="修改"/></a>
                        <a href="#" class="removeUser"><img src="img/schu.png" alt="删除" title="删除"/></a>
                    </td>
                </tr>
                			<%}%>  --%>
                 <tr>
                 <th width="5%"><a href="user?op=cusMan&pageOn=1&userName=<%=userName %>&word=<%=word%>">首页</a> </th>
                 <th width="5%"><a href="user?op=cusMan&pageOn=<%=pageUt.getPageUp() %>&userName=<%=userName %>&word=<%=word%>">上一页 </a> </th>
                 <th width="5%"><%=pageUt.getPageOn() %>/<%=pageUt.totalPage() %> </th>
                 <th width="5%"><a href="user?op=cusMan&pageOn=<%=pageUt.getPageDown()%>&userName=<%=userName %>&word=<%=word%>">下一页</a> </th>
                 <th width="5%"><a href="user?op=cusMan&pageOn=<%=pageUt.lastPage()%>&userName=<%=userName %>&word=<%=word%>" >尾页</a> </th> 
                 </tr> 
            </table>

        </div>
    </section>

<!--点击删除按钮后弹出的页面-->
<div class="zhezhao"></div>
<div class="remove" id="removeUse">
    <div class="removerChid">
        <h2>提示</h2>
        <div class="removeMain">
            <p>你确定要删除该用户吗？</p>
            <a href="#" id="yes">确定</a>
            <a href="#" id="no">取消</a>
        </div>
    </div>
</div>

    <footer class="footer">
    </footer>

<script src="js/jquery.js"></script>
<script src="js/js.js"></script>
<script src="js/time.js"></script>
<script type="text/javascript">
	function findCus(id){
		var cusName = document.getElementById("cusName").value;
		location.href="user?op=cusMan&pageOn=1&userName="+id+"&word="+cusName;
	}
</script>
</body>
</html>