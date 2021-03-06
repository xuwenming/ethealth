<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.mobian.model.TfdMemberDoctorSh" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="jb" uri="http://www.jb.cn/jbtag"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<script type="text/javascript">
	$(function() {
		parent.$.messager.progress('close');
		$('#form').form({
			url : '${pageContext.request.contextPath}/fdMemberDoctorShController/edit',
			onSubmit : function() {
				parent.$.messager.progress({
					title : '提示',
					text : '数据处理中，请稍后....'
				});
				var isValid = $(this).form('validate');
				if (!isValid) {
					parent.$.messager.progress('close');
				}
				return isValid;
			},
			success : function(result) {
				parent.$.messager.progress('close');
				result = $.parseJSON(result);
				if (result.success) {
					parent.$.modalDialog.openner_dataGrid.datagrid('reload');//之所以能在这里调用到parent.$.modalDialog.openner_dataGrid这个对象，是因为user.jsp页面预定义好了
					parent.$.modalDialog.handler.dialog('close');
				} else {
					parent.$.messager.alert('错误', result.msg, 'error');
				}
			}
		});
	});
</script>
<div class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'center',border:false" title="" style="overflow: auto;">
		<form id="form" method="post">
			<input type="hidden" name="id" value = "${fdMemberDoctorSh.id}"/>
			<input type="hidden" name="auditType" value = "${fdMemberDoctorSh.auditType}"/>
			<table class="table table-hover table-condensed">
				<tr>
					<th width="10%">手机号</th>
					<td width="40%">${fdMemberDoctorSh.mobile}</td>
					<th width="10%">头像</th>
					<td width="40%" rowspan="3">
						<c:if test="${!empty fdMemberDoctorSh.pics}">
							<img src="${fdMemberDoctorSh.pics}" width="80px" height="80px"/>
						</c:if>
					</td>
				</tr>
				<tr>
					<th>真实姓名</th>
					<td>
						${fdMemberDoctorSh.realName}
					</td>
				</tr>
				<tr>
					<th>性别</th>
					<td>
						<c:choose>
							<c:when test="${fdMemberDoctorSh.sex == 1}">男</c:when>
							<c:when test="${fdMemberDoctorSh.sex == 2}">女</c:when>
						</c:choose>
					</td>
				</tr>
				<tr>
					<th>出生日期</th>
					<td>
						${fdMemberDoctorSh.birthdayStr}
					</td>
					<th>邮箱</th>
					<td>${fdMemberDoctorSh.email}</td>
				</tr>
				<tr>
					<th>职称</th>
					<td>
						${fdMemberDoctorSh.levelName}
					</td>
					<th>科室</th>
					<td>${fdMemberDoctorSh.departmentName}</td>
				</tr>
				<tr>
					<th>医院全称</th>
					<td colspan="3">
						${fdMemberDoctorSh.hospitalName}
					</td>
				</tr>
				<tr>
					<th>专业特长</th>
					<td colspan="3">
						${fdMemberDoctorSh.speciality}
					</td>
				</tr>
				<tr>
					<th>个人简介</th>
					<td colspan="3">
						${fdMemberDoctorSh.introduce}
					</td>
				</tr>
				<tr>
					<th>提交时间</th>
					<td colspan="3">
						<fmt:formatDate value="${fdMemberDoctorSh.updateTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
					</td>
				</tr>
				<tr>
					<th>审核状态</th>
					<td>
						<input type="hidden" name="status" value = "${fdMemberDoctorSh.status}"/>
						<c:choose>
							<c:when test="${fdMemberDoctorSh.status == 1}">审核中</c:when>
							<c:when test="${fdMemberDoctorSh.status == 2}">审核通过</c:when>
							<c:otherwise>审核拒绝</c:otherwise>
						</c:choose>
					</td>
					<th>审核类型</th>
					<td>
						<c:choose>
							<c:when test="${fdMemberDoctorSh.auditType == 1}">注册</c:when>
							<c:when test="${fdMemberDoctorSh.auditType == 2}">编辑</c:when>
						</c:choose>
					</td>
				</tr>

				<tr>
					<th>处理结果</th>
					<td colspan="3">
						<textarea class="easyui-validatebox" data-options="required:true" style="width: 510px;height: 60px;" name="reason">${fdMemberDoctorSh.reason}</textarea>
					</td>
				</tr>
			</table>				
		</form>
	</div>
</div>