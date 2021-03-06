<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.mobian.model.TfdBanner" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="jb" uri="http://www.jb.cn/jbtag"%>  
<script type="text/javascript">
	$(function() {
	 parent.$.messager.progress('close');
		$('#form').form({
			url : '${pageContext.request.contextPath}/fdBannerController/add',
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
					<th><%=TfdBanner.ALIAS_TITLE%></th>	
					<td>
											<input class="span2" name="title" type="text"/>
					</td>							
					<th><%=TfdBanner.ALIAS_LINK%></th>	
					<td>
											<input class="span2" name="link" type="text"/>
					</td>							
				</tr>	
				<tr>	
					<th><%=TfdBanner.ALIAS_NUM%></th>	
					<td>
											<input class="span2" name="num" type="text"/>
					</td>							
					<th><%=TfdBanner.ALIAS_PIC%></th>	
					<td>
											<input class="span2" name="pic" type="text"/>
					</td>							
				</tr>	
				<tr>	
					<th><%=TfdBanner.ALIAS_CREATE_BY%></th>	
					<td>
											<input class="span2" name="createBy" type="text"/>
					</td>							
					<th><%=TfdBanner.ALIAS_CREATE_TIME%></th>	
					<td>
											<input class="span2" name="createTime" type="text"/>
					</td>							
				</tr>	
				<tr>	
					<th><%=TfdBanner.ALIAS_UPDATE_BY%></th>	
					<td>
											<input class="span2" name="updateBy" type="text"/>
					</td>							
					<th><%=TfdBanner.ALIAS_UPDATE_TIME%></th>	
					<td>
											<input class="span2" name="updateTime" type="text"/>
					</td>							
				</tr>	
				<tr>	
					<th><%=TfdBanner.ALIAS_STATUS%></th>	
					<td>
											<input class="span2" name="status" type="text"/>
					</td>							
					<th><%=TfdBanner.ALIAS_SOURCE%></th>	
					<td>
											<input class="span2" name="source" type="text"/>
					</td>							
				</tr>	
			</table>		
		</form>
	</div>
</div>