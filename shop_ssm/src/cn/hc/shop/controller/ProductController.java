package cn.hc.shop.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cn.hc.shop.entities.Cart;
import cn.hc.shop.entities.Category;
import cn.hc.shop.entities.Product;
import cn.hc.shop.service.CategoryService;
import cn.hc.shop.service.ProductService;
import cn.hc.shop.utils.PageBean;

@Controller
public class ProductController {
	@Autowired
	ProductService productService;
	
	@Autowired
	private CategoryService categoryService;
	
	
	@RequestMapping("/test")
	public String test(){
		return "success";
	}
	
	@RequestMapping("/index")
	public String index(HttpSession session, HttpServletRequest request){
		//查找Category 其中有属性为：CategorySecond，所以一起查找。
		List<Category> cList= categoryService.findAllCAndCs();
		session.setAttribute("cList", cList);
		
		//最热
		List<Product> hList = productService.findHot();
		request.setAttribute("hList", hList);
		//最新、
		List<Product> nList = productService.findNew();
		request.setAttribute("nList", nList);
		
		return "index";
	}
	
	@RequestMapping("/product_findByCid")
	public String product_findByCid(Integer cid,@RequestParam(value = "page", required = false ,defaultValue="1") int page, Map<String, Object> map){
		//根据页面可得：需要获得pageBean
		PageBean<Product> pageBean = productService.getPageBean(cid,page);
		 
		map.put("pageBean", pageBean);
		map.put("cid", cid);
		return "productList";
	}
	
	
	@RequestMapping("/product_findByCsid")
	public String product_findByCsid(Integer csid, @RequestParam(value="page", required=false,defaultValue="1") int page,Map<String, Object> map){
		PageBean<Product> pageBean = productService.getPageBeanByCsid(csid,page);
		
		map.put("pageBean", pageBean);
		map.put("csid", csid);
		return "productList";
	}
	


	
	//图片信息：
	@RequestMapping("/product_findByPid")
	public String product_findByPid(Integer pid,Map<String, Object> map){
		Product model = productService.findProductByPid(pid);
		map.put("model", model);
		return "product";
	}
	
	@RequestMapping("/search")
	public String search(@RequestParam(value="sid", required=false,defaultValue="1") int sid, @RequestParam(value="lprice", required=false ) Double lprice, @RequestParam(value="hprice",required=false) Double hprice,@RequestParam(value="prname", required=false) String prname,@RequestParam(value="page", required=false,defaultValue="1") int page,Map<String, Object> map,HttpSession session){
		if(lprice != null || hprice!=null || prname!=null){
			session.setAttribute("lprice", lprice);
			session.setAttribute("hprice", hprice);
			session.setAttribute("prname", prname);
		}
		
		if(lprice == null && hprice==null && prname==null){
			Object oLprice = session.getAttribute("lprice");
			if(oLprice != null){
				lprice = (Double) oLprice;
			}
			Object oHprice = session.getAttribute("hprice");
			if(oHprice != null){
				hprice = (Double) oHprice;
			}
			Object oPrname = session.getAttribute("prname");
			if(oPrname != null){
				prname = (String) oPrname;
			}
		}
		
		PageBean<Product> pageBean = productService.getPageBean(lprice,hprice,prname,page);
		map.put("pageBean", pageBean);
		map.put("sid", sid);
		return "productList";
	}
	
	
}
