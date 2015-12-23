<%@page import="com.pojo.Project"%>
<%@page import="java.util.List"%>
<%@page import="com.pojo.User"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<% 

User user=(User)request.getSession().getAttribute("loginUserInfo");
List<User> users=(List<User>)request.getAttribute("users");
List<Project> projects=(List<Project>)request.getAttribute("projects");
%>
<!DOCTYPE html>
<html lang="zh"  class="no-js">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"> 
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>BUG提交录入</title>
	<link rel="stylesheet" type="text/css" href="file/css/normalizeluru.css" />
	<link rel="stylesheet" type="text/css" href="file/css/defaultluru.css">
	<link rel='stylesheet' href='file/css/font-awesome.min.css'>
	<link href='file/css/lurufontcss.css' rel='stylesheet' type='text/css'>
	<link rel="stylesheet" type="text/css" href="file/css/stylesluru.css">
	<script src="file/js/jquery-1.8.3.min.js"></script>
	<script src="file/laydate/laydate.js" type="text/javascript"></script>
	<script src="file/layer/layer.min.js"></script>
	<script src="file/js/modernizr.js"></script> <!-- Modernizr -->
	<script src="file/js/mymain.js"></script> <!-- Modernizr -->
	<script src="file/js/My97DatePicker/WdatePicker.js" charset="UTF-8" type="text/javascript"></script>
	<script src="file/js/mymain.js"></script> <!-- Modernizr -->
	<!--[if IE]>
		<script src="file/js/html5shiv.min.js"></script>
	<![endif]-->
	
	<link rel="stylesheet" type="text/css" href="file/css/imgupload/css/reset.css">
    <link rel="stylesheet" type="text/css" href="file/css/imgupload/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="file/css/imgupload/css/default.css">
    <link rel="stylesheet" type="text/css" href="file/css/imgupload/css/jquery.filer.css">
    <link rel="stylesheet" type="text/css" href="file/css/imgupload/css/jquery.filer-dragdropbox-theme.css">
    <link rel="stylesheet" type="text/css" href="file/css/imgupload/css/tomorrow.css">
    <link rel="stylesheet" type="text/css" href="file/css/imgupload/css/custom.css">
	
	<script> 
	  function getFullPath(obj){ 
	  	if(obj){ 
			 //ie 
			 if (window.navigator.userAgent.indexOf("MSIE")>=1){ 
	                obj.select(); 
	 				return document.selection.createRange().text; 
			 //firefox 
	         }else if(window.navigator.userAgent.indexOf("Firefox")>=1){ 
				if(obj.files){ 
					return obj.files.item(0).getAsDataURL(); 
	            } 
				 return obj.value; 
	         } 
	 		 return obj.value; 
	     }
	  }
 </script> 
</head>
<body>
<div class="row">
    <div class="col-md-6" style="margin-left: 200px;">
    <form action="BugsController.do?m=uploadImg" method="post" enctype="multipart/form-data" onsubmit="return checkdata()">

		<article class="htmleaf-container">
			<div class="htmleaf-content">
					  <!--  General -->
					  <div class="form-group">
					    <h1 class="heading">BUG信息清单</h1>
					    <br/>
					    <div class="controls">
					      <input type="text" id="title" class="floatLabel" name="title">
					      <label for="title">标题</label>
					    </div>
					    <div class="controls">
					      <textarea name="description" class="floatLabel" id="description"></textarea>
					      <label for="description">BUG现象详细描述</label>
					    </div>
					    <div class="controls">
					      <i class="fa fa-sort"></i>
					      <select class="floatLabel" id="projectId" name="projectId">
					        <option value="0"></option>
					       <%
					        if(projects!=null&&projects.size()!=0){
						        for(int i=0;i<projects.size();i++){
						        	Project item=projects.get(i);
						        	if(item.getIslive().equals("1")){
						        		
						    %>
					        <option value="<%=item.getId()%>"><%=item.getPro_name()%></option>
						    <%
						        	}
						        }
					        }
					        %>
					      </select>
					      <label for="projectId">BUG所属项目</label>
					    </div>
					    <div class="controls">
					      <input name="occurtime" class="floatLabel" id="occurtime" type="text"  onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" />
					      <label for="occurtime">发生时间</label>
					    </div>
					    
					    <div class="controls">
					      <i class="fa fa-sort"></i>
					      <select class="floatLabel" id="leave" name="leave">
					        <option value="0"></option>
					        <option value="1">Ⅰ级(建议性,不影响使用，但是可以优化,无需立即修复)</option>
					        <option value="2">Ⅱ级(影响使用,需要尽快修复)</option>
					        <option value="3">Ⅲ级(严重影响使用,不立即修复无法使用)</option>
					      </select>
					      <label for="leave">BUG等级</label>
					    </div>
					    
					    
					    <div class="controls">
					      <i class="fa fa-sort"></i>
					      <select class="floatLabel" id="userid" name="userid">
					        <option value="0"></option>
					        <%
					        if(users!=null&&users.size()!=0){
						        for(int i=0;i<users.size();i++){
						        	User item=users.get(i);
						        	if(!item.getUserid().equals(user.getUserid())){
						    %>
					        <option value="<%=item.getUserid()%>"><%=item.getUname()%></option>
						    <%
						        	 }
						        }
					        }
					        %>
					      </select>
					      <label for="userid">指定修复开发人员</label>
					     <!--  <button onclick="admewbug();">提交</button> -->
					    </div>
					    
					  </div>
					  
		                
		                
				</div>
			</article>
                               
            <h3 class="heading">BUG截图</h3>
            <input type="file" name="files[]" id="demo-fileInput-4" multiple="multiple">
            <input type="submit" id="submit" style="width: 100%;height: 50px;font-size: 20px;"  class="btn-custom orange" value="提交">
      </form>
  </div>
</div>


<script src="file/js/jquery.min.js" type="text/javascript"></script>
	<script>window.jQuery || document.write('<script src="file/js/jquery-2.1.1.min.js"><\/script>')</script>
	<script>
	(function ($) {
	    function floatLabel(inputType) {
	        $(inputType).each(function () {
	            var $this = $(this);
	            $this.focus(function () {
	                $this.next().addClass('active');
	            });
	            $this.blur(function () {
	                if ($this.val() === '' || $this.val() === 'blank') {
	                    $this.next().removeClass();
	                }
	            });
	        });
	    }
	    floatLabel('.floatLabel');
	}(jQuery));
	
	function checkdata(){
		var title=$("#title").val();
		if (title==''||title.trim=='') {
			layer.tips('标题不能为空！', '#title',{ guide: 0,time: 3});
			$("#title").css("border","1px solid red");
			$("#title").focus();
			return false;
		}
		$("#title").css("border","1px solid #e7e7e7");
		var description=$("#description").val();
		if (description==''||description.trim=='') {
			layer.tips('BUG描述不能为空！', '#description',{ guide: 0,time: 3});
			$("#description").css("border","1px solid red");
			$("#description").focus();
			return false;
		}
		$("#description").css("border","1px solid #e7e7e7");
		
		var projectId=$("#projectId").val();
		if (projectId=='0'||projectId.trim=='0') {
			layer.tips('BUG描述不能为空！', '#projectId',{ guide: 0,time: 3});
			$("#projectId").css("border","1px solid red");
			$("#projectId").focus();
			return false;
		}
		$("#projectId").css("border","1px solid #e7e7e7");
		
		var occurtime=$("#occurtime").val();
		if (occurtime==''||occurtime.trim=='') {
			layer.tips('BUG描述不能为空！', '#occurtime',{ guide: 0,time: 3});
			$("#occurtime").css("border","1px solid red");
			$("#occurtime").focus();
			return false;
		}
		$("#occurtime").css("border","1px solid #e7e7e7");
		
		
		var leave=$("#leave").val();
		if (leave=='0'||leave.trim=='0') {
			layer.tips('BUG描述不能为空！', '#leave',{ guide: 0,time: 3});
			$("#leave").css("border","1px solid red");
			$("#leave").focus();
			return false;
		}
		$("#leave").css("border","1px solid #e7e7e7");
		
		var userid=$("#userid").val();
		if (userid=='0'||userid.trim=='0') {
			layer.tips('BUG描述不能为空！', '#userid',{ guide: 0,time: 3});
			$("#userid").css("border","1px solid red");
			$("#userid").focus();
			return false;
		}
		$("#userid").css("border","1px solid #e7e7e7");
		
		return true;
	}
	</script>
	
	
	<script src="file/css/imgupload/js/bootstrap.min.js" type="text/javascript"></script>
    <script src="file/css/imgupload/js/jquery.filer.min.js" type="text/javascript"></script>
    <script src="file/css/imgupload/js/prettify.js" type="text/javascript"></script>
    <script src="file/css/imgupload/js/scripts.js" type="text/javascript"></script>
    <script src="file/css/imgupload/js/custom.js" type="text/javascript"></script>
</body>
</html>