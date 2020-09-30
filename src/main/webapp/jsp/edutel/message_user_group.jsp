<%@ taglib prefix="s" uri="/struts-tags"%>
<s:include value="i_edutel_init.jsp" />
<link rel="stylesheet" href="css/jquery.boxes.min.css" />
<script type="text/javascript" src="js/jquery.boxes.min.js"></script>
<style>
.btnstyle {
	background: url('images/Login/button-bg.png') repeat-x top center;
	font-family: Tahoma, Arial, verdana, helvetica, sans-serif;
	font-size: 11px;
	border: 1px solid #999;
	padding: 5px;
	color: white;
	font-weight: bold;
	-webkit-border-radius: 5px;
	-moz-border-radius: 5px;
	border-radius: 5px;
	width: 90px;
}

.btnstyle:HOVER {
	background: url('images/Login/button-bg_hover.png') repeat-x top
		center;
	font-family: Tahoma, Arial, verdana, helvetica, sans-serif;
	font-size: 11px;
	border: 1px solid #999;
	padding: 5px;
	color: #336699;
	font-weight: bold;
	-webkit-border-radius: 5px;
	-moz-border-radius: 5px;
	border-radius: 5px;
	width: 90px;
}

.btnstyle:disabled {
	background: url('images/Login/button-bg_hover.png') repeat-x top
		center;
	background-color: #ebebeb;
	font-family: Tahoma, Arial, verdana, helvetica, sans-serif;
	font-size: 11px;
	border: 1px solid #999;
	padding: 5px;
	color: gray;
	font-weight: bold;
	-webkit-border-radius: 5px;
	-moz-border-radius: 5px;
	border-radius: 5px;
	width: 90px;
}
</style>
<script>
function assignMessageToNewGroup()
{
	var userGroupNameDialog = $("#userGroupNameDialog").val();
	if(allTrim(userGroupNameDialog) == "")
	{
		alert("Please enter User Group Name");
		return false;
	}
	else
	{
		 $("#userGroupNameHidden").val( $("#userGroupNameDialog").val());
		 document.forms["messagesUserForm"].submit();
	}
	
}

function assignMessageToUsers()
{
	 $("#userGroupNameHidden").val("");
	 document.forms["messagesUserForm"].submit();
}
</script>
<div id="groupDiv" align="center" style="width: 500px; padding: 25px; background-color: #035688;">
	<table width="100%">
		<tr>
			<td width="5%" valign="bottom"><img src="images/png/user_group.png" width="150px;" height="150px;" /></td>
			<td width="90%" valign="top"><table width="100%">
					<tr>
						<td>&nbsp;</td>
					</tr>
					<tr>
						<td><span class="lblStyle" style="font-size: 16px; color: #ffffff;">Group Name</span></td>
						<td><s:textfield name="userGroupNameDialog" id="userGroupNameDialog" theme="simple" cssStyle="width: 200px;font-size: 16px; color: #336699;" /></td>
					</tr>
					<tr>
						<td>&nbsp;</td>
					</tr>
					<tr>
						<td>&nbsp;</td>
					</tr>
					<tr>
						<td nowrap="nowrap" colspan="2"><input id="assignBtn" type="button" class="btnstyle" value="Proceed" onclick="assignMessageToNewGroup()"
							style="width: 150px;" />&nbsp;&nbsp;&nbsp; <input id="resetBtn" type="button" class="btnstyle" value="Skip and Continue" onclick="assignMessageToUsers()"
							style="width: 150px;" /></td>
					</tr>
					<tr>
						<td>&nbsp;</td>
					</tr>
					</tbody>
				</table></td>
			<td width="5%">&nbsp;</td>
		</tr>
		</tbody>
	</table>
</div>