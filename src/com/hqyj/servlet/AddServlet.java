package com.hqyj.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hqyj.bean.Product;
import com.hqyj.service.ProductService;
import com.hqyj.service.UserService;

public class AddServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException ,IOException {
		req.getRequestDispatcher("add.jsp").forward(req, resp);
	}
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String productName = new String(req.getParameter("productName").getBytes("ISO-8859-1"),"UTF-8");
		String price = new String(req.getParameter("price").getBytes("ISO-8859-1"),"UTF-8");
		String id = new String(req.getParameter("id").getBytes("ISO-8859-1"),"utf-8");
		Product product = new Product();
		product.setPrice(Double.parseDouble(price));
		product.setProductName(productName);
		product.setState(0);
		product.setUserId(Integer.parseInt(id));
		
		ProductService proservice = new ProductService();
		boolean b = proservice.addProductByAll(product);
		if(b){
			req.setAttribute("MSG", "增加成功");
			UserService uservice = new UserService();
			ArrayList<Product> list = uservice.queryAllSeller(Integer.parseInt(id));
			req.setAttribute("list", list);
			req.getRequestDispatcher("listBy1.jsp").forward(req, resp);
		}else{
			req.setAttribute("MSG", "增加失败");
			req.getRequestDispatcher("add.jsp").forward(req, resp);
		}
	}
}
