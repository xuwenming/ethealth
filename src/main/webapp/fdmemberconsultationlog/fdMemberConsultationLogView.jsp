<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.mobian.model.TfdMemberConsultationLog" %>
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
					<th><%=TfdMemberConsultationLog.ALIAS_MTYPE%></th>	
					<td>
						${fdMemberConsultationLog.mtype}							
					</td>							
					<th><%=TfdMemberConsultationLog.ALIAS_FROM_USER_ID%></th>	
					<td>
						${fdMemberConsultationLog.fromUserId}							
					</td>							
				</tr>		
				<tr>	
					<th><%=TfdMemberConsultationLog.ALIAS_TO_USER_ID%></th>	
					<td>
						${fdMemberConsultationLog.toUserId}							
					</td>							
					<th><%=TfdMemberConsultationLog.ALIAS_CREATE_TIME%></th>	
					<td>
						${fdMemberConsultationLog.createTime}							
					</td>							
				</tr>		
				<tr>	
					<th><%=TfdMemberConsultationLog.ALIAS_UPDATE_TIME%></th>	
					<td>
						${fdMemberConsultationLog.updateTime}							
					</td>							
					<th><%=TfdMemberConsultationLog.ALIAS_STATUS%></th>	
					<td>
						${fdMemberConsultationLog.status}							
					</td>							
				</tr>		
				<tr>	
					<th><%=TfdMemberConsultationLog.ALIAS_CONTENT%></th>	
					<td>
						${fdMemberConsultationLog.content}							
					</td>							
					<th><%=TfdMemberConsultationLog.ALIAS_SENDER_TYPE%></th>	
					<td>
						${fdMemberConsultationLog.senderType}							
					</td>							
				</tr>		
		</table>
	</div>
</div>