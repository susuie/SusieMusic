 $(document).ready(function(){
	 //用户管理
	$("#usermanage").dataTable().fnDestroy();//销毁当前表
  	$("#usermanage").dataTable({           //重绘表     		
  		ajax: {
             url: "../user/lists",
             type:"get",           
             dataSrc: function(result) {
                          //这里result和上面jquery的ajax的代码类似，也是可以得到data.json的数据，但是这样的格式，Datatables不能直接使用，这时候需要在这里处理一下
                          //直接返回Datatables需要的那部分数据即可
                         return result.data;
             		 }
         },		
          bPaginate:true,//是否分页，默认为true  
         "iDisplayLength":3,//设置每页展示的行数
      	"bProcessing": false, // 是否显示取数据时的那个等待提示           
          "bSortable":true,
          "bScrollCollapse" : true,
          "bFilter":false,                 
          "bLengthChange":false,
          "bInfo":true,
          "oLanguage": { //国际化配置  
              "sProcessing" : "正在获取数据，请稍后...",    
              "sLengthMenu" : "显示 _MENU_ 条",    
              "sZeroRecords" : "没有您要搜索的内容",    
              "sInfo" : "共 _TOTAL_ 条记录，当前显示_PAGE_页",    
              "sInfoEmpty" : "记录数为0",    
              "sInfoFiltered" : "(全部记录数 _MAX_ 条)",    
              "sInfoPostFix" : "",    
              "sSearch" : "搜索",    
              "sUrl" : "",                    
              "oPaginate": {    
                  "sFirst" : "第一页",    
                  "sPrevious" : "上一页",    
                  "sNext" : "下一页",    
                  "sLast" : "最后一页"    
              }             		 
          },		                         
          "columns": [
        	  { "data":"name" },
         		{ "data":"balance" }, 
         		{ "data": "role"},                    
         		{ "data": "time" },
 				{ "data": "type" }, 
 				{"data":"usex" },                           
             { "data": "password","visible":false },
             { "data": "umg","visible":false },
 			{ "data": "userid" }
          ],
         
          columnDefs : [         
          {  
              targets: [8],  
              render: function(data, type, row, meta) { 
              	return '<a href="javascript:;" data-toggle="modal" data-target="#addUser"'
                    +' onclick="addUser()" '
						+'style="text-decoration:none;margin-right:10px;">添加用户</a>'
						+'<a title="delete"'
		                +' href="javascript:;" data-toggle="modal" data-target="#deleteUser"'
		                +'onclick="deleteUser('+data+')" '
						+'style="text-decoration:none;margin-right:10px;">删除用户</a>';
              }  
          },        		
		] ,                              
   });
  	//财务管理
  	$("#finacemanage").dataTable().fnDestroy();//销毁当前表
  	$("#finacemanage").dataTable({           //重绘表     		
  		ajax: {
             url: "../finance/lists",
             type:"post",           
             dataSrc: function(result) {
                          //这里result和上面jquery的ajax的代码类似，也是可以得到data.json的数据，但是这样的格式，Datatables不能直接使用，这时候需要在这里处理一下
                          //直接返回Datatables需要的那部分数据即可
                         return result.data;
             		 }
         },		
          bPaginate:true,//是否分页，默认为true  
         "iDisplayLength":2,//设置每页展示的行数
      	"bProcessing": false, // 是否显示取数据时的那个等待提示           
          "bSortable":true,
          "bScrollCollapse" : true,
          "bFilter":false,                 
          "bLengthChange":false,
          "bInfo":true,
          "oLanguage": { //国际化配置  
              "sProcessing" : "正在获取数据，请稍后...",    
              "sLengthMenu" : "显示 _MENU_ 条",    
              "sZeroRecords" : "没有您要搜索的内容",    
              "sInfo" : "共 _TOTAL_ 条记录，当前显示_PAGE_页",    
              "sInfoEmpty" : "记录数为0",    
              "sInfoFiltered" : "(全部记录数 _MAX_ 条)",    
              "sInfoPostFix" : "",    
              "sSearch" : "搜索",    
              "sUrl" : "",                    
              "oPaginate": {    
                  "sFirst" : "第一页",    
                  "sPrevious" : "上一页",    
                  "sNext" : "下一页",    
                  "sLast" : "最后一页"    
              }             		 
          },		                         
          "columns": [
        	  { "data":"p_time" },
         		{ "data":"p_in" }, 
         		{ "data": "p_out"},                    
         		{ "data": "p_balance" }, 				
 			{ "data": "p_id" ,"visible":false}
          ],                        
   });
  	//歌曲管理
  	$("#songmanage").dataTable().fnDestroy();//销毁当前表
  	$("#songmanage").dataTable({           //重绘表     		
  		ajax: {
             url: "../song/lists",
             type:"post",           
             dataSrc: function(result) {
                          //这里result和上面jquery的ajax的代码类似，也是可以得到data.json的数据，但是这样的格式，Datatables不能直接使用，这时候需要在这里处理一下
                          //直接返回Datatables需要的那部分数据即可
                         return result.song;
             		 }
         },		
          bPaginate:true,//是否分页，默认为true  
         "iDisplayLength":3,//设置每页展示的行数
      	"bProcessing": false, // 是否显示取数据时的那个等待提示           
          "bSortable":true,
          "bScrollCollapse" : true,
          "bFilter":false,                 
          "bLengthChange":false,
          "bInfo":true,
          "oLanguage": { //国际化配置  
              "sProcessing" : "正在获取数据，请稍后...",    
              "sLengthMenu" : "显示 _MENU_ 条",    
              "sZeroRecords" : "没有您要搜索的内容",    
              "sInfo" : "共 _TOTAL_ 条记录，当前显示_PAGE_页",    
              "sInfoEmpty" : "记录数为0",    
              "sInfoFiltered" : "(全部记录数 _MAX_ 条)",    
              "sInfoPostFix" : "",    
              "sSearch" : "搜索",    
              "sUrl" : "",                    
              "oPaginate": {    
                  "sFirst" : "第一页",    
                  "sPrevious" : "上一页",    
                  "sNext" : "下一页",    
                  "sLast" : "最后一页"    
              }             		 
          },		                         
          "columns": [
        	  
         		{ "data":"title" }, 
         		{ "data": "artist"},                    
         		{ "data": "mp3" },
 				{ "data": "s_kind" }, 
 				{"data":"s_hears" }, 
 				{"data":"s_tup"},
 				{"data":"s_download"},
 				{"data":"s_comment"},
 				{"data":"lrc"},
 				{"data":"s_id"},
             { "data": "poster","visible":false },
             
          ],
         
          columnDefs : [         
          {  
              targets: [9],  
              render: function(data, type, row, meta) { 
              	return '<a href="javascript:;" data-toggle="modal" data-target="#addUser"'
                    +' onclick="addsong()" '
						+'style="text-decoration:none;margin-right:10px;">添加歌曲</a>'
						+'<a title="delete"'
		                +' href="javascript:;" data-toggle="modal" data-target="#deleteUser"'
		                +'onclick="deleteSong('+data+')" '
						+'style="text-decoration:none;margin-right:10px;">删除歌曲</a>';
              }  
          },        		
		] ,                              
   });
});
function addUser(){
	$("#addUser").modal("show");
	$('#add-confirm').unbind('click').bind('click',function(){			
	$.ajax({
        url:"../user/addUser",
        type:"post",
        data: {
            "name": $("#addname").val(),
            "password":$("#pwd").val(),
            "role": "用户",
            "type": "大众",
            "usex": $("#sex").val(), 
            "balance":"0",
            "time":5,
            "umg":"../image/logo2.png",	
        }, 
        success: function (data) {
        	alert("新增用户成功！");
        	$("#addUser").modal("hide");
        	document.location.reload();
        },
        error:function(data){
        	alert("添加失败！");
        }     
    });
	});
}
function deleteUser(id){
	if(confirm("确定删除该用户？")){
		$.ajax({
			url:"../user/delete",
			type:"post",
			async:false,
			data:{"u_id":id},
			success: function(result){
				alert("删除用户成功！");
				document.location.reload();
			},
			error:function(result){
				alert("该用户删除失败！");
			}
		});
	}
}