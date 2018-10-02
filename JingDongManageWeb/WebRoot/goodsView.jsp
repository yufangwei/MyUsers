<!DOCTYPE html>
<%@page import="java.util.ArrayList"%>
<%@page import="com.entity.GoodsInfo"%>
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
	PageUtil<GoodsInfo>pageGi = (PageUtil<GoodsInfo>) request.getAttribute("pageGi");
	String word = request.getAttribute("word").toString();
	ArrayList<GoodsInfo> list = pageGi.getList();
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
                    <li ><a href="user?op=billList&pageOn=1&userName=<%=userName %>&goodsName=&cusName=&quOrders=">账单管理</a></li>
                    <li><a href="user?op=notice&userName=<%=userName%>&pageOn=1&word=">公告管理</a></li>
                    <li><a href="user?op=cusMan&userName=<%=userName%>&pageOn=1&word=">用户管理</a></li>
                    <li id="active"><a href="user?op=goodsMan&userName=<%=userName%>&pageOn=1&word=">商品管理</a></li>
                    <li><a href="user?op=changePassword&userName=<%=userName %>">密码修改</a></li>
                    <li><a href="login.jsp">退出系统</a></li>
                </ul>
            </nav>
        </div>
        <div class="right">
            <div class="location">
                <strong>你现在所在的位置是:</strong>
                <span>商品管理页面</span>
            </div>
            <div class="search">
                <span>商品名：</span>
                <input type="text" name="cusName" id="cusName" placeholder="<%=word %>"/>
                <input type="button" value="查询" onclick="findGoods(<%=userName%>)"/>
                <a href="user?op=addGoods&userName=<%=userName%>&word=<%=word%>">添加商品</a>
            </div>
              <table class="providerTable" cellpadding="0" cellspacing="0">
                <tr class="firstTr">
                    <th width="5%">商品编码</th>
                    <th width="5%">商品类型</th>
                    <th width="10%">商品名称</th>
                    <th width="10%">商品价格</th>
                    <th width="10%">商品备注</th>
                    <th width="20%">商品图片</th>
                    <th width="5%">折扣</th>
                    <th width="5%">新品</th>
                    <th width="5%">推荐</th>
                    <th width="5%">状态</th>
                </tr>
                <%
                	if(list!=null){
                		for(GoodsInfo gi:list){
                		%>
                	<tr>
                	<td><%=gi.getGoodsId() %></td>
                	<td><%=gi.getTypeName() %></td>
                	<td><%=gi.getGoodsName() %></td>
                	<td><%=gi.getPrice() %></td>
                	<td><%=gi.getRemark() %></td>
                	<td><%=gi.getPhoto() %></td>
                	<td><%=gi.getDiscount() %></td>
                	<td><%=gi.getIsNew() %></td>
                	<td><%=gi.getIsRecommend() %></td>
                	<td><%=gi.getStatus() %></td>
                	<td> 
                	<a href="user?op=fff&userName=<%=userName%>&id=<%=gi.getGoodsId() %>&photo=<%=gi.getPhoto()%>"><img src="img/read.png" alt="查看" title="查看"/></a>
                	<a href="user?op=updateGoods&word=<%=word %>&userName=<%=userName %>&id=<%=gi.getGoodsId() %>"><img src="img/xiugai.png" alt="修改" title="修改"/></a>
                	<a href="user?op=delGoods&word=<%=word %>&userName=<%=userName %>&id=<%=gi.getGoodsId() %>"><img src="img/schu.png" alt="删除" title="删除"/></a>
                	</td>
                	</tr>
                <% 		}
                	}
                 %>
                 <tr>
                 <th width="5%"><a href="user?op=goodsMan&pageOn=1&userName=<%=userName %>&word=<%=word%>">首页</a> </th>
                 <th width="5%"><a href="user?op=goodsMan&pageOn=<%=pageGi.getPageUp() %>&userName=<%=userName %>&word=<%=word%>">上一页</th>
                 <th width="5%"><%=pageGi.getPageOn() %>/<%=pageGi.totalPage() %> </th>
                 <th width="5%"><a href="user?op=goodsMan&pageOn=<%=pageGi.getPageDown()%>&userName=<%=userName %>&word=<%=word%>">下一页</a> </th>
                 <th width="5%"><a href="user?op=goodsMan&pageOn=<%=pageGi.lastPage()%>&userName=<%=userName %>&word=<%=word%>" >尾页</a> </th>
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
                        <a href="user?op=delGoods" class="removeBill"><img src="img/schu.png" alt="删除" title="删除"/></a>
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
                        <a href="user"><img src="img/read.png" alt="查看" title="查看"/></a>
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