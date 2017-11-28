<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.mobian.model.TfdMemberAppointmentComment" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="jb" uri="http://www.jb.cn/jbtag"%> 
<!DOCTYPE html>
<html>
<head>
<title>FdMemberAppointmentComment管理</title>
<jsp:include page="../inc.jsp"></jsp:include>
<c:if test="${fn:contains(sessionInfo.resourceList, '/fdMemberAppointmentCommentController/editPage')}">
	<script type="text/javascript">
		$.canEdit = true;
	</script>
</c:if>
<c:if test="${fn:contains(sessionInfo.resourceList, '/fdMemberAppointmentCommentController/delete')}">
	<script type="text/javascript">
		$.canDelete = true;
	</script>
</c:if>
<c:if test="${fn:contains(sessionInfo.resourceList, '/fdMemberAppointmentCommentController/view')}">
	<script type="text/javascript">
		$.canView = true;
	</script>
</c:if>
<script type="text/javascript">
	var dataGrid;
	$(function() {
		dataGrid = $('#dataGrid').datagrid({
			url : '${pageContext.request.contextPath}/fdMemberAppointmentCommentController/dataGrid',
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
				field : 'doctorId',
				title : '<%=TfdMemberAppointmentComment.ALIAS_DOCTOR_ID%>',
				width : 50		
				}, {
				field : 'appointmentId',
				title : '<%=TfdMemberAppointmentComment.ALIAS_APPOINTMENT_ID%>',
				width : 50		
				}, {
				field : 'userId',
				title : '<%=TfdMemberAppointmentComment.ALIAS_USER_ID%>',
				width : 50		
				}, {
				field : 'createTime',
				title : '<%=TfdMemberAppointmentComment.ALIAS_CREATE_TIME%>',
				width : 50		
				}, {
				field : 'updateTime',
				title : '<%=TfdMemberAppointmentComment.ALIAS_UPDATE_TIME%>',
				width : 50		
				}, {
				field : 'status',
				title : '<%=TfdMemberAppointmentComment.ALIAS_STATUS%>',
				width : 50		
				}, {
				field : 'effect',
				title : '<%=TfdMemberAppointmentComment.ALIAS_EFFECT%>',
				width : 50		
				}, {
				field : 'attitude',
				title : '<%=TfdMemberAppointmentComment.ALIAS_ATTITUDE%>',
				width : 50		
				}, {
				field : 'disease',
				title : '<%=TfdMemberAppointmentComment.ALIAS_DISEASE%>',
				width : 50		
				}, {
				field : 'objective',
				title : '<%=TfdMemberAppointmentComment.ALIAS_OBJECTIVE%>',
				width : 50		
				}, {
				field : 'objectiveOther',
				title : '<%=TfdMemberAppointmentComment.ALIAS_OBJECTIVE_OTHER%>',
				width : 50		
				}, {
				field : 'therapy',
				title : '<%=TfdMemberAppointmentComment.ALIAS_THERAPY%>',
				width : 50		
				}, {
				field : 'therapyOther',
				title : '<%=TfdMemberAppointmentComment.ALIAS_THERAPY_OTHER%>',
				width : 50		
				}, {
				field : 'comment',
				title : '<%=TfdMemberAppointmentComment.ALIAS_COMMENT%>',
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
				$.post('${pageContext.request.contextPath}/fdMemberAppointmentCommentController/delete', {
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
			href : '${pageContext.request.contextPath}/fdMemberAppointmentCommentController/editPage?id=' + id,
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
			href : '${pageContext.request.contextPath}/fdMemberAppointmentCommentController/view?id=' + id
		});
	}

	function addFun() {
		parent.$.modalDialog({
			title : '添加数据',
			width : 780,
			height : 500,
			href : '${pageContext.request.contextPath}/fdMemberAppointmentCommentController/addPage',
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
	        url:'${pageContext.request.contextPath}/fdMemberAppointmentCommentController/download',
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
							<th><%=TfdMemberAppointmentComment.ALIAS_DOCTOR_ID%></th>	
							<td>
											<input type="text" name="doctorId" maxlength="10" class="span2"/>
							</td>
							<th><%=TfdMemberAppointmentComment.ALIAS_APPOINTMENT_ID%></th>	
							<td>
											<input type="text" name="appointmentId" maxlength="10" class="span2"/>
							</td>
							<th><%=TfdMemberAppointmentComment.ALIAS_USER_ID%></th>	
							<td>
											<input type="text" name="userId" maxlength="10" class="span2"/>
							</td>
							<th><%=TfdMemberAppointmentComment.ALIAS_CREATE_TIME%></th>	
							<td>
											<input type="text" name="createTime" maxlength="19" class="span2"/>
							</td>
						</tr>	
						<tr>	
							<th><%=TfdMemberAppointmentComment.ALIAS_UPDATE_TIME%></th>	
							<td>
											<input type="text" name="updateTime" maxlength="19" class="span2"/>
							</td>
							<th><%=TfdMemberAppointmentComment.ALIAS_STATUS%></th>	
							<td>
											<input type="text" name="status" maxlength="2" class="span2"/>
							</td>
							<th><%=TfdMemberAppointmentComment.ALIAS_EFFECT%></th>	
							<td>
											<input type="text" name="effect" maxlength="12" class="span2"/>
							</td>
							<th><%=TfdMemberAppointmentComment.ALIAS_ATTITUDE%></th>	
							<td>
											<input type="text" name="attitude" maxlength="12" class="span2"/>
							</td>
						</tr>	
						<tr>	
							<th><%=TfdMemberAppointmentComment.ALIAS_DISEASE%></th>	
							<td>
											<input type="text" name="disease" maxlength="100" class="span2"/>
							</td>
							<th><%=TfdMemberAppointmentComment.ALIAS_OBJECTIVE%></th>	
							<td>
											<jb:select dataType="OT" name="objective"></jb:select>	
							</td>
							<th><%=TfdMemberAppointmentComment.ALIAS_OBJECTIVE_OTHER%></th>	
							<td>
											<input type="text" name="objectiveOther" maxlength="100" class="span2"/>
							</td>
							<th><%=TfdMemberAppointmentComment.ALIAS_THERAPY%></th>	
							<td>
											<jb:select dataType="TW" name="therapy"></jb:select>	
							</td>
						</tr>	
						<tr>	
							<th><%=TfdMemberAppointmentComment.ALIAS_THERAPY_OTHER%></th>	
							<td>
											<input type="text" name="therapyOther" maxlength="100" class="span2"/>
							</td>
							<th><%=TfdMemberAppointmentComment.ALIAS_COMMENT%></th>	
							<td>
											<input type="text" name="comment" maxlength="300" class="span2"/>
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
		<c:if test="${fn:contains(sessionInfo.resourceList, '/fdMemberAppointmentCommentController/addPage')}">
			<a onclick="addFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'bug_add'">添加</a>
		</c:if>
		<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'brick_add',plain:true" onclick="searchFun();">过滤条件</a><a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'brick_delete',plain:true" onclick="cleanFun();">清空条件</a>
		<c:if test="${fn:contains(sessionInfo.resourceList, '/fdMemberAppointmentCommentController/download')}">
			<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'server_go',plain:true" onclick="downloadTable();">导出</a>		
			<form id="downloadTable" target="downloadIframe" method="post" style="display: none;">
			</form>
			<iframe id="downloadIframe" name="downloadIframe" style="display: none;"></iframe>
		</c:if>
	</div>	
</body>
</html>