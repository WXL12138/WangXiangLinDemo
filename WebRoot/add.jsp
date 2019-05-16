<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String msg = (String)request.getAttribute("MSG");
Cookie[] cook =  request.getCookies();
String loginId = "";
if(cook!=null){
	for(Cookie c:cook){
		if("loginId".equals(c.getName())){
			loginId = c.getValue();
		}
	}
}
Cookie[] cook1 =  request.getCookies();
String loginName = "";
if(cook!=null){
	for(Cookie c:cook){
		if("loginName".equals(c.getName())){
			loginName = c.getValue();
		}
	}
}
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'add.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script>
		function myload(){
			var mg = "<%=msg%>";
			if(mg!="null"){
				alert(mg);
			}
		}
	</script>
  </head>
  
  <body onload="myload()">
  	<%=loginName %>你好
    <form action="addServlet" method="post">
  		请输入产品名称<input type="text" name="productName">
  		请输入产品价格<input type="text" name="price">
  		<input type="hidden" name="id" value="<%=loginId %>">
  		<input type="submit" value="增加">
  	</form>
  </body>
</html>
