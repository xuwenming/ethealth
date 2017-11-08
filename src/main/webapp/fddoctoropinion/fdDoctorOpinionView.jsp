<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.mobian.model.TfdDoctorOpinion" %>
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
					<th><%=TfdDoctorOpinion.ALIAS_USER_ID%></th>	
					<td>
						${fdDoctorOpinion.userId}							
					</td>							
					<th><%=TfdDoctorOpinion.ALIAS_TITLE%></th>	
					<td>
						${fdDoctorOpinion.title}							
					</td>							
				</tr>		
				<tr>	
					<th><%=TfdDoctorOpinion.ALIAS_VIEW%></th>	
					<td>
						${fdDoctorOpinion.view}							
					</td>							
					<th><%=TfdDoctorOpinion.ALIAS_COMMENT%></th>	
					<td>
						${fdDoctorOpinion.comment}							
					</td>							
				</tr>		
				<tr>	
					<th><%=TfdDoctorOpinion.ALIAS_CREATE_TIME%></th>	
					<td>
						${fdDoctorOpinion.createTime}							
					</td>							
					<th><%=TfdDoctorOpinion.ALIAS_UPDATE_TIME%></th>	
					<td>
						${fdDoctorOpinion.updateTime}							
					</td>							
				</tr>		
				<tr>	
					<th><%=TfdDoctorOpinion.ALIAS_VERIFY_STATUS%></th>	
					<td>
						${fdDoctorOpinion.verifyStatus}							
					</td>							
					<th><%=TfdDoctorOpinion.ALIAS_IS_UP%></th>	
					<td>
						${fdDoctorOpinion.isUp}							
					</td>							
				</tr>		
				<tr>	
					<th><%=TfdDoctorOpinion.ALIAS_STATUS%></th>	
					<td>
						${fdDoctorOpinion.status}							
					</td>							
					<th><%=TfdDoctorOpinion.ALIAS_PIC%></th>	
					<td>
						${fdDoctorOpinion.pic}							
					</td>							
				</tr>		
				<tr>	
					<th><%=TfdDoctorOpinion.ALIAS_CONTENT%></th>	
					<td>
						${fdDoctorOpinion.content}							
					</td>							
					<th><%=TfdDoctorOpinion.ALIAS_BRIEF%></th>	
					<td>
						${fdDoctorOpinion.brief}							
					</td>							
				</tr>		
				<tr>	
					<th><%=TfdDoctorOpinion.ALIAS_FILE%></th>	
					<td>
						${fdDoctorOpinion.file}							
					</td>							
				</tr>		
		</table>
	</div>
</div>