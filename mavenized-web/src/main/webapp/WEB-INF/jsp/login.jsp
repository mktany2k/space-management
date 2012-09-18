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
                <div class="span4 offset4 well">
                    <legend>Please Sign In</legend>
                    <div class="alert alert-error">
                        <a class="close" data-dismiss="alert" href="#">×</a>Incorrect Username or Password!
                    </div>
                    <s:form acceptcharset="UTF-8">
                        <s:textfield key="username" cssClass="span4" placeholder="Username"/>
                        <s:password key="password" cssClass="span4" placeholder="Password"/>
                        <label class="checkbox">
                            <s:checkbox key="remember" value="1">Remember Me</s:checkbox>
                        </label>
                        <s:a key="submit" cssClass="btn btn-info btn-block" href="#">Sign in</s:a>
                        <%--<s:submit name="submit" cssClass="btn btn-info btn-block" value="Sign in"/>--%>
                        <!--<button type="submit" name="submit" class="btn btn-info btn-block">Sign in</button>-->
                    </s:form>
                </div>
            </div>
        </div>
    </body>
</html>
