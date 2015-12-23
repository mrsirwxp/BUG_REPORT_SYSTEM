package com.dao.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.dao.IUserDB;
import com.pojo.User;
import com.pojo.options.IUserOptions;
import com.util.DBMyBatisControl;

public class UserDB implements IUserDB {

	@Override
	public List<User> userLogin(String uname, String pwd) throws Exception {
		// TODO Auto-generated method stub
		User user = new User();
		user.setUserid(uname);
		user.setPswd(pwd);
		SqlSessionFactory sessionFactory = DBMyBatisControl.getSession();
		SqlSession session=sessionFactory.openSession();
		IUserOptions options =session.getMapper(IUserOptions.class);
		List<User> users=options.userLogin(user);
		session.close();
		return users;
	}

	@Override
	public String adduser(String uname, String pwd, String email, String juse)
			throws Exception {
		// TODO Auto-generated method stub
		
		User user = new User();
		user.setUname(uname);
		user.setPswd(pwd);
		user.setEmail(email);
		user.setIsdeveloper(juse);
		
		SqlSessionFactory sessionFactory = DBMyBatisControl.getSession();
		SqlSession session=sessionFactory.openSession();
		IUserOptions options =session.getMapper(IUserOptions.class);
		
		
		options.adduser(user);
		session.commit();
		session.close();
		
		return user.getUserid();
	}

	@Override
	public String uploadHeadImg(String uid, String headSrc) throws Exception {
		// TODO Auto-generated method stub
		
		User user = new User();
		user.setUserid(uid);
		user.setImgsrc(headSrc);
		
		SqlSessionFactory sessionFactory = DBMyBatisControl.getSession();
		SqlSession session=sessionFactory.openSession();
		IUserOptions options =session.getMapper(IUserOptions.class);
		options.uploadHeadImg(user);
		session.commit();
		session.close();
		
		return "1";
	}

	@Override
	public String upDateUInfo(String uid, String uname, String email,
			String newpswd) throws Exception {
		// TODO Auto-generated method stub
		
		User user = new User();
		user.setUserid(uid);
		user.setUname(uname);
		user.setEmail(email);
		user.setPswd(newpswd);
		
		SqlSessionFactory sessionFactory = DBMyBatisControl.getSession();
		SqlSession session=sessionFactory.openSession();
		IUserOptions options =session.getMapper(IUserOptions.class);
		options.upDateUInfo(user);
		session.commit();
		session.close();
		
		return "修改成功！";
	}

}
