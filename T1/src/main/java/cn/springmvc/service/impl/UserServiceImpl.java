package cn.springmvc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import cn.springmvc.common.EncodeMD5;
import cn.springmvc.dao.UserInfoMapper;
import cn.springmvc.model.UserInfo;
import cn.springmvc.model.UserInfoExample;
import cn.springmvc.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService {

	@Autowired
	private UserInfoMapper userInfoMapper;
	
	public void insertuser(UserInfo userInfo) {
		int insert = userInfoMapper.insert(userInfo);
	}

	public boolean checkUserAccoutExist(String account) {
		UserInfoExample example = new UserInfoExample();
		example.createCriteria().andAccountEqualTo(account);
		List<UserInfo> selectByExample = userInfoMapper.selectByExample(example);
		if (selectByExample.size()>0) {
			return true;
		}
		return false;
		
	}

	public UserInfo insetLogin(String account, String password) {
		UserInfoExample example = new UserInfoExample();
		example.createCriteria().andAccountEqualTo(account).andPasswordEqualTo(EncodeMD5.GetMD5Code(password));
		List<UserInfo> selectByExample = userInfoMapper.selectByExample(example);
		if(selectByExample.size()==0){
			return null;
		}
		return selectByExample.get(0);
	}

	public Page<UserInfo> selectUserList(Integer page, Integer pageSize) {
		
		PageHelper.startPage(page, pageSize);
		UserInfoExample example = new UserInfoExample();
		
		
		return  (Page<UserInfo>)userInfoMapper.selectByExample(example); 
	}


	public boolean update(UserInfo userInfo) {
		int i = userInfoMapper.updateByPrimaryKeySelective(userInfo);
		if(i==0){
			return false;
		}{
			return true;
		}
	}

	public UserInfo selectUserById(Long id) {
		
		return userInfoMapper.selectByPrimaryKey(id);
	}

}
