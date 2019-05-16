package com.hqyj.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hqyj.bean.User;
import com.hqyj.service.ProductService;
import com.hqyj.vo.ProductInfo;

public class SendSellerUserName extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String name = "";
		Cookie[] c1 = req.getCookies();
		if(c1!=null){
			for (Cookie cookie : c1) {
				if("loginName".equals(cookie.getName())){
					name = cookie.getValue();
				}
			}
		}
		String userId = "";
		Cookie[] c2 = req.getCookies();
		if(c2!=null){
			for (Cookie cookie : c2) {
				if("loginId".equals(cookie.getName())){
					userId = cookie.getValue();
				}
			}
		}
		ProductService ps = new ProductService();
		ArrayList<ProductInfo> list = ps.queryPro();
		req.setAttribute("list", list);
		req.setAttribute("MSG", "µÇÂ¼³É¹¦");
		req.getRequestDispatcher("listBy0.jsp").forward(req, resp);
		
	}
}
