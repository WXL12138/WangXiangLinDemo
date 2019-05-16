<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String msg = (String)request.getAttribute("MSG");
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'register.jsp' starting page</title>
    
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
			var mg = "<%=msg%>";
			if(mg!="null"){
				alert(mg);
			}
		}
	</script>
  </head>
  
  <body onload="myload()">
  	<form action="registerServlet" method="post">
  		请输入用户名<input type="text" name="name">
  		请输入密码<input type="password" name="pwd">
  		<input type="radio" name="user" checked="checked" value="0">买家
  		<input type="radio" name="user" value="1">卖家
  		<input type="submit" value="提交"> 
  	</form>
  </body>
</html>
