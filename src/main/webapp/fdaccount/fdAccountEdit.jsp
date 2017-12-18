<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.mobian.model.TfdAccount" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="jb" uri="http://www.jb.cn/jbtag"%> 
<script type="text/javascript">
	$(function() {
		parent.$.messager.progress('close');
		$('#form').form({
			url : '${pageContext.request.contextPath}/fdAccountController/edit',
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
				<input type="hidden" name="userId" value = "${fdAccount.userId}"/>
			<table class="table table-hover table-condensed">
				<tr>	
					<th><%=TfdAccount.ALIAS_BANK_ACCOUNT%></th>	
					<td>
											<input class="span2" name="bankAccount" type="text" value="${fdAccount.bankAccount}"/>
					</td>							
					<th><%=TfdAccount.ALIAS_BANK_PHONE%></th>	
					<td>
											<input class="span2" name="bankPhone" type="text" value="${fdAccount.bankPhone}"/>
					</td>							
			</tr>	
				<tr>	
					<th><%=TfdAccount.ALIAS_BANK_ID_NO%></th>	
					<td>
											<input class="span2" name="bankIdNo" type="text" value="${fdAccount.bankIdNo}"/>
					</td>							
					<th><%=TfdAccount.ALIAS_BANK_NAME%></th>	
					<td>
											<input class="span2" name="bankName" type="text" value="${fdAccount.bankName}"/>
					</td>							
			</tr>	
				<tr>	
					<th><%=TfdAccount.ALIAS_BANK_CARD%></th>	
					<td>
											<input class="span2" name="bankCard" type="text" value="${fdAccount.bankCard}"/>
					</td>							
					<th><%=TfdAccount.ALIAS_ALIPAY%></th>	
					<td>
											<input class="span2" name="alipay" type="text" value="${fdAccount.alipay}"/>
					</td>							
			</tr>	
			</table>				
		</form>
	</div>
</div>