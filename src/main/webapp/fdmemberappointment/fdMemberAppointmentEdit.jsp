<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.mobian.model.TfdMemberAppointment" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="jb" uri="http://www.jb.cn/jbtag"%> 
<script type="text/javascript">
	$(function() {
		parent.$.messager.progress('close');
		$('#form').form({
			url : '${pageContext.request.contextPath}/fdMemberAppointmentController/edit',
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
				<input type="hidden" name="id" value = "${fdMemberAppointment.id}"/>
			<table class="table table-hover table-condensed">
				<tr>	
					<th><%=TfdMemberAppointment.ALIAS_TIME%></th>	
					<td>
											<input class="span2" name="time" type="text" value="${fdMemberAppointment.time}"/>
					</td>							
					<th><%=TfdMemberAppointment.ALIAS_USER_ID%></th>	
					<td>
											<input class="span2" name="userId" type="text" value="${fdMemberAppointment.userId}"/>
					</td>							
			</tr>	
				<tr>	
					<th><%=TfdMemberAppointment.ALIAS_DOCTOR_ID%></th>	
					<td>
											<input class="span2" name="doctorId" type="text" value="${fdMemberAppointment.doctorId}"/>
					</td>							
					<th><%=TfdMemberAppointment.ALIAS_APPOINT_TIME%></th>	
					<td>
											<input class="span2" name="appointTime" type="text" value="${fdMemberAppointment.appointTime}"/>
					</td>							
			</tr>	
				<tr>	
					<th><%=TfdMemberAppointment.ALIAS_SECOND_TIME%></th>	
					<td>
											<input class="span2" name="secondTime" type="text" value="${fdMemberAppointment.secondTime}"/>
					</td>							
					<th><%=TfdMemberAppointment.ALIAS_APPOINT_NAME%></th>	
					<td>
											<input class="span2" name="appointName" type="text" value="${fdMemberAppointment.appointName}"/>
					</td>							
			</tr>	
				<tr>	
					<th><%=TfdMemberAppointment.ALIAS_LINK_NAME%></th>	
					<td>
											<input class="span2" name="linkName" type="text" value="${fdMemberAppointment.linkName}"/>
					</td>							
					<th><%=TfdMemberAppointment.ALIAS_LINK_WAY%></th>	
					<td>
											<input class="span2" name="linkWay" type="text" value="${fdMemberAppointment.linkWay}"/>
					</td>							
			</tr>	
				<tr>	
					<th><%=TfdMemberAppointment.ALIAS_APPOINT_MESSAGE%></th>	
					<td>
											<input class="span2" name="appointMessage" type="text" value="${fdMemberAppointment.appointMessage}"/>
					</td>							
					<th><%=TfdMemberAppointment.ALIAS_CREATE_BY%></th>	
					<td>
											<input class="span2" name="createBy" type="text" value="${fdMemberAppointment.createBy}"/>
					</td>							
			</tr>	
				<tr>	
					<th><%=TfdMemberAppointment.ALIAS_CREATE_TIME%></th>	
					<td>
											<input class="span2" name="createTime" type="text" value="${fdMemberAppointment.createTime}"/>
					</td>							
					<th><%=TfdMemberAppointment.ALIAS_UPDATE_BY%></th>	
					<td>
											<input class="span2" name="updateBy" type="text" value="${fdMemberAppointment.updateBy}"/>
					</td>							
			</tr>	
				<tr>	
					<th><%=TfdMemberAppointment.ALIAS_UPDATE_TIME%></th>	
					<td>
											<input class="span2" name="updateTime" type="text" value="${fdMemberAppointment.updateTime}"/>
					</td>							
					<th><%=TfdMemberAppointment.ALIAS_STATUS%></th>	
					<td>
											<input class="span2" name="status" type="text" value="${fdMemberAppointment.status}"/>
					</td>							
			</tr>	
				<tr>	
					<th><%=TfdMemberAppointment.ALIAS_COUPON_ID%></th>	
					<td>
											<input class="span2" name="couponId" type="text" value="${fdMemberAppointment.couponId}"/>
					</td>							
					<th><%=TfdMemberAppointment.ALIAS_PICS%></th>	
					<td>
											<input class="span2" name="pics" type="text" value="${fdMemberAppointment.pics}"/>
					</td>							
			</tr>	
				<tr>	
					<th><%=TfdMemberAppointment.ALIAS_APPOINT_STATUS%></th>	
					<td>
											<input class="span2" name="appointStatus" type="text" value="${fdMemberAppointment.appointStatus}"/>
					</td>							
					<th><%=TfdMemberAppointment.ALIAS_ADJUSTMENT%></th>	
					<td>
											<input class="span2" name="adjustment" type="text" value="${fdMemberAppointment.adjustment}"/>
					</td>							
			</tr>	
				<tr>	
					<th><%=TfdMemberAppointment.ALIAS_REFUSE_REASON%></th>	
					<td>
											<input class="span2" name="refuseReason" type="text" value="${fdMemberAppointment.refuseReason}"/>
					</td>							
					<th><%=TfdMemberAppointment.ALIAS_CONFIRM_TIME%></th>	
					<td>
											<input class="span2" name="confirmTime" type="text" value="${fdMemberAppointment.confirmTime}"/>
					</td>							
			</tr>	
				<tr>	
					<th><%=TfdMemberAppointment.ALIAS_FIRST_CONFIRM%></th>	
					<td>
											<input class="span2" name="firstConfirm" type="text" value="${fdMemberAppointment.firstConfirm}"/>
					</td>							
					<th><%=TfdMemberAppointment.ALIAS_FILE%></th>	
					<td>
											<input class="span2" name="file" type="text" value="${fdMemberAppointment.file}"/>
					</td>							
			</tr>	
			</table>				
		</form>
	</div>
</div>