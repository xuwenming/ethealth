<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.mobian.model.TfdMemberConsultation" %>
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
					<th><%=TfdMemberConsultation.ALIAS_USER_ID%></th>	
					<td>
						${fdMemberConsultation.userId}							
					</td>							
					<th><%=TfdMemberConsultation.ALIAS_DOCTOR_ID%></th>	
					<td>
						${fdMemberConsultation.doctorId}							
					</td>							
				</tr>		
				<tr>	
					<th><%=TfdMemberConsultation.ALIAS_CONSULTATION_MESSAGE%></th>	
					<td>
						${fdMemberConsultation.consultationMessage}							
					</td>							
					<th><%=TfdMemberConsultation.ALIAS_CREATE_BY%></th>	
					<td>
						${fdMemberConsultation.createBy}							
					</td>							
				</tr>		
				<tr>	
					<th><%=TfdMemberConsultation.ALIAS_CREATE_TIME%></th>	
					<td>
						${fdMemberConsultation.createTime}							
					</td>							
					<th><%=TfdMemberConsultation.ALIAS_UPDATE_BY%></th>	
					<td>
						${fdMemberConsultation.updateBy}							
					</td>							
				</tr>		
				<tr>	
					<th><%=TfdMemberConsultation.ALIAS_UPDATE_TIME%></th>	
					<td>
						${fdMemberConsultation.updateTime}							
					</td>							
					<th><%=TfdMemberConsultation.ALIAS_STATUS%></th>	
					<td>
						${fdMemberConsultation.status}							
					</td>							
				</tr>		
				<tr>	
					<th><%=TfdMemberConsultation.ALIAS_COUPON_ID%></th>	
					<td>
						${fdMemberConsultation.couponId}							
					</td>							
					<th><%=TfdMemberConsultation.ALIAS_PICS%></th>	
					<td>
						${fdMemberConsultation.pics}							
					</td>							
				</tr>		
				<tr>	
					<th><%=TfdMemberConsultation.ALIAS_FILE%></th>	
					<td>
						${fdMemberConsultation.file}							
					</td>							
					<th><%=TfdMemberConsultation.ALIAS_CONSULTATION_STATUS%></th>	
					<td>
						${fdMemberConsultation.consultationStatus}							
					</td>							
				</tr>		
				<tr>	
					<th><%=TfdMemberConsultation.ALIAS_DOCTOR_REPLY%></th>	
					<td>
						${fdMemberConsultation.doctorReply}							
					</td>							
				</tr>		
		</table>
	</div>
</div>