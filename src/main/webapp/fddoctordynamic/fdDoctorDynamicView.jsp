<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.mobian.model.TfdDoctorDynamic" %>
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
					<th><%=TfdDoctorDynamic.ALIAS_USER_ID%></th>	
					<td>
						${fdDoctorDynamic.userId}							
					</td>							
					<th><%=TfdDoctorDynamic.ALIAS_CREATE_TIME%></th>	
					<td>
						${fdDoctorDynamic.createTime}							
					</td>							
				</tr>		
				<tr>	
					<th><%=TfdDoctorDynamic.ALIAS_UPDATE_TIME%></th>	
					<td>
						${fdDoctorDynamic.updateTime}							
					</td>							
					<th><%=TfdDoctorDynamic.ALIAS_STATUS%></th>	
					<td>
						${fdDoctorDynamic.status}							
					</td>							
				</tr>		
				<tr>	
					<th><%=TfdDoctorDynamic.ALIAS_CONTENT%></th>	
					<td>
						${fdDoctorDynamic.content}							
					</td>							
				</tr>		
		</table>
	</div>
</div>