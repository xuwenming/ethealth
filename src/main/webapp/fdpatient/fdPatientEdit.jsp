<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.mobian.model.TfdPatient" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="jb" uri="http://www.jb.cn/jbtag"%> 
<script type="text/javascript">
	$(function() {
		parent.$.messager.progress('close');
		$('#form').form({
			url : '${pageContext.request.contextPath}/fdPatientController/edit',
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
				<input type="hidden" name="userId" value = "${fdPatient.userId}"/>
			<table class="table table-hover table-condensed">
				<tr>	
					<th><%=TfdPatient.ALIAS_CREATE_TIME%></th>	
					<td>
											<input class="span2" name="createTime" type="text" value="${fdPatient.createTime}"/>
					</td>							
					<th><%=TfdPatient.ALIAS_UPDATE_TIME%></th>	
					<td>
											<input class="span2" name="updateTime" type="text" value="${fdPatient.updateTime}"/>
					</td>							
			</tr>	
				<tr>	
					<th><%=TfdPatient.ALIAS_STATUS%></th>	
					<td>
											<input class="span2" name="status" type="text" value="${fdPatient.status}"/>
					</td>							
					<th><%=TfdPatient.ALIAS_REAL_NAME%></th>	
					<td>
											<input class="span2" name="realName" type="text" value="${fdPatient.realName}"/>
					</td>							
			</tr>	
				<tr>	
					<th><%=TfdPatient.ALIAS_SEX%></th>	
					<td>
											<input class="span2" name="sex" type="text" value="${fdPatient.sex}"/>
					</td>							
					<th><%=TfdPatient.ALIAS_BIRTHDAY%></th>	
					<td>
											<input class="span2" name="birthday" type="text" value="${fdPatient.birthday}"/>
					</td>							
			</tr>	
				<tr>	
					<th><%=TfdPatient.ALIAS_RELATION%></th>	
					<td>
											<jb:select dataType="PR" name="relation" value="${fdPatient.relation}"></jb:select>	
					</td>							
			</tr>	
			</table>				
		</form>
	</div>
</div>