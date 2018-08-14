define(function(require, exports, module){
	var oPic = require("table");
	
	oPic.init("#table");
	
	//删除
	$("#delete").on("click", function (){
		var slt = $("#tab").bootstrapTable("getSelections");
		console.log(slt);
		var slt_ids = $.map(slt, function (row){ return row.resume_id; }); // 提取选中行的id
		console.log(slt_ids);
		$.ajax({
			type: "post",
			url: "/dbmonitor/bigdataconfig/del_interface/",
			data: {ids: JSON.stringify(slt_ids)},
			dataType: "text",
			success: function (d){
			show_alert("操作结果", JSON.parse(d).result);
			// $("#data_info").bootstrapTable("remove", {field: "id", values: slt_ids, offset: 0});
			$.getJSON("/dbmonitor/bigdataconfig/bigdata_info/", function(data){
				oPic.initDataTable("destroy", data);
			});
			}
		});
	});
	
});
