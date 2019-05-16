package com.hqyj.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hqyj.bean.Product;
import com.hqyj.bean.User;
import com.hqyj.service.ProductService;
import com.hqyj.service.UserService;

public class SendBuyerUserName extends HttpServlet{
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
		User user = new User();
		user.setName(name);
		UserService userivce = new UserService();
		int thisId = userivce.getThisId(user);
		ProductService ps =new ProductService();
		Product pro = new Product();
		pro.setUserId(thisId);
		ArrayList<Product> list  = ps.queryMyPro(pro);
		req.setAttribute("list", list);
		
		
		Cookie c3 = new Cookie("loginName",user.getName());
		Cookie c4 = new Cookie("loginId",String.valueOf(thisId));
		resp.addCookie(c3);
		resp.addCookie(c4);
		req.getRequestDispatcher("listBy1.jsp").forward(req, resp);
	}
}
