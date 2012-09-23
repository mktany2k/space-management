<%@ taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html> 
<html> 
    <head> 
        <title><decorator:title default="Struts-2 CRUD Tutorial: Decorating with SiteMesh"/></title> 
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="shortcut icon" href="<s:url value="/ico/favicon.ico"/>">
        <link href="<s:url value="/css/bootstrap.min.css"/>" rel="stylesheet">
        <style type="text/css">
            body {
                padding-top: 60px;
                padding-bottom: 40px;
            }
        </style>
        <decorator:head/>
        <script src="<s:url value="/js/jquery-1.8.1.min.js"/>"></script>
        <script src="<s:url value="/js/bootstrap.min.js"/>"></script>
    </head>
    <body> 
        <div class="navbar navbar-inverse navbar-fixed-top">
            <div class="navbar-inner">
                <div class="container">
                    <a class="brand" href="#">Office Space Management</a>
                    <shiro:authenticated>
                        <div class="nav-collapse collapse pull-right">
                            <ul class="nav">
                                <li class="dropdown">
                                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">Logged in as <shiro:principal/><b class="caret"></b></a>
                                    <ul class="dropdown-menu">
                                        <li><s:a action="Logout" namespace="/example">Logout</s:a></li>
                                        </ul>
                                    </li>
                                </ul>
                            </div>
                    </shiro:authenticated>
                    <shiro:notAuthenticated>
                        <div class="nav pull-right">
                            <li class="dropdown">
                                <a class="dropdown-toggle" href="#" data-toggle="dropdown">Log In <strong class="caret"></strong></a>
                                <div class="dropdown-menu well form">
                                    <%--<s:form acceptcharset="UTF-8">
                                        <s:textfield key="username" placeholder="Username" size="30"/>
                                        <s:password key="password" placeholder="Password" size="30"/>
                                        <label class="checkbox string optional">
                                            <s:checkbox key="remember" value="1">Remember me</s:checkbox>
                                        </label>
                                        <s:submit cssClass="btn btn-primary" key="submit" value="Sign In"/>
                                    </s:form>--%>
                                </div>
                            </li>
                        </div>
                    </shiro:notAuthenticated>
                </div>
            </div>
        </div>
        <decorator:body/> 
        <div class="container">
            <hr>
            <footer>
                <div class="container">
                    <div class="row">
                        <div class="span12">
                            <p>FOOTER &copy; Company 2012</p>
                        </div>
                    </div>
                </div>
            </footer>
        </div>
    </body>
</html> 