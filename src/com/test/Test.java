package com.test;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.dao.impl.BugDB;
import com.pojo.Bug;

public class Test implements Controller {

	@Override
	public ModelAndView handleRequest(HttpServletRequest arg0,
			HttpServletResponse arg1) throws Exception {
		// TODO Auto-generated method stub
		
		ModelAndView view =new ModelAndView("NewFile");
		return view;
	}
	
	public static void main(String[] args) throws Exception {
		BugDB bugDB =new BugDB();
		Bug bug =new Bug();
		bug.setTitle("21212");
		bug.setDescription("asdasd");
		bug.setCreateuserid("10002");
		bug.setProjectid("100");
		bug.setOccurtime("2015-12-18 15:41:19");
		bug.setLeavel("1");
		bug.setProcessuserid("10003");
		String key = bugDB.addBug(bug);
		System.out.println(bug.getBugid());
	}

}
