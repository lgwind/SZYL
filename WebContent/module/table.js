//var initDataTable;
define(function(require, exports, module){
	require('bootstrapcss');
	require('bootstrap-tablecss');
	require('tablecss');
	require('bootstrap');
	require('bootstrap-table');
	require('bootstrap-table-zh-CN');
	
	var sCurUrl = module.uri;
	var sHtmlUrl = sCurUrl.replace('.js','.html');
	
	exports.init = function(selector){
		var htmlObj = $.ajax({
			url:sHtmlUrl,
			async:false
		});
		$(selector).html(htmlObj.responseText);
		
		//新建表格
		$.getJSON("/SZYL/resume/getAllByUsername?username="+getCookie("username"), function(data){// 获取表格数据的url/*http://192.168.43.71:8080/SZYL/resume/getAll*/
			tableCreate("destroy", data);
		});
	};
});

/**
 * 查看某个简历
 */
var show = function(resume_id){
	console.log("查看简历："+resume_id);
	window.open("/SZYL/file/check?resume_id="+resume_id);
}

/**
 * 删除某个简历
 */
var del = function(resume_id){
	console.log("删除简历："+resume_id);	
	$.ajax({
        url:'/SZYL/resume/delete?resume_id='+resume_id,
        type:'get',
        success:function(){
        	alert("删除成功");
//        	location.reload();
//        	var shuaxin = require("table");
        	$.ajax({
                type: "get",
                url: "/SZYL/resume/getAllByUsername?username="+getCookie("username"),
                dataType:"json",
                success : function(json) {
                	console.log("getAll");
                	console.log(json);
                   $("#data_info").bootstrapTable('load', json);//主要是要这种写法
               }
           });
        },
        error:function(){
        	alert("删除失败");
        }
    });
}

/**
 * 创建简历表格
 * @param op
 * @param data
 */
function tableCreate(op,data) {
	$("#data_info").bootstrapTable({ // 对应table标签的id
		method: "get",        //  请求方式有get和post两种，一般get就行了
		  toolbar: "#toolbar",        //  指明自定义的toolbar，它是一个jQuery选择器，使用场景：比如，自定义新增、修改、删除等操作
		  striped: true,                //  隔行变色,表格显示条纹，默认为false
          dataType: "json",
          contentType: "application/x-www-form-urlencoded",
       	  toolbar: "#toolbar",
       	  cache: false,
       	  pagination: true, // 在表格底部显示分页组件，默认false
	      pageList: [5, 10, 20, 50, 100], // 设置页面可以显示的数据条数[10, 20, 50, 100]
	      sidePagination: 'client', //设置为客户端分页
	      pageSize: 5, // 页面数据条数
	      pageNumber: 1, // 首页页码
	      paginationPreText:'上一页',
          paginationNextText:'下一页',
          oPaginate: {
		        sFirst:    "首页",
		        sPrevious: "上页",
		        sNext:     "下页",
		        sLast:     "末页"
		   },
          showFooter:true,//显示列脚
	      search: true, //是否显示搜索框
	      sortOrder: "desc",    // 设置排序的方式
		  searchOnEnterKey: true,        // 当搜索框启用后，再开启这个设置，表示按回车后触发搜索；否则自动触发搜索
		  showColumns: true, //是否显示可选择显示的列
		  showRefresh: true,                 // 是否显示刷新按钮
		  showToggle: true,        // 显示切换试图按钮
		  uniqueId: "resume_id",        // 指示每一行的唯一标识符，一般使用id来做为标识，当使用table的事件时，就可以捕获这个值
          showPaginationSwitch: true,  //是否显示选择分页数按钮
	     
	      
	      clickToSelect: true,
//	      queryParams: function (params) { // 服务器端分页，请求服务器数据时发送的参数，可以在这里添加额外的查询参数，返回false则终止请求
//	
//	          return {
//	              pageSize: params.limit, // 每页要显示的数据条数
//	              offset: params.offset, // 每页显示数据的开始行号
//	              sort: params.sort, // 要排序的字段
//	              sortOrder: params.order, // 排序规则
//	             // dataId: $("#dataId").val()  额外添加的参数
//	          }
//	      },
//	      sortName: 'id', // 要排序的字段
//	      sortOrder: 'desc', // 排序规则
	      columns:  [
//			{field:"checked",checkbox:true},
			{
	              title: '序号', // 表格表头显示文字
	              formatter: function (value, row, index) {
	            	  return index+1;
	              },
	              align: 'center', // 左右居中
	              valign: 'middle',// 上下居中
	              width:60 
	        },
	      	{field: 'resume_id', // 返回json数据中的name
		              title: '简历ID', // 表格表头显示文字
		              align: 'center', // 左右居中
		              valign: 'middle',// 上下居中
		              visible: false,
		              width:60 
	        }, { 
	              field: 'username',
	              title: '用户名',
	              align: 'center',
	              valign: 'middle',
	              visible: false,
	              width:100 
	          }, {
	              field: 'name',
	              title: '姓名',
	              align: 'center',
	              valign: 'middle',
	              width:100         
	          }, {field: 'phone',
	              title: "手机",
	              align: 'center',
	              valign: 'middle',
	              width:100 
	          }, {field: 'email',
	              title: "邮箱",
	              align: 'center',
	              valign: 'middle',
	              width:100 
	          }, {field: 'school',
	              title: "学校",
	              align: 'center',
	              valign: 'middle',
	              width:100 
	          }, {field: 'major',
	              title: "专业",
	              align: 'center',
	              valign: 'middle',
	              width:80 
	          }, {field: 'position',
	              title: "申请职位",
	              align: 'center',
	              valign: 'middle',
	              width:100 
	          }, {field: 'age',
	              title: "年龄",
	              align: 'center',
	              valign: 'middle',
	              width:50 
	          }, {field: 'state',
	              title: "在职状态",
	              align: 'center',
	              valign: 'middle',
	              width:100 
	          }, {field: 'work_age',
	              title: "工作年限",
	              align: 'center',
	              valign: 'middle',
	              width:50 
	          }, {field: 'skill',
	              title: "技能关键词",
	              align: 'center',
	              valign: 'middle',
	              width:200 
	          }, {field: 'eml',
	              title: "简历邮件名称（.eml）",
	              align: 'center',
	              valign: 'middle',
	              visible: false,
	              width:100 
	          }, {field: 'update_time',
	              title: "最后更新时间",
	              align: 'center',
	              valign: 'middle',
	              visible: false,
	              width:100 
	          }, {field: '',
	              title: "操作",
	              formatter: operateFormatter,
	              align: 'center',
	              valign: 'middle',
	              width:100 
	          }
	      ],
	      data: data,   // 用于接收后台返回的json数据  formatData(data)

	      onLoadSuccess: function(){  //加载成功时执行
	            console.info("加载成功");
	      },
	      onLoadError: function(){  //加载失败时执行
	            console.info("加载数据失败");
	      }
	})
}
/**
 * 表格操作栏按钮设计
 * @param value
 * @param row
 * @param index
 * @returns
 */
function operateFormatter(value, row, index) {
//	console.log("row=");
//	console.log(row);
    return [
            '<button  type="button" class="btn btn-primary btn-xs button_del" onclick="show(\''+row.resume_id+'\')">查看</button>'
            + '&nbsp;<button  type="button" class="btn btn-primary btn-xs button_del" onclick="del(\''+row.resume_id+'\')">删除</button>'
            ].join('');
}


