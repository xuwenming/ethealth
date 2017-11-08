<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.mobian.model.TfdDoctorGroup" %>
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
					<th><%=TfdDoctorGroup.ALIAS_HOSPITAL_ID%></th>	
					<td>
						${fdDoctorGroup.hospitalId}							
					</td>							
					<th><%=TfdDoctorGroup.ALIAS_DEPT_ID%></th>	
					<td>
						${fdDoctorGroup.deptId}							
					</td>							
				</tr>		
				<tr>	
					<th><%=TfdDoctorGroup.ALIAS_GROUP_NAME%></th>	
					<td>
						${fdDoctorGroup.groupName}							
					</td>							
					<th><%=TfdDoctorGroup.ALIAS_INTRODUCE%></th>	
					<td>
						${fdDoctorGroup.introduce}							
					</td>							
				</tr>		
				<tr>	
					<th><%=TfdDoctorGroup.ALIAS_PICS%></th>	
					<td>
						${fdDoctorGroup.pics}							
					</td>							
					<th><%=TfdDoctorGroup.ALIAS_CREATE_BY%></th>	
					<td>
						${fdDoctorGroup.createBy}							
					</td>							
				</tr>		
				<tr>	
					<th><%=TfdDoctorGroup.ALIAS_CREATE_TIME%></th>	
					<td>
						${fdDoctorGroup.createTime}							
					</td>							
					<th><%=TfdDoctorGroup.ALIAS_UPDATE_BY%></th>	
					<td>
						${fdDoctorGroup.updateBy}							
					</td>							
				</tr>		
				<tr>	
					<th><%=TfdDoctorGroup.ALIAS_UPDATE_TIME%></th>	
					<td>
						${fdDoctorGroup.updateTime}							
					</td>							
					<th><%=TfdDoctorGroup.ALIAS_STATUS%></th>	
					<td>
						${fdDoctorGroup.status}							
					</td>							
				</tr>		
				<tr>	
					<th><%=TfdDoctorGroup.ALIAS_LEADER%></th>	
					<td>
						${fdDoctorGroup.leader}							
					</td>							
				</tr>		
		</table>
	</div>
</div>