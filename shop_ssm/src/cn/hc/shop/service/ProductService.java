package cn.hc.shop.service;

import java.util.List;

import cn.hc.shop.entities.Product;
import cn.hc.shop.utils.PageBean;

public interface ProductService {

	List<Product> findHot();
	
	
	List<Product> findNew();


	PageBean<Product> getPageBean(Integer cid, int page);


	PageBean<Product> getPageBeanByCsid(Integer csid, int page);


	Product findProductByPid(Integer pid);




	PageBean<Product> getPageBean(Double lprice, Double hprice, String prname,int page);


}
