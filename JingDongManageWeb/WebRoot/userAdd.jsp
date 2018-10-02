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
	String word = request.getAttribute("word").toString();
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
            <span>用户管理页面 >> 用户添加页面</span>
        </div>
        <div class="providerAdd">
            <form action="user?op=addCusTwo&userName=<%=userName%>&word=<%=word%>" method="post">
                <!--div的class 为error是验证错误，ok是验证成功-->
                <div>
                    <label for="userName">用户名称：</label>
                    <input type="text" name="name" id="name" onblur="checkName()"/>
                     <span  id="s"></span>
                </div>
                <div>
                    <label for="userpassword">用户密码：</label>
                    <input type="password" name="pwd" id="pwd" onblur="checkPwd()"/>
                     <span  id="s1"></span>

                </div>
                <div>
                    <label for="userRemi">确认密码：</label>
                    <input type="password" name="rePwd" id="rePwd" onblur="checkPwd1()"/>
                    <span  id="s2"></span>
                </div>
                <div>
                    <label for="data">用户邮箱：</label>
                    <input type="text" name="email" id="email" onblur="checkEmail()"/>
                    <span  id="s3"></span>
                    <span >*</span>
                </div>
                <div>
                    <label for="userphone">用户电话：</label>
                    <input type="text" name="telPhone" id="telPhone" onblur="checkTelPhone()"/>
                    <span  id="s4"></span>
                    <span >*</span>
                </div>
                <div>
                    <label for="userphone">移动电话：</label>
                    <input type="text" name="movePhone" id="movePhone" onblur="checkMovePhone()"/>
                    <span  id="s5"></span>
                    <span >*</span>
                </div>
                <div>
                    <label for="userAddress">用户地址：</label>
                    <input type="text" name="address" id="address" onblur="checkAddress()"/>
                    <span  id="s6"></span>
                </div>
                <div>
                    <label >用户类别：</label>
                    <input type="radio" name="userlei" value="user"/>管理员
                    <input type="radio" name="userlei" value="cus"/>普通用户
					<span  id="s7"></span>
                </div>
                <div class="providerAddBtn">
                    <!--<a href="#">保存</a>-->
                    <!--<a href="userList.html">返回</a>-->
                    <input type="submit" value="保存" />
                    <input type="button" value="返回" onclick="history.back(-1)"/>
                </div>
            </form>
        </div>

    </div>
</section>
<footer class="footer">
</footer>
<script src="js/time.js"></script>
<script type="text/javascript">
  	var XMLHttp = null;
  	//验证用户名
  	function checkName(){
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
 
  		var name = document.getElementById("name").value;
  		var url = "user?op=yzname&name="+name;
  		//创建请求的打开方法
  		 
  		XMLHttp.open("get",url,true);
  		XMLHttp.send(null);
  		
  		//判断请求是否成功，回调函数
  		XMLHttp.onreadystatechange=function(){
  			if(XMLHttp.readyState==4&&XMLHttp.status==200){
  				var op = XMLHttp.responseText;
  				var s = document.getElementById("s");
  				if(op=="已存在"){
  					s.innerHTML = "<font color='red'>x 用户名已存在！</font>";
  				}else if(op=="不能为空"){
  					s.innerHTML = "<font color='red'>x 用户名不能为空！</font>";
  				}
  				else{
  					s.innerHTML = "<font color='green'>√ 用户名可以使用!</font>"
  				}
  			}
  		}
  	}
  	
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
 
  		var pwd = document.getElementById("pwd").value;
  		var url = "user?op=yzpwd&pwd="+pwd;
  		//创建请求的打开方法
  		 
  		XMLHttp.open("get",url,true);
  		XMLHttp.send(null);
  		
  		//判断请求是否成功，回调函数
  		XMLHttp.onreadystatechange=function(){
  			if(XMLHttp.readyState==4&&XMLHttp.status==200){
  				var op = XMLHttp.responseText;
  				var p = document.getElementById("s1");
  				if(op=="字母开头，允许5-16字节，允许字母数字下划线"){
  					p.innerHTML = "<font color='red'>x 字母开头，允许5-16字节，允许字母数字下划线！</font>";
  				}
  				else{
  					p.innerHTML = "<font color='green'>√ 密码可以使用!</font>"
  				}
  			}
  		}	
	
	}
	
	/* 确认密码的方法 */
	function checkPwd1(){
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
 
  		var pwd = document.getElementById("pwd").value;
  		var rePwd = document.getElementById("rePwd").value;
  		url = "user?op=reyzpwd&pwd="+pwd+"&rePwd="+rePwd;
  		//创建请求的打开方法
  		 
  		XMLHttp.open("get",url,true);
  		XMLHttp.send(null);
  		
  		//判断请求是否成功，回调函数
  		XMLHttp.onreadystatechange=function(){
  			if(XMLHttp.readyState==4&&XMLHttp.status==200){
  				var op = XMLHttp.responseText;
  				var p = document.getElementById("s2");
  				if(op=="重复输入密码错误"){
  					p.innerHTML = "<font color='red'>x 重复输入密码错误！</font>";
  				}
  				else{
  					p.innerHTML = "<font color='green'>√</font>"
  				}
  			}
  		}	
	}
	
	/* 验证邮箱的方法 */
    function checkEmail(){
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
 
  		var email = document.getElementById("email").value;
  		url = "user?op=yzEmail&email="+email;
  		//创建请求的打开方法
  		 
  		XMLHttp.open("get",url,true);
  		XMLHttp.send(null);
  		
  		//判断请求是否成功，回调函数
  		XMLHttp.onreadystatechange=function(){
  			if(XMLHttp.readyState==4&&XMLHttp.status==200){
  				var op = XMLHttp.responseText;
  				var p = document.getElementById("s3");
  				if(op=="邮箱格式不正确"){
  					p.innerHTML = "<font color='red'>x 邮箱格式不正确！</font>";
  				}
  				else{
  					p.innerHTML = "<font color='green'>√</font>"
  				}
  			}
  		}	
    }
    //验证电话的ajax
    function checkTelPhone(){
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
 
  		var telPhone = document.getElementById("telPhone").value;
  		url = "user?op=yzTelPhone&telPhone="+telPhone;
  		//创建请求的打开方法
  		 
  		XMLHttp.open("get",url,true);
  		XMLHttp.send(null);
  		
  		//判断请求是否成功，回调函数
  		XMLHttp.onreadystatechange=function(){
  			if(XMLHttp.readyState==4&&XMLHttp.status==200){
  				var op = XMLHttp.responseText;
  				var p = document.getElementById("s4");
  				if(op=="手机格式不正确"){
  					p.innerHTML = "<font color='red'>x 手机格式不正确！</font>";
  				}
  				else{
  					p.innerHTML = "<font color='green'>√</font>"
  				}
  			}
  		}	
    }
    
    //验证移动电话的ajax
    function checkMovePhone(){
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
 
  		var movePhone = document.getElementById("movePhone").value;
  		url = "user?op=yzMovePhone&movePhone="+movePhone;
  		//创建请求的打开方法
  		 
  		XMLHttp.open("get",url,true);
  		XMLHttp.send(null);
  		
  		//判断请求是否成功，回调函数
  		XMLHttp.onreadystatechange=function(){
  			if(XMLHttp.readyState==4&&XMLHttp.status==200){
  				var op = XMLHttp.responseText;
  				var p = document.getElementById("s5");
  				if(op=="移动电话格式不正确"){
  					p.innerHTML = "<font color='red'>x 移动电话格式不正确！</font>";
  				}
  				else{
  					p.innerHTML = "<font color='green'>√</font>"
  				}
  			}
  		}	
    }
    
     //住址的ajax
    function checkAddress(){
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
 
  		var address = document.getElementById("address").value;
  		url = "user?op=yzAddress&address="+address;
  		//创建请求的打开方法
  		 
  		XMLHttp.open("get",url,true);
  		XMLHttp.send(null);
  		
  		//判断请求是否成功，回调函数
  		XMLHttp.onreadystatechange=function(){
  			if(XMLHttp.readyState==4&&XMLHttp.status==200){
  				var op = XMLHttp.responseText;
  				var p = document.getElementById("s6");
  				if(op=="住址格式不正确"){
  					p.innerHTML = "<font color='red'>x 住址格式不正确！</font>";
  				}
  				else{
  					p.innerHTML = "<font color='green'>√</font>"
  				}
  			}
  		}	
    }
</script>

</body>
</html>