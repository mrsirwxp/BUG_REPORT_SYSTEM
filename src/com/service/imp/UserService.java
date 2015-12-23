package com.service.imp;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.dao.IUserDB;
import com.pojo.User;
import com.service.IUserService;

public class UserService implements IUserService {
	
	private IUserDB iUserdao;
	
	public void setiUserdao(IUserDB iUserdao) {
		this.iUserdao = iUserdao;
	}
	private static  Logger logger = Logger.getLogger(UserService.class);
	
	@Override
	public Boolean userLogin(HttpServletRequest request,String uname, String pwd) {
		// TODO Auto-generated method stub
		
		List<User> users=null;
		try {
			users=iUserdao.userLogin( uname, pwd);
		} catch (Exception e) {
			logger.error("登录出错！", e);
		}finally{
			
		}
		if (users!=null&&users.size()==1) {
			User user=users.get(0);
			logger.info("用户"+user.getUname()+"登录系统！");
			request.getSession().setAttribute("loginUserInfo",user);
			request.getSession().setAttribute("isOnLine","1");
			return true;
		}
		return false;
	}
	@Override
	public String adduser(String uname, String pwd, String email, String juse) {
		// TODO Auto-generated method stub
		
		String res=null;
		try {
			res=iUserdao.adduser( uname, pwd, email, juse);
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("添加用户出错！"+e.getMessage(), e);
		}
		return res;
	}
	@Override
	public String uploadHeadImg(String uid, String headSrc) {
		// TODO Auto-generated method stub
		
		String res=null;
		try {
			res=iUserdao.uploadHeadImg( uid, headSrc);
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("更新用户头像出错！"+e.getMessage(), e);
		}
		return res;
	}
	@Override
	public String upDateUInfo(String uid, String uname, String email,
			String newpswd) {
		// TODO Auto-generated method stub
		
		String res=null;
		try {
			res=iUserdao.upDateUInfo(uid, uname,email,newpswd);
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("更新用户头像出错！"+e.getMessage(), e);
		}
		return res;
	}


}
