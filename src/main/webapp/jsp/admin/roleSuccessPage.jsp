<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@ include file="/jsp/common/init.jsp"%>
</head>
<body style="background: url('') no-repeat scroll right center #b3d9ff;">
	<table width="100%" border="0">
		<tr>
			<td height="100px;"></td>
		</tr>
		<tr>
			<td align="center"><span
				style="color: chocolate; font-weight: bold; font-family: Tahoma;">
					<%out.println(request.getAttribute("status"));%>
			</span></td>
		</tr>
		<tr>
			<td>&nbsp;</td>
		</tr>
		<tr>
			<td>&nbsp;</td>
		</tr>
		<tr>
			<td align="center"><input type="button" class="btnstyle"
				id="close" name="close" value="Close" onclick="self.close();" /></td>
		</tr>
	</table>
</body>

</html>