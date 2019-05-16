package com.hqyj.service;

import java.util.ArrayList;

import com.hqyj.bean.Product;
import com.hqyj.dao.ProductDao;
import com.hqyj.vo.ProductInfo;
import com.hqyj.vo.ProductPrice;

public class ProductService {
	
	public ArrayList<Product> queryMyPro(Product mypro){
		ArrayList<Product> list = null;
		ProductDao dao = new ProductDao();
		list = dao.queryPro(mypro);
		return list;
	}
	public boolean addProductByAll(Product product){
		boolean result = false;
		ProductDao dao = new ProductDao();
		int i = dao.addPro(product);
		if(i>0){
			result = true;
		}
		return result;
	}
	public boolean delProById(int id){
		ProductDao dao=  new ProductDao();
		int i = dao.deleteById(id);
		if(i>0){
			return true;
		}else{
			return false;
		}
	}
	public Product queryById(int id){
		ProductDao dao = new ProductDao();
		Product pro = dao.queryId(id);
		return pro;
	}
	public boolean updateById(Product pro){
		ProductDao dao = new ProductDao();
		int i = dao.updateFromPro(pro);
		if(i>0){
			return true;
		}else{
			return false;
		}
	}
	public ArrayList<ProductInfo> queryPro(){
		ArrayList<ProductInfo> list = new ArrayList<ProductInfo>();
		list = new ProductDao().queryAllPro();
		return list;
	}
	public ArrayList<Product> getMyQuery(ProductPrice pp){
		ArrayList<Product> list = new ArrayList<Product>();
		ProductDao dao = new ProductDao();
		list = dao.queryMyInfo(pp);
		return list;
	}
}
