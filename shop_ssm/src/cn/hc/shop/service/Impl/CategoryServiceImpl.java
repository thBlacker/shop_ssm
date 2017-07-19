package cn.hc.shop.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.hc.shop.dao.CategoryMapper;
import cn.hc.shop.dao.CategorySecondMapper;
import cn.hc.shop.entities.Category;
import cn.hc.shop.entities.CategorySecond;
import cn.hc.shop.service.CategoryService;

@Service("categoryService")
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategorySecondMapper categorySecondMapper;
	
	@Autowired
	private CategoryMapper categoryMapper; 
	

	@Override
	public List<Category> findAllCAndCs() {
		List<Category> list = categoryMapper.getAllCategory();
		for (Category c : list) {
			int cid = c.getCid();
			List<CategorySecond> cslist = categorySecondMapper.getAllCategorySecond(cid);
			c.setCategorySeconds(cslist);
		}
		return list;
	}

	
}
