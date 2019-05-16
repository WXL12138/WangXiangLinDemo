package com.hqyj.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.hqyj.bean.Product;
import com.hqyj.bean.User;

public class UserDao extends BaseDao{
	public boolean exsitUser(String name){
		boolean result = false;
		Connection conn = getConnection();
		if(conn!=null){
			PreparedStatement ps;
			try {
				ps = conn.prepareStatement("select * from user where name=?");
				ps.setString(1, name);
				ResultSet rs = ps.executeQuery();
				if(rs.next()){
					result = true;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		return result;
	}
	public boolean addUser(User user){
		boolean result = false;
		Connection conn = getConnection();
		if(conn!=null){
			PreparedStatement ps;
			try {
				ps = conn.prepareStatement("insert into user(name,password,type) values(?,?,?)");
				ps.setString(1, user.getName());
				ps.setString(2, user.getPassword());
				ps.setInt(3,user.getType());
				int r = ps.executeUpdate();
				if(r>0){
					result = true;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	public User queryUser(User user){
		User thisUser =null;
		Connection conn = getConnection();
		if(conn!=null){
			PreparedStatement ps;
			try {
				ps = conn.prepareStatement("select*from user where name = ?");
				ps.setString(1, user.getName());
				ResultSet rs = ps.executeQuery();
				if(rs.next()){
					thisUser = new User();
					thisUser.setName(rs.getString("name"));
					thisUser.setPassword(rs.getString("password"));
					thisUser.setType(rs.getInt("type"));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return thisUser;
	}
	public int queryId(String name){
		int thisId =0;
		Connection conn = getConnection();
		if(conn!=null){
			PreparedStatement ps;
			try {
				ps = conn.prepareStatement("select id from user where name = ?");
				ps.setString(1, name);
				ResultSet rs = ps.executeQuery();
				if(rs.next()){
					thisId = rs.getInt("id");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return thisId;
	}
	public ArrayList<Product> querySellerPro(int thisId){
		ArrayList<Product> list = new ArrayList<Product>();
		Connection conn = getConnection();
		if(conn!=null){
			PreparedStatement ps;
			try {
				ps = conn.prepareStatement("select p.id,productName,price,state,userid from user u,product p where u.id = p.userid and userid=?");
				ps.setInt(1,thisId);
				ResultSet rs = ps.executeQuery();
				while(rs.next()){
					Product pro = new Product();
					pro.setId(rs.getInt("id"));
					pro.setPrice(rs.getDouble("price"));
					pro.setProductName(rs.getString("productName"));
					pro.setState(rs.getInt("state"));
					pro.setUserId(rs.getInt("userid"));
					list.add(pro);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
}
