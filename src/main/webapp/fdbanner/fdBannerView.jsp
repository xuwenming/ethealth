<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.mobian.model.TfdBanner" %>
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
					<th><%=TfdBanner.ALIAS_TITLE%></th>	
					<td>
						${fdBanner.title}							
					</td>							
					<th><%=TfdBanner.ALIAS_LINK%></th>	
					<td>
						${fdBanner.link}							
					</td>							
				</tr>		
				<tr>	
					<th><%=TfdBanner.ALIAS_NUM%></th>	
					<td>
						${fdBanner.num}							
					</td>							
					<th><%=TfdBanner.ALIAS_PIC%></th>	
					<td>
						${fdBanner.pic}							
					</td>							
				</tr>		
				<tr>	
					<th><%=TfdBanner.ALIAS_CREATE_BY%></th>	
					<td>
						${fdBanner.createBy}							
					</td>							
					<th><%=TfdBanner.ALIAS_CREATE_TIME%></th>	
					<td>
						${fdBanner.createTime}							
					</td>							
				</tr>		
				<tr>	
					<th><%=TfdBanner.ALIAS_UPDATE_BY%></th>	
					<td>
						${fdBanner.updateBy}							
					</td>							
					<th><%=TfdBanner.ALIAS_UPDATE_TIME%></th>	
					<td>
						${fdBanner.updateTime}							
					</td>							
				</tr>		
				<tr>	
					<th><%=TfdBanner.ALIAS_STATUS%></th>	
					<td>
						${fdBanner.status}							
					</td>							
					<th><%=TfdBanner.ALIAS_SOURCE%></th>	
					<td>
						${fdBanner.source}							
					</td>							
				</tr>		
		</table>
	</div>
</div>