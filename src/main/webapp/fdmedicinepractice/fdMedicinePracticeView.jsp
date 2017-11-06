<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.mobian.model.TfdMedicinePractice" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript">
	$(function() {
		parent.$.messager.progress('close');		
	});
</script>
<div class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'center',border:false">
		<table class="table table-hover table-condensed">
				<tr>	
					<th><%=TfdMedicinePractice.ALIAS_TITLE%></th>	
					<td>
						${fdMedicinePractice.title}							
					</td>							
					<th><%=TfdMedicinePractice.ALIAS_VIEW%></th>	
					<td>
						${fdMedicinePractice.view}							
					</td>							
				</tr>		
				<tr>	
					<th><%=TfdMedicinePractice.ALIAS_COMMENT%></th>	
					<td>
						${fdMedicinePractice.comment}							
					</td>							
					<th><%=TfdMedicinePractice.ALIAS_CREATE_TIME%></th>	
					<td>
						${fdMedicinePractice.createTime}							
					</td>							
				</tr>		
				<tr>	
					<th><%=TfdMedicinePractice.ALIAS_UPDATE_TIME%></th>	
					<td>
						${fdMedicinePractice.updateTime}							
					</td>							
					<th><%=TfdMedicinePractice.ALIAS_IS_UP%></th>	
					<td>
						${fdMedicinePractice.isUp}							
					</td>							
				</tr>		
				<tr>	
					<th><%=TfdMedicinePractice.ALIAS_STATUS%></th>	
					<td>
						${fdMedicinePractice.status}							
					</td>							
					<th><%=TfdMedicinePractice.ALIAS_PIC%></th>	
					<td>
						${fdMedicinePractice.pic}							
					</td>							
				</tr>		
				<tr>	
					<th><%=TfdMedicinePractice.ALIAS_CONTENT%></th>	
					<td>
						${fdMedicinePractice.content}							
					</td>							
					<th><%=TfdMedicinePractice.ALIAS_DESC%></th>	
					<td>
						${fdMedicinePractice.desc}							
					</td>							
				</tr>		
				<tr>	
					<th><%=TfdMedicinePractice.ALIAS_FILE%></th>	
					<td>
						${fdMedicinePractice.file}							
					</td>							
				</tr>		
		</table>
	</div>
</div>