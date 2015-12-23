package com.dao.imp;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.dao.IBugsDao;
import com.pojo.Bug;
import com.pojo.Comment;
import com.pojo.Images;
import com.pojo.Project;
import com.pojo.User;
import com.util.CMSUtil;
import com.util.DBControl;

public class BugsDao  {/*

	
	public String addBug(Connection connection, Bug bug) throws Exception {
		// TODO Auto-generated method stub
		String key=null;
		StringBuffer sql=new StringBuffer(
				"insert into BUGS(BUGID,TITLE,DESCRIPTION,CREATEUSERID,"
				+ "CREATETIME,PROCESSUSERID,PROJECTID,STATUS,OCCURTIME,LEAVEL)"
				+ " values(seq_bugs.nextval,?,?,?,to_char(sysdate,'yyyy-mm-dd hh24:mi:ss'),?,?,'1',?,?) ");
		Object object[] ={bug.getTitle(),bug.getDescription(),bug.getUserid(),
				bug.getProcessedUid(),bug.getProjectId(),bug.getOccurtime(),bug.getLeave()};
		key=(String)DBControl.insert(connection, sql.toString(), object,new String[]{"BUGID"});
		return key;
	}

	
	public void addImage(Connection connection, String bugId, String src)
			throws Exception {
		// TODO Auto-generated method stub
		StringBuffer sql=new StringBuffer(
				"insert into IMAGE(IMGID,BUGID,IMGSRC) values(seq_imgs.nextval,?,?) ");
		Object object[] ={bugId,src};
		DBControl.insert(connection, sql.toString(), object,new String[]{"IMGID"});
	}

	
	public List<Bug> showAllBugs(Connection connection, String start, String end)
			throws Exception {
		// TODO Auto-generated method stub
		List<Bug> bugs=null;
		StringBuffer sql=new StringBuffer("select * from  (select rownum dd,m.* from (select t.*   from bugs t order by t.bugid desc) m) n where n.dd>=? and n.dd<?");
		Object object[] ={start,end};
		List<Map<String, Object>> list=DBControl.select(connection, sql.toString(), object);
		if (list!=null&&list.size()!=0) {
			bugs=new ArrayList<Bug>();
			for (int i = 0; i < list.size(); i++) {
				Bug bug =new Bug();
				bug.setId(list.get(i).get("BUGID").toString());
				bug.setTitle(list.get(i).get("TITLE").toString());
				bug.setDescription(list.get(i).get("DESCRIPTION").toString());
				bug.setUserid(list.get(i).get("CREATEUSERID").toString());
				bug.setCreatetime(list.get(i).get("CREATETIME").toString());
				bug.setProcessedUid(list.get(i).get("PROCESSUSERID").toString());
				bug.setProjectId(list.get(i).get("PROJECTID").toString());
				bug.setStatus(list.get(i).get("STATUS").toString());
				bug.setOccurtime(list.get(i).get("OCCURTIME").toString());
				bug.setLeave(list.get(i).get("LEAVEL").toString());
				
				List<Comment> comments=selectCommentByBugID(connection, list.get(i).get("BUGID").toString());
				bug.setImage(selectImagesByBugID(connection, list.get(i).get("BUGID").toString()));
//				bug.setComment(comments);
				if (comments!=null&&comments.size()!=0) {
					bug.setCommentNum(comments.size()+"");
				}else {
					bug.setCommentNum("0");
				}
				bug.setUser(selectBugByUID(connection, list.get(i).get("CREATEUSERID").toString()));
				System.out.println("评论数"+bug.getCommentNum());
				bugs.add(bug);
			}
		}
		return bugs;
	}

	
	public Bug selectBugByID(Connection connection, String bugId)
			throws Exception {
		// TODO Auto-generated method stub
		String sql="select * from BUGS t where t.bugid =? ";
		Bug bug =null;
		List<Map<String,Object>> list=DBControl.select(connection, sql, new String[]{bugId});
		if (list!=null&&list.size()!=0) {
			List<Comment> comments =selectCommentByBugID(connection, bugId);
			List<Images> images=selectImagesByBugID(connection, bugId);
			bug=new Bug();
			bug.setComment(comments);
			if (comments!=null&&comments.size()!=0) {
				bug.setCommentNum(comments.size()+"");
			}else {
				bug.setCommentNum("0");
			}
			bug.setCreatetime(list.get(0).get("CREATETIME").toString());
			bug.setDescription(list.get(0).get("DESCRIPTION").toString());
			bug.setId(bugId);
			bug.setImage(images);
			bug.setTitle(list.get(0).get("TITLE").toString());
			bug.setUserid(list.get(0).get("CREATEUSERID").toString());
			bug.setProcessedUid(list.get(0).get("PROCESSUSERID").toString());
			bug.setProjectId(list.get(0).get("PROJECTID").toString());
			bug.setStatus(list.get(0).get("STATUS").toString());
			bug.setOccurtime(list.get(0).get("OCCURTIME").toString());
			bug.setLeave(list.get(0).get("LEAVEL").toString());
			bug.setUser(selectBugByUID(connection, list.get(0).get("CREATEUSERID").toString()));
			bug.setProcessedUser(selectBugByUID(connection, list.get(0).get("PROCESSUSERID").toString()));
			bug.setProject(selectProjectByID(connection, list.get(0).get("PROJECTID").toString()));
			
		}
		return bug;
	}

	
	public List<Bug> selectBugByUID(Connection connection, String UserId,
			String start, String end) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	
	public List<Bug> selectBugByUidAndStatus(Connection connection,
			String UserId, String Status, String start, String end)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	
	public List<Bug> selectBugByStatus(Connection connection, String Status,
			String start, String end) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	
	public User selectBugByUID(Connection connection, String UId)
			throws Exception {
		// TODO Auto-generated method stub
		User user =null;
		String sql="select * from USERS t where t.userid=?";
		Object args[]={UId};
		List<Map<String, Object>> list =DBControl.select(connection, sql, args);
		if (list!=null&&list.size()!=0) {
			user=new User();
			user.setId(UId);
			user.setImgsrc(list.get(0).get("IMGSRC").toString());
			user.setName(list.get(0).get("UNAME").toString());
		}
		return user;
	}

	
	public List<Comment> selectCommentByBugID(Connection connection,
			String bugId) throws Exception {
		// TODO Auto-generated method stub
		List<Comment> comments =null;
		String sql="select * from COMM t where t.bugid=?";
		Object args[]={bugId};
		List<Map<String, Object>> list =DBControl.select(connection, sql, args);
		if (list!=null&&list.size()!=0) {
			comments=new ArrayList<Comment>();
			for (int i = 0; i < list.size(); i++) {
				Comment comment =new Comment();
				
				comment.setBugid(bugId);
				comment.setContent(list.get(i).get("CONTENT").toString());
				comment.setId(list.get(i).get("COMMENTID").toString());
				comment.setTime(list.get(i).get("TIME").toString());
				comment.setUid(list.get(i).get("USERID").toString());
				
				comments.add(comment);
			}
		}
		return comments;
	}

	
	public List<Images> selectImagesByBugID(Connection connection, String bugId)
			throws Exception {
		// TODO Auto-generated method stub
		List<Images> imgs =null;
		String sql="select * from IMAGE t where t.bugid=?";
		Object args[]={bugId};
		List<Map<String, Object>> list =DBControl.select(connection, sql, args);
		if (list!=null&&list.size()!=0) {
			imgs=new ArrayList<Images>();
			for (int i = 0; i < list.size(); i++) {
				Images img =new Images();
				
				img.setBugid(bugId);
				img.setId(list.get(i).get("IMGID").toString());
				img.setSrc(list.get(i).get("IMGSRC").toString());
				
				imgs.add(img);
			}
		}
		return imgs;
	}

	
	public void updateBugStatus(Connection connection, String bugId,
			String Status) throws Exception {
		// TODO Auto-generated method stub
		String sql="update bugs b set b.status=? where b.bugid=? ";
		Object[] args={Status,bugId};
		DBControl.update(connection, sql, args);
	}

	
	public void updateBugProcessUserid(Connection connection, String bugId,
			String processUserid) throws Exception {
		// TODO Auto-generated method stub
		String sql="update bugs b set b.processuserid=? where b.bugid=? ";
		Object[] args={processUserid,bugId};
		DBControl.update(connection, sql, args);
	}

	
	public void addComment(Connection connection, String bugId, String content,
			String uid) throws Exception {
		// TODO Auto-generated method stub
		String sql= "insert into COMM(COMMENTID,CONTENT,TIME,USERID,BUGID) values(seq_comms.nextval,?,to_char(sysdate,'yyyy-mm-dd hh24:mi:ss'),?,?) ";
		Object[] p={content,uid,bugId};
		DBControl.insert(connection, sql, p, new String[]{"COMMENTID"});
	}

	
	public List<Project> selectProjects(Connection connection) throws Exception {
		// TODO Auto-generated method stub
		List<Project> projects=null;
		String sql="select * from PROJECT ";
		List<Map<String,Object>> list=DBControl.select(connection, sql, null);
		if (list!=null&&list.size()!=0) {
			projects=new ArrayList<Project>();
			for (int i = 0; i <list.size(); i++) {
				Project project=new Project();
				project.setId(list.get(i).get("ID").toString());
				project.setName(list.get(i).get("PRO_NAME").toString());
				project.setDescription(CMSUtil.stringFormat(list.get(i).get("DESCRIPTION"), "尚未录入！"));
				project.setIslive(list.get(i).get("ISLIVE").toString());
				projects.add(project);
			}
		}
		return projects;
	}
	
	public Project selectProjectByID(Connection connection , String pid) throws Exception {
		// TODO Auto-generated method stub
		Project project=null;
		String sql="select * from PROJECT t where t.id=? ";
		List<Map<String,Object>> list=DBControl.select(connection, sql, new String[]{pid});
		if (list!=null&&list.size()!=0) {
			project=new Project();
			project.setId(list.get(0).get("ID").toString());
			project.setName(list.get(0).get("PRO_NAME").toString());
		}
		return project;
	}

	
	public List<User> selectUsers(Connection connection) throws Exception {
		// TODO Auto-generated method stub  select t.userid,t.uname from USERS t where t.isdeveloper = '1'
		List<User> users=null;
		String sql="select t.userid,t.uname ,t.imgsrc from USERS t where t.isdeveloper = '1' ";
		List<Map<String,Object>> list=DBControl.select(connection, sql, null);
		if (list!=null&&list.size()!=0) {
			users=new ArrayList<User>();
			for (int i = 0; i <list.size(); i++) {
				User user=new User();
				user.setId(list.get(i).get("USERID").toString());
				user.setName(list.get(i).get("UNAME").toString());
				user.setImgsrc(list.get(i).get("IMGSRC").toString());
				users.add(user);
			}
		}
		return users;
	}

	
	public String bugToMe(Connection connection, String uid) throws Exception {
		// TODO Auto-generated method stub
		String sql="select count(distinct(t.bugid)) c from BUGS t where (t.processuserid=? and t.status in ('1','4','5'))  or (t.createuserid=? and t.status ='2')";
		Object p[]={uid,uid};
		List<Map<String,Object>> list=DBControl.select(connection, sql, p);
		return list.get(0).get("C").toString();
	}

	
	public String mySubmitBug(Connection connection, String uid)
			throws Exception {
		// TODO Auto-generated method stub
		String sql="select count(distinct(t.bugid)) c from BUGS t where t.createuserid=? ";
		Object p[]={uid};
		List<Map<String,Object>> list=DBControl.select(connection, sql, p);
		return list.get(0).get("C").toString();
	}

	
	public String stillNotSolvedBug(Connection connection) throws Exception {
		// TODO Auto-generated method stub
		String sql="select count(distinct(t.bugid)) c from BUGS t where t.status!='3'";
		List<Map<String,Object>> list=DBControl.select(connection, sql, null);
		return list.get(0).get("C").toString();
	}

	
	public String allBugCount(Connection connection) throws Exception {
		// TODO Auto-generated method stub
		String sql="select count(distinct(t.bugid)) c from BUGS t ";
		List<Map<String,Object>> list=DBControl.select(connection, sql, null);
		return list.get(0).get("C").toString();
	}

	
	public String allSolvedBug(Connection connection) throws Exception {
		// TODO Auto-generated method stub
		String sql="select count(distinct(t.bugid)) c from BUGS t where t.status='3'";
		List<Map<String,Object>> list=DBControl.select(connection, sql, null);
		return list.get(0).get("C").toString();
	}

	
	public List<Bug> selectbugToMe(Connection connection, String uid,
			String start, String end) throws Exception {
		// TODO Auto-generated method stub
		List<Bug> bugs=null;
		StringBuffer sql=new StringBuffer("select * from  (select rownum dd,m.* from (select  t.*   from BUGS t where (t.processuserid=? and t.status in ('1','4','5'))  or (t.createuserid=? and t.status ='2') order by t.bugid desc) m) n where n.dd>=? and n.dd<?");
		Object object[] ={uid,uid,start,end};
		List<Map<String, Object>> list=DBControl.select(connection, sql.toString(), object);
		if (list!=null&&list.size()!=0) {
			bugs=new ArrayList<Bug>();
			for (int i = 0; i < list.size(); i++) {
				Bug bug =new Bug();
				bug.setId(list.get(i).get("BUGID").toString());
				bug.setTitle(list.get(i).get("TITLE").toString());
				bug.setDescription(list.get(i).get("DESCRIPTION").toString());
				bug.setUserid(list.get(i).get("CREATEUSERID").toString());
				bug.setCreatetime(list.get(i).get("CREATETIME").toString());
				bug.setProcessedUid(list.get(i).get("PROCESSUSERID").toString());
				bug.setProjectId(list.get(i).get("PROJECTID").toString());
				bug.setStatus(list.get(i).get("STATUS").toString());
				bug.setOccurtime(list.get(i).get("OCCURTIME").toString());
				bug.setLeave(list.get(i).get("LEAVEL").toString());
				
				List<Comment> comments=selectCommentByBugID(connection, list.get(i).get("BUGID").toString());
				bug.setImage(selectImagesByBugID(connection, list.get(i).get("BUGID").toString()));
//				bug.setComment(comments);
				if (comments!=null&&comments.size()!=0) {
					bug.setCommentNum(comments.size()+"");
				}else {
					bug.setCommentNum("0");
				}
				bug.setUser(selectBugByUID(connection, list.get(i).get("CREATEUSERID").toString()));
				System.out.println("评论数"+bug.getCommentNum());
				bugs.add(bug);
			}
		}
		return bugs;
	}

	
	public List<Bug> mySubmitBug(Connection connection, String uid,
			String start, String end) throws Exception {
		// TODO Auto-generated method stub
		List<Bug> bugs=null;
		StringBuffer sql=new StringBuffer("select * from  (select rownum dd,m.* from (select t.*   from BUGS t where t.createuserid=?  order by t.bugid desc) m) n where n.dd>=? and n.dd<?");
		Object object[] ={uid,start,end};
		List<Map<String, Object>> list=DBControl.select(connection, sql.toString(), object);
		if (list!=null&&list.size()!=0) {
			bugs=new ArrayList<Bug>();
			for (int i = 0; i < list.size(); i++) {
				Bug bug =new Bug();
				bug.setId(list.get(i).get("BUGID").toString());
				bug.setTitle(list.get(i).get("TITLE").toString());
				bug.setDescription(list.get(i).get("DESCRIPTION").toString());
				bug.setUserid(list.get(i).get("CREATEUSERID").toString());
				bug.setCreatetime(list.get(i).get("CREATETIME").toString());
				bug.setProcessedUid(list.get(i).get("PROCESSUSERID").toString());
				bug.setProjectId(list.get(i).get("PROJECTID").toString());
				bug.setStatus(list.get(i).get("STATUS").toString());
				bug.setOccurtime(list.get(i).get("OCCURTIME").toString());
				bug.setLeave(list.get(i).get("LEAVEL").toString());
				
				List<Comment> comments=selectCommentByBugID(connection, list.get(i).get("BUGID").toString());
				bug.setImage(selectImagesByBugID(connection, list.get(i).get("BUGID").toString()));
//				bug.setComment(comments);
				if (comments!=null&&comments.size()!=0) {
					bug.setCommentNum(comments.size()+"");
				}else {
					bug.setCommentNum("0");
				}
				bug.setUser(selectBugByUID(connection, list.get(i).get("CREATEUSERID").toString()));
				System.out.println("评论数"+bug.getCommentNum());
				bugs.add(bug);
			}
		}
		return bugs;
	}

	
	public List<Bug> stillNotSolvedBug(Connection connection, String start,
			String end) throws Exception {
		// TODO Auto-generated method stub
		List<Bug> bugs=null;
		StringBuffer sql=new StringBuffer("select * from  (select rownum dd,m.* from (select t.*   from BUGS t where t.status!='3'  order by t.bugid desc) m) n where n.dd>=? and n.dd<?");
		Object object[] ={start,end};
		List<Map<String, Object>> list=DBControl.select(connection, sql.toString(), object);
		if (list!=null&&list.size()!=0) {
			bugs=new ArrayList<Bug>();
			for (int i = 0; i < list.size(); i++) {
				Bug bug =new Bug();
				bug.setId(list.get(i).get("BUGID").toString());
				bug.setTitle(list.get(i).get("TITLE").toString());
				bug.setDescription(list.get(i).get("DESCRIPTION").toString());
				bug.setUserid(list.get(i).get("CREATEUSERID").toString());
				bug.setCreatetime(list.get(i).get("CREATETIME").toString());
				bug.setProcessedUid(list.get(i).get("PROCESSUSERID").toString());
				bug.setProjectId(list.get(i).get("PROJECTID").toString());
				bug.setStatus(list.get(i).get("STATUS").toString());
				bug.setOccurtime(list.get(i).get("OCCURTIME").toString());
				bug.setLeave(list.get(i).get("LEAVEL").toString());
				
				List<Comment> comments=selectCommentByBugID(connection, list.get(i).get("BUGID").toString());
				bug.setImage(selectImagesByBugID(connection, list.get(i).get("BUGID").toString()));
//				bug.setComment(comments);
				if (comments!=null&&comments.size()!=0) {
					bug.setCommentNum(comments.size()+"");
				}else {
					bug.setCommentNum("0");
				}
				bug.setUser(selectBugByUID(connection, list.get(i).get("CREATEUSERID").toString()));
				System.out.println("评论数"+bug.getCommentNum());
				bugs.add(bug);
			}
		}
		return bugs;
	}

	
	public List<Bug> allSolvedBug(Connection connection, String start,
			String end) throws Exception {
		// TODO Auto-generated method stub
		List<Bug> bugs=null;
		StringBuffer sql=new StringBuffer("select * from  (select rownum dd,m.* from (select t.*   from BUGS t where t.status='3'  order by t.bugid desc) m) n where n.dd>=? and n.dd<?");
		Object object[] ={start,end};
		List<Map<String, Object>> list=DBControl.select(connection, sql.toString(), object);
		if (list!=null&&list.size()!=0) {
			bugs=new ArrayList<Bug>();
			for (int i = 0; i < list.size(); i++) {
				Bug bug =new Bug();
				bug.setId(list.get(i).get("BUGID").toString());
				bug.setTitle(list.get(i).get("TITLE").toString());
				bug.setDescription(list.get(i).get("DESCRIPTION").toString());
				bug.setUserid(list.get(i).get("CREATEUSERID").toString());
				bug.setCreatetime(list.get(i).get("CREATETIME").toString());
				bug.setProcessedUid(list.get(i).get("PROCESSUSERID").toString());
				bug.setProjectId(list.get(i).get("PROJECTID").toString());
				bug.setStatus(list.get(i).get("STATUS").toString());
				bug.setOccurtime(list.get(i).get("OCCURTIME").toString());
				bug.setLeave(list.get(i).get("LEAVEL").toString());
				
				List<Comment> comments=selectCommentByBugID(connection, list.get(i).get("BUGID").toString());
				bug.setImage(selectImagesByBugID(connection, list.get(i).get("BUGID").toString()));
//				bug.setComment(comments);
				if (comments!=null&&comments.size()!=0) {
					bug.setCommentNum(comments.size()+"");
				}else {
					bug.setCommentNum("0");
				}
				bug.setUser(selectBugByUID(connection, list.get(i).get("CREATEUSERID").toString()));
				System.out.println("评论数"+bug.getCommentNum());
				bugs.add(bug);
			}
		}
		return bugs;
	}

	
	public Map<String,String> addPro(Connection connection, String proName,
			String proDesc) throws Exception {
		// TODO Auto-generated method stub
		Map<String,String>  msg= new HashMap<String, String>();
//		String sql="select count(*) c from PROJECT t";
		String sql="insert into project (id,pro_name,description) values (seq_project.nextval,?,?) ";
		Object[] p= {proName,proDesc};
		String key=(String)DBControl.insert(connection, sql, p, new String[]{"ID"});
		if(key!=null){
			msg.put("proId", key);
		}else{
			msg.put("proId", "error");
		}
		sql="select count(*) c from PROJECT t";
		List<Map<String, Object>> list=DBControl.select(connection, sql, null);
		if(list!=null&&list.size()!=0){
			msg.put("count", list.get(0).get("C").toString());
		}else{
			msg.put("count", "0");
		}
		
		return msg;
	}

	
	public void activePro(Connection connection, String id, String status)
			throws Exception {
		// TODO Auto-generated method stub
		String sql="update project p set p.islive=? where p.id=?";
		Object p[]={status,id};
		DBControl.update(connection, sql, p);
	}

*/}
