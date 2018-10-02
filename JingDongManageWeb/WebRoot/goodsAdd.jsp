<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>超市账单管理系统</title>
    <link rel="stylesheet" href="css/public.css"/>
    <link rel="stylesheet" href="css/style.css"/>
</head>
<%
	String userName = request.getAttribute("userName").toString();
	
 %>
<body>
<!--头部-->
<header class="publicHeader">
    <h1>超市账单管理系统</h1>

    <div class="publicHeaderR">
        <p><span>下午好！</span><span style="color: #fff21b"> <%=userName %></span> , 欢迎你！</p>
        <a href="login.html">退出</a>
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
                <li><a href="user?op=notice&userName=<%=userName%>&pageOn=1&word=">供应商管理</a></li>
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
            <span>商品管理页面 >> 商品添加页面</span>
        </div>
        <div class="providerAdd">
        
        <!-- enctype="multipart/form-data" -->
            <form action="user?op=addGoodsTwo&userName=<%=userName %>&word=" method="post" >
                <!--div的class 为error是验证错误，ok是验证成功-->
               <!--  <div class="">
                    <label for="billId">商品类型：</label>
                    <input type="text" name="goodsType" id="goodsType" required/>
                    <span>*请输入商品类型(饮食、家电、汽车、手机、床上用品)</span>
                </div> -->
                <div>
                    <label >商品类型：</label>
                    <input type="radio" name="goodsType" id="goodsType" value="饮食"  checked/>饮食
                    <input type="radio" name="goodsType" id="goodsType" value="汽车" />汽车
                    <input type="radio" name="goodsType" id="goodsType" value="家电" />家电
                    <input type="radio" name="goodsType" id="goodsType" value="手机" />手机
                    <input type="radio" name="goodsType" id="goodsType" value="床上用品" />床上用品
                    <span>*是或否：</span>
                </div>
                <div>
                    <label for="billName">商品名称：</label>
                    <input type="text" name="goodsName" id="goodsName" required/>
                    <span >*请输入商品名称</span>
                </div>
                <div>
                    <label for="billCom">商品价格：</label>
                    <input type="text" name="goodsPrice" id="goodsPrice" required/>
                    <span>*商品价格</span>

                </div>
                <div>
                    <label for="billNum">商品折扣：</label>
                    <input type="text" name="discount" id="discount" required/>
                    <span>*输入折扣</span>
                </div>
                <div>
                    <label >是否新品：</label>
                    <input type="radio" name="isNew" value="isNew" checked />是
                    <input type="radio" name="isNew" value="isNew" />否
                    <span>*是或否：</span>
                </div>
                <div>
                    <label >是否推荐：</label>
                     <input type="radio" name="isRecommend" id="isRecommend" value="是" checked/>是
                    <input type="radio" name="isRecommend" id="isRecommend" value="否" />否
                    <span>*是或否</span>
                </div>
                 <div>
                    <label >是否上架：</label>
                     <input type="radio" name="status" id="status" value="是"  checked/>是
                    <input type="radio" name="status" id="status" value="否" />否
                    <span>*是或否</span>
                </div>
                 <div>
                    <label for="billNum">商品备注：</label>
                    <input type="text" name="remark" id="remark" required/>
                    <span>*输入备注信息</span>
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