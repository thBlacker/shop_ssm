package cn.hc.shop.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cn.hc.shop.entities.Cart;
import cn.hc.shop.entities.CartItem;
import cn.hc.shop.entities.OrderItem;
import cn.hc.shop.entities.Orders;
import cn.hc.shop.entities.User;
import cn.hc.shop.service.OrderService;
import cn.hc.shop.utils.PageBean;


@Controller
public class OrderController {

	@Autowired
	private OrderService orderService;
	

	@RequestMapping("/order_findByUid")
	public String order_findByUid(HttpSession session,@RequestParam(value="page",required=false, defaultValue="1") int page,  Map<String ,Object> map){
		User user = (User) session.getAttribute("existUser");
		int uid = user.getUid();
		PageBean<Orders> pageBean = orderService.getOrdersByuid(uid,page);
		map.put("pageBean", pageBean);
		return "orderList";
	}
	
	@RequestMapping("/order_deleteByOid")
	public String order_deleteByOid(@RequestParam("oid") int oid,Map<String,Object> map){
		orderService.delOrderByOid(oid);
		return "redirect:/order_findByUid";
	}
	
	
	@RequestMapping("/order_saveOrder")
	public String order_saveOrder(HttpSession session, Map<String, Object> map){
		User user = (User)session.getAttribute("existUser");
		Orders order = orderService.addByUser(user);
		map.put("order", order);
		user.setCart(new Cart());
		return "order";
	}
	
	
}
