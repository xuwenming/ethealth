<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.mobian.model.TfdCashLog" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="jb" uri="http://www.jb.cn/jbtag"%> 
<script type="text/javascript">
	$(function() {
		parent.$.messager.progress('close');
		$('#form').form({
			url : '${pageContext.request.contextPath}/fdCashLogController/edit',
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
				<input type="hidden" name="id" value = "${fdCashLog.id}"/>
			<table class="table table-hover table-condensed">
				<tr>	
					<th><%=TfdCashLog.ALIAS_STATUS%></th>	
					<td>
											<input class="span2" name="status" type="text" value="${fdCashLog.status}"/>
					</td>							
					<th><%=TfdCashLog.ALIAS_CREATE_TIME%></th>	
					<td>
											<input class="span2" name="createTime" type="text" value="${fdCashLog.createTime}"/>
					</td>							
			</tr>	
				<tr>	
					<th><%=TfdCashLog.ALIAS_UPDATE_TIME%></th>	
					<td>
											<input class="span2" name="updateTime" type="text" value="${fdCashLog.updateTime}"/>
					</td>							
					<th><%=TfdCashLog.ALIAS_HANDLE_STATUS%></th>	
					<td>
											<jb:select dataType="HS" name="handleStatus" value="${fdCashLog.handleStatus}"></jb:select>	
					</td>							
			</tr>	
				<tr>	
					<th><%=TfdCashLog.ALIAS_HANDLE_LOGIN_ID%></th>	
					<td>
											<input class="span2" name="handleLoginId" type="text" value="${fdCashLog.handleLoginId}"/>
					</td>							
					<th><%=TfdCashLog.ALIAS_HANDLE_REMARK%></th>	
					<td>
											<input class="span2" name="handleRemark" type="text" value="${fdCashLog.handleRemark}"/>
					</td>							
			</tr>	
				<tr>	
					<th><%=TfdCashLog.ALIAS_HANDLE_TIME%></th>	
					<td>
					<input class="span2" name="handleTime" type="text" onclick="WdatePicker({dateFmt:'<%=TfdCashLog.FORMAT_HANDLE_TIME%>'})"   maxlength="0" value="${fdCashLog.handleTime}"/>
					</td>							
					<th><%=TfdCashLog.ALIAS_USER_ID%></th>	
					<td>
											<input class="span2" name="userId" type="text" class="easyui-validatebox span2" data-options="required:true" value="${fdCashLog.userId}"/>
					</td>							
			</tr>	
				<tr>	
					<th><%=TfdCashLog.ALIAS_AMOUNT%></th>	
					<td>
											<input class="span2" name="amount" type="text" class="easyui-validatebox span2" data-options="required:true" value="${fdCashLog.amount}"/>
					</td>							
					<th><%=TfdCashLog.ALIAS_REF_TYPE%></th>	
					<td>
											<input class="span2" name="refType" type="text" class="easyui-validatebox span2" data-options="required:true" value="${fdCashLog.refType}"/>
					</td>							
			</tr>	
				<tr>	
					<th><%=TfdCashLog.ALIAS_CONTENT%></th>	
					<td>
											<input class="span2" name="content" type="text" value="${fdCashLog.content}"/>
					</td>							
					<th><%=TfdCashLog.ALIAS_BANK_ACCOUNT%></th>	
					<td>
											<input class="span2" name="bankAccount" type="text" value="${fdCashLog.bankAccount}"/>
					</td>							
			</tr>	
				<tr>	
					<th><%=TfdCashLog.ALIAS_BANK_PHONE%></th>	
					<td>
											<input class="span2" name="bankPhone" type="text" value="${fdCashLog.bankPhone}"/>
					</td>							
					<th><%=TfdCashLog.ALIAS_BANK_ID_NO%></th>	
					<td>
											<input class="span2" name="bankIdNo" type="text" value="${fdCashLog.bankIdNo}"/>
					</td>							
			</tr>	
				<tr>	
					<th><%=TfdCashLog.ALIAS_BANK_NAME%></th>	
					<td>
											<input class="span2" name="bankName" type="text" value="${fdCashLog.bankName}"/>
					</td>							
					<th><%=TfdCashLog.ALIAS_BANK_CARD%></th>	
					<td>
											<input class="span2" name="bankCard" type="text" value="${fdCashLog.bankCard}"/>
					</td>							
			</tr>	
				<tr>	
					<th><%=TfdCashLog.ALIAS_ALIPAY%></th>	
					<td>
											<input class="span2" name="alipay" type="text" value="${fdCashLog.alipay}"/>
					</td>							
			</tr>	
			</table>				
		</form>
	</div>
</div>