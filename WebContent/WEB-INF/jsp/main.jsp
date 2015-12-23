<%@page import="com.pojo.User"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<% 

User user=(User)request.getSession().getAttribute("loginUserInfo");

%>
<!DOCTYPE html>
<html lang="zh"  class="no-js">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"> 
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>BUG提交系统</title>
	<link rel="stylesheet" href="file/css/reset.css"> <!-- CSS reset -->
	<link rel="stylesheet" type="text/css" href="file/css/bugdefault.css">
	<link rel="stylesheet" href="file/css/bugstyle.css"> <!-- Resource style -->
	<script src="file/js/jquery-1.8.3.min.js"></script>
	<script src="file/laydate/laydate.js" type="text/javascript"></script>
	<script src="file/layer/layer.min.js"></script>
	<script src="file/js/modernizr.js"></script> <!-- Modernizr -->
	<script src="file/js/mymain.js"></script> <!-- Modernizr -->
	<!--[if IE]>
		<script src="file/js/html5shiv.min.js"></script>
	<![endif]-->
</head>
<body>
	<header class="cd-main-header">
		<a href="" class="cd-logo">BUG管理系统</a>
		
		<div class="cd-search is-hidden">
			<form action="#0">
				<input type="search" placeholder="Search...">
			</form>
		</div> <!-- cd-search -->

		<a href="#0" class="cd-nav-trigger">Menu<span></span></a>

		<nav class="cd-nav">
			<ul class="cd-top-nav">
				<li class="has-children account">
					<a href="#0">
						<img src="<%=user.getImgsrc() %>" alt="avatar">
						<%=user.getUname() %>
					</a>

					<ul>
						<li><a href="javascript:void(0);" onclick="myAccount();">我的帐户</a></li>
						<li><a href="javascript:void(0);" onclick="projectMangeList();">项目管理</a></li>
						<li><a href="login.do?m=logout">退出系统</a></li>
					</ul>
				</li>
			</ul>
		</nav>
	</header> <!-- .cd-main-header -->
	
	<main class="cd-main-content">
		<nav class="cd-side-nav">
			<ul>
				<li class="cd-label icon-htmleaf-home-outline">主菜单</li>
				<li class="has-children overview">
					<a href="javascript:void(0);" onclick="addnewbug();">提交新BUG</a>
				</li>
				<li class="has-children notifications overview">
					<a href="javascript:void(0);" onclick="bugToMe();">待我处理BUG<span class="count" id="toMe">${bugToMe}</span></a>
				</li>
				<li class="has-children overview">
					<a href="javascript:void(0);" onclick="mySubmitBug();">已提交BUG<span class="count" id="submitted">${mySubmitBug}</span></a>
				</li>
				<li class="has-children overview">
					<a href="javascript:void(0);" onclick="stillNotSolvedBug();">尚未解决的BUG<span class="count" id="existed">${stillNotSolvedBug}</span></a>
				</li>
				<li class="has-children overview">
					<a href="javascript:void(0);" onclick="allSolvedBug();">已解决的BUG<span class="count" id="solved">${allSolvedBug}</span></a>
				</li>
				<li class="has-children overview comments">
					<a href="javascript:void(0);" onclick="showallbugs();">全部BUG<span class="count" id="allBug">${allBugCount}</span></a>
				</li>

			</ul>
		</nav>

		<div class="content-wrapper">
			<iframe src="login.do?m=welcome" style="width:100%; height: 923px;"   name="TrainComplaint" id="TrainComplaint"></iframe>
		</div> <!-- .content-wrapper -->
	</main> <!-- .cd-main-content -->
   
    <script src="file/js/jquery.min.js" type="text/javascript"></script>
	<script>window.jQuery || document.write('<script src="file/js/jquery-2.1.1.min.js"><\/script>')</script>
	<script src="file/js/jquery.menu-aim.js"></script>
	<script src="file/js/main.js"></script> <!-- Resource jQuery -->
	<script type="text/javascript">
	$(function() {
		getMM();
	});
	function getMM(){
		refresh();
		var random = 3;
		do {
			random = Math.floor(Math.random()*30+1);
		}
		while (random<=0);
		random += 30000;
		setTimeout("getMM()",random);
	}
	function refresh() {
		$.ajax({
		      url:"BugsController.do?m=getCount", 
		      // url:"dict_unitajax.mor?action=msg", 
		       dataType:"JSON",
		       data:{uid:'<%=user.getUserid()%>'},
		       error:function(){
		    	   layer.msg('数据库连接有问题，未取到提示信息.');
		       },
		       success:function(data){
		    	   $("#toMe").text(data.toMe);
		    	   $("#existed").text(data.existed);
		    	   $("#submitted").text(data.submitted);
		    	   $("#solved").text(data.solved);
		    	   $("#allBug").text(data.allBug);
		       }
		 });
	}
	
	
	function myAccount(){
		$("#TrainComplaint").attr("src","login.do?m=myAccount");
	}
	</script>

</body>
</html>