<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.mobian.model.TfdHospital" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="jb" uri="http://www.jb.cn/jbtag"%> 
<script type="text/javascript">
	$(function() {
		parent.$.messager.progress('close');
		$('#form').form({
			url : '${pageContext.request.contextPath}/fdHospitalController/edit',
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
				<input type="hidden" name="id" value = "${fdHospital.id}"/>
			<table class="table table-hover table-condensed">
				<tr>	
					<th><%=TfdHospital.ALIAS_HOSPITAL_NAME%></th>	
					<td>
											<input class="span2" name="hospitalName" type="text" value="${fdHospital.hospitalName}"/>
					</td>							
					<th><%=TfdHospital.ALIAS_HOSPITAL_LEVEL%></th>	
					<td>
											<input class="span2" name="hospitalLevel" type="text" value="${fdHospital.hospitalLevel}"/>
					</td>							
			</tr>	
				<tr>	
					<th><%=TfdHospital.ALIAS_INTRODUCE%></th>	
					<td>
											<input class="span2" name="introduce" type="text" value="${fdHospital.introduce}"/>
					</td>							
					<th><%=TfdHospital.ALIAS_PROVINCE%></th>	
					<td>
											<input class="span2" name="province" type="text" value="${fdHospital.province}"/>
					</td>							
			</tr>	
				<tr>	
					<th><%=TfdHospital.ALIAS_CITY%></th>	
					<td>
											<input class="span2" name="city" type="text" value="${fdHospital.city}"/>
					</td>							
					<th><%=TfdHospital.ALIAS_COUNTY%></th>	
					<td>
											<input class="span2" name="county" type="text" value="${fdHospital.county}"/>
					</td>							
			</tr>	
				<tr>	
					<th><%=TfdHospital.ALIAS_PICS%></th>	
					<td>
											<input class="span2" name="pics" type="text" value="${fdHospital.pics}"/>
					</td>							
					<th><%=TfdHospital.ALIAS_CREATE_BY%></th>	
					<td>
											<input class="span2" name="createBy" type="text" value="${fdHospital.createBy}"/>
					</td>							
			</tr>	
				<tr>	
					<th><%=TfdHospital.ALIAS_CREATE_TIME%></th>	
					<td>
											<input class="span2" name="createTime" type="text" value="${fdHospital.createTime}"/>
					</td>							
					<th><%=TfdHospital.ALIAS_UPDATE_BY%></th>	
					<td>
											<input class="span2" name="updateBy" type="text" value="${fdHospital.updateBy}"/>
					</td>							
			</tr>	
				<tr>	
					<th><%=TfdHospital.ALIAS_UPDATE_TIME%></th>	
					<td>
											<input class="span2" name="updateTime" type="text" value="${fdHospital.updateTime}"/>
					</td>							
					<th><%=TfdHospital.ALIAS_STATUS%></th>	
					<td>
											<input class="span2" name="status" type="text" value="${fdHospital.status}"/>
					</td>							
			</tr>	
			</table>				
		</form>
	</div>
</div>