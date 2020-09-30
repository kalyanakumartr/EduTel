var newwindow;
var locationWindow;
var glwindow;
var lookupwindow;

function numericValidation(fieldId)
{
	var fieldVal = $('#' + fieldId).val();
	var exp = /^[A-Za-z0-9]{1,3}?$/.test(fieldVal);
	if (!exp)
	{
		alert("Number's only allowed..!!");
		$('#' + fieldId).val("");
	}
}

function allTrim(cValue)
{
	var lDone = false;
	while (lDone == false)
	{
		if (cValue.length == 0)
		{
			return cValue;
		}
		if (cValue.indexOf(' ') == 0)
		{
			cValue = cValue.substring(1);
			lDone = false;
			continue;
		}
		else
		{
			lDone = true;
		}
		if (cValue.lastIndexOf(' ') == cValue.length - 1)
		{
			cValue = cValue.substring(0, cValue.length - 1);
			lDone = false;
			continue;
		}
		else
		{
			lDone = true;
		}
	}
	return cValue;
}

function validateEmail(email) 
	{ 
    	var re = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    	return re.test(email);
	} 

function GetElementValue(objElement)
{
	var result = "";
	switch (objElement.type)
	{
	case "text":
	case "hidden":
	case "textarea":
	case "password":
		result = objElement.value;
		break;

	case "select-one":
	case "select":
		if (objElement.selectedIndex >= 0)
			result = objElement.options[objElement.selectedIndex].value;
		break;

	case "radio":
	case "checkbox":
		for ( var i = 0; i < objElement.form.elements.length; i++)
		{
			if (objElement.form.elements[i].name == objElement.name)
			{
				if (objElement.form.elements[i].checked)
					result += objElement.form.elements[i].value + ",";
			}
		}
		break;
	}
	return result;
}

// Empty Validation
function ValidateNotEmpty(objElement)
{
	var strValue = GetElementValue(objElement);
	var blnResult = true;
	if (strValue == null || allTrim(strValue) == "") // check for nothing
	{
		blnResult = false;
	}
	return blnResult;

}

// Integer Validation
function ValidateInteger(objElement)
{
	// check for valid numeric strings
	var strString = GetElementValue(objElement);
	var strValidChars = "0123456789";
	var strChar;
	var blnResult = true;

	// test strString consists of valid characters listed above
	for (i = 0; i < strString.length && blnResult == true; i++)
	{
		strChar = strString.charAt(i);
		if (strValidChars.indexOf(strChar) == -1)
		{
			blnResult = false;
		}
	}
	return blnResult;

}

// Number Validation
function ValidateNumber(objElement)
{
	// check for valid numeric strings
	var strString = GetElementValue(objElement);
	var strValidChars = ".0123456789"; // decimal ok
	var strChar;
	var blnResult = true;
	// test strString consists of valid characters listed above
	for (i = 0; i < strString.length && blnResult == true; i++)
	{
		strChar = strString.charAt(i);
		if (strValidChars.indexOf(strChar) == -1)
		{
			blnResult = false;
		}
	}
	return blnResult;
}

// List size validation
function ValidateListNotEmpty(objElement)
{
	var listSize = GetElementValue(objElement);
	var blnResult = true;
	if (listSize < 1)
	{
		blnResult = false;
	}
	return blnResult;
}

// Future Date validation
function ValidateFutureDate(objElement)
{
	var dateValue = GetElementValue(objElement);
	var re = /^(\d{1,2})\-(\d{1,2})\-(\d{4})$/;
	var blnResult = false;
	if (dateValue.match(re))
	{
		var mm = RegExp.$1;
		var dd = RegExp.$2;
		var yy = RegExp.$3;
		// try to create the same date using Date Object
		var dt = new Date(parseFloat(yy), parseFloat(mm) - 1, parseFloat(dd), 0, 0, 0, 0);
		var today = new Date();

		// If future date is selected return false
		if (dt < today)
		{
			blnResult = true;
		}
	}
	return blnResult;
}

// Email Validation
function ValidateEmail(objElement)
{
	// Will check for @, period after @ and text in between
	var strValue = GetElementValue(objElement);
	var in_space = strValue.indexOf(" ");
	if (in_space != -1)
	{
		return false;
	}

	/*
	 * var apo_a = strValue.indexOf("'"); if (apo_a != -1) { return false; }
	 */

	var len = strValue.length;
	var alpha = strValue.indexOf("@");
	var last_alpha = strValue.lastIndexOf("@");
	if (alpha != last_alpha)
	{
		return false;
	}

	// No @, in first position, or name too short
	if (alpha == -1 || alpha == 0 || len < 6)
	{
		return false;
	}

	var last_p = strValue.lastIndexOf(".");
	// Be sure period at least two spaces after @, but not last char.
	if (last_p - alpha < 2 || last_p == (len - 1))
	{
		return false;
	}

}

// Valid PhoneNumber
function ValidatePhone(objElement)
{
	// non-digit characters which are allowed in phone numbers
	var phoneNumberDelimiters = "()- ";

	// characters which are allowed in international phone numbers
	// (a leading + is OK)
	var validWorldPhoneChars = phoneNumberDelimiters + "+";

	// Minimum no of digits in an international phone no.
	var minDigitsInIPhoneNumber = 10;

	var strValue = GetElementValue(objElement);
	s = stripCharsInBag(strValue, validWorldPhoneChars);

	return (ValidateInteger(s) && s.length >= minDigitsInIPhoneNumber);
}

function stripCharsInBag(s, bag)
{
	var i;
	var returnString = "";

	// Search through string’s characters one by one.
	// If character is not in bag, append to returnString.
	for (i = 0; i < s.length; i++)
	{
		// Check that current character isn’t whitespace.
		var c = s.charAt(i);
		if (bag.indexOf(c) == -1)
			returnString += c;
	}

	return returnString;

}

function InsertError(element, strMessage)
{
	if ((element.form.getAttribute("show_alert")) && (element.form.getAttribute("show_alert") != "0"))
	{
		alert(strMessage);
		return;
	}

	var strSpanID = element.name + "_val_error";
	var objSpan = document.getElementById(strSpanID);
	if (!objSpan)
	{
		if ((element.type == "radio") || (element.type == "checkbox"))
		{
			for ( var i = 0; i < element.form.elements.length; i++)
			{
				if (element.form.elements[i].name == element.name)
				{
					element = element.form.elements[i];
				}
			}
		}
		objSpan = document.createElement("span");
		objSpan.id = strSpanID;
		objSpan.className = "validation_error";

		var nodeAfter = 0;
		var nodeParent = element.parentNode;
		for ( var i = 0; i < nodeParent.childNodes.length; i++)
		{
			if (nodeParent.childNodes[i] == element)
			{
				if (i < (nodeParent.childNodes.length - 1))
					nodeAfter = nodeParent.childNodes[i + 1];
				break;
			}
		}

		if ((!nodeAfter) && (nodeParent.parentNode))
		{
			nodeParent = nodeParent.parentNode;
			for ( var i = 0; i < nodeParent.childNodes.length; i++)
			{
				if (nodeParent.childNodes[i] == element.parentNode)
				{
					if (i < (nodeParent.childNodes.length - 1))
						nodeAfter = nodeParent.childNodes[i + 1];
					break;
				}
			}
		}

		if (nodeAfter)
			nodeParent.insertBefore(objSpan, nodeAfter);
		else
			document.body.appendChild(objSpan);
	}
	objSpan.innerHTML = strMessage;
}

function ClearError(element)
{
	var strSpanID = element.name + "_val_error";
	var objSpan = document.getElementById(strSpanID);
	if (objSpan)
	{
		objSpan.innerHTML = "";
	}
}

function Validate(objForm)
{

	var arrValidated = new Array();
	var blnValid = true;
	for ( var i = 0; i < objForm.elements.length; i++)
	{
		var element = objForm.elements[i];
		// var elName=element.name;
		var elName = element.id;
		if ((!elName) || (elName.length == 0) || (arrValidated[elName]))
			continue;

		arrValidated[elName] = true;
		var validationType = element.getAttribute("validate");
		if ((!validationType) || (validationType.length == 0))
			continue;

		var strMessages = element.getAttribute("msg");
		if (!strMessages)
			strMessages = "";

		var arrMessages = strMessages.split("|");
		var arrValidationTypes = validationType.split("|");
		for ( var j = 0; j < arrValidationTypes.length; j++)
		{
			var curValidationType = arrValidationTypes[j];
			blnValid = true;
			switch (curValidationType)
			{
			case "not_empty":
				blnValid = ValidateNotEmpty(element);
				break;
			case "integer":
				blnValid = ValidateInteger(element);
				break;
			case "number":
				blnValid = ValidateNumber(element);
				break;
			case "email":
				blnValid = ValidateEmail(element);
				break;
			case "phone":
				blnValid = ValidatePhone(element);
				break;
			case "date":
				blnValid = ValidateDate(element);
				break;
			case "not_list_empty":
				blnValid = ValidateListNotEmpty(element);
				break;
			case "future_date":
				blnValid = ValidateFutureDate(element);
				break;
			default:
				try
				{
					blnValid = eval(curValidationType + "(element)");
				}
				catch (ex)
				{
					blnValid = true;
				}
			}
			if (blnValid == false)
			{
				try
				{
					var fieldName = element.getAttribute("fieldname");
					var message = "Invalid value for " + fieldName;
					if ((j < arrMessages.length) && (arrMessages[j].length > 0))
						message = arrMessages[j];
					InsertError(element, message);
					if ((typeof element.focus == "function") || (element.focus))
					{
						element.focus();
					}
					return false;
				}
				catch (ex)
				{
					return false;
				}
			}
			else
			{
				ClearError(element);
			}
		}
	}
	return true;
}

function limitText(limitField, limitCount, limitNum)
{
	if (limitField.value.length > limitNum)
	{
		limitField.value = limitField.value.substring(0, limitNum);
	}
	else
	{
		limitCount.value = limitNum - limitField.value.length;
	}
}

function onlyNumbers()
{
	/*
	 * var floatVal = /^((\d+(\.\d*)?)|((\d*\.)?\d+))$/; alert(s + " val " + floatVal.test(s)); return floatVal.test(s); var text = evt.value; var ck_name = /^[A-Za-z0-9 ]$/; var valid = false;
	 * if(ck_name.test(text)) { valid = true; } alert(valid); return valid;
	 */
	var e = event || evt; // for trans-browser compatibility
	var charCode = e.which || e.keyCode;
	if (charCode > 47 && charCode < 58)
	{
		return true;
	}

	return false;

}

// Date Validation
function ValidateDate(objElement)
{
	// Will check for @, period after @ and text in between
	var dateValue = GetElementValue(objElement);
	var re = /^(\d{1,2})\-(\d{1,2})\-(\d{4})$/;
	if (dateValue.match(re))
	{
		var mm = RegExp.$1;
		var dd = RegExp.$2;
		var yy = RegExp.$3;
		// try to create the same date using Date Object
		var dt = new Date(parseFloat(yy), parseFloat(mm) - 1, parseFloat(dd), 0, 0, 0, 0);
		// invalid day
		if (parseFloat(dd) != dt.getDate())
		{
			return false;
		}
		// invalid month
		if (parseFloat(mm) - 1 != dt.getMonth())
		{
			return false;
		}
		// invalid year
		if (parseFloat(yy) != dt.getFullYear())
		{
			return false;
		}

		// everything fine
		return true;
	}
	else
	{
		// not even a proper date
		return false;
	}
}

function DateValidation(dateC, strvalue)
{
	var dateValue = dateC.value;
	alert(dateValue);
	var dateString = "";
	if (dateValue.length <= 0)
	{
		dateString = "Please Enter the" + strvalue + "$";
		return dateString;
	}
	else if (/[^-\/\d]/.test(dateValue))
	{
		dateString = "Please Enter Correct date format for " + strvalue + "$";
		return dateString;
	}
	else
	{
		var DateSplit;
		if (/[^\/\d]/.test(dateValue))
		{

			DateSplit = dateValue.split('-');
		}
		else if (/[^-\d]/.test(dateValue))
		{
			DateSplit = dateValue.split('/');
		}
		else
		{
			dateString = "Please Enter Correct date format for " + strvalue + "$";
			return dateString;
		}

		if (DateSplit.length <= 2 || DateSplit.length > 3)
		{
			dateString = "Please Enter Correct date format for " + strvalue + "$";
			return dateString;
		}

		if (DateSplit[0] == 1 || DateSplit[0] == 3 || DateSplit[0] == 5 || DateSplit[0] == 7 || DateSplit[0] == 8 || DateSplit[0] == 10 || DateSplit[0] == 12)
			Days = 31;
		else if (DateSplit[0] == 2 & DateSplit[2] % 4 != 0)
			Days = 28;
		else if (DateSplit[0] == 2 & DateSplit[2] % 4 == 0)
			Days = 29;
		else if (DateSplit[0] == 4 || DateSplit[0] == 6 || DateSplit[0] == 9 || DateSplit[0] == 11)
			Days = 30;

		if (DateSplit[2].length < 4 || DateSplit[2].length > 4 || DateSplit[2] < 1800)
		{
			dateString = "Enter a Valid Year for " + strvalue + "$";
			return dateString;
		}

		if (DateSplit[0].length < 1 || DateSplit[0].length > 2 || DateSplit[0] > 12 || DateSplit[0] < 1)
		{
			dateString = "Enter a Valid Month for " + strvalue + "$";
			return dateString;
		}

		if (DateSplit[1].length < 1 || DateSplit[1].length > 2 || DateSplit[1] > Days || DateSplit[1] < 1)
		{
			dateString = "Enter a Valid Day for " + strvalue + "$";
			return dateString;
		}
	}

	return dateString;
}

function uncheckExceptions(statusObj)
{
	clearRescanMsg();
	$("#exceptions").attr('disabled', !statusObj.checked);
	$("#exceptions").find("option").attr("selected", false);
	if (statusObj.checked)
		$("#exceptions").css("background-color", '#FFFFFF');
	else
		$("#exceptions").css("background-color", '#C0C0C0');

	var routeTo = $('#routeTo').val();
	if (routeTo != undefined && routeTo != null)
	{
		if (statusObj.checked)
		{
			$('#routeTo').val("P005");
			$('#save').attr("disabled", true);
			$('#save').attr("style", "color:gray");
		}
		else
		{
			$('#routeTo').val("");
			$('#save').removeAttr("disabled", "");
			$('#save').removeAttr("style", "");

		}
	}
}

function onExceptionFixed()
{
	if ($('#unselect').val() != undefined && $('#unselect').is(':checked') && $('#routeTo').val() != "P005")
	{
		$('#routeTo').val("P005");
	}
}

function limitTextDescription(textObj, countSpan, limitNum)
{
	if (textObj != null && textObj != undefined)
	{
		var str = textObj.value;
		if (str.length > limitNum)
		{
			textObj.value = textObj.value.substring(0, limitNum);
		}
		else
		{
			var remain = limitNum - str.length;
			$('#' + countSpan).html(remain);
		}
	}
}

function checkOnlyAmount(cellObj)
{
	var decimals = 2;
	
	if (decimals == null || decimals == undefined || decimals == '0')
	{
		decimals = $("[name=currencyDecimals]", window.opener.document).val();
	}
	
	var amount = cellObj.value;

	// In Patter 0,decimals is mandatory onkeyup validataion
	if (amount.length == 1 && (amount == '-' || amount == '+'))
		return true;

	if (amount.length == 1 && amount == '.')
	{
		cellObj.value = "0.";
		return true;
	}

	var result = new RegExp("^[-+]?\\d{0,5}(\\.\\d{0," + decimals + "})?$").test(amount);
	
	if (result == false)
	{
		amount = amount.substring(0, amount.length);
		result = /^[-+]?\d{1,18}?$/.test(amount);
		if (result == true)
		{
			cellObj.value = amount;
		}
		else
		{
			if (allTrim(amount) != "")
			{
				cellObj.value = "";
				alert("Decimal value's only allowed. \n(Maximum 18 digits without " + decimals + " decimal places)");
			}
		}
		return false;
	}
	return true;
}

function amtDecimalValidation(objVal)
{
	var decimals = 2;
	if (decimals == null || decimals == undefined || decimals == '0')
	{
		decimals = $("[name=currencyDecimals]", window.opener.document).val();
	}	
	var amount = objVal.value;
	
	if (amount != null && amount != undefined && allTrim(amount) != '')
	{
		try
		{
			amount = roundVal(parseFloat(amount)).toString();
		}
		catch (e)
		{
			amount = objVal.value;
		}
	}

	
	var amtValid = /^[-+]?[0-9]+(\.[0-9]+)?$/.test(amount);
	
	if (!amtValid)
	{
		objVal.value = "";
		alert("Decimal value's only allowed.")
		return false;
	}

	var result = new RegExp("^[-+]?\\d{0,5}(\\.\\d{" + decimals + "})?$").test(amount);

	if (!result)
	{
		if (amount.indexOf('.') != -1)
		{
			var decVal = amount.split('.')[1];
			var intVal = amount.split('.')[0];

			if (decVal.length > parseInt(decimals))
			{
				amount = intVal + ".";
				for ( var i = 0; i < parseInt(decimals); i++)
				{
					amount += decVal.charAt(i);
				}
			}
		}
	}

	if (amount.indexOf('.') == -1)
	{
		amount = amount + ".";
	}

	var decVal = amount.split('.')[1];

	for ( var i = decVal.length; i < parseInt(decimals); i++)
	{
		amount += "0";
	}

	var isnumber = parseFloat(amount);
	if (isnumber == undefined)
		return false;

	objVal.value = amount;
	return true;
}

// Empty Validation for value
function NotEmptyValue(val)
{
	var blnResult = true;
	if (val == null || allTrim(val) == "") // check for nothing
	{
		blnResult = false;
	}
	return blnResult;

}

// Date Validation
function ValidateDateValue(dateValue)
{
	// Will check for @, period after @ and text in between
	var re = /^(\d{1,2})\-(\d{1,2})\-(\d{4})$/;
	if (dateValue.match(re))
	{
		var mm = RegExp.$1;
		var dd = RegExp.$2;
		var yy = RegExp.$3;
		// try to create the same date using Date Object
		var dt = new Date(parseFloat(yy), parseFloat(mm) - 1, parseFloat(dd), 0, 0, 0, 0);
		// invalid day
		if (parseFloat(dd) != dt.getDate())
		{
			return false;
		}
		// invalid month
		if (parseFloat(mm) - 1 != dt.getMonth())
		{
			return false;
		}
		// invalid year
		if (parseFloat(yy) != dt.getFullYear())
		{
			return false;
		}

		// everything fine
		return true;
	}
	else
	{
		// not even a proper date
		return false;
	}
}

function invoiceDetails(invoiceNo, invoiceDate, invoiceAmt, invoiceDesc)
{
	// Validate Indexing fields
	if (!NotEmptyValue(invoiceNo))
	{
		alert("Invoice number is required");
		return false;
	}
	if (!NotEmptyValue(invoiceDate))
	{
		alert("Invoice date is required");
		return false;
	}

	if (!ValidateDateValue(invoiceDate))
	{
		alert("Invalid invoice date format");
		return false;
	}

	if (!NotEmptyValue(invoiceAmt))
	{
		alert("Invoice amount is required");
		return false;
	}

	if (!NotEmptyValue(invoiceDesc))
	{
		alert("Invoice description is required");
		return false;
	}
	return true;

}

function vendorDetails(invVendorNo, invVendorCd, invVendorAddr)
{

	// Validate vendor fields
	if (!NotEmptyValue(invVendorNo))
	{
		alert("Vendor name is required");
		return false;
	}
	if (!NotEmptyValue(invVendorCd))
	{
		alert("Vendor code is required");
		return false;
	}
	if (!NotEmptyValue(invVendorAddr))
	{
		alert("Vendor address is required");
		return false;
	}
	return true;

}

function glModelDetails(glModelList)
{

	if (glModelList <= 0)
	{
		alert("GL Code  is required");
		return false;
	}
	return true;

}
function scanDateValidation(dateVal, urnNumber)
{
	var urn = urnNumber + "";
	var dateValue = dateVal.value;
	var re = /^(\d{1,2})\-(\d{1,2})\-(\d{4})$/;
	var str = "";
	var urnYear = urn.slice(0, 4);
	var urnMonth = urn.slice(4, 6);
	var urnDate = urn.slice(6, 8);
	if (dateValue.match(re))
	{
		var mm = RegExp.$1;
		var dd = RegExp.$2;
		var yy = RegExp.$3;
		// try to create the same date using Date Object
		var dt = new Date(parseFloat(yy), parseFloat(mm) - 1, parseFloat(dd), 0, 0, 0, 0);
		var today = new Date(parseFloat(urnYear), parseFloat(urnMonth) - 1, parseFloat(urnDate), 0, 0, 0, 0);
		// var today = new Date();
		// If future date is selected return false
		if (dt > today)
		{
			dateVal.value = str;
			alert("Scan date cannot be greater than Invoice upload date");
			return false;
		}

		// invalid day
		if (parseFloat(dd) != dt.getDate())
		{
			alert("Invalid date");
			return false;
		}
		// invalid month
		if (parseFloat(mm) - 1 != dt.getMonth())
		{
			alert("Invalid month");
			return false;
		}
		// invalid year
		if (parseFloat(yy) != dt.getFullYear())
		{
			alert("Invalid year");
			return false;
		}
	}
	else
	{
		alert("Invalid date");
		return false;

	}
	return true;
}

function futureDateValidation(dateVal)
{
	var dateValue = dateVal.value;
	var re = /^(\d{1,2})\-(\d{1,2})\-(\d{4})$/;
	var str = "";
	if (dateValue.match(re))
	{
		var mm = RegExp.$1;
		var dd = RegExp.$2;
		var yy = RegExp.$3;
		// try to create the same date using Date Object
		var dt = new Date(parseFloat(yy), parseFloat(mm) - 1, parseFloat(dd), 0, 0, 0, 0);
		var today = new Date();
		// If future date is selected return false
		if (dt > today)
		{
			dateVal.value = str;
			alert("Future date is not allowed");
			return false;
		}

		// invalid day
		if (parseFloat(dd) != dt.getDate())
		{
			alert("Invalid date");
			return false;
		}
		// invalid month
		if (parseFloat(mm) - 1 != dt.getMonth())
		{
			alert("Invalid month");
			return false;
		}
		// invalid year
		if (parseFloat(yy) != dt.getFullYear())
		{
			alert("Invalid year");
			return false;
		}
	}
	else
	{
		alert("Invalid date");
		return false;

	}
	return true;
}

function futureDateValidation30Days(dateVal)
{
	var dateValue = dateVal.value;
	var re = /^(\d{1,2})\-(\d{1,2})\-(\d{4})$/;
	var str = "";
	if (dateValue.match(re))
	{
		var mm = RegExp.$1;
		var dd = RegExp.$2;
		var yy = RegExp.$3;
		// try to create the same date using Date Object
		var dt = new Date(parseFloat(yy), parseFloat(mm) - 1, parseFloat(dd), 0, 0, 0, 0);
		var today = new Date();
		today.setDate(today.getDate() + 30);
		// If future date is selected return false
		if (dt > today)
		{
			dateVal.value = str;
			alert("Future date is not allowed beyond 30 days");
			return false;
		}

		// invalid day
		if (parseFloat(dd) != dt.getDate())
		{
			alert("Invalid date");
			return false;
		}
		// invalid month
		if (parseFloat(mm) - 1 != dt.getMonth())
		{
			alert("Invalid month");
			return false;
		}
		// invalid year
		if (parseFloat(yy) != dt.getFullYear())
		{
			alert("Invalid year");
			return false;
		}
	}
	else
	{
		alert("Invalid date");
		return false;

	}
	return true;
}

function pastDateValidation(dateVal)
{
	var dateValue = dateVal.value;
	var re = /^(\d{1,2})\-(\d{1,2})\-(\d{4})$/;
	var str = "";
	if (dateValue.match(re))
	{
		var mm = RegExp.$1;
		var dd = RegExp.$2;
		var yy = RegExp.$3;

		var today = new Date();

		// try to create the same date using Date Object
		var dt = new Date(parseFloat(yy), parseFloat(mm) - 1, parseFloat(dd), 23, 59, 00);

		// If past date is selected return false
		if (dt.getTime() < today.getTime())
		{
			dateVal.value = str;
			alert("Past date is not allowed");
			return false;
		}

		// invalid day
		if (parseFloat(dd) != dt.getDate())
		{
			alert("Invalid date");
			return false;
		}
		// invalid month
		if (parseFloat(mm) - 1 != dt.getMonth())
		{
			alert("Invalid month");
			return false;
		}
		// invalid year
		if (parseFloat(yy) != dt.getFullYear())
		{
			alert("Invalid year");
			return false;
		}
	}
	else
	{
		alert("Invalid date");
		return false;

	}
	return true;
}

function compareDate(startDate, endDate)
{
	var re = /^(\d{1,2})\-(\d{1,2})\-(\d{4})$/;
	if (startDate.match(re))
	{
		var mm = RegExp.$1;
		var dd = RegExp.$2;
		var yy = RegExp.$3;
		// try to create the same date using Date Object
		var startdt = new Date(parseFloat(yy), parseFloat(mm) - 1, parseFloat(dd));

		if (endDate.match(re))
		{
			var mmm = RegExp.$1;
			var ddd = RegExp.$2;
			var yyy = RegExp.$3;
			// try to create the same date using Date Object
			var enddt = new Date(parseFloat(yyy), parseFloat(mmm) - 1, parseFloat(ddd));

			var compDt = enddt.getTime() - startdt.getTime();
			// If past date is selected return false
			if (compDt < 0)
			{
				return false;
			}
		}
		else
		{
			alert("Invalid date");
			return false;
		}
	}
	else
	{
		alert("Invalid date");
		return false;
	}
	return true;
}

function clearVendorResults()
{
	$('#invVendorNm').removeAttr("readonly", "");
	$('#invVendorCd').removeAttr("readonly", "");
	$('#invVendorAddr').removeAttr("readonly", "");
	$('#invVendorNm').css("background-color", "");
	$('#invVendorCd').css("background-color", "");
	$('#invVendorAddr').css("background-color", "");
	$('#paymentTerms').css("background-color", "");
	// $('#paymentMethods').css("background-color", "");
	$('#payGroup').css("background-color", "");
	// $("#paymentMethods").removeAttr("disabled","");
	resetVendorValues();
}

function resetVendorValues()
{
	// $("#paymentMethods").removeAttr("disabled","");
	$('#invVendorNm').val("");
	$('#invVendorCd').val("");
	$('#invVendorSite').val("");
	$('#invPayGroup').val("");
	$('#invOrgName').val("");
	$('#invVendorAddr').val("");
	$('#paymentTerms').val("");
	$('#paymentMethods').val("");
	$('#payGroup').val("");
	$('#payTermComments').val("");
	$('#indDiscount').val("");

	// TODO
	/*
	 * if($("#payGroup").val() != "emptyVal") { var x=document.getElementById("payGroup"); var option=document.createElement("option"); option.text=" "; option.value="emptyVal"; try { // for IE
	 * earlier than version 8 x.add(option,x.options[null]); } catch (e) { x.add(option,null); } }
	 */
	// $("#payGroup").val("emptyVal");
	// $('#payGroup').attr('disabled','true');
}

function clearCommentSection()
{
	var commentsAreaVal = $('#limitedComments').val();
	$('#countdown1').html("250");
	$('#limitedComments').val("");
	$('#limitedComments').css('color', '#000000');
}

function clearApproverSection()
{
	$('#company').val("");
	$('#division').val("");
	$('#department').val("");
	$('#approver').find('option').remove();
}

function unLockInvoice(urnNum, queue)
{
	if (urnNum != null && ((window.event.clientX < 10 || event.clientX < 10) || (window.event.clientY < 10 || event.clientY < 10)))
	{
		releaseLock(urnNum, queue);
	}
	else if (newwindow != null && !newwindow.closed)
	{
		newwindow.close();
	}
}

function releaseLock(urnNum, queue)
{

	var url = "urnNum=" + urnNum + "&queue=" + queue + "&uid=" + Math.random();
	$.ajax(
	{
		type : "GET",
		url : 'unLockInvoiceOnClose.do',
		data : url,
		complete : function(data)
		{
		}
	});

	if (newwindow != null && !newwindow.closed)
	{
		newwindow.close();
	}
}

function adminReleaseLock(urnNum)
{

	var url = "urnNum=" + urnNum + "&uid=" + Math.random();
	$.ajax(
	{
		type : "GET",
		url : 'adminUnLockInvoiceOnClose.do',
		data : url,
		complete : function(data)
		{
		}
	});

	if (newwindow != null && !newwindow.closed)
	{
		newwindow.close();
	}
}

function readOnlyFormFields(formName)
{
	var fName = document.forms[formName];
	for ( var i = 0, fLen = fName.length; i < fLen; i++)
	{
		fName.elements[i].readonly = true;
	}
}

function adjustdesccount()
{
	var invdesc = document.getElementById('invoiceDesc').value;
	if (invdesc != '')
	{
		var descLen = invdesc.length;
		var remain = 250 - parseInt(descLen);
		$('#countdown').html(remain);
	}
}

function chkZero(elm)
{
	var val = elm.value;
	if (allTrim(val) == "0.00")
	{
		elm.value = '';
	}
}
function checkOnlyNumber(cellObj)
{

	var strString = cellObj.value;
	var result = /^[0-9 ]{1,50}$/.test(strString);

	if (result == false)
	{
		strString = strString.substring(0, strString.length);
		result = /^[0-9 ]{1,50}$/.test(strString);
		if (result == true)
		{
			cellObj.value = strString;
		}
		else
		{
			if (allTrim(strString) != "")
			{
				// cellObj.value=strString =
				// strString.substring(0,strString.length-1);
				cellObj.value = '';
				alert("Numbers only allowed");
			}
		}
		return false;
	}
	return true;
}

function alphanumericValidation(fieldId)
{
	var fieldVal = fieldId.value;

	var result = true;
	var exp = /^[A-Za-z0-9]{1,50}$/.test(fieldVal);
	if (!exp)
	{
		result = false;
	}

	if (result == false)
	{
		fieldVal = fieldVal.substring(0, fieldVal.length);
		result = /^[A-Za-z0-9]{1,50}$/.test(fieldVal);
		if (result == true)
		{
			fieldId.value = fieldVal;
		}
		else
		{
			if (allTrim(fieldVal) != "")
			{
				fieldId.value = '';
				alert("Alpha numeric values only allowed.");
				return false;

			}
		}
	}
	return true;
}

function alphanumericValidationHypenUnderScore(fieldId)
{
	var fieldVal = fieldId.value;

	var result = true;
	var exp = /^[A-Za-z0-9-_]{1,50}$/.test(fieldVal);
	if (!exp)
	{
		result = false;
	}

	if (result == false)
	{
		fieldVal = fieldVal.substring(0, fieldVal.length);
		result = /^[A-Za-z0-9 ]{1,50}$/.test(fieldVal);
		if (result == true)
		{
			fieldId.value = fieldVal;
		}
		else
		{
			if (allTrim(fieldVal) != "")
			{
				// fieldId.value=fieldVal =
				// fieldVal.substring(0,fieldVal.length-1);
				fieldId.value = '';
				alert("Alpha numeric values only allowed.");
				return false;

			}
		}
	}
	return true;
}

function vendorNameValidation(fieldId)
{
	var fieldVal = fieldId.value;

	var result = true;
	var exp = /^[A-Za-z0-9'& ]{1,50}$/.test(fieldVal);
	if (!exp)
	{
		result = false;
	}

	if (result == false)
	{
		fieldVal = fieldVal.substring(0, fieldVal.length);
		result = /^[A-Za-z0-9'& ]{1,50}$/.test(fieldVal);
		if (result == true)
		{
			fieldId.value = fieldVal;
		}
		else
		{
			if (allTrim(fieldVal) != "")
			{
				// fieldId.value=fieldVal =
				// fieldVal.substring(0,fieldVal.length-1);
				fieldId.value = '';
				alert("Special characters not allowed.");
				return false;

			}
		}
	}
	return true;
}

function adminNameEmailValidation(fieldId)
{
	var fieldVal = fieldId.value;

	var result = true;
	var exp = /^[A-Za-z0-9@._' ]{1,50}$/.test(fieldVal);
	if (!exp)
	{
		result = false;
	}

	if (result == false)
	{
		fieldVal = fieldVal.substring(0, fieldVal.length);
		result = /^[A-Za-z0-9@._' ]{1,50}$/.test(fieldVal);
		if (result == true)
		{
			fieldId.value = fieldVal;
		}
		else
		{
			if (allTrim(fieldVal) != "")
			{
				// fieldId.value=fieldVal =
				// fieldVal.substring(0,fieldVal.length-1);
				fieldId.value = '';
				alert("Special characters not allowed.");
				return false;

			}
		}
	}
	return true;
}
function adminUserNameValidation(fieldId)
{
	var fieldVal = fieldId.value;

	var result = true;
	var exp = /^[A-Za-z,.' ]{1,50}$/.test(fieldVal);
	if (!exp)
	{
		result = false;
	}

	if (result == false)
	{
		fieldVal = fieldVal.substring(0, fieldVal.length);
		result = /^[A-Za-z,.' ]{1,50}$/.test(fieldVal);
		if (result == true)
		{
			fieldId.value = fieldVal;
		}
		else
		{
			if (allTrim(fieldVal) != "")
			{
				fieldId.value = '';
				alert("Numerical values, Special characters not allowed.");
				return false;
			}
		}
	}
	return true;
}
function adminEmpId(cellObj)
{

	var strString = cellObj.value;
	var result = /^[0-9]{1,25}$/.test(strString);

	if (result == false)
	{
		strString = strString.substring(0, strString.length);
		result = /^[0-9]{1,25}$/.test(strString);
		if (result == true)
		{
			cellObj.value = strString;
		}
		else
		{
			if (allTrim(strString) != "")
			{
				// cellObj.value=strString =
				// strString.substring(0,strString.length-1);
				cellObj.value = '';
				alert("Numbers only allowed");
			}
		}
		return false;
	}
	return true;
}

function discountValidation(cellObj)
{

	var strString = cellObj.value;
	var result = /^[0-9.]{1,15}$/.test(strString);

	if (result == false)
	{
		strString = strString.substring(0, strString.length);
		result = /^[0-9.]{1,15}$/.test(strString);
		if (result == true)
		{
			cellObj.value = strString;
		}
		else
		{
			if (allTrim(strString) != "")
			{
				// cellObj.value=strString =
				// strString.substring(0,strString.length-1);
				cellObj.value = '';
				alert("Numbers only allowed");
			}
		}
		return false;
	}
	return true;
}
function GetFilename(url)
{
	if (url)
	{
		// var m = url.toString().match(/.*\/(.+?)/);
		var m = url.toString().match(/.*\/([^\/]+)/);
		if (m && m.length > 1)
		{
			return m[1];
		}
	}
	return "";
}

function closeChildWindows()
{
	if (newwindow != undefined && newwindow != null && !newwindow.closed)
	{
		newwindow.close();
	}
	if (glwindow != undefined && glwindow != null && !glwindow.closed)
	{
		glwindow.close();
	}
	if (lookupwindow != undefined && lookupwindow != null && !lookupwindow.closed)
	{
		lookupwindow.close();
	}
	if (newauditwindow != undefined && newauditwindow != null && !newauditwindow.closed)
	{
		newauditwindow.close();
	}
}

function closeRoleWindows()
{
	if (roleWindow != undefined && roleWindow != null && !roleWindow.closed)
	{
		roleWindow.close();
	}
}
function closeDelAppWindow()
{
	if (delAppWindow != undefined && delAppWindow != null && !delAppWindow.closed)
	{
		delAppWindow.close();
	}
}

function closeTierAppWindow()
{
	if (tierAppWindow != undefined && tierAppWindow != null && !tierAppWindow.closed)
	{
		tierAppWindow.close();
	}
}
function closeTierWindow()
{
	if (tierWindow != undefined && tierWindow != null && !tierWindow.closed)
	{
		tierWindow.close();
	}
}
function closeLocationWindow()
{

	if (locationWindow != undefined && locationWindow != null && !locationWindow.closed)
	{
		locationWindow.close();
	}
}
function resetVendorOnLoad()
{
	var vendorName = $('#invVendorNm').val();

	if (vendorName == null || allTrim(vendorName) == "")
	{
		resetVendorValues();
	}
}
function roundVal(val)
{
	var decimals = $("#currencyDecimals").val();
	if (decimals == null || decimals == undefined || decimals == '0')
	{
		decimals = $("[name=currencyDecimals]", window.opener.document).val();
	}	
	
	if (val == null)
	{
		return null;
	}
	var result = Math.round(val * Math.pow(10, decimals)) / Math.pow(10, decimals);
	return result;
}

function resetVndrDetails()
{
	var vendorSts = document.getElementById('invVendorCd').readOnly;
	if (!vendorSts)
	{
		$('#invVendorCd').val('');
		$('#invVendorNm').val('');
		$('#invVendorSite').val('');
		$('#invVendorSiteCode').val('');
		$('#invVendorTypeLookupCode').val('');
		$('#invVendorAddr').val('');
	}
}

function charSuppress(cellObj)
{
	var result = /[\|\n\r\{\}]/ig;
	if (result.test(cellObj.value))
	{
		alert('Pipe symbol ("|") and Curly braces and Enter key are not allowed.');
		cellObj.value = cellObj.value.replace(result, '');
	}
}


function textValidation(fieldId) {
	var fieldVal = fieldId.value;	
	var result = true;
	var exp = /^[A-Za-z0-9-_#\)\( ]{1,500}$/.test(fieldVal);
	
	if(exp == false) {
		if(allTrim(fieldVal) != "")
		{
			fieldId.value='';
			alert("Special characters not allowed.");
			return false;

		}
	}
		
	return true ;
}

function adminTextValidation(fieldId) {
	var fieldVal = fieldId.value;	
	var result = true;
	var exp = /^[A-Za-z0-9-_#\'\)\( ]{1,500}$/.test(fieldVal);
	
	if(exp == false) {
		if(allTrim(fieldVal) != "")
		{
			fieldId.value='';
			alert("Special characters not allowed.");
			return false;

		}
	}
		
	return true ;
}

function checkUnchkExcep()
{	
		
		$("#unselect").attr('checked', false);
		$("#exceptions").attr('disabled',true);
	    $("#exceptions").find("option").attr("selected", false);
		 $("#exceptions").css("background-color",'#C0C0C0');

	
}

function onExceptionFixed()
{
	if($('#unselect').val()!=undefined && $('#unselect').is(':checked') && $('#routeTo').val() != "P005") 
	{
		$('#routeTo').val("P005");
	}
}

function closeGLWindow()
{
	if (glwindow != undefined && glwindow != null && !glwindow.closed)
	{
		glwindow.close();
	}

}



function checkIsNumberAndLength(cellObj,length)
{
	
	var cellVal = allTrim(cellObj.value);
	if(cellVal==''){
	}else{
	var exp=/^\+?([0-9]\d*)$/;
	var bExp=exp.test(cellVal);
		if(bExp == true) 
		{
			if(cellVal.length > parseInt(length))
			{
				cellObj.value = cellVal.substring(0,parseInt(length));
				alert("Number's only allowed..!! Maximum "+length+" digits allowed.");
				return false;
			}

		}else{
			alert("Number's only allowed..!! ");
			cellObj.value="";
			return false;
		}
	}
		return true;
}


// invoice number validation
function alphaNumVendorValidation(cellObj)
{
	var cellVal = allTrim(cellObj.value);
	if( cellVal == '')
	{
		cellObj.value = '';
		return true;
	}
	
	var exp = /^[A-Za-z0-9-_#\)\( ]{1,120}$/.test(cellObj.value);
	if(exp == false) 
	{
		
		if(cellVal.length > 120)
		{
			cellObj.value = cellVal.substring(0,120);
			alert("Special characters not allowed. \n\nMaximum 120 Characters allowed for Invoice(s)");
		}
		else
		{
			cellObj.value = '';
			alert("Special characters not allowed.");
		}
		
		return false;
	}
	else
	{
		return true;
	}
}



//invoice number validation
function alphaNumValidation(cellObj)
{

	var cellVal = allTrim(cellObj.value);
	if( cellVal == '')
	{
		cellObj.value = '';
		return true;
	}
	
	var exp = /^[A-Za-z0-9-_#\)\( ]{1,20}$/.test(cellObj.value);
	if(exp == false) 
	{
		
		if(cellVal.length > 20)
		{
			cellObj.value = cellVal.substring(0,20);
			alert("Special characters not allowed. \n\nMaximum 20 Characters allowed for Invoice(s)");
		}
		else
		{
			cellObj.value = '';
			alert("Special characters not allowed.");
		}
		
		return false;
	}
	else
	{
		return true;
	}
}

//Query Answer validation
function QueryAnswerValidation(cellObj,limit)
{

	var cellVal = allTrim(cellObj.value);
	if( cellVal == '')
	{
		cellObj.value = '';
		return true;
	}
	
	var exp = /^[A-Za-z0-9-,. ]{1,200}$/.test(cellObj.value);
	if(exp == false) 
	{
		
		if(cellVal.length > limit)
		{
			cellObj.value = cellVal.substring(0,limit);
			alert("Special characters not allowed. \n\nMaximum "+ limit+" Characters allowed for Query");
		}
		else
		{
			cellObj.value = '';
			alert("Special characters not allowed.");
		}
		
		return false;
	}
	else
	{
		return true;
	}
}

function passwordStructureValidation(fieldId)
{
	var fieldVal = fieldId.value;
	var result = true;
	var exp = /^[A-Za-z0-9~!$%^&*_=+-<>?@#\(\)]{1,15}$/.test(fieldVal);
	if (!exp)
	{
		result = false;
	}

	if (result == false)
	{
		fieldVal = fieldVal.substring(0, fieldVal.length);
		result = /^[A-Za-z0-9~!$%^&*_=+-<>?@#\(\)]{1,15}$/.test(fieldVal);
		if (result == true)
		{
			fieldId.value = fieldVal;
		}
		else
		{
			if (allTrim(fieldVal) != "")
			{
				fieldId.value = '';
				alert("Your Password must be the combinations of these characters\" A-Z a-z 0-9 ~ ! @ # $ % ^ & * ( ) _ - + | < > ?\"");
				return false;

			}
		}
	}
	return true;
}


