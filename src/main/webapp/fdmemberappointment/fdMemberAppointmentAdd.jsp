<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.mobian.model.TfdMemberAppointment" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="jb" uri="http://www.jb.cn/jbtag"%>  
<script type="text/javascript">
	$(function() {
	 parent.$.messager.progress('close');
		$('#form').form({
			url : '${pageContext.request.contextPath}/fdMemberAppointmentController/add',
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
				<input type="hidden" name="id"/>
			<table class="table table-hover table-condensed">
				<tr>	
					<th><%=TfdMemberAppointment.ALIAS_TIME%></th>	
					<td>
											<input class="span2" name="time" type="text"/>
					</td>							
					<th><%=TfdMemberAppointment.ALIAS_USER_ID%></th>	
					<td>
											<input class="span2" name="userId" type="text"/>
					</td>							
				</tr>	
				<tr>	
					<th><%=TfdMemberAppointment.ALIAS_DOCTOR_ID%></th>	
					<td>
											<input class="span2" name="doctorId" type="text"/>
					</td>							
					<th><%=TfdMemberAppointment.ALIAS_APPOINT_TIME%></th>	
					<td>
											<input class="span2" name="appointTime" type="text"/>
					</td>							
				</tr>	
				<tr>	
					<th><%=TfdMemberAppointment.ALIAS_SECOND_TIME%></th>	
					<td>
											<input class="span2" name="secondTime" type="text"/>
					</td>							
					<th><%=TfdMemberAppointment.ALIAS_APPOINT_NAME%></th>	
					<td>
											<input class="span2" name="appointName" type="text"/>
					</td>							
				</tr>	
				<tr>	
					<th><%=TfdMemberAppointment.ALIAS_LINK_NAME%></th>	
					<td>
											<input class="span2" name="linkName" type="text"/>
					</td>							
					<th><%=TfdMemberAppointment.ALIAS_LINK_WAY%></th>	
					<td>
											<input class="span2" name="linkWay" type="text"/>
					</td>							
				</tr>	
				<tr>	
					<th><%=TfdMemberAppointment.ALIAS_APPOINT_MESSAGE%></th>	
					<td>
											<input class="span2" name="appointMessage" type="text"/>
					</td>							
					<th><%=TfdMemberAppointment.ALIAS_CREATE_BY%></th>	
					<td>
											<input class="span2" name="createBy" type="text"/>
					</td>							
				</tr>	
				<tr>	
					<th><%=TfdMemberAppointment.ALIAS_CREATE_TIME%></th>	
					<td>
											<input class="span2" name="createTime" type="text"/>
					</td>							
					<th><%=TfdMemberAppointment.ALIAS_UPDATE_BY%></th>	
					<td>
											<input class="span2" name="updateBy" type="text"/>
					</td>							
				</tr>	
				<tr>	
					<th><%=TfdMemberAppointment.ALIAS_UPDATE_TIME%></th>	
					<td>
											<input class="span2" name="updateTime" type="text"/>
					</td>							
					<th><%=TfdMemberAppointment.ALIAS_STATUS%></th>	
					<td>
											<input class="span2" name="status" type="text"/>
					</td>							
				</tr>	
				<tr>	
					<th><%=TfdMemberAppointment.ALIAS_COUPON_ID%></th>	
					<td>
											<input class="span2" name="couponId" type="text"/>
					</td>							
					<th><%=TfdMemberAppointment.ALIAS_PICS%></th>	
					<td>
											<input class="span2" name="pics" type="text"/>
					</td>							
				</tr>	
				<tr>	
					<th><%=TfdMemberAppointment.ALIAS_APPOINT_STATUS%></th>	
					<td>
											<input class="span2" name="appointStatus" type="text"/>
					</td>							
					<th><%=TfdMemberAppointment.ALIAS_ADJUSTMENT%></th>	
					<td>
											<input class="span2" name="adjustment" type="text"/>
					</td>							
				</tr>	
				<tr>	
					<th><%=TfdMemberAppointment.ALIAS_REFUSE_REASON%></th>	
					<td>
											<input class="span2" name="refuseReason" type="text"/>
					</td>							
					<th><%=TfdMemberAppointment.ALIAS_CONFIRM_TIME%></th>	
					<td>
											<input class="span2" name="confirmTime" type="text"/>
					</td>							
				</tr>	
				<tr>	
					<th><%=TfdMemberAppointment.ALIAS_FIRST_CONFIRM%></th>	
					<td>
											<input class="span2" name="firstConfirm" type="text"/>
					</td>							
					<th><%=TfdMemberAppointment.ALIAS_FILE%></th>	
					<td>
											<input class="span2" name="file" type="text"/>
					</td>							
				</tr>	
			</table>		
		</form>
	</div>
</div>