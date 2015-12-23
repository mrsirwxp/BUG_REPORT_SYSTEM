package com.dao;

import java.util.List;
import java.util.Map;

import com.pojo.Bug;
import com.pojo.Comment;
import com.pojo.Images;
import com.pojo.Project;
import com.pojo.User;

public interface IBugDB {
	public String addBug(Bug bug) throws Exception ;
	
	public void addImage(String bugId,String src) throws Exception ;
	
	public List<Bug> showAllBugs(String start,String end) throws Exception ;
	
	public Bug selectBugByID(String bugId) throws Exception ;
	
	
	public void updateBugStatus(String bugId,String Status) throws Exception ;
	
	public void updateBugProcessUserid(String bugId,String processUserid) throws Exception ;
	
	public void addComment(String bugId,String content,String uid) throws Exception ;
	
	public List<Project> selectProjects() throws Exception ;
	public List<User> selectUsers() throws Exception ;
	
	
	public String bugToMe(String uid) throws Exception ;
	public String mySubmitBug(String uid) throws Exception ;
	public String stillNotSolvedBug() throws Exception ;
	public String allBugCount() throws Exception ;
	public String allSolvedBug() throws Exception ;
	
	
	public List<Bug> selectbugToMe(String uid,String start,String end) throws Exception ;
	public List<Bug> mySubmitBug(String uid,String start,String end) throws Exception ;
	public List<Bug> stillNotSolvedBug(String start,String end) throws Exception ;
	public List<Bug> allSolvedBug(String start,String end) throws Exception ;
	
	public Map<String,String>  addPro(String proName,String proDesc)throws Exception;
	public void activePro(String id,String status)throws Exception;
	
}
