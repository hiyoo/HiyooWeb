// 把表单参数,封装成json对象
$.fn.serializeJson = function(){
	var paramArray = $(this).serializeArray();
	var paramObj = {};
	$.each(paramArray,function(i,param){
		//Object { name="searchKey", value="136"}	
		paramObj[param.name] = param.value;
	});
	return paramObj;
};

// 通用的数据格式化方法
function objectFormatter(value,row,index){		
	return  value?value.name||value.nickname||value.title||value:"";
}
function genderFormatter(value,row,index){		
	switch (value) {
		case 0:
			return "女";
			break;
		case 1:
			return "男";
			break;
	
		default:
			return "未知";
			break;
		}
}