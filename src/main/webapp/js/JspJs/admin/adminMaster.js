var searchValue="";
$(document).ready(function()
{
	var myHeight = $(window).height() - 135;
	var myWidth = $(window).width() - 20;
	$('.jmesa').height(myHeight);
	$('.jmesa').width(myWidth);
	$('.jmesa').css('overflow', 'auto');
	setFooterLocation();
	$('#refresh').hide();
	
});
function setFooterLocation()
{
	var docHeight = $(window).height();
	var footerHeight = $('#footer').height();
	var footerTop = $('#footer').position().top + footerHeight;

	if (footerTop < docHeight)
	{
		$('#footer').css('margin-top', (docHeight - footerTop) + 'px');
	}
}
//check the email validation
function validateEmail(emailid)
{
	var in_space = emailid.indexOf(" ");
	if (in_space != -1)
	{
		return false;
	}
	var x = emailid;
	var atpos = x.indexOf("@");
	var lastatpos = x.lastIndexOf("@");
	var dotpos = x.lastIndexOf(".");
	if (atpos < 1 || dotpos < atpos + 2 || dotpos + 2 >= x.length || atpos != lastatpos)
	{
		return false;
	}
}
function jmesaWidth(){
	var myHeight = $(window).height() - 285;
	var myWidth = $(window).width() - 20;
	$('.jmesa').height(myHeight);
	$('.jmesa').width(myWidth);
	$('.jmesa').css('overflow', 'auto');
}
function onFilterSubmit()
{
	var showFilter = document.getElementById("showFilter");
	if (showFilter.value == "false" || showFilter.value == "")
	{
		showFilter.value = "true";
	}
	else
	{
		showFilter.value = "false";
	}
	onInvokeAction('');
}
function onInvokeAction(id)
{
	var parameterString = "";
	if (id != '')
	{
		$.jmesa.setExportToLimit(id, '');
		parameterString = "&" + createParameterStringForLimit(id);
	}
	var masterSearch = $('#masterSearch').val();
	var showFilter = $("#showFilter").val();
	var loadImg = "<img src='images/loading.gif' title='loading' alt='loading' style='border:0'><span class='lblspan'>Loading...</span>";
	$("#masterListDiv").empty().html(loadImg);
	var actionUrl = "getMasterDataList.do?masterSearch=" + masterSearch + "&showFilter=" + showFilter + parameterString+ "&uid=" + Math.random();
	$.get(encodeURI(actionUrl), function(data)
	{
		$("#masterListDiv").html(data);
		$('#addDiv').hide();
		$('#updateDiv').hide();
		jmesaWidth();
		setFooterLocation();
	});
}
function onInvokeExportAction(id)
{
	var parameterString = $.jmesa.createParameterStringForLimit(id);
	var masterSearch = $('#masterSearch').val();
	parameterString=parameterString+"&masterSearch="+masterSearch;
	window.open('adminMasterExportAction.do?' + parameterString, "_blank");
}

function showMasterRecords(){
	var masterSearch = $('#masterSearch').val();
	searchValue = masterSearch;
	if(allTrim(searchValue)!=''){
	var loadImg = "<img src='images/loading.gif' style='border:0'><span class='lblspan'>Loading...</span>";
	$("#masterListDiv").empty().html(loadImg);
	setFooterLocation();
	var actionUrl = "getMasterDataList.do?masterSearch=" + masterSearch + "&uid=" + Math.random();
		$.get(encodeURI(actionUrl), function(data)
		{
			if (data.indexOf('Your session expired. Relogin again!') != -1)
			{
				alert("Your session has timed out due to inactivity. Please Login to continue"); // Show an error message - or not - your choice.
				window.parent.location.href = 'index.do'; // ...and redirect to login page.
				return true;
			}
			else
			{	$('#refresh').show();
				$('#masterListDiv').show();
				$("#masterListDiv").html(data);
				$('#addDiv').hide();
				jmesaWidth();
			}
		});
	}else{
		$('#refresh').hide();
		$("#masterListDiv").empty();
	}
}

function searchAgain(){
	
		showMasterRecords();
	
}
function addMaterTabData(objForm){
	clearMasterData(objForm);
	$('#updateDiv').hide();
	$('#updateDiv').html('');
	var masterSearch = $('#masterSearch').val();
	searchValue = masterSearch;
	$('#addDiv').show();
	var actionUrl = "addMasterTabData.do?masterSearch=" + masterSearch + "&uid=" + Math.random();
	$.get(encodeURI(actionUrl), function(data)
			{
		if (data.indexOf('Your session expired. Relogin again!') != -1)
		{
			alert("Your session has timed out due to inactivity. Please Login to continue"); // Show an error message - or not - your choice.
			window.parent.location.href = 'index.do'; // ...and redirect to login page.
			return true;
		}
		else
		{
			$('#addDiv').show();
			$("#addDiv").html(data);
			$('#footer').css('position', 'relative');
		}
	});
}

function saveMasterData(objForm){
	var masterSearch = $('#masterSearch').val();
	searchValue = masterSearch;
	if (!Validate(objForm))
	{
		return false;
	}
	for ( var i = 0; i < objForm.elements.length; i++)
	{
		var element = objForm.elements[i];
		var elName = element.name;
		if(element.type=='hidden' && element.name!=element.id){
			var email = $('#'+element.name).val();
			if(allTrim(email)==""){
				alert("Please Enter e-mail address");
				$('#'+element.name).focus();
				return false;
			}else if (validateEmail(email) == '')
			{
				alert("Please Enter valid e-mail address");
				$('#'+element.name).focus();
				return false;
			}
		}
	}
	var actionData = "masterSearch="+encodeURIComponent(allTrim(masterSearch))+"&uid=" + Math.random();
	var data = "";//dont change

	for ( var i = 0; i < objForm.elements.length; i++)
	{
		var element = objForm.elements[i];
		var elName = element.name;
		var elValue = element.value;
		if (elName != null && allTrim(elName) != '' && elName!='showFilter' && elName!='masterSearch' && elName != 'add'  && elName != 'save' && elName != 'clear' && elName!='update' && elName != 'cancel' && elName != 'maxRows' && elName != 'currencyDecimals')
		{
			if (allTrim(elValue) != '' && allTrim(elValue) != ',')
			{

				actionData = actionData + "&" + elName + "=" + encodeURIComponent(allTrim(elValue));
			}
			if (allTrim(elValue) != ',' && elValue!='Y' && elValue!='N' && elValue!='1' && elValue!='0')
			{
				data = data + allTrim(elValue);
			}
		}
	}
	if (allTrim(data) == '')
	{
		alert("please enetr data for mandatory fields");
	}else{
		$.ajax(
				{
					type : "GET",
					url : 'saveMasterTabData.do',
					data : actionData,
					complete : function(data)
					{
						var result = data.responseText;
						if(result.indexOf("Failed")!=-1)
						{
							alert(result);
						}else{
							alert(result);
							$('#addDiv').hide();
							$('#updateDiv').hide();
							showMasterRecords();
						}
					}
				});
	}
}

function clearMasterData(objForm){
	
	for ( var i = 0; i < objForm.elements.length; i++)
	{
		var element = objForm.elements[i];
		var elName = element.name;
		var elValue = element.value;
		if (elName != null && allTrim(elName) != '' && elName!='showFilter' && elName != 'add'  && elName != 'save' && elName!="update" && elName != 'clear' && elName != 'cancel' && elName != 'maxRows' && elName !='masterSearch' && elName != 'currencyDecimals'  )
		{
			$('#'+elName).val('');
		}
	}
}
function cancleMasterData(objForm){
	clearMasterData(objForm);
	$('#addDiv').hide();
	$('#updateDiv').hide();
	$('#addDiv').html('');
	$('#updateDiv').html('');
	setFooterLocation(); 
}

function viewMasterData(pValue){
	clearMasterData(document.forms[0]);
	$('#addDiv').hide();
	$('#addDiv').html('');
	var masterSearch = $('#masterSearch').val();
	searchValue = masterSearch;
	var actionUrl = "viewMasterTabData.do?masterSearch="+masterSearch+"&pValue="+pValue+"&uid=" + Math.random();
	$.get(encodeURI(actionUrl), function(data)
			{
		if (data.indexOf('Your session expired. Relogin again!') != -1)
		{
			alert("Your session has timed out due to inactivity. Please Login to continue"); // Show an error message - or not - your choice.
			window.parent.location.href = 'index.do'; // ...and redirect to login page.
			return true;
		}
		else
		{
			$('#updateDiv').show();
			$("#updateDiv").html(data);
			$('#footer').css('position', 'relative');
		}
	});
}

function deleteMasterData(pValue){
	$('#addDiv').hide();
	$('#updateDiv').hide();
	var masterSearch = $('#masterSearch').val();
	searchValue = masterSearch;
	
	var actionData = "masterSearch="+masterSearch+"&pValue="+pValue+"&uid=" + Math.random();
	if(confirm("Are you sure you want to delete this record?")){
		$.ajax(
				{
					type : "GET",
					url : 'deleteMasterTabData.do',
					data : actionData,
					complete : function(data)
					{
					var result = data.responseText;
					alert(result);
					showMasterRecords();
					}
				});
	}else{
		return false;
	}
}

function modifyMasterData(pValue){
	$('#addDiv').hide();
	$('#updateDiv').hide();
	var masterSearch = $('#masterSearch').val();
	searchValue = masterSearch;
	
	var actionData = "masterSearch="+masterSearch+"&pValue="+pValue+"&uid=" + Math.random();
	if(confirm("Are you sure you want to make this record as inactive?")){
		$.ajax(
				{
					type : "GET",
					url : 'deleteMasterTabData.do',
					data : actionData,
					complete : function(data)
					{
					var result = data.responseText;
					alert(result);
					showMasterRecords();
					}
				});
	}else{
		return false;
	}
	
}

function updateMasterData(objForm){

	var masterSearch = $('#masterSearch').val();
	searchValue = masterSearch;
	if (!Validate(objForm))
	{
		return false;
	}
	for ( var i = 0; i < objForm.elements.length; i++)
	{
		var element = objForm.elements[i];
		var elName = element.name;
		if(element.type=='hidden' && element.name!=element.id){
			var email = $('#'+element.name).val();
			if(allTrim(email)==""){
				alert("Please Enter e-mail address");
				$('#'+element.name).focus();
				return false;
			}else if (validateEmail(email) == '')
			{
				alert("Please Enter valid e-mail address");
				$('#'+element.name).focus();
				return false;
			}
		}
	}
	var actionData = "masterSearch="+encodeURIComponent(allTrim(masterSearch))+"&uid=" + Math.random();
	var data = "";//dont change

	for ( var i = 0; i < objForm.elements.length; i++)
	{
		var element = objForm.elements[i];
		var elName = element.name;
		var elValue = element.value;
		if (elName != null && allTrim(elName) != '' && elName!='showFilter' && elName!='pValue' && elName!='masterSearch' && elName != 'add'  && elName != 'save' && elName != 'clear' && elName!='update' && elName != 'cancel' && elName != 'maxRows' && elName != 'currencyDecimals')
		{
			if (allTrim(elValue) != '' && allTrim(elValue) != ',')
			{

				actionData = actionData + "&" + elName + "=" + encodeURIComponent(allTrim(elValue));
			}
			if (allTrim(elValue) != ',' && elValue!='Y' && elValue!='N' && elValue!='1' && elValue!='0' )
			{
				data = data + allTrim(elValue);
			}
		}
	}
	if (allTrim(data) == '')
	{
		alert("please enetr data for mandatory fields");
	}else{
		$.ajax(
				{
					type : "GET",
					url : 'updateMasterTabData.do',
					data : actionData,
					complete : function(data)
					{
						var result = data.responseText;
						if(result.indexOf("Failed")!=-1)
						{
							alert(result);
						}else{
							alert(result);
							$('#addDiv').hide();
							$('#updateDiv').hide();
							showMasterRecords();
						}
					}
				});
	}
}