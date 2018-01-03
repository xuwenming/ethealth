<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.mobian.model.TfdMessage" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="jb" uri="http://www.jb.cn/jbtag"%>  
<script type="text/javascript">
	var editor;
	$(function() {
		window.setTimeout(function() {
			editor = KindEditor.create('#content', {
				width : '580px',
				height : '240px',
				items : ['undo', 'redo', '|', 'removeformat', 'hr', 'lineheight', 'link', 'unlink', '|', 'image', 'multiimage', 'media', '|', 'fullscreen', '/', 'formatblock', 'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold', 'italic', 'underline', '|', 'justifyleft', 'justifycenter', 'justifyright', 'justifyfull', 'insertorderedlist', 'insertunorderedlist', 'indent', 'outdent'],
				uploadJson : '${pageContext.request.contextPath}/fileController/upload',
				fileManagerJson : '${pageContext.request.contextPath}/fileController/fileManage',
				allowFileManager : true
			});
		}, 1);
	 	parent.$.messager.progress('close');
		$('#form').form({
			url : '${pageContext.request.contextPath}/fdMessageController/add',
			onSubmit : function() {
				parent.$.messager.progress({
					title : '提示',
					text : '数据处理中，请稍后....'
				});
				var isValid = $(this).form('validate');
				if (!isValid) {
					parent.$.messager.progress('close');
				}
				editor.sync();
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
	<div data-options="region:'center',border:false" title="" style="overflow: auto;">
		<form id="form" method="post">		
			<input type="hidden" name="id"/>
			<table class="table table-hover table-condensed">
				<tr>
					<th><%=TfdMessage.ALIAS_MTYPE%></th>
					<td>
						<select name="mtype" class="easyui-combobox"
								data-options="width:140,height:29,editable:false,panelHeight:'auto'">
							<option value="MT01">系统消息</option>
							<option value="MT03">免费咨询</option>
						</select>
					</td>
					<th>消费方类型</th>
					<td>
						<select name="consumerType" class="easyui-combobox"
								data-options="width:140,height:29,editable:false,panelHeight:'auto'">
							<option value="0">不限</option>
							<option value="1">患者端</option>
							<option value="2">医生端</option>
						</select>
					</td>
				</tr>
				<tr>
					<th><%=TfdMessage.ALIAS_TITLE%></th>
					<td colspan="3">
						<input class="span2" name="title" type="text" style="width: 500px;"/>
					</td>
				</tr>
				<tr>
					<th>状态</th>
					<td colspan="3">
						<jb:select dataType="ST" name="status" value="ST01"></jb:select>
					</td>
				</tr>

				<tr>
					<th>开始日期</th>
					<td>
						<input type="text" class="span2" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" id="startDateStr" name="startDateStr"/>
					</td>
					<th>结束日期</th>
					<td>
						<input type="text" class="span2" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" id="endDateStr" name="endDateStr"/>
					</td>
				</tr>
				<tr>
					<th>推送正文</th>
					<td colspan="3">
						<textarea style="width: 510px;height: 60px;" name="pushContent"></textarea>
					</td>
				</tr>
				<tr>
					<th valign="top">消息详情</th>
					<td colspan="3">
						<textarea name="content" id="content" style="height:180px;visibility:hidden;"></textarea>
					</td>
				</tr>
			</table>		
		</form>
	</div>
</div>