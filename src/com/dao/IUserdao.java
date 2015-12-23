package com.dao;

import java.sql.Connection;
import java.util.List;

import com.pojo.User;

public interface IUserdao {
	public List<User> userLogin(Connection connection,String uname,String pwd) throws Exception ;
	public String adduser(Connection connection,String uname,String pwd,String email,String juse) throws Exception ;
	public String uploadHeadImg(Connection connection,String uid, String headSrc) throws Exception ;
	public String upDateUInfo(Connection connection,String uid,String uname,String email,String newpswd) throws Exception ;
}
