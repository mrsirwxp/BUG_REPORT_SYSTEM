package com.service.imp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.dao.IBugDB;
import com.pojo.Bug;
import com.pojo.Comment;
import com.pojo.Project;
import com.pojo.User;
import com.service.IBugsService;

public class BugsService implements IBugsService {
	
	IBugDB iBugsDao;
	public void setiBugsDao(IBugDB iBugsDao) {
		this.iBugsDao = iBugsDao;
	}

	private static  Logger logger = Logger.getLogger(BugsService.class);
	
	@Override
	public Boolean addbug(Bug bug) {
		// TODO Auto-generated method stub
		
		
		Boolean boolean1=false;
		try {
			String key=null;
			key=iBugsDao.addBug( bug);
			List<String> imgs=bug.getImages();
			for (int i = 0; i < imgs.size(); i++) {
				if (!"upload/".equals(imgs.get(i))) {
					iBugsDao.addImage( key, imgs.get(i));
				}
			}
			
			boolean1=true;
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("提交bug录入出错！",e);
		}finally{
			
			
			
		}
		return boolean1;
	}



	@Override
	public List<Bug> selectAllBugs(String start,String end) {
		// TODO Auto-generated method stub
		List<Bug> bugs=null;
		
		try {
			bugs=iBugsDao.showAllBugs( start, end);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			
			
		}
		return bugs;
	}

	@Override
	public Boolean updateBug(String Status, String bugId) {
		// TODO Auto-generated method stub
		Boolean flag=false;
		
		try {
			iBugsDao.updateBugStatus( bugId, Status);
			flag=true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			
			
		}
		return flag;
	}

	@Override
	public List<Project> selectProjects() {
		// TODO Auto-generated method stub
		List<Project> projects=null;
		
		try {
			projects=iBugsDao.selectProjects();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			
			
		}
		return projects;
	}

	@Override
	public List<User> selectUsers() {
		// TODO Auto-generated method stub
		List<User> users=null;
		
		try {
			users=iBugsDao.selectUsers();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			
			
		}
		return users;
	}

	@Override
	public Bug selectBugByID(String bugid) {
		// TODO Auto-generated method stub
		Bug bug=null;
		
		try {
			bug=iBugsDao.selectBugByID( bugid);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			
			
		}
		return bug;
	}

	@Override
	public Boolean updateBugProcessUser(String bugid, String uid) {
		// TODO Auto-generated method stub
		Boolean flag=false;
		
		try {
			iBugsDao.updateBugProcessUserid( bugid, uid);
			flag=true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			
			
		}
		return flag;
	}

	@Override
	public Boolean addcomment(Comment comment) {
		// TODO Auto-generated method stub
		Boolean res=false;
		
		try {
			iBugsDao.addComment( comment.getBugid(), comment.getContent(), comment.getUserid());
			res=true;
		} catch (Exception e) {
			// TODO: handle exception
		}finally{
			
			
		}
		return res;
	}

	@Override
	public String bugToMe(String uid) {
		// TODO Auto-generated method stub
		
		String count="0";
		try {
			count=iBugsDao.bugToMe( uid);
		} catch (Exception e) {
			// TODO: handle exception
		}finally{
			
			
		}
		return count;
	}

	@Override
	public String mySubmitBug(String uid) {
		// TODO Auto-generated method stub
		
		String count="0";
		try {
			count=iBugsDao.mySubmitBug( uid);
		} catch (Exception e) {
			// TODO: handle exception
		}finally{
			
			
		}
		return count;
	}

	@Override
	public String stillNotSolvedBug() {
		// TODO Auto-generated method stub
		
		String count="0";
		try {
			count=iBugsDao.stillNotSolvedBug();
		} catch (Exception e) {
			// TODO: handle exception
		}finally{
			
			
		}
		return count;
	}

	@Override
	public String allBugCount() {
		// TODO Auto-generated method stub
		
		String count="0";
		try {
			count=iBugsDao.allBugCount();
		} catch (Exception e) {
			// TODO: handle exception
		}finally{
			
			
		}
		return count;
	}

	@Override
	public String allSolvedBug() {
		// TODO Auto-generated method stub
		
		String count="0";
		try {
			count=iBugsDao.allSolvedBug();
		} catch (Exception e) {
			// TODO: handle exception
		}finally{
			
			
		}
		return count;
	}

	@Override
	public List<Bug> selectbugToMe(String uid, String start, String end) {
		// TODO Auto-generated method stub
		List<Bug> bugs=null;
		
		try {
			bugs=iBugsDao.selectbugToMe(uid, start, end);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			
			
		}
		return bugs;
	}

	@Override
	public List<Bug> mySubmitBug(String uid, String start, String end) {
		// TODO Auto-generated method stub
		List<Bug> bugs=null;
		
		try {
			bugs=iBugsDao.mySubmitBug(uid, start, end);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			
			
		}
		return bugs;
	}

	@Override
	public List<Bug> stillNotSolvedBug(String start, String end) {
		// TODO Auto-generated method stub
		List<Bug> bugs=null;
		
		try {
			bugs=iBugsDao.stillNotSolvedBug(start, end);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			
			
		}
		return bugs;
	}

	@Override
	public List<Bug> allSolvedBug(String start, String end) {
		// TODO Auto-generated method stub
		List<Bug> bugs=null;
		
		try {
			bugs=iBugsDao.allSolvedBug(start, end);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			
			
		}
		return bugs;
	}

	@Override
	public Map<String, Object> getCount(String uid) {
		// TODO Auto-generated method stub
		String toMe=bugToMe(uid);
		String submitted=mySubmitBug(uid);
		String existed=stillNotSolvedBug();
		String solved=allSolvedBug();
		String allBug=allBugCount();
		Map<String, Object> map= new HashMap<String, Object>();
		map.put("toMe", toMe);
		map.put("submitted", submitted);
		map.put("existed", existed);
		map.put("solved", solved);
		map.put("allBug", allBug);
		return map;
	}

	@Override
	public Map<String,String>  addPro(String proName, String proDesc) {
		// TODO Auto-generated method stub
		Map<String,String>  msg=null;
		
		try {
			msg=iBugsDao.addPro(proName,proDesc);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return msg;
	}

	@Override
	public void activePro(String id, String status) {
		// TODO Auto-generated method stub
		
		try {
			iBugsDao.activePro(id,status);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
