<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.mobian.model.TfdFeedback" %>
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
					<th><%=TfdFeedback.ALIAS_CONTACT_WAY%></th>	
					<td>
						${fdFeedback.contactWay}							
					</td>							
					<th><%=TfdFeedback.ALIAS_CONTENT%></th>	
					<td>
						${fdFeedback.content}							
					</td>							
				</tr>		
				<tr>	
					<th><%=TfdFeedback.ALIAS_CREATE_BY%></th>	
					<td>
						${fdFeedback.createBy}							
					</td>							
					<th><%=TfdFeedback.ALIAS_CREATE_TIME%></th>	
					<td>
						${fdFeedback.createTime}							
					</td>							
				</tr>		
				<tr>	
					<th><%=TfdFeedback.ALIAS_UPDATE_BY%></th>	
					<td>
						${fdFeedback.updateBy}							
					</td>							
					<th><%=TfdFeedback.ALIAS_UPDATE_TIME%></th>	
					<td>
						${fdFeedback.updateTime}							
					</td>							
				</tr>		
				<tr>	
					<th><%=TfdFeedback.ALIAS_STATUS%></th>	
					<td>
						${fdFeedback.status}							
					</td>							
				</tr>		
		</table>
	</div>
</div>