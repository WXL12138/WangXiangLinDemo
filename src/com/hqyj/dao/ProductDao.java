package com.hqyj.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.hqyj.bean.Product;
import com.hqyj.vo.ProductInfo;
import com.hqyj.vo.ProductPrice;

public class ProductDao extends BaseDao{
	public ArrayList<Product> queryPro(Product mypro){
		ArrayList<Product> list = new ArrayList<Product>();
		Connection conn = getConnection();
		if(conn!=null){
			PreparedStatement ps;
			try {
				ps = conn.prepareStatement("select * from product p, user u where u.id = p.userid and userid=?");
				ps.setInt(1, mypro.getUserId());
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
	
	public int addPro(Product product){
		int result = 0;
		Connection conn = getConnection();
		if(conn!=null){
			PreparedStatement ps;
			try {
				ps = conn.prepareStatement("insert into product(productName,price,state,userid) values(?,?,?,?)");
										  //insert into product(productName,price,state,userid) values("Æ»¹û",5.0,0,4);
				ps.setString(1, product.getProductName());
				ps.setDouble(2, product.getPrice());
				ps.setInt(3, product.getState());
				ps.setInt(4, product.getUserId());
				result = ps.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	public int deleteById(int id){
		int result = 0;
		Connection conn = getConnection();
		if(conn!=null){
			PreparedStatement ps;
			try {
				ps = conn.prepareStatement("delete from product where id = ?");
				ps.setInt(1, id);
				result = ps.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	public Product queryId(int id){
		Product pro  = null;
		Connection conn = getConnection();
		if(conn!=null){
			PreparedStatement ps;
			try {
				ps = conn.prepareStatement("select * from product where id = ?");
				ps.setInt(1, id);
				ResultSet rs = ps.executeQuery();
				if(rs.next()){
					pro = new Product();
					pro.setId(rs.getInt("id"));
					pro.setPrice(rs.getDouble("price"));
					pro.setProductName(rs.getString("productName"));
					pro.setState(rs.getInt("state"));
					pro.setUserId(rs.getInt("userid"));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return pro;
	}
	public int updateFromPro(Product pro){
		int result = 0;
		Connection conn = getConnection();
		if(conn!=null){
			PreparedStatement ps;
			try {
				ps = conn.prepareStatement("update product set productName=?,price=?,state=? where id = ?");
				ps.setString(1,pro.getProductName());
				ps.setDouble(2, pro.getPrice());
				ps.setInt(3, pro.getState());
				ps.setInt(4, pro.getId());
				result=ps.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	public ArrayList<ProductInfo> queryAllPro(){
		ArrayList<ProductInfo> list = new ArrayList<ProductInfo>();
		Connection conn = getConnection();
		if(conn!=null){
			PreparedStatement ps;
			try {
				ps = conn.prepareStatement("select productName,price,state,u.name from user u,product p where u.id = p.userid and state = 1");
				ResultSet rs = ps.executeQuery();
				while(rs.next()){
					ProductInfo info = new ProductInfo();
					info.setName(rs.getString("name"));
					info.setPrice(rs.getDouble("price"));
					info.setProductName(rs.getString("productName"));
					info.setState(rs.getInt("state"));
					list.add(info);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	
	public ArrayList<Product> queryMyInfo(ProductPrice pp){
		ArrayList<Product> list = new ArrayList<Product>();
		StringBuffer str = new StringBuffer("select * from product where userid=? ");
		ArrayList valueList = new ArrayList();
		valueList.add(pp.getUserId());
		if(!"".equals(pp.getProductName())){
			str.append("and productName like ? ");
			valueList.add("%"+pp.getProductName()+"%");
		}
		if(0!=pp.getMinprice()){
			str.append("and price>? ");
			valueList.add(pp.getMinprice());
		}
		if(0!=pp.getMaxprice()){
			str.append("and price<? ");
			valueList.add(pp.getMaxprice());
		}
		Connection conn = getConnection();
		if(conn!=null){
			PreparedStatement ps;
			try {
				ps = conn.prepareStatement(str.toString());
//				ps.setInt(1, pp.getUserId());
				for (int i = 0; i < valueList.size(); i++) {
					ps.setObject(i+1, valueList.get(i));
				} 
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
			} catch (SQLException e){
				e.printStackTrace();
			}
			
		}
		return list;
	}
}
