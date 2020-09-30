<%@ taglib prefix="s" uri="/struts-tags"%>
<style>
div.fileinputs {
	position: relative;
	opacity: 100%;
}

input.file {
	vertical-align: middle;
	font-family: Tahoma;
	font-size: 11px;
	font-weight: normal;
	color: #000000;
	width: 100%;
	text-align: left;
	border: 1px solid #dbdbdb;
	-moz-opacity: 0;
	filter: alpha(opacity :               0%);
	opacity: 0%;
	z-index: 2;
	text-align: left;
	height: 35px;
	border: 1px solid #dbdbdb;
	-moz-opacity: 0;
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

.alert-info {
	background-color: #1d86aa;
	border-color: #1d86aa;
	color: white;
}
</style>
<script>
	var reqCnt = 1;
	var fileCnt = 0;
	var tempDays = 0;
	var processedAnswerFileId = "";
	var uploadRowIndex = 0;
	function addUploadRow() {
		try {
			var stuAnswerFileName = $("#answeredFileName option:selected")
					.text();
			var answeredFileId = $("#answeredFileName").val();
			if (allTrim(answeredFileId) != "") {
				if (processedAnswerFileId.indexOf(answeredFileId) == -1) {
					if (fileCnt == 0 || (fileCnt < 10)) {
						var tableObj = document.getElementById("tblRequest");
						var tablerows = tableObj.rows.length;
						var newRow = tableObj.insertRow(tablerows);

						oCell = newRow.insertCell(0);

						var strHtml1 = "<input name=\"answerFileName\" readonly id=\'answerFileName"
								+ reqCnt
								+ "\' type=\"text\" title=\"Answer File Name\" style=\"width: 100%; height: 34px; position: relative; left: 0.0; outline: none; ";
						strHtml1 += "background-color: white; border: 1px solid #e5e5e5; border-radius: 0; box-shadow: none; color: #333333; font-size: 14px; ";
						strHtml1 += "font-weight: normal;\" placeholder=\"Answer File Name\" class=\"form-control\">";

						oCell.innerHTML = strHtml1;

						oCell = newRow.insertCell(1);

						var strHtml2 = "<input name=\"createdBy\" readonly id=\'createdBy"
								+ reqCnt
								+ "\' type=\"text\" title=\"Created By\" style=\"width: 100%; height: 34px; position: relative; left: 0.0; outline: none; background-color: white; border: 1px solid #e5e5e5; border-radius: 0; box-shadow: none; color: #333333; font-size: 14px; font-weight: normal;\" placeholder=\"Created By\" class=\"form-control\">";
						oCell.align = "center";
						oCell.innerHTML = strHtml2;

						oCell = newRow.insertCell(2);
						var strHtml3 = "<div class=\'fileinputs\' id=\'fileinputs" + reqCnt + "\'><input type=\'file\' name=\'uploadFiles\' id=\'fileUpload"
								+ reqCnt
								+ "\' class='file' onkeypress='return false;' onkeydown='return false;' onchange='return checkDocumentType(fileUpload"
								+ reqCnt + "," + reqCnt + ");'/></div>";

						oCell.innerHTML = strHtml3;

						oCell = newRow.insertCell(3);
						var strHtml4 = "<img SRC='images/png/cancel_48.png' BORDER='0'ALT='' style='cursor: pointer;' onclick='javascript:deleteRequestUpload(this)' ";
						strHtml4 += "title='Remove file' height='20' width='20'>";
						strHtml4 += "<input type=\"hidden\" name=\"employeeId\" id=\'employeeId"+reqCnt+"\'  value=\"\" /> ";
						strHtml4 += "<input type=\"hidden\" name=\"answerId\" id=\'answerId"+reqCnt+"\'  value=\"\" /> ";
						oCell.innerHTML = strHtml4;

						$("#answerFileName" + reqCnt).val(stuAnswerFileName);
						$("#answerId" + reqCnt).val(answeredFileId);
						$("#createdBy" + reqCnt).val($("#studentName").text());
						$("#employeeId" + reqCnt)
								.val($("#stuEmployeeId").val());

						fileCnt++;
						reqCnt++;
					}

					processedAnswerFileId += answeredFileId + ",";
				} else {
					bootbox.alert("This " + stuAnswerFileName
							+ " file is already selected.");
				}

			} else {
				bootbox.alert("please Select Answer File Name");
				return false;
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
		var table = document.getElementById('tblRequest');
		var rowCount = table.rows.length;
		if (oRow.rowIndex > 0) {
			uploadRowIndex = oRow.rowIndex;
			removedFileId = $("#answerId" + oRow.rowIndex).val() + ",";
			processedAnswerFileId = processedAnswerFileId.replace(
					removedFileId, "");
			document.getElementById("tblRequest").deleteRow(oRow.rowIndex);
			fileCnt--;
		}

	}
</script>
<div class="col-md-12">
	<div class="form-group" style="margin-bottom: -15px;">
		<div class="alert alert-info" id="commentInlineheader">
			<span class="control-label" style="width: 95%;">${param.attachmentBannerName}</span>
			<span class="control-label" style="width: 5%; float: right;">
				<button type="button" class="btn green" id="btnAddImgUpload"
					style="margin-top: -10px;" onclick="javascript:addUploadRow();"
					title="Add files" name="btnAddImgUpload">Add</button>
			</span>

		</div>

	</div>
</div>
<table class="table" id="tblRequest" style="margin-bottom: 0px;">
	<thead>
		<tr>
			<td class="col-md-3" align="center"><label class="control-label">Answer
					File Name</label></td>
			<td class="col-md-3" align="center"><label class="control-label">Stduent
					Name</label></td>
			<td class="col-md-6" colspan="2" align="center"><label
				class="control-label">Upload Validated Answer</label></td>
		</tr>
	</thead>
</table>
