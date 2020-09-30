<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/strict.dtd">
<%
	response.setContentType("text/html; charset=UTF-8");
	request.setCharacterEncoding("UTF8");
	response.setHeader("Cache-Control", "no-store"); //HTTP 1.1
	response.setHeader("Pragma", "no-cache"); //HTTP 1.0
	response.setDateHeader("Expires", 0); //prevents caching at the proxy server
%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="com.hbs.edutel.common.model.interfaces.IUsers"%>
<%@ page import="com.hbs.edutel.common.model.interfaces.IUserRoles"%>
<%@ page import="com.hbs.edutel.util.CommonUtil"%>
<%@ page import="com.hbs.edutel.util.common.consts.ConstInterface"%>
<%@ page import="com.hbs.edutel.util.common.ConstEnumUtil.*"%>
<%@ page import="java.util.*"%>
<%@ page
	import="com.hbs.edutel.util.common.property.factory.PropFactory"%>
<html>
<!--[if IE 8 ]><html class="ie ie8" lang="en"> <![endif]-->
<!--[if IE 9 ]><html class="ie ie9" lang="en"> <![endif]-->
<!--[if (gte IE 9)|!(IE)]><!-->
<!--<![endif]-->
<head>
<link type="text/css" rel="stylesheet" href="css/style/style.css">
<link type="text/css" rel="stylesheet" href="css/dropdown.css">
<link type="text/css" rel="stylesheet" href="css/dropdown_popupmenu.css">
<link type="text/css" rel="stylesheet" href="css/dropdown_one.css">

<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/jquery-1.9.1.js"></script>
<script type="text/javascript" src="js/jquery.validate.js"></script>
<script type="text/javascript" src="js/jquery.reveal.js"></script>
<script type="text/javascript" src="js/jquery.bxslider.js"></script>
<link type="text/css" rel="stylesheet" href="css/jquery.bxslider.css" />


<title><%=PropFactory.getInstance().getProperty(EGeneral.Project_Title)%></title>