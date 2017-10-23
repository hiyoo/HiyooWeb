<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!-- 引入Easyui资源 -->
<script type="text/javascript" >
	//  1. 设置一个默认主题
	var themeName = "default";
	// 2. 判断是否有全局样式
	if(top.Config && top.Config.theme){
		themeName = top.Config.theme;
	}
	// 3. 通过js代码,动态添加样式引入标签
	document.write('<link id="themeStyle" rel="stylesheet" type="text/css" href="js/jquery-easyui/themes/'+ themeName +'/easyui.css">');
</script>   
<link rel="stylesheet" type="text/css" href="js/jquery-easyui/themes/icon.css">   
<script type="text/javascript" src="js/jquery-easyui/jquery.min.js"></script>   
<script type="text/javascript" src="js/jquery-easyui/jquery.easyui.min.js"></script>  
<script type="text/javascript" src="js/jquery-easyui/datagrid-detailview.js"></script>
	
	<!-- 国际化 -->
<script type="text/javascript" src="js/jquery-easyui/locale/easyui-lang-zh_CN.js"></script>  
<!-- 通用JS -->
<script type="text/javascript" src="js/common.js"></script>  
	<!-- 通用样式 -->
<link rel="stylesheet" type="text/css" href="style/common.css">   
<!--  -->
<script type="text/javascript" src="plugins/FusionChart/FusionCharts.js"></script>
