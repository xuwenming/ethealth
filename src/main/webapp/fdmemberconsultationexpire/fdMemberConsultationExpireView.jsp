<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.mobian.model.TfdMemberConsultationExpire" %>
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
					<th><%=TfdMemberConsultationExpire.ALIAS_USER_ID%></th>	
					<td>
						${fdMemberConsultationExpire.userId}							
					</td>							
					<th><%=TfdMemberConsultationExpire.ALIAS_DOCTOR_ID%></th>	
					<td>
						${fdMemberConsultationExpire.doctorId}							
					</td>							
				</tr>		
				<tr>	
					<th><%=TfdMemberConsultationExpire.ALIAS_CREATE_BY%></th>	
					<td>
						${fdMemberConsultationExpire.createBy}							
					</td>							
					<th><%=TfdMemberConsultationExpire.ALIAS_CREATE_TIME%></th>	
					<td>
						${fdMemberConsultationExpire.createTime}							
					</td>							
				</tr>		
				<tr>	
					<th><%=TfdMemberConsultationExpire.ALIAS_UPDATE_BY%></th>	
					<td>
						${fdMemberConsultationExpire.updateBy}							
					</td>							
					<th><%=TfdMemberConsultationExpire.ALIAS_UPDATE_TIME%></th>	
					<td>
						${fdMemberConsultationExpire.updateTime}							
					</td>							
				</tr>		
				<tr>	
					<th><%=TfdMemberConsultationExpire.ALIAS_STATUS%></th>	
					<td>
						${fdMemberConsultationExpire.status}							
					</td>							
					<th><%=TfdMemberConsultationExpire.ALIAS_EXPIRE_DATE%></th>	
					<td>
						${fdMemberConsultationExpire.expireDate}							
					</td>							
				</tr>		
		</table>
	</div>
</div>