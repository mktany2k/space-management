<%@ taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator"%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@page contentType="text/html; charset=UTF-8" %> 

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
    </head> 

    <body> 
        <div class="navbar navbar-inverse navbar-fixed-top">
            <div class="navbar-inner">
                <div class="container">
                    <a class="brand" href="#">Office Space Management</a>
                    <div class="nav-collapse collapse">
                        <ul class="nav">
                            <li class="active"><a href="#">Home</a></li>
                            <li><a href="#about">About</a></li>
                            <li><a href="#contact">Contact</a></li>

                        </ul>
                    </div><!--/.nav-collapse -->
                    <%--<shiro:authenticated>--%>
                    <div class="nav-collapse collapse pull-right">
                        <ul class="nav">
                            <li class="dropdown">
                                <a href="#" class="dropdown-toggle" data-toggle="dropdown">Login is as<b class="caret"></b></a>
                                <ul class="dropdown-menu">
                                    <li><a href="#">Action</a></li>
                                    <li><a href="#">Another action</a></li>
                                    <li><a href="#">Something else here</a></li>
                                    <li class="divider"></li>
                                    <li class="nav-header">Nav header</li>
                                    <li><a href="#">Separated link</a></li>
                                    <li><a href="#">One more separated link</a></li>
                                </ul>
                            </li>
                        </ul>
                    </div>
                    <%--</shiro:authenticated>--%>
                    <%--<shiro:notAuthenticated>--%>
                    <s:a action="login" cssClass="btn btn-primary pull-right">Login</s:a>
                    <%--</shiro:notAuthenticated>--%>


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
        <script src="<s:url value="/js/jquery-1.8.1.min.js"/>"></script>
        <script src="<s:url value="/js/bootstrap.min.js"/>"></script>
    </body>
</html> 