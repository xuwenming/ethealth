<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.mobian.model.TfdMemberDoctor" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="jb" uri="http://www.jb.cn/jbtag"%> 
<script type="text/javascript">
	$(function() {
		parent.$.messager.progress('close');
		$('#form').form({
			url : '${pageContext.request.contextPath}/fdMemberDoctorController/edit',
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
			<input type="hidden" name="id" value = "${fdMemberDoctor.id}"/>
			<table class="table table-hover table-condensed">
				<tr>
					<th width="12%">所属团队</th>
					<td width="33%">
						<jb:selectSql dataType="SQL001" name="groupId" value="${fdMemberDoctor.groupId}"></jb:selectSql>
					</td>
					<th width="10%">学历</th>
					<td width="45%">
						<jb:select dataType="ED" name="education" value="${fdMemberDoctor.education}"></jb:select>
					</td>							
				</tr>
				<tr>
					<th>著名专家</th>
					<td>
						<select name="isBest" class="easyui-combobox"
								data-options="width:140,height:29,editable:false,panelHeight:'auto'">
							<option value="1" <c:if test="${fdMemberDoctor.isBest}">selected</c:if>>是</option>
							<option value="0" <c:if test="${!fdMemberDoctor.isBest}">selected</c:if>>否</option>
						</select>
					</td>
					<th>排序</th>
					<td>
						<input name="seq" value="${fdMemberDoctor.seq}"
							   class="easyui-numberspinner" style="width: 140px; height: 29px;"
							   required="required" data-options="editable:true" />
						<font color="red">*数值越小越靠前</font>
					</td>
				</tr>
			</table>				
		</form>
	</div>
</div>