package cn.hc.shop.service.Impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.hc.shop.dao.UserMapper;
import cn.hc.shop.entities.User;
import cn.hc.shop.service.UserService;
@Transactional
@Service("userService")
public class UserSeviceImpl implements UserService {
	
	@Autowired
	private  UserMapper userMapper;

	
	public String queryUser(String username) {
		User user = userMapper.queryUserByUsername(username);
		if(user != null){
			return "yes";
		}
		return null;
	}


	@Override
	public User queryUser(String username, String password) {
		User user = userMapper.queryUserByUsernameAndPassword(username, password);
			return user;
	}


	@Override
	public void modify(User user) {
		userMapper.insertSelective(user);
	}


	@Override
	public User queryUserByCode(String code) {
		User user = userMapper.selectByCode(code);
		return user;
	}


	@Override
	public void update(User user) {
		userMapper.udpateByUser(user);
	}

}
