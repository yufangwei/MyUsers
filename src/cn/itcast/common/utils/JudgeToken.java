package cn.itcast.common.utils;

import javax.servlet.http.HttpServletRequest;

public class JudgeToken {

	 /**
	24          * 判断客户端提交上来的令牌和服务器端生成的令牌是否一致
	25          * @param request
	26          * @return 
	27          *         true 用户重复提交了表单 
	28          *         false 用户没有重复提交表单
	29          */
	         public boolean isRepeatSubmit(HttpServletRequest request) {
	             String client_token = request.getParameter("token");
	             System.out.println(client_token);
	             //1、如果用户提交的表单数据中没有token，则用户是重复提交了表单
	             if(client_token==null){
	                 return true;
	             }
	             //取出存储在Session中的token
	             String server_token = (String) request.getSession().getAttribute("token");
	             //2、如果当前用户的Session中不存在Token(令牌)，则用户是重复提交了表单
	             if(server_token==null){
	                 return true;
	             }
	             //3、存储在Session中的Token(令牌)与表单提交的Token(令牌)不同，则用户是重复提交了表单
	             if(!client_token.equals(server_token)){
	                 return true;
	             }
	             
	             return false;
	         }

}
