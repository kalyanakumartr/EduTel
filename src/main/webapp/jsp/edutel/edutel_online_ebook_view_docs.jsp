<%@ taglib prefix="s" uri="/struts-tags"%>
<s:iterator value="onlineEBookList" var="docs">
<div class="img" onclick="downloadEBook('${oeBookId}');" style="cursor:pointer;">
		<img src="images/png/${uploadFileScreenShot}" alt="${uploadFileScreenShot}"
		 onclick="downloadEBook('${oeBookId}');" width="100" height="80" style="cursor:pointer;">
		<div class="desc">${oeBookName}</div>
	</div>
</s:iterator>
<s:if test="onlineEBookList.size==0">
<table>
<tr><td valign="baseline"><img src="images/png/warning_48.png" /></td><td valign="middle">&nbsp;&nbsp;&nbsp;<span style="color:#336699;font-size:24px;">E-Books Not Found</span></td></tr>
</table>

</s:if>