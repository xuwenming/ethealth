<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.mobian.model.TfdMemberAppointment" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="jb" uri="http://www.jb.cn/jbtag"%> 
<!DOCTYPE html>
<html>
<head>
<title>FdMemberAppointment管理</title>
<jsp:include page="../inc.jsp"></jsp:include>
<c:if test="${fn:contains(sessionInfo.resourceList, '/fdMemberAppointmentController/editPage')}">
	<script type="text/javascript">
		$.canEdit = true;
	</script>
</c:if>
<c:if test="${fn:contains(sessionInfo.resourceList, '/fdMemberAppointmentController/delete')}">
	<script type="text/javascript">
		$.canDelete = true;
	</script>
</c:if>
<c:if test="${fn:contains(sessionInfo.resourceList, '/fdMemberAppointmentController/view')}">
	<script type="text/javascript">
		$.canView = true;
	</script>
</c:if>
<script type="text/javascript">
	var dataGrid;
	$(function() {
		dataGrid = $('#dataGrid').datagrid({
			url : '${pageContext.request.contextPath}/fdMemberAppointmentController/dataGrid',
			fit : true,
			fitColumns : false,
			border : false,
			pagination : true,
			idField : 'id',
			pageSize : 10,
			pageList : [ 10, 20, 30, 40, 50 ],
			sortName : 'id',
			sortOrder : 'desc',
			checkOnSelect : false,
			selectOnCheck : false,
			nowrap : true,
			striped : true,
			rownumbers : true,
			singleSelect : true,
			frozenColumns : [ [ {
				field : 'id',
				title : '编号',
				width : 150,
				hidden : true
				}, {
				field : 'appointmentNo',
				title : '订单号',
				width : 150
				}, {
				field : 'amount',
				title : '金额(元)',
				width : 80,
				align:'right',
				formatter:function(value){
					return $.formatMoney(value);
				}
				}, {
				field : 'createTime',
				title : '下单时间',
				width : 150,
				formatter : function (value, row, index) {
					return new Date(value).format('yyyy-MM-dd HH:mm:ss');
				}
				},{
				field : 'status',
				title : '支付状态',
				width : 100,
				formatter : function(value, row, index) {
					var str;
					if(value == 1) str = '<font color="#4cd964;">已支付</font>';
					else str =  '<font color="#f6383a;">未支付</font>';

					return str;
				}
				}, {
					field : 'appointStatus',
					title : '预约状态',
					width : 100,
					formatter : function (value, row, index) {
						var str = '医生待确认';
						if(value == 1) str = '<font color="orange">医生已确认</font>';
						else if(value == 2) str = '<font color="#4cd964;">患者已确认</font>';
						else if(value == 3 && row.status == 0) str = '<font style="color:#1AAFF0;">已取消</font>';
						else if(value == 3 && row.status == 1) str =  '<font color="red">医生已拒绝</font>';

						return str;
					}
				}]]	,
			columns : [ [{
				field : 'userName',
				title : '下单人姓名',
				width : 120
				}, {
				field : 'userMobile',
				title : '下单人手机号',
				width : 120
				}, {
				field : 'doctorName',
				title : '医生姓名',
				width : 120
				}, {
				field : 'doctorMobile',
				title : '医生手机号',
				width : 120
				}, {
				field : 'appointTime',
				title : '预约时间',
				width : 150
				}, {
				field : 'appointName',
				title : '<%=TfdMemberAppointment.ALIAS_APPOINT_NAME%>',
				width : 100
				}, {
				field : 'appointAddress',
				title : '预约地址',
				width : 150
				}, {
				field : 'appointMessage',
				title : '病情描述',
				width : 200
				}, {
				field : 'refuseReason',
				title : '拒绝理由',
				width : 200
				}, {

				field : 'sourseZh',
				title : '下单渠道',
				width : 120
				}
				<%--, {--%>
				<%--field : 'action',--%>
				<%--title : '操作',--%>
				<%--width : 100,--%>
				<%--formatter : function(value, row, index) {--%>
					<%--var str = '';--%>
					<%--if ($.canEdit) {--%>
						<%--str += $.formatString('<img onclick="editFun(\'{0}\');" src="{1}" title="编辑"/>', row.id, '${pageContext.request.contextPath}/style/images/extjs_icons/bug/bug_edit.png');--%>
					<%--}--%>
					<%--str += '&nbsp;';--%>
					<%--if ($.canDelete) {--%>
						<%--str += $.formatString('<img onclick="deleteFun(\'{0}\');" src="{1}" title="删除"/>', row.id, '${pageContext.request.contextPath}/style/images/extjs_icons/bug/bug_delete.png');--%>
					<%--}--%>
					<%--str += '&nbsp;';--%>
					<%--if ($.canView) {--%>
						<%--str += $.formatString('<img onclick="viewFun(\'{0}\');" src="{1}" title="查看"/>', row.id, '${pageContext.request.contextPath}/style/images/extjs_icons/bug/bug_link.png');--%>
					<%--}--%>
					<%--return str;--%>
				<%--}--%>
			<%--} --%>
			] ],
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
				$.post('${pageContext.request.contextPath}/fdMemberAppointmentController/delete', {
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
			href : '${pageContext.request.contextPath}/fdMemberAppointmentController/editPage?id=' + id,
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
			href : '${pageContext.request.contextPath}/fdMemberAppointmentController/view?id=' + id
		});
	}

	function addFun() {
		parent.$.modalDialog({
			title : '添加数据',
			width : 780,
			height : 500,
			href : '${pageContext.request.contextPath}/fdMemberAppointmentController/addPage',
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
		$.merge($colums, options.frozenColumns[0]);
		$.merge($colums, options.columns[0]);
		var colums = [];
		colums.push($colums);
		var columsStr = JSON.stringify(colums);
	    $('#downloadTable').form('submit', {
	        url:'${pageContext.request.contextPath}/fdMemberAppointmentController/download',
	        onSubmit: function(param){
	        	$.extend(param, $.serializeObject($('#searchForm')));
	        	param.downloadFields = columsStr;
	        	param.page = options.pageNumber;
	        	param.rows = options.pageSize;
				param.sort = options.sortName;
				param.order = options.sortOrder;
	        	
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
		<div data-options="region:'north',title:'查询条件',border:false" style="height: 115px; overflow: hidden;">
			<form id="searchForm">
				<table class="table table-hover table-condensed" style="display: none;">
						<tr>
							<th>订单号</th>
							<td>
								<input type="text" name="appointmentNo" maxlength="50" class="span2"/>
							</td>
							<th>下单人</th>
							<td>
								<jb:selectGrid dataType="userId" name="userId" params="{isAdmin:0}"></jb:selectGrid>
							</td>
							<th>医生</th>
							<td>
								<jb:selectGrid dataType="userId" name="doctorId" params="{isAdmin:2}"></jb:selectGrid>
							</td>
							<th>预约状态</th>
							<td>
								<select name="appointStatus" class="easyui-combobox"
										data-options="width:140,height:29,editable:false,panelHeight:'auto'">
									<option value="">全部</option>
									<option value="-1">已取消</option>
									<option value="0">医生待确认</option>
									<option value="1">医生已确认</option>
									<option value="2">患者已确认</option>
									<option value="3">医生已拒绝</option>
								</select>
							</td>

						</tr>
						<tr>
							<th>支付状态</th>
							<td>
								<select name="status" class="easyui-combobox"
										data-options="width:140,height:29,editable:false,panelHeight:'auto'">
									<option value="">全部</option>
									<option value="1">已支付</option>
									<option value="0">未支付</option>
								</select>
							</td>
							<th>下单时间</th>
							<td colspan="3"><input class="span2" name="createTimeStartDate"
									   placeholder="点击选择时间"
									   onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss'})"
									   readonly="readonly" />至<input class="span2"
																	 name="createTimeEndDate" placeholder="点击选择时间"
																	 onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss'})"
																	 readonly="readonly" /></td>

							<th>预约时间</th>
							<td colspan="5"><input class="span2" name="startDate"
									   placeholder="点击选择时间"
									   onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss'})"
									   readonly="readonly" />至<input class="span2"
																	 name="endDate" placeholder="点击选择时间"
																	 onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss'})"
																	 readonly="readonly" /></td>
						</tr>
				</table>
			</form>
		</div>
		<div data-options="region:'center',border:false">
			<table id="dataGrid"></table>
		</div>
	</div>
	<div id="toolbar" style="display: none;">
		<c:if test="${fn:contains(sessionInfo.resourceList, '/fdMemberAppointmentController/addPage')}">
			<!--<a onclick="addFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'bug_add'">添加</a>-->
		</c:if>
		<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'brick_add',plain:true" onclick="searchFun();">查询</a><a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'brick_delete',plain:true" onclick="cleanFun();">清空条件</a>
		<c:if test="${fn:contains(sessionInfo.resourceList, '/fdMemberAppointmentController/download')}">
			<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'server_go',plain:true" onclick="downloadTable();">导出</a>		
			<form id="downloadTable" target="downloadIframe" method="post" style="display: none;">
			</form>
			<iframe id="downloadIframe" name="downloadIframe" style="display: none;"></iframe>
		</c:if>
	</div>	
</body>
</html>