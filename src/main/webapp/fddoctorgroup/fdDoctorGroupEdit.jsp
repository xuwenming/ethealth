<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.mobian.model.TfdDoctorGroup" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="jb" uri="http://www.jb.cn/jbtag"%> 
<script type="text/javascript">
	$(function() {
		parent.$.messager.progress('close');
		$('#form').form({
			url : '${pageContext.request.contextPath}/fdDoctorGroupController/edit',
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
				<input type="hidden" name="id" value = "${fdDoctorGroup.id}"/>
			<table class="table table-hover table-condensed">
				<tr>	
					<th><%=TfdDoctorGroup.ALIAS_HOSPITAL_ID%></th>	
					<td>
											<input class="span2" name="hospitalId" type="text" value="${fdDoctorGroup.hospitalId}"/>
					</td>							
					<th><%=TfdDoctorGroup.ALIAS_DEPT_ID%></th>	
					<td>
											<input class="span2" name="deptId" type="text" value="${fdDoctorGroup.deptId}"/>
					</td>							
			</tr>	
				<tr>	
					<th><%=TfdDoctorGroup.ALIAS_GROUP_NAME%></th>	
					<td>
											<input class="span2" name="groupName" type="text" value="${fdDoctorGroup.groupName}"/>
					</td>							
					<th><%=TfdDoctorGroup.ALIAS_INTRODUCE%></th>	
					<td>
											<input class="span2" name="introduce" type="text" value="${fdDoctorGroup.introduce}"/>
					</td>							
			</tr>	
				<tr>	
					<th><%=TfdDoctorGroup.ALIAS_PICS%></th>	
					<td>
											<input class="span2" name="pics" type="text" value="${fdDoctorGroup.pics}"/>
					</td>							
					<th><%=TfdDoctorGroup.ALIAS_CREATE_BY%></th>	
					<td>
											<input class="span2" name="createBy" type="text" value="${fdDoctorGroup.createBy}"/>
					</td>							
			</tr>	
				<tr>	
					<th><%=TfdDoctorGroup.ALIAS_CREATE_TIME%></th>	
					<td>
											<input class="span2" name="createTime" type="text" value="${fdDoctorGroup.createTime}"/>
					</td>							
					<th><%=TfdDoctorGroup.ALIAS_UPDATE_BY%></th>	
					<td>
											<input class="span2" name="updateBy" type="text" value="${fdDoctorGroup.updateBy}"/>
					</td>							
			</tr>	
				<tr>	
					<th><%=TfdDoctorGroup.ALIAS_UPDATE_TIME%></th>	
					<td>
											<input class="span2" name="updateTime" type="text" value="${fdDoctorGroup.updateTime}"/>
					</td>							
					<th><%=TfdDoctorGroup.ALIAS_STATUS%></th>	
					<td>
											<input class="span2" name="status" type="text" value="${fdDoctorGroup.status}"/>
					</td>							
			</tr>	
				<tr>	
					<th><%=TfdDoctorGroup.ALIAS_LEADER%></th>	
					<td>
											<input class="span2" name="leader" type="text" value="${fdDoctorGroup.leader}"/>
					</td>							
			</tr>	
			</table>				
		</form>
	</div>
</div>