<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.mobian.model.TfdMemberDoctorLevel" %>
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
					<th><%=TfdMemberDoctorLevel.ALIAS_NAME%></th>	
					<td>
						${fdMemberDoctorLevel.name}							
					</td>							
					<th><%=TfdMemberDoctorLevel.ALIAS_REMARK%></th>	
					<td>
						${fdMemberDoctorLevel.remark}							
					</td>							
				</tr>		
				<tr>	
					<th><%=TfdMemberDoctorLevel.ALIAS_CREATE_BY%></th>	
					<td>
						${fdMemberDoctorLevel.createBy}							
					</td>							
					<th><%=TfdMemberDoctorLevel.ALIAS_CREATE_TIME%></th>	
					<td>
						${fdMemberDoctorLevel.createTime}							
					</td>							
				</tr>		
				<tr>	
					<th><%=TfdMemberDoctorLevel.ALIAS_UPDATE_BY%></th>	
					<td>
						${fdMemberDoctorLevel.updateBy}							
					</td>							
					<th><%=TfdMemberDoctorLevel.ALIAS_UPDATE_TIME%></th>	
					<td>
						${fdMemberDoctorLevel.updateTime}							
					</td>							
				</tr>		
				<tr>	
					<th><%=TfdMemberDoctorLevel.ALIAS_STATUS%></th>	
					<td>
						${fdMemberDoctorLevel.status}							
					</td>							
				</tr>		
		</table>
	</div>
</div>