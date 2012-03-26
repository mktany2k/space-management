<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div id="fileupload">
       <div class="fileupload-buttonbar">
           <label class="fileinput-button">
               <span>Add files...</span>
               <input type="file" name="files[]" multiple="multiple"/>
           </label>
           <button type="button" class="delete">Delete files</button>
           <button type="submit" class="start ui-hidden">Start upload</button>
           <!--
           <button type="reset" class="cancel">Cancel upload</button>
           -->
       </div>
    <div class="fileupload-content">
        <table class="files"></table>
        <div class="fileupload-progressbar"></div>
    </div>
</div>
<script id="template-upload" type="text/x-jquery-tmpl">
    <tr class="template-upload{{if error}} ui-state-error{{/if}}">
        <td class="preview"></td>
        <td class="name"><c:out value="${'${'}name}" escapeXml="false"/></td>
        <td class="size"><c:out value="${'${'}sizef}" escapeXml="false"/></td>
        {{if error}}
            <td class="error" colspan="2">[template-upload] Error:
                {{if error === 'maxFileSize'}}File is too big
                {{else error === 'minFileSize'}}File is too small
                {{else error === 'acceptFileTypes'}}Filetype not allowed
                {{else error === 'maxNumberOfFiles'}}Max number of files exceeded
                {{else}}<c:out value="${'${'}error}" escapeXml="false"/>
                {{/if}}
            </td>
        	<td class="cancel"><button>Cancel</button></td>
        {{else}}
            <td class="progress"><div></div></td>
        	<td class="cancel"><button>Cancel</button></td>
            <td class="start ui-hidden"><button>Start</button></td>
			<!--
			-->
        {{/if}}
    </tr>
</script>
<script id="template-download" type="text/x-jquery-tmpl">
	<tr class="template-download{{if error}} ui-state-error{{/if}}">
        {{if error}}
            <td></td>
            <td class="name"><c:out value="${'${'}name}" escapeXml="false"/></td>
            <td class="size"><c:out value="${'${'}sizef}" escapeXml="false"/></td>
            <td class="error" colspan="2">[template-download] Error:
                {{if error === 1}}File exceeds upload_max_filesize (php.ini directive)
                {{else error === 2}}File exceeds MAX_FILE_SIZE (HTML form directive)
                {{else error === 3}}File was only partially uploaded
                {{else error === 4}}No File was uploaded
                {{else error === 5}}Missing a temporary folder
                {{else error === 6}}Failed to write file to disk
                {{else error === 7}}File upload stopped by extension
                {{else error === 'maxFileSize'}}File is too big
                {{else error === 'minFileSize'}}File is too small
                {{else error === 'acceptFileTypes'}}Filetype not allowed
                {{else error === 'maxNumberOfFiles'}}Max number of files exceeded
                {{else error === 'uploadedBytes'}}Uploaded bytes exceed file size
                {{else error === 'emptyResult'}}Empty file upload result
                {{else}}<c:out value="${'${'}error}" escapeXml="false"/>
                {{/if}}
            </td>
        {{else}}
            <td class="preview">
                {{if thumbnail_url}}
                    <a href="<c:out value="${'${'}url}" escapeXml="false"/>" target="_blank"><img src="<c:out value="${'${'}thumbnail_url}" escapeXml="false"/>"></a>
                {{/if}}
            </td>
            <td class="name">
                <a href="<c:out value="${'${'}url}" escapeXml="false"/>"{{if thumbnail_url}} target="_blank"{{/if}}><c:out value="${'${'}name}" escapeXml="false"/></a>
            </td>
            <td class="size"><c:out value="${'${'}sizef}" escapeXml="false"/></td>
            <td colspan="2"></td>
        {{/if}}
        <td class="delete">
            <button data-type="<c:out value="${'${'}delete_type}" escapeXml="false"/>" data-url="<c:out value="${'${'}delete_url}" escapeXml="false"/>">Delete</button>
        </td>
    </tr>
</script>