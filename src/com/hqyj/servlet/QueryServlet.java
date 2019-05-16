package com.hqyj.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hqyj.bean.Product;
import com.hqyj.service.ProductService;
import com.hqyj.vo.ProductPrice;

public class QueryServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String productName = new String(req.getParameter("productName").getBytes("ISO-8859-1"),"utf-8");
		String minPrice = new String(req.getParameter("minPrice").getBytes("ISO-8859-1"),"utf-8");
		String maxPrice = new String(req.getParameter("maxPrice").getBytes("ISO-8859-1"),"utf-8");
		String userId = new String(req.getParameter("id").getBytes("ISO-8859-1"),"UTF-8");
		ProductPrice pp = new ProductPrice();
		pp.setUserId(Integer.parseInt(userId));
		if("".equals(minPrice)){
			pp.setMinprice(0);
		}else{
			pp.setMinprice(Double.parseDouble(minPrice));
		}
		if("".equals(maxPrice)){
			pp.setMaxprice(Double.MAX_VALUE);
		}else{
			pp.setMaxprice(Double.parseDouble(maxPrice));
		}
//		pp.setUserId(Integer.parseInt(userId));
		pp.setProductName(productName);
		ProductService pService = new ProductService();
		ArrayList<Product> list =  pService.getMyQuery(pp);
		req.setAttribute("list",list);
		req.getRequestDispatcher("listBy1.jsp").forward(req, resp);
	}
}
