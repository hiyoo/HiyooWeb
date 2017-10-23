<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户管理</title>
	<!-- 引入资源 -->
	<%@ include file="/WEB-INF/views/common.jsp" %>
	<script type="text/javascript">
		function statusFormatter(value,row,index){
			switch (value) {
			case -1:
				return  "<font color='red'>离职</font>";
				break;

			default:
				return "<font color='green'>正常</font>";
				break;
			}
		}
		
		// grid菜单
		function create(){
			$("#userForm").form("clear");
			$("#userDlg").dialog("open");
		}
		
		function edit(){
			// 获取选中行
			var rowData  = $("#userGrid").datagrid("getSelected");
			if(!rowData){
				$.messager.alert("温馨提示","请选中一行数据!!","info");
				return;
			}
			// 初始化对话框
			$("#userForm").form("clear");
			$("#userDlg").dialog("open");
			
			// 加载数据
			$("#userForm").form("load",rowData);
			
		}
		
		function del(){
			// 获取选中行
			var rowData  = $("#userGrid").datagrid("getSelected");
			if(!rowData){
				$.messager.alert("温馨提示","请选中一行数据!!","info");
				return;
			}
			$.messager.confirm("温馨提示","是否确定离职["+rowData.nickname+"]??",function(yes){
				if(yes){
					// 发送ajxa请求
					$.get("employee_delete.action?id="+rowData.id,function(data){
						//console.debug(data);
						if(data.success){
							$.messager.alert("温馨提示","离职成功!!","info",function(){
								$("#userGrid").datagrid("reload");
							});
						}
					},"json");
				}
			});
		}
		
		function refresh(){
			$("#userGrid").datagrid("reload");
		}
		
		// 对话框按钮
		function save(){
			$("#userForm").form("submit",{
				url: "employee_save.action",
				success:function(data){
					data = $.parseJSON(data);
					if(data.success){
							$("#userDlg").dialog("close");
							$.messager.alert("温馨提示","<B>"+data.msg+"</B>","info",function(){
								$("#userGrid").datagrid("reload");
							});
					}else{
						alert(data.msg);
						$.messager.alert("温馨提示","<B>"+data.msg+"</B>","info");
					}
				}
			});
		}
		function cancel(){
			$('#userDlg').dialog('close');
		}
		function doSearch(){
			// 1. 拿到表单参数
			//console.debug($("#userSearchForm").serialize());
			//console.debug($("#userSearchForm").serializeArray());
			var paramObj =$("#userSearchForm").serializeJson();
			//console.debug(paramObj);
			// 2. 通过表格组件的方法,向后台传递过滤参数
			$("#userGrid").datagrid("load",paramObj);
		}
	</script>
</head>
<body>
	<!-- 1.表格 -->
	 <table id="userGrid"  class="easyui-datagrid"  fit="true"  border="false" 
			url="/userMgrAction?type=1"
			toolbar = "#employee-tb"
			pagination="true"  
			rownumbers="true" 
			pageSize="2"
			pageList="[2,5,10,20,50]"
			fitColumns="true" 
			singleSelect="true">
			<thead>
				<tr>
					<th field="vc_uid" width="50">用户编码</th>
					<th field="vc_name" width="50">用户名称</th>
					<th field="vc_tel" width="50">固定电话</th>
					<th field="vc_mobile" width="50">手机电话</th>
					<th field="vc_email" width="50">电子邮件</th>
					<th field="vc_addr" width="50">联系地址</th>
					<th field="d_registerTime" width="50">注册时间</th>
					<th field="d_lastLoginTime" width="50">上次登陆时间</th>
					<th field="vc_lastLoginIp" width="50">上次登陆IP</th>
					<th field="l_status" width="50" formatter="statusFormatter">状态</th>
				</tr>
			</thead>
	</table>
	<!-- 2.表格菜单按钮 -->
	 <div id="employee-tb">
	    <div>
	       <!-- 如何判断 -->
	       <!-- ${employee.name} or  ${employee.getName()} -->
	       
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="create()">添加</a> 
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="edit()">编辑</a>
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="del()">删除</a>
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-no" plain="true" onclick="forbidden()">启用/禁用</a>
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-reload" plain="true" onclick="refresh()">刷新</a>
	    </div>
	    <div>
	       <form id="userSearchForm">
		    	关键字: <input name="query.searchKey" style="width:100px">
		    	上次登陆时间: <input name="query.lastLoginTime1" class="easyui-datebox" style="width:110px">
				到: <input  name="query.lastLoginTime2" class="easyui-datebox" style="width:110px">
				状态:
				<select name="query.status" class="easyui-combobox" panelHeight="auto" style="width:100px">
					<option value="" selected="selected">---请选择---</option>
					<option value="0">正常</option>
					<option value="1">锁定</option>
				</select>
				<a href="#" class="easyui-linkbutton" iconCls="icon-search" onclick="doSearch();">搜索</a>
	       </form>
	    </div>
	</div>
	<!-- 3.对话框 -->
	<div id="userDlg" class="easyui-dialog" title="添加/编辑用户信息" style="width: 300px; height: 340px;"
			closed="true"
			modal="true"
			buttons="#userDlgBtn"
	>
		<form id="userForm" method="post">
			<input type="hidden" name="id">
			<table align="center" style="margin-top: 10px;">
				<tr>
					<td>用户编码:</td><td><input name="userid" class="easyui-validatebox" required="true"/></td>
				</tr>
				<tr>
					<td>用户名称:</td><td><input name="username" class="easyui-validatebox" required="true"/></td>
				</tr>				
				<tr>
					<td>用户密码:</td><td><input type="password" name="password" class="easyui-passwordbox" required="true"/></td>
				</tr>				
				<tr>
					<td>固定电话:</td><td><input name="tel" /></td>
				</tr>	
				<tr>
					<td>手机号码:</td><td><input name="mobile" /></td>
				</tr>
				<tr>
					<td>电子邮件:</td><td><input name="email" class="easyui-validatebox" validType="email"/></td>
				</tr>
				<tr>
					<td>联系地址:</td><td><input name="addr" /></td>
				</tr>
				<tr>
					<td>所属角色:</td>
					<td>
								 <input id="roleCombo" class="easyui-combobox" style="width:170px;"
									name="roleArr"
									data-options="
									url:'role_listAll.action',
									method:'get',
									valueField:'id',
									textField:'name',
									multiple:true,
									panelHeight:'auto'
									">
					</td>
				</tr>
				<tr>
					<td>用户状态:</td>
					<td>
						<select id="status" style="width:170px;" name="statusAttr" required="true">
							<option value="0">启用</option>
							<option value="1">锁定</option>
							<option value="2">作废</option>
						</select>
					</td>
				</tr>
			</table>
		</form>
	</div>
	<!-- 4.对话框按钮 -->
	<div id="userDlgBtn">
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" onclick="save()">保存</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="cancel()">取消</a>
	</div>
</body>
</html>