<%@ taglib prefix="s" uri="/struts-tags"%>
<table style="padding: 0px; width: 80%">
	<tr>
		<s:if test="userList.size > 0">
			<td colspan="3">
				<div id="userRoles"
					style="border: 1px solid #b7b7b7; overflow: auto; height: auto;">
					<table id="inlineItemTab" style="padding: 0px; width: 100%">
						<thead>
							<tr class="toggelCSS">
								<td class="toggelCSS" align="left"><span class="lblspan">&nbsp;&nbsp;User
										Id</span></td>
								<td class="toggelCSS" align="left"><span class="lblspan">User
										Name</span></td>
								<td class="toggelCSS" align="center"><span class="lblspan">Edit</span></td>
							</tr>
						</thead>
						<tbody>
							<% int i =0; %>
							<s:iterator value="userList">
								<%i++; %>
								<% if ( i % 2 == 0) {%>
								<tr bgcolor="#FFFFFF">
									<%}
									else
									{
									%>
								
								<tr bgcolor="#f5f5f5">
									<%} %>
									<td align="left" style="font-size: 11px; font-family: Tahoma;"><s:property
											value="usUserId" /></td>
									<td align="left" style="font-size: 11px; font-family: Tahoma;"><s:property
											value="usUserName" /></td>
									<td align="center"><a href="javascript:void(0);"
										onclick="editUserRoles('<s:property value="usEmployeeId"/>')"
										style="outline: none; color: #ffffff"> <img
											src='images/png/edit.png' title='Edit Field' alt='Edit Field'
											style='border: 0'>
									</a></td>
								</tr>
							</s:iterator>
						</tbody>
					</table>
				</div>
			</td>
		</s:if>
		<s:else>
			<br>
			<span style="color: red;">User Id / Email Id does not exists.</span>
		</s:else>
	</tr>
</table>