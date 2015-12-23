package com.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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

import com.pojo.Bug;
import com.pojo.User;
import com.service.IBugsService;
import com.service.IUserService;
import com.util.CMSUtil;


public class UserController implements Controller {
	private IUserService iUserService;
	private IBugsService iBugsService;
	private static  Logger logger = Logger.getLogger(UserController.class);

	public void setiUserService(IUserService iUserService) {
		this.iUserService = iUserService;
	}
	
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
		if ("loginuser".equals(method)) {
			return loginuser(request, response);
		}else if ("".equals(method)||method==null) {
			return index(request, response);
		}else if ("welcome".equals(method)||method==null) {
			return welcome(request, response);
		}else if ("logout".equals(method)||method==null) {
			return logout(request, response);
		}else if ("regestUser".equals(method)||method==null) {
			return regestUser(request, response);
		}else if ("regUser".equals(method)||method==null) {
			return regUser(request, response);
		}else if ("myAccount".equals(method)||method==null) {
			return myAccount(request, response);
		}else if ("uploadHeadImg".equals(method)||method==null) {
			return uploadHeadImg(request, response);
		}else if ("updateUInfo".equals(method)||method==null) {
			return updateUInfo(request, response);
		}
		return null;
	}

	private ModelAndView updateUInfo(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		String uname=request.getParameter("uname");
		String email=request.getParameter("email");
		String pswd=request.getParameter("pswd");
		String newpswd=request.getParameter("newpswd");
		User user=(User)request.getSession().getAttribute("loginUserInfo");
		Boolean boolean1=iUserService.userLogin(request, user.getUserid(), pswd);
		String resStr=null;
		if(boolean1==false){
			resStr="旧密码不正确，没有选项被修改！";
		}else{
			if(uname==null||"".equals(uname.trim().replace(" ", ""))){
				resStr="姓名不能为空！";
			}else if(email==null||"".equals(email.trim().replace(" ", ""))){
				resStr="邮箱不能为空！";
			}else if(newpswd==null||"".equals(newpswd.trim().replace(" ", ""))){
				resStr="新密码不能为空！";
			}else{
				resStr=iUserService.upDateUInfo(user.getUserid(), uname,email,newpswd);
			}
		}
		
		PrintWriter out=response.getWriter();
		out.println(resStr);
		return null;
	}

	private ModelAndView uploadHeadImg(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		System.out.println("进入上传图片");
		Random random = new Random(System.currentTimeMillis());
		response.setContentType("text/html;charset=utf-8");
		User user=(User)request.getSession().getAttribute("loginUserInfo");
		String images=null;
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
					 }else{//处理文件二进制流
						 //更改图片名称
						 
						 String fileName=CMSUtil.DateStrFormat(new Date(), "yyyy-MM-dd-hh-mm-ss")+random.nextInt(100000);
//						 fileName=URLEncoder.encode("upload/"+fileItem.getName(),"UTF-8");
					 //保存文件，其实就是把缓存里的数据写到目标路径下    
					 if(fileItem.getName()!=null && fileItem.getSize()!=0){      
						 String endWith[]=fileItem.getName().split("\\.");
						 String end=endWith[1];
						 images= "upload/"+fileName+"."+end;
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
		String res=iUserService.uploadHeadImg(user.getUserid(), images);
		PrintWriter out=response.getWriter();
		if("1".equals(res)){
			out.println("true:"+images);
		}else{
			out.println("false:"+images);
		}
		return null;
	}

	private ModelAndView myAccount(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		ModelAndView view = new ModelAndView("myAccount");
		return view;
	}

	private ModelAndView regUser(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		ModelAndView view =null;
		String username=request.getParameter("username");
		String email=request.getParameter("email");
		String password=request.getParameter("password");
		String passwordAgain=request.getParameter("passwordAgain");
		String juese=request.getParameter("juese");
		if(!password.equals(passwordAgain)){
			view = new ModelAndView("regestUser");
			view.addObject("username", username);
			view.addObject("email", email);
			view.addObject("juese", juese);
			view.addObject("msgg", "两次输入的密码不一致！");
		}else if("0".equals(juese)){
			view = new ModelAndView("regestUser");
			view.addObject("username", username);
			view.addObject("email", email);
			view.addObject("juese", juese);
			view.addObject("msgg", "请选择角色！");
		}else{
			view = new ModelAndView("regestUserSuccess");
			String uid=iUserService.adduser(username, passwordAgain, email, juese);
			view.addObject("msgg", uid);
		}
		
		return view;
	}

	private ModelAndView regestUser(HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView andView =new ModelAndView("regestUser");
		andView.addObject("msgg", "0");
		return andView;
	}

	private ModelAndView logout(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
//		ModelAndView andView =new ModelAndView("redirect:/login");
		HttpSession session = request.getSession(false);//防止创建Session
		if (session==null) {
//			andView =new ModelAndView("redirect:login");
			response.sendRedirect("login.do");
		}
		session.removeAttribute("loginUserInfo");
		session.removeAttribute("isOnLine");
		/*User user=(User)request.getSession().getAttribute("loginUserInfo");
		ModelAndView andView =new ModelAndView("login");
		if (user==null) {
			return andView;
		}
		logger.info("用户"+user.getName()+"退出系统！");
		request.getSession().setAttribute("loginUserInfo",null);
		request.getSession().setAttribute("isOnLine","0");*/
		response.sendRedirect("login.do");
		return null;
	}

	private ModelAndView welcome(HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView andView =new ModelAndView("welcome");
		return andView;
	}

	private ModelAndView index(HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView andView =new ModelAndView("login");
		Object object=request.getSession().getAttribute("loginUserInfo");
		if (object!=null&&(object instanceof User)) {
			User user=(User)object;
			String bugToMe=iBugsService.bugToMe(user.getUserid());
			String mySubmitBug=iBugsService.mySubmitBug(user.getUserid());
			String stillNotSolvedBug=iBugsService.stillNotSolvedBug();
			String allBugCount=iBugsService.allBugCount();
			andView =new ModelAndView("main");
			andView.addObject("bugToMe",bugToMe);
			andView.addObject("mySubmitBug",mySubmitBug);
			andView.addObject("stillNotSolvedBug",stillNotSolvedBug);
			andView.addObject("allBugCount",allBugCount);
		}
		return andView;
	}

	private ModelAndView loginuser(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		ModelAndView andView =new ModelAndView("login");
		String uid=request.getParameter("username");
		String pswd=request.getParameter("password");
		String isOnLine=(String)request.getSession().getAttribute("isOnLine");
		System.out.println(isOnLine);
			Boolean boolean1=iUserService.userLogin(request, uid, pswd);
			if (boolean1) {
				String bugToMe=iBugsService.bugToMe(uid);
				String mySubmitBug=iBugsService.mySubmitBug(uid);
				String stillNotSolvedBug=iBugsService.stillNotSolvedBug();
				String allBugCount=iBugsService.allBugCount();
				String allSolvedBug=iBugsService.allSolvedBug();
				
				andView=new ModelAndView("main");
				andView.addObject("bugToMe",bugToMe);
				andView.addObject("mySubmitBug",mySubmitBug);
				andView.addObject("stillNotSolvedBug",stillNotSolvedBug);
				andView.addObject("allBugCount",allBugCount);
				andView.addObject("allSolvedBug",allSolvedBug);
			}
		return andView;
	}

}
