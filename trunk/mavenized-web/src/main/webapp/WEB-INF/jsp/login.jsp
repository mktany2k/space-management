<%@page contentType="text/html" pageEncoding="UTF-8" session="false"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
    <head>
        <title><fmt:message key="project.name"/> - <s:text name="action"/></title>
    </head>
    <body>
        <div class="container">
            <div class="row">
                <div class=" well span4 offset4">
                    <s:form acceptcharset="UTF-8" validate="true" action="Login" namespace="/auth">
                        <fieldset>
                            <div id="legend">
                                <legend>Please Sign In</legend>
                            </div>
                            <div class="alert alert-info">
                                <a class="close" data-dismiss="alert" href="#">×</a>Please use admin/admin to test
                            </div>
                            <div class="control-group">
                                <div class="controls">
                                    <s:textfield key="username" cssClass="span4 input-xlarge" placeholder="getText('username')" required="true"/>
                                </div>
                            </div>
                            <div class="control-group">
                                <div class="controls">
                                    <s:password key="password" cssClass="span4 input-xlarge" placeholder="getText('password')"/>
                                </div>
                            </div>
                            <div class="control-group">
                                <div class="controls">
                                    <label class="checkbox">
                                        <s:checkbox key="rememberMe" value="0">Remember Me</s:checkbox>
                                    </label>
                                </div>
                            </div>
                            <button type="submit" class="btn btn-info btn-block">Sign in</button>
                        </fieldset>
                    </s:form>
                </div>
            </div>
        </div>
    </body>
</html>
