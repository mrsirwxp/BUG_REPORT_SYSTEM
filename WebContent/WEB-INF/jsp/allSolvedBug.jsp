<%@page import="com.util.BugData"%>
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
<title>BUG管理系统</title>
	<link rel='stylesheet' href='file/pubuliu/style.css' media='screen' />
	<link rel='stylesheet' href='file/css/bugShowStyle.css' media='screen' />
	<script src="file/js/jquery-1.8.3.min.js"></script>
	<script type="text/javascript" src="file/pubuliu/js/jquery.lazyload.min.js"></script>
	<script type="text/javascript" src="file/pubuliu/js/blocksit.min.js"></script>
	<script type="text/javascript" src="file/js/layer2.01/layer/layer.js"></script>
</head>
<body>

<div id="wrapper">

	<div id="container">
		
	</div>
	
</div>


<script type="text/javascript">
$(function(){
	var count=0;
	var imgIdRec=new Array();
	
	$("img.lazy").lazyload({		
		load:function(){
			$('#container').BlocksIt({
				numOfCol:1,
				offsetX: 8,
				offsetY: 8
			});
		}
	});	
	  $(window).scroll(function(){
			// 当滚动到最底部以上50像素时， 加载新内容
		//if ($(document).height() - $(this).scrollTop() - $(this).height()<10){
		if ($(document).scrollTop() >= $(document).height() - $(window).height()) {
			var html='';
				
			$.post('BugsController.do?m=pubuAllSolvedBug',{}, null, 'json')
			.success(function(data) {
				
				if (data==null) {
					layer.msg('已经到底啦！');
				}else{
						var images;
						var comment;
						var user;
						var imgcount=0;
					for(var i=0;i<data.length;i++){

						count++;
						images=data[i].image;
						user=data[i].user;
						comment=data[i].comment;
						
						html+='<div class="grid"  >';
						
						if (images!=null) {
							html+='<div id ="imagees'+count+'" class="photos-demo">';
							$.each(images,function(i,imag){
								html+='<img layer-pid="" layer-src="'+imag.imgsrc+'" src="'+imag.imgsrc+'" width="175px;" />';
							});
							html+='</div>';
							imgIdRec[imgcount]='#imagees'+count;
							imgcount++;
						}
						
						html+='<strong>'+data[i].title+'</strong>';
						html+='<p>'+data[i].description+'</p>';
						html+='<div class="shuxing"><span style="float: left;">'+count+'楼&nbsp;&nbsp;&nbsp;&nbsp;'+data[i].createtime+'&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<img alt="创建人" src="'+user.imgsrc+'" style="width: 18px;">'+user.uname+'</span><img alt="状态" src="img/status.png" style="width: 18px;">';
						if(data[i].status==1){
							html+='<%=BugData.SubbmitAndIsRepairing%>';
						}else if(data[i].status==2){
							html+='<%=BugData.RepairedAndIsTesting%>';
						}else if(data[i].status==3){
							html+='<span style="color:#31D608;">'+'<%=BugData.Closed%>'+'</span>';
						}else if(data[i].status==4){
							html+='<%=BugData.NotPassTestAndReturnRepairing%>';
						}else if(data[i].status==5){
							html+='<%=BugData.ReopenAndRepairing%>';
						}
						
						html+='&nbsp;&nbsp;&nbsp;<img alt="评论" src="img/comment.png" style="width: 18px;">'+data[i].commentNum+'&nbsp;&nbsp;&nbsp;</div>';
						html+='<div class="meta"><a href="javascript:void(0);" onclick="showBugDetail('+data[i].bugid+')" >点击查看>>></a></div>';
						html+='</div>';
					}
					console.log("jinru  container"); 
					$('#container').append(html);
					for(var i=0;i<imgIdRec.length;i++){
						layer.ready(function(){
						    //使用相册
						    layer.photos({
						        photos: imgIdRec[i]
						    });
						});
					}
					$('#container').BlocksIt({
						numOfCol:1,
						offsetX: 8,
						offsetY: 8
					});
					$("img.lazy").lazyload();
				}
        	  })
        	 .error(function(){
        		$.messager.alert( "错误", "系统错误", "error");
        	  });
		}
	});  
	
	
	//window resize
	var currentWidth = 1100;
	$(window).resize(function() {
		var winWidth = $(window).width();
		var conWidth;
		if(winWidth < 660) {
			conWidth = 440;
			col = 2
		} else if(winWidth < 880) {
			conWidth = 660;
			col = 3
		} else if(winWidth < 1100) {
			conWidth = 880;
			col = 4;
		} else {
			conWidth = 1100;
			col = 5;
		}
		
		if(conWidth != currentWidth) {
			currentWidth = conWidth;
			$('#container').width(conWidth);
			$('#container').BlocksIt({
				numOfCol: 1,
				offsetX: 8,
				offsetY: 8
			});
		}
	});
	  
	
	layer.config({
	    extend: 'extend/layer.ext.js'
	});
	var bugs=$.parseJSON('${bugs}');
	var html='';
	var images;
	var comment;
	var user;
	if(bugs!=null){
	$.each(bugs,function(i,item){
		count++;
		
		console.log("count="+count);
		images=item.image;
		user=item.user;
		comment=item.comment;
		
		html+='<div class="grid" >';
		
		if (images!=null) {
			html+='<div id ="imagees'+count+'" class="photos-demo">';
			$.each(images,function(i,imag){
				html+='<img layer-pid="" layer-src="'+imag.imgsrc+'" src="'+imag.imgsrc+'" width="175px;" />';
			});
			html+='</div>';
			var iddddd ='#imagees'+count;
			layer.ready(function(){
			    //使用相册
			    layer.photos({
			        photos: iddddd
			    });
			});
		}
		
		html+='<strong>'+item.title+'</strong>';
		html+='<p>'+item.description+'</p>';
		html+='<div class="shuxing"><span style="float: left;">'+count+'楼&nbsp;&nbsp;&nbsp;&nbsp;'+item.createtime+'&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<img alt="创建人" src="'+user.imgsrc+'" style="width: 18px;">'+user.uname+'</span><img alt="状态" src="img/status.png" style="width: 18px;">';
		if(item.status==1){
			html+='<%=BugData.SubbmitAndIsRepairing%>';
		}else if(item.status==2){
			html+='<%=BugData.RepairedAndIsTesting%>';
		}else if(item.status==3){
			html+='<span style="color:#31D608;">'+'<%=BugData.Closed%>'+'</span>';
		}else if(item.status==4){
			html+='<%=BugData.NotPassTestAndReturnRepairing%>';
		}else if(item.status==5){
			html+='<%=BugData.ReopenAndRepairing%>';
		}
		
		html+='&nbsp;&nbsp;&nbsp;<img alt="评论" src="img/comment.png" style="width: 18px;">'+item.commentNum+'&nbsp;&nbsp;&nbsp;</div>';
		html+='<div class="meta"><a href="javascript:void(0);" onclick="showBugDetail('+item.bugid+')" >点击查看>>></a></div>';
		html+='</div>';
	});
	}else{
		console.log("bug为空");
		html='';
	}
	if(html==''){ 
		html='<div class="grid" style="text-align:center;font-family: 华文楷体;margin-top: 180px;"><strong>暂无BUG!</strong></div>';
		$('#container').html(html);
		console.log("暂无BUG暂无BUG");
	}else{
	$('#container').html(html);
	}
});

function showBugDetail(bugid){
	var index = layer.open({
	    type: 2,
	    content: 'BugsController.do?m=bugsDetail&bugid='+bugid,
	    maxmin: true
	});
	layer.full(index);
}
</script>
</body>
</html>