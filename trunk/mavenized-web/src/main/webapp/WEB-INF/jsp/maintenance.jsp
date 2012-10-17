<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix='fn' uri="http://java.sun.com/jsp/jstl/functions"%>
<html>
	<head>
		<title><c:out value="${page_title}" escapeXml="false"/></title>
		<link rel="shortcut icon" href="../images/svg.ico"/>
		<!--
		-->
		<link rel="stylesheet" href="../css/default.css" type="text/css"/>
		<link rel="stylesheet" href="../js/SlickGrid-1.4.3-62/slick.grid.css" type="text/css" media="screen"/>
		<link rel="stylesheet" href="../js/SlickGrid-1.4.3-62/css/smoothness/jquery-ui-1.8.5.custom.css" type="text/css" media="screen"/>
		<link rel="stylesheet" href="../js/SlickGrid-1.4.3-62/examples/examples.css" type="text/css" media="screen"/>
		<script type="text/javascript" src="../js/fn-maintenance.js"></script>
		<!--
		<style type="text/css">@import "../js/jquery-ui-1.8.13.custom/css/smoothness/jquery-ui-1.8.13.custom.css";</style>
		-->
		<!--
		<script type="text/javascript" src="../js/jquery-1.6.1.min.js"></script>
		<script type="text/javascript" src="../js/jquery-ui-1.8.13.custom/js/jquery-ui-1.8.13.custom.min.js"></script>
		-->

		<!-- jquery-blueimp starts 
		<link rel="stylesheet" href="../js/blueimp/jquery.fileupload-ui.css" type="text/css"/>
		<script type="text/javascript" src="../js/blueimp/jquery.fileupload.js"></script>
		<script type="text/javascript" src="../js/blueimp/jquery.fileupload-ui.js"></script>
		<script type="text/javascript" src="../js/blueimp/jquery.iframe-transport.js"></script>
		<script type="text/javascript"  src="../js/jquery.tmpl.min.js"></script>
		-->
		<!-- jquery-blueimp ends -->

		<!-- SlickGrid starts -->
		<script src="../js/SlickGrid-1.4.3-62/lib/firebugx.js"></script>
		<script src="../js/SlickGrid-1.4.3-62/lib/jquery-1.4.3.min.js"></script>
		<script src="../js/SlickGrid-1.4.3-62/lib/jquery-ui-1.8.5.custom.min.js"></script>
		<script src="../js/SlickGrid-1.4.3-62/lib/jquery.event.drag-2.0.min.js"></script>
		<script src="../js/SlickGrid-1.4.3-62/plugins/slick.cellrangeselector.js"></script>
		<script src="../js/SlickGrid-1.4.3-62/plugins/slick.cellselectionmodel.js"></script>
        <script src="../js/SlickGrid-1.4.3-62/slick.core.js"></script>
		<script src="../js/SlickGrid-1.4.3-62/slick.editors.js"></script>
		<script src="../js/SlickGrid-1.4.3-62/slick.grid.js"></script>
		<!-- SlickGrid ends -->
		<script type="text/javascript">
			$(document).ready(function() {
				initHTML(event);

				var data = [];
				var columns = [
					{id:"title", name:"Title", field:"title", width:120, cssClass:"cell-title", editor:TextCellEditor, validator:requiredFieldValidator},
		            {id:"desc", name:"Description", field:"description", width:100, editor:LongTextCellEditor},
		            {id:"duration", name:"Duration", field:"duration", editor:TextCellEditor},
					{id:"%", name:"% Complete", field:"percentComplete", cssClass:"cell-pc", width:80, resizable:false, formatter:GraphicalPercentCompleteCellFormatter, editor:PercentCompleteCellEditor},
					{id:"start", name:"Start", field:"start", minWidth:60, editor:DateCellEditor},
					{id:"finish", name:"Finish", field:"finish", minWidth:60, editor:DateCellEditor},
					{id:"effort-driven", name:"Effort Driven", field:"effortDriven", cssClass:"cell-effort-driven", width:80, minWidth:20, maxWidth:80, formatter:BoolCellFormatter, editor:YesNoCheckboxCellEditor}
				];
				var options = {
					editable: true,
					enableAddRow: true,
					enableCellNavigation: true,
					asyncEditorLoading: false,
		            autoEdit: true
				};
				$(function() {
					for (var i = 0; i < 500; i++) {
						var d = (data[i] = {});
		
						d["title"] = "Task " + i;
		                d["description"] = "This is a sample task description.\n  It can be multiline";
						d["duration"] = "5 days";
						d["percentComplete"] = Math.round(Math.random() * 100);
						d["start"] = "17/09/2010";
						d["finish"] = "14/10/2011";
						d["effortDriven"] = (i % 5 == 0);
					}
					var grid = new Slick.Grid("#maintenance-grid", data, columns, options);
					//grid.registerPlugin(new Slick.CellRangeSelector());
					//grid.setSelectionModel(new Slick.CellSelectionModel());
		            grid.onAddNewRow.subscribe(function(e, args) {
		                var item = args.item;
		                var column = args.column;
		                grid.invalidateRow(data.length);
		                data.push(item);
		                grid.updateRowCount();
		                grid.render();
		            });
				})
			});

			function requiredFieldValidator(value) {
				if (value == null || value == undefined || !value.length)
					return {valid:false, msg:"This is a required field"};
				else
					return {valid:true, msg:null};
			}
		</script>
		<style>
			.cell-title {
				font-weight: bold;
			}
			
			.cell-pc {
				vertical-align: middle;
			}
	
			.cell-effort-driven {
				text-align: center;
			}
		</style>
	</head>
	<body>
		<table>
			<tr>
				<td class="svg-body">
					<div id="maintenance-accordian" class="ui-maintenance-accordian ui-height-10pc">
						<h3><a href="#" class="text-bolded">Project</a></h3>
						<div class="ui-maintenance-tab">
							<div class="ui-maintenance-inner">
								<table class="ui-display-table">
									<tr>
										<td class="ui-display-table-header">
											Maintenance
										</td>
									</tr>
									<tr>
										<td style="height: 6px;"></td>
									</tr>
									<tr>
										<td style="height: 100%;">
											<div style="width: 800px;">
												<div id="maintenance-grid" style="width: 100%; height: 430px;"></div>
											</div>
										</td>
									</tr>
									
									<!--
									<tr>
										<td width="1px;" style="vertical-align: top; background-color: #ccc;">
											<table class="ui-display-cell-font" border="0" cellspacing="0" cellpadding="4">
												<tr>
													<td class="ui-display-cell-header" onclick="javascript: find(event, 'input-hc');">
														<div class="ui-display-header-input-hc">Find</div>
													</td>
												</tr>
												<tr>
													<td class="ui-display-cell-header" onclick="javascript: find(event, 'input-lot');">
														<div class="ui-display-header-input-lot">Lot</div>
													</td>
												</tr>
												<tr>
													<td class="ui-display-cell-header" onclick="javascript: find(event, 'input-name');">
														<div class="ui-display-header-input-name">Name</div>
													</td>
												</tr>
												<tr>
													<td class="ui-display-cell-header" onclick="javascript: find(event, 'input-desc');">
														<div class="ui-display-header-input-desc">Description</div>
													</td>
												</tr>
												<tr>
													<td class="ui-display-cell-header" onclick="javascript: find(event, 'input-tenant');">
														<div class="ui-display-header-input-tenant">Tenant</div>
													</td>
												</tr>
												<tr>
													<td class="ui-display-cell-header" onclick="javascript: find(event, 'input-size');">
														<div class="ui-display-header-input-size">Size (${unit})</div>
													</td>
												</tr>
											</table>
										</td>
										<td width="1px;"></td>
										<td id="maintenance-container" style="overflow: auto;">
											<div style="width: 1px;">
												<table class="ui-display-cell-font" border="0" cellspacing="0" cellpadding="0">
													<tr>
														<c:forEach var="lot" items="${lots}" varStatus="status">
															<td title="${lot.hashCode}" class="ui-display-cell-header ui-display-cell-header-id" style="text-align: center;" onclick="javascript: maintainLot(event, ${lot.hashCode});">
																<div id="div-hc-${lot.hashCode}" class="ui-display-cell">
																	<input id="input-hc-${lot.hashCode}" 
																		   class="ui-display-cell-header-text ui-highlight-${lot.hashCode}" 
																		   onfocus="javascript: focusInput(event, 'input-hc', this, $('#input-hc-${lot.hashCode}').offset());" 
																		   onblur="javascript: scrollInput(event, this, 0, 0);" 
																		   type="button" 
																		   value="${lot.hashCode}" 
																		   readonly="readonly"
																	/>
																</div>
															</td>
														</c:forEach>
													</tr>
													<tr>
														<c:forEach var="lot" items="${lots}" varStatus="status">
															<td title="${lot.lot}" class="ui-display-cell-content">
																<div id="div-lot-${lot.hashCode}" class="ui-display-cell">
																	<input id="input-lot-${lot.hashCode}" 
																		   class="ui-display-cell-text ui-highlight-${lot.hashCode}" 
																		   onfocus="javascript: focusInput(event, 'input-lot', this, $('#input-lot-${lot.hashCode}').offset());" 
																		   onblur="javascript: scrollInput(event, this, 0, 0);" 
																		   type="text" 
																		   value="${lot.lot}"
																	/>
																</div>
															</td>
														</c:forEach>
													</tr>
													<tr>
														<c:forEach var="lot" items="${lots}" varStatus="status">
															<td title="${lot.name}" class="ui-display-cell-content">
																<div id="div-name-${lot.hashCode}" class="ui-display-cell">
																	<input id="input-name-${lot.hashCode}" 
																		   class="ui-display-cell-text ui-highlight-${lot.hashCode}" 
																		   onfocus="javascript: focusInput(event, 'input-name', this, $('#input-name-${lot.hashCode}').offset());" 
																		   onblur="javascript: scrollInput(event, this, 0, 0);" 
																		   type="text" 
																		   value="${lot.name}"
																	/>
																</div>
															</td>
														</c:forEach>
													</tr>
													<tr>
														<c:forEach var="lot" items="${lots}" varStatus="status">
															<td title="${lot.summary}" class="ui-display-cell-content ui-highlight-${lot.hashCode}">
																<div id="div-desc-${lot.hashCode}" class="ui-display-cell">
																	<textarea id="input-desc-${lot.hashCode}" 
																			  class="ui-display-cell-text ui-highlight-${lot.hashCode}" 
																			  onfocus="javascript: focusInput(event, 'input-desc', this, $('#input-desc-${lot.hashCode}').offset());" 
																			  onblur="javascript: scrollInput(event, this, 0, 0);">${lot.description}</textarea>
																</div>
															</td>
														</c:forEach>
													</tr>
													<tr>
														<c:forEach var="lot" items="${lots}" varStatus="status">
															<td title="${lot.tenant}" class="ui-display-cell-content">
																<div id="div-tenant-${lot.hashCode}" class="ui-display-cell">
																	<input id="input-tenant-${lot.hashCode}" 
																		   class="ui-display-cell-text ui-highlight-${lot.hashCode}" 
																		   onfocus="javascript: focusInput(event, 'input-tenant', this, $('#input-tenant-${lot.hashCode}').offset());" 
																		   onblur="javascript: scrollInput(event, this, 0, 0);" 
																		   type="text" 
																		   value="${lot.tenant}"
																	/>
																</div>
															</td>
														</c:forEach>
													</tr>
													<tr>
														<c:forEach var="lot" items="${lots}" varStatus="status">
															<td title="${lot.size}" class="ui-display-cell-content" onclick="javascript: modifyCell(event, 'size', ${lot.hashCode}, 'input');">
																<div id="div-size-${lot.hashCode}" class="ui-display-cell">
																	<input id="input-size-${lot.hashCode}" 
																		   class="ui-display-cell-text ui-highlight-${lot.hashCode}" 
																		   onfocus="javascript: focusInput(event, 'input-size', this, $('#input-size-${lot.hashCode}').offset());" 
																		   onblur="javascript: scrollInput(event, this, 0, 0);" 
																		   type="text" 
																		   value="${lot.size}"
																	/>
																</div>
															</td>
														</c:forEach>
													</tr>
												</table>
											</div>
										</td>
									</tr>
									-->
								</table>
							</div>
						</div>
						<h3><a href="#" class="text-bolded">Settings</a></h3>
						<div class="ui-maintenance-tab">
							<div class="ui-maintenance-inner">
							</div>
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div class="ui-menu-tab-bottom-nav" align="right" style="padding: 4px;">
						<input type="button" name="op" value="Back" class="ui-dialog-button" onclick="javascript: location.href = 'open';"/>
						<input type="button" name="op" value="Update" class="ui-dialog-button" onclick="javascript: updateMaintenance(event);"/>
					</div>
				</td>
			</tr>
		</table>
	</body>
</html>