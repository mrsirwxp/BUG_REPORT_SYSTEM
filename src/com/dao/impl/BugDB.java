package com.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.dao.IBugDB;
import com.pojo.Bug;
import com.pojo.Comment;
import com.pojo.Images;
import com.pojo.Page;
import com.pojo.Project;
import com.pojo.User;
import com.pojo.options.IBugOptions;
import com.util.DBMyBatisControl;

public class BugDB implements IBugDB {
	public static SqlSessionFactory sessionFactory = DBMyBatisControl.getSession();
	@Override
	public String addBug(Bug bug) throws Exception {
		// TODO Auto-generated method stub
		SqlSession session=sessionFactory.openSession();
		IBugOptions bugOptions=session.getMapper(IBugOptions.class);
		bugOptions.addBug(bug);
		System.out.println("bugid="+bug.getBugid());
		session.commit();
		session.close();
		return bug.getBugid();
	}
	

	@Override
	public void addImage(String bugId, String src) throws Exception {
		// TODO Auto-generated method stub
		SqlSession session=sessionFactory.openSession();
		IBugOptions bugOptions=session.getMapper(IBugOptions.class);
		
		Images images = new Images();
		images.setBugid(bugId);
		images.setImgsrc(src);
		bugOptions.addImage(images);
		session.commit();
		session.close();

	}

	@Override
	public List<Bug> showAllBugs(String start, String end) throws Exception {
		// TODO Auto-generated method stub
		SqlSession session=sessionFactory.openSession();
		IBugOptions bugOptions=session.getMapper(IBugOptions.class);
		Page page = new Page();
		page.setEnd(end);
		page.setStart(start);
		List<Bug> bugs=bugOptions.selectAllBugs(page);
		if (bugs!=null&&bugs.size()!=0) {
			for (int i = 0; i < bugs.size(); i++) {
				Bug bug=bugs.get(i);
				bug.setImage(bugOptions.selectImagesByBugID(bug.getBugid()));
				List<Comment> comments=bugOptions.selectCommentByBugID(bug.getBugid());
				if (comments!=null&&comments.size()!=0) {
					bug.setCommentNum(comments.size()+"");
				}else {
					bug.setCommentNum("0");
				}
				bug.setUser(bugOptions.selectUserByUID(bug.getCreateuserid()));
			}
		}
		session.close();
		return bugs;
	}

	@Override
	public Bug selectBugByID(String bugId) throws Exception {
		// TODO Auto-generated method stub
		SqlSession session=sessionFactory.openSession();
		IBugOptions bugOptions=session.getMapper(IBugOptions.class);
		Bug bug = bugOptions.selectBugByID(bugId);
		List<Comment> comments=bugOptions.selectCommentByBugID(bugId);
		bug.setImage(bugOptions.selectImagesByBugID(bugId));
		bug.setComment(comments);
		if (comments!=null&&comments.size()!=0) {
			bug.setCommentNum(comments.size()+"");
		}else {
			bug.setCommentNum("0");
		}
		bug.setUser(bugOptions.selectUserByUID(bug.getCreateuserid()));
		bug.setProject(bugOptions.selectProjectByID(bug.getProjectid()));
		bug.setProcessedUser(bugOptions.selectUserByUID(bug.getProcessuserid()));
		session.close();
		return bug;
	}



	@Override
	public void updateBugStatus(String bugId, String Status) throws Exception {
		// TODO Auto-generated method stub
		Bug bug =new Bug();
		bug.setBugid(bugId);
		bug.setStatus(Status);
		
		SqlSession session=sessionFactory.openSession();
		IBugOptions bugOptions=session.getMapper(IBugOptions.class);
		
		bugOptions.updateBugStatus(bug);
		
		session.commit();
		session.close();
		
	}

	@Override
	public void updateBugProcessUserid(String bugId, String processUserid)
			throws Exception {
		// TODO Auto-generated method stub
		Bug bug =new Bug();
		bug.setBugid(bugId);
		bug.setProjectid(processUserid);
		
		SqlSession session=sessionFactory.openSession();
		IBugOptions bugOptions=session.getMapper(IBugOptions.class);
		
		bugOptions.updateBugProcessUserid(bug);
		
		session.commit();
		session.close();
	}

	@Override
	public void addComment(String bugId, String content, String uid)
			throws Exception {
		// TODO Auto-generated method stub
		Comment comment =new Comment();
		comment.setBugid(bugId);
		comment.setContent(content);
		comment.setUserid(uid);
		
		SqlSession session=sessionFactory.openSession();
		IBugOptions bugOptions=session.getMapper(IBugOptions.class);
		
		bugOptions.addComment(comment);
		
		session.commit();
		session.close();

	}

	@Override
	public List<Project> selectProjects() throws Exception {
		// TODO Auto-generated method stub
		SqlSession session=sessionFactory.openSession();
		IBugOptions bugOptions=session.getMapper(IBugOptions.class);
		List<Project> projects=bugOptions.selectProjects();
		session.close();
		return projects;
	}

	@Override
	public List<User> selectUsers() throws Exception {
		// TODO Auto-generated method stub
		SqlSession session=sessionFactory.openSession();
		IBugOptions bugOptions=session.getMapper(IBugOptions.class);
		List<User> users=bugOptions.selectUsers();
		session.close();
		return users;
	}

	@Override
	public String bugToMe(String uid) throws Exception {
		// TODO Auto-generated method stub
		SqlSession session=sessionFactory.openSession();
		IBugOptions bugOptions=session.getMapper(IBugOptions.class);
		String count=bugOptions.bugToMeCount(uid);
		session.close();
		return count;
	}

	@Override
	public String mySubmitBug(String uid) throws Exception {
		// TODO Auto-generated method stub
		SqlSession session=sessionFactory.openSession();
		IBugOptions bugOptions=session.getMapper(IBugOptions.class);
		String count=bugOptions.mySubmitBugCount(uid);
		session.close();
		return count;
	}

	@Override
	public String stillNotSolvedBug() throws Exception {
		// TODO Auto-generated method stub
		SqlSession session=sessionFactory.openSession();
		IBugOptions bugOptions=session.getMapper(IBugOptions.class);
		String count=bugOptions.stillNotSolvedBugCount();
		session.close();
		return count;
	}

	@Override
	public String allBugCount() throws Exception {
		// TODO Auto-generated method stub
		SqlSession session=sessionFactory.openSession();
		IBugOptions bugOptions=session.getMapper(IBugOptions.class);
		String count = bugOptions.allBugCount();
		session.close();
		return count;
	}

	@Override
	public String allSolvedBug() throws Exception {
		// TODO Auto-generated method stub
		SqlSession session=sessionFactory.openSession();
		IBugOptions bugOptions=session.getMapper(IBugOptions.class);
		String count = bugOptions.allSolvedBugCount();
		session.close();
		return count;
	}

	@Override
	public List<Bug> selectbugToMe(String uid, String start, String end)
			throws Exception {
		// TODO Auto-generated method stub
		SqlSession session=sessionFactory.openSession();
		IBugOptions bugOptions=session.getMapper(IBugOptions.class);
		
		Page page=new Page();
		page.setEnd(end);
		page.setStart(start);
		page.setUid(uid);
		
		List<Bug> bugs =bugOptions.selectbugToMe(page);
		if (bugs!=null&&bugs.size()!=0) {
			for (int i = 0; i < bugs.size(); i++) {
				Bug bug=bugs.get(i);
				List<Comment> comments=bugOptions.selectCommentByBugID(bug.getBugid());
				if (comments!=null&&comments.size()!=0) {
					bug.setCommentNum(comments.size()+"");
				}else {
					bug.setCommentNum("0");
				}
				bug.setImage(bugOptions.selectImagesByBugID(bug.getBugid()));
				bug.setUser(bugOptions.selectUserByUID(bug.getCreateuserid()));
			}
		}
		session.close();
		return bugs;
	}

	@Override
	public List<Bug> mySubmitBug(String uid, String start, String end)
			throws Exception {
		// TODO Auto-generated method stub
		SqlSession session=sessionFactory.openSession();
		IBugOptions bugOptions=session.getMapper(IBugOptions.class);
		
		Page page=new Page();
		page.setEnd(end);
		page.setStart(start);
		page.setUid(uid);
		
		List<Bug> bugs =bugOptions.mySubmitBug(page);
		if (bugs!=null&&bugs.size()!=0) {
			for (int i = 0; i < bugs.size(); i++) {
				Bug bug=bugs.get(i);
				List<Comment> comments=bugOptions.selectCommentByBugID(bug.getBugid());
				if (comments!=null&&comments.size()!=0) {
					bug.setCommentNum(comments.size()+"");
				}else {
					bug.setCommentNum("0");
				}
				bug.setImage(bugOptions.selectImagesByBugID(bug.getBugid()));
				bug.setUser(bugOptions.selectUserByUID(bug.getCreateuserid()));
			}
		}
		session.close();
		return bugs;
	}

	@Override
	public List<Bug> stillNotSolvedBug(String start, String end)
			throws Exception {
		// TODO Auto-generated method stub
		SqlSession session=sessionFactory.openSession();
		IBugOptions bugOptions=session.getMapper(IBugOptions.class);
		
		Page page=new Page();
		page.setEnd(end);
		page.setStart(start);
		
		List<Bug> bugs =bugOptions.stillNotSolvedBug(page);
		if (bugs!=null&&bugs.size()!=0) {
			for (int i = 0; i < bugs.size(); i++) {
				Bug bug=bugs.get(i);
				List<Comment> comments=bugOptions.selectCommentByBugID(bug.getBugid());
				if (comments!=null&&comments.size()!=0) {
					bug.setCommentNum(comments.size()+"");
				}else {
					bug.setCommentNum("0");
				}
				bug.setImage(bugOptions.selectImagesByBugID(bug.getBugid()));
				bug.setUser(bugOptions.selectUserByUID(bug.getCreateuserid()));
			}
		}
		session.close();
		return bugs;
	}

	@Override
	public List<Bug> allSolvedBug(String start, String end) throws Exception {
		// TODO Auto-generated method stub
		SqlSession session=sessionFactory.openSession();
		IBugOptions bugOptions=session.getMapper(IBugOptions.class);
		
		Page page=new Page();
		page.setEnd(end);
		page.setStart(start);
		
		List<Bug> bugs =bugOptions.allSolvedBug(page);
		if (bugs!=null&&bugs.size()!=0) {
			for (int i = 0; i < bugs.size(); i++) {
				Bug bug=bugs.get(i);
				List<Comment> comments=bugOptions.selectCommentByBugID(bug.getBugid());
				if (comments!=null&&comments.size()!=0) {
					bug.setCommentNum(comments.size()+"");
				}else {
					bug.setCommentNum("0");
				}
				bug.setImage(bugOptions.selectImagesByBugID(bug.getBugid()));
				bug.setUser(bugOptions.selectUserByUID(bug.getCreateuserid()));
			}
		}
		session.close();
		return bugs;
	}

	@Override
	public Map<String, String> addPro(String proName, String proDesc)
			throws Exception {
		// TODO Auto-generated method stub
		SqlSession session=sessionFactory.openSession();
		IBugOptions bugOptions=session.getMapper(IBugOptions.class);
		
		Project project = new Project();
		project.setDescription(proDesc);
		project.setPro_name(proName);
		
		String key=bugOptions.addPro(project);
		String count=bugOptions.selectProCount();
		
		Map<String , String > map=new HashMap<String, String>();
		if(key!=null){
			map.put("proId", project.getId());
		}else{
			map.put("proId", "error");
		}
		
		map.put("count", count);
		session.close();
		return null;
	}

	@Override
	public void activePro(String id, String status) throws Exception {
		// TODO Auto-generated method stub
		SqlSession session=sessionFactory.openSession();
		IBugOptions bugOptions=session.getMapper(IBugOptions.class);
		
		Project project = new Project();
		project.setId(id);
		project.setIslive(status);
		
		bugOptions.activePro(project);
		
		session.commit();
		session.close();

	}

}
