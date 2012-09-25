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
        <script src="<s:url value="/js/jquery-1.8.2.min.js"/>"></script>
        <script src="<s:url value="/js/bootstrap.min.js"/>"></script>
    </head>
    <body> 
        <div class="navbar navbar-inverse navbar-fixed-top">
            <div class="navbar-inner">
                <div class="container">
                    <s:a cssClass="brand" action="index" namespace="/"><s:text name="project.name"/></s:a>
                    <shiro:user>
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
                    </shiro:user>
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