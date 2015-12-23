jQuery(document).ready(function(){
});

//main.jsp
function addnewbug(){
	$("#TrainComplaint").attr("src","BugsController.do?m=newbug");
}
function showallbugs(){
	$("#TrainComplaint").attr("src","BugsController.do?m=allbugs");
}
function bugToMe(){
	$("#TrainComplaint").attr("src","BugsController.do?m=bugToMe");
}

function mySubmitBug(){
	$("#TrainComplaint").attr("src","BugsController.do?m=mySubmitBug");
}
function stillNotSolvedBug(){
	$("#TrainComplaint").attr("src","BugsController.do?m=stillNotSolvedBug");
}
function allSolvedBug(){
	$("#TrainComplaint").attr("src","BugsController.do?m=allSolvedBug");
}

function projectMangeList(){
	$("#TrainComplaint").attr("src","BugsController.do?m=projectMangeList");
}

//newbug.jsp
function admewbug(){
	var title=$("#title").val();
	if (title==''||title.trim=='') {
		layer.tips('标题不能为空！', '#title',{ guide: 0,time: 3});
		$("#title").css("border","1px solid red");
		$("#title").focus();
		return;
	}
	$("#title").css("border","1px solid #e7e7e7");
	var description=$("#description").val();
	if (description==''||description.trim=='') {
		layer.tips('BUG描述不能为空！', '#description',{ guide: 0,time: 3});
		$("#description").css("border","1px solid red");
		$("#description").focus();
		return;
	}
	$("#description").css("border","1px solid #e7e7e7");
	
	var projectId=$("#projectId").val();
	if (projectId=='0'||projectId.trim=='0') {
		layer.tips('BUG描述不能为空！', '#projectId',{ guide: 0,time: 3});
		$("#projectId").css("border","1px solid red");
		$("#projectId").focus();
		return;
	}
	$("#projectId").css("border","1px solid #e7e7e7");
	
	var occurtime=$("#occurtime").val();
	if (occurtime==''||occurtime.trim=='') {
		layer.tips('BUG描述不能为空！', '#occurtime',{ guide: 0,time: 3});
		$("#occurtime").css("border","1px solid red");
		$("#occurtime").focus();
		return;
	}
	$("#occurtime").css("border","1px solid #e7e7e7");
	
	
	var leave=$("#leave").val();
	if (leave=='0'||leave.trim=='0') {
		layer.tips('BUG描述不能为空！', '#leave',{ guide: 0,time: 3});
		$("#leave").css("border","1px solid red");
		$("#leave").focus();
		return;
	}
	$("#leave").css("border","1px solid #e7e7e7");
	
	var userid=$("#userid").val();
	if (userid=='0'||userid.trim=='0') {
		layer.tips('BUG描述不能为空！', '#userid',{ guide: 0,time: 3});
		$("#userid").css("border","1px solid red");
		$("#userid").focus();
		return;
	}
	$("#userid").css("border","1px solid #e7e7e7");
	
	var url='BugsController.do?m=add';
	var data={title:title,description:description,projectId:projectId,occurtime:occurtime,leave:leave,userid:userid};
	$.post(url,data,function(data){
		if (data.isSuccess=='true') {
			layer.msg(data.msg,3,function(){
				window.location.href="login.do?m=welcome";
			});
		}else{
			layer.msg(data.msg);
		}
	},"json")
	
}


