var searchvalue = "";
var oldUserId;
$(document).ready(function()
{
	var docHeight = $(window).height();
	var footerHeight = $('#footer').height();
	var footerTop = $('#footer').position().top + footerHeight;

	if (footerTop < docHeight)
	{
		$('#footer').css('margin-top', 10 + (docHeight - footerTop) + 'px');
	}

	var myHeight = $(window).height() - 250;
	$('.jmesa').height(myHeight);
	$('.jmesa').css('overflow', 'auto');
	$("#successMsg").hide();
	$('#newPwd').keyup(function()
	{
		$('#result').html(passwordStrength($('#newPwd').val(), $('#pwd_usUserId').val()))
	});
	$("#emailChange").hide();
	// user has gsi admin role this check box will enable
	// once check this one email change will enable
	$("#emailCheck").click(function()
	{
		if (this.checked)
		{
			$("#emailChange").show();
			$("#nu_userId").val('');
			$("#cnf_userId").val('');
		}
		else
		{
			$("#emailChange").hide();
		}
	});

});
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
		// $.jmesa.createHiddenInputFieldsForLimitAndSubmit(id);
		parameterString = "&" + createParameterStringForLimit(id);
	}
	var userId = $("#searchString").val();
	var showFilter = $("#showFilter").val();

	var url = "getAdminUserList.do?ajax=true&userId=" + userId + "&showFilter=" + showFilter + parameterString;
	$.get(encodeURI(url), function(data)
	{
		var loadImg = "<img src='images/loading.gif' title='loading' alt='loading' style='border:0'><span class='lblspan'>Loading...</span>";
		$("#userListDiv").empty().html(loadImg);
		$("#userListDiv").html(data);
	});
}
function onInvokeExportAction(id)
{
	var parameterString = $.jmesa.createParameterStringForLimit(id);
	location.href = 'readytask.htm?' + parameterString;
}
// search the user based on search string
function searchUser()
{
	closeTierWindow();
	$("#successMsg").hide();
	var userId = $("#searchString").val();
	searchvalue = userId;
	if (userId == '' && userId != null)
	{
		alert("Enter Username / User Id");
		return false;
	}
	else
	{
		var loadImg = "<img src='images/loading.gif' style='border:0'><span class='lblspan'>Loading...</span>";
		$("#userListDiv").empty().html(loadImg);
		var actionUrl = "getAdminUserList.do?userId=" + userId + "&uid=" + Math.random();
		$.get(encodeURI(actionUrl), function(data)
		{
			if (data.indexOf('Your session expired. Relogin again!') != -1)
			{
				alert("Your session has timed out due to inactivity. Please Login to continue"); // Show
																									// an
																									// error
																									// message
																									// - or
																									// not
																									// -
																									// your
																									// choice.
				window.parent.location.href = 'index.do'; // ...and
																		// redirect
																		// to
																		// login
																		// page.
				return true;

			}
			else
			{
				$('#userListDiv').show();
				$("#userListDiv").html(data);
			}
		});
	}
}
// reload the search lists based on search string
function searchAddUser(userId)
{
	$("#successMsg").hide();
	var loadImg = "<img src='images/loading.gif' title='loading' alt='loading' style='border:0'><span class='lblspan'>Loading...</span>";
	$("#userListDiv").empty().html(loadImg);
	var actionUrl = "getAdminUserList.do?userId=" + userId + "&uid=" + Math.random();
	$("#userListDiv").show();
	// $("#userListDiv").load(encodeURI(actionUrl));
	$.get(encodeURI(actionUrl), function(data)
	{
		if (data.indexOf('Your session expired. Relogin again!') != -1)
		{
			alert("Your session has timed out due to inactivity. Please Login to continue"); // Show
																								// an
																								// error
																								// message
																								// - or
																								// not
																								// -
																								// your
																								// choice.
			window.parent.location.href = 'index.do'; // ...and
																	// redirect
																	// to login
																	// page.
			return true;

		}
		else
		{
			$("#userListDiv").html(data);
		}
	});
}

// once user id added it will list the newly added user.
function searchAddNewUser(userId)
{

	var loadImg = "<img src='images/loading.gif' title='loading' alt='loading' style='border:0'><span class='lblspan'>Loading...</span>";
	$("#userListDiv").empty().html(loadImg);
	var actionUrl = "getAdminUserList.do?userId=" + userId + "&uid=" + Math.random();
	$("#userListDiv").show();
	$("#successMsg").show();
	$("#successMsg").empty().html(
			"<span style='color: chocolate;'>User \"" + userId
					+ "\" is enrolled successfully.  To assign roles, please click the Edit Roles icon.</span>");
	// $("#userListDiv").load(encodeURI(actionUrl));
	$.get(encodeURI(actionUrl), function(data)
	{
		if (data.indexOf('Your session expired. Relogin again!') != -1)
		{
			alert("Your session has timed out due to inactivity. Please Login to continue"); // Show
																								// an
																								// error
																								// message
																								// - or
																								// not
																								// -
																								// your
																								// choice.
			window.parent.location.href = 'index.do'; // ...and
																	// redirect
																	// to login
																	// page.
			return true;

		}
		else
		{
			$("#userListDiv").html(data);
		}
	});
}
function pressenter(e)
{
	// $('#searchString').css("color","White");
	if (minimumSearchLength())
	{
		var keynum;

		if (window.event) // IE
		{
			keynum = e.keyCode;
		}
		else if (e.which) // Netscape/Firefox/Opera
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
	return true;
}
// click the edit icon edit user details div will show
function editUserDetails(empId, userName, userId, status)
{
	closeTierWindow();
	$('#addUser').hide();
	$('#updateUser').show();
	$('#add').hide();
	$('#searchTab').hide();
	$('#userListDiv').hide();

	enableUpdate();

	document.getElementById("u_empId").value = empId;
	document.getElementById("u_userName").value = userName;
	document.getElementById("u_userId").value = userId;
	oldUserId = userId;
	$('#emailCheck').attr('checked', false);
	$("#emailChange").hide();

	$("#u_Status").attr('checked', status);

	// hide the changeuserId for sutherland users.
	var gsiadmin = $('#gsiadmin').val();// TODO
	var gsdadmin = $('#gsdadmin').val();// TODO
	if ((gsiadmin != null && gsiadmin != undefined))
	{
		if (userId.indexOf("sutherlandglobal.com") >= 0)
		{
			$('#chText').hide();
			$('#emailCheck').hide();
		}
		else
		{
			$('#chText').show();
			$('#emailCheck').show();
		}
	}

	$("#approverTier").hide();
	var url = "userId=" + encodeURIComponent(userId) + "&empId=" + encodeURI(empId) + "&uid="
			+ Math.random();
	$.ajax(
	{
		type : "GET",
		url : 'adminHasApproverTier.do',
		data : url,
		complete : function(data)
		{
			var result = data.responseText;
			if (result.indexOf("Approver") >= 0 && gsiadmin != null && gsiadmin != undefined)
			{
				$("#approverTier").show();
			}
			else
			{
				$("#approverTier").hide();
			}
			if (result.indexOf("Logged") >= 0)
			{
				$("#log_Status").attr('checked', true);
				$("#log_Status").attr('disabled', true);
			}
			else
			{
				$("#log_Status").attr('checked', false);
				$("#log_Status").attr('disabled', true);
			}
			if (result.indexOf("Processed") >= 0)
			{
				$("#prcs_Status").attr('checked', true);
				$("#prcs_Status").attr('disabled', true);
			}
			else
			{
				$("#prcs_Status").attr('checked', false);
				$("#prcs_Status").attr('disabled', true);
			}
		}
	});

	$('#edit').val('Update');
	$('#cancel').val('Cancel');
}
// check the email validation
function validateEmail(emailid)
{
	var in_space = emailid.indexOf(" ");
	if (in_space != -1)
	{
		return false;
	}
	/*
	 * var apo_a = emailid.indexOf("'"); if (apo_a != -1) { return false; }
	 */

	var x = emailid;
	var atpos = x.indexOf("@");
	var lastatpos = x.lastIndexOf("@");
	var dotpos = x.lastIndexOf(".");
	if (atpos < 1 || dotpos < atpos + 2 || dotpos + 2 >= x.length || atpos != lastatpos)
	{
		return false;
	}
}
// update the user details based on data
function updateUserDetails()
{

	var empId = document.getElementById("u_empId").value;
	var userName = document.getElementById("u_userName").value;
	var userId = document.getElementById("u_userId").value;
	var status = $('#u_Status').is(':checked');
	var emlChk = $('#emailCheck').is(':checked');

	if (emlChk)
	{
		var nUserId = document.getElementById("nu_userId").value;
		var cnfUserId = document.getElementById("cnf_userId").value;

		if (nUserId == '')
		{
			alert("Please Enter New UserId");
			return false;
		}
		else if (validateEmail(nUserId) == '')
		{
			alert("Please Enter valid new UserId");
			return false;
		}
		else if (cnfUserId == '')
		{
			alert("Please Enter confirm UserId");
			return false;
		}
		else if (userId == nUserId || (userId.toUpperCase() === nUserId.toUpperCase()))
		{
			alert("UserId and New User Id should not be same.");
			return false;
		}
		else if (validateEmail(cnfUserId) == '' || !(nUserId.toUpperCase() === cnfUserId.toUpperCase()))
		{
			alert("New UserId and confirm UserId should be same.");
			return false;
		}
	}

	if (empId == '' || allTrim(empId) == "")
	{
		alert("Please Enter Employee Id!");
		return false;
	}
	else if (userName == '')
	{
		alert("Please Enter Usename!");
		return false;
	}

	else if (userId == '')
	{
		alert("Please Enter UserId/Email-id!");
		return false;
	}
	else if (validateEmail(userId) == '')
	{
		alert("Please Enter valid UserId/Email-id!");
		return false;
	}
	else
	{
		if (emlChk)
		{
			var nUserId = document.getElementById("nu_userId").value;
			var url = "empId=" + encodeURI(empId) + "&userName=" + encodeURI(userName) + "&oldUserId="
					+ encodeURI(oldUserId) + "&userId=" + encodeURI(nUserId) + "&status=" + status + "&uid=" + Math.random();
		}
		else
		{
			var url = "empId=" + encodeURI(empId) + "&userName=" + encodeURI(userName) + "&oldUserId="
					+ encodeURI(oldUserId) + "&userId=" + encodeURI(userId) + "&status=" + status + "&uid=" + Math.random();
		}
		disableUpdate();
		$.ajax(
		{
			type : "GET",
			url : 'updateAdminUserDetails.do',
			data : url,
			complete : function(data)
			{
				var result = data.responseText;
				alert(result);
				document.getElementById("updateUser").style.display = "none";
				searchAddUser(searchvalue);
				$('#searchTab').show();
				$('#add').show();
			}
		});
	}
}
// delete the user details(now disabled)
function deleteUserDetails(userId)
{
	closeTierWindow();
	$('#addUser').hide();
	$('#updateUser').hide();
	$('#add').show();
	if (confirm("Are you sure want to delete Account?"))
	{
		var url = "userId=" + userId + "&uid=" + Math.random();
		$.ajax(
		{
			type : "GET",
			url : 'deleteAdminUserDetails.do',
			data : url,
			complete : function(data)
			{
				var result = data.responseText;
				alert(result);
				searchAddUser(searchvalue);
				$('#add').show();
			}
		});
	}
}
// click add user icon add user details div will show
function addUserDetails()
{
	closeTierWindow();
	$("#successMsg").hide();
	$('#addUser').show();
	$('#updateUser').hide();
	$('#add').hide();
	$('#searchTab').hide();
	$("#userListDiv").hide();

	document.getElementById("empId").value = '';
	document.getElementById("userName").value = '';
	document.getElementById("userId").value = '';
	document.getElementById("cUserId").value = '';
	// document.getElementById("chRole").value='';

	$('#save').val('Save');
	$('#close').val('Cancel');
}

function enableUpdate()
{
	$('#edit').attr("disabled", false);
	//$('#edit').attr("style", "color:White");
}
function disableUpdate()
{
	$('#edit').attr("disabled", true);
}
function enableAddUser()
{
	$('#save').attr("disabled", false);
	//$('#save').attr("style", "color:White");
}
// save or add the user details
function saveUserDetails()
{
	$('#save').attr("disabled", true);

	var empId = document.getElementById("empId").value;
	var userName = document.getElementById("userName").value;
	var userId = document.getElementById("userId").value;
	var cUserId = document.getElementById("cUserId").value;

	if (empId == '' || allTrim(empId) == "")
	{
		alert("Please Enter Employee Id!");
		$("#empId").val('');
		enableAddUser();
		return false;
	}
	else if (userName == '')
	{
		alert("Please Enter Usename!");
		enableAddUser();
		return false;
	}
	else if (userId == '')
	{
		alert("Please Enter UserId/Email-id!");
		enableAddUser();
		return false;
	}
	else if (validateEmail(userId) == '')
	{
		alert("Please Enter valid UserId/Email-id!");
		enableAddUser();
		return false;
	}
	else if (validateEmail(cUserId) == '' || !(userId.toUpperCase() === cUserId.toUpperCase()))
	{
		alert("UserId and confirm User Id should be same.");
		enableAddUser();
		return false;
	}
	else
	{
		var url = "empId=" + encodeURI(empId) + "&userName=" + encodeURI(userName) + "&userId=" + encodeURI(userId) + "&uid=" + Math.random();
		$.ajax(
		{
			type : "GET",
			url : 'saveAdminUserDetails.do',
			data : url,
			complete : function(data)
			{
				var result = data.responseText;
				document.getElementById("addUser").style.display = "none";
				alert(result);
				enableAddUser();
				$('#searchTab').show();
				$('#u_empId').show();
				if (result.indexOf("success") >= 0)
				{
					searchAddNewUser(userId);
				}
				$('#add').show();
				minimumSearchLength();
				$('#userListDiv').show();
			}
		});
	}
}

// update password
function updateUserPwdDetails(userId)
{
	closeTierWindow();
	if (confirm("Are you sure want to re-set password?"))
	{
		var url = "userId=" + userId + "&uid=" + Math.random();
		$.ajax(
		{
			type : "GET",
			url : 'updateAdminUserPwdDetails.do',
			data : url,
			complete : function(data)
			{
				var result = data.responseText;
				alert(result);
				searchAddUser(searchvalue);
				$('#add').show();
				$("#successMsg").hide();
				minimumSearchLength();
			}
		});
	}

}
function enableRole()
{
	$('#rclose').val('Cancel');
}
// edit the user roles click roles icon itlilst the roles in new window.
function editUserRoles(userId)
{
	closeTierWindow();
	var userId = userId;
	var w = 600;
	var h = 400;
	var left = (screen.width / 2) - (w / 2);
	var top = (screen.height / 2) - (h / 2);
	MM_openBrWindow('getAvailRolesList.do?userId=' + userId + "&uid=" + Math.random(), 'EditRoles',
			'toolbars=no,menubar=no,location=no,scrollbars=yes,resizable=yes,status=yes,width=' + w + ',height=' + h + ',top=' + top + ',left='
					+ left)
}
function MM_openBrWindow(theURL, winName, features)
{
	closeRoleWindows();
	roleWindow = window.open(theURL, winName, features);
}
// add div close
function closeUserDetails()
{
	$("#successMsg").hide();
	$('#addUser').hide();
	$('#rolesDiv').hide();
	$('#add').show();
	$('#searchTab').show();
	$("#userListDiv").show();

}
// update div close
function cancelUserDetails()
{
	$("#successMsg").hide();
	$('#updateUser').hide();
	$('#add').show();
	$('#searchTab').show();
	$("#userListDiv").show();
	enableAddUser();
}
// disable Rightclick for a particular field
function whichButton(event)
{
	if (event.button == 2)// For right click
	{
		alert("Right Click Not Allowed!");
	}
}
// disable CTRl button
function noCTRL(e)
{
	var code = (document.all) ? event.keyCode : e.which;

	var msg = "Sorry, this functionality is disabled.";
	if (parseInt(code) == 17) // This is the Key code for CTRL key
	{
		alert(msg);
		window.event.returnValue = false;
	}
}

function editApproverData()
{
	closeTierWindow();
	var userId = $("#u_userId").val();
	var w = 800;
	var h = 500;
	var left = (screen.width / 2) - (w / 2);
	var top = (screen.height / 2) - (h / 2);
	MM_openBrAppWindow('getApproverAuthorityDetails.do?userId=' + userId + "&uid=" + Math.random(), 'ApproverDetailsFrm',
			'toolbars=no,menubar=no,location=no,scrollbars=yes,resizable=yes,status=yes,width=' + w + ',height=' + h + ',top=' + top + ',left='
					+ left)
}
function MM_openBrAppWindow(theURL, winName, features)
{
	tierWindow = window.open(theURL, winName, features);
}

function minimumSearchLength()
{
	var srh = $("#searchString").val();
	if (srh.length < 3)
	{
		$("#searchBtn").attr("disabled", true);
		return false;
	}
	else
	{
		$("#searchBtn").attr("disabled", false);
		return true;
	}
}
