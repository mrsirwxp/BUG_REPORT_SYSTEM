package com.pojo.options;


import java.util.List;

import com.pojo.Bug;
import com.pojo.Comment;
import com.pojo.Images;
import com.pojo.Page;
import com.pojo.Project;
import com.pojo.User;


public interface IBugOptions {
	public int addBug(Bug bug);
	public void addImage(Images images);
	public List<Bug> selectAllBugs(Page page);
	public List<Comment> selectCommentByBugID(String bugid);
	public List<Images> selectImagesByBugID(String bugid);
	public User selectUserByUID(String uid);
	public Bug selectBugByID(String bugid);
	public void updateBugStatus(Bug bug);
	public void updateBugProcessUserid(Bug bug);
	
	public void addComment(Comment comment);
	public List<Project> selectProjects();
	public Project selectProjectByID(String proId);
	public List<User> selectUsers();
	public String bugToMeCount(String uid);
	public String mySubmitBugCount(String uid);
	public String stillNotSolvedBugCount();
	public String allBugCount();
	public String allSolvedBugCount();
	
	public List<Bug> selectbugToMe(Page page);
	public List<Bug> mySubmitBug(Page page);
	public List<Bug> stillNotSolvedBug(Page page);
	public List<Bug> allSolvedBug(Page page);
	public String addPro(Project project);
	public String selectProCount();
	public void activePro(Project project);
	
}
