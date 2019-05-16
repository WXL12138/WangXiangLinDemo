package com.hqyj.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hqyj.bean.User;
import com.hqyj.service.UserService;

public class LoginServlet extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String name = new String(req.getParameter("name").getBytes("ISO-8859-1"),"utf-8");
		String password = new String(req.getParameter("pwd").getBytes("ISO-8859-1"),"utf-8");
		String type = new String(req.getParameter("user").getBytes("ISO-8859-1"),"utf-8");
		User user = new User();
		user.setName(name);
		user.setPassword(password);
		user.setType(Integer.parseInt(type));
		UserService service = new UserService();
		int i = service.queryByName(user);
		//1:登录成功0:用户名不存在-1:密码错误
		if(i==1&&user.getType()==1){
			int thisId = service.getThisId(user);
			Cookie c1  =new Cookie("loginName",name);
			Cookie c2 = new Cookie("loginId",String.valueOf(thisId));
			resp.addCookie(c1);
			resp.addCookie(c2);
			resp.sendRedirect("sendBuyerUserName");
		}else if(i==1&&user.getType()==0){
			int thisId = service.getThisId(user);
			Cookie cookie1 = new Cookie("loginId",String.valueOf(thisId));
			Cookie cookie2 = new Cookie("loginName",user.getName());
			resp.addCookie(cookie1);
			resp.addCookie(cookie2);
			resp.sendRedirect("sendSellerUserName");
		}else if(i==2){
			req.setAttribute("MSG", "用户类型错误");
			int a = 5;
			req.setAttribute("a",123);
			HttpSession session = req.getSession();
			session.setAttribute("a", 456);
			req.getRequestDispatcher("login.jsp").forward(req, resp);
		}else if(i==0){
			req.setAttribute("MSG", "用户名不存在");
			req.getRequestDispatcher("login.jsp").forward(req, resp);
		}else{
			req.setAttribute("MSG", "密码错误");
			req.getRequestDispatcher("login.jsp").forward(req, resp);
		}
	}
}
