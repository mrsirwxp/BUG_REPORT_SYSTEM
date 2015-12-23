<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="zh">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"> 
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>BUG管理系统登录</title>
	<link rel="stylesheet" type="text/css" href="file/css/normalize.css" />
	<link rel="stylesheet" type="text/css" href="file/css/default.css">
	<script src="file/js/jquery-2.1.1.min.js"></script>
	<script src="file/laydate/laydate.js" type="text/javascript"></script>
	<script src="file/js/layer2.01/layer/layer.js"></script>
	<link rel="stylesheet" type="text/css" href="file/css/styles.css">
	<!--[if IE]>
		<script src="file/js/html5shiv.min.js"></script>
	<![endif]-->
	<script type="text/javascript">
	$(function(){
		var msg='${msgg}';
		if(msg!='0'){
			$("#username").val('${username}');
			$("#email").val('${email}');
			$("#juese").val('${juese}');
			layer.msg(msg,function(){});
		}
	});
	</script>
</head>
<body>
	<div id="logo"> 
	  <h1 class="hogo"><i>BUG管理系统注册</i></h1>
	</div> 
	<section class="stark-login">
  <form action="login.do" method="post">	
    <div id="fade-box">
      		<input type="text" name="username" id="username" placeholder="用户姓名" required>
      		<input type="email" name="email" id="email" placeholder="邮箱" required>
        	<input type="password" placeholder="密码" name="password" required>
        	<input type="password" placeholder="确认密码" name="passwordAgain" required>
        	<select required name="juese" id="juese">
        	<option value="0">请选择角色</option>
        	<option value="1">开发者</option>
        	<option value="2">测试者</option>
        	</select>
            <input type="hidden" name="m" value="regUser" />
            <button>注册</button>
            <span style="color: white;">已有账号？现在<a href="login.do">《登录》</a></span>
     </div>
   </form>
      <div class="hexagons">
        <span>&#x2B22;</span>
        <span>&#x2B22;</span>
        <span>&#x2B22;</span>
        <span>&#x2B22;</span>
        <span>&#x2B22;</span>
        <span>&#x2B22;</span>
        <span>&#x2B22;</span>
        <span>&#x2B22;</span>
        <span>&#x2B22;</span>
        <span>&#x2B22;</span>
        <span>&#x2B22;</span>
        <span>&#x2B22;</span>
        <br>
          <span>&#x2B22;</span>
          <span>&#x2B22;</span>
          <span>&#x2B22;</span>
          <span>&#x2B22;</span>
          <span>&#x2B22;</span>
          <span>&#x2B22;</span>
          <span>&#x2B22;</span>
          <span>&#x2B22;</span>
          <span>&#x2B22;</span>
          <span>&#x2B22;</span>
          <span>&#x2B22;</span>
          <br>
            <span>&#x2B22;</span>
            <span>&#x2B22;</span>
            <span>&#x2B22;</span>
            <span>&#x2B22;</span> 
            <span>&#x2B22;</span>
            <span>&#x2B22;</span>
            <span>&#x2B22;</span>
            <span>&#x2B22;</span>
            <span>&#x2B22;</span>
            <span>&#x2B22;</span>
            <span>&#x2B22;</span>
            <span>&#x2B22;</span>
            
            <br>
              <span>&#x2B22;</span>
              <span>&#x2B22;</span>
              <span>&#x2B22;</span>
              <span>&#x2B22;</span>
              <span>&#x2B22;</span>
              <span>&#x2B22;</span>
              <span>&#x2B22;</span>
              <span>&#x2B22;</span>
              <span>&#x2B22;</span>
              <span>&#x2B22;</span>
              <span>&#x2B22;</span>
              <br>
                <span>&#x2B22;</span>
                <span>&#x2B22;</span>
                <span>&#x2B22;</span>
                <span>&#x2B22;</span>
                <span>&#x2B22;</span>
                <span>&#x2B22;</span>
                <span>&#x2B22;</span>
                <span>&#x2B22;</span>
                <span>&#x2B22;</span>
                <span>&#x2B22;</span>
                <span>&#x2B22;</span>
                <span>&#x2B22;</span>
              </div>      
        </section> 
    
        <div id="circle1">
          <div id="inner-cirlce1">
            <h2> </h2>
          </div>
        </div>
    <ul>
      <li></li>
      <li></li>
      <li></li>
      <li></li>
      <li></li>
    </ul>
    
    <header class="htmleaf-header">
			<h1>版权所有  中国铁路信息技术中心 <span>1975-2015 All Right Received</span></h1>
			<div class="htmleaf-links">
				<a class="htmleaf-icon icon-htmleaf-home-outline" href="http://www.12306.cn/" title="12306网站首页" target="_blank"><span> 12306网站首页</span></a>
				<a class="htmleaf-icon icon-htmleaf-arrow-forward-outline" href="http://www.12306.cn" title="注册用户" target="_blank"><span>注册用户</span></a>
			</div>
	</header>

</body>
</html>