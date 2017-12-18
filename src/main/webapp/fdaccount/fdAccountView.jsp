<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.mobian.model.TfdAccount" %>
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
					<th><%=TfdAccount.ALIAS_BANK_ACCOUNT%></th>	
					<td>
						${fdAccount.bankAccount}							
					</td>							
					<th><%=TfdAccount.ALIAS_BANK_PHONE%></th>	
					<td>
						${fdAccount.bankPhone}							
					</td>							
				</tr>		
				<tr>	
					<th><%=TfdAccount.ALIAS_BANK_ID_NO%></th>	
					<td>
						${fdAccount.bankIdNo}							
					</td>							
					<th><%=TfdAccount.ALIAS_BANK_NAME%></th>	
					<td>
						${fdAccount.bankName}							
					</td>							
				</tr>		
				<tr>	
					<th><%=TfdAccount.ALIAS_BANK_CARD%></th>	
					<td>
						${fdAccount.bankCard}							
					</td>							
					<th><%=TfdAccount.ALIAS_ALIPAY%></th>	
					<td>
						${fdAccount.alipay}							
					</td>							
				</tr>		
		</table>
	</div>
</div>