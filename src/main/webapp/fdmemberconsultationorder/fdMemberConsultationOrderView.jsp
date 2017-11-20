<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.mobian.model.TfdMemberConsultationOrder" %>
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
					<th><%=TfdMemberConsultationOrder.ALIAS_USER_ID%></th>	
					<td>
						${fdMemberConsultationOrder.userId}							
					</td>							
					<th><%=TfdMemberConsultationOrder.ALIAS_DOCTOR_ID%></th>	
					<td>
						${fdMemberConsultationOrder.doctorId}							
					</td>							
				</tr>		
				<tr>	
					<th><%=TfdMemberConsultationOrder.ALIAS_ORDER_NO%></th>	
					<td>
						${fdMemberConsultationOrder.orderNo}							
					</td>							
					<th><%=TfdMemberConsultationOrder.ALIAS_CREATE_BY%></th>	
					<td>
						${fdMemberConsultationOrder.createBy}							
					</td>							
				</tr>		
				<tr>	
					<th><%=TfdMemberConsultationOrder.ALIAS_CREATE_TIME%></th>	
					<td>
						${fdMemberConsultationOrder.createTime}							
					</td>							
					<th><%=TfdMemberConsultationOrder.ALIAS_UPDATE_BY%></th>	
					<td>
						${fdMemberConsultationOrder.updateBy}							
					</td>							
				</tr>		
				<tr>	
					<th><%=TfdMemberConsultationOrder.ALIAS_UPDATE_TIME%></th>	
					<td>
						${fdMemberConsultationOrder.updateTime}							
					</td>							
					<th><%=TfdMemberConsultationOrder.ALIAS_STATUS%></th>	
					<td>
						${fdMemberConsultationOrder.status}							
					</td>							
				</tr>		
		</table>
	</div>
</div>