<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.mobian.model.TfdPaymentBase" %>
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
					<th><%=TfdPaymentBase.ALIAS_ORDER_NO%></th>	
					<td>
						${fdPaymentBase.orderNo}							
					</td>							
					<th><%=TfdPaymentBase.ALIAS_TYPE%></th>	
					<td>
						${fdPaymentBase.type}							
					</td>							
				</tr>		
				<tr>	
					<th><%=TfdPaymentBase.ALIAS_PRICE%></th>	
					<td>
						${fdPaymentBase.price}							
					</td>							
					<th><%=TfdPaymentBase.ALIAS_STATUS%></th>	
					<td>
						${fdPaymentBase.status}							
					</td>							
				</tr>		
				<tr>	
					<th><%=TfdPaymentBase.ALIAS_REMARK%></th>	
					<td>
						${fdPaymentBase.remark}							
					</td>							
					<th><%=TfdPaymentBase.ALIAS_EXPIRY_DATE%></th>	
					<td>
						${fdPaymentBase.expiryDate}							
					</td>							
				</tr>		
				<tr>	
					<th><%=TfdPaymentBase.ALIAS_CREATE_DATE%></th>	
					<td>
						${fdPaymentBase.createDate}							
					</td>							
					<th><%=TfdPaymentBase.ALIAS_UPDATE_DATE%></th>	
					<td>
						${fdPaymentBase.updateDate}							
					</td>							
				</tr>		
		</table>
	</div>
</div>