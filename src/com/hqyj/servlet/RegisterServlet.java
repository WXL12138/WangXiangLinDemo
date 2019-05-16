package com.hqyj.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hqyj.bean.User;
import com.hqyj.service.UserService;

public class RegisterServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.getRequestDispatcher("register.jsp").forward(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String name = new String(req.getParameter("name").getBytes("ISO-8859-1"),"UTF-8");
		String password = new String(req.getParameter("pwd").getBytes("ISO-8859-1"),"UTF-8");
		String type = new String(req.getParameter("user").getBytes("ISO-8859-1"),"UTF-8");
		User user =new User();
		user.setName(name);
		user.setPassword(password);
		user.setType(Integer.parseInt(type));
		//-1代表用户名已存在
		//0代表添加成功
		//1代表添失败
		UserService service = new UserService();
		int i = service.addUserTo(user);
		if(i==0){
			req.setAttribute("MSG", "注册成功");
			req.getRequestDispatcher("login.jsp").forward(req, resp);
		}else if(i==0){
			req.setAttribute("MSG", "注册失败");
			req.getRequestDispatcher("register.jsp").forward(req, resp);
		}else{
			req.setAttribute("MSG", "用户名已存在");
			req.getRequestDispatcher("register.jsp").forward(req, resp);
		}
	}
}
