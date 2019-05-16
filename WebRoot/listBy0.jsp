<%@ page language="java" import="java.util.*,com.hqyj.vo.ProductInfo" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String msg =(String)request.getAttribute("MSG");
ArrayList<ProductInfo> list = (ArrayList<ProductInfo>)request.getAttribute("list");
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
    
    <title>My JSP 'listBy0.jsp' starting page</title>
    
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
  	<%=loginName %>你好
  	这是买家表
  	<table>
  		<caption>您的所有商品</caption>
  		<tr>
  			<th>商品名</th>
  			<th>价格</th>
  			<th>状态</th>
  			<th>商家</th>
  			
  		</tr>
  		<%for(ProductInfo pro:list){ %>
  		<tr>
  			<td><%=pro.getProductName() %></td>
  			<td><%=pro.getPrice() %></td>
  			<%-- <td><%=pro.getState() %> --%>
  			<td><%if(pro.getState()==0){ %>
  					下线	
  				<%}else{ %>
  					在售
  				<%} %>
  			</td>
  			<td><%=pro.getName() %></td>
  		</tr>	
  		<%} %>
  	</table>		
  </body>
</html>
