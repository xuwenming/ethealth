<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.mobian.model.TfdMemberDoctorLevel" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="jb" uri="http://www.jb.cn/jbtag"%> 
<script type="text/javascript">
	$(function() {
		parent.$.messager.progress('close');
		$('#form').form({
			url : '${pageContext.request.contextPath}/fdMemberDoctorLevelController/edit',
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
				<input type="hidden" name="id" value = "${fdMemberDoctorLevel.id}"/>
			<table class="table table-hover table-condensed">
				<tr>
					<th><%=TfdMemberDoctorLevel.ALIAS_NAME%></th>
					<td colspan="3">
						<input value="${fdMemberDoctorLevel.name}" class="easyui-validatebox span2" data-options="required:true" maxlength="100" name="name" type="text" style="width: 380px;"/>
					</td>
				</tr>
				<tr>
					<th width="12%">预约费用</th>
					<td width="33%">
						<input name="appointmentCost" value="${fdMemberDoctorLevel.appointmentCost}"
							   class="easyui-numberspinner" style="width: 140px; height: 29px;"
							   required="required" data-options="editable:true" />
					</td>
					<th width="10%">排序</th>
					<td width="45%">
						<input name="seq" value="${fdMemberDoctorLevel.seq}"
							   class="easyui-numberspinner" style="width: 140px; height: 29px;"
							   required="required" data-options="editable:true" />
						<font color="red">*数值越小越靠前</font>
					</td>
				</tr>
			</table>				
		</form>
	</div>
</div>