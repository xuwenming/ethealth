<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.mobian.model.TfdMemberDoctor" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="jb" uri="http://www.jb.cn/jbtag"%>  
<script type="text/javascript">
	$(function() {
	 parent.$.messager.progress('close');
		$('#form').form({
			url : '${pageContext.request.contextPath}/fdMemberDoctorController/add',
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
			<table class="table table-hover table-condensed">
				<tr>	
					<th><%=TfdMemberDoctor.ALIAS_LEVEL%></th>	
					<td>
											<input class="span2" name="level" type="text"/>
					</td>							
					<th><%=TfdMemberDoctor.ALIAS_HOSPITAL%></th>	
					<td>
											<input class="span2" name="hospital" type="text"/>
					</td>							
				</tr>	
				<tr>	
					<th><%=TfdMemberDoctor.ALIAS_DEPARTMENT%></th>	
					<td>
											<input class="span2" name="department" type="text"/>
					</td>							
					<th><%=TfdMemberDoctor.ALIAS_EDUCATION%></th>	
					<td>
											<input class="span2" name="education" type="text"/>
					</td>							
				</tr>	
				<tr>	
					<th><%=TfdMemberDoctor.ALIAS_CONSULTING_HOUR%></th>	
					<td>
											<input class="span2" name="consultingHour" type="text"/>
					</td>							
					<th><%=TfdMemberDoctor.ALIAS_SPECIAL_HOUR%></th>	
					<td>
											<input class="span2" name="specialHour" type="text"/>
					</td>							
				</tr>	
				<tr>	
					<th><%=TfdMemberDoctor.ALIAS_SPECIALITY%></th>	
					<td>
											<input class="span2" name="speciality" type="text"/>
					</td>							
					<th><%=TfdMemberDoctor.ALIAS_INTRODUCE%></th>	
					<td>
											<input class="span2" name="introduce" type="text"/>
					</td>							
				</tr>	
				<tr>	
					<th><%=TfdMemberDoctor.ALIAS_PICS%></th>	
					<td>
											<input class="span2" name="pics" type="text"/>
					</td>							
					<th><%=TfdMemberDoctor.ALIAS_CREATE_BY%></th>	
					<td>
											<input class="span2" name="createBy" type="text"/>
					</td>							
				</tr>	
				<tr>	
					<th><%=TfdMemberDoctor.ALIAS_CREATE_TIME%></th>	
					<td>
					<input class="span2" name="createTime" type="text" onclick="WdatePicker({dateFmt:'<%=TfdMemberDoctor.FORMAT_CREATE_TIME%>'})"  maxlength="0" class="" />
					</td>							
					<th><%=TfdMemberDoctor.ALIAS_UPDATE_BY%></th>	
					<td>
											<input class="span2" name="updateBy" type="text"/>
					</td>							
				</tr>	
				<tr>	
					<th><%=TfdMemberDoctor.ALIAS_UPDATE_TIME%></th>	
					<td>
					<input class="span2" name="updateTime" type="text" onclick="WdatePicker({dateFmt:'<%=TfdMemberDoctor.FORMAT_UPDATE_TIME%>'})"  maxlength="0" class="" />
					</td>							
					<th><%=TfdMemberDoctor.ALIAS_GROUP_ID%></th>	
					<td>
											<input class="span2" name="groupId" type="text"/>
					</td>							
				</tr>	
			</table>		
		</form>
	</div>
</div>