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
	<title>用户登录</title>
 
 	<!-- 引入资源 -->
 	<%@ include file="/WEB-INF/views/common.jsp" %>
</head>
 
<body>
	<%
		request.getSession().invalidate();// 销毁session
	%>

	<script type="text/javascript">
 		function postForm(){
 			$('#loginForm').submit();
 		}
 		function resetForm(){
 			$("#loginForm").form("clear");
 		}
 	</script>

	<div align="center" style="margin-top: 100px;">
		<div class="easyui-panel" title="用户登陆" iconCls="icon-logo" style="width: 400px;height: 200px;text-align:center;" >
			<form id="loginForm" action="/HiyooWeb/loginAction"  method="post" class="easyui-form" >
				<table align="center" style="margin-top: 15px;">
					<tr height="20">
						<td>用户名:</td>
					</tr>
					<tr height="10">
						<td><input id="username" name="username" class="easyui-validatebox1" required="true" /></td>
					</tr>
					<tr height="20">
						<td>密&emsp;码:</td>
					</tr>
					<tr height="10">
						<td><input id="password" name="password" type="password"  class="easyui-validatebox1" required="true" /></td>
					</tr>
					<tr height="40">
						<td align="center">
							<a href="javascript:;" class="easyui-linkbutton" onclick="postForm();">登录</a>
							<a href="javascript:;" class="easyui-linkbutton" onclick="resetForm();">重置</a>
						</td>
					</tr>
				</table>
			</form>
		</div>
	</div>
</body>
</html>