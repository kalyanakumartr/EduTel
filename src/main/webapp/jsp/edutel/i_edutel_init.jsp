<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	response.setContentType("text/html; charset=UTF-8");
	request.setCharacterEncoding("UTF8");
	response.setHeader("Cache-Control", "no-store"); //HTTP 1.1
	response.setHeader("Pragma", "no-cache"); //HTTP 1.0
	response.setDateHeader("Expires", -1); //prevents caching at the proxy server
%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="com.hbs.edutel.common.model.interfaces.IUsers"%>
<%@ page import="com.hbs.edutel.common.model.interfaces.IUserRoles"%>
<%@ page import="com.hbs.edutel.util.CommonUtil"%>
<%@ page import="com.hbs.edutel.util.common.consts.ConstInterface"%>
<%@ page import="com.hbs.edutel.util.common.ConstEnumUtil.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.hbs.edutel.util.common.property.factory.PropFactory"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html lang="en" class="no-js">
<!--[if IE 8 ]><html class="ie ie8" lang="en"> <![endif]-->
<!--[if IE 9 ]><html class="ie ie9" lang="en"> <![endif]-->
<!--[if (gte IE 9)|!(IE)]><!-->
<!--<![endif]-->
<head>
<link type="text/css" rel="stylesheet" href="css/dropdown.css">
<link type="text/css" rel="stylesheet" href="css/dropdown_popupmenu.css">
<link type="text/css" rel="stylesheet" href="css/dropdown_one.css">
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/jquery-1.9.1.js"></script>
<script type="text/javascript" src="js/jquery.validate.js"></script>
<script type="text/javascript" src="js/jquery.bxslider.js"></script>
<script type='text/javascript' src="js/datepicker/utils/zapatec.js"></script>
<script type="text/javascript" src="js/datepicker/zpcal/src/calendar.js"></script>
<script type="text/javascript" src="js/datepicker/zpcal/lang/calendar-en.js"></script>
<link href="js/datepicker/zpcal/themes/aqua.css" rel="stylesheet" type="text/css">
<link type="text/css" rel="stylesheet" href="css/jquery.bxslider.css" />
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<link rel="stylesheet" type="text/css" href="css/circle.css" />
<script type="text/javascript" src="js/progress-circle.js"></script>
<link type="text/css" rel="stylesheet" href="css/style/style.css">
<link rel="stylesheet" type="text/css" href="css/default.css" />
<link rel="stylesheet" type="text/css" href="css/bookblock.css" />
<script src="js/modernizr.custom.js"></script>
<link type="text/css" rel="stylesheet" href="css/i_edutel_style.css">
<link type="text/css" rel="stylesheet" href="css/jquery-ui.css">
<script type="text/javascript" src="js/jquery-ui.js"></script>
<title><%=PropFactory.getInstance().getProperty(EGeneral.Project_Title)%></title>
<link href="css/jquery.dataTables.css" rel="stylesheet" type="text/css" />
<link href="css/jquery.dataTables_themeroller.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery.dataTables.js"></script>
<link href="css/jquery.dataTables_themeroller.css" rel="stylesheet" type="text/css" />
<link href="css/demo_page.css" rel="stylesheet" type="text/css" />
<link href="css/jquery-ui-1.8.4.custom.css" rel="stylesheet" type="text/css" />
<script src="js/jquery.dataTables.js"></script>
<script type="text/javascript" src="js/commonUtil.js"></script>
<script type="text/javascript" src="js/tinymce/tinymce.min.js"></script>
</head>
<s:set name="Result_MCQ_Practice_Exam"><%=ConstInterface.Result_MCQ_Practice_Exam%></s:set>