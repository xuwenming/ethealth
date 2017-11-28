<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.mobian.model.TfdMemberDoctorSh" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="jb" uri="http://www.jb.cn/jbtag"%> 
<script type="text/javascript">
	$(function() {
		parent.$.messager.progress('close');
		$('#form').form({
			url : '${pageContext.request.contextPath}/fdMemberDoctorShController/edit',
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
				<input type="hidden" name="id" value = "${fdMemberDoctorSh.id}"/>
			<table class="table table-hover table-condensed">
				<tr>	
					<th><%=TfdMemberDoctorSh.ALIAS_LEVEL%></th>	
					<td>
											<input class="span2" name="level" type="text" value="${fdMemberDoctorSh.level}"/>
					</td>							
					<th><%=TfdMemberDoctorSh.ALIAS_HOSPITAL%></th>	
					<td>
											<input class="span2" name="hospital" type="text" value="${fdMemberDoctorSh.hospital}"/>
					</td>							
			</tr>	
				<tr>	
					<th><%=TfdMemberDoctorSh.ALIAS_DEPARTMENT%></th>	
					<td>
											<input class="span2" name="department" type="text" value="${fdMemberDoctorSh.department}"/>
					</td>							
					<th><%=TfdMemberDoctorSh.ALIAS_EDUCATION%></th>	
					<td>
											<input class="span2" name="education" type="text" value="${fdMemberDoctorSh.education}"/>
					</td>							
			</tr>	
				<tr>	
					<th><%=TfdMemberDoctorSh.ALIAS_CONSULTING_HOUR%></th>	
					<td>
											<input class="span2" name="consultingHour" type="text" value="${fdMemberDoctorSh.consultingHour}"/>
					</td>							
					<th><%=TfdMemberDoctorSh.ALIAS_SPECIAL_HOUR%></th>	
					<td>
											<input class="span2" name="specialHour" type="text" value="${fdMemberDoctorSh.specialHour}"/>
					</td>							
			</tr>	
				<tr>	
					<th><%=TfdMemberDoctorSh.ALIAS_SPECIALITY%></th>	
					<td>
											<input class="span2" name="speciality" type="text" value="${fdMemberDoctorSh.speciality}"/>
					</td>							
					<th><%=TfdMemberDoctorSh.ALIAS_INTRODUCE%></th>	
					<td>
											<input class="span2" name="introduce" type="text" value="${fdMemberDoctorSh.introduce}"/>
					</td>							
			</tr>	
				<tr>	
					<th><%=TfdMemberDoctorSh.ALIAS_PICS%></th>	
					<td>
											<input class="span2" name="pics" type="text" value="${fdMemberDoctorSh.pics}"/>
					</td>							
					<th><%=TfdMemberDoctorSh.ALIAS_CREATE_BY%></th>	
					<td>
											<input class="span2" name="createBy" type="text" value="${fdMemberDoctorSh.createBy}"/>
					</td>							
			</tr>	
				<tr>	
					<th><%=TfdMemberDoctorSh.ALIAS_CREATE_TIME%></th>	
					<td>
					<input class="span2" name="createTime" type="text" onclick="WdatePicker({dateFmt:'<%=TfdMemberDoctorSh.FORMAT_CREATE_TIME%>'})"   maxlength="0" value="${fdMemberDoctorSh.createTime}"/>
					</td>							
					<th><%=TfdMemberDoctorSh.ALIAS_UPDATE_BY%></th>	
					<td>
											<input class="span2" name="updateBy" type="text" value="${fdMemberDoctorSh.updateBy}"/>
					</td>							
			</tr>	
				<tr>	
					<th><%=TfdMemberDoctorSh.ALIAS_UPDATE_TIME%></th>	
					<td>
					<input class="span2" name="updateTime" type="text" onclick="WdatePicker({dateFmt:'<%=TfdMemberDoctorSh.FORMAT_UPDATE_TIME%>'})"   maxlength="0" value="${fdMemberDoctorSh.updateTime}"/>
					</td>							
					<th><%=TfdMemberDoctorSh.ALIAS_STATUS%></th>	
					<td>
											<input class="span2" name="status" type="text" value="${fdMemberDoctorSh.status}"/>
					</td>							
			</tr>	
				<tr>	
					<th><%=TfdMemberDoctorSh.ALIAS_REASON%></th>	
					<td>
											<input class="span2" name="reason" type="text" value="${fdMemberDoctorSh.reason}"/>
					</td>							
					<th><%=TfdMemberDoctorSh.ALIAS_REAL_NAME%></th>	
					<td>
											<input class="span2" name="realName" type="text" value="${fdMemberDoctorSh.realName}"/>
					</td>							
			</tr>	
				<tr>	
					<th><%=TfdMemberDoctorSh.ALIAS_SEX%></th>	
					<td>
											<input class="span2" name="sex" type="text" value="${fdMemberDoctorSh.sex}"/>
					</td>							
					<th><%=TfdMemberDoctorSh.ALIAS_BIRTHDAY%></th>	
					<td>
											<input class="span2" name="birthday" type="text" value="${fdMemberDoctorSh.birthday}"/>
					</td>							
			</tr>	
				<tr>	
					<th><%=TfdMemberDoctorSh.ALIAS_GROUP_ID%></th>	
					<td>
											<input class="span2" name="groupId" type="text" value="${fdMemberDoctorSh.groupId}"/>
					</td>							
			</tr>	
			</table>				
		</form>
	</div>
</div>