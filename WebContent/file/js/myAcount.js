jQuery(document).ready(function(){

	$('#uploadHead').filer({
		limit:1,
		extensions:['jpg', 'jpeg', 'png', 'gif'],
		showThumbs: true,
		appendTo:'.headd',
		templates: {
            box: '<ul class="jFiler-items-list jFiler-items-grid"></ul>',
            item: '<div class="jFiler-item-inner">\
                        <div class="jFiler-item-thumb">\
                            <div class="jFiler-item-status"></div><br/><br/>\
                            {{fi-image}}\
                        </div>\
                        <div class="jFiler-item-assets jFiler-row">\
                            <ul class="list-inline pull-left">\
                                <li>{{fi-progressBar}}</li>\
                            </ul>\
                            <ul class="list-inline pull-right">\
                                <li><a class="icon-jfi-trash jFiler-item-trash-action"></a></li>\
                            </ul>\
                        </div>\
                    </div>',
            itemAppend: '<li class="jFiler-item" style="width: 49%">\
                            <div class="jFiler-item-container">\
                                <div class="jFiler-item-inner">\
                                    <div class="jFiler-item-thumb">\
                                        <div class="jFiler-item-status"></div>\
                                        <div class="jFiler-item-info">\
                                            <span class="jFiler-item-title"><b title="{{fi-name}}">{{fi-name | limitTo: 25}}</b></span>\
                                            <span class="jFiler-item-others">{{fi-size2}}</span>\
                                        </div>\
                                        {{fi-image}}\
                                    </div>\
                                    <div class="jFiler-item-assets jFiler-row">\
                                        <ul class="list-inline pull-left">\
                                            <li><span class="jFiler-item-others">{{fi-icon}}</span></li>\
                                        </ul>\
                                        <ul class="list-inline pull-right">\
                                            <li><a class="icon-jfi-trash jFiler-item-trash-action"></a></li>\
                                        </ul>\
                                    </div>\
                                </div>\
                            </div>\
                        </li>',
            progressBar: '<div class="bar"></div>',
            itemAppendToEnd: false,
            removeConfirmation: true,
            _selectors: {
                list: '.jFiler-items-list',
                item: '.jFiler-item-inner',
                progressBar: '.bar',
                remove: '.jFiler-item-trash-action'
            }
        },
        uploadFile: {
            url: "login.do?m=uploadHeadImg",
            data: {},
            type: 'POST',
            enctype: 'multipart/form-data',
            beforeSend: function(){},
            success: function(data, el){
            	console.log(data.split(':')[0]);
            	var rs=data.split(':')[0];
            	if(rs=='true'){
            		$("#uhead").attr("src",data.split(':')[1]);
            		$(".jFiler-item-inner").css("display","none");
            	}
                var parent = el.find(".jFiler-jProgressBar").parent();
                el.find(".jFiler-jProgressBar").fadeOut("slow", function(){
                    $("<div class=\"jFiler-item-others text-success\"><i class=\"icon-jfi-check-circle\"></i> Success</div>").hide().appendTo(parent).fadeIn("slow");
                });

            },
            error: function(el){
                var parent = el.find(".jFiler-jProgressBar").parent();
                el.find(".jFiler-jProgressBar").fadeOut("slow", function(){
                    $("<div class=\"jFiler-item-others text-error\"><i class=\"icon-jfi-minus-circle\"></i> Error</div>").hide().appendTo(parent).fadeIn("slow");    
                });
            },
            statusCode: null,
            onProgress: null,
            onComplete: null
        }
	}
	);
	
});


function updataUInfo(){
	var uname=$("#username").val();
	var email=$("#email").val();
	var pswd=$("#passwordOld").val();
	var newpswd=$("#passwordNew").val();
	var data={
			uname:uname,
			email:email,
			pswd:pswd,
			newpswd:newpswd
	}
	$.ajax({
		url:"login.do?m=updateUInfo",
		type:"post",
		data:data,
		dataType:"text",
		success:function(data){
			layer.msg(data);
		}
	});
}

