<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.mobian.model.TfdMember" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="../inc.jsp"></jsp:include>
<script type="text/javascript">
	$(function() {
		parent.$.messager.progress('close');		
	});
</script>
</head>
<body>
<div class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'center',border:false">
		<div style="font-size: 16pt; padding: 8px;">基本信息</div>
		<table class="table table-hover table-condensed">
			<tr>
				<th width="5%">用户名</th>
				<td width="20%">${fdMember.username}</td>
				<th width="8%">姓名/昵称</th>
				<td width="18%">${fdMember.customer.realName == null ? fdMember.customer.nickName : fdMember.customer.realName}</td>
				<th width="5%">性别</th>
				<td width="20%">${fdMember.customer.sex == 1 ? '男' : (fdMember.customer.sex == 2 ? '女' : '未知')}</td>
				<th width="5%">头像</th>
				<td width="20%" rowspan="3">
					<c:if test="${!empty fdMember.picUrl}">
						<img src="${fdMember.picUrl}" width="80px" height="80px"/>
					</c:if>
				</td>
			</tr>
			<tr>
				<th>手机号</th>
				<td>${fdMember.mobile}</td>
				<th>出生日期</th>
				<td colspan="3">${fdMember.customer.birthdayStr}</td>
			</tr>
		</table>
		<c:if test="${fdMember.patient != null}">
			<div style="font-size: 16pt; padding: 8px;">患者信息</div>
			<table class="table table-hover table-condensed">
				<tr>

					<th width="5%">姓名</th>
					<td width="20%">${fdMember.patient.realName}</td>
					<th width="5%">性别</th>
					<td width="20%">${fdMember.patient.sex == 1 ? '男' : (fdMember.patient.sex == 2 ? '女' : '未知')}</td>
					<th width="5%">出生日期</th>
					<td width="20%">${fdMember.patient.birthdayStr}</td>
					<th width="8%">与患者关系</th>
					<td width="18%">${fdMember.patient.relationZh}</td>
				</tr>
			</table>
		</c:if>

	</div>
</div>
</body>
</html>