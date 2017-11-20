<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.mobian.model.TfdBalanceLog" %>
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
					<th><%=TfdBalanceLog.ALIAS_ADMIN_ID%></th>	
					<td>
						${fdBalanceLog.adminId}							
					</td>							
					<th><%=TfdBalanceLog.ALIAS_USER_ID%></th>	
					<td>
						${fdBalanceLog.userId}							
					</td>							
				</tr>		
				<tr>	
					<th><%=TfdBalanceLog.ALIAS_BALANCE_NO%></th>	
					<td>
						${fdBalanceLog.balanceNo}							
					</td>							
					<th><%=TfdBalanceLog.ALIAS_REF_ID%></th>	
					<td>
						${fdBalanceLog.refId}							
					</td>							
				</tr>		
				<tr>	
					<th><%=TfdBalanceLog.ALIAS_REF_TYPE%></th>	
					<td>
						${fdBalanceLog.refType}							
					</td>							
					<th><%=TfdBalanceLog.ALIAS_CREATE_TIME%></th>	
					<td>
						${fdBalanceLog.createTime}							
					</td>							
				</tr>		
				<tr>	
					<th><%=TfdBalanceLog.ALIAS_UPDATE_TIME%></th>	
					<td>
						${fdBalanceLog.updateTime}							
					</td>							
					<th><%=TfdBalanceLog.ALIAS_AMOUNT%></th>	
					<td>
						${fdBalanceLog.amount}							
					</td>							
				</tr>		
				<tr>	
					<th><%=TfdBalanceLog.ALIAS_AMOUNT_LOG%></th>	
					<td>
						${fdBalanceLog.amountLog}							
					</td>							
					<th><%=TfdBalanceLog.ALIAS_NOTE%></th>	
					<td>
						${fdBalanceLog.note}							
					</td>							
				</tr>		
				<tr>	
					<th><%=TfdBalanceLog.ALIAS_STATUS%></th>	
					<td>
						${fdBalanceLog.status}							
					</td>							
				</tr>		
		</table>
	</div>
</div>