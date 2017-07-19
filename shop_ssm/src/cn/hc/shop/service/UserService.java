package cn.hc.shop.service;

import java.util.List;

import cn.hc.shop.entities.Category;
import cn.hc.shop.entities.User;

public interface UserService {

	String queryUser(String username);
	
	User queryUser(String username, String password);

	void modify(User user);

	User queryUserByCode(String code);

	void update(User user);

}
