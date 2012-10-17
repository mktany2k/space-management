<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div class="ui-dialog-overlay" onclick="javascript: closeSvgLot(event);">
</div>
<div class="ui-dialog-content">
	<input id="lotImage" class="ui-dialog-upload" type="file" accept="image/*"
		   onchange="javascript: uploadFile(event, this);"/>
	<input id="lotName" title="Lot Name" class="ui-dialog-input ui-dialog-header" maxlength="45" value="${lot.name}" type="text" 
		   onchange="javascript: dirtyLot(event, this);" 
		   onkeypress="javascript: keyDone(event, this);"/>
	<span class="ui-icon ui-icon-closethick ui-dialog-close" onclick="javascript: closeSvgLot(event);" title="Close"></span>
	<div class="ui-dialog-body">
		<table>
			<tr>
				<td class="ui-dialog-image-cell">
					<div>
						<img class="ui-dialog-image" 
							 title="<c:choose><c:when test="${lot.image ne 'image-not-available.png'}">${lot.name}</c:when><c:otherwise>Image not available</c:otherwise></c:choose>" 
							 src="../images/lot/${lot.image}"
							 onclick="javascript: $('.ui-dialog-upload').click();"
						/>
					</div>
					<div title="Creation date" class="ui-dialog-details-static"><fmt:formatDate pattern="dd MMM yyyy" value="${lot.dtCreated}" type="DATE"/></div>
					<div title="Last modified date" class="ui-dialog-details-static"><fmt:formatDate pattern="dd MMM yyyy" value="${lot.dtModified}" type="DATE"/></div>
					<div title="Updated by" class="ui-dialog-details-static">${lot.updatedBy}</div>
				</td>
				<td class="ui-dialog-info-cell">
					<textarea id="lotDescription" 
							  title="Lot Description" 
							  class="ui-dialog-input ui-dialog-info-desc" 
							  rows="1" 
							  cols="1" 
							  onchange="javascript: dirtyLot(event, this);">${lot.description}</textarea>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<div id="lot-tab" class="ui-dialog-details">
						<ul>
							<li><a href="#tab-contract" class="ui-dialog-tab-link">Contract</a></li>
							<li><a href="#tab-account" class="ui-dialog-tab-link">Account</a></li>
						</ul>
						<div id="tab-contract" class="ui-dialog-tab">
							Test
						</div>
						<div id="tab-account" class="ui-dialog-tab">
							<input type="text" 
								   class="ui-dialog-input ui-dialog-details-left" 
								   value="${lot.accountCode}" 
								   maxlength="45" 
								   onchange="javascript: dirtyLot(event, this);"
								   onkeypress="javascript: keyDone(event, this);"/>
						</div>
					</div>
				</td>
			</tr>
		</table>
	</div>
	<div class="ui-dialog-footer">
		<span title="Close" class="ui-dialog-button" onclick="javascript: closeSvgLot(event);">Cancel</span>
		<span title="Save" class="ui-dialog-button" onclick="javascript: updateSvgLot(event, ${lot.lotKey.projectId}, ${lot.lotKey.planId}, ${lot.lotKey.lotId});">Apply</span>
	</div>
</div>