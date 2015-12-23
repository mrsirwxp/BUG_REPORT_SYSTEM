package com.dao;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import com.pojo.Bug;
import com.pojo.Comment;
import com.pojo.Images;
import com.pojo.Project;
import com.pojo.User;

public interface IBugsDao {
	public String addBug(Connection connection,Bug bug) throws Exception ;
	
	public void addImage(Connection connection,String bugId,String src) throws Exception ;
	
	public List<Bug> showAllBugs(Connection connection,String start,String end) throws Exception ;
	
	public Bug selectBugByID(Connection connection,String bugId) throws Exception ;
	
	public List<Bug> selectBugByUID(Connection connection,String UserId,String start,String end) throws Exception ;
	
	public List<Bug> selectBugByUidAndStatus(Connection connection,String UserId,String Status,String start,String end) throws Exception ;
	
	public List<Bug> selectBugByStatus(Connection connection,String Status,String start,String end) throws Exception ;
	
	
	public User selectBugByUID(Connection connection,String UId) throws Exception ;
	public List<Comment> selectCommentByBugID(Connection connection,String bugId) throws Exception ;
	public List<Images> selectImagesByBugID(Connection connection,String bugId) throws Exception ;
	
	public void updateBugStatus(Connection connection,String bugId,String Status) throws Exception ;
	
	public void updateBugProcessUserid(Connection connection,String bugId,String processUserid) throws Exception ;
	
	public void addComment(Connection connection,String bugId,String content,String uid) throws Exception ;
	
	public List<Project> selectProjects(Connection connection) throws Exception ;
	public List<User> selectUsers(Connection connection) throws Exception ;
	
	
	public String bugToMe(Connection connection,String uid) throws Exception ;
	public String mySubmitBug(Connection connection,String uid) throws Exception ;
	public String stillNotSolvedBug(Connection connection) throws Exception ;
	public String allBugCount(Connection connection) throws Exception ;
	public String allSolvedBug(Connection connection) throws Exception ;
	
	
	public List<Bug> selectbugToMe(Connection connection,String uid,String start,String end) throws Exception ;
	public List<Bug> mySubmitBug(Connection connection,String uid,String start,String end) throws Exception ;
	public List<Bug> stillNotSolvedBug(Connection connection,String start,String end) throws Exception ;
	public List<Bug> allSolvedBug(Connection connection,String start,String end) throws Exception ;
	
	public Map<String,String>  addPro(Connection connection,String proName,String proDesc)throws Exception;
	public void activePro(Connection connection,String id,String status)throws Exception;
	
}
