<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.mobian.model.TfdMember" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="jb" uri="http://www.jb.cn/jbtag"%> 
<script type="text/javascript">
	$(function() {
		parent.$.messager.progress('close');
		$('#form').form({
			url : '${pageContext.request.contextPath}/fdMemberController/edit',
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
				<input type="hidden" name="id" value = "${fdMember.id}"/>
			<table class="table table-hover table-condensed">
				<tr>	
					<th><%=TfdMember.ALIAS_USERNAME%></th>	
					<td>
											<input class="span2" name="username" type="text" class="easyui-validatebox span2" data-options="required:true" value="${fdMember.username}"/>
					</td>							
					<th><%=TfdMember.ALIAS_PASSWORD%></th>	
					<td>
											<input class="span2" name="password" type="text" class="easyui-validatebox span2" data-options="required:true" value="${fdMember.password}"/>
					</td>							
			</tr>	
				<tr>	
					<th><%=TfdMember.ALIAS_SCORE%></th>	
					<td>
											<input class="span2" name="score" type="text" value="${fdMember.score}"/>
					</td>							
					<th><%=TfdMember.ALIAS_EMAIL%></th>	
					<td>
											<input class="span2" name="email" type="text" value="${fdMember.email}"/>
					</td>							
			</tr>	
				<tr>	
					<th><%=TfdMember.ALIAS_LOGIN%></th>	
					<td>
											<input class="span2" name="login" type="text" value="${fdMember.login}"/>
					</td>							
					<th><%=TfdMember.ALIAS_MOBILE%></th>	
					<td>
											<input class="span2" name="mobile" type="text" class="easyui-validatebox span2" data-options="required:true" value="${fdMember.mobile}"/>
					</td>							
			</tr>	
				<tr>	
					<th><%=TfdMember.ALIAS_REG_TIME%></th>	
					<td>
											<input class="span2" name="regTime" type="text" value="${fdMember.regTime}"/>
					</td>							
					<th><%=TfdMember.ALIAS_REG_IP%></th>	
					<td>
											<input class="span2" name="regIp" type="text" value="${fdMember.regIp}"/>
					</td>							
			</tr>	
				<tr>	
					<th><%=TfdMember.ALIAS_LAST_LOGIN_TIME%></th>	
					<td>
											<input class="span2" name="lastLoginTime" type="text" value="${fdMember.lastLoginTime}"/>
					</td>							
					<th><%=TfdMember.ALIAS_LAST_LOGIN_IP%></th>	
					<td>
											<input class="span2" name="lastLoginIp" type="text" value="${fdMember.lastLoginIp}"/>
					</td>							
			</tr>	
				<tr>	
					<th><%=TfdMember.ALIAS_UPDATE_TIME%></th>	
					<td>
											<input class="span2" name="updateTime" type="text" value="${fdMember.updateTime}"/>
					</td>							
					<th><%=TfdMember.ALIAS_STATUS%></th>	
					<td>
											<input class="span2" name="status" type="text" value="${fdMember.status}"/>
					</td>							
			</tr>	
				<tr>	
					<th><%=TfdMember.ALIAS_IS_ADMIN%></th>	
					<td>
											<input class="span2" name="isAdmin" type="text" value="${fdMember.isAdmin}"/>
					</td>							
					<th><%=TfdMember.ALIAS_GROUPID%></th>	
					<td>
											<input class="span2" name="groupid" type="text" value="${fdMember.groupid}"/>
					</td>							
			</tr>	
				<tr>	
					<th><%=TfdMember.ALIAS_AMOUNT%></th>	
					<td>
											<input class="span2" name="amount" type="text" value="${fdMember.amount}"/>
					</td>							
					<th><%=TfdMember.ALIAS_MODELID%></th>	
					<td>
											<input class="span2" name="modelid" type="text" value="${fdMember.modelid}"/>
					</td>							
			</tr>	
				<tr>	
					<th><%=TfdMember.ALIAS_MESSAGE%></th>	
					<td>
											<input class="span2" name="message" type="text" value="${fdMember.message}"/>
					</td>							
					<th><%=TfdMember.ALIAS_PIC%></th>	
					<td>
											<input class="span2" name="pic" type="text" value="${fdMember.pic}"/>
					</td>							
			</tr>	
				<tr>	
					<th><%=TfdMember.ALIAS_DOCTOR_ID%></th>	
					<td>
											<input class="span2" name="doctorId" type="text" value="${fdMember.doctorId}"/>
					</td>							
					<th><%=TfdMember.ALIAS_GROUP_ID%></th>	
					<td>
											<input class="span2" name="groupId" type="text" value="${fdMember.groupId}"/>
					</td>							
			</tr>	
				<tr>	
					<th><%=TfdMember.ALIAS_VIP_END_TIME%></th>	
					<td>
											<input class="span2" name="vipEndTime" type="text" value="${fdMember.vipEndTime}"/>
					</td>							
			</tr>	
			</table>				
		</form>
	</div>
</div>