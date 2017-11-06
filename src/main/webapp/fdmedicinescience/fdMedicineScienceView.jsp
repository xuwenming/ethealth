<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.mobian.model.TfdMedicineScience" %>
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
					<th><%=TfdMedicineScience.ALIAS_TITLE%></th>	
					<td>
						${fdMedicineScience.title}							
					</td>							
					<th><%=TfdMedicineScience.ALIAS_VIEW%></th>	
					<td>
						${fdMedicineScience.view}							
					</td>							
				</tr>		
				<tr>	
					<th><%=TfdMedicineScience.ALIAS_COMMENT%></th>	
					<td>
						${fdMedicineScience.comment}							
					</td>							
					<th><%=TfdMedicineScience.ALIAS_CREATE_TIME%></th>	
					<td>
						${fdMedicineScience.createTime}							
					</td>							
				</tr>		
				<tr>	
					<th><%=TfdMedicineScience.ALIAS_UPDATE_TIME%></th>	
					<td>
						${fdMedicineScience.updateTime}							
					</td>							
					<th><%=TfdMedicineScience.ALIAS_IS_UP%></th>	
					<td>
						${fdMedicineScience.isUp}							
					</td>							
				</tr>		
				<tr>	
					<th><%=TfdMedicineScience.ALIAS_STATUS%></th>	
					<td>
						${fdMedicineScience.status}							
					</td>							
					<th><%=TfdMedicineScience.ALIAS_PIC%></th>	
					<td>
						${fdMedicineScience.pic}							
					</td>							
				</tr>		
				<tr>	
					<th><%=TfdMedicineScience.ALIAS_CONTENT%></th>	
					<td>
						${fdMedicineScience.content}							
					</td>							
					<th><%=TfdMedicineScience.ALIAS_DESC%></th>	
					<td>
						${fdMedicineScience.desc}							
					</td>							
				</tr>		
				<tr>	
					<th><%=TfdMedicineScience.ALIAS_FILE%></th>	
					<td>
						${fdMedicineScience.file}							
					</td>							
				</tr>		
		</table>
	</div>
</div>