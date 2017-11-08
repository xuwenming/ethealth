<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.mobian.model.TfdCustomer" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="jb" uri="http://www.jb.cn/jbtag"%> 
<!DOCTYPE html>
<html>
<head>
<title>FdCustomer管理</title>
<jsp:include page="../inc.jsp"></jsp:include>
<c:if test="${fn:contains(sessionInfo.resourceList, '/fdCustomerController/editPage')}">
	<script type="text/javascript">
		$.canEdit = true;
	</script>
</c:if>
<c:if test="${fn:contains(sessionInfo.resourceList, '/fdCustomerController/delete')}">
	<script type="text/javascript">
		$.canDelete = true;
	</script>
</c:if>
<c:if test="${fn:contains(sessionInfo.resourceList, '/fdCustomerController/view')}">
	<script type="text/javascript">
		$.canView = true;
	</script>
</c:if>
<script type="text/javascript">
	var dataGrid;
	$(function() {
		dataGrid = $('#dataGrid').datagrid({
			url : '${pageContext.request.contextPath}/fdCustomerController/dataGrid',
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
				field : 'realName',
				title : '<%=TfdCustomer.ALIAS_REAL_NAME%>',
				width : 50		
				}, {
				field : 'phone',
				title : '<%=TfdCustomer.ALIAS_PHONE%>',
				width : 50		
				}, {
				field : 'province',
				title : '<%=TfdCustomer.ALIAS_PROVINCE%>',
				width : 50		
				}, {
				field : 'city',
				title : '<%=TfdCustomer.ALIAS_CITY%>',
				width : 50		
				}, {
				field : 'county',
				title : '<%=TfdCustomer.ALIAS_COUNTY%>',
				width : 50		
				}, {
				field : 'addr',
				title : '<%=TfdCustomer.ALIAS_ADDR%>',
				width : 50		
				}, {
				field : 'qq',
				title : '<%=TfdCustomer.ALIAS_QQ%>',
				width : 50		
				}, {
				field : 'sex',
				title : '<%=TfdCustomer.ALIAS_SEX%>',
				width : 50		
				}, {
				field : 'birthday',
				title : '<%=TfdCustomer.ALIAS_BIRTHDAY%>',
				width : 50		
				}, {
				field : 'groupId',
				title : '<%=TfdCustomer.ALIAS_GROUP_ID%>',
				width : 50		
				}, {
				field : 'point',
				title : '<%=TfdCustomer.ALIAS_POINT%>',
				width : 50		
				}, {
				field : 'messageIds',
				title : '<%=TfdCustomer.ALIAS_MESSAGE_IDS%>',
				width : 50		
				}, {
				field : 'prop',
				title : '<%=TfdCustomer.ALIAS_PROP%>',
				width : 50		
				}, {
				field : 'balance',
				title : '<%=TfdCustomer.ALIAS_BALANCE%>',
				width : 50		
				}, {
				field : 'custom',
				title : '<%=TfdCustomer.ALIAS_CUSTOM%>',
				width : 50		
				}, {
				field : 'checkinTime',
				title : '<%=TfdCustomer.ALIAS_CHECKIN_TIME%>',
				width : 50		
				}, {
				field : 'nickName',
				title : '<%=TfdCustomer.ALIAS_NICK_NAME%>',
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
				$.post('${pageContext.request.contextPath}/fdCustomerController/delete', {
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
			href : '${pageContext.request.contextPath}/fdCustomerController/editPage?id=' + id,
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
			href : '${pageContext.request.contextPath}/fdCustomerController/view?id=' + id
		});
	}

	function addFun() {
		parent.$.modalDialog({
			title : '添加数据',
			width : 780,
			height : 500,
			href : '${pageContext.request.contextPath}/fdCustomerController/addPage',
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
	        url:'${pageContext.request.contextPath}/fdCustomerController/download',
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
							<th><%=TfdCustomer.ALIAS_REAL_NAME%></th>	
							<td>
											<input type="text" name="realName" maxlength="50" class="span2"/>
							</td>
							<th><%=TfdCustomer.ALIAS_PHONE%></th>	
							<td>
											<input type="text" name="phone" maxlength="50" class="span2"/>
							</td>
							<th><%=TfdCustomer.ALIAS_PROVINCE%></th>	
							<td>
											<input type="text" name="province" maxlength="19" class="span2"/>
							</td>
							<th><%=TfdCustomer.ALIAS_CITY%></th>	
							<td>
											<input type="text" name="city" maxlength="19" class="span2"/>
							</td>
						</tr>	
						<tr>	
							<th><%=TfdCustomer.ALIAS_COUNTY%></th>	
							<td>
											<input type="text" name="county" maxlength="19" class="span2"/>
							</td>
							<th><%=TfdCustomer.ALIAS_ADDR%></th>	
							<td>
											<input type="text" name="addr" maxlength="250" class="span2"/>
							</td>
							<th><%=TfdCustomer.ALIAS_QQ%></th>	
							<td>
											<input type="text" name="qq" maxlength="15" class="span2"/>
							</td>
							<th><%=TfdCustomer.ALIAS_SEX%></th>	
							<td>
											<input type="text" name="sex" maxlength="0" class="span2"/>
							</td>
						</tr>	
						<tr>	
							<th><%=TfdCustomer.ALIAS_BIRTHDAY%></th>	
							<td>
											<input type="text" name="birthday" maxlength="19" class="span2"/>
							</td>
							<th><%=TfdCustomer.ALIAS_GROUP_ID%></th>	
							<td>
											<input type="text" name="groupId" maxlength="10" class="span2"/>
							</td>
							<th><%=TfdCustomer.ALIAS_POINT%></th>	
							<td>
											<input type="text" name="point" maxlength="10" class="span2"/>
							</td>
							<th><%=TfdCustomer.ALIAS_MESSAGE_IDS%></th>	
							<td>
											<input type="text" name="messageIds" maxlength="65535" class="span2"/>
							</td>
						</tr>	
						<tr>	
							<th><%=TfdCustomer.ALIAS_PROP%></th>	
							<td>
											<input type="text" name="prop" maxlength="65535" class="span2"/>
							</td>
							<th><%=TfdCustomer.ALIAS_BALANCE%></th>	
							<td>
											<input type="text" name="balance" maxlength="10" class="span2"/>
							</td>
							<th><%=TfdCustomer.ALIAS_CUSTOM%></th>	
							<td>
											<input type="text" name="custom" maxlength="255" class="span2"/>
							</td>
							<th><%=TfdCustomer.ALIAS_CHECKIN_TIME%></th>	
							<td>
											<input type="text" name="checkinTime" maxlength="19" class="span2"/>
							</td>
						</tr>	
						<tr>	
							<th><%=TfdCustomer.ALIAS_NICK_NAME%></th>	
							<td>
											<input type="text" name="nickName" maxlength="50" class="span2"/>
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
		<c:if test="${fn:contains(sessionInfo.resourceList, '/fdCustomerController/addPage')}">
			<a onclick="addFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'bug_add'">添加</a>
		</c:if>
		<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'brick_add',plain:true" onclick="searchFun();">过滤条件</a><a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'brick_delete',plain:true" onclick="cleanFun();">清空条件</a>
		<c:if test="${fn:contains(sessionInfo.resourceList, '/fdCustomerController/download')}">
			<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'server_go',plain:true" onclick="downloadTable();">导出</a>		
			<form id="downloadTable" target="downloadIframe" method="post" style="display: none;">
			</form>
			<iframe id="downloadIframe" name="downloadIframe" style="display: none;"></iframe>
		</c:if>
	</div>	
</body>
</html>