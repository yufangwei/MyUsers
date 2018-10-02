<!DOCTYPE html>
<%@page import="java.util.ArrayList"%>
<%@page import="com.entity.Orders"%>
<%@page import="com.util.PageUtil"%>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>超市账单管理系统</title>
    <link rel="stylesheet" href="css/public.css"/>
    <link rel="stylesheet" href="css/style.css"/>
</head>
<%
	String userName = request.getAttribute("userName").toString();
	PageUtil<Orders>pageOd = (PageUtil<Orders>)request.getAttribute("ypage");
		String goodsName = request.getAttribute("goodsName").toString();
		String cusName = request.getAttribute("cusName").toString();
		String quOrders = request.getAttribute("quOrders").toString();
	ArrayList<Orders> list = new ArrayList<Orders>();
	if(pageOd!=null){
		list = pageOd.getList();
	}
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
                <span>账单管理页面</span>
            </div>
            <div class="search">
                <span>商品名称：</span>
                <input type="text" placeholder="请输入商品的名称" name="goodsName" id="goodsName" value="<%=goodsName%>"/>
                
                <span>客户编号</span>
                <input type="text" placeholder="请输入客户编号" name="cusName" id="cusName" value="<%=cusName%>" />

                <span>是否确认订单：</span>
                <select name="getQuOrders" id="getQuOrders" value="<%=quOrders%>">
                    <option name="quOrders" id="quOrders" >--请选择--</option>
                    <option name="quOrders" id="quOrders" value="已确认">已确认</option>
                    <option name="quOrders" id="quOrders" value="未确认">未确认</option>
                </select>
                <input type="button" value="查询" onclick="seaGoods()"/><a href="user?op=addOrdersOne&userName=<%=userName%>">添加订单</a>
                
            </div>
            <!--账单表格 样式和供应商公用-->
            <table class="providerTable" cellpadding="0" cellspacing="0">
                <tr class="firstTr">
                    <th width="10%">账单编码</th>
                    <th width="5%">客户id</th>
                    <th width="5%">商品id</th>
                    <th width="10%">商品名称</th>
                    <th width="10%">商品价格</th>
                    <th width="10%">商品数量</th>
                    <th width="20%">创建时间</th>
                    <th width="10%">是否付款</th>
                    <th width="20%">操作</th>
                </tr>
                <%
                	if(list!=null){
                		for(Orders od :list){
                	%>
                	<tr>
                	<th><%=od.getOrderId() %> </th>
                	<th><%=od.getCusId() %> </th>
                	<th><%=od.getGoodsId() %> </th>
                	<th><%=od.getGoodsName() %> </th>
                	<th><%=od.getPrice() %> </th>
                	<th><%=od.getQuantity() %> </th>
                	<th><%=od.getOrderTime() %> </th>
                	<th><%=od.getStatus()%> </th>
                	<th><a href="user?op=checkOrders&goodsId=<%=od.getGoodsId()%>&orderId=<%=od.getOrderId() %>&userName=<%=userName %>"><img src="img/read.png" alt="查看" title="查看"/></a>
                	<a href="user?op=updateOrders&cusId=<%=od.getCusId()%>&goodsId=<%=od.getGoodsId()%>&ordersId=<%=od.getOrderId() %>&userName=<%=userName %>"><img src="img/xiugai.png" alt="修改" title="修改"/></a> 
                	&nbsp;&nbsp;&nbsp;&nbsp;<a href="user?op=delOrders&goodsId=<%=od.getGoodsId()%>&ordersId=<%=od.getOrderId() %>&userName=<%=userName %>"><img src="img/schu.png" alt="删除" title="删除"/></a> </th>
                	</tr>		
                	<%	
                		}
                	}
                 %>
                 <tr>
                 <th width="5%"><a href="user?op=billList&pageOn=1&userName=<%=userName %>&goodsName=<%=goodsName %>
                 &cusName=<%=cusName %>&quOrders=<%=quOrders %>">首页</a> </th>
                 <th width="5%"><a href="user?op=billList&pageOn=<%=pageOd.getPageUp() %>&userName=<%=userName %>&goodsName=<%=goodsName %>
                 &cusName=<%=cusName %>&quOrders=<%=quOrders %>">上一页</th>
                 <th width="5%"><%=pageOd.getPageOn() %>/<%=pageOd.totalPage() %> </th>
                 <th width="5%"><a href="user?op=billList&pageOn=<%=pageOd.getPageDown()%>&userName=<%=userName %>&goodsName=<%=goodsName %>
                 &cusName=<%=cusName %>&quOrders=<%=quOrders %>">下一页</a> </th>
                 <th width="5%"><a href="user?op=billList&pageOn=<%=pageOd.lastPage()%>&userName=<%=userName %>&goodsName=<%=goodsName %>
                 &cusName=<%=cusName %>&quOrders=<%=quOrders %>" >尾页</a> </th>
                 </tr>
               <!--  <tr>
                    <td>213</td>
                    <td>123</td>
                    <td>北京市粮油总公司</td>
                    <td>22.00</td>
                    <td>未付款</td>
                    <td>2015-11-12</td>
                    <td>
                        <a href="billView.jsp"><img src="img/read.png" alt="查看" title="查看"/></a>
                        <a href="billUpdate.jsp"><img src="img/xiugai.png" alt="修改" title="修改"/></a>
                        <a href="#" class="removeBill"><img src="img/schu.png" alt="删除" title="删除"/></a>
                    </td>
                </tr>
                <tr>
                    <td>PRO-CODE—001</td>
                    <td>测试供应商001</td>
                    <td>邯郸市五得利面粉厂</td>
                    <td>15918230478</td>
                    <td>15918230478</td>
                    <td>2015-11-12</td>
                    <td>
                        <a href="providerView.jsp"><img src="img/read.png" alt="查看" title="查看"/></a>
                        <a href="providerUpdate.jsp"><img src="img/xiugai.png" alt="修改" title="修改"/></a>
                        <a href="#" class="removeBill"><img src="img/schu.png" alt="删除" title="删除"/></a>
                    </td>
                </tr> -->
            </table>
        </div>
    </section>

<!--点击删除按钮后弹出的页面-->
<div class="zhezhao"></div>
<div class="remove" id="removeBi">
    <div class="removerChid">
        <h2>提示</h2>
        <div class="removeMain">
            <p>你确定要删除该订单吗？</p>
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
	function seaGoods(){
		var goodsName = document.getElementById("goodsName").value;
		var cusName = document.getElementById("cusName").value;
		var getQuOrders = document.getElementById("getQuOrders").value;
		location.href="user?op=billList&pageOn=1&userName=<%=userName %>&goodsName="+goodsName+"&cusName="+cusName+"&quOrders="+getQuOrders;
	}
</script>

</body>
</html> 