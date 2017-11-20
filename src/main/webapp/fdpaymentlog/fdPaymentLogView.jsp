<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.mobian.model.TfdPaymentLog" %>
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
					<th><%=TfdPaymentLog.ALIAS_PAYMENT_ID%></th>	
					<td>
						${fdPaymentLog.paymentId}							
					</td>							
					<th><%=TfdPaymentLog.ALIAS_TYPE%></th>	
					<td>
						${fdPaymentLog.type}							
					</td>							
				</tr>		
				<tr>	
					<th><%=TfdPaymentLog.ALIAS_STATUS%></th>	
					<td>
						${fdPaymentLog.status}							
					</td>							
					<th><%=TfdPaymentLog.ALIAS_TOTAL_FEE%></th>	
					<td>
						${fdPaymentLog.totalFee}							
					</td>							
				</tr>		
				<tr>	
					<th><%=TfdPaymentLog.ALIAS_REF_ID%></th>	
					<td>
						${fdPaymentLog.refId}							
					</td>							
					<th><%=TfdPaymentLog.ALIAS_REFUND_NO%></th>	
					<td>
						${fdPaymentLog.refundNo}							
					</td>							
				</tr>		
				<tr>	
					<th><%=TfdPaymentLog.ALIAS_REF_REFUND_NO%></th>	
					<td>
						${fdPaymentLog.refRefundNo}							
					</td>							
					<th><%=TfdPaymentLog.ALIAS_REFUND_FEE%></th>	
					<td>
						${fdPaymentLog.refundFee}							
					</td>							
				</tr>		
				<tr>	
					<th><%=TfdPaymentLog.ALIAS_REFUND_DATE%></th>	
					<td>
						${fdPaymentLog.refundDate}							
					</td>							
					<th><%=TfdPaymentLog.ALIAS_CREATE_DATE%></th>	
					<td>
						${fdPaymentLog.createDate}							
					</td>							
				</tr>		
				<tr>	
					<th><%=TfdPaymentLog.ALIAS_REMARK%></th>	
					<td>
						${fdPaymentLog.remark}							
					</td>							
				</tr>		
		</table>
	</div>
</div>