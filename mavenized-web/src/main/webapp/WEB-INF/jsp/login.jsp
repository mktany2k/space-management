<%@page contentType="text/html" pageEncoding="UTF-8" session="false"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Login</title>
    </head>
    <body>
        <div class="hero-unit">
            <div class="container">
                <div class="row">
                    <div class="well span7">
                        <h2>Welcome</h2>
                    </div>
                    <div class="well span5 pull-right">
                        <fieldset>
                            <legend>Login</legend>
                            <s:form action="signin" theme="simple" cssClass="form">
                                <s:label key="username"/>
                                <s:textfield key="username"/>
                                <s:label key="password"/>
                                <s:password key="password"/>
                                <p><s:a cssClass="btn btn-primary" action="">Login</s:a></p>
                            </s:form>
                        </fieldset>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
