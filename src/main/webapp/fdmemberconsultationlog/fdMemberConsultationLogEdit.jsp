<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.mobian.model.TfdMemberConsultationLog" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="jb" uri="http://www.jb.cn/jbtag"%> 
<script type="text/javascript">
	$(function() {
		parent.$.messager.progress('close');
		$('#form').form({
			url : '${pageContext.request.contextPath}/fdMemberConsultationLogController/edit',
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
				<input type="hidden" name="id" value = "${fdMemberConsultationLog.id}"/>
			<table class="table table-hover table-condensed">
				<tr>	
					<th><%=TfdMemberConsultationLog.ALIAS_MTYPE%></th>	
					<td>
											<input class="span2" name="mtype" type="text" value="${fdMemberConsultationLog.mtype}"/>
					</td>							
					<th><%=TfdMemberConsultationLog.ALIAS_FROM_USER_ID%></th>	
					<td>
											<input class="span2" name="fromUserId" type="text" value="${fdMemberConsultationLog.fromUserId}"/>
					</td>							
			</tr>	
				<tr>	
					<th><%=TfdMemberConsultationLog.ALIAS_TO_USER_ID%></th>	
					<td>
											<input class="span2" name="toUserId" type="text" value="${fdMemberConsultationLog.toUserId}"/>
					</td>							
					<th><%=TfdMemberConsultationLog.ALIAS_CREATE_TIME%></th>	
					<td>
											<input class="span2" name="createTime" type="text" value="${fdMemberConsultationLog.createTime}"/>
					</td>							
			</tr>	
				<tr>	
					<th><%=TfdMemberConsultationLog.ALIAS_UPDATE_TIME%></th>	
					<td>
											<input class="span2" name="updateTime" type="text" value="${fdMemberConsultationLog.updateTime}"/>
					</td>							
					<th><%=TfdMemberConsultationLog.ALIAS_STATUS%></th>	
					<td>
											<input class="span2" name="status" type="text" value="${fdMemberConsultationLog.status}"/>
					</td>							
			</tr>	
				<tr>	
					<th><%=TfdMemberConsultationLog.ALIAS_CONTENT%></th>	
					<td>
											<input class="span2" name="content" type="text" value="${fdMemberConsultationLog.content}"/>
					</td>							
					<th><%=TfdMemberConsultationLog.ALIAS_SENDER_TYPE%></th>	
					<td>
											<input class="span2" name="senderType" type="text" value="${fdMemberConsultationLog.senderType}"/>
					</td>							
			</tr>	
			</table>				
		</form>
	</div>
</div>