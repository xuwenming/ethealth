<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.mobian.model.TfdMessage" %>
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
					<th><%=TfdMessage.ALIAS_TITLE%></th>	
					<td>
						${fdMessage.title}							
					</td>							
					<th><%=TfdMessage.ALIAS_CONTENT%></th>	
					<td>
						${fdMessage.content}							
					</td>							
				</tr>		
				<tr>	
					<th><%=TfdMessage.ALIAS_CREATE_BY%></th>	
					<td>
						${fdMessage.createBy}							
					</td>							
					<th><%=TfdMessage.ALIAS_CREATE_TIME%></th>	
					<td>
						${fdMessage.createTime}							
					</td>							
				</tr>		
				<tr>	
					<th><%=TfdMessage.ALIAS_UPDATE_BY%></th>	
					<td>
						${fdMessage.updateBy}							
					</td>							
					<th><%=TfdMessage.ALIAS_UPDATE_TIME%></th>	
					<td>
						${fdMessage.updateTime}							
					</td>							
				</tr>		
				<tr>	
					<th><%=TfdMessage.ALIAS_STATUS%></th>	
					<td>
						${fdMessage.status}							
					</td>							
					<th><%=TfdMessage.ALIAS_USER_ID%></th>	
					<td>
						${fdMessage.userId}							
					</td>							
				</tr>		
				<tr>	
					<th><%=TfdMessage.ALIAS_MTYPE%></th>	
					<td>
						${fdMessage.mtype}							
					</td>							
					<th><%=TfdMessage.ALIAS_IS_READ%></th>	
					<td>
						${fdMessage.isRead}							
					</td>							
				</tr>		
				<tr>	
					<th><%=TfdMessage.ALIAS_URL%></th>	
					<td>
						${fdMessage.url}							
					</td>							
				</tr>		
		</table>
	</div>
</div>