<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>

<script type="text/javascript">
	function setSize()
	{
		var viewportwidth;
		var viewportheight;

		// the more standards compliant browsers (mozilla/netscape/opera/IE7) use window.innerWidth and window.innerHeight

		if (typeof window.innerWidth != 'undefined')
		{
			viewportwidth = window.innerWidth, viewportheight = window.innerHeight
		}

		// IE6 in standards compliant mode (i.e. with a valid doctype as the first line in the document)

		else if (typeof document.documentElement != 'undefined' && typeof document.documentElement.clientWidth != 'undefined' && document.documentElement.clientWidth != 0)
		{
			viewportwidth = document.documentElement.clientWidth, viewportheight = document.documentElement.clientHeight
		}

		// older versions of IE
		else
		{
			viewportwidth = document.getElementsByTagName('body')[0].clientWidth, viewportheight = document.getElementsByTagName('body')[0].clientHeight
		}

		//-->
		var iframeElement = parent.document.getElementById("reportFrame");
		iframeElement.style.width = viewportwidth - 30 + "px"; //100px or 100% 
	}
</script>

<%@ include file="/jsp/common/header.jsp"%>

<center>
	<%
		String reportParam = request.getParameter("reportParam");
	%>
	<IFRAME SRC="${reportURL}" id="reportFrame" WIDTH="100%" HEIGHT="700px"
		onload="setSize()" frameborder="0" scrolling="no"></IFRAME>
</center>
<div
	style="min-height: 100%; position: relative; z-index: -1; padding: 20px">
	<div
		style="position: absolute; bottom: 0; width: 100%; height: 5%; padding: 10px">
		<%@ include file="/jsp/common/i_footer.jsp"%>
	</div>
</div>
</body>
</html>
