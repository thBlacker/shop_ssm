package cn.hc.shop.controller;


import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.hc.shop.entities.User;
import cn.hc.shop.service.UserService;
import cn.hc.shop.utils.MailUitls;
import cn.hc.shop.utils.UUIDUtils;



@Controller
public class UserController {

	@RequestMapping("/user_exit")
	public String user_exit(HttpSession session){
		session.invalidate();
		return "redirect:/index";
	}
	
	@Autowired
	private UserService userService;
	//跳转到注册页面
	@RequestMapping("/user_registPage")
	public String user_registPage(){
		return "regist";
	}
	
	//检查用户名是否存在
	@ResponseBody
	@RequestMapping("/checkUsername")
	public String checkUsername(@RequestParam("username") String username){
		return userService.queryUser(username);
	}
	
	@RequestMapping("/user_regist")
	public String user_regist(User user, @RequestParam("checkcode") String checkcode,HttpSession session,Map<String, Object> map){
		//判断验证码
		String sCode = (String) session.getAttribute("checkCode");
		if(!checkcode.equalsIgnoreCase(sCode)){
			map.put("checkCodeError", "验证码错误！");
			return "regist";
		}
		//System.out.println(user);
		//User [uid=null, username=www, password=www, name=asd, email=12@qq.com, phone=, addr=, state=null, code=null]
		//其中 uid（数据库会自动添加），state（状态），code（激活码）是null 用户无法填入的，由后台处理
		user.setState(0);
		String code = UUIDUtils.getUUID();
		user.setCode(code);
		System.out.println(code);
		//增加到数据库
		userService.modify(user);
		//挑转到msg页面提醒邮箱激活
		MailUitls.sendMail("13646718520@sina.cn", code);
		map.put("registMsg", "注册成功！请去邮箱激活！");
		return "msg";
	}
	
	//点击邮箱进来激活
	@RequestMapping("/user_active")
	public String user_active(@RequestParam("code") String code,Map<String, Object> map){
		User user = userService.queryUserByCode(code);
		System.out.println(code);
		if(user == null){
			map.put("activeMsg", "激活失败！");
			return "msg";
		}
		user.setState(1);
		user.setCode(null);
		userService.update(user);
		map.put("activeMsg", "激活成功！快去登录吧！");
		return "msg";
	}
	
	
	//跳到登录页面
	@RequestMapping("/user_loginPage")
	public String user_loginPage(){
		return "login";
	}
	
	//登录校验
	@RequestMapping("/user_login")
	public String user_login(@RequestParam("username") String username, @RequestParam("password") String password,Map<String,Object> map, HttpSession session){
		 User user = userService.queryUser(username, password);
		 if(user == null){
			 map.put("loginFail", "用户名密码错误！");
			 return "login";
		 }
		 int state = user.getState();
		 if(state != 1){
			 map.put("loginFail", "该用户未激活！");
			 return "login";
		 }
		 session.setAttribute("existUser", user);
		
		 return "redirect:/index";
	}
	
}	
	
	

