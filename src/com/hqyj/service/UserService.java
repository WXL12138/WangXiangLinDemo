package com.hqyj.service;

import java.util.ArrayList;

import com.hqyj.bean.Product;
import com.hqyj.bean.User;
import com.hqyj.dao.UserDao;

public class UserService {
	//-1代表用户名已存在
	//0代表添加成功
	//1代表添失败
	public int addUserTo(User user){
		int result = 0;
		UserDao dao = new UserDao();
		boolean b = dao.exsitUser(user.getName());
		if(b){
			result =-1; 
		}else{
			boolean re = dao.addUser(user);
			if(re){
				result = 0;
			}else{
				result = 1;
			}
		}
		return result;
	}
	//1:登录成功0:用户名不存在-1:密码错误2:用户类型错误
	public int queryByName(User user){	
		int result = 1;
		UserDao dao = new UserDao();
		User userres =  dao.queryUser(user);
		if(userres==null){
			result = 0;
		}else if(!user.getPassword().equals(userres.getPassword())){
			result = -1;
		}else if(user.getType()!=userres.getType()){
			result = 2;
		}
		return result;
	}
	public int getThisId(User user){
		int thisId = 0;
		UserDao dao = new UserDao();
		thisId = dao.queryId(user.getName());
		return thisId;
	}
	public ArrayList<Product> queryAllSeller(int thisId){
		ArrayList<Product> list = new ArrayList<Product>();
		UserDao dao = new UserDao();
		list = dao.querySellerPro(thisId);
		return list;
	}
}
