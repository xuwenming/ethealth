<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.mobian.model.TfdMember" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="jb" uri="http://www.jb.cn/jbtag"%> 
<!DOCTYPE html>
<html>
<head>
<title>FdMember管理</title>
<jsp:include page="../inc.jsp"></jsp:include>
<c:if test="${fn:contains(sessionInfo.resourceList, '/fdMemberController/editPage')}">
	<script type="text/javascript">
		$.canEdit = true;
	</script>
</c:if>
<c:if test="${fn:contains(sessionInfo.resourceList, '/fdMemberController/delete')}">
	<script type="text/javascript">
		$.canDelete = true;
	</script>
</c:if>
<c:if test="${fn:contains(sessionInfo.resourceList, '/fdMemberController/view')}">
	<script type="text/javascript">
		$.canView = true;
	</script>
</c:if>
<script type="text/javascript">
	var dataGrid;
	$(function() {
		dataGrid = $('#dataGrid').datagrid({
			url : '${pageContext.request.contextPath}/fdMemberController/dataGrid',
			fit : true,
			fitColumns : true,
			border : false,
			pagination : true,
			idField : 'id',
			pageSize : 10,
			pageList : [ 10, 20, 30, 40, 50 ],
			sortName : 'id',
			sortOrder : 'desc',
			checkOnSelect : false,
			selectOnCheck : false,
			nowrap : false,
			striped : true,
			rownumbers : true,
			singleSelect : true,
			columns : [ [ {
				field : 'id',
				title : '编号',
				width : 150,
				hidden : true
				}, {
				field : 'username',
				title : '<%=TfdMember.ALIAS_USERNAME%>',
				width : 50		
				}, {
				field : 'password',
				title : '<%=TfdMember.ALIAS_PASSWORD%>',
				width : 50		
				}, {
				field : 'score',
				title : '<%=TfdMember.ALIAS_SCORE%>',
				width : 50		
				}, {
				field : 'email',
				title : '<%=TfdMember.ALIAS_EMAIL%>',
				width : 50		
				}, {
				field : 'login',
				title : '<%=TfdMember.ALIAS_LOGIN%>',
				width : 50		
				}, {
				field : 'mobile',
				title : '<%=TfdMember.ALIAS_MOBILE%>',
				width : 50		
				}, {
				field : 'regTime',
				title : '<%=TfdMember.ALIAS_REG_TIME%>',
				width : 50		
				}, {
				field : 'regIp',
				title : '<%=TfdMember.ALIAS_REG_IP%>',
				width : 50		
				}, {
				field : 'lastLoginTime',
				title : '<%=TfdMember.ALIAS_LAST_LOGIN_TIME%>',
				width : 50		
				}, {
				field : 'lastLoginIp',
				title : '<%=TfdMember.ALIAS_LAST_LOGIN_IP%>',
				width : 50		
				}, {
				field : 'updateTime',
				title : '<%=TfdMember.ALIAS_UPDATE_TIME%>',
				width : 50		
				}, {
				field : 'status',
				title : '<%=TfdMember.ALIAS_STATUS%>',
				width : 50		
				}, {
				field : 'isAdmin',
				title : '<%=TfdMember.ALIAS_IS_ADMIN%>',
				width : 50		
				}, {
				field : 'groupid',
				title : '<%=TfdMember.ALIAS_GROUPID%>',
				width : 50		
				}, {
				field : 'amount',
				title : '<%=TfdMember.ALIAS_AMOUNT%>',
				width : 50		
				}, {
				field : 'modelid',
				title : '<%=TfdMember.ALIAS_MODELID%>',
				width : 50		
				}, {
				field : 'message',
				title : '<%=TfdMember.ALIAS_MESSAGE%>',
				width : 50		
				}, {
				field : 'pic',
				title : '<%=TfdMember.ALIAS_PIC%>',
				width : 50		
				}, {
				field : 'doctorId',
				title : '<%=TfdMember.ALIAS_DOCTOR_ID%>',
				width : 50		
				}, {
				field : 'groupId',
				title : '<%=TfdMember.ALIAS_GROUP_ID%>',
				width : 50		
				}, {
				field : 'vipEndTime',
				title : '<%=TfdMember.ALIAS_VIP_END_TIME%>',
				width : 50		
			}, {
				field : 'action',
				title : '操作',
				width : 100,
				formatter : function(value, row, index) {
					var str = '';
					if ($.canEdit) {
						str += $.formatString('<img onclick="editFun(\'{0}\');" src="{1}" title="编辑"/>', row.id, '${pageContext.request.contextPath}/style/images/extjs_icons/bug/bug_edit.png');
					}
					str += '&nbsp;';
					if ($.canDelete) {
						str += $.formatString('<img onclick="deleteFun(\'{0}\');" src="{1}" title="删除"/>', row.id, '${pageContext.request.contextPath}/style/images/extjs_icons/bug/bug_delete.png');
					}
					str += '&nbsp;';
					if ($.canView) {
						str += $.formatString('<img onclick="viewFun(\'{0}\');" src="{1}" title="查看"/>', row.id, '${pageContext.request.contextPath}/style/images/extjs_icons/bug/bug_link.png');
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
				$.post('${pageContext.request.contextPath}/fdMemberController/delete', {
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
			width : 780,
			height : 500,
			href : '${pageContext.request.contextPath}/fdMemberController/editPage?id=' + id,
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
		if (id == undefined) {
			var rows = dataGrid.datagrid('getSelections');
			id = rows[0].id;
		}
		parent.$.modalDialog({
			title : '查看数据',
			width : 780,
			height : 500,
			href : '${pageContext.request.contextPath}/fdMemberController/view?id=' + id
		});
	}

	function addFun() {
		parent.$.modalDialog({
			title : '添加数据',
			width : 780,
			height : 500,
			href : '${pageContext.request.contextPath}/fdMemberController/addPage',
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
	        url:'${pageContext.request.contextPath}/fdMemberController/download',
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
		<div data-options="region:'north',title:'查询条件',border:false" style="height: 160px; overflow: hidden;">
			<form id="searchForm">
				<table class="table table-hover table-condensed" style="display: none;">
						<tr>	
							<th><%=TfdMember.ALIAS_USERNAME%></th>	
							<td>
											<input type="text" name="username" maxlength="16" class="span2"/>
							</td>
							<th><%=TfdMember.ALIAS_PASSWORD%></th>	
							<td>
											<input type="text" name="password" maxlength="32" class="span2"/>
							</td>
							<th><%=TfdMember.ALIAS_SCORE%></th>	
							<td>
											<input type="text" name="score" maxlength="7" class="span2"/>
							</td>
							<th><%=TfdMember.ALIAS_EMAIL%></th>	
							<td>
											<input type="text" name="email" maxlength="32" class="span2"/>
							</td>
						</tr>	
						<tr>	
							<th><%=TfdMember.ALIAS_LOGIN%></th>	
							<td>
											<input type="text" name="login" maxlength="10" class="span2"/>
							</td>
							<th><%=TfdMember.ALIAS_MOBILE%></th>	
							<td>
											<input type="text" name="mobile" maxlength="15" class="span2"/>
							</td>
							<th><%=TfdMember.ALIAS_REG_TIME%></th>	
							<td>
											<input type="text" name="regTime" maxlength="20" class="span2"/>
							</td>
							<th><%=TfdMember.ALIAS_REG_IP%></th>	
							<td>
											<input type="text" name="regIp" maxlength="19" class="span2"/>
							</td>
						</tr>	
						<tr>	
							<th><%=TfdMember.ALIAS_LAST_LOGIN_TIME%></th>	
							<td>
											<input type="text" name="lastLoginTime" maxlength="20" class="span2"/>
							</td>
							<th><%=TfdMember.ALIAS_LAST_LOGIN_IP%></th>	
							<td>
											<input type="text" name="lastLoginIp" maxlength="19" class="span2"/>
							</td>
							<th><%=TfdMember.ALIAS_UPDATE_TIME%></th>	
							<td>
											<input type="text" name="updateTime" maxlength="20" class="span2"/>
							</td>
							<th><%=TfdMember.ALIAS_STATUS%></th>	
							<td>
											<input type="text" name="status" maxlength="3" class="span2"/>
							</td>
						</tr>	
						<tr>	
							<th><%=TfdMember.ALIAS_IS_ADMIN%></th>	
							<td>
											<input type="text" name="isAdmin" maxlength="3" class="span2"/>
							</td>
							<th><%=TfdMember.ALIAS_GROUPID%></th>	
							<td>
											<input type="text" name="groupid" maxlength="3" class="span2"/>
							</td>
							<th><%=TfdMember.ALIAS_AMOUNT%></th>	
							<td>
											<input type="text" name="amount" maxlength="8" class="span2"/>
							</td>
							<th><%=TfdMember.ALIAS_MODELID%></th>	
							<td>
											<input type="text" name="modelid" maxlength="5" class="span2"/>
							</td>
						</tr>	
						<tr>	
							<th><%=TfdMember.ALIAS_MESSAGE%></th>	
							<td>
											<input type="text" name="message" maxlength="0" class="span2"/>
							</td>
							<th><%=TfdMember.ALIAS_PIC%></th>	
							<td>
											<input type="text" name="pic" maxlength="256" class="span2"/>
							</td>
							<th><%=TfdMember.ALIAS_DOCTOR_ID%></th>	
							<td>
											<input type="text" name="doctorId" maxlength="10" class="span2"/>
							</td>
							<th><%=TfdMember.ALIAS_GROUP_ID%></th>	
							<td>
											<input type="text" name="groupId" maxlength="10" class="span2"/>
							</td>
						</tr>	
						<tr>	
							<th><%=TfdMember.ALIAS_VIP_END_TIME%></th>	
							<td>
											<input type="text" name="vipEndTime" maxlength="20" class="span2"/>
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
		<c:if test="${fn:contains(sessionInfo.resourceList, '/fdMemberController/addPage')}">
			<a onclick="addFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'bug_add'">添加</a>
		</c:if>
		<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'brick_add',plain:true" onclick="searchFun();">过滤条件</a><a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'brick_delete',plain:true" onclick="cleanFun();">清空条件</a>
		<c:if test="${fn:contains(sessionInfo.resourceList, '/fdMemberController/download')}">
			<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'server_go',plain:true" onclick="downloadTable();">导出</a>		
			<form id="downloadTable" target="downloadIframe" method="post" style="display: none;">
			</form>
			<iframe id="downloadIframe" name="downloadIframe" style="display: none;"></iframe>
		</c:if>
	</div>	
</body>
</html>