<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.mobian.model.TfdPicture" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="jb" uri="http://www.jb.cn/jbtag"%>  
<script type="text/javascript">
	$(function() {
	 parent.$.messager.progress('close');
		$('#form').form({
			url : '${pageContext.request.contextPath}/fdPictureController/add',
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
					<th><%=TfdPicture.ALIAS_PATH%></th>	
					<td>
					
											<input  name="path" type="text" class="easyui-validatebox span2" data-options="required:true"/>
					</td>							
					<th><%=TfdPicture.ALIAS_URL%></th>	
					<td>
					
											<input  name="url" type="text" class="easyui-validatebox span2" data-options="required:true"/>
					</td>							
				</tr>	
				<tr>	
					<th><%=TfdPicture.ALIAS_MD5%></th>	
					<td>
					
											<input  name="md5" type="text" class="easyui-validatebox span2" data-options="required:true"/>
					</td>							
					<th><%=TfdPicture.ALIAS_SHA1%></th>	
					<td>
					
											<input  name="sha1" type="text" class="easyui-validatebox span2" data-options="required:true"/>
					</td>							
				</tr>	
				<tr>	
					<th><%=TfdPicture.ALIAS_STATUS%></th>	
					<td>
					
											<input  name="status" type="text" class="easyui-validatebox span2" data-options="required:true"/>
					</td>							
					<th><%=TfdPicture.ALIAS_CREATE_TIME%></th>	
					<td>
					
											<input  name="createTime" type="text" class="easyui-validatebox span2" data-options="required:true"/>
					</td>							
				</tr>	
				<tr>	
					<th><%=TfdPicture.ALIAS_TYPE%></th>	
					<td>
											<input class="span2" name="type" type="text"/>
					</td>							
					<th><%=TfdPicture.ALIAS_SOURCE_ID%></th>	
					<td>
											<input class="span2" name="sourceId" type="text"/>
					</td>							
				</tr>	
			</table>		
		</form>
	</div>
</div>