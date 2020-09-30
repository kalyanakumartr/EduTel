
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page import="com.hbs.edutel.util.common.consts.ConstInterface"%>
<s:set name="DISPLAY_TIME_ZONE">IST</s:set>
<s:set name="attachReadOnly">${param.attachReadOnly}</s:set>
<s:set name="attachmentBannerName">${param.attachmentBannerName}</s:set>
<s:set name="attachmentName">${param.attachmentName}</s:set>
<s:set name="attachmentDynamicName">${param.attachmentDynamicName}</s:set>
<s:set name="attachmentUpdate">${param.attachmentUpdate}</s:set>
<style>
div.fileinputs {
	position: relative;
	opacity: 100%;
}

div.fakefile {
	position: absolute;
	top: 0px;
	left: 0px;
	z-index: 1;
}

input.file {
	font-family: Tahoma;
	font-size: 11px;
	font-weight: normal;
	color: #000000;
	width: 400px;
	text-align: left;
	border: 1px solid #cbcbcb;
	-moz-opacity: 0;
	filter: alpha(opacity : 0%);
	opacity: 0%;
	z-index: 2;
	text-align: left;
	border: 1px solid #cbcbcb;
	-moz-opacity: 0;
	filter: alpha(opacity : 0%);
}

.toggelCSS {
	background: url('images/Login/button-bg.png') repeat-x top center;
	border: 1px solid #D3D3D3;
	color: #FFFFFF;
	font-weight: bold;
	font-family: Tahoma;
	font-size: 100%;
	line-height: 1.3;
	list-style: none outside none;
	margin: 0;
	outline: 0 none;
	padding: 0;
	text-decoration: none;
	cursor: pointer;
	margin-top: 1px;
}
</style>
<script>
	$(document).ready(function() {
		if ('${attachmentName}' != '') {
			$('#attachmentArrow').hide();
			$('#btnAddImgUpload').hide();
			$('#btnDelImgUpload').hide();
			$('#fileSizeSpan').hide();
		}
	});
	var reqCnt = 2;
	var fileCnt = 1;
	var tempDays = 0;

	function addUploadRow() {
		try {
			var filePath = "";

			if (fileCnt > 0)
				filePath = document.getElementById('fileUpload' + fileCnt).value;

			if (fileCnt == 0
					|| (fileCnt < 5 && filePath != undefined
							&& filePath != null && allTrim(filePath) != '')) {
				var tableObj = document.getElementById("tblRequest");
				var tablerows = tableObj.rows.length;
				var newRow = tableObj.insertRow(tablerows);

				oCell = newRow.insertCell(0);
				var strHtml1 = "&nbsp;";
				oCell.innerHTML = strHtml1;

				oCell = newRow.insertCell(1);
				var strHtml2 = "<div class=\'fileinputs\' id=\'fileinputs" + reqCnt + "\'><input type=\'file\' name=\'uploadFiles\' id=\'fileUpload"
						+ reqCnt
						+ "\' size='40' class='file' onkeypress='return false;' onkeydown='return false;' onchange='return checkDocumentType(fileUpload"
						+ reqCnt + "," + reqCnt + ");'/></div>";

				oCell.innerHTML = strHtml2;

				oCell = newRow.insertCell(2);
				var strHtml3 = "&nbsp;&nbsp;&nbsp;<img SRC='images/png/cancel_48.png' BORDER='0'ALT='' style='cursor: pointer;' onclick='javascript:deleteRequestUpload(this)' title='Remove file'>";
				oCell.innerHTML = strHtml3;
				fileCnt++;
				reqCnt++;
			}
		} catch (e) {
		}
	}

	function checkDocumentType(str, count) {
		
		var filterlist = [ 'doc', 'docx', 'pdf','zip','7z','rar'];
		var msg = 'Invalid File Format!\n\nPlease select valid file format(doc, docx, pdf,zip,7z,rar).' ;
		if('${attachmentName}'.indexOf("Image") > -1 )
		{
			filterlist = [ 'tiff', 'tif', 'jpg', 'jpeg' ,'png'];
			msg = 'Invalid File Format!\n\nPlease select valid file format(tiff, tif, jpg, jpeg ,png).';
		}
		var ext = str.value.split('.').pop().toLowerCase();
		if ($.inArray(ext, filterlist) == -1) {
			u = document.getElementById('fileinputs' + count);
			u.innerHTML = "<input type=\'file\' name=\'uploadFiles\' id=\'"
					+ str.id
					+ "\' size='40' class='file' "
					+ "onkeypress='return false;' onkeydown='return false;' onchange='return checkDocumentType("
					+ str.id + "," + count + ");'/>";
			alert(msg);
			return false;
		}
		return true;
	}

	function deleteRequestUpload(src) {

		var oRow = src.parentNode.parentNode;
		// once the row reference is obtained, delete it passing in its rowIndex
		document.getElementById("tblRequest").deleteRow(oRow.rowIndex);
		fileCnt--;
		reqCnt--;
	}
</script>
<div id="edutelAttach" style="padding: 0px; width: 100%;">
	<div id="commentInlineheader">
		<table class="toggelCSS" style="width: 100%">
			<tr class="edutelAttachToggle" height="25" valign="bottom">
				<td width="97%" valign="middle"><span
					style="font-size: 12px; font-weight: bold;">&nbsp;&nbsp;${attachmentBannerName}</span>
					<s:if test="#attachmentDynamicName !=''">
						<span style="font-size: 11px;">(<s:property
								value="attachmentList.size" />)
						</span>
					</s:if></td>
				<td width="03%"><img id="attachmentArrow"
					class="edutelAttachToggle" src="images/png/arrow-down.png" /></td>
			</tr>
		</table>
	</div>
	<div id="edutelAttachContent" class="edutelAttachContent">
		<s:if test="#attachReadOnly !='true'">
			<table id="tblRequest" width="100%" border="0" class="backColor">
				<tr>
					<td class="txtLabel" nowrap="nowrap" width="10%" height="20">&nbsp;</td>
					<td class="txtLabel" nowrap="nowrap" width="40%" height="20"><span
						id="fileSizeSpan" class="lblstyle" style="text-align: middle;">Max
							2MB Size and Max 5 Files</span></td>
					<td align="left" width="50%" height="20">&nbsp;&nbsp;&nbsp;<img
						src="images/png/plus.png" style="cursor: pointer"
						id="btnAddImgUpload" onclick="javascript:addUploadRow()"
						title="Add files" width="20px" style="vertical-align: bottom;"></td>
				</tr>
				<tr>
					<td class="txtLabel" nowrap="nowrap" width="10%" height="22"><span
						id="fileSizeSpan" class="lblstyle">&nbsp;&nbsp;&nbsp;${attachmentName}</span></td>
					<td width="20%">
						<div class="fileinputs" id="fileinputs1">
							<input type="file" name="uploadFiles" id="fileUpload1" size="40"
								class="file" onchange="return checkDocumentType(this,1);" />
						</div>
					</td>
					<td width="70%" align="left" valign="bottom">&nbsp;&nbsp;&nbsp;<img
						src="images/png/minus.png" id="btnDelImgUpload" width="25px"
						style="cursor: pointer; vertical-align: bottom;"
						onclick="javascript:deleteRequestUpload(this)" title="Remove file"></td>
				</tr>
			</table>
		</s:if>
		<s:if test="#attachmentUpdate=='true'">
			<table id="commentInlineItemTab" style="padding: 0px; width: 100%"
				class="backColor">
				<colgroup>
					<col width="20%" />
					<col width="50%" />
					<col width="30%" />
				</colgroup>
				<thead>
					<tr style="background-color: #efefef" height="22">
						<td align="center" valign="middle"><span
							style="font-size: 11px;">Date</span></td>
						<td align="center" valign="middle"><span
							style="font-size: 11px;">${attachmentName}</span></td>
						<td align="center" valign="middle"><span
							style="font-size: 11px;">Uploaded By</span></td>
					</tr>
				</thead>
				<tbody>
					<s:set name="listSize" value="attachmentList.size" />
					<s:if test="#listSize > 0 && #attachmentUpdate=='true'">
						<s:iterator value="attachmentList" var="attList">
							<tr>
								<td align="center"><span style="font-size: 11px;"><s:date
											name="uploadFileDate" format="MM-dd-yyyy hh:mm a"
											timezone="%{DISPLAY_TIME_ZONE}" /></span></td>
								<td align="center"><span style="font-size: 11px;"><a
										href="javascript:void(0);"
										onclick="openEduTelDocument('View','${uploadFileNamePrimaryId}')">
											<s:property value="uploadFileName" />
									</a></span></td>
								<td align="center"><span style="font-size: 11px;"><s:property
											value="createdBy" /></span></td>
							</tr>
						</s:iterator>
					</s:if>
					<s:else>
						<tr>
							<td align="center" colspan="4"><span
								style="font-size: 11px;">No ${attachmentBannerName} found</span></td>
						</tr>
					</s:else>
					<tr>
						<td align="center" colspan="4"><span style="font-size: 11px;">&nbsp;</span></td>
					</tr>
				</tbody>
			</table>
		</s:if>
	</div>
	<br>
</div>
