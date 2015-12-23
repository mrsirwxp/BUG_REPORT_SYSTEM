package com.util;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

public  class CMSUtil {
	
	 /** 
	  * 判断是否登录
	  * @author wxp
	  * @param HttpServletRequest request
	  * @return String
	  */ 
	 public static boolean isLogin(HttpServletRequest request){  
		 if(request.getSession().getAttribute("loginUserName")!=null){
			 return true;
		 }else{
			 return false;
		 }
	 }  
	 
	 /** 
	  * 判断是否介于日期之间
	  * @author wxp
	  * @param HttpServletRequest request
	  * @return String
	  */ 
	 public static boolean isValidDate(String date1,String date2)throws ParseException{
		 if(date1!=null&&date2!=null){
			 SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
			 // Calendar calendar = Calendar.getInstance();//日历对象
			 //calendar.set(calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DATE),0,0,0);
			 Date date=new Date();
			 date=sdf.parse((sdf.format(date)));
			 if(date.compareTo(sdf.parse(date1))>=0&&date.compareTo(sdf.parse(date2))<=0){
				 return true;
			 }else{
				 return false;
			 }
		 }else{
			 return false;
		 }
	 }
	 
	 /** 
	  * 判断是否介于日期之间
	  * @author wxp
	  * @param HttpServletRequest request
	  * @return String
	  */ 
	 public static boolean isValidDate(Object date1,Object date2)throws ParseException{
		 if(date1!=null&&date2!=null){
			 SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
			 // Calendar calendar = Calendar.getInstance();//日历对象
			 //calendar.set(calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DATE),0,0,0);
			 Date date=new Date();
			 date=sdf.parse((sdf.format(date)));
			 if(date.compareTo(sdf.parse(date1.toString()))>=0&&date.compareTo(sdf.parse(date2.toString()))<=0){
				 return true;
			 }else{
				 return false;
			 }
		 }else{
			 return false;
		 }
	 }  
	 
	 	/**
	 * 获取当前登录用户名
	 * @author wxp
	 * @param request
	 * @return
	 */
	public static String getLoginUser(HttpServletRequest request){
		String loginUserName = null;
		if(request.getSession().getAttribute("loginUserName")!=null){
			loginUserName = request.getSession().getAttribute("loginUserName").toString();
		}
		return loginUserName;
	}
	 
	/**
	 * 转换日期类型
	 * @author wxp
	 * @param obj
	 * @return Date
	 * @throws ParseException
	 */
	public static Date TimeFormatToDate(Object obj) throws ParseException{
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//小写的mm表示的是分钟
		String str = "1970-01-01 00:00:00";
		if(obj !=null){
			str = obj.toString();
		}
		return sdf.parse(str);
	}
	/**
	 * 转换日期类型
	 * @author wxp
	 * @param obj
	 * @return String
	 */
	public static String TimeFormatToString(Object obj){
		String str = "1970-01-01 00:00:00";
		if(obj !=null){
			str = obj.toString().substring(0, 19);
		}
		return str;
	}
	/**
	 * 字符串长日期转换 :年月日  时分秒
	 * @author wxp
	 * @param obj
	 * @return String
	 */
	public static Date DateTimeFormat(Object str){
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//小写的mm表示的是分钟  
		if (str == null) {
			return null;
		}
		try {
			return sdf.parse(str.toString());
		} catch (ParseException e) {
			return null;
		}
	}
	/**
	 * 字符串转换日期类型
	 * @author wxp
	 * @param str
	 * @return Date
	 * @throws ParseException
	 */
	public static Date DayFormat(String str) throws ParseException{
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");//小写的mm表示的是分钟  
		return sdf.parse(str);
	}
	
	/**
	 * 可自定义转换格式的字符串转换日期类型
	 * @author wxp
	 * @param String DateStr
	 * @param String format
	 * @return Date
	 * @throws ParseException
	 */
	public static Date DateTimeFormat(String str,String format) throws ParseException{
		SimpleDateFormat sdf=new SimpleDateFormat(format);//小写的mm表示的是分钟 
		if(str!=null&&str.length()>0){
			return sdf.parse(str);
		}else{
			return null;
		}
	}
	
	/**
	 * 可自定义转换格式的字符串转换日期类型
	 * @author wxp
	 * @param Date date
	 * @param String format
	 * @return String
	 */
	public static String DateStrFormat(Date date,String format){
		SimpleDateFormat sdf=new SimpleDateFormat(format);//小写的mm表示的是分钟 
		if(date!=null){
			return sdf.format(date);
		}else{
			return "";
		}
	}
	
	/**
	 * 可自定义转换格式的字符串转换日期类型
	 * @author wxp
	 * @param String format
	 * @return String str
	 * @throws ParseException
	 */
	public static String DateTimeStr(String format){
		SimpleDateFormat sdf=new SimpleDateFormat(format);//小写的mm表示的是分钟 
		return sdf.format(new Date());
	}
	
	/**
	 * 获取当前系统时间 yyyy-MM-dd HH:mm:ss格式
	 * @author wxp
	 * @return String
	 */
	public static String getSystemTime(){
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//小写的mm表示的是分钟
		return sdf.format(new Date());
		
	}
	/**
	 * 获取当前系统时间 yyyyMM格式
	 * @author wxp
	 * @return String
	 */
	public static String getTimeAccurateToMonth(){
		SimpleDateFormat sdf=new SimpleDateFormat("yyyyMM");//小写的mm表示的是分钟
		return sdf.format(new Date());
	}
	/**
	 * 获取当前系统时间 yyyyMMdd格式
	 * @author wxp
	 * @return String
	 */
	public static String getTimeAccurateToDay(){
		SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd");//小写的mm表示的是分钟
		return sdf.format(new Date());
	}
	/**
	 * 获取当前系统时间 yyyyMMddHHmmssSSS格式
	 * @author wxp
	 * @return String
	 */
	public static String getTimeAccurateToMS(){
		SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmssSSS");//小写的mm表示的是分钟
		return sdf.format(new Date());
	}
	/**
	 * 获取当前系统时间毫秒
	 * @author wxp
	 * @return long
	 */
	public static long getStartTime(){
		return new Date().getTime();
	}
	/**
	 * 计算执行所需时间毫秒
	 * @author wxp
	 * @return long
	 */
	public static long getExecTime(long startTime) {
		long endTime = new Date().getTime();
		return endTime-startTime;
	}
	/**
	 * 获取用户Ip
	 * @author wxp
	 * @param request
	 * @return String
	 */
	public static String logUserIp(HttpServletRequest request){
		// 获取客户机IP
		String logUserIp = request.getHeader("x-forwarded-for");
		if (logUserIp == null || logUserIp.length() == 0
				|| logUserIp.equalsIgnoreCase("unknown")) {
			logUserIp = request.getHeader("Proxy-Client-IP");
		}
		if (logUserIp == null || logUserIp.length() == 0
				|| logUserIp.equalsIgnoreCase("unknown")) {
			logUserIp = request.getHeader("WL-Proxy-Client-IP");
		}
		if (logUserIp == null || logUserIp.length() == 0
				|| logUserIp.equalsIgnoreCase("unknown")) {
			logUserIp = request.getRemoteAddr();
		}
		if(logUserIp.equals("0:0:0:0:0:0:0:1")){
			logUserIp = "127.0.0.1";
		}
		return logUserIp;
	}
	/**
	 * 获取当前系统日期  yyyy-MM-dd 格式
	 * @author wxp
	 * @return String
	 */
	public static String getSystemDay(){
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");//小写的mm表示的是分钟
		return sdf.format(new Date());
	}
	/**
	 * String转换Long
	 * @author wxp
	 * @param Object
	 * @return Long
	 */
	public static long longFormat(String str){
		long lg = 0;
		if(str != null && str.length()>0){
			lg = Long.parseLong(str.toString());
		}
		return lg;
	}
	/**
	 * Object转换Long
	 * @author wxp
	 * @param Object
	 * @param defaultVal	默认值
	 * @return Long
	 */
	public static long longFormat(Object object, long defaultVal){
		long lg = defaultVal;
		if(object != null && object.toString().length() > 0){
			lg = Long.parseLong(object.toString());
		}
		return lg;
	}
	
	/**
	 * String转换Integer
	 * @author wxp
	 * @param Object
	 * @return Long
	 */
	public static Integer intFormat(String str){
		int i = 0;
		if(str != null && str.length()>0){
			i = Integer.parseInt(str.toString());
		}
		return i;
	}
	/**
	 * Object转换Integer
	 * @author wxp
	 * @param Object
	 * @param defaultVal 默认值
	 * @return Integer
	 */
	public static Integer intFormat(Object object, int defaultVal){
		int i = defaultVal;
		if(object != null && object.toString().length() > 0){
			i = Integer.parseInt(object.toString());
		}
		return i;
	}
	/**
	 * Object转换String
	 * @author wxp
	 * @param Object
	 * @return String
	 */
	public static String stringFormat(Object obj){
		String str = "";
		if(obj != null){
			str = obj.toString();
		}
		return str;
	}
	
	/**
	 * Object转换String并截取长度
	 * @author wxp
	 * @param Object
	 * @return String
	 * @throws ParseException
	 */
	public static String stringFormat(Object obj,int num){
		String str = "";
		if(obj != null){
			str = obj.toString();
			if(str.length()>num){
				str = str.substring(0,num)+"...";
			}
		}
		return str;
	}
	/**
	 * 带默认值的Object转换String,如为空则返回传入默认字符
	 * @author wxp
	 * @param Object
	 * @param String 
	 * @return String
	 */
	public static String stringFormat(Object obj,String str){
		if(obj != null){
			str = obj.toString();
		}
		return str;
	}
	
	/**
	 * 转换字符串,如为空则返回传入默认字符,不为空则排除给定字符
	 * @author wxp
	 * @param Object obj
	 * @param String defStr:默认的字符
	 * @param String exceptStr:排除的字符
	 * @return String
	 */
	public static String stringFormat(Object obj,String defStr,String exceptStr){
		String str="";
		if(obj == null){
			return defStr;
		}else{
			str=obj.toString();
			if(exceptStr!=null&&str.equals(exceptStr)){
				return defStr;
			}else{
				return str;
			}
		}
	}
	
	/**
	 * String转换小数
	 * @author wxp
	 * @param String str
	 * @return double dou
	 */
	public static double dobleFormat(String str){
		double dou=0.0;
		if(str != null&&str.length()>0){
			dou = Double.parseDouble(str);
		}
		return dou;
	}
	
	/**
	 * Object转换小数
	 * @author wxp
	 * @param Object obj
	 * @return double dou
	 */
	public static double dobleFormat(Object obj){
		double dou=0.0;
		if(obj != null&&obj.toString().length()>0){
			dou = Double.parseDouble(obj.toString());
		}
		return dou;
	}
	
	/**
	 * 获取当前登录用户名
	 * @author wxp
	 * @param request
	 * @return
	 */
	public static String getLoginUserName(HttpServletRequest request){
		String loginUserName = "system";
		if(request.getSession().getAttribute("loginUserName")!=null){
			loginUserName = request.getSession().getAttribute("loginUserName").toString();
		}
		return loginUserName;
	}
	
	/**
	 * 获取当前登录用户Id
	 * @author wxp
	 * @param request
	 * @return
	 */
	public static long getLoginUserId(HttpServletRequest request){
		long loginUserId = 1;
		if(request.getSession().getAttribute("loginUserId")!=null){
			loginUserId = CMSUtil.longFormat(request.getSession().getAttribute("loginUserId").toString());
		}
		return loginUserId;
	}
	
    /**
	 * 获取"yyyyMM/yyyyMMdd/"的路径
	 * @author wxp
	 * @return String folderPath:路径
	 */
    public static String getShortFolderPath(){
    	StringBuffer sb=null;
    	sb=new StringBuffer();
    	sb.append("\\");
    	sb.append(CMSUtil.getTimeAccurateToMonth());
    	sb.append("\\");
    	sb.append(CMSUtil.getTimeAccurateToDay());
    	sb.append("\\");
    	String folderPath=sb.toString();
    	return  folderPath;
    }
    /**
	 * 获取文件名
	 * @author wxp
	 * @param String extension:扩展名
	 * @return String fileName:文件名
	 */
    public static String getFileName(String extension){
    	Random random=new Random();
    	StringBuffer sb=null;
    	sb=new StringBuffer();
    	int length=extension.length();
    	if(length>3){
    		sb.append(extension.substring(0,3));
    	}else if(length==3){
    		sb.append(extension);
    	}else{
    		sb.append(extension);
    		for(int i=0;i<(3-length);i++){
    			sb.append("_");
    		}
    	}
    	sb.append("_");
    	sb.append(CMSUtil.getTimeAccurateToMS());
    	sb.append("_");
    	sb.append(random.nextInt(100000));
    	sb.append(".");
    	sb.append(extension);
    	String fileName=sb.toString();
    	return  fileName;
    }
    /**
	 * 获取短物理路径
	 * @author wxp
	 * @param  request
	 * @return String physicalPath:短物理路径
	 */
    public static String getPhysicalPath(HttpServletRequest request){
    	String physicalPath=request.getSession().getServletContext().getRealPath("/");
    	physicalPath=physicalPath.substring(0,physicalPath.indexOf("webapps")-1);
    	int index=physicalPath.lastIndexOf("\\");
    	if(index!=-1){
    		physicalPath=physicalPath.substring(0,index+1);
    	}
    	return physicalPath;
    }
    /**
	 * 获取全物理路径
	 * @author wxp
	 * @param  request
	 * @param  String folder:upload,zip,webpic等
	 * @param  String sitePath:site路径名
	 * @return String physicalPath:全物理路径
	 */
    public static String getFullPhysicalPath(HttpServletRequest request,String folder,String sitePath){
    	String physicalPath=request.getSession().getServletContext().getRealPath("/");
    	physicalPath=physicalPath.substring(0,physicalPath.indexOf("webapps")-1);
    	int index=physicalPath.lastIndexOf("\\");
    	if(index!=-1){
    		physicalPath=physicalPath.substring(0,index+1);
    	}
    	StringBuffer  sb=null;
    	sb=new StringBuffer();
    	sb.append(physicalPath);
    	sb.append("\\JNETDATA\\");
    	sb.append(folder);
    	sb.append("\\");
    	sb.append(sitePath);
    	sb.append("\\");
    	sb.append(CMSUtil.getTimeAccurateToMonth());
    	sb.append("\\");
    	sb.append(CMSUtil.getTimeAccurateToDay());
    	sb.append("\\");
    	physicalPath=sb.toString();
    	CMSUtil.createFolders(physicalPath);
    	return physicalPath;
    }
    /**
	 * 创建文件夹
	 * @author wxp
	 * @param  String path
	 * @return boolean bool
	 */
    public static boolean createFolders(String path){
    	boolean bool=false;
    	File file=new File(path);
    	if(!file.exists()){
    		try {
				file.mkdirs();
				bool=true;
			} catch (Exception e) {
				System.out.println("异常:上传图片文件夹创建错误!");
			}
    	} else {
    		bool = true;
    	}
    	return bool;
    }
    /**
	 * 删除文件夹
	 * @author wxp
	 * @param  String path
	 * @return boolean bool
	 */
    public static void delteFolders(String path){
    	File file = new File(path);
    	if(file.exists()){                    //判断文件是否存在
    	    if(file.isFile()){                    //判断是否是文件
    	     file.delete();                       //delete()方法 你应该知道 是删除的意思;
    	    }else if(file.isDirectory()){              //否则如果它是一个目录
    	     File files[] = file.listFiles();               //声明目录下所有的文件 files[];
    	     for(int i=0;i<files.length;i++){            //遍历目录下所有的文件
    	    	 files[i].delete();             //把每个文件 用这个方法进行迭代
    	     } 
    	    } 
    	    file.delete(); 
    	   }else{ 
    	    System.out.println("所删除的文件不存在！"+'\n'); 
    	   } 
    }
    /**
	 * 创建文件
	 * @author wxp
	 * @param  String path
	 */  
    public static boolean createFile(File filePath)throws FileNotFoundException{  
     boolean bool=false;  
     try{  
    	 if(!filePath.exists()){  
    		 filePath.createNewFile();  
    		 bool=true;  
    	 }  
     }catch(Exception e){  
    	 e.printStackTrace();  
     }  
     	return bool;  
    }   
    public static String getLogoFolder(){
    	String logoFolder="logo";
    	return  logoFolder;
    }
    public static int  getMaxLogoSize(){
    	int maxLogoSize=1024000;
    	return  maxLogoSize;
    }
    public static String getLogoEncoding(){
    	String  logoEncoding="UTF-8";
    	return logoEncoding;
    }
    public static String killNull(String str){
    	if(str==null){
    		str="";
    	}
    	return str;
    }
    /**
	 * 解析rightValue的List
	 * @author wxp
	 * @param List<Map<String,Object>> rightList:权限值的集合
	 * @param String rightValue:默认权限值
	 * @return boolean status:是否有权限
	 */
	public static boolean hasRight(List<Map<String,Object>> rightList,String rightValue){
		boolean status=false;
		if(rightList!=null&&rightList.size()>0){
			for(int i=0;i<rightList.size();i++){
				String currentRight=CMSUtil.stringFormat(rightList.get(i).get("RIGHTVALUE"));
				if(currentRight.contains(rightValue)){
					status=true;
					return status;
				}
			}
		}
		return status;
	}
	
	/**
	 * 给List定义长度
	 * @author wxp
	 * @param List<Map<String,Object>> list
	 * @param int size
	 * @return String
	 */
	public static int getListSize(List<Map<String,Object>> list,int size) {
			if(list!=null&&list.size()>0){
				return list.size();
			}else{
				return size;
			}
	}
	/**
	 * 给Map定义长度
	 * @author wxp
	 * @param Map<String,Object> map
	 * @param int size
	 * @return String
	 */
	public static int getMapSize(Map<String,Object> map,int size) {
			if(map!=null&&map.size()>0){
				return map.size();
			}else{
				return size;
			}
	}
	
	/**
	 * 判定是否为空
	 * @author wxp
	 * @param str
	 * @return
	 */
	public static boolean isEmpty(Object str) {
		if (str == null) {
			return true;
		}else if (str.toString().length() <= 0) {
			return true;
		} else {
			return false;
		}
	}
	/**
	 * 判定不为空
	 * @author wxp
	 * @param str
	 * @return
	 */
	public static boolean isNotEmpty(Object str) {
		if (str == null) {
			return false;
		}else if (str.toString().length() <= 0) {
			return false;
		} else {
			return true;
		}
	}
	/**
	 * 将内容添加到文件中
	 * @param file
	 * @param context 文件内容
	 * @param append  是否追加
	 * @return
	 */
	public static boolean createFile(String path, String filename, String context, boolean append) {
		if (context == null) {
			return false;
		}
		if (!createFolders(path)) {
			return false;
		}
		FileOutputStream out = null;
		BufferedOutputStream buffer = null;
		try {
			out = new FileOutputStream(new File(path + filename), append);
			buffer = new BufferedOutputStream(out);
			byte[] b = context.getBytes();
			buffer.write(b);
			return true;
		} catch (Exception e) {
			return false;
		} finally {
			if (buffer != null) {
				try {
					buffer.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	 /**
	 * 读取Properties配置文件
	 * @author wxp
	 * @param HttpServletRequest request
	 * @param String path
	 * @return Properties props
	 */
	public static Properties getProperties(HttpServletRequest request,String path){
		Properties props = new Properties();
	    try {
	    	 String realpath = request.getSession().getServletContext().getRealPath("/");
			 FileInputStream in = new FileInputStream(realpath+path);  
			 props.load(in);
		} catch (IOException  e) {
      		e.printStackTrace();
		}
		return props;
	}
	 /**
	 * 获取当前项目的根地址
	 * @author wxp
	 * @param HttpServletRequest request
	 * @return String
	 * @throws Exception
	 */
	public static String getServerPath(HttpServletRequest request){
		StringBuffer sb=null;
		sb=new StringBuffer();
		sb.append(request.getScheme());
		sb.append("://");
		sb.append(request.getServerName());
		sb.append(":");
		sb.append(request.getServerPort());
		sb.append(request.getContextPath());
		sb.append("/");
		String path=sb.toString();
		return path;
	}
	
	 /**
	 * 获取当前项目的根地址
	 * @author wxp
	 * @param HttpServletRequest request
	 * @return String
	 */
	public static String getServerPath(HttpServletRequest request,String extraPath){
		StringBuffer sb=null;
		sb=new StringBuffer();
		sb.append(request.getScheme());
		sb.append("://");
		sb.append(request.getServerName());
		sb.append(":");
		sb.append(request.getServerPort());
		sb.append(request.getContextPath());
		sb.append("/");
		sb.append(extraPath);
		String path=sb.toString();
		return path;
	}
	
	 /**
	 * 获取当前项目的域名
	 * @author wxp
	 * @param HttpServletRequest request
	 * @return String
	 */
	public static String getServerHost(HttpServletRequest request){
		StringBuffer sb=null;
		sb=new StringBuffer();
		sb.append(request.getScheme());
		sb.append("://");
		sb.append(request.getServerName());
		sb.append(":");
		sb.append(request.getServerPort());
		sb.append("/");
		String path=sb.toString();
		return path;
	}
	
	 /**
	 * 获取当前项目的物理路径
	 * @author wxp
	 * @param HttpServletRequest request
	 * @param String path
	 * @return String
	 */
	public static String getServerRelPath(HttpServletRequest request,String path){
		path=request.getSession().getServletContext().getRealPath(path);
		return path;
	}
	
	/** 
	  * 读TXT文件内容 
	  * @author wxp
	  * @param fileName 
	  * @return 
	  */  
	public static String readTxtFile(File fileName){  
	  String result=null;  
	  FileReader fileReader=null;  
	  BufferedReader bufferedReader=null;  
	  try{  
		  fileReader=new FileReader(fileName);  
		  bufferedReader=new BufferedReader(fileReader);  
    	  String read=null;  
    	  while((read=bufferedReader.readLine())!=null){  
    		  result=result+read+"\r\n";  
    	  }  
	  }catch(IOException e){  
		  e.printStackTrace();  
	  }finally{  
		  if(bufferedReader!=null){  
			  try {
				bufferedReader.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  
		  }  
		  if(fileReader!=null){  
			  try {
				fileReader.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  
		  }  
	  }  
	  return result;  
    }  
	/** 
	  * 添加TXT文件内容 
	  * @author wxp
	  * @param fileName 
	  * @return 
	  */ 
	 public static void addTextFile(String str,String  filePath){  
		  String s = new String();
		  StringBuffer sb = new StringBuffer();
		  File f = new File(filePath);
		  if(!f.getParentFile().exists()) {
			  f.getParentFile().mkdirs();
		  }
		  try {
			  if(!f.exists()){
				  f.createNewFile();//不存在则创建
		      }
			  //BufferedReader input = new BufferedReader(new FileReader(f));
			  InputStreamReader input = new InputStreamReader(new FileInputStream(f),"UTF-8");
			  BufferedReader reader=new BufferedReader(input);
			  while((s = reader.readLine())!=null){
			      sb.append(s);
			      sb.append("\r\n");
			  }
			  input.close();
			  sb.append(str);
			  //BufferedWriter output = new BufferedWriter(new FileWriter(f));
			  OutputStreamWriter output = new OutputStreamWriter(new FileOutputStream(f),"UTF-8");
			  output.write(sb.toString());
			  output.close();
		  }catch (IOException e) {
		      e.printStackTrace();
		  }
	}  
	 
	 /** 
	  * 创建TXT文件内容 
	  * @author wxp
	  * @param fileName 
	  * @return 
	  */ 
	 public static void createTextFile(String str,String  filePath){  
		  File f = new File(filePath);
		  if(!f.getParentFile().exists()) {
			  f.getParentFile().mkdirs();
		  }
		  try {
			  if(f.exists()){
				  f.delete();//存在则删除
		      }
			  f.createNewFile();//创建文件
			  OutputStreamWriter output = new OutputStreamWriter(new FileOutputStream(f),"UTF-8");
			  //BufferedWriter output = new BufferedWriter(new FileWriter(f));
			  output.write(str);
			  output.close();
		  }catch (IOException e) {
		      e.printStackTrace();
		  }
	}  
	 
	 /** 
	  * 判断字符串长度并替换内容
	  * @author wxp 
	  * @param String str
	  * @param int length
	  * @param String repStr
	  * @return 
	  */ 
	 public static String getByteLength(String str,int length,String repStr){  
		    int valueLength = 0; 
		    String value="";
	        String chinese = "[\u4e00-\u9fa5]";  
	        // 获取字段值的长度，如果含中文字符，则每个中文字符长度为2，否则为1  
	        for (int i = 0; i < str.length(); i++) {  
	            // 获取一个字符  
	            String temp = str.substring(i, i + 1);  
	            // 判断是否为中文字符  
	            if (temp.matches(chinese)) {  
	                // 中文字符长度为1  
	                valueLength += 2;  
	            } else {  
	                // 其他字符长度为0.5  
	                valueLength += 1;  
	            }  
	            if(valueLength>length){
	            	value=str.substring(0,i)+repStr;
	            	return value;
	            }
	        }  
	        return  str;
	     /*
	        if(str==null){
				return "";
			}else if(str.length()*2 <=length){
	            return str;  
	        }else{
		        char[] chArr = str.toCharArray();  
		        int lenByte = 0;
		        if(repStr==null){
		        	repStr="...";
		        }
		        for (int i = 0; i < chArr.length; i++) {
		            if (chArr[i] > 255) { 
		                lenByte += 2;
		            } else {  
		                ++lenByte;  
		            }
		            if(lenByte>=length){  
		                if(lenByte==length){  
		                    return str.substring(0,i+1)+repStr;  
		                }else{
		                	return str.substring(0,i)+repStr; 
		                } 
		            }  
		        }
		        return str;  
	        }*/
	 }  
	 
	 /** 
	  * 字符串替换双引号
	  * @author wxp 
	  * @param String str
	  * @return String
	  */ 
	 public static String getTransferStr(String str){  
		StringBuffer sb=null;
		sb=new StringBuffer();
		if(str!=null&&str.length()>0){
			char[] charArr=str.toCharArray();
			for(int i=0;i<charArr.length;i++){
				if('\"'!=charArr[i]){
					sb.append(charArr[i]);
				}else {
					sb.append("&quot;");
				}
			}
			return sb.toString();
		}
			return "";
	 }  
	 
	 /** 
	  * 字符串subString
	  * @author wxp 
	  * @param String str
	  * @param int startIndex
	  * @param int endIndex
	  * @return String
	  */ 
	 public static String subString(String str,int startIndex,int endIndex){  
		int length=endIndex+1;
		if(str!=null&&(str.length()>=length)){
			str=str.substring(startIndex,endIndex);
		}
		return str;
	 }  
	 /** 
	  * 字符串缩写
	  * @author wxp 
	  * @param String str 原字符串
	  * @param num 保留长度
	  * @return String 新字符串
	  */ 
	 public static String shortString(String str,int num){  
		String suffix = "...";
		if(str.length()>num){
			str=str.substring(0,num)+suffix;
		}
		return str;
	 }  
	 
	 /** 
	  * Object转字符串subString
	  * @author wxp 
	  * @param Object str
	  * @param int startIndex
	  * @param int endIndex
	  * @return String
	  */ 
	 public static String subString(Object obj,int startIndex,int endIndex){ 
		String str="";
		if(obj!=null){
			str=obj.toString();
			if(str.length()>=(endIndex+1)){
				str=str.substring(startIndex,endIndex);
			}
		}
		return str;
	 }  
	 
	 
	 /** 
	  * 日期加月
	  * @author wxp 
	  * @param Object str
	  * @param int startIndex
	  * @param int endIndex
	  * @return String
	  */ 
	 public static Date addMonth(Date date,int num){ 
		  //SimpleDateFormat sdf = new SimpleDateFormat(format);//格式化对象
		  Calendar calendar = Calendar.getInstance();//日历对象
		  calendar.setTime(date);//设置当前日期
		  //calendar.set(calendar.YEAR, calendar.MONTH, calendar.DATE);
		  calendar.add(Calendar.MONTH, num);//月
		  date=calendar.getTime();//输出格式化的日期
		  return date;
	 }  
	 
	 /** 
	  * 日期加年
	  * @author wxp 
	  * @param Object str
	  * @param int startIndex
	  * @return String
	  */ 
	 public static Date addYear(Date date,int num){ 
		  //SimpleDateFormat sdf = new SimpleDateFormat(format);//格式化对象
		  Calendar calendar = Calendar.getInstance();//日历对象
		  calendar.setTime(date);//设置当前日期
		  //calendar.set(calendar.YEAR, calendar.MONTH, calendar.DATE);
		  calendar.add(Calendar.YEAR, num);//年
		  date=calendar.getTime();//输出格式化的日期
		  return date;
	 }  
	 
	 /** 
	  * 日期加日
	  * @author wxp 
	  * @param Object str
	  * @param int startIndex
	  * @return String
	  */ 
	 public static Date addDay(Date date,int num){ 
		  //SimpleDateFormat sdf = new SimpleDateFormat(format);//格式化对象
		  Calendar calendar = Calendar.getInstance();//日历对象
		  calendar.setTime(date);//设置当前日期
		  //calendar.set(calendar.YEAR, calendar.MONTH, calendar.DATE);
		  calendar.add(Calendar.DATE, num);//日
		  date=calendar.getTime();//输出格式化的日期
		  return date;
	 }  
	 
	 /** 
	  * 日期加年/月/日
	  * @author wxp 
	  * @param Object str
	  * @param int startIndex
	  * @return String
	  */ 
	 public static Date addTime(Date date,int year,int month ,int day){ 
		  //SimpleDateFormat sdf = new SimpleDateFormat(format);//格式化对象
		  Calendar calendar = Calendar.getInstance();//日历对象
		  calendar.setTime(date);//设置当前日期
		  //calendar.set(calendar.YEAR, calendar.MONTH, calendar.DATE);
		  calendar.add(Calendar.YEAR, year);//年
		  calendar.add(Calendar.MONTH, month);//月
		  calendar.add(Calendar.DATE, day);//日
		  date=calendar.getTime();//输出格式化的日期
		  return date;
	 } 
	 
	 /** 
	  * 每月第一天
	  * @author wxp 
	  * @param Object str
	  * @param int startIndex
	  * @return String
	  */ 
	 public static Date getMonthFirstDay(){ 
		  Date date = new Date(); 
		  Calendar calendar = Calendar.getInstance();//日历对象
		  calendar.setTime(date);//设置当前日期
		  calendar.set(calendar.YEAR, calendar.MONTH, calendar.DATE);
		  calendar.set(Calendar.DAY_OF_MONTH, 1);//日
		  calendar.set(Calendar.HOUR_OF_DAY, 0);//日
		  calendar.set(Calendar.MINUTE, 0);//日
		  calendar.set(Calendar.SECOND, 0);//日
		  date=calendar.getTime();//输出格式化的日期
		  return date;
	 }  
	 
	 /** 
	  * 每月第一天
	  * @author wxp 
	  * @param Object str
	  * @param int startIndex
	  * @return String
	  */ 
	 public static String getMonthFirstDayStr(){ 
		  Date date = new Date(); 
		  SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM");
		  String dateStr=sdf.format(date);
		  dateStr=dateStr+"-01";
		  return dateStr;
	 } 
	 
	 /** 
	  * 当前日期截止到日
	  * @author wxp 
	  * @return Date
	  */ 
	 public static Date getDateShort() throws ParseException{   
		 SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		 Date date = new Date(); 
		 String dateStr = sdf.format(date); 
		 date=sdf.parse(dateStr);
		 return date;
	} 
	/**
	 * 当前session中是否存在已登录的用户
	 * @author wxp 
	 */
	public  boolean checkSession(HttpServletRequest request,String userName){
	    boolean issession = true;
	    Hashtable hashtable= (Hashtable)request.getSession().getServletContext().getAttribute("idlist");
	    HashMap hashMap=(HashMap)request.getSession().getServletContext().getAttribute("sessionid");
	    synchronized(this){
	    	Object obj = hashtable.get(userName);
	        if(obj!=null){
	        	//这里使用Hashtable因为 Hashtable本身是synchronized 所以为了方便就使用Hashtable
	        	//如果自己编写一个类实现了synchronized 并且只是放入String[只是验证登录名称] 效果会更好
	        	//如果不是null就已经可以判断出来登录了，如果想进一步判断登录信息，这里做处理
	        	//.............
	        	issession=false;
	        }else{
	        	//放入上下文
	            //SessionModel sessionModel=new SessionModel();
	            //sessionModel.setPhone("12345678");
	        	//UserInfo userInfo = new UserInfo();
	            hashtable.put(userName,request.getSession().getId());
	            hashMap.put(request.getSession().getId(),userName);
	        }
	    }
	    return issession;
	}
	
	public static String getSessionId(HttpServletRequest request,String userName){
		Hashtable hashtable= (Hashtable)request.getSession().getServletContext().getAttribute("idlist");
		String sessionId = "";
		sessionId = CMSUtil.stringFormat(hashtable.get(userName));
		return sessionId;
	}
	
	 /** 
	  * 获取随机数
	  * @author wxp 
	  * @return String
	  */ 
	 public static String getRandom(){
		 Random random=new Random();
		 String randomStr=System.currentTimeMillis()+"_"+ random.nextInt(999999);
		 return randomStr;
	}
	
	 /** 
	  * 校验参数
	  * @author wxp 
	  * @param String str
	  * @return boolean
	  */ 
	 public static boolean checkParam(String str){
		 if(str!=null&&str.trim().length()>0){
			 return true;
		 }else{
			 return false;
		 }
	} 
	
	 /** 
	  * 校验参数
	  * @author wxp 
	  * @param String[] strArr
	  * @return boolean
	  */ 
	 public static boolean checkParams(String[] strArr) throws Exception{
		 if(strArr!=null&&strArr.length>0){
			 for(int i=0;i<strArr.length;i++){
				 if(!CMSUtil.checkParam(strArr[i])){
					 return false; 
				 }
			 }
			 return true;
		 }else{
			 return false;
		 }
	} 
	
	 
	public static void main(String[] args) throws Exception{
		/*RightService rightService=new RightService();
		List<Map<String,Object>> lst=rightService.getSeniorRight(0, 1, 68);
		boolean bool=CMSUtil.hasRight(lst, "12");
		//System.out.println(bool);
		String str=CMSUtil.getByteLength("''''''''''''",10,"***");*/
		//String str=CMSUtil.getTransferStr("\"\"\"\"'''''''''''");
		//System.out.println(str);
		//Date date1=CMSUtil.addMonth( new Date(),1);
		//Date date2=CMSUtil.addYear( new Date(),1);
		//Date date3=CMSUtil.addDay( new Date(),1);
		//Date date4=CMSUtil.addTime(new Date(), 1, 1, 1);
		//SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		/*System.out.println(sdf.format(date1));
		//System.out.println(sdf.format(date2));
		//System.out.println(sdf.format(date3));
		//System.out.println(sdf.format(date4));
		String date=CMSUtil.getMonthFirstDayStr();
		//System.out.println(CMSUtil.getFileName("doc"));
		//System.out.println(date);*/
		//System.out.println(CMSUtil.subString("1899-12-30 16:00:00.0", 0, 19));
		//System.out.println("1899-12-30 16:00:00.0".substring(0, 19));
		/*List list = getAllChannelById(1103);
		for(Object o :list){
			//System.out.println(CMSUtil.longFormat(o, 0));
		}*/
		System.out.println(String.format("%.2f", 12.234567f));
		System.out.println(String.format("%.2fs", 0.00));
	}
}
