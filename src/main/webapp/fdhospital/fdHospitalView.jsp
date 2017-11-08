<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.mobian.model.TfdHospital" %>
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
					<th><%=TfdHospital.ALIAS_HOSPITAL_NAME%></th>	
					<td>
						${fdHospital.hospitalName}							
					</td>							
					<th><%=TfdHospital.ALIAS_HOSPITAL_LEVEL%></th>	
					<td>
						${fdHospital.hospitalLevel}							
					</td>							
				</tr>		
				<tr>	
					<th><%=TfdHospital.ALIAS_INTRODUCE%></th>	
					<td>
						${fdHospital.introduce}							
					</td>							
					<th><%=TfdHospital.ALIAS_PROVINCE%></th>	
					<td>
						${fdHospital.province}							
					</td>							
				</tr>		
				<tr>	
					<th><%=TfdHospital.ALIAS_CITY%></th>	
					<td>
						${fdHospital.city}							
					</td>							
					<th><%=TfdHospital.ALIAS_COUNTY%></th>	
					<td>
						${fdHospital.county}							
					</td>							
				</tr>		
				<tr>	
					<th><%=TfdHospital.ALIAS_PICS%></th>	
					<td>
						${fdHospital.pics}							
					</td>							
					<th><%=TfdHospital.ALIAS_CREATE_BY%></th>	
					<td>
						${fdHospital.createBy}							
					</td>							
				</tr>		
				<tr>	
					<th><%=TfdHospital.ALIAS_CREATE_TIME%></th>	
					<td>
						${fdHospital.createTime}							
					</td>							
					<th><%=TfdHospital.ALIAS_UPDATE_BY%></th>	
					<td>
						${fdHospital.updateBy}							
					</td>							
				</tr>		
				<tr>	
					<th><%=TfdHospital.ALIAS_UPDATE_TIME%></th>	
					<td>
						${fdHospital.updateTime}							
					</td>							
					<th><%=TfdHospital.ALIAS_STATUS%></th>	
					<td>
						${fdHospital.status}							
					</td>							
				</tr>		
		</table>
	</div>
</div>