<!DOCTYPE HTML>
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
<head>
<title>EduTel Academy</title>
<link href="academia/css/style.css" rel="stylesheet" type="text/css"
	media="all" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<link href='http://fonts.googleapis.com/css?family=Open+Sans'
	rel='stylesheet' type='text/css'>
<link rel="stylesheet" href="academia/css/responsiveslides.css">

<link href="css/bootbox/components.css" rel="stylesheet" type="text/css" />
<link href="css/bootbox/plugins.css" rel="stylesheet" type="text/css" />

<script type="text/javascript" src="js/jquery-1.11.0.min.js"></script>

<script src="js/bootbox/bootbox.min.js" type="text/javascript"></script>
<script src="js/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>

<script src="academia/js/responsiveslides.min.js"></script>

<script type="text/javascript" src="js/jquery.vticker-min.js"></script>

<script>
	// You can also use "$(window).load(function() {"
	$(function() {
		// Slideshow 1
		$("#slider1").responsiveSlides({
			maxwidth : 2500,
			speed : 600
		});
	});
</script>
<!--light-box-->
<script type="text/javascript" src="academia/js/jquery.lightbox.js"></script>
<link rel="stylesheet" type="text/css" href="academia/css/lightbox.css"
	media="screen">
<script type="text/javascript">
	$(function() {
		$('.gallery a').lightBox();
	});
</script>
</head>