<%@page contentType="text/html" pageEncoding="UTF-8" session="false"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="shortcut icon" href="<s:url value="/ico/favicon.ico"/>">
        <link href="<s:url value="/css/bootstrap.min.css"/>" rel="stylesheet">
        <style type="text/css">
            body {
                padding-top: 60px;
                padding-bottom: 40px;
            }
        </style>
        <title>Login</title>
    </head>
    <body>
        <div class="container">
            <div class="row-fluid">
                <div class="span12">
                    <div class="span4 pull-right">
                        <h2>Login</h2>
                        <s:form action="signin" theme="simple" cssClass="form-horizontal">
                            <s:label key="username"/>
                            <s:textfield key="username"/>
                            <s:label key="password"/>
                            <s:password key="password"/>
                            <p><s:a cssClass="btn btn-primary" action="">Login</s:a></p>
                        </s:form>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
