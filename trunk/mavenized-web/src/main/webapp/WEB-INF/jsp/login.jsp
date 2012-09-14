<%@page contentType="text/html" pageEncoding="UTF-8" session="false"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>

    <head>
        <title>Login</title>
    </head>
    <body>
        <div class="container">
            <div class="row">
                <div class="hero-unit">
                    <h2>Welcome</h2>
                    <div class="pull-right">
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
