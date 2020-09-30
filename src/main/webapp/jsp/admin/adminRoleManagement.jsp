<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@ include file="/jsp/common/header.jsp"%>
<title>Role Management</title>
<%@ include file="/jsp/common/init.jsp"%>
<style type="text/css">
.lblspan {
	font-family: Tahoma;
	font-size: 11px;
	font-weight: bold;
}

.textbox {
	font-family: Tahoma;
	font-size: 11px;
	font-weight: normal;
	color: #000000;
	text-decoration: none;
	width: 165px;
}

.divStyle {
	top: 25px;
	margin: 50px 15px 15px 200px;
	background-color: #FFFFFF;
	width: 65%;
}

.tabStyle {
	padding: 0px;
	width: 100%;
	margin: 0px;
	font-size: 13px;
	background-color: #316099;
	color: #ffffff;
	font-family: Tahoma;
}
</style>
<script type="text/javascript">

function searchUser(){
	var userId = $("#searchString").val();
	if(userId=='' && userId!=null){
		alert("Enter User Id / Email Id");
		return false;
	}
	else{
		var actionUrl = "getUserRoles.do?userId="+userId;
		$("#userRolesDiv").load(actionUrl);
		$("#searchString").val('');
	}
}
function pressenter(e)
{
	var keynum;
	
	if(window.event) // IE
		{
		keynum = e.keyCode;
		}
	else if(e.which) // Netscape/Firefox/Opera
		{
		keynum = e.which;
		}
	if (keynum == 13)
	{
		searchUser();
		return false;
	}
	else
		return true;

}
function editUserRoles(userId){
  	var w=300;
  	var h=350;
  	var left = (screen.width/2)-(w/2);
	var top = (screen.height/2)-(h/2);
	MM_openBrWindow('getAvailRolesList.do?userId='+userId,'','toolbars=no,menubar=no,location=no,scrollbars=yes,resizable=yes,status=yes,width='+w+',height='+h+',top='+top+',left='+left) 

}

function MM_openBrWindow(theURL,winName,features) { 
  window.open(theURL,winName,features);
}
</script>
</head>
<body>
	<form name="input" action="" method="post">
		<div id="popupContact" class="divStyle">
			<div style="border: 0px solid rgb(183, 183, 183);">
				<table class="tabStyle">
					<tr>
						<td align="center"><b>Role Management</b></td>
					</tr>
				</table>
				<table width="100%" border="0">
					<tr>
						<td width="100%" valign="top">
							<table id="outlineItemTab" width="100%" border="0">
								<tr>
									<td valign="top" class="lblspan">User Id/Email Id <span
										style="color: red;">*</span>&nbsp; <input name="searchString"
										id="searchString" type="text" class="textbox"
										onKeyPress="return pressenter(event)"
										onkeyup="adminNameEmailValidation(this);" />&nbsp;&nbsp; <input
										name="button1" type="button" class="btnstyle" id="button1"
										value="Search" onclick="searchUser();" />&nbsp;&nbsp;
									</td>
								</tr>
							</table>
						</td>
					</tr>
					<tr>
						<td>
							<div id="userRolesDiv" style="overflow: auto; height: auto;"></div>
						</td>
					</tr>
				</table>
			</div>
		</div>
	</form>
	<%@ include file="/jsp/common/i_footer.jsp"%>
</body>
</html>