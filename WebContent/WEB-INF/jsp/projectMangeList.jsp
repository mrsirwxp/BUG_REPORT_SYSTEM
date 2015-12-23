<%@page import="com.util.BugData"%>
<%@page import="com.pojo.User"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<% 

User user=(User)request.getSession().getAttribute("loginUserInfo");

%>
<!DOCTYPE html>
<html lang="zh"  class="no-js">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"> 
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>BUG管理系统</title>
	<link rel="stylesheet" type="text/css" href="file/css/projectMangement.css">
	<script src="file/js/jquery-1.8.3.min.js"></script>
	<script type="text/javascript" src="file/js/layer2.01/layer/layer.js"></script>
	<script type="text/javascript">
		function addPro(){
			var proName=$("#proName").val();
			proName=proName.replace(new RegExp(" ",'gm'),'');
			if(proName==''){
			layer.msg("项目名称不能为空！",function(){});
			$("#proName").val("");
			$("#proName").focus();
			return
			}
			var proDescript=$("#proDescript").val();
			var data={
					proName:proName,
					proDescript:proDescript
			}
			$.ajax({
				url:"BugsController.do?m=addPro",
				type:"post",
				data:data,
				dataType:"json",
				success:function(data){
					//layer.msg(data.proId);
					if(data.proId=='error'){
						layer.msg("录入失败，稍后重试！",function(){});
						return;
					}
					var html='<div class="project">';
					html+='<span class="numbercount">'+data.count+'</span>';
					html+='<span>'+proName+'</span>';
					html+='<label class="buttonn">';
					html+='<input type="checkbox"  onchange="delPro(this,'+data.proId+');"  checked="checked" >';
					html+='<span></span>';
					html+='<span></span>';
					html+='<span></span>';
					html+='</label>';
					html+='</div>';
					$("#contentner").append(html);
				}
			});
		}
		function delPro(proId,id){
			
			if($(proId).attr('checked')=='checked'){
				$.ajax({
					url:"BugsController.do?m=activePro",
					type:"post",
					data:{id:id},
					dataType:"text",
					success:function(data){
					layer.msg("已激活！");
						
					}
				});
			}else{
				$.ajax({
					url:"BugsController.do?m=unActivePro",
					type:"post",
					data:{id:id},
					dataType:"text",
					success:function(data){
					layer.msg("已关闭！");
						
					}
				});
			}
		}
	</script>
</head>
<body>
<div class="tools">
<h1>项目管理页面</h1>
<button class="addpro" onclick="$('#newProj').slideToggle();">添加项目</button>
</div>
<div class="newProj" id="newProj">
	<div>
	项目名称：<input type="text" class="inputinput" placeholder="项目名称" id="proName"  required="required">
	</div>
	<div>
	项目描述：<input type="text"  class="inputinput"  placeholder="项目描述" id="proDescript">
	</div>
	<button class="addpro" style="margin-bottom: 10px;" onclick="addPro();">添加</button>
</div>
<div class="contentner" id="contentner">
		<div class="project" style="font-family: 黑体;">
		<span class="numbercount">序号</span>
		<span>项目名称</span>
		<span style="font-family: 黑体;float: right;margin-right:68px;">操作</span>
		</div>
	<c:forEach var="project" items="${projects }" varStatus="status">
		<div class="project">
		<span class="numbercount">${status.count}</span>
		<span>${project.pro_name}</span>
		<c:if test="${project.islive=='1'}">
			<label class="buttonn">
			  <input type="checkbox"  onchange="delPro(this,${project.id});" checked="checked">
			  <span></span>
			  <span></span>
			  <span></span>
			</label>
		</c:if>
		<c:if test="${project.islive!='1'}">
			<label class="buttonn">
			  <input type="checkbox"  onchange="delPro(this,${project.id});" >
			  <span></span>
			  <span></span>
			  <span></span>
			</label>
		</c:if>
		</div>
	</c:forEach>
</div>

</body>
</html>