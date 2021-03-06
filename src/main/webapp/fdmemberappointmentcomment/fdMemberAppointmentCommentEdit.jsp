<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.mobian.model.TfdMemberAppointmentComment" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="jb" uri="http://www.jb.cn/jbtag"%> 
<script type="text/javascript">
	$(function() {
		parent.$.messager.progress('close');
		$('#form').form({
			url : '${pageContext.request.contextPath}/fdMemberAppointmentCommentController/edit',
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
				<input type="hidden" name="id" value = "${fdMemberAppointmentComment.id}"/>
			<table class="table table-hover table-condensed">
				<tr>	
					<th><%=TfdMemberAppointmentComment.ALIAS_DOCTOR_ID%></th>	
					<td>
											<input class="span2" name="doctorId" type="text" value="${fdMemberAppointmentComment.doctorId}"/>
					</td>							
					<th><%=TfdMemberAppointmentComment.ALIAS_APPOINTMENT_ID%></th>	
					<td>
											<input class="span2" name="appointmentId" type="text" value="${fdMemberAppointmentComment.appointmentId}"/>
					</td>							
			</tr>	
				<tr>	
					<th><%=TfdMemberAppointmentComment.ALIAS_USER_ID%></th>	
					<td>
											<input class="span2" name="userId" type="text" value="${fdMemberAppointmentComment.userId}"/>
					</td>							
					<th><%=TfdMemberAppointmentComment.ALIAS_CREATE_TIME%></th>	
					<td>
											<input class="span2" name="createTime" type="text" value="${fdMemberAppointmentComment.createTime}"/>
					</td>							
			</tr>	
				<tr>	
					<th><%=TfdMemberAppointmentComment.ALIAS_UPDATE_TIME%></th>	
					<td>
											<input class="span2" name="updateTime" type="text" value="${fdMemberAppointmentComment.updateTime}"/>
					</td>							
					<th><%=TfdMemberAppointmentComment.ALIAS_STATUS%></th>	
					<td>
											<input class="span2" name="status" type="text" value="${fdMemberAppointmentComment.status}"/>
					</td>							
			</tr>	
				<tr>	
					<th><%=TfdMemberAppointmentComment.ALIAS_EFFECT%></th>	
					<td>
											<input class="span2" name="effect" type="text" value="${fdMemberAppointmentComment.effect}"/>
					</td>							
					<th><%=TfdMemberAppointmentComment.ALIAS_ATTITUDE%></th>	
					<td>
											<input class="span2" name="attitude" type="text" value="${fdMemberAppointmentComment.attitude}"/>
					</td>							
			</tr>	
				<tr>	
					<th><%=TfdMemberAppointmentComment.ALIAS_DISEASE%></th>	
					<td>
											<input class="span2" name="disease" type="text" value="${fdMemberAppointmentComment.disease}"/>
					</td>							
					<th><%=TfdMemberAppointmentComment.ALIAS_OBJECTIVE%></th>	
					<td>
											<jb:select dataType="OT" name="objective" value="${fdMemberAppointmentComment.objective}"></jb:select>	
					</td>							
			</tr>	
				<tr>	
					<th><%=TfdMemberAppointmentComment.ALIAS_OBJECTIVE_OTHER%></th>	
					<td>
											<input class="span2" name="objectiveOther" type="text" value="${fdMemberAppointmentComment.objectiveOther}"/>
					</td>							
					<th><%=TfdMemberAppointmentComment.ALIAS_THERAPY%></th>	
					<td>
											<jb:select dataType="TW" name="therapy" value="${fdMemberAppointmentComment.therapy}"></jb:select>	
					</td>							
			</tr>	
				<tr>	
					<th><%=TfdMemberAppointmentComment.ALIAS_THERAPY_OTHER%></th>	
					<td>
											<input class="span2" name="therapyOther" type="text" value="${fdMemberAppointmentComment.therapyOther}"/>
					</td>							
					<th><%=TfdMemberAppointmentComment.ALIAS_COMMENT%></th>	
					<td>
											<input class="span2" name="comment" type="text" value="${fdMemberAppointmentComment.comment}"/>
					</td>							
			</tr>	
			</table>				
		</form>
	</div>
</div>