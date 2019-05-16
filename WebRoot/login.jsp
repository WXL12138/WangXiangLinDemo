<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String msg = (String)request.getAttribute("MSG");
String a =(String)request.getAttribute("a");
String name = (String)request.getParameter("name");
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript">
		 function myload(){
			var msg = "<%=msg%>";
			if(msg!="null"){
				alert(msg);
			}
		} 
	</script>
  </head>
  
  <body onload="myload()">
  <%=name %>
  	<form action="loginServlet" method="post">
  		用户名<input type="text" name="name">
  		密码<input type="password" name="pwd">
  		<input type="radio" name="user" value=1 checked="checked">卖家
  		<input type="radio" name="user" value=0>买家
  		<input type="submit" value="登录">
  	</form>
  	<form action="registerServlet">
  		<input type="submit" value="注册">
  	</form>
  </body>
</html>
