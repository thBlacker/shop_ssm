package cn.hc.shop.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.hc.shop.dao.CategoryMapper;
import cn.hc.shop.dao.CategorySecondMapper;
import cn.hc.shop.dao.ProductMapper;
import cn.hc.shop.entities.Product;
import cn.hc.shop.service.ProductService;
import cn.hc.shop.utils.PageBean;

@Service("productService")
public class ProductServiceImpl implements ProductService {

	@Autowired
	ProductMapper productMapper;

	@Override
	public List<Product> findHot() {
		return productMapper.getHot();
	}

	@Override
	public List<Product> findNew() {
		return productMapper.getNew();
	}

	@Override
	public PageBean<Product> getPageBean(Integer cid, int page) {
		PageBean<Product> pageBean = new PageBean<Product>();
		int limit = 12;
		pageBean.setLimit(limit);
		
		pageBean.setPage(page);
		
		
		int begin = (page-1)*limit;
		List<Product> list = productMapper.getProductsByCid(cid, begin, limit);
		pageBean.setList(list);
		
		int totalCount = productMapper.getCount(cid);
		pageBean.setTotalCount(totalCount);
		
		int totalPage = totalCount%limit == 0 ? totalCount/limit:(totalCount/limit+1);
		pageBean.setTotalPage(totalPage);
		return pageBean;
	}

	@Override
	public PageBean<Product> getPageBeanByCsid(Integer csid, int page) {
		PageBean<Product> pageBean = new PageBean<Product>();
		int limit = 12;
		pageBean.setLimit(limit);
		
		pageBean.setPage(page);
		
		int begin = (page-1)*limit;
		List<Product> list = productMapper.getProductByCsid(csid,begin,limit);
		pageBean.setList(list);
		
		
		int totalCount = productMapper.getCountByCsid(csid);
		pageBean.setTotalCount(totalCount);
		
		int totalPage = totalCount%limit == 0 ? totalCount/limit:(totalCount/limit+1);
		pageBean.setTotalPage(totalPage);
		
		return pageBean;
	}


	@Override
	public PageBean<Product> getPageBean(Double lprice, Double hprice, String prname,int page) {
		PageBean<Product> pageBean = new PageBean<Product>();
		int limit = 12;
		pageBean.setLimit(limit);
		pageBean.setPage(page);
		
		int begin = (page-1)*10;
		
		List<Product> list = productMapper.findProductBySearch(lprice,hprice,prname,begin,limit);
		pageBean.setList(list);
		
		int totalCount = productMapper.getCounts(lprice,hprice,prname);
		pageBean.setTotalCount(totalCount);
		
		int totalPage = totalCount%limit == 0 ? totalCount/limit:(totalCount/limit+1);
		pageBean.setTotalPage(totalPage);
		
		return pageBean;
	}

	
	
	
	@Override
	public Product findProductByPid(Integer pid) {
		Product product = productMapper.selectByPrimaryKey(pid);
		return product;
	}

	
}
