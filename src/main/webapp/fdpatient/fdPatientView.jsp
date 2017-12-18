<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.mobian.model.TfdPatient" %>
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
					<th><%=TfdPatient.ALIAS_CREATE_TIME%></th>	
					<td>
						${fdPatient.createTime}							
					</td>							
					<th><%=TfdPatient.ALIAS_UPDATE_TIME%></th>	
					<td>
						${fdPatient.updateTime}							
					</td>							
				</tr>		
				<tr>	
					<th><%=TfdPatient.ALIAS_STATUS%></th>	
					<td>
						${fdPatient.status}							
					</td>							
					<th><%=TfdPatient.ALIAS_REAL_NAME%></th>	
					<td>
						${fdPatient.realName}							
					</td>							
				</tr>		
				<tr>	
					<th><%=TfdPatient.ALIAS_SEX%></th>	
					<td>
						${fdPatient.sex}							
					</td>							
					<th><%=TfdPatient.ALIAS_BIRTHDAY%></th>	
					<td>
						${fdPatient.birthday}							
					</td>							
				</tr>		
				<tr>	
					<th><%=TfdPatient.ALIAS_RELATION%></th>	
					<td>
						${fdPatient.relation}							
					</td>							
				</tr>		
		</table>
	</div>
</div>