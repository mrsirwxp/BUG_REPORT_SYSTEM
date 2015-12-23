<%@page import="com.util.BugData"%>
<%@page import="com.pojo.Comment"%>
<%@page import="java.util.List"%>
<%@page import="com.pojo.Bug"%>
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
    <link rel="stylesheet" type="text/css" href="file/css/imgupload/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="file/css/imgupload/css/jquery.filer.css">
    <link rel="stylesheet" type="text/css" href="file/css/imgupload/css/jquery.filer-dragdropbox-theme.css">
    <link rel="stylesheet" type="text/css" href="file/css/myAcount.css">
	<script src="file/js/jquery-1.8.3.min.js"></script>
	<script type="text/javascript" src="file/js/layer2.01/layer/layer.js"></script>

</head>
<body>
<div class="headd">
<a href="javascript:void(0);"><img alt="" id="uhead" src="<%=user.getImgsrc()%>" style="width: 110px;height: 110px;"></a>
</div>

<div class="right">

<table class="zebra">
	<tr>
		<td style="width: 30%;text-align: right;border-right: 3px solid #00ffff;">姓名</td><td><%=user.getUname()%></td>
	</tr>
	<tr>
		<td style="width: 30%;text-align: right;border-right: 3px solid #00ffff;">邮箱</td><td><%=user.getEmail()%></td>
	</tr>
</table>
<button onclick="$('#down').fadeToggle(1000);">↓修改资料↓</button>
</div>

<div class="down" id="down" >
	<table class="zebra">
		<tr>
			<td style="width: 30%;text-align: right;border-right: 3px solid #00ffff;">姓名</td><td><input type="text" id="username" placeholder="姓名" value="<%=user.getUname()%>"  required="required"></td>
		</tr>
		<tr>
			<td style="width: 30%;text-align: right;border-right: 3px solid #00ffff;">邮箱</td><td><input type="email" id="email" placeholder="邮箱" value="<%=user.getEmail()%>"  required="required"></td>
		</tr>
		<tr>
			<td style="width: 30%;text-align: right;border-right: 3px solid #00ffff;">旧密码</td><td><input type="password" id="passwordOld" placeholder="旧密码" required="required"></td>
		</tr>
		<tr>
			<td style="width: 30%;text-align: right;border-right: 3px solid #00ffff;">新密码</td><td><input type="password" id="passwordNew" placeholder="旧密码" required="required"></td>
		</tr>
	</table>
	<input type="file" name="files[]" id="uploadHead" multiple="multiple">
	<button onclick="updataUInfo();">确认修改</button>
</div>

<script src="file/js/myAcount.js" type="text/javascript"></script>
<script src="file/css/imgupload/js/bootstrap.min.js" type="text/javascript"></script>
    <script src="file/css/imgupload/js/jquery.filer.min.js" type="text/javascript"></script>
</body>
</html>