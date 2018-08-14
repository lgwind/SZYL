$(function(){
	seajs.config({
		base:"./",
	    alias:{
	    	/*----------框架----------*/
	    	"bootstrapcss":"css/bootstrap.min.css",
	    	"bootstrap-tablecss":"css/bootstrap-table.min.css",
	    	"bootstrap":"js/bootstrap.js",
	    	"bootstrap-table":"js/bootstrap-table.min.js",
	    	"bootstrap-table-zh-CN":"js/bootstrap-table-zh-CN.js",
			//页面模块
	        "indexcss":"css/index.css",
	        "module":"js/indexModule.js",
	        //资源
	        "table":"module/table",/*注意目录的斜杠线方向*/
	        "tablecss":"module/table.css",
	    },
	    map:
            [
                //防止js、css文件夹下的文件被缓存
                [/^(.*\.(?:css|js))(.*)$/i,'$1?_=' + new Date().getTime()]
            ],
        debug: true/*值为true时候，加载器不会删除动态插入的script标签，插件可以通过debug来决定log的输出信息*/
	});
	seajs.use('indexcss',function(){ 
	    seajs.use('module'); 
	});
});

/**
 * 导出
 */
function exportExcel(){
	window.open("/SZYL/xls/"+getCookie("username")+".xls");
}
/**
 * 全部删除
 */
function deleteAll(){
	$.ajax({
        url:'/SZYL/resume/delete?username='+getCookie("username"),
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
                   $("#data_info").bootstrapTable('load', json);//主要是要这种写法
               }
           });
        },
        error:function(){
        	alert("删除失败");
        }
    });
}
