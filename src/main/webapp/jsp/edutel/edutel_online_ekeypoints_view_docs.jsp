<%@ taglib prefix="s" uri="/struts-tags"%>
<s:iterator value="onlineEKeyPointsList" var="docs">
	<div class="img" onclick="downloadEKeyPoints('${oeKeyPointsId}');" style="cursor:pointer;">
		<img src="images/png/${uploadFileScreenShot}" alt="${uploadFileScreenShot}"
		 onclick="downloadEKeyPoints('${oeKeyPointsId}');" width="100" height="100" style="cursor:pointer;">
		<div class="desc">${oeKeyPointsName}</div>
		<div class="desc1">Subject : ${oeSubject}</div>
		<div class="desc1">Chapter : ${oeChapter}</div>
	</div>
</s:iterator>
<s:if test="onlineEKeyPointsList.size==0">
<table>
<tr><td valign="baseline"><img src="images/png/warning_48.png" /></td><td valign="middle">&nbsp;&nbsp;&nbsp;<span style="color:#336699;font-size:24px;">E-KeyPoints Not Found</span></td></tr>
</table>

</s:if>