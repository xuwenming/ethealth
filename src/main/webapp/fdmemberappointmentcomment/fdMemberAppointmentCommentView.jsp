<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.mobian.model.TfdMemberAppointmentComment" %>
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
					<th><%=TfdMemberAppointmentComment.ALIAS_DOCTOR_ID%></th>	
					<td>
						${fdMemberAppointmentComment.doctorId}							
					</td>							
					<th><%=TfdMemberAppointmentComment.ALIAS_APPOINTMENT_ID%></th>	
					<td>
						${fdMemberAppointmentComment.appointmentId}							
					</td>							
				</tr>		
				<tr>	
					<th><%=TfdMemberAppointmentComment.ALIAS_USER_ID%></th>	
					<td>
						${fdMemberAppointmentComment.userId}							
					</td>							
					<th><%=TfdMemberAppointmentComment.ALIAS_CREATE_TIME%></th>	
					<td>
						${fdMemberAppointmentComment.createTime}							
					</td>							
				</tr>		
				<tr>	
					<th><%=TfdMemberAppointmentComment.ALIAS_UPDATE_TIME%></th>	
					<td>
						${fdMemberAppointmentComment.updateTime}							
					</td>							
					<th><%=TfdMemberAppointmentComment.ALIAS_STATUS%></th>	
					<td>
						${fdMemberAppointmentComment.status}							
					</td>							
				</tr>		
				<tr>	
					<th><%=TfdMemberAppointmentComment.ALIAS_EFFECT%></th>	
					<td>
						${fdMemberAppointmentComment.effect}							
					</td>							
					<th><%=TfdMemberAppointmentComment.ALIAS_ATTITUDE%></th>	
					<td>
						${fdMemberAppointmentComment.attitude}							
					</td>							
				</tr>		
				<tr>	
					<th><%=TfdMemberAppointmentComment.ALIAS_DISEASE%></th>	
					<td>
						${fdMemberAppointmentComment.disease}							
					</td>							
					<th><%=TfdMemberAppointmentComment.ALIAS_OBJECTIVE%></th>	
					<td>
						${fdMemberAppointmentComment.objective}							
					</td>							
				</tr>		
				<tr>	
					<th><%=TfdMemberAppointmentComment.ALIAS_OBJECTIVE_OTHER%></th>	
					<td>
						${fdMemberAppointmentComment.objectiveOther}							
					</td>							
					<th><%=TfdMemberAppointmentComment.ALIAS_THERAPY%></th>	
					<td>
						${fdMemberAppointmentComment.therapy}							
					</td>							
				</tr>		
				<tr>	
					<th><%=TfdMemberAppointmentComment.ALIAS_THERAPY_OTHER%></th>	
					<td>
						${fdMemberAppointmentComment.therapyOther}							
					</td>							
					<th><%=TfdMemberAppointmentComment.ALIAS_COMMENT%></th>	
					<td>
						${fdMemberAppointmentComment.comment}							
					</td>							
				</tr>		
		</table>
	</div>
</div>