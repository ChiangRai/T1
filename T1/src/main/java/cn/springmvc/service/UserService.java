package cn.springmvc.service;

import java.util.List;

import com.github.pagehelper.Page;

import cn.springmvc.model.UserInfo;

public interface UserService {

	public void insertuser(UserInfo userInfo);

	public boolean checkUserAccoutExist(String account);

	public UserInfo insetLogin(String account, String password);

	public Page<UserInfo> selectUserList(Integer page, Integer pageSize);
	
	public boolean update(UserInfo userInfo);

	public UserInfo selectUserById(Long id);
}
