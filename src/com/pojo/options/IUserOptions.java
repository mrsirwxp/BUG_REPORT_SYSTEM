package com.pojo.options;

import java.util.List;

import com.pojo.User;

public interface IUserOptions {
	public List<User> userLogin(User user);
	public String adduser(User user);
	public void uploadHeadImg(User user);
	public void upDateUInfo(User user);
	
	
	
}
