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

public class DeleteServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String id = new String(req.getParameter("id").getBytes("ISO-8859-1"),"utf-8");
		String userId = new String(req.getParameter("userid").getBytes("ISO-8859-1"),"utf-8");
		ProductService proservice = new ProductService();
		boolean b = proservice.delProById(Integer.parseInt(id));
		if(b){
			req.setAttribute("MSG", "É¾³ý³É¹¦");
			UserService uservice = new UserService();
			ArrayList<Product> list = uservice.queryAllSeller(Integer.parseInt(userId));
			req.setAttribute("list", list);
			req.getRequestDispatcher("listBy1.jsp").forward(req, resp);
		}else{
			req.setAttribute("MSG", "É¾³ýÊ§°Ü");
			UserService uservice = new UserService();
			ArrayList<Product> list = uservice.queryAllSeller(Integer.parseInt(userId));
			req.setAttribute("list", list);
			req.getRequestDispatcher("listBy1.jsp").forward(req, resp);
		}
	}
}
