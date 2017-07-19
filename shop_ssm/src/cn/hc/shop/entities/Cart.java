package cn.hc.shop.entities;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Cart {
	
	//购物车中的购物项
	private Map<Integer, CartItem> carts = new LinkedHashMap<Integer,CartItem>();

    private double total;


    
	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}
	
	//一个购物车中有多个购物项
    public List<CartItem> getCartItems(){
    	return new ArrayList<>(carts.values());
    }
    
    //删除操作后，重新放入集合
    public void setCartItems(List<CartItem> list){
    	carts.clear();
    	for (CartItem c : list) {
    		carts.put(c.getProduct().getPid(), c);
		}
    }
    
    
    
    //把购物项增加到购物车
    public void addCartItem(CartItem cartItem){
    	Integer pid = cartItem.getProduct().getPid();
    	
    	if(carts.containsKey(pid)){
    		//添加了已有的商品，获取之前的购物明细，添加数量
    		CartItem cartItem2 = carts.get(pid);
    		cartItem2.setCount(cartItem2.getCount()+cartItem.getCount());
    	}else{
    		//添加没有的商品,直接放入购物车
    		carts.put(pid, cartItem);
    	}
    }
	
}
