<%@ taglib prefix="s" uri="/struts-tags"%>
<s:iterator value="onlineEBluePrintList" var="docs">
	<div class="img" onclick="downloadEBluePrint('${oeBluePrintId}');" style="cursor:pointer;">
		<img src="images/png/${uploadFileScreenShot}" alt="${uploadFileScreenShot}"
		 onclick="downloadEBluePrint('${oeBluePrintId}');" width="100" height="80" style="cursor:pointer;">
		<div class="desc">${oeBluePrintName}</div>
	</div>
</s:iterator>