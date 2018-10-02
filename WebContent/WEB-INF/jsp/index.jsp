<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html>
<head>
<title>MOBAN</title>
<link href="<%=basePath%>css/style.css" rel='stylesheet' type='text/css' />
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="keywords" content="App Loction Form,Login Forms,Sign up Forms,Registration Forms,News latter Forms,Elements"./>
<script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
</script>
<!--webfonts-->
<!-- link href='https://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,400,300,600,700,800' rel='stylesheet' type='text/css'> -->
<!--//webfonts-->
</head>
<body>
	<h1>App Location Form</h1>
		<div class="app-location">
			<h2>Welcome to CRM</h2>
			<div class="line"><span></span></div>
			<div class="location"><img src="<%=basePath%>images/location.png" class="img-responsive" alt="" /></div>
			<form action="${pageContext.request.contextPath}/customer/verifyUser.action" method="post" onsubmit="return dosubmit()">
				<input type="text" name="user_name" class="text" value="Password" onFocus="this.value = '';" onBlur="if (this.value == '') {this.value = 'Password';}" >
				<!-- 使用隐藏域储存生成的token -->
				<input type="password" name="user_password" value="Password" onFocus="this.value = '';" onBlur="if (this.value == '') {this.value = 'Password';}">
				<input type="hidden" name="token" value="${token}" />
				<div class="submit" id="divSubmit"><input type="submit" value="Sign in" id="submit" ></div>
				<div class="clear"></div>
				<div class="new">
					<h3><a href="#">忘记密码 ?</a></h3>
					<div class="clear"></div>
				</div>
			</form>
		</div>
		<script type="text/javascript">
		function dosubmit(){
			    //获取表单提交按钮
			     var btnSubmit = document.getElementById("submit");
			     //将表单提交按钮设置为不可用，这样就可以避免用户再次点击提交按钮
			    btnSubmit.disabled= "disabled";
			    //返回true让表单可以正常提交 
			    document.getElementById("divSubmit").style.background="black";
			     return true;
		}
		</script>
</body>
</html>