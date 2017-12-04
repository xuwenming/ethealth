<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.mobian.model.TfdMemberConsultationFriend" %>
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
					<th><%=TfdMemberConsultationFriend.ALIAS_USER_ID%></th>	
					<td>
						${fdMemberConsultationFriend.userId}							
					</td>							
					<th><%=TfdMemberConsultationFriend.ALIAS_DOCTOR_ID%></th>	
					<td>
						${fdMemberConsultationFriend.doctorId}							
					</td>							
				</tr>		
				<tr>	
					<th><%=TfdMemberConsultationFriend.ALIAS_CREATE_TIME%></th>	
					<td>
						${fdMemberConsultationFriend.createTime}							
					</td>							
					<th><%=TfdMemberConsultationFriend.ALIAS_STATUS%></th>	
					<td>
						${fdMemberConsultationFriend.status}							
					</td>							
				</tr>		
				<tr>	
					<th><%=TfdMemberConsultationFriend.ALIAS_LAST_CONTENT%></th>	
					<td>
						${fdMemberConsultationFriend.lastContent}							
					</td>							
					<th><%=TfdMemberConsultationFriend.ALIAS_LAST_TIME%></th>	
					<td>
						${fdMemberConsultationFriend.lastTime}							
					</td>							
				</tr>		
		</table>
	</div>
</div>