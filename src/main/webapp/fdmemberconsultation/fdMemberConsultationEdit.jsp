<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.mobian.model.TfdMemberConsultation" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="jb" uri="http://www.jb.cn/jbtag"%> 
<script type="text/javascript">
	$(function() {
		parent.$.messager.progress('close');
		$('#form').form({
			url : '${pageContext.request.contextPath}/fdMemberConsultationController/edit',
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
				<input type="hidden" name="id" value = "${fdMemberConsultation.id}"/>
			<table class="table table-hover table-condensed">
				<tr>	
					<th><%=TfdMemberConsultation.ALIAS_USER_ID%></th>	
					<td>
											<input class="span2" name="userId" type="text" value="${fdMemberConsultation.userId}"/>
					</td>							
					<th><%=TfdMemberConsultation.ALIAS_DOCTOR_ID%></th>	
					<td>
											<input class="span2" name="doctorId" type="text" value="${fdMemberConsultation.doctorId}"/>
					</td>							
			</tr>	
				<tr>	
					<th><%=TfdMemberConsultation.ALIAS_CONSULTATION_MESSAGE%></th>	
					<td>
											<input class="span2" name="consultationMessage" type="text" value="${fdMemberConsultation.consultationMessage}"/>
					</td>							
					<th><%=TfdMemberConsultation.ALIAS_CREATE_BY%></th>	
					<td>
											<input class="span2" name="createBy" type="text" value="${fdMemberConsultation.createBy}"/>
					</td>							
			</tr>	
				<tr>	
					<th><%=TfdMemberConsultation.ALIAS_CREATE_TIME%></th>	
					<td>
											<input class="span2" name="createTime" type="text" value="${fdMemberConsultation.createTime}"/>
					</td>							
					<th><%=TfdMemberConsultation.ALIAS_UPDATE_BY%></th>	
					<td>
											<input class="span2" name="updateBy" type="text" value="${fdMemberConsultation.updateBy}"/>
					</td>							
			</tr>	
				<tr>	
					<th><%=TfdMemberConsultation.ALIAS_UPDATE_TIME%></th>	
					<td>
											<input class="span2" name="updateTime" type="text" value="${fdMemberConsultation.updateTime}"/>
					</td>							
					<th><%=TfdMemberConsultation.ALIAS_STATUS%></th>	
					<td>
											<input class="span2" name="status" type="text" value="${fdMemberConsultation.status}"/>
					</td>							
			</tr>	
				<tr>	
					<th><%=TfdMemberConsultation.ALIAS_COUPON_ID%></th>	
					<td>
											<input class="span2" name="couponId" type="text" value="${fdMemberConsultation.couponId}"/>
					</td>							
					<th><%=TfdMemberConsultation.ALIAS_PICS%></th>	
					<td>
											<input class="span2" name="pics" type="text" value="${fdMemberConsultation.pics}"/>
					</td>							
			</tr>	
				<tr>	
					<th><%=TfdMemberConsultation.ALIAS_FILE%></th>	
					<td>
											<input class="span2" name="file" type="text" value="${fdMemberConsultation.file}"/>
					</td>							
					<th><%=TfdMemberConsultation.ALIAS_CONSULTATION_STATUS%></th>	
					<td>
											<input class="span2" name="consultationStatus" type="text" value="${fdMemberConsultation.consultationStatus}"/>
					</td>							
			</tr>	
				<tr>	
					<th><%=TfdMemberConsultation.ALIAS_DOCTOR_REPLY%></th>	
					<td>
											<input class="span2" name="doctorReply" type="text" value="${fdMemberConsultation.doctorReply}"/>
					</td>							
			</tr>	
			</table>				
		</form>
	</div>
</div>