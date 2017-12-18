<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.mobian.model.TfdMessageReadLog" %>
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
					<th><%=TfdMessageReadLog.ALIAS_CREATE_TIME%></th>	
					<td>
						${fdMessageReadLog.createTime}							
					</td>							
					<th><%=TfdMessageReadLog.ALIAS_UPDATE_TIME%></th>	
					<td>
						${fdMessageReadLog.updateTime}							
					</td>							
				</tr>		
				<tr>	
					<th><%=TfdMessageReadLog.ALIAS_STATUS%></th>	
					<td>
						${fdMessageReadLog.status}							
					</td>							
					<th><%=TfdMessageReadLog.ALIAS_USER_ID%></th>	
					<td>
						${fdMessageReadLog.userId}							
					</td>							
				</tr>		
				<tr>	
					<th><%=TfdMessageReadLog.ALIAS_MESSAGE_ID%></th>	
					<td>
						${fdMessageReadLog.messageId}							
					</td>							
				</tr>		
		</table>
	</div>
</div>