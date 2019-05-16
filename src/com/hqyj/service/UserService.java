package com.hqyj.service;

import java.util.ArrayList;

import com.hqyj.bean.Product;
import com.hqyj.bean.User;
import com.hqyj.dao.UserDao;

public class UserService {
	//-1�����û����Ѵ���
	//0������ӳɹ�
	//1������ʧ��
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
	//1:��¼�ɹ�0:�û���������-1:�������2:�û����ʹ���
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
