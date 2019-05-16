<%@ page language="java" import="java.util.*,com.hqyj.bean.Product" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String msg = (String)request.getAttribute("MSG");
ArrayList<Product> list = (ArrayList<Product>)request.getAttribute("list");
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
    
    <title>My JSP 'listBy1.jsp' starting page</title>
    
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
		function myQuery(){
			var minPrice = document.getElementsByName("minPrice")[0].value;
			var maxPrice = document.getElementsByName("maxPrice")[0].value;
			
			if(minPrice!="" && maxPrice!="" &&  minPrice>=maxPrice){
				alert("最低价格应该小于最高价格");
				return false;
			}
			return true;
		}
	</script>
  </head>
  
  <body onload = "myload()">
  	<%=loginName %>你好
  	这是商家表
  	<form action="addServlet">
  		<input type="submit" value="增加商品">
  	</form>
  	<table>
  		<caption>您的所有商品</caption>
  		<tr>
  			<th>商品名称</th>
  			<th>价格</th>
  			<th>状态</th>
  			<th>删除</th>
  			
  		</tr>
  		<%for(Product p:list){ %>
  		<tr>
  			<td><%=p.getProductName() %></td>
  			<td><%=p.getPrice() %></td>
  			<td><%if(p.getState()==0){ %>
  					下线	
  				<%}else{ %>
  					在售
  				<%} %>
  			</td>
  			<td>
  				<form action="deleteServlet">
  					<input type="hidden" name="userid" value="<%=p.getUserId()%>">
  					<input type="hidden" name="id" value="<%=p.getId() %>">
  					<input type="submit" value="删除">
  				</form>
  			</td>
  			<td>
  				<form action="updateServlet">
  					<input type="hidden" name="userid" value="<%=p.getUserId()%>">
  					<input type="hidden" name="id" value="<%=p.getId() %>">
  					<input type="submit" value="修改">
  				</form>
  			</td>
  		</tr>
  		<%}%>
  	</table>
  	
  	
  	<form action="queryServlet">
  		<input type="hidden" name="username" value="<%=loginName %>">
  		请输入产品名称<input type="text" name="productName">
  		请输入最低价格<input type="text" name="minPrice">
  		请输入最高价格<input type="text" name="maxPrice">
  		<input type="hidden" name = "id" value="<%=loginId %>">
  		<input type="submit" value="搜索" onclick="return myQuery()">
  	</form>
  </body>
</html>
