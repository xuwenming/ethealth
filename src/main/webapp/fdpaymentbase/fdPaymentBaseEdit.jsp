<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.mobian.model.TfdPaymentBase" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="jb" uri="http://www.jb.cn/jbtag"%> 
<script type="text/javascript">
	$(function() {
		parent.$.messager.progress('close');
		$('#form').form({
			url : '${pageContext.request.contextPath}/fdPaymentBaseController/edit',
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
				<input type="hidden" name="id" value = "${fdPaymentBase.id}"/>
			<table class="table table-hover table-condensed">
				<tr>	
					<th><%=TfdPaymentBase.ALIAS_ORDER_NO%></th>	
					<td>
											<input class="span2" name="orderNo" type="text" value="${fdPaymentBase.orderNo}"/>
					</td>							
					<th><%=TfdPaymentBase.ALIAS_TYPE%></th>	
					<td>
											<input class="span2" name="type" type="text" value="${fdPaymentBase.type}"/>
					</td>							
			</tr>	
				<tr>	
					<th><%=TfdPaymentBase.ALIAS_PRICE%></th>	
					<td>
											<input class="span2" name="price" type="text" value="${fdPaymentBase.price}"/>
					</td>							
					<th><%=TfdPaymentBase.ALIAS_STATUS%></th>	
					<td>
											<input class="span2" name="status" type="text" value="${fdPaymentBase.status}"/>
					</td>							
			</tr>	
				<tr>	
					<th><%=TfdPaymentBase.ALIAS_REMARK%></th>	
					<td>
											<input class="span2" name="remark" type="text" value="${fdPaymentBase.remark}"/>
					</td>							
					<th><%=TfdPaymentBase.ALIAS_EXPIRY_DATE%></th>	
					<td>
					<input class="span2" name="expiryDate" type="text" onclick="WdatePicker({dateFmt:'<%=TfdPaymentBase.FORMAT_EXPIRY_DATE%>'})"   maxlength="0" value="${fdPaymentBase.expiryDate}"/>
					</td>							
			</tr>	
				<tr>	
					<th><%=TfdPaymentBase.ALIAS_CREATE_DATE%></th>	
					<td>
					<input class="span2" name="createDate" type="text" onclick="WdatePicker({dateFmt:'<%=TfdPaymentBase.FORMAT_CREATE_DATE%>'})"   maxlength="0" value="${fdPaymentBase.createDate}"/>
					</td>							
					<th><%=TfdPaymentBase.ALIAS_UPDATE_DATE%></th>	
					<td>
					<input class="span2" name="updateDate" type="text" onclick="WdatePicker({dateFmt:'<%=TfdPaymentBase.FORMAT_UPDATE_DATE%>'})"   maxlength="0" value="${fdPaymentBase.updateDate}"/>
					</td>							
			</tr>	
			</table>				
		</form>
	</div>
</div>