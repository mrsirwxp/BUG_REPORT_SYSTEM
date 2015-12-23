package com.service;

import java.util.List;
import java.util.Map;

import com.pojo.Bug;
import com.pojo.Comment;
import com.pojo.Project;
import com.pojo.User;

public interface IBugsService {
	public Boolean addbug(Bug bug);
	public List<Bug> selectAllBugs(String start,String end);
	public Boolean updateBug(String status,String bugId);
	public Boolean updateBugProcessUser(String bugid,String uid);
	public List<Project> selectProjects();
	public List<User> selectUsers();
	public Bug selectBugByID(String bugid);
	public Boolean addcomment(Comment comment);
	
	public String bugToMe(String uid);
	public String mySubmitBug(String uid);
	public String stillNotSolvedBug();
	public String allSolvedBug();
	public String allBugCount();
	
	public List<Bug> selectbugToMe(String uid,String start,String end);
	public List<Bug> mySubmitBug(String uid,String start,String end);
	public List<Bug> stillNotSolvedBug(String start,String end);
	public List<Bug> allSolvedBug(String start,String end);
	public Map<String, Object> getCount(String uid);
	
	public Map<String,String>  addPro(String proName,String proDesc);
	public void  activePro(String id,String status);
}
