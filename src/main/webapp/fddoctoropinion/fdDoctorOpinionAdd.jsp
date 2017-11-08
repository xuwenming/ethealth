<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.mobian.model.TfdDoctorOpinion" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="jb" uri="http://www.jb.cn/jbtag"%>  
<script type="text/javascript">
	$(function() {
	 parent.$.messager.progress('close');
		$('#form').form({
			url : '${pageContext.request.contextPath}/fdDoctorOpinionController/add',
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
					<th><%=TfdDoctorOpinion.ALIAS_USER_ID%></th>	
					<td>
					
											<input  name="userId" type="text" class="easyui-validatebox span2" data-options="required:true"/>
					</td>							
					<th><%=TfdDoctorOpinion.ALIAS_TITLE%></th>	
					<td>
					
											<input  name="title" type="text" class="easyui-validatebox span2" data-options="required:true"/>
					</td>							
				</tr>	
				<tr>	
					<th><%=TfdDoctorOpinion.ALIAS_VIEW%></th>	
					<td>
					
											<input  name="view" type="text" class="easyui-validatebox span2" data-options="required:true"/>
					</td>							
					<th><%=TfdDoctorOpinion.ALIAS_COMMENT%></th>	
					<td>
					
											<input  name="comment" type="text" class="easyui-validatebox span2" data-options="required:true"/>
					</td>							
				</tr>	
				<tr>	
					<th><%=TfdDoctorOpinion.ALIAS_CREATE_TIME%></th>	
					<td>
					
											<input  name="createTime" type="text" class="easyui-validatebox span2" data-options="required:true"/>
					</td>							
					<th><%=TfdDoctorOpinion.ALIAS_UPDATE_TIME%></th>	
					<td>
					
											<input  name="updateTime" type="text" class="easyui-validatebox span2" data-options="required:true"/>
					</td>							
				</tr>	
				<tr>	
					<th><%=TfdDoctorOpinion.ALIAS_VERIFY_STATUS%></th>	
					<td>
					
											<input  name="verifyStatus" type="text" class="easyui-validatebox span2" data-options="required:true"/>
					</td>							
					<th><%=TfdDoctorOpinion.ALIAS_IS_UP%></th>	
					<td>
					
											<input  name="isUp" type="text" class="easyui-validatebox span2" data-options="required:true"/>
					</td>							
				</tr>	
				<tr>	
					<th><%=TfdDoctorOpinion.ALIAS_STATUS%></th>	
					<td>
											<input class="span2" name="status" type="text"/>
					</td>							
					<th><%=TfdDoctorOpinion.ALIAS_PIC%></th>	
					<td>
											<input class="span2" name="pic" type="text"/>
					</td>							
				</tr>	
				<tr>	
					<th><%=TfdDoctorOpinion.ALIAS_CONTENT%></th>	
					<td>
					
											<input  name="content" type="text" class="easyui-validatebox span2" data-options="required:true"/>
					</td>							
					<th><%=TfdDoctorOpinion.ALIAS_BRIEF%></th>	
					<td>
											<input class="span2" name="brief" type="text"/>
					</td>							
				</tr>	
				<tr>	
					<th><%=TfdDoctorOpinion.ALIAS_FILE%></th>	
					<td>
											<input class="span2" name="file" type="text"/>
					</td>							
				</tr>	
			</table>		
		</form>
	</div>
</div>