<%@ page language="java" import="java.util.*,com.hqyj.bean.Product" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
Product pro = (Product)request.getAttribute("Product");
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'update.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
  	<form action="updateServlet" method="post">
  		<input type="hidden" name="id" value="<%=pro.getId() %>">
  		<input type="hidden" name="userid" value="<%=pro.getUserId() %>">
  		产品名称<input type="text" name = "productName" value="<%=pro.getProductName()%>">
  		价格<input type="text" name = "price" value="<%=pro.getPrice() %>">
  		状态<select name="state">,
  			<option value="0" selected>下线</option>
  			<option value="1">在售</option>
  			 </select>
  		<input type="submit" value="修改">
  	</form>
  </body>
</html>
