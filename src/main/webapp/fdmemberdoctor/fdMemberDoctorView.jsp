<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.mobian.model.TfdMemberDoctor" %>
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
					<th><%=TfdMemberDoctor.ALIAS_LEVEL%></th>	
					<td>
						${fdMemberDoctor.level}							
					</td>							
					<th><%=TfdMemberDoctor.ALIAS_HOSPITAL%></th>	
					<td>
						${fdMemberDoctor.hospital}							
					</td>							
				</tr>		
				<tr>	
					<th><%=TfdMemberDoctor.ALIAS_DEPARTMENT%></th>	
					<td>
						${fdMemberDoctor.department}							
					</td>							
					<th><%=TfdMemberDoctor.ALIAS_EDUCATION%></th>	
					<td>
						${fdMemberDoctor.education}							
					</td>							
				</tr>		
				<tr>	
					<th><%=TfdMemberDoctor.ALIAS_CONSULTING_HOUR%></th>	
					<td>
						${fdMemberDoctor.consultingHour}							
					</td>							
					<th><%=TfdMemberDoctor.ALIAS_SPECIAL_HOUR%></th>	
					<td>
						${fdMemberDoctor.specialHour}							
					</td>							
				</tr>		
				<tr>	
					<th><%=TfdMemberDoctor.ALIAS_SPECIALITY%></th>	
					<td>
						${fdMemberDoctor.speciality}							
					</td>							
					<th><%=TfdMemberDoctor.ALIAS_INTRODUCE%></th>	
					<td>
						${fdMemberDoctor.introduce}							
					</td>							
				</tr>		
				<tr>	
					<th><%=TfdMemberDoctor.ALIAS_PICS%></th>	
					<td>
						${fdMemberDoctor.pics}							
					</td>							
					<th><%=TfdMemberDoctor.ALIAS_CREATE_BY%></th>	
					<td>
						${fdMemberDoctor.createBy}							
					</td>							
				</tr>		
				<tr>	
					<th><%=TfdMemberDoctor.ALIAS_CREATE_TIME%></th>	
					<td>
						${fdMemberDoctor.createTime}							
					</td>							
					<th><%=TfdMemberDoctor.ALIAS_UPDATE_BY%></th>	
					<td>
						${fdMemberDoctor.updateBy}							
					</td>							
				</tr>		
				<tr>	
					<th><%=TfdMemberDoctor.ALIAS_UPDATE_TIME%></th>	
					<td>
						${fdMemberDoctor.updateTime}							
					</td>							
					<th><%=TfdMemberDoctor.ALIAS_GROUP_ID%></th>	
					<td>
						${fdMemberDoctor.groupId}							
					</td>							
				</tr>		
		</table>
	</div>
</div>