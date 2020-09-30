<%@ taglib prefix="s" uri="/struts-tags"%>
<table id="outlineItemTab" width="100%" border="0" class="formtext">
	<tr>
		<td colspan="5">
			<div id="userDiv"
				style="border: 0px solid #b7b7b7; overflow: auto; height: auto;">
				<jmesa:tableModel id="userdetails" items="${userList}" maxRows="10"
					var="bean" autoFilterAndSort="false"
					toolbar="org.jmesa.view.html.toolbar.HtmlToolbar"
					stateAttr="restore">
					<s:set var='status'>${bean.usStatus}</s:set>
					<s:set var='ntLogin'>${bean.usNTUser}</s:set>
					<jmesa:htmlTable width="100%">
						<jmesa:htmlRow>
							<jmesa:htmlColumn property="usEmployeeId" title="Employee Id"
								width="10%" filterable="false" style="text-align: left;" />
							<jmesa:htmlColumn property="usUserName" title="User Name"
								width="25%" filterable="false" style="text-align: left;" />
							<jmesa:htmlColumn property="usUserId" title="User Id" width="35%"
								filterable="false" style="text-align: left;" />
							<jmesa:htmlColumn title="Edit User" sortable="false"
								filterable="false" width="10%" style="text-align: center;">
								<a href="javascript:void(0);" style="text-decoration: none;"
									onclick="editUserDetails('${bean.usEmployeeId}','<s:property value="#attr.bean.usUserName" escapeJavaScript="true"/>','<s:property value="#attr.bean.usUserId" escapeJavaScript="true"/>',${bean.usStatus})">
									<img src='images/png/edit.png' title='Edit User'
									alt='Edit User' style='border: 0' />
								</a>&nbsp;
							</jmesa:htmlColumn>
							<jmesa:htmlColumn property="" title="Edit Roles" sortable="false"
								filterable="false" width="10%" style="text-align: center;">
								<s:if test='#status'>
									<a href="javascript:void(0);" style="text-decoration: none;"
										onclick="editUserRoles('<s:property value="#attr.bean.usEmployeeId" escapeJavaScript="true"/>')">
										<img src='images/png/edit_role.png' title='Edit Roles'
										alt='Edit Roles' style='border: 0' />
									</a>&nbsp;
												</s:if>
							</jmesa:htmlColumn>
							<jmesa:htmlColumn property=" " title="Reset Password"
								sortable="false" filterable="false" width="10%"
								style="text-align: center;">
								<s:if test="#status=='true' && #ntLogin=='false'">
									<a href="javascript:void(2);" style="text-decoration: none;"
										onclick="updateUserPwdDetails('<s:property value="#attr.bean.usUserId" escapeJavaScript="true"/>')">
										<img src='images/png/password.png' title='Reset Password'
										alt='Reset Password' style='border: 0' />
									</a>
								</s:if>
								<s:else>
									<a
										href="javascript:alert('User Cannot Reset NT Authentication Password.\n\n\nPlease contact your Administrator.');"
										style="text-decoration: none;"><img
										src='images/png/passwordDisabled.png'
										title='User Cannot Reset NT Authentication Password'
										alt='NT Password' style='border: 0' /></a>
								</s:else>
							</jmesa:htmlColumn>
						</jmesa:htmlRow>
					</jmesa:htmlTable>
				</jmesa:tableModel>
			</div>
		</td>
	</tr>
</table>
