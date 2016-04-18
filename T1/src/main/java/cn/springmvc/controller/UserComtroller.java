package cn.springmvc.controller;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.Page;

import cn.springmvc.common.Base;
import cn.springmvc.common.CONSTANTUtil;
import cn.springmvc.common.EncodeMD5;
import cn.springmvc.common.SessionUtil;
import cn.springmvc.model.UserInfo;
import cn.springmvc.service.UserService;


@Controller
public class UserComtroller extends Base {
	
	@Autowired
	private UserService userService;
	
	
	@RequestMapping("/index")
	public ModelAndView index(){
		ModelAndView view = new ModelAndView("index");
//		UserInfo user = new UserInfo();
//		user.setNickname("天天");
//		//user.setId(11l);
//		userService.insertuser(user);
		view.addObject("index","完美");
		return view;
	}
	@RequestMapping("/registered")
	public ModelAndView registered(
			HttpSession session,
			HttpServletRequest request
			) throws Exception{
		ModelAndView view = new ModelAndView("/registered/registered");
		String token  = UUID.randomUUID().toString();
		view.addObject("token",token);
		SessionUtil.setSession(session, "regToken", token);
		logger.info("----------token----"+token);
		return view;
	}
	
	@RequestMapping("/saveRegistered")
	public Object saveRegistered(
			HttpSession session,
			HttpServletRequest request,
			@RequestParam(value = "token",required = true) String token,
			@RequestParam(value = "account",required = true) String account,
			@RequestParam(value = "password",required = true) String password
			) throws Exception{
		String stoken = (String) SessionUtil.getSession(session, "regToken");
		SessionUtil.removeSession(session, "regToken");
		if(null==stoken||!stoken.equals(token)){
			return goUrl("/index.do", "请不要重复提交");
		}
		if(userService.checkUserAccoutExist(account.trim())){
			return goUrl("/index.do", "对不起!您的帐号已经存在了");
		}
		try {
			UserInfo  userInfo  = new UserInfo();
			userInfo.setAccount(account);
			userInfo.setPassword(EncodeMD5.GetMD5Code(password.trim()));
			userInfo.setEnable(0);
			userInfo.setCreatedate(new Date());
			userInfo.setStatus(0);
			userService.insertuser(userInfo);
			return goUrl("/index.do", "注册成功");
		} catch (Exception e) {
			return	goUrl("/index.do", "注册失败");
		}
	}
	 public void ojor(){
		 for (int i = 0; i < 100; i++) {
			 String account = "user"+i;
			 String password = "123456";
			  UserInfo  userInfo  = new UserInfo();
				userInfo.setAccount(account);
				userInfo.setPassword(EncodeMD5.GetMD5Code(password.trim()));
				userInfo.setEnable(0);
				userInfo.setCreatedate(new Date());
				userInfo.setStatus(0);
				if(userService.checkUserAccoutExist(account.trim())){
					
				}else{
					userService.insertuser(userInfo);
				}
		}
		 	
	 }

	//登录
	@RequestMapping("/login")
	public Object login(
			HttpSession session
			) throws Exception{
		ModelAndView view = new ModelAndView("/login");
		String token  = UUID.randomUUID().toString();
		view.addObject("loginToken",token);
		SessionUtil.setSession(session, "loginToken", token);
		logger.info("----------loginToken----"+token);
		return view;
		
	}
	
	@RequestMapping("/saveLogin")
	public Object saveLogin(
			@RequestParam(value = "token",required= true) String token,
			@RequestParam(value = "account",required= true) String account,
			@RequestParam(value = "password",required= true) String password,
			HttpSession session
			) throws Exception{
		String stoken = (String) SessionUtil.getSession(session, "loginToken");
		SessionUtil.removeSession(session, "loginToken");
		if(null==stoken||!stoken.equals(token)){
			return goUrl("/login.do", "请不要重复提交");
		}
		UserInfo info = 	userService.insetLogin(account,password);
		if(null==info){
			return goUrl("/login.do", "帐号或者密码错误");
		}
		SessionUtil.setSession(session, CONSTANTUtil.USERINFO, info);
		 return goUrl("/user_details.do", "登录成功");
		
	}
	
	//详情
	@RequestMapping("/user_details")
	public ModelAndView userDetails(){
		ModelAndView view = new ModelAndView("/user/details");
		return view;
	}
	
	
	//修改
	@RequestMapping("/user_update")
	public Object userupdate(
			HttpSession session,
			@RequestParam(value = "nickname",required = true)String nickname
			) throws Exception{
		ModelAndView view = new ModelAndView("/user/details");
		UserInfo userInfo = (UserInfo) SessionUtil.getSession(session, CONSTANTUtil.USERINFO);
		userInfo.setNickname(nickname);
		boolean update = userService.update(userInfo);
		if(update){
			UserInfo selectUserById = userService.selectUserById(userInfo.getId());
			SessionUtil.setSession(session, CONSTANTUtil.USERINFO, selectUserById);
			return super.goUrl("/user_details.do", "修改成功");
		}else{
			return super.goUrl("/user_details.do", "修改失败");
		}
	}
	
	
	//列表
	@RequestMapping("/user_list")
	public ModelAndView userList(
			@RequestParam(value="page",required =false)Integer page,
			@RequestParam(value="pageSize",required =false)Integer pageSize
			){
		if(page==null)
		page = 1;
		pageSize = 10;
		ModelAndView view = new ModelAndView("/user/userList");
		Page<UserInfo> list =  (Page<UserInfo>) userService.selectUserList(page,pageSize);
		view.addObject("pageInfo",list);
		System.out.println(list.getResult().size());
		return view;
	}
	
}
