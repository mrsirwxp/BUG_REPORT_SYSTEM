<%@page import="com.util.BugData"%>
<%@page import="com.pojo.Comment"%>
<%@page import="java.util.List"%>
<%@page import="com.pojo.Bug"%>
<%@page import="com.pojo.User"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<% 

User user=(User)request.getSession().getAttribute("loginUserInfo");
Bug bug =(Bug)request.getAttribute("bug");
String bugLeave=bug.getLeavel();
if("1".equals(bugLeave)){
	bugLeave=BugData.bugLeve1;
}else if("2".equals(bugLeave)){
	bugLeave=BugData.bugLeve2;
}else if("3".equals(bugLeave)){
	bugLeave=BugData.bugLeve3;
}
List<User> users =(List<User>)request.getAttribute("users");
List<Comment> comments=bug.getComment();

%>
<!DOCTYPE html>
<html lang="zh"  class="no-js">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"> 
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>BUG管理系统</title>
<link rel="stylesheet" type="text/css" href="file/css/normalizeluru.css" />
	<link rel="stylesheet" type="text/css" href="file/css/imgupload/css/reset.css">
    <link rel="stylesheet" type="text/css" href="file/css/imgupload/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="file/css/imgupload/css/default.css">
    <link rel="stylesheet" type="text/css" href="file/css/imgupload/css/jquery.filer.css">
    <link rel="stylesheet" type="text/css" href="file/css/imgupload/css/jquery.filer-dragdropbox-theme.css">
    <link rel="stylesheet" type="text/css" href="file/css/imgupload/css/tomorrow.css">
    <link rel="stylesheet" type="text/css" href="file/css/imgupload/css/custom.css">
	<script src="file/js/jquery-1.8.3.min.js"></script>
	<script type="text/javascript" src="file/js/layer2.01/layer/layer.js"></script>
	<script type="text/javascript" charset="utf-8" src="file/js/editor/ueditor.config.js"></script>
    <script type="text/javascript" charset="utf-8" src="file/js/editor/ueditor.all.min.js"> </script>
    <!--建议手动加在语言，避免在ie下有时因为加载语言失败导致编辑器加载失败-->
    <!--这里加载的语言文件会覆盖你在配置项目里添加的语言类型，比如你在配置项目里配置的是英文，这里加载的中文，那最后就是中文-->
    <script type="text/javascript" charset="utf-8" src="file/js/editor/lang/zh-cn/zh-cn.js"></script>

</head>
<body>
<h2 style="text-align: center;font-family: 黑体;">${bug.title}</h2>
<p style="text-align:right;color:red;margin-right: 100px;"><span style="float: left;margin-left: 20px;color: red;"><%=bugLeave %></span><img alt="" src="${bug.user.imgsrc}"style="width: 26px;"/>${bug.user.uname}&nbsp;&nbsp;&nbsp;${bug.createtime}&nbsp;&nbsp;&nbsp;&nbsp;BUG编号:${bug.bugid}&nbsp;&nbsp;&nbsp;<img alt="状态" src="img/status.png" style="width: 18px;">
<c:if test="${bug.status==1}">
	<%=BugData.SubbmitAndIsRepairing %>
</c:if>
<c:if test="${bug.status==2}">
	<%=BugData.RepairedAndIsTesting %>
</c:if>
<c:if test="${bug.status==3}">
	<span style="color:#31D608;"> <%=BugData.Closed%> </span> 
</c:if>
<c:if test="${bug.status==4}">
	<%=BugData.NotPassTestAndReturnRepairing %>
</c:if>
<c:if test="${bug.status==5}">
	<%=BugData.ReopenAndRepairing %>
</c:if>
</p>
<hr  style="border: 1px solid #ff0000">
<p style="text-align: left;margin: 0 80px 0;font-family: 华文楷体;">
&nbsp;&nbsp;&nbsp;&nbsp;${bug.description}
</p>
<hr style="border:1px dashed silver; height:1px">
<div style="text-align:center;  margin: 0 80px 0;font-family: 华文楷体;" id="bugimgs">
<span style="float: left;">BUG截图</span><br/>
<c:if test="${bug.image!=null}">
	<c:forEach items="${bug.image}" var="imgg">
	<img layer-pid="" layer-src="${imgg.imgsrc }" src="${imgg.imgsrc }" width="100%"><br>
	</c:forEach>
</c:if>
<c:if test="${bug.image==null}">
	没有相关截图！
</c:if>
</div>

<hr style="border:1px dashed silver; height:1px">
<%
if(bug.getProcessuserid().equals(user.getUserid())){//登陆人是修复bug人
	if(bug.getStatus().equals("3")){
	%>
	<p style="padding-left:80px; font-family: 华文楷体;">
	<span style="color: green;">您已经修复了该BUG！</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	BUG所属项目:${bug.project.name}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	BUG发生时间:${bug.occurtime}
	</p>
	<%
	}else{
		%>
	<p style="padding-left:80px; font-family: 华文楷体;">
	<span style="color: red;">该BUG需要您来处理！</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	BUG所属项目:${bug.project.pro_name}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	BUG发生时间:${bug.occurtime}
	</p>
	<div style="padding-left:80px;">
	处理选项：<br/>
	<c:if test="${bug.status=='1' }">
	您目前是否修复了该BUG？
	<select id="isRepaired">
		<option value="否">否</option>
		<option value="是">是</option>
	</select>
	是否转交他人修复？
	<select id="repaireUser">
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
	<button id="subb" onclick="bugRepaired();" class="btn-custom orange">提交</button>
	</c:if>
	<c:if test="${bug.status=='4' }">
	您目前是否修复了该BUG？
	<select id="isRepaired">
		<option value="否">否</option>
		<option value="是">是</option>
	</select>
	<input type="hidden" id="repaireUser" value="0">
	<button id="subb" onclick="bugRepaired();" class="btn-custom orange">提交</button>
	</c:if>
	<c:if test="${bug.status=='5' }">
	您目前是否修复了该BUG？
	<select id="isRepaired">
		<option value="否">否</option>
		<option value="是">是</option>
	</select>
	<input type="hidden" id="repaireUser" value="0">
	<button id="subb" onclick="bugRepaired();" class="btn-custom orange">提交</button>
	</c:if>
	<c:if test="${bug.status=='2' }">
	您已修复该BUG，正在测试，请等待测试结果反馈！
	</c:if>
	</div>
		<%
	}
}else if(bug.getUser().getUserid().equals(user.getUserid())){//登陆人是创建BUG人
	if(bug.getStatus().equals("3")){
	%>
	<p style="padding-left:80px; font-family: 华文楷体;">
	<span style="color: green;">该BUG已经修复！</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	BUG所属项目:${bug.project.pro_name}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	BUG发生时间:${bug.occurtime}<br/>
	是否重新开启该BUG？
	<select id="isRepaired">
		<option value="否">否</option>
		<option value="是">是</option>
	</select>
	<button id="subb" onclick="reOpenBug();" class="btn-custom orange">提交</button>
	</p>
	<%
	}else{
		%>
	<p style="padding-left:80px; font-family: 华文楷体;">
	<span style="color: red;">该BUG需要您来处理！</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	BUG所属项目:${bug.project.pro_name}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	BUG发生时间:${bug.occurtime}
	</p>
<div style="padding-left:80px;">
	处理选项：<br/>
	<c:if test="${bug.status=='1' }">
	正在修复中，没有可处理的选项！
	</c:if>
	<c:if test="${bug.status=='4' }">
	正在修复中，没有可处理的选项！
	</c:if>
	<c:if test="${bug.status=='5' }">
	正在修复中，没有可处理的选项！
	</c:if>
	<c:if test="${bug.status=='2' }">
	经过您的测试验收，该BUG是否修复？
	<select id="isRepaired">
		<option value="否">否</option>
		<option value="是">是</option>
	</select>
	<button id="subb" onclick="isRepaired();" class="btn-custom orange">提交</button>
	</c:if>
</div>
		<%
	}
}else{//登陆人不是创建人也不是修复人
	%>
	<p style="padding-left:80px; font-family: 华文楷体;color: black;">
	BUG负责人:${bug.processedUser.uname}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	BUG所属项目:${bug.project.pro_name}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	BUG发生时间:${bug.occurtime}
	</p>
	<%
}

%>
<hr style="border:1px dashed silver; height:1px">
<div style="text-align:center;  margin: 0 80px 0;font-family: 华文楷体;" id="comments">
<span style="float: left;font-size: 20px;">评论</span><br/><br/><br/>
<%
if(comments==null||comments.size()==0){
	%>
	暂无评论！
	<%
}else{
	for(int i=0;i<comments.size();i++){
		Comment comment =comments.get(i);
		User usr=null;
		for(int j=0;j<users.size();j++){
			if(users.get(j).getUserid().equals(comment.getUserid())){
				usr=users.get(j);
			}
		}
		%>
		<div style="width: 100%;">
		<span style="float: left;">
		<%=i+1%>楼&nbsp;<img alt="" style="width: 46px;" src="<%=usr.getImgsrc()%>"><%=usr.getUname()%>&nbsp;&nbsp;
		<%=comment.getTime()%></span><br>
		<span style="float: left;">
		<%=comment.getContent()%>
		</span>
		</div>
		<br><br><br><br><br>
		<br><br><br><br><br>
		<hr style="border:1px dashed silver;">
		<%
	}
}
%>
</div>
<br>
<br><br>
<div style="text-align:center;  margin: 0 80px 0;font-family: 华文楷体;width: 80%;" id="addcomments">
<span style="float: left;"><b>添加评论</b></span><br/>
    <script id="editor" type="text/plain" style="padding-right: 50px;"></script>
<br>
<input type="submit" id="submit" style="width: 100%;height: 50px;font-size: 20px;" class="btn-custom orange" value="提交评论" onclick="addcomment();">
</div>

<script type="text/javascript">
$(function(){
	
	//加载扩展模块
	layer.config({
	    extend: 'extend/layer.ext.js'
	});
	layer.ready(function(){ 
	    //使用相册
	    layer.photos({
	        photos: '#bugimgs'
	    });
	});
	
});
function isRepaired(){
	var isRepaired=$("#isRepaired").val();
	if(isRepaired=='是'){
		var bugid='<%=bug.getBugid()%>';
		var data={bugid:bugid};
		var inde=layer.load();
		$.post('BugsController.do?m=isRepaired',data, null, 'json')
		.success(function(data) {
			layer.close(inde);
			location.reload();
		}).error(function(){
    		$.messager.alert( "错误", "系统错误", "error");
  	  	});
	}else{

		var bugid='<%=bug.getBugid()%>';
		var data={bugid:bugid};
		var inde=layer.load();
		$.post('BugsController.do?m=isRepaired',data, null, 'json')
		.success(function(data) {
			layer.close(inde);
			location.reload();
		}).error(function(){
    		$.messager.alert( "错误", "系统错误", "error");
  	  	});
	}
	
}

function bugRepaired(){
	var  isRepaired=$("#isRepaired").val();
	var  repaireUser=$("#repaireUser").val();
	if((repaireUser==null||repaireUser==''||repaireUser=='0')&&isRepaired=='否'){
		layer.msg("没有进行任何改变！");
	}else{
		if(repaireUser==null||repaireUser==''||repaireUser=='0'||repaireUser=='undefined'){
			repaireUser=='0';
		}
		
		if(repaireUser!='0'&&isRepaired=='是'){
			layer.alert("已修复的BUG不可转交他人！");
			return;
		}
		var inde=layer.load();
		var bugid='<%=bug.getBugid()%>';
		var data={bugid:bugid,repaireUser:repaireUser};
		$.post('BugsController.do?m=bugRepaired',data, null, 'json')
		.success(function(data) {
			layer.close(inde);
			location.reload();
		}).error(function(){
    		$.messager.alert( "错误", "系统错误", "error");
  	  	});
	}
}

function reOpenBug(){
	var isReOpenBug=$("#isRepaired").val();
	if(isReOpenBug=='否'){
		layer.msg("没有进行任何改变！");
	}else{
	var bugid='<%=bug.getBugid()%>';
	var data={bugid:bugid};
	var inde=layer.load();
	$.post('BugsController.do?m=reOpenBug',data, null, 'json')
	.success(function(data) {
		layer.close(inde);
		location.reload();
	}).error(function(){
		$.messager.alert( "错误", "系统错误", "error");
	  	});
	}
}
</script>

<script type="text/javascript">
    var ue = UE.getEditor('editor');
    
    var bugid='<%=bug.getBugid()%>';
    var uid='<%=user.getUserid()%>';
    function addcomment(){
    	var contents=ue.getContent();
    	if(!ue.hasContents()){
    		layer.msg("内容不能为空！");
    		ue.focus();
    		return;
    	}
    	var inde=layer.load();
    	$.post('BugsController.do?m=addcomment',{contents:contents,bugid:bugid,uid:uid}, null, 'json')
		.success(function(data) {
			layer.close(inde);
			location.reload();
			//alert("成功")
		}).error(function(){
    		$.messager.alert( "错误", "系统错误", "error");
  	  });
    }
</script>
</body>
</html>