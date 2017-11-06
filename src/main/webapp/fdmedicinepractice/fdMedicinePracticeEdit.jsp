<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.mobian.model.TfdMedicinePractice" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="jb" uri="http://www.jb.cn/jbtag"%> 
<script type="text/javascript">
	$(function() {
		parent.$.messager.progress('close');
		$('#form').form({
			url : '${pageContext.request.contextPath}/fdMedicinePracticeController/edit',
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
				<input type="hidden" name="id" value = "${fdMedicinePractice.id}"/>
			<table class="table table-hover table-condensed">
				<tr>	
					<th><%=TfdMedicinePractice.ALIAS_TITLE%></th>	
					<td>
											<input class="span2" name="title" type="text" value="${fdMedicinePractice.title}"/>
					</td>							
					<th><%=TfdMedicinePractice.ALIAS_VIEW%></th>	
					<td>
											<input class="span2" name="view" type="text" value="${fdMedicinePractice.view}"/>
					</td>							
			</tr>	
				<tr>	
					<th><%=TfdMedicinePractice.ALIAS_COMMENT%></th>	
					<td>
											<input class="span2" name="comment" type="text" value="${fdMedicinePractice.comment}"/>
					</td>							
					<th><%=TfdMedicinePractice.ALIAS_CREATE_TIME%></th>	
					<td>
											<input class="span2" name="createTime" type="text" value="${fdMedicinePractice.createTime}"/>
					</td>							
			</tr>	
				<tr>	
					<th><%=TfdMedicinePractice.ALIAS_UPDATE_TIME%></th>	
					<td>
											<input class="span2" name="updateTime" type="text" value="${fdMedicinePractice.updateTime}"/>
					</td>							
					<th><%=TfdMedicinePractice.ALIAS_IS_UP%></th>	
					<td>
											<input class="span2" name="isUp" type="text" value="${fdMedicinePractice.isUp}"/>
					</td>							
			</tr>	
				<tr>	
					<th><%=TfdMedicinePractice.ALIAS_STATUS%></th>	
					<td>
											<input class="span2" name="status" type="text" value="${fdMedicinePractice.status}"/>
					</td>							
					<th><%=TfdMedicinePractice.ALIAS_PIC%></th>	
					<td>
											<input class="span2" name="pic" type="text" value="${fdMedicinePractice.pic}"/>
					</td>							
			</tr>	
				<tr>	
					<th><%=TfdMedicinePractice.ALIAS_CONTENT%></th>	
					<td>
											<input class="span2" name="content" type="text" value="${fdMedicinePractice.content}"/>
					</td>							
					<th><%=TfdMedicinePractice.ALIAS_DESC%></th>	
					<td>
											<input class="span2" name="desc" type="text" value="${fdMedicinePractice.desc}"/>
					</td>							
			</tr>	
				<tr>	
					<th><%=TfdMedicinePractice.ALIAS_FILE%></th>	
					<td>
											<input class="span2" name="file" type="text" value="${fdMedicinePractice.file}"/>
					</td>							
			</tr>	
			</table>				
		</form>
	</div>
</div>