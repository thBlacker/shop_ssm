package cn.hc.shop.service;

import cn.hc.shop.entities.Orders;
import cn.hc.shop.entities.User;
import cn.hc.shop.utils.PageBean;

public interface OrderService {

	
	PageBean<Orders> getOrdersByuid(int uid, int page);

	void delOrderByOid(int oid);

	Orders addByUser(User user);

}
