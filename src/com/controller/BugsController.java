package com.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUpload;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.RequestContext;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.servlet.ServletRequestContext;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.pojo.Bug;
import com.pojo.Comment;
import com.pojo.Project;
import com.pojo.User;
import com.service.IBugsService;
import com.util.CMSUtil;


public class BugsController implements Controller {
	private IBugsService iBugsService;
	private static  Logger logger = Logger.getLogger(BugsController.class);


	public void setiBugsService(IBugsService iBugsService) {
		this.iBugsService = iBugsService;
	}

	@Override
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/javascript;charset=utf-8");
		String method = request.getParameter("m");
		if ("add".equals(method)) {
			return add(request, response);
		}else if ("newbug".equals(method)) {
			return newbug(request, response);
		}else if ("getProjects".equals(method)) {
			return getProjects(request, response);
		}else if ("getUsers".equals(method)) {
			return getUsers(request, response);
		}else if ("uploadImg".equals(method)) {
			return uploadImg(request, response);
		}else if ("allbugs".equals(method)) {
			return allbugs(request, response);
		}else if ("pubuAllbugs".equals(method)) {
			return pubuAllbugs(request, response);
		}else if ("bugsDetail".equals(method)) {
			return bugsDetail(request, response);
		}else if ("bugRepaired".equals(method)) {
			return bugRepaired(request, response);
		}else if ("bugToMe".equals(method)) {
			return bugToMe(request, response);
		}else if ("pubuBugToMe".equals(method)) {
			return pubuBugToMe(request, response);
		}else if ("addcomment".equals(method)) {
			return addcomment(request, response);
		}else if ("reOpenBug".equals(method)) {
			return reOpenBug(request, response);
		}else if ("isRepaired".equals(method)) {
			return isRepaired(request, response);
		}else if ("mySubmitBug".equals(method)) {
			return mySubmitBug(request, response);
		}else if ("pubuMySubmitBug".equals(method)) {
			return pubuMySubmitBug(request, response);
		}else if ("stillNotSolvedBug".equals(method)) {
			return stillNotSolvedBug(request, response);
		}else if ("pubuStillNotSolvedBug".equals(method)) {
			return pubuStillNotSolvedBug(request, response);
		}else if ("allSolvedBug".equals(method)) {
			return allSolvedBug(request, response);
		}else if ("pubuAllSolvedBug".equals(method)) {
			return pubuAllSolvedBug(request, response);
		}else if ("getCount".equals(method)) {
			return getCount(request, response);
		}else if ("projectMangeList".equals(method)) {
			return projectMangeList(request, response);
		}else if ("addPro".equals(method)) {
			return addPro(request, response);
		}else if ("activePro".equals(method)) {
			return activePro(request, response);
		}else if ("unActivePro".equals(method)) {
			return unActivePro(request, response);
		}
		return null;
	}

	private ModelAndView activePro(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		String id=request.getParameter("id");
		iBugsService.activePro(id,"1");
		PrintWriter writer = response.getWriter();
		writer.println("已激活！");
		return null;
	}
	
	private ModelAndView unActivePro(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		String id=request.getParameter("id");
		iBugsService.activePro(id,"0");
		PrintWriter writer = response.getWriter();
		writer.println("已关闭！");
		return null;
	}

	private ModelAndView addPro(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		String proName=request.getParameter("proName");
		String proDescript=request.getParameter("proDescript");
		Map<String, String> msg=iBugsService.addPro(proName,proDescript);
		Gson g = new GsonBuilder().serializeNulls().create();
		PrintWriter writer = response.getWriter();
		writer.println(g.toJson(msg));
		System.out.println(g.toJson(msg));
		return null;
	}

	private ModelAndView projectMangeList(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		ModelAndView view = new ModelAndView("projectMangeList");
		List<Project> projects =iBugsService.selectProjects();
		view.addObject("projects", projects);
		return view;
	}

	private ModelAndView getCount(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		String uid=request.getParameter("uid");
		Map<String, Object> list=iBugsService.getCount(uid);
		Gson g = new GsonBuilder().serializeNulls().create();
		PrintWriter writer = response.getWriter();
		writer.println(g.toJson(list));
		System.out.println(g.toJson(list));
		return null;
	}

	private ModelAndView pubuAllSolvedBug(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		Integer start=Integer.valueOf(request.getSession().getAttribute("start").toString());
		int end=start+10;
		List<Bug> bugs=iBugsService.allSolvedBug(start+"", end+"");
		Gson g = new GsonBuilder().serializeNulls().create();
		String bugsJson=g.toJson(bugs);
		bugsJson=bugsJson.replace("\\r\\n", "<br/>");
		PrintWriter writer = response.getWriter();
		writer.println(bugsJson);
		if (bugs!=null&&bugs.size()!=0) {
			logger.info("更改start");
			request.getSession().setAttribute("start",end);
		}
		logger.info(bugsJson);
		return null;
	}

	private ModelAndView allSolvedBug(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		ModelAndView andView =new ModelAndView("allSolvedBug");
		
		List<Bug> bugs=null;
		bugs=iBugsService.allSolvedBug("1", "11");
		request.getSession().setAttribute("start","11");
		Gson g = new GsonBuilder().serializeNulls().create();
		String bugsJson=g.toJson(bugs);
		bugsJson=bugsJson.replace("\\r\\n", "<br/>");
		andView.addObject("bugs",bugsJson);
		logger.info(bugsJson);
		return andView;
	}

	private ModelAndView pubuStillNotSolvedBug(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		Integer start=Integer.valueOf(request.getSession().getAttribute("start").toString());
		int end=start+10;
		List<Bug> bugs=iBugsService.stillNotSolvedBug(start+"", end+"");
		Gson g = new GsonBuilder().serializeNulls().create();
		String bugsJson=g.toJson(bugs);
		bugsJson=bugsJson.replace("\\r\\n", "<br/>");
		PrintWriter writer = response.getWriter();
		writer.println(bugsJson);
		if (bugs!=null&&bugs.size()!=0) {
			logger.info("更改start");
			request.getSession().setAttribute("start",end);
		}
		logger.info(bugsJson);
		return null;
	}

	private ModelAndView stillNotSolvedBug(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		ModelAndView andView =new ModelAndView("stillNotSolvedBug");
		
		List<Bug> bugs=null;
		bugs=iBugsService.stillNotSolvedBug("1", "11");
		request.getSession().setAttribute("start","11");
		Gson g = new GsonBuilder().serializeNulls().create();
		String bugsJson=g.toJson(bugs);
		bugsJson=bugsJson.replace("\\r\\n", "<br/>");
		andView.addObject("bugs",bugsJson);
		logger.info(bugsJson);
		return andView;
	}

	private ModelAndView pubuMySubmitBug(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		Integer start=Integer.valueOf(request.getSession().getAttribute("start").toString());
		int end=start+10;
		User user=(User)request.getSession().getAttribute("loginUserInfo");
		List<Bug> bugs=iBugsService.mySubmitBug(user.getUserid(),start+"", end+"");
		Gson g = new GsonBuilder().serializeNulls().create();
		String bugsJson=g.toJson(bugs);
		bugsJson=bugsJson.replace("\\r\\n", "<br/>");
		PrintWriter writer = response.getWriter();
		writer.println(bugsJson);
		if (bugs!=null&&bugs.size()!=0) {
			logger.info("更改start");
			request.getSession().setAttribute("start",end);
		}
		logger.info(bugsJson);
		return null;
	}

	private ModelAndView mySubmitBug(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		ModelAndView andView =new ModelAndView("mySubmitBug");
		User user=(User)request.getSession().getAttribute("loginUserInfo");
		List<Bug> bugs=null;
		bugs=iBugsService.mySubmitBug(user.getUserid(),"1", "11");
		request.getSession().setAttribute("start","11");
		Gson g = new GsonBuilder().serializeNulls().create();
		String bugsJson=g.toJson(bugs);
		bugsJson=bugsJson.replace("\\r\\n", "<br/>");
		andView.addObject("bugs",bugsJson);
		logger.info(bugsJson);
		return andView;
	}

	private ModelAndView pubuBugToMe(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		Integer start=Integer.valueOf(request.getSession().getAttribute("start").toString());
		int end=start+10;
		User user=(User)request.getSession().getAttribute("loginUserInfo");
		List<Bug> bugs=iBugsService.selectbugToMe(user.getUserid(),start+"", end+"");
		Gson g = new GsonBuilder().serializeNulls().create();
		String bugsJson=g.toJson(bugs);
		bugsJson=bugsJson.replace("\\r\\n", "<br/>");
		PrintWriter writer = response.getWriter();
		writer.println(bugsJson);
		if (bugs!=null&&bugs.size()!=0) {
			logger.info("更改start");
			request.getSession().setAttribute("start",end);
		}
		logger.info(bugsJson);
		return null;
	}

	private ModelAndView isRepaired(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		String bugid=request.getParameter("bugid");
		iBugsService.updateBug("3", bugid);
		return null;
	}

	private ModelAndView reOpenBug(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		String bugid=request.getParameter("bugid");
		iBugsService.updateBug("5", bugid);
		return null;
	}

	private ModelAndView addcomment(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		String content=request.getParameter("contents");
		String bugid=request.getParameter("bugid");
		String uid=request.getParameter("uid");
		Comment comment=new Comment();
		comment.setBugid(bugid);
		comment.setContent(content);
		comment.setUserid(uid);;
		iBugsService.addcomment(comment);
		return null;
	}

	private ModelAndView bugToMe(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		ModelAndView andView =new ModelAndView("bugToMe");
		User user=(User)request.getSession().getAttribute("loginUserInfo");
		List<Bug> bugs=null;
		bugs=iBugsService.selectbugToMe(user.getUserid(),"1", "11");
		request.getSession().setAttribute("start","11");
		Gson g = new GsonBuilder().serializeNulls().create();
		String bugsJson=g.toJson(bugs);
		bugsJson=bugsJson.replace("\\r\\n", "<br/>");
		andView.addObject("bugs",bugsJson);
		logger.info(bugsJson);
		return andView;
	}

	private ModelAndView bugRepaired(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		String bugid=request.getParameter("bugid");
		String repaireUser=request.getParameter("repaireUser");
		if(repaireUser.equals("0")){
			iBugsService.updateBug("2", bugid);
		}else{
			iBugsService.updateBugProcessUser(bugid, repaireUser);
		}
		return null;
	}

	private ModelAndView bugsDetail(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		String bugid=CMSUtil.stringFormat(request.getParameter("bugid"));
		System.out.println(bugid);
		Bug bug= iBugsService.selectBugByID(bugid);
		ModelAndView andView = new ModelAndView("bugsDetail");
		List<User> users=iBugsService.selectUsers();
		andView.addObject("bug",bug);
		request.setAttribute("bug", bug);
		request.setAttribute("users", users);
		System.out.println(bug.toString());
		return andView;
	}

	private ModelAndView pubuAllbugs(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		Integer start=Integer.valueOf(request.getSession().getAttribute("start").toString());
		int end=start+10;
		
		List<Bug> bugs=iBugsService.selectAllBugs(start+"", end+"");
		Gson g = new GsonBuilder().serializeNulls().create();
		String bugsJson=g.toJson(bugs);
		bugsJson=bugsJson.replace("\\r\\n", "<br/>");
		PrintWriter writer = response.getWriter();
		writer.println(bugsJson);
		if (bugs!=null&&bugs.size()!=0) {
			logger.info("更改start");
			request.getSession().setAttribute("start",end);
		}
		logger.info(bugsJson);
		return null;
	}

	private ModelAndView allbugs(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		ModelAndView andView =new ModelAndView("allbugs");
		
		List<Bug> bugs=null;
		bugs=iBugsService.selectAllBugs("1", "11");
		request.getSession().setAttribute("start","11");
		Gson g = new GsonBuilder().serializeNulls().create();
		String bugsJson=g.toJson(bugs);
		bugsJson=bugsJson.replace("\\r\\n", "<br/>");
		andView.addObject("bugs",bugsJson);
		logger.info(bugsJson);
		return andView;
	}

	private ModelAndView uploadImg(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		System.out.println("进入上传图片");
		Random random = new Random(System.currentTimeMillis());
		response.setContentType("text/html;charset=utf-8");
		String title=null;
		String description=null;
		String projectId=null;
		String occurtime=null;
		String leave=null;
		String userid=null;
		User user=(User)request.getSession().getAttribute("loginUserInfo");
		List<String> images=new ArrayList<String>();
		//这里对request进行封装，RequestContext提供了对request多个访问方法 
		 RequestContext requestContext = new ServletRequestContext(request); 
		//判断表单是否是Multipart类型的。这里可以直接对request进行判断
		 if(FileUpload.isMultipartContent(requestContext)){
			 DiskFileItemFactory factory = new DiskFileItemFactory();
			//设置文件的缓存路径
			 String path = request.getSession().getServletContext().getRealPath("/")+ File.separator + "upload" + File.separator;;
//			 factory.setRepository(new File(path));//
			 factory.setRepository(new File("c:\\newzhcx"));
			 //String uploadPath = request.getRealPath( "/")+ File.separator + "upload" + File.separator;
			 ServletFileUpload upload = new ServletFileUpload(factory);//格式转换不太管用
			 upload.setHeaderEncoding("utf-8");
			//设置上传文件大小的上限，-1表示无上限 
			 upload.setSizeMax(-1); 
			 List items = new ArrayList(); 
			 try { 
				 //上传文件，并解析出所有的表单字段，包括普通字段和文件字段  //下面对每个字段进行处理，分普通字段和文件字段					
				 items = upload.parseRequest(request);
				 Iterator it = items.iterator();
				 while(it.hasNext()){ 
					 FileItem fileItem = (FileItem) it.next();
					 if(fileItem.isFormField()){//处理普通字段
						 try {
							 if(fileItem.getFieldName().equals("title")){
								 //pp.setName(new String(fileItem.getString().getBytes("ISO8859-1"), "UTF-8"));
								 title=new String(fileItem.getString().getBytes("ISO8859-1"), "UTF-8");
							 }
							 if(fileItem.getFieldName().equals("description")){
								 description=new String(fileItem.getString().getBytes("ISO8859-1"), "UTF-8");
							 }
							 if(fileItem.getFieldName().equals("projectId")){
								 projectId=fileItem.getString();
							 }
							 if(fileItem.getFieldName().equals("occurtime")){
								 occurtime=fileItem.getString();
							 }
							 if(fileItem.getFieldName().equals("leave")){
								 leave=fileItem.getString();
							 }
							 if(fileItem.getFieldName().equals("userid")){
								 userid=fileItem.getString();
							 }
						} catch (Exception e) {
							e.printStackTrace();
						}						 
					 }else{//处理文件二进制流
						 //更改图片名称
						 
						 String fileName=CMSUtil.DateStrFormat(new Date(), "yyyy-MM-dd-hh-mm-ss")+random.nextInt(100000);
//						 fileName=URLEncoder.encode("upload/"+fileItem.getName(),"UTF-8");
					 //保存文件，其实就是把缓存里的数据写到目标路径下    
					 if(fileItem.getName()!=null && fileItem.getSize()!=0){      
						 String endWith[]=fileItem.getName().split("\\.");
						 String end=endWith[1];
						 images.add( "upload/"+fileName+"."+end);
							 File fullFile = new File(fileName+"."+end);
							 logger.info("path + fullFile.getName()="+path + fullFile.getName());
							 File newFile = new File(path + fullFile.getName());
							 try {
								   fileItem.write(newFile);
							     }catch (Exception e) {
										// TODO: handle exception
							        	e.printStackTrace();
							     }
						  }
						 else{
							 System.out.println("没有选择文件或文件为空");
						 }		
					 }			   
				   }
			    } catch (FileUploadException e1 ) {
				 System.out.println("上传出现错误："+e1.getMessage());
			    }	 
		 }
		Bug bug=new Bug();
	 	bug.setDescription(description);
		bug.setLeavel(leave);;
		bug.setProcessuserid(userid);;
		bug.setCreateuserid(user.getUserid());
		bug.setOccurtime(occurtime);
		bug.setProjectid(projectId);
		bug.setTitle(title);
		bug.setImages(images);
		System.out.println(bug.toString());
		Boolean boolean1=iBugsService.addbug(bug);
		ModelAndView andView =null;
		if (boolean1) {
			andView =new ModelAndView("success");
			andView.addObject("msg", "BUG提交成功！");
		}else {
			andView =new ModelAndView("error");
			andView.addObject("msg", "BUG提交失败！");
		}
		return andView;
	}

	private ModelAndView newbug(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		ModelAndView andView=new ModelAndView("newbug");
		List<User> users=iBugsService.selectUsers();
		List<Project> projects =iBugsService.selectProjects();
		request.setAttribute("users", users);
		request.setAttribute("projects", projects);
		return andView;
	}

	private ModelAndView getUsers(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		return null;
	}

	private ModelAndView getProjects(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		return null;
	}

	private ModelAndView add(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		String title=CMSUtil.stringFormat(request.getParameter("title"));
		String description=CMSUtil.stringFormat(request.getParameter("description"));
		String projectId=CMSUtil.stringFormat(request.getParameter("projectId"));
		String occurtime=CMSUtil.stringFormat(request.getParameter("occurtime"));
		String leave=CMSUtil.stringFormat(request.getParameter("leave"));
		String userid=CMSUtil.stringFormat(request.getParameter("userid"));
		User user=(User)request.getSession().getAttribute("loginUserInfo");
		Bug bug=new Bug();
		bug.setDescription(description);
		bug.setLeavel(leave);;
		bug.setProcessuserid(userid);
		bug.setCreateuserid(user.getUserid());
		bug.setOccurtime(occurtime);
		bug.setProjectid(projectId);
		bug.setTitle(title);
		Boolean boolean1=iBugsService.addbug(bug);
//		Boolean boolean1=true;
		PrintWriter out=response.getWriter();
		String reString="{\"isSuccess\":\"false\",\"msg\":\"提交失败！\"}";
		if (boolean1) {
			reString="{\"isSuccess\":\"true\",\"msg\":\"提交成功！\"}";
		}
		out.println(reString);
		return null;
	}


}
