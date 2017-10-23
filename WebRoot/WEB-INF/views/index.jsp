<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%
  String path = request.getContextPath();
  String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<base href="<%=basePath %>" >
	<title>HiYOO供应链管理系统</title>
	<!-- 引入资源 -->
	<%@ include file="/WEB-INF/views/common.jsp"%>
	
	<script type="text/javascript">
		function refreshTime(){
			var d = new Date();
			var dataStr = d.getFullYear()+"年"+(d.getMonth()+1)+"月"+d.getDate()+"日 "+d.getHours()+":"+d.getMinutes()+":"+d.getUTCSeconds();
			$("#time").html(dataStr);
		}
		
	
		$(function(){
			refreshTime();
			// 页面加载完毕,开启时钟
			setInterval(refreshTime, 1000);
			
			$.messager.show({
				 title:'温馨提示',
				 msg:'<strong>${sessionScope.user_in_session.nickname}</strong>,欢迎登录HiYOO供应链管理系统 !!',
				 showType:'show',
				 timeout:1500,
			 });
			
			$("#systemMenuTree").tree({
				//url:"demo/tree1.json",
				url:"/HiyooWeb/systemMenuAction",
				method:"post",
				animate:true,
				formatter:function(node){
					if(node.attributes&&node.attributes.url){
						node.iconCls="icon-ok";
					}
					return node.text;
				},
				onClick : function(node){
					// 分析node中,是否有url
					//alert(node.text);
					if(node.attributes && node.attributes.url){
						// 获取url
						var url = node.attributes.url;
						// 获取图标样式
						var icon = node.iconCls;
						// 获取节点名称
						var text = node.text;
						// 判断是否已经打开
						if($("#mainTabs").tabs("exists",text)){
							// 如果已打开,选中
							$("#mainTabs").tabs("select",text);					
						}else{
							// 如果没打开,则打开
							$("#mainTabs").tabs("add",{
								title : text,
								//href : url,
								content : "<iframe src='"+url+"' style='width: 100%;height: 100%' frameborder='0'></iframe>",
								iconCls : icon,
								closable : true
							});
						}
					}
				}
			});
			
			$("#mainTabs").tabs({
				fit:true,
				border:false,
				tabWidth:110
			});
			
			// 切换皮肤
			$("[data-theme]").bind("click",function(){
				//console.debug($(this).data("theme"));
				// 1. 获取新样式名称
				var themeName = $(this).data("theme");
				
				// 2. 修改主页面,样式资源的引入
				$("#themeStyle").attr("href","js/jquery-easyui/themes/"+themeName+"/easyui.css");
				
				// 3. 修改所有iframe下,页面对应的主题样式
				$("iframe").each(function(i,iframeDom){
					//console.debug(iframeDom.contentDocument);
					$(iframeDom.contentDocument).find("#themeStyle").attr("href","js/jquery-easyui/themes/"+themeName+"/easyui.css");
				});
				
				// 4. 把当前样式,更新到全局对象中
				window.Config = {
					theme : themeName							
				};
				
				// 5. 修改banner图片
				if(themeName=="default" || themeName=="gray"){
					$("#banner").css("background","url('../../images/themes/"+themeName+"/banner-pic.gif') no-repeat bottom");
					$("#banner").css("background-size","cover");
				}else{
					$("#banner").css("background","");
					$("#banner").css("background-size","");
				}
			});
		});
		
	</script>
	
	
</head>
<body>
<div fit="true" class="easyui-layout">
	<div id="banner" data-options="{region:'north',height:65,border:false}" style="background: url('images/themes/default/banner-pic.gif') no-repeat bottom ; background-size: cover;">
		<h1>HiYOO供应链管理系统</h1>
		<!-- 欢迎语 -->
		<div style="position: absolute;right: 5px;top: 5px;font-size: 15px;">
			
			<!-- 主题设置 -->
			<a class="easyui-menubutton" data-options="menu:'#themeMenu',iconCls:'icon-theme'">主题</a>
			<a class="easyui-linkbutton" data-options="iconCls:'icon-undo',plain:true"  onclick="javascript:location.href='/login.jsp'">注销</a>
		</div>
		<!-- 电子时钟-->
		<div style="position: absolute;right: 20px;bottom: -5px;" >
			<h4>
			<font color="blue" style="font-weight: bold;">${sessionScope.user_in_session.nickname}</font>,欢迎登陆HiYOO供应链管理系统!
			<span id="time"></span>
			</h4>
		</div>
		 <div id="themeMenu" style="width:50px;">
			<div data-theme="default" iconCls="icon-color-default">默认</div>
			<div data-theme="gray" iconCls="icon-color-gray">灰色</div>
			<div data-theme="black" iconCls="icon-color-black">黑色</div>
		</div>
	</div>
	<div data-options="{region:'south',height:20,border:false}" style="background: url('images/themes/default/banner-pic.gif') no-repeat bottom ; background-size: cover;">
		<div align="center"><strong>HiYOO版权所有</strong></div>
	</div>
	<div data-options="{region:'west',width:160,title:'系统菜单',headerCls:'border-right-none',bodyCls:'border-right-none'}">
		<ul id="systemMenuTree"></ul>
	</div>
	<div data-options="{region:'center'}">
		<div id="mainTabs">
			<div title="欢迎页">
				<p align="center" style="margin-top:100px;font-size: 40px;font-weight: bold;color: blue"><img src="images/face.gif" width="30px" height="30px">欢迎登录HiYOO供应链管理系统<img src="images/face.gif" width="30px" height="30px"></p>
			</div>
		</div>
	</div>
</div>
</body>
</html>