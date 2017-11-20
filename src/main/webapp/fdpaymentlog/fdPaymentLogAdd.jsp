<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.mobian.model.TfdPaymentLog" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="jb" uri="http://www.jb.cn/jbtag"%>  
<script type="text/javascript">
	$(function() {
	 parent.$.messager.progress('close');
		$('#form').form({
			url : '${pageContext.request.contextPath}/fdPaymentLogController/add',
			onSubmit : function() {
				parent.$.messager.progress({
					title : '提示',
					text : '数据处理中，请稍后....'
				});
				var isValid = $(this).form('validate');
				if (!isValid) {
					parent.$.messager.progress('close');
				}
				return isValid;
			},
			success : function(result) {
				parent.$.messager.progress('close');
				result = $.parseJSON(result);
				if (result.success) {
					parent.$.modalDialog.openner_dataGrid.datagrid('reload');//之所以能在这里调用到parent.$.modalDialog.openner_dataGrid这个对象，是因为user.jsp页面预定义好了
					parent.$.modalDialog.handler.dialog('close');
				} else {
					parent.$.messager.alert('错误', result.msg, 'error');
				}
			}
		});
	});
</script>
<div class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'center',border:false" title="" style="overflow: hidden;">	
		<form id="form" method="post">		
				<input type="hidden" name="id"/>
			<table class="table table-hover table-condensed">
				<tr>	
					<th><%=TfdPaymentLog.ALIAS_PAYMENT_ID%></th>	
					<td>
											<input class="span2" name="paymentId" type="text"/>
					</td>							
					<th><%=TfdPaymentLog.ALIAS_TYPE%></th>	
					<td>
											<input class="span2" name="type" type="text"/>
					</td>							
				</tr>	
				<tr>	
					<th><%=TfdPaymentLog.ALIAS_STATUS%></th>	
					<td>
											<input class="span2" name="status" type="text"/>
					</td>							
					<th><%=TfdPaymentLog.ALIAS_TOTAL_FEE%></th>	
					<td>
											<input class="span2" name="totalFee" type="text"/>
					</td>							
				</tr>	
				<tr>	
					<th><%=TfdPaymentLog.ALIAS_REF_ID%></th>	
					<td>
											<input class="span2" name="refId" type="text"/>
					</td>							
					<th><%=TfdPaymentLog.ALIAS_REFUND_NO%></th>	
					<td>
											<input class="span2" name="refundNo" type="text"/>
					</td>							
				</tr>	
				<tr>	
					<th><%=TfdPaymentLog.ALIAS_REF_REFUND_NO%></th>	
					<td>
											<input class="span2" name="refRefundNo" type="text"/>
					</td>							
					<th><%=TfdPaymentLog.ALIAS_REFUND_FEE%></th>	
					<td>
											<input class="span2" name="refundFee" type="text"/>
					</td>							
				</tr>	
				<tr>	
					<th><%=TfdPaymentLog.ALIAS_REFUND_DATE%></th>	
					<td>
					<input class="span2" name="refundDate" type="text" onclick="WdatePicker({dateFmt:'<%=TfdPaymentLog.FORMAT_REFUND_DATE%>'})"  maxlength="0" class="" />
					</td>							
					<th><%=TfdPaymentLog.ALIAS_CREATE_DATE%></th>	
					<td>
					<input class="span2" name="createDate" type="text" onclick="WdatePicker({dateFmt:'<%=TfdPaymentLog.FORMAT_CREATE_DATE%>'})"  maxlength="0" class="" />
					</td>							
				</tr>	
				<tr>	
					<th><%=TfdPaymentLog.ALIAS_REMARK%></th>	
					<td>
											<input class="span2" name="remark" type="text"/>
					</td>							
				</tr>	
			</table>		
		</form>
	</div>
</div>