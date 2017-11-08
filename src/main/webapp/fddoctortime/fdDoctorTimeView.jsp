<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.mobian.model.TfdDoctorTime" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript">
	$(function() {
		parent.$.messager.progress('close');		
	});
</script>
<div class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'center',border:false">
		<table class="table table-hover table-condensed">
				<tr>	
					<th><%=TfdDoctorTime.ALIAS_USER_ID%></th>	
					<td>
						${fdDoctorTime.userId}							
					</td>							
					<th><%=TfdDoctorTime.ALIAS_WEEK%></th>	
					<td>
						${fdDoctorTime.week}							
					</td>							
				</tr>		
				<tr>	
					<th><%=TfdDoctorTime.ALIAS_TIME%></th>	
					<td>
						${fdDoctorTime.time}							
					</td>							
					<th><%=TfdDoctorTime.ALIAS_VISIT_TYPE%></th>	
					<td>
						${fdDoctorTime.visitType}							
					</td>							
				</tr>		
				<tr>	
					<th><%=TfdDoctorTime.ALIAS_ADDRESS%></th>	
					<td>
						${fdDoctorTime.address}							
					</td>							
					<th><%=TfdDoctorTime.ALIAS_NUMBER%></th>	
					<td>
						${fdDoctorTime.number}							
					</td>							
				</tr>		
				<tr>	
					<th><%=TfdDoctorTime.ALIAS_CREATE_BY%></th>	
					<td>
						${fdDoctorTime.createBy}							
					</td>							
					<th><%=TfdDoctorTime.ALIAS_CREATE_TIME%></th>	
					<td>
						${fdDoctorTime.createTime}							
					</td>							
				</tr>		
				<tr>	
					<th><%=TfdDoctorTime.ALIAS_UPDATE_BY%></th>	
					<td>
						${fdDoctorTime.updateBy}							
					</td>							
					<th><%=TfdDoctorTime.ALIAS_UPDATE_TIME%></th>	
					<td>
						${fdDoctorTime.updateTime}							
					</td>							
				</tr>		
				<tr>	
					<th><%=TfdDoctorTime.ALIAS_STATUS%></th>	
					<td>
						${fdDoctorTime.status}							
					</td>							
				</tr>		
		</table>
	</div>
</div>