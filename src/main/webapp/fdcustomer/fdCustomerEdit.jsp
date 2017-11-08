<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.mobian.model.TfdCustomer" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="jb" uri="http://www.jb.cn/jbtag"%> 
<script type="text/javascript">
	$(function() {
		parent.$.messager.progress('close');
		$('#form').form({
			url : '${pageContext.request.contextPath}/fdCustomerController/edit',
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
				<input type="hidden" name="userId" value = "${fdCustomer.userId}"/>
			<table class="table table-hover table-condensed">
				<tr>	
					<th><%=TfdCustomer.ALIAS_REAL_NAME%></th>	
					<td>
											<input class="span2" name="realName" type="text" value="${fdCustomer.realName}"/>
					</td>							
					<th><%=TfdCustomer.ALIAS_PHONE%></th>	
					<td>
											<input class="span2" name="phone" type="text" value="${fdCustomer.phone}"/>
					</td>							
			</tr>	
				<tr>	
					<th><%=TfdCustomer.ALIAS_PROVINCE%></th>	
					<td>
											<input class="span2" name="province" type="text" value="${fdCustomer.province}"/>
					</td>							
					<th><%=TfdCustomer.ALIAS_CITY%></th>	
					<td>
											<input class="span2" name="city" type="text" value="${fdCustomer.city}"/>
					</td>							
			</tr>	
				<tr>	
					<th><%=TfdCustomer.ALIAS_COUNTY%></th>	
					<td>
											<input class="span2" name="county" type="text" value="${fdCustomer.county}"/>
					</td>							
					<th><%=TfdCustomer.ALIAS_ADDR%></th>	
					<td>
											<input class="span2" name="addr" type="text" value="${fdCustomer.addr}"/>
					</td>							
			</tr>	
				<tr>	
					<th><%=TfdCustomer.ALIAS_QQ%></th>	
					<td>
											<input class="span2" name="qq" type="text" value="${fdCustomer.qq}"/>
					</td>							
					<th><%=TfdCustomer.ALIAS_SEX%></th>	
					<td>
											<input class="span2" name="sex" type="text" value="${fdCustomer.sex}"/>
					</td>							
			</tr>	
				<tr>	
					<th><%=TfdCustomer.ALIAS_BIRTHDAY%></th>	
					<td>
											<input class="span2" name="birthday" type="text" value="${fdCustomer.birthday}"/>
					</td>							
					<th><%=TfdCustomer.ALIAS_GROUP_ID%></th>	
					<td>
											<input class="span2" name="groupId" type="text" value="${fdCustomer.groupId}"/>
					</td>							
			</tr>	
				<tr>	
					<th><%=TfdCustomer.ALIAS_POINT%></th>	
					<td>
											<input class="span2" name="point" type="text" value="${fdCustomer.point}"/>
					</td>							
					<th><%=TfdCustomer.ALIAS_MESSAGE_IDS%></th>	
					<td>
											<input class="span2" name="messageIds" type="text" value="${fdCustomer.messageIds}"/>
					</td>							
			</tr>	
				<tr>	
					<th><%=TfdCustomer.ALIAS_PROP%></th>	
					<td>
											<input class="span2" name="prop" type="text" value="${fdCustomer.prop}"/>
					</td>							
					<th><%=TfdCustomer.ALIAS_BALANCE%></th>	
					<td>
											<input class="span2" name="balance" type="text" value="${fdCustomer.balance}"/>
					</td>							
			</tr>	
				<tr>	
					<th><%=TfdCustomer.ALIAS_CUSTOM%></th>	
					<td>
											<input class="span2" name="custom" type="text" value="${fdCustomer.custom}"/>
					</td>							
					<th><%=TfdCustomer.ALIAS_CHECKIN_TIME%></th>	
					<td>
											<input class="span2" name="checkinTime" type="text" value="${fdCustomer.checkinTime}"/>
					</td>							
			</tr>	
				<tr>	
					<th><%=TfdCustomer.ALIAS_NICK_NAME%></th>	
					<td>
											<input class="span2" name="nickName" type="text" value="${fdCustomer.nickName}"/>
					</td>							
			</tr>	
			</table>				
		</form>
	</div>
</div>