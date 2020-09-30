<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page import="com.hbs.edutel.util.common.consts.ConstInterface"%>
<s:set name="DISPLAY_TIME_ZONE">IST</s:set>
<style>
div.fileinputs {
	vertical-align: middle;
	opacity: 100%;
	padding: 5px;
}

div.fakefile {
	vertical-align: middle;
	top: 0px;
	left: 0px;
	z-index: 1;
}

input.file {
	vertical-align: middle;
	font-family: Tahoma;
	font-size: 11px;
	font-weight: normal;
	color: #000000;
	width: 350px;
	text-align: left;
	border: 1px solid #cbcbcb;
	-moz-opacity: 0;
	filter: alpha(opacity :               0%);
	opacity: 0%;
	z-index: 2;
	text-align: left;
	border: 1px solid #cbcbcb;
	height: 50px;
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
	var reqCnt = 6;
	var fileCnt = 5;

	function addUploadRow${questionNo}() 
	{
		try 
		{
			
			var filePath = "";
			if (fileCnt > 0)
				filePath = document.getElementById('fileUpload${questionNo}' + fileCnt).value;

			if (fileCnt == 0 || fileCnt < 8)
			{
				var tableObj = document.getElementById("tblRequest${questionNo}");
				var tablerows = tableObj.rows.length;
				var newRow = tableObj.insertRow(tablerows);

				oCell = newRow.insertCell(0);
				var strHtml1 = "&nbsp;&nbsp;&nbsp;&nbsp;<textarea name=\'oeAnswerText\' id=\'oeAnswerText${questionNo}" + reqCnt + "\'  onchange=\"setReadOnlyImageField${questionNo}(this," + reqCnt + ")\" style=\"width:300px;height: 50px;resize:none;\"></textarea>";
				oCell.innerHTML = strHtml1;

				oCell = newRow.insertCell(1);
				var strHtml2 = "<div class=\'fileinputs\' id=\'fileinputs${questionNo}" + reqCnt + "\'>&nbsp;&nbsp;&nbsp;&nbsp;<input type=\'file\' name=\'uploadFiles\' id=\'fileUpload${questionNo}"
						+ reqCnt
						+ "\' size='40' class='file' onkeypress='return false;' onkeydown='return false;' onchange='return checkDocumentType${questionNo}(fileUpload${questionNo}"
						+ reqCnt + "," + reqCnt + ");'/></div>";

				oCell.innerHTML = strHtml2;

				oCell = newRow.insertCell(2);
				var strHtml3 = "&nbsp;&nbsp;<img src='images/png/cancel_48.png' border='0'alt='' style='cursor: pointer;' onclick='javascript:deleteRequestUpload${questionNo}(this)' title='Remove file' height='22' width='22'>&nbsp;&nbsp;";
				oCell.innerHTML = strHtml3;
				fileCnt++;
				reqCnt++;
			}
		} catch (e) {
		}
	}

	function checkDocumentType${questionNo}(str, count) 
	{
		var ext = str.value.split('.').pop().toLowerCase();
		if ($.inArray(ext, [ 'jpg', 'jpeg', 'gif','tiff','png' ]) == -1) {
			u = document.getElementById('fileinputs${questionNo}' + count);
			u.innerHTML = "<input type=\'file\' name=\'uploadFiles\' id=\'"
					+ str.id
					+ "\' size='40' class='file' "
					+ "onkeypress='return false;' onkeydown='return false;' onchange='return checkDocumentType${questionNo}("
					+ str.id + "," + count + ");'/>";
			alert('Invalid File Format!\n\nPlease select valid file format( jpg /jpeg / gif / tiff / png ).');
			return false;
		}
		return true;
	}

	function deleteRequestUpload${questionNo}(src) 
	{

		var tableObj = document.getElementById("tblRequest${questionNo}");
		var tablerows = tableObj.rows.length;
		
		var oRow = src.parentNode.parentNode;
		// once the row reference is obtained, delete it passing in its rowIndex
		if (oRow.rowIndex > 4 && tablerows > 5) 
		{
			document.getElementById("tblRequest${questionNo}").deleteRow(oRow.rowIndex);
			fileCnt--;
			reqCnt--;
		}

	}
	
	function setReadOnlyImageField${questionNo}(object,count)
	{
		if(allTrim(object.value) !='')
		{
			var oeFileUpload = "fileUpload${questionNo}" + count ;
			$("#"+ oeFileUpload).val("");
		}
	}
</script>
<div id="edutelAttach${questionNo}" style="width: 100%;">
	<div id="commentInlineheader${questionNo}">
		<table class="toggelCSS" style="width: 100%">
			<tr class="edutelAttachToggle" height="25" valign="bottom">
				<td width="97%" valign="middle"><span
					style="font-size: 12px; font-weight: bold;">&nbsp;&nbsp;Question&nbsp;</span></td>
				<td width="03%">&nbsp;</td>
			</tr>
		</table>
	</div>
	<div id="edutelAttachContent${questionNo}" class="edutelAttachContent"
		style="background-color: #ececec;">
		<table id="tblRequest${questionNo}" width="100%" border="1"
			class="backColor" rules="rows" frame="hsides">
			<tr>
				<td class="txtLabel" nowrap="nowrap" width="90%" colspan="2"
					valign="middle">
					<table width="99%">
						<tr>
							<td valign="middle" width="5%"><br> <span
								id="fileSizeSpan" class="lblstyle"
								style="text-align: middle; valign: middle;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Question&nbsp;</span></td>
							<td valign="middle" width="50%"><br>&nbsp;&nbsp;<textarea
									name="onlineExamQuestion.oeQuestion"
									id="oeQuestion${questionNo}1"
									style="width: 100%; height: 50px; resize: none; overflow: auto;"></textarea></td>
							<td valign="middle" width="45%">&nbsp;&nbsp;
								<div class="fileinputs" id="fileinputs${questionNo}0">
									&nbsp;&nbsp;&nbsp; <input type="file" name="uploadQuestionFile"
										id="fileUpload${questionNo}0" size="40" class="file"
										onchange="return checkDocumentType(this,0);" />
								</div>
							</td>
						</tr>
					</table>
				</td>
				<td class="txtLabel" nowrap="nowrap" width="10%">&nbsp;&nbsp;</td>
			</tr>
			<tr>
				<td class="txtLabel" nowrap="nowrap" width="90%" height="22"
					colspan="2" valign="middle">
					<table width="99%">
						<tr>
							<td valign="middle" width="5%"><br> <span
								id="fileSizeSpan" class="lblstyle"
								style="text-align: middle; valign: middle;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Explanation&nbsp;</span></td>
							<td valign="middle" width="50%"><br>&nbsp;&nbsp;<textarea
									name="onlineExamQuestion.oeExplanation" id="oeExplanation"
									style="width: 100%; height: 50px; resize: none; overflow: auto;"></textarea></td>
							<td valign="middle"><br>
								<div class="fileinputs" id="fileinputs${questionNo}10">
									&nbsp;&nbsp;&nbsp; <input type="file"
										name="uploadExplanationFile" id="fileUpload${questionNo}10"
										size="40" class="file"
										onchange="return checkDocumentType(this,10);" />
								</div></td>
						</tr>
						<tr>
							<td class="txtLabel" nowrap="nowrap">&nbsp;&nbsp;</td>
						</tr>
					</table>
				</td>
				<td class="txtLabel" nowrap="nowrap" width="10%">&nbsp;&nbsp;&nbsp;</td>
			</tr>
			<tr>
				<td class="txtLabel" nowrap="nowrap" width="90%" height="22"
					colspan="2" valign="middle">
					<table>
						<tr>
							<td valign="middle"><span id="fileSizeSpan" class="lblstyle"
								style="text-align: middle;"><br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Correct
									Answer&nbsp;</span><select name="onlineExamQuestion.oeCorrectAnswer"
								id="onlineExamQuestion.oeCorrectAnswer" style="width: 100px;">
									<option value="1">Answer 1</option>
									<option value="2">Answer 2</option>
									<option value="3">Answer 3</option>
									<option value="4">Answer 4</option>
									<option value="">None of these</option>
							</select></td>
							<td valign="middle"><span id="fileSizeSpan" class="lblstyle"
								style="text-align: middle;"><br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Mark
									per Answer&nbsp;</span><select
								name="onlineExamQuestion.oeMarkPerAnswer"
								id="onlineExamQuestion.oeMarkPerAnswer" style="width: 50px;">
									<option value="1">1</option>
									<option value="2">2</option>
									<option value="5">5</option>
									<option value="10">10</option>
							</select></td>
							<td valign="middle"><span id="fileSizeSpan" class="lblstyle"
								style="text-align: middle;">&nbsp;</span></td>
							<td valign="middle"><span id="fileSizeSpan" class="lblstyle"
								style="text-align: middle;">&nbsp;</span></td>
						</tr>
						<tr>
							<td class="txtLabel" nowrap="nowrap">&nbsp;&nbsp;</td>
						</tr>
					</table>
				</td>
				<td class="txtLabel" nowrap="nowrap" width="10%">&nbsp;&nbsp;&nbsp;</td>
			</tr>
			<tr>
				<td class="txtLabel" nowrap="nowrap" width="40%"><span
					id="fileSizeSpan" class="lblstyle">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Answer
						Text</span></td>
				<td class="txtLabel" nowrap="nowrap" width="50%"><span
					id="fileSizeSpan" class="lblstyle">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Answer
						Image&nbsp;&nbsp;</span></td>
				<td class="txtLabel" nowrap="nowrap" width="10%" height="22"
					align="center">&nbsp;&nbsp;<img src="images/png/plus.png"
					style="cursor: pointer; vertical-align: bottom;"
					id="btnAddImgUpload${questionNo}"
					onclick="javascript:addUploadRow${questionNo}()" title="Add files"
					height="22" width="22">&nbsp;&nbsp;
				</td>
			</tr>
			<tr>
				<td align="left" nowrap="nowrap" colspan="3">&nbsp;&nbsp;&nbsp;</td>
			</tr>
			<tr>
				<td class="txtLabel" nowrap="nowrap" width="40%">&nbsp;&nbsp;&nbsp;
					<textarea name="oeAnswerText" id="oeAnswerText${questionNo}5"
						onkeyup="setReadOnlyImageField${questionNo}(this,5);"
						style="width: 300px; height: 50px; resize: none; overflow: auto;"></textarea>
				</td>
				<td class="txtLabel" nowrap="nowrap" width="50%"><div
						class="fileinputs" id="fileinputs${questionNo}5">
						&nbsp;&nbsp;&nbsp; <input type="file" name="uploadFiles"
							id="fileUpload${questionNo}5" size="40" class="file"
							onchange="return checkDocumentType${questionNo}(this,5);" />
					</div></td>
				<td class="txtLabel" nowrap="nowrap" width="10%" align="center">&nbsp;&nbsp;<img
					src="images/png/cancel_48.png" id="btnDelImgUpload${questionNo}"
					style="cursor: pointer; vertical-align: bottom;"
					onclick="javascript:deleteRequestUpload${questionNo}(this)"
					title="Remove file" height="22" width="22">&nbsp;&nbsp;
				</td>
			</tr>
		</table>
	</div>
	<br>
</div>
