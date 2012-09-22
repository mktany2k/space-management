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
                    <s:form acceptcharset="UTF-8" validate="true" action="Login" namespace="/example">
                        <s:textfield key="username" cssClass="span4" placeholder="Username" required="true"/>
                        <s:password key="password" cssClass="span4" placeholder="Password"/>
                        <label class="checkbox">
                            <s:checkbox key="remember" value="1">Remember Me</s:checkbox>
                        </label>
                        <button type="submit" class="btn btn-info btn-block">Sign in</button>
                    </s:form>
                </div>
            </div>
        </div>
        <script>
                $('#Login').submit(function(){
                    alert("submit");
                });
        </script>
    </body>
</html>
