<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.mobian.model.TfdAccount" %>
<%@ page import="com.mobian.model.TfdDoctorCloseTime" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
	<title></title>
	<jsp:include page="../inc.jsp"></jsp:include>
<script type="text/javascript">
	$(function() {
		var gridMap = {
			handle:function(obj,clallback){
				if (obj.grid == null) {
					obj.grid = clallback();
				} else {
					obj.grid.datagrid('reload');
				}
			},
			0: {
				invoke: function () {
				}, grid: null
			}, 1: {
				invoke: function () {
					gridMap.handle(this,loadAccountDataGrid);
				}, grid: null
			}, 2: {
				invoke: function () {
					gridMap.handle(this,loadCloseTimeDataGrid);
				}, grid: null
			}
		};
		$('#doctor_view_tabs').tabs({
			onSelect: function (title, index) {
				gridMap[index].invoke();
			}
		});
	});

	function loadAccountDataGrid() {
		return $('#accountDataGrid').datagrid({
			url : '${pageContext.request.contextPath}/fdAccountController/dataGrid?userId=${fdMemberDoctor.id}',
			fit : true,
			fitColumns : true,
			border : false,
			pagination : false,
			idField : 'userId',
			pageSize : 10,
			pageList : [ 10, 20, 30, 40, 50 ],
			sortName : 'userId',
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
				field : 'bankAccount',
				title : '<%=TfdAccount.ALIAS_BANK_ACCOUNT%>',
				width : 30
			}, {
				field : 'bankPhone',
				title : '<%=TfdAccount.ALIAS_BANK_PHONE%>',
				width : 30
			}, {
				field : 'bankIdNo',
				title : '<%=TfdAccount.ALIAS_BANK_ID_NO%>',
				width : 50
			}, {
				field : 'bankCodeZh',
				title : '银行类别',
				width : 30
			}, {
				field : 'bankName',
				title : '<%=TfdAccount.ALIAS_BANK_NAME%>',
				width : 80
			}, {
				field : 'bankCard',
				title : '<%=TfdAccount.ALIAS_BANK_CARD%>',
				width : 50
			}, {
				field : 'alipay',
				title : '<%=TfdAccount.ALIAS_ALIPAY%>',
				width : 50
			} ] ]
		});
	}

	function loadCloseTimeDataGrid() {
		return $('#closeTimeDataGrid').datagrid({
			url : '${pageContext.request.contextPath}/fdDoctorCloseTimeController/dataGrid?doctorId=${fdMemberDoctor.id}',
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
				field : 'closeDate',
				title : '<%=TfdDoctorCloseTime.ALIAS_CLOSE_DATE%>',
				width : 30,
				formatter : function(value, row, index) {
					return new Date(value).format('yyyy-MM-dd');
				}
			}, {
				field : 'time',
				title : 'time',
				width : 50,
				formatter : function(value, row, index) {
					var str = row.week + " ";
					if(value == 1) str += '上午';
					else if(value == 2) str += '下午';
					else if(value == 3) str += '夜班';
					else str += '全天';
					return str;
				}
			} ] ]
		});
	}
</script>
</head>
<body>
	<div class="easyui-layout" data-options="fit:true,border:false">
		<div data-options="region:'center',border:false">
			<div id="doctor_view_tabs" class="easyui-tabs" data-options="fit : true,border:false">
				<div title="基本信息">
					<table class="table table-hover table-condensed">
						<tr>
							<th width="5%">用户名</th>
							<td width="20%">${fdMemberDoctor.username}</td>
							<th width="8%">姓名</th>
							<td width="18%">${fdMemberDoctor.customer.realName}</td>
							<th width="5%">性别</th>
							<td width="20%">${fdMemberDoctor.customer.sex == 1 ? '男' : (fdMemberDoctor.customer.sex == 2 ? '女' : '未知')}</td>
							<th width="5%">头像</th>
							<td width="20%" rowspan="3">
								<c:if test="${!empty fdMemberDoctor.picUrl}">
									<img src="${fdMemberDoctor.picUrl}" width="80px" height="80px"/>
								</c:if>
							</td>
						</tr>
						<tr>
							<th>手机号</th>
							<td>${fdMemberDoctor.mobile}</td>
							<th>出生日期</th>
							<td>${fdMemberDoctor.customer.birthdayStr}</td>
							<th>邮箱</th>
							<td>${fdMemberDoctor.email}</td>
						</tr>
						<tr>
							<th>所属团队</th>
							<td>${fdMemberDoctor.groupName}</td>
							<th>所在医院</th>
							<td>${fdMemberDoctor.hospitalName}</td>
							<th>科室</th>
							<td>${fdMemberDoctor.departmentName}</td>
						</tr>
						<tr>
							<th>职称</th>
							<td>${fdMemberDoctor.levelName}</td>
							<th>学历</th>
							<td>${fdMemberDoctor.educationName}</td>
							<th>排序</th>
							<td>${fdMemberDoctor.seq}</td>
						</tr>
						<tr>
							<th>著名专家</th>
							<td>${fdMemberDoctor.isBest ? '是' : '否'}</td>
							<th>加号服务</th>
							<td>${fdMemberDoctor.acceptAppointment ? '开启' : '关闭'}</td>
							<th>咨询服务</th>
							<td>${fdMemberDoctor.acceptConsultation ? '开启' : '关闭'}</td>
						</tr>
						<tr>
							<th>特长介绍</th>
							<td colspan="5">${fdMemberDoctor.speciality}</td>
						</tr>
						<tr>
							<th>个人简介</th>
							<td colspan="5">${fdMemberDoctor.introduce}</td>
						</tr>
					</table>
				</div>
				<div title="账号信息">
					<table id="accountDataGrid"></table>
				</div>
				<div title="停诊记录">
					<table id="closeTimeDataGrid"></table>
				</div>
			</div>
		</div>
	</div>
</body>
</html>