package com.dao;

import java.util.List;

import com.pojo.User;

public interface IUserDB {
	public List<User> userLogin(String uname,String pwd) throws Exception ;
	public String adduser(String uname,String pwd,String email,String juse) throws Exception ;
	public String uploadHeadImg(String uid, String headSrc) throws Exception ;
	public String upDateUInfo(String uid,String uname,String email,String newpswd) throws Exception ;
}
