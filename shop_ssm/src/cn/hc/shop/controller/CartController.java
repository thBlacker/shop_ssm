package cn.hc.shop.controller;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.hc.shop.entities.Cart;
import cn.hc.shop.entities.CartItem;
import cn.hc.shop.entities.Product;
import cn.hc.shop.entities.User;
import cn.hc.shop.service.ProductService;

@Controller
public class CartController {
	
	@Autowired
	ProductService productService;
	

	@RequestMapping("/cart_myCart")
	public String cart_myCart(HttpSession session,Map<String,Object> map){
		User user = (User) session.getAttribute("existUser");
		if(user == null){
			map.put("cartMsg", "您还没有登录！");
			return "cart";
		}
		Cart cart = user.getCart();
		if(cart == null){
			cart = new Cart();
			user.setCart(cart);
		}
		if(cart.getCartItems() == null || cart.getCartItems().size() == 0){
			map.put("cartMsg", "亲!您还没有购物!请先去购物!");
			return "cart";
		}
		map.put("cart", cart);
		return "cart";
	}
	
	
	@RequestMapping("/cart_addCart")
	public String cart_addCart(HttpSession session,int pid,int count,Map<String,Object> map){
		User user = (User) session.getAttribute("existUser");
		if(user == null){
			map.put("loginFail", "您还没有登录，请登录后再购买！");
			return "login";
		}
		Cart cart = user.getCart();
		if(cart == null){
			cart = new Cart();
			user.setCart(cart);
			map.put("cart", cart);
		}
		Product product = productService.findProductByPid(pid);
		CartItem cartItem = new CartItem();
		cartItem.setProduct(product);
		cartItem.setCount(count);
		cart.addCartItem(cartItem);
		double total = 0;
		List<CartItem> cartItems = cart.getCartItems();
		for (CartItem c : cartItems) {
			total = total + c.getSubtotal();
		}
		cart.setTotal(total);
		user.setCart(cart);
		session.setAttribute("existUser", user);
		map.put("cart", cart);
		return "cart";
	}
	
	
	@RequestMapping("/cart_clearCart")
	public String cart_clearCart(HttpSession session,Map<String,Object> map){
		User user = (User) session.getAttribute("existUser");
		Cart cart = new Cart();
		user.setCart(cart);
		session.setAttribute("existUser", user);
		map.put("cart", cart);
		map.put("cartMsg", "亲!您还没有购物!请先去购物!");
		return "cart";
	}
	
	@RequestMapping("/cart_removeCart")
	public String cart_removeCartByPid(int pid,HttpSession session){
		User user = (User)session.getAttribute("existUser");
		Cart cart = user.getCart();
		List<CartItem> list = cart.getCartItems();
		Iterator<CartItem> i = list.iterator();
		while(i.hasNext()){
			CartItem c = i.next();
			if(c.getProduct().getPid() == pid){
				i.remove();
			}
		}
		cart.setCartItems(list);
		user.setCart(cart);
		session.setAttribute("existUser", user);
		return "redirect:/cart_myCart";
	}
	

	
}
