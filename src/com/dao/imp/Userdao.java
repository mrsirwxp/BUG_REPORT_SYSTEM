package com.dao.imp;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.dao.IUserdao;
import com.pojo.User;
import com.util.CMSUtil;
import com.util.DBControl;

public class Userdao  {/*

	
	public List<User> userLogin(Connection connection, String uname, String pwd) throws Exception {
		// TODO Auto-generated method stub
		StringBuffer sql=new StringBuffer("select *  from USERS u where u.userid=? and u.pswd=? ");
		Object object[] ={uname,pwd};
		List<Map<String, Object>> list =DBControl.select(connection, sql.toString(), object);
		List<User> users=null;
		if (list!=null&&list.size()==1) {
			users=new ArrayList<User>();
			User user=new User();
			Map<String, Object> map = list.get(0);
			user.setId(CMSUtil.stringFormat(map.get("USERID")));
			user.setName(CMSUtil.stringFormat(map.get("UNAME")));
			user.setCreatetime(CMSUtil.stringFormat(map.get("CREATETIME")));
			user.setImgsrc(CMSUtil.stringFormat(map.get("IMGSRC")));
			user.setEmail(CMSUtil.stringFormat(map.get("EMAIL")));
			users.add(user);
		}
		return users;
	}

	
	public String adduser(Connection connection,String uname,String pwd,String email,String juse) throws Exception  {
		// TODO Auto-generated method stub
		String key=null;
		String sql="insert into USERS (userid, uname, pswd, createtime, imgsrc, isdeveloper,email)  values (seq_users.nextval, ?, ?, to_char(sysdate,'yyyy-mm-dd hh24:mi:ss') , 'img/userhead.png', ?,?) ";
		Object object[] ={uname,pwd,juse,email};
		key=(String)DBControl.insert(connection, sql, object,new String[]{"USERID"});
		return key; 
	}

	
	public String uploadHeadImg(Connection connection, String uid,
			String headSrc) throws Exception {
		// TODO Auto-generated method stub
		String sql= "update users u set u.imgsrc=? where u.userid=? ";
		Object p[] = {headSrc,uid};
		DBControl.update(connection, sql, p);
		return "1";
	}

	
	public String upDateUInfo(Connection connection, String uid, String uname,
			String email, String newpswd) throws Exception {
		// TODO Auto-generated method stub
		String sql= "update users u set u.uname=?,u.pswd=?,u.email=? where u.userid=? ";
		Object p[] = {uname,newpswd,email,uid};
		DBControl.update(connection, sql, p);
		return "修改成功！";
	}

*/}
