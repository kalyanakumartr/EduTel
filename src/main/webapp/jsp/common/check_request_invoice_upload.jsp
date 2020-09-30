<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Check Request upload</title>
<script type="text/javascript">
var myWidth = 0, myHeight = 0;
function loadCompleted()
{ 
	if( typeof( window.innerWidth ) == 'number' ) {
		//Non-IE
		myWidth = window.innerWidth;
		myHeight = window.innerHeight;
	} else if( document.documentElement && ( document.documentElement.clientWidth || document.documentElement.clientHeight ) ) {
		//IE 6+ in 'standards compliant mode'
		myWidth = document.documentElement.clientWidth;
		myHeight = document.documentElement.clientHeight + 75;
	} else if( document.body && ( document.body.clientWidth || document.body.clientHeight ) ) {
		//IE 4 compatible
		myWidth = document.body.clientWidth;
		myHeight = document.body.clientHeight + 75;
	}
	//  window.alert( 'Width = ' + myWidth );
	//  window.alert( 'Height = ' + myHeight );
	
}

function uploadImg(){
	if(chkType()) {
		$('#upload').attr("disabled", true);
		$('#upload').attr("style","color:gray");

		var page = $('#pageUrl').val();
		loadCompleted();
		var size='width='+myWidth+",height="+ myHeight;
		
		document.forms["uploadImage"].action="submitCRInvoice.do?page="+page;
		document.forms["uploadImage"].onsubmit= window.open('','newwin',size+',top=0,left=0,menubar=0,resizable=yes,status=0,location=no,toolbar=0,scrollbars=0');
		document.forms["uploadImage"].submit();

		$('#dialog-modal').dialog("close");
	}
}
function chkType(){
	
	var clear = "";
	var ext = $('#uploadImage').val().split('.').pop().toLowerCase();
	if($.inArray(ext, ['tiff','tif']) == -1) {
		$('#uploadImage').val(clear);
	    alert('Invalid Image File Format!\n\nPlease select valid Image format (TIFF or tif).');
	    return false;
	}
	return true;	
}

</script>
<style type="text/css">
.btnstyle1 {
	color: #3E3E3E;
	text-decoration: none;
	background-image: url(../images/APWAS/btnbg.gif);
	background-repeat: repeat-x;
	background-position: center;
	border: 1px solid #cbcbcb;
	margin: 0px;
	padding-top: 2px;
	padding-right: 4px;
	padding-bottom: 2px;
	padding-left: 4px;
	background-color: #FFFFFF;
}

input.file {
	position: relative;
	font-family: Tahoma;
	font-size: 11px;
	font-weight: normal;
	color: #000000;
	text-align: left;
	border: 1px solid #cbcbcb;
}
</style>
</head>
<body>
	<s:if test="hasActionErrors()">
		<div style="width: 60%; margin: 100px;">
			<table border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td align="center" width="100%" style="color: red" colspan="4"><s:iterator
							value="actionErrors">
							<span class="errorMessage"> <s:property escape="false" />
							</span>
						</s:iterator></td>
				</tr>
				<tr>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td align="center"><a href="javascript:void(0);"
						onclick="self.close();">Close</a></td>
				</tr>
			</table>
		</div>
	</s:if>
	<s:else>
		<s:form name="uploadImage" action="submitCRInvoice.do" method="post"
			enctype="multipart/form-data" target="newwin">
			<table width="100%">
				<tr>
					<td><input type="hidden" id="pageUrl" name="pageUrl"
						value="<s:property value="#parameters['page'][0]"/>" /></td>
					<td><s:file id="uploadImage" name="uploadImage"
							cssClass="file" label="Upload Invoice"
							onchange="return chkType();" /></td>
				</tr>
				<tr>&nbsp;
				</tr>
				<tr>
					<td align="center" colspan="4" style="border: 0;"><input
						type="button" class="btnstyle1"
						style="font-family: Arial, Helvetica, sans-serif; font-size: 12px; font-weight: bold;"
						id="upload" name="upload" value="Upload" onclick="uploadImg();" /></td>
				</tr>
			</table>
		</s:form>
	</s:else>
</body>
</html>