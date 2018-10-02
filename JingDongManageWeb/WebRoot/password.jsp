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
	String userPwd = request.getAttribute("userPwd").toString();
%>
  <script type="text/javascript">
  	var XMLHttp = null;
  	//验证用户名
  	function checkPwd(){
  	//验证浏览器
  	
  		if(window.XMLHttpRequest){//非IE
  			XMLHttp = new XMLHttpRequest();
  		}else if(window.ActiveXObject){
  			try{
  				XMLHttp = new ActiveXObject("Msxml2.XMLHTTP");
  			}catch(e){
  				XMLHttp = new ActiveXObject("Microsoft.XMLHTTP");
  			}  	
  		}
  		var newPassword = document.getElementById("newPassword").value;
  	    var reNewPassword = document.getElementById("reNewPassword").value;
  		var url = "user?op=ajaxChangePwd&newPassword="+newPassword+"&reNewPassword="+reNewPassword;
  		//创建请求的打开方法
  		XMLHttp.open("get",url,true);
  		XMLHttp.send(null);
  		//判断请求是否成功，回调函数
  		XMLHttp.onreadystatechange=function(){
  			if(XMLHttp.readyState==4&&XMLHttp.status==200){
  				var op = XMLHttp.responseText;
  				var s = document.getElementById("s");
  				if(op=="重复输入密码错误"){
  					s.innerHTML = "<font color='red'>新、旧密码不一致,并且密码不小于6位！</font>";
  				}else{
  					s.innerHTML = "<font color='green'>√</font>"
  				}
  			}
  		}
  	}
  </script>
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
                    <li ><a href="user?op=billList&pageOn=1&userName=<%=userName %>&goodsName=&cusName=&quOrders=">账单管理</a></li>
                    <li><a href="user?op=notice&userName=<%=userName%>&pageOn=1&word=">公告管理</a></li>
                    <li><a href="user?op=cusMan&userName=<%=userName%>&pageOn=1&word=">用户管理</a></li>
                    <li><a href="user?op=goodsMan&userName=<%=userName%>&pageOn=1&word=">商品管理</a></li>
                    <li id="active"><a href="user?op=changePassword&userName=<%=userName %>">密码修改</a></li>
                    <li><a href="login.jsp">退出系统</a></li>
                </ul>
            </nav>
        </div>
        <div class="right">
            <div class="location">
                <strong>你现在所在的位置是:</strong>
                <span>密码修改页面</span>
            </div>
            <div class="providerAdd">
                <form action="user?op=changePwdTwo&userName=<%=userName %>&userPwd=<%=userPwd%>" method="post">
                    <!--div的class 为error是验证错误，ok是验证成功-->
                    <div class="">
                        <label for="oldPassword">旧密码：</label>
                        <input type="password" name="oldPassword" id="oldPassword" required/>
                        <span>*请输入原密码</span>
                    </div>
                    <div>
                        <label for="newPassword">新密码：</label>
                        <input type="password" name="newPassword" id="newPassword" required/>
                        <span >*请输入新密码</span>
                    </div>
                    <div>
                        <label for="reNewPassword">确认新密码：</label>
                        <input type="password" name="reNewPassword" id="reNewPassword" onblur="checkPwd()" required />
                        <span id="s"></span><br><br>
                    </div>
                    <div class="providerAddBtn">
                        <!--<a href="#">保存</a>-->
                        <input type="submit" value="保存" onclick="history.back(-1)"/>
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