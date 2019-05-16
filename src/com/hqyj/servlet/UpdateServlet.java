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

@SuppressWarnings("serial")
public class UpdateServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String id = new String(req.getParameter("id").getBytes("ISO-8859-1"),"utf-8");
		ProductService proService = new ProductService();
		Product pro = proService.queryById(Integer.parseInt(id));
		req.setAttribute("Product", pro);
		req.getRequestDispatcher("update.jsp").forward(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String id = new String(req.getParameter("id").getBytes("ISO-8859-1"),"utf-8");
		String productName = new String(req.getParameter("productName").getBytes("ISO-8859-1"),"UTF-8");
		String price = new String(req.getParameter("price").getBytes("ISO-8859-1"),"UTF-8");
		String state = new String(req.getParameter("state").getBytes("ISO-8859-1"),"UTF-8");
		String userid = new String(req.getParameter("userid").getBytes("ISO-8859-1"),"UTF-8");
		Product pro= new Product();
		pro.setId(Integer.parseInt(id));
		pro.setPrice(Double.parseDouble(price));
		pro.setProductName(productName);
		pro.setState(Integer.parseInt(state));
		pro.setUserId(Integer.parseInt(userid));
		ProductService proService = new ProductService();
		boolean b= proService.updateById(pro);
		if(b){
			UserService uService = new UserService();
			ArrayList<Product> list = uService.queryAllSeller(Integer.parseInt(userid));
			req.setAttribute("list", list);
			req.setAttribute("MSG", "修改成功");
			req.getRequestDispatcher("listBy1.jsp").forward(req, resp);
		}else{
			req.setAttribute("MSG", "修改失败");
			req.getRequestDispatcher("update.jsp").forward(req, resp);
		}
	}
}
