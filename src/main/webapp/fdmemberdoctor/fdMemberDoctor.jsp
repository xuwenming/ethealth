﻿<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.mobian.model.TfdMemberDoctor" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="jb" uri="http://www.jb.cn/jbtag"%> 
<!DOCTYPE html>
<html>
<head>
<title>FdMemberDoctor管理</title>
<jsp:include page="../inc.jsp"></jsp:include>
<c:if test="${fn:contains(sessionInfo.resourceList, '/fdMemberDoctorController/editPage')}">
	<script type="text/javascript">
		$.canEdit = true;
	</script>
</c:if>
<c:if test="${fn:contains(sessionInfo.resourceList, '/fdMemberDoctorController/delete')}">
	<script type="text/javascript">
		$.canDelete = true;
	</script>
</c:if>
<c:if test="${fn:contains(sessionInfo.resourceList, '/fdMemberDoctorController/view')}">
	<script type="text/javascript">
		$.canView = true;
	</script>
</c:if>
<c:if test="${fn:contains(sessionInfo.resourceList, '/fdBalanceLogController/manager')}">
	<script type="text/javascript">
		$.canViewBalance = true;
	</script>
</c:if>
<script type="text/javascript">
	var dataGrid;
	$(function() {
		dataGrid = $('#dataGrid').datagrid({
			url : '${pageContext.request.contextPath}/fdMemberDoctorController/dataGrid',
			fit : true,
			fitColumns : true,
			border : false,
			pagination : true,
			idField : 'id',
			pageSize : 10,
			pageList : [ 10, 20, 30, 40, 50 ],
			sortName : 'createTime',
			sortOrder : 'desc',
			checkOnSelect : false,
			selectOnCheck : false,
			nowrap : false,
			striped : true,
			rownumbers : true,
			singleSelect : true,
			columns : [ [ {
				field : 'id',
				title : '专家ID',
				width : 30,
				formatter : function (value, row, index) {
					if ($.canView) {
						return '<a onclick="viewFun(\'' + row.id + '\')">'+value+'</a>';
					}
					return value
				}
				}, {
				field : 'username',
				title : '用户名',
				width : 50
				}, {
				field : 'realName',
				title : '姓名',
				width : 50,
				formatter:function(value,row){
					return row.customer.realName;
				}
				}, {
				field : 'mobile',
				title : '手机号',
				width : 50		
				}, {
				field : 'groupName',
				title : '所属团队',
				width : 50		
				}, {
				field : 'hospitalName',
				title : '所在医院',
				width : 80
				}, {
				field : 'departmentName',
				title : '科室',
				width : 50		
				}, {
				field : 'levelName',
				title : '职称',
				width : 50		
				}, {
				field : 'isBest',
				title : '著名专家',
				width : 30,
				formatter : function(value, row, index) {
					var str;
					if(value) str = '<font color="#4cd964;">是</font>';
					else str =  '否';

					return str;
				}
				}, {
				field : 'seq',
				title : '排序',
				width : 30,
				sortable:true
				}, {
				field : 'balance',
				title : '余额',
				width : 40,
				align:'right',
				formatter:function(value,row){
					var balance;
					if(!row.customer.balance) balance = '0.00';
					else balance = row.customer.balance;
					if($.canViewBalance) {
						return '<a onclick="viewBalance(\'' + row.id + '\')">'+balance+'</a>';
					}
					return balance;
				}
				}, {
				field : 'status',
				title : '状态',
				width : 30,
				formatter : function(value, row, index) {
					var str = '待审核';
					if(value == 1) str = '<font color="#4cd964;">审核通过</font>';
					else if(value == 3) str =  '<font color="red">审核拒绝</font>';

					return str;
				}
			}, {
				field : 'action',
				title : '操作',
				width : 30,
				formatter : function(value, row, index) {
					var str = '';
					if ($.canEdit) {
						str += '<a onclick="editFun(\'' + row.id + '\')">编辑</a>';
					}
					str += '&nbsp;';
					if ($.canDelete) {
						<%--str += $.formatString('<img onclick="deleteFun(\'{0}\');" src="{1}" title="删除"/>', row.id, '${pageContext.request.contextPath}/style/images/extjs_icons/bug/bug_delete.png');--%>
					}
					str += '&nbsp;';
					if ($.canView) {
						<%--str += $.formatString('<img onclick="viewFun(\'{0}\');" src="{1}" title="查看"/>', row.id, '${pageContext.request.contextPath}/style/images/extjs_icons/bug/bug_link.png');--%>
					}
					return str;
				}
			} ] ],
			toolbar : '#toolbar',
			onLoadSuccess : function() {
				$('#searchForm table').show();
				parent.$.messager.progress('close');

				$(this).datagrid('tooltip');
			}
		});
	});

	function deleteFun(id) {
		if (id == undefined) {
			var rows = dataGrid.datagrid('getSelections');
			id = rows[0].id;
		}
		parent.$.messager.confirm('询问', '您是否要删除当前数据？', function(b) {
			if (b) {
				parent.$.messager.progress({
					title : '提示',
					text : '数据处理中，请稍后....'
				});
				$.post('${pageContext.request.contextPath}/fdMemberDoctorController/delete', {
					id : id
				}, function(result) {
					if (result.success) {
						parent.$.messager.alert('提示', result.msg, 'info');
						dataGrid.datagrid('reload');
					}
					parent.$.messager.progress('close');
				}, 'JSON');
			}
		});
	}

	function editFun(id) {
		if (id == undefined) {
			var rows = dataGrid.datagrid('getSelections');
			id = rows[0].id;
		}
		parent.$.modalDialog({
			title : '编辑数据',
			width : 600,
			height : 240,
			href : '${pageContext.request.contextPath}/fdMemberDoctorController/editPage?id=' + id,
			buttons : [ {
				text : '编辑',
				handler : function() {
					parent.$.modalDialog.openner_dataGrid = dataGrid;//因为添加成功之后，需要刷新这个dataGrid，所以先预定义好
					var f = parent.$.modalDialog.handler.find('#form');
					f.submit();
				}
			} ]
		});
	}

	function viewFun(id) {
		var href = '${pageContext.request.contextPath}/fdMemberDoctorController/view?id=' + id;
		parent.$("#index_tabs").tabs('add', {
			title : '专家详情-' + id,
			content : '<iframe src="' + href + '" frameborder="0" scrolling="auto" style="width:100%;height:98%;"></iframe>',
			closable : true
		});
	}

	function viewBalance(id) {
		var href = '${pageContext.request.contextPath}/fdBalanceLogController/manager?userId=' + id;
		parent.$("#index_tabs").tabs('add', {
			title : '余额-' + id,
			content : '<iframe src="' + href + '" frameborder="0" scrolling="auto" style="width:100%;height:98%;"></iframe>',
			closable : true
		});
	}

	function addFun() {
		parent.$.modalDialog({
			title : '添加数据',
			width : 780,
			height : 500,
			href : '${pageContext.request.contextPath}/fdMemberDoctorController/addPage',
			buttons : [ {
				text : '添加',
				handler : function() {
					parent.$.modalDialog.openner_dataGrid = dataGrid;//因为添加成功之后，需要刷新这个dataGrid，所以先预定义好
					var f = parent.$.modalDialog.handler.find('#form');
					f.submit();
				}
			} ]
		});
	}
	function downloadTable(){
		var options = dataGrid.datagrid("options");
		var $colums = [];		
		$.merge($colums, options.columns); 
		$.merge($colums, options.frozenColumns);
		var columsStr = JSON.stringify($colums);
	    $('#downloadTable').form('submit', {
	        url:'${pageContext.request.contextPath}/fdMemberDoctorController/download',
	        onSubmit: function(param){
	        	$.extend(param, $.serializeObject($('#searchForm')));
	        	param.downloadFields = columsStr;
	        	param.page = options.pageNumber;
	        	param.rows = options.pageSize;
	        	
       	 }
        }); 
	}
	function searchFun() {
		dataGrid.datagrid('load', $.serializeObject($('#searchForm')));
	}
	function cleanFun() {
		$('#searchForm input').val('');
		dataGrid.datagrid('load', {});
	}
</script>
</head>
<body>
	<div class="easyui-layout" data-options="fit : true,border : false">
		<div data-options="region:'north',title:'查询条件',border:false" style="height: 120px; overflow: hidden;">
			<form id="searchForm">
				<table class="table table-hover table-condensed" style="display: none;">
						<tr>
							<th>姓名</th>
							<td>
								<input type="text" name="username" maxlength="10" class="span2"/>
							</td>
							<th>手机号</th>
							<td>
								<input type="text" name="mobile" maxlength="10" class="span2"/>
							</td>
							<th>所属团队</th>
							<td>
								<jb:selectSql dataType="SQL001" name="groupId"></jb:selectSql>
							</td>
							<th>所在医院</th>
							<td>
								<jb:selectSql dataType="SQL002" name="hospital"></jb:selectSql>
							</td>
						</tr>	
						<tr>
							<th>科室</th>
							<td>
								<jb:selectSql dataType="SQL003" name="department"></jb:selectSql>
							</td>
							<th>职称</th>
							<td>
								<jb:selectSql dataType="SQL004" name="level"></jb:selectSql>
							</td>
							<th>著名专家</th>
							<td>
								<select name="isBest" class="easyui-combobox"
										data-options="width:140,height:29,editable:false,panelHeight:'auto'">
									<option value="">全部</option>
									<option value="1">是</option>
									<option value="0">否</option>
								</select>
							</td>
							<th>状态</th>
							<td>
								<select name="status" class="easyui-combobox"
										data-options="width:140,height:29,editable:false,panelHeight:'auto'">
									<option value="">全部</option>
									<option value="2">待审核</option>
									<option value="1">审核通过</option>
									<option value="3">审核拒绝</option>
								</select>
							</td>
						</tr>
				</table>
			</form>
		</div>
		<div data-options="region:'center',border:false">
			<table id="dataGrid"></table>
		</div>
	</div>
	<div id="toolbar" style="display: none;">
		<c:if test="${fn:contains(sessionInfo.resourceList, '/fdMemberDoctorController/addPage')}">
			<%--<a onclick="addFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'bug_add'">添加</a>--%>
		</c:if>
		<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'brick_add',plain:true" onclick="searchFun();">查询</a><a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'brick_delete',plain:true" onclick="cleanFun();">清空条件</a>
		<c:if test="${fn:contains(sessionInfo.resourceList, '/fdMemberDoctorController/download')}">
			<%--<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'server_go',plain:true" onclick="downloadTable();">导出</a>		--%>
			<form id="downloadTable" target="downloadIframe" method="post" style="display: none;">
			</form>
			<iframe id="downloadIframe" name="downloadIframe" style="display: none;"></iframe>
		</c:if>
	</div>	
</body>
</html>