package cn.hc.shop.service.Impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.hc.shop.dao.OrderItemMapper;
import cn.hc.shop.dao.OrdersMapper;
import cn.hc.shop.dao.ProductMapper;
import cn.hc.shop.entities.Cart;
import cn.hc.shop.entities.CartItem;
import cn.hc.shop.entities.OrderItem;
import cn.hc.shop.entities.Orders;
import cn.hc.shop.entities.Product;
import cn.hc.shop.entities.User;
import cn.hc.shop.service.OrderService;
import cn.hc.shop.utils.PageBean;

@Service("orderService")
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrdersMapper ordersMapper;
	
	@Autowired
	private OrderItemMapper orderItemMapper;
	
	@Autowired
	private ProductMapper productMapper;
	
	
	
	@Override
	public PageBean<Orders> getOrdersByuid(int uid,int page) {
		
		PageBean<Orders> pageBean = new PageBean<>();
		int limit = 12;
		pageBean.setLimit(limit);
		pageBean.setPage(page);
		int begin = (page-1)*limit;
		
		List<Orders> list = ordersMapper.getOrderItemsbyUid(uid,begin,limit);
		for (Orders o : list) {
			int oid = o.getOid();
			List<OrderItem> orderItems = orderItemMapper.getOrderItemByOid(oid);
			for (OrderItem oi : orderItems) {
				int pid = oi.getPid();
				Product product = productMapper.selectByPrimaryKey(pid);
				oi.setProduct(product);
			}
			o.setOrderItems(orderItems);
		}
		pageBean.setList(list);
		
		int totalCount = ordersMapper.getCountByUid(uid);
		pageBean.setTotalCount(totalCount);
		
		
		int totalPage = totalCount%limit == 0?totalCount/limit:totalCount/limit+1;
		pageBean.setTotalPage(totalPage);
		
		return pageBean;
	}



	@Override
	public void delOrderByOid(int oid) {
		ordersMapper.remove(oid);
	}



	@Override
	public Orders addByUser(User user) {
		
		Cart cart = user.getCart();
		Orders order = new Orders();
		order.setAddr(user.getAddr());
		order.setName(user.getName());
		order.setOid(null);
		order.setOrdertime(new Date());
		order.setPhone(user.getPhone());
		order.setState(1);
		order.setTotal(cart.getTotal());
		order.setUid(user.getUid());
		//
		ordersMapper.insertAndGetId(order);
		int oid = order.getOid();
		
		List<OrderItem> orderItems = new ArrayList<>();
		List<CartItem> list = cart.getCartItems();
		for (CartItem c : list) {
			OrderItem orderItem = new OrderItem();
			//放入对应属性的值
			orderItem.setItemid(null);
			orderItem.setOid(oid);
			orderItem.setCount(c.getCount());
			orderItem.setPid(c.getProduct().getPid());
			orderItem.setSubtotal(c.getSubtotal());
			orderItemMapper.insertSelective(orderItem);
			//放入对应属性的值
			orderItem.setProduct(c.getProduct());
			orderItems.add(orderItem);
		}
		order.setOrderItems(orderItems);
		order.setOid(oid);
		order.setUser(user);
		
		return order;
	}
}
