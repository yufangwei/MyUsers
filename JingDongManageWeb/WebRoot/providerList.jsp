<!DOCTYPE html>
<%@page import="java.util.ArrayList"%>
<%@page import="com.entity.Bulletin"%>
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
	PageUtil<Bulletin>noPage = (PageUtil<Bulletin>)request.getAttribute("noticePage");
	ArrayList<Bulletin>list = noPage.getList();
 %>
 
 <script type="text/javascript">
  	function findNotice(){
  		var fnNotice = document.getElementById("fnNotice").value;
  		location.href = "user?op=notice&userName=<%=userName%>&pageOn=1&word="+fnNotice;
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
            <span>公告管理页面</span>
        </div>
        <div class="search">
            <span>公告标题：</span>
            <input type="text" name="fnNotice" id="fnNotice" placeholder="<%=word %>"/>
            <input type="button" value="查询" onclick="findNotice()"/>
            <a href="user?op=addBbs&userName=<%=userName%>">添加公告</a>
        </div>
        <!--供应商操作表格-->
        <table class="providerTable" cellpadding="0" cellspacing="0">
          
            <tr class="firstTr">
                <th width="10%">全选<input type="checkbox" name="qureyAll" id="qureyAll" onclick="qurey()"/> </th>
                <th width="10%">公告编号</th>
                <th width="20%">公告标题</th>
                <th width="10%">发布者</th>
                <th width="20%">发布时间</th>
                <th width="30%">操作</th>
            </tr>
            <%
            	if(list!=null){
            		for(Bulletin be :list){
            %>			
            <tr>
            	<td><input type="checkbox" name="qureyOne" id="qureyOne" onclick="qureyO()" value="<%=be.getId()%>"/> </td>
                <td><%=be.getId() %></td>
                <td><%=be.getTitle() %></td>
                <td><%=be.getUsername() %></td>
                <td><%=be.getCreateTime() %></td>
                <td>
                    <a href="user?op=checkNotice&userName=<%=userName%>&noticeId=<%=be.getId()%>"><img src="img/read.png" alt="查看" title="查看"/></a>
                    <a href="user?op=updateNotice&userName=<%=userName%>&noticeId=<%=be.getId()%>&title=<%=be.getTitle() %>&author=<%=be.getUsername()%>&createTime=<%=be.getCreateTime()%>">
                    <img src="img/xiugai.png" alt="修改" title="修改"/></a>
                    <%-- <a href="user?op=delNotice&userName=<%=userName%>&noticeId=<%be.getId()%>"><img src="img/schu.png" alt="删除" title="删除"/></a>  --%>
                    <%-- <a href="user?op=delNotice&userName=<%=userName%>&noticeId=<%=be.getId()%>"><img src="img/schu.png" alt="删除" title="删除"/></a> --%>
                </td>
            </tr>
           			
            <%		}
            	}
             %>
               <tr>
                 <th ><a href="user?op=notice&pageOn=1&userName=<%=userName%>&word=<%=word%>">首页</a> </th>
                 <th ><a href="user?op=notice&pageOn=<%=noPage.getPageUp() %>&userName=<%=userName %>&word=<%=word%>">上一页</th>
                 <th ><%=noPage.getPageOn() %>/<%=noPage.totalPage() %> </th>
                 <th ><a href="user?op=notice&pageOn=<%=noPage.getPageDown()%>&userName=<%=userName %>&word=<%=word%>">下一页</a> </th>
                 <th ><a href="user?op=notice&pageOn=<%=noPage.lastPage()%>&userName=<%=userName %>&word=<%=word%>" >尾页</a> </th>
                 <th> <input type="button" value="多选删除" onclick="delMore()"/></th>
                 </tr>
        </table>

    </div>
</section>

<!--点击删除按钮后弹出的页面-->
<div class="zhezhao"></div>
<div class="remove" id="removeProv">
   <div class="removerChid">
       <h2>提示</h2>
       <div class="removeMain" >
           <p>你确定要删除该供应商吗？</p>
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
  function qurey(){
  var qureyAll =  document.getElementById("qureyAll");
  var qureyOne = document.getElementsByName("qureyOne");
  	for(var i= 0;i<qureyOne.length;i++){
  		qureyOne[i].checked = qureyAll.checked;
  	}
  }
  
  function qureyO(){
  	var qureyOne = document.getElementsByName("qureyOne");
  	var qureyAll = document.getElementById("qureyAll");
  	for(var i=0;i<qureyOne.length;i++){
  		if(!qureyOne[i].checked){
  			qureyAll.checked = false;
  		}
  	}
  	var bo = true;
  	for(var i=0;i<qureyOne.length;i++){
  		if(!qureyOne[i].checked){
  			bo = false;
  			break;
  		}	
  	}
  	if(bo){
  	qureyAll.checked = bo;
  	}
  }
  
  function delMore(){
  	var qureyOne = document.getElementsByName("qureyOne");
  	var str = "";
  	 alert(1);
  	for(var i =0;i<qureyOne.length;i++){
  	    if(qureyOne[i].checked){
  	       str += qureyOne[i].value+","
  	    }
  	}
  	
  	location.href = "user?op=delBbs&userName=<%=userName%>&pageOn=1&str="+str+"&word="+"";
  }
</script>
</body>
</html>