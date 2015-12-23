package com.service;

import javax.servlet.http.HttpServletRequest;

public interface IUserService {
	public Boolean userLogin(HttpServletRequest request,String uname,String pwd);
	public String adduser(String uname,String pwd,String email,String juse);
	public String uploadHeadImg(String uid,String headSrc);
	public String upDateUInfo(String uid,String uname,String email,String newpswd);
}
