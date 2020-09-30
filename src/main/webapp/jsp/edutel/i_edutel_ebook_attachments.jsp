<%@ taglib prefix="s" uri="/struts-tags"%>
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
	color: #000000;
	width: 400px;
	text-align: left;
	z-index: 2;
	text-align: left;
	height: 25px;
	position: relative;
	left: 0;
	outline: none;
	border: 2px solid #cdcdcd;
	border-color: rgba(0, 0, 0, .15);
	background-color: white;
	font-family: Tahoma, Arial, Verdana;
	font-size: 11px;
	-webkit-border-radius: 2px;
	-moz-border-radius: 2px;
	border-radius: 2px;
	vertical-align: middle;
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
	var reqCnt = 2;
	var fileCnt = 1;
	var tempDays = 0;

	function addUploadRow() {
		try {
			var filePath = "";

			if (fileCnt > 0)
				filePath = document.getElementById('fileUpload' + fileCnt).value;

			if (fileCnt == 0
					|| (fileCnt < 10 && filePath != undefined
							&& filePath != null && allTrim(filePath) != '')) {
				var tableObj = document.getElementById("tblRequest");
				var tablerows = tableObj.rows.length;
				var newRow = tableObj.insertRow(tablerows);

				oCell = newRow.insertCell(0);
				var strHtml1 = "<span id=\"fileSizeSpan\" class=\"lblstyle\">&nbsp;&nbsp;&nbsp;${attachmentName}&nbsp;&nbsp;</span><input type=\'text\' name=\'uploadFileDisplayName\' id=\'uploadFileDisplayName" + reqCnt + "\' />";
				oCell.innerHTML = strHtml1;

				oCell = newRow.insertCell(1);
				var strHtml2 = "<select name=\"oeChapter\" id=\"oeChapter" + reqCnt + "\" style=\"width:100px;vertical-align: middle;padding:0px;\"><option value=\"\">Chapter All</option><option value=\"1\">Chapter I</option><option value=\"2\">Chapter II</option><option value=\"3\">Chapter III</option><option value=\"4\">Chapter IV</option><option value=\"5\">Chapter V</option><option value=\"6\">Chapter VI</option><option value=\"7\">Chapter VII</option><option value=\"8\">Chapter VIII</option><option value=\"9\">Chapter IX</option><option value=\"10\">Chapter X</option><option value=\"11\">Chapter XI</option><option value=\"12\">Chapter XII</option><option value=\"13\">Chapter XIII</option><option value=\"14\">Chapter XIV</option><option value=\"15\">Chapter XV</option><option value=\"16\">Chapter XVI</option><option value=\"17\">Chapter XVII</option><option value=\"18\">Chapter XVIII</option><option value=\"19\">Chapter XIX</option><option value=\"20\">Chapter XX</option><option value=\"21\">Chapter XXI</option><option value=\"22\">Chapter XXII</option><option value=\"23\">Chapter XXIII</option><option value=\"24\">Chapter XXIV</option><option value=\"25\">Chapter XXV</option></select>";
				oCell.align = "center";
				oCell.innerHTML = strHtml2;

				oCell = newRow.insertCell(2);
				var strHtml3 = "<div class=\'fileinputs\' id=\'fileinputs" + reqCnt + "\'><input type=\'file\' name=\'uploadFiles\' id=\'fileUpload"
						+ reqCnt
						+ "\' class='file' onkeypress='return false;' onkeydown='return false;' onchange='return checkDocumentType(fileUpload"
						+ reqCnt + "," + reqCnt + ");'/></div>";

				oCell.innerHTML = strHtml3;

				oCell = newRow.insertCell(3);
				var strHtml4 = "&nbsp;&nbsp;<img SRC='images/png/cancel_48.png' BORDER='0'ALT='' style='cursor: pointer;' onclick='javascript:deleteRequestUpload(this)' title='Remove file' height='20' width='20'>";
				oCell.innerHTML = strHtml4;
				fileCnt++;
				reqCnt++;
			}
		} catch (e) {
		}
	}

	function checkDocumentType(str, count) {
		var ext = str.value.split('.').pop().toLowerCase();
		if ($.inArray(ext, [ 'doc', 'docx', 'pdf' ]) == -1) {
			u = document.getElementById('fileinputs' + count);
			u.innerHTML = "<input type=\'file\' name=\'uploadFiles\' id=\'"
					+ str.id
					+ "\' size='40' class='file' "
					+ "onkeypress='return false;' onkeydown='return false;' onchange='return checkDocumentType("
					+ str.id + "," + count + ");'/>";
			alert('Invalid File Format!\n\nPlease select valid file format(Pdf / Docx / Doc).');
			return false;
		}
		return true;
	}

	function deleteRequestUpload(src) {

		var oRow = src.parentNode.parentNode;
		// once the row reference is obtained, delete it passing in its rowIndex
		if (oRow.rowIndex > 1) {
			document.getElementById("tblRequest").deleteRow(oRow.rowIndex);
			fileCnt--;
			reqCnt--;
		}

	}
</script>
<div id="edutelAttach" style="width: 100%;">
	<div id="commentInlineheader">
		<table class="toggelCSS" style="width: 100%">
			<tr class="edutelAttachToggle" height="25" valign="bottom">
				<td width="97%" valign="middle"><span style="font-size: 12px; font-weight: bold;">&nbsp;&nbsp;${param.attachmentBannerName}</span></td>
				<td width="03%">&nbsp;</td>
			</tr>
		</table>
	</div>
	<div id="edutelAttachContent" class="edutelAttachContent">
		<table id="tblRequest" width="100%" border="0" class="backColor">
			<tr>
				<td class="txtLabel" nowrap="nowrap" width="50%" height="22" colspan="3" align="center"><span id="fileSizeSpan" class="lblstyle" style="text-align: middle;">Max
						5MB Size and Max 10 Files</span></td>
				<td class="txtLabel" nowrap="nowrap" width="30%" height="22">&nbsp;&nbsp;<img src="images/png/plus.png"
					style="cursor: pointer; vertical-align: bottom;" id="btnAddImgUpload" onclick="javascript:addUploadRow()" title="Add files" height="20" width="20">&nbsp;&nbsp;
				</td>
			</tr>
			<tr>
				<td nowrap="nowrap" width="25%"><span id="fileSizeSpan" class="lblstyle">&nbsp;&nbsp;&nbsp;${attachmentName}&nbsp;&nbsp;</span><input type="text"
					name="uploadFileDisplayName" id="uploadFileDisplayName1"></td>
				<td nowrap="nowrap" width="25%" align="center"><select name="oeChapter" id="oeChapter1" style="width: 100px; vertical-align: middle; padding: 0px;">
						<option value="">Chapter All</option>
						<option value="1">Chapter I</option>
						<option value="2">Chapter II</option>
						<option value="3">Chapter III</option>
						<option value="4">Chapter IV</option>
						<option value="5">Chapter V</option>
						<option value="6">Chapter VI</option>
						<option value="7">Chapter VII</option>
						<option value="8">Chapter VIII</option>
						<option value="9">Chapter IX</option>
						<option value="10">Chapter X</option>
						<option value="11">Chapter XI</option>
						<option value="12">Chapter XII</option>
						<option value="13">Chapter XIII</option>
						<option value="14">Chapter XIV</option>
						<option value="15">Chapter XV</option>
						<option value="16">Chapter XVI</option>
						<option value="17">Chapter XVII</option>
						<option value="18">Chapter XVIII</option>
						<option value="19">Chapter XIX</option>
						<option value="20">Chapter XX</option>
						<option value="21">Chapter XXI</option>
						<option value="22">Chapter XXII</option>
						<option value="23">Chapter XXIII</option>
						<option value="24">Chapter XXIV</option>
						<option value="25">Chapter XXV</option>
				</select></td>
				<td width="45%">
					<div class="fileinputs" id="fileinputs1">
						<input type="file" name="uploadFiles" id="fileUpload1" class="file" onchange="return checkDocumentType(this,1);" />
					</div>
				</td>
				<td width="5%" align="left" valign="bottom">&nbsp;&nbsp;<img src="images/png/cancel_48.png" id="btnDelImgUpload"
					style="cursor: pointer; vertical-align: bottom;" onclick="javascript:deleteRequestUpload(this)" title="Remove file" height="20" width="20">&nbsp;&nbsp;
				</td>
			</tr>
		</table>
	</div>
	<br>
</div>
