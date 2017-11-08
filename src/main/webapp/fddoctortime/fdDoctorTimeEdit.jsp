<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.mobian.model.TfdDoctorTime" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="jb" uri="http://www.jb.cn/jbtag"%> 
<script type="text/javascript">
	$(function() {
		parent.$.messager.progress('close');
		$('#form').form({
			url : '${pageContext.request.contextPath}/fdDoctorTimeController/edit',
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
				<input type="hidden" name="id" value = "${fdDoctorTime.id}"/>
			<table class="table table-hover table-condensed">
				<tr>	
					<th><%=TfdDoctorTime.ALIAS_USER_ID%></th>	
					<td>
											<input class="span2" name="userId" type="text" value="${fdDoctorTime.userId}"/>
					</td>							
					<th><%=TfdDoctorTime.ALIAS_WEEK%></th>	
					<td>
											<input class="span2" name="week" type="text" value="${fdDoctorTime.week}"/>
					</td>							
			</tr>	
				<tr>	
					<th><%=TfdDoctorTime.ALIAS_TIME%></th>	
					<td>
											<input class="span2" name="time" type="text" value="${fdDoctorTime.time}"/>
					</td>							
					<th><%=TfdDoctorTime.ALIAS_VISIT_TYPE%></th>	
					<td>
											<input class="span2" name="visitType" type="text" value="${fdDoctorTime.visitType}"/>
					</td>							
			</tr>	
				<tr>	
					<th><%=TfdDoctorTime.ALIAS_ADDRESS%></th>	
					<td>
											<input class="span2" name="address" type="text" value="${fdDoctorTime.address}"/>
					</td>							
					<th><%=TfdDoctorTime.ALIAS_NUMBER%></th>	
					<td>
											<input class="span2" name="number" type="text" value="${fdDoctorTime.number}"/>
					</td>							
			</tr>	
				<tr>	
					<th><%=TfdDoctorTime.ALIAS_CREATE_BY%></th>	
					<td>
											<input class="span2" name="createBy" type="text" value="${fdDoctorTime.createBy}"/>
					</td>							
					<th><%=TfdDoctorTime.ALIAS_CREATE_TIME%></th>	
					<td>
											<input class="span2" name="createTime" type="text" value="${fdDoctorTime.createTime}"/>
					</td>							
			</tr>	
				<tr>	
					<th><%=TfdDoctorTime.ALIAS_UPDATE_BY%></th>	
					<td>
											<input class="span2" name="updateBy" type="text" value="${fdDoctorTime.updateBy}"/>
					</td>							
					<th><%=TfdDoctorTime.ALIAS_UPDATE_TIME%></th>	
					<td>
											<input class="span2" name="updateTime" type="text" value="${fdDoctorTime.updateTime}"/>
					</td>							
			</tr>	
				<tr>	
					<th><%=TfdDoctorTime.ALIAS_STATUS%></th>	
					<td>
											<input class="span2" name="status" type="text" value="${fdDoctorTime.status}"/>
					</td>							
			</tr>	
			</table>				
		</form>
	</div>
</div>