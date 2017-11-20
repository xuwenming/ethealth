<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.mobian.model.TfdBalanceLog" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="jb" uri="http://www.jb.cn/jbtag"%> 
<script type="text/javascript">
	$(function() {
		parent.$.messager.progress('close');
		$('#form').form({
			url : '${pageContext.request.contextPath}/fdBalanceLogController/edit',
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
				<input type="hidden" name="id" value = "${fdBalanceLog.id}"/>
			<table class="table table-hover table-condensed">
				<tr>	
					<th><%=TfdBalanceLog.ALIAS_ADMIN_ID%></th>	
					<td>
											<input class="span2" name="adminId" type="text" value="${fdBalanceLog.adminId}"/>
					</td>							
					<th><%=TfdBalanceLog.ALIAS_USER_ID%></th>	
					<td>
											<input class="span2" name="userId" type="text" class="easyui-validatebox span2" data-options="required:true" value="${fdBalanceLog.userId}"/>
					</td>							
			</tr>	
				<tr>	
					<th><%=TfdBalanceLog.ALIAS_BALANCE_NO%></th>	
					<td>
											<input class="span2" name="balanceNo" type="text" value="${fdBalanceLog.balanceNo}"/>
					</td>							
					<th><%=TfdBalanceLog.ALIAS_REF_ID%></th>	
					<td>
											<input class="span2" name="refId" type="text" value="${fdBalanceLog.refId}"/>
					</td>							
			</tr>	
				<tr>	
					<th><%=TfdBalanceLog.ALIAS_REF_TYPE%></th>	
					<td>
											<input class="span2" name="refType" type="text" class="easyui-validatebox span2" data-options="required:true" value="${fdBalanceLog.refType}"/>
					</td>							
					<th><%=TfdBalanceLog.ALIAS_CREATE_TIME%></th>	
					<td>
											<input class="span2" name="createTime" type="text" value="${fdBalanceLog.createTime}"/>
					</td>							
			</tr>	
				<tr>	
					<th><%=TfdBalanceLog.ALIAS_UPDATE_TIME%></th>	
					<td>
											<input class="span2" name="updateTime" type="text" value="${fdBalanceLog.updateTime}"/>
					</td>							
					<th><%=TfdBalanceLog.ALIAS_AMOUNT%></th>	
					<td>
											<input class="span2" name="amount" type="text" class="easyui-validatebox span2" data-options="required:true" value="${fdBalanceLog.amount}"/>
					</td>							
			</tr>	
				<tr>	
					<th><%=TfdBalanceLog.ALIAS_AMOUNT_LOG%></th>	
					<td>
											<input class="span2" name="amountLog" type="text" class="easyui-validatebox span2" data-options="required:true" value="${fdBalanceLog.amountLog}"/>
					</td>							
					<th><%=TfdBalanceLog.ALIAS_NOTE%></th>	
					<td>
											<input class="span2" name="note" type="text" value="${fdBalanceLog.note}"/>
					</td>							
			</tr>	
				<tr>	
					<th><%=TfdBalanceLog.ALIAS_STATUS%></th>	
					<td>
											<input class="span2" name="status" type="text" value="${fdBalanceLog.status}"/>
					</td>							
			</tr>	
			</table>				
		</form>
	</div>
</div>