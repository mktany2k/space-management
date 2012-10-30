<%@taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title><decorator:title default="Struts-2 CRUD Tutorial: Decorating with SiteMesh"/></title> 
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="shortcut icon" href="<s:url value="/ico/favicon.ico"/>">
        <link href="<s:url value="/css/bootstrap.min.css"/>" rel="stylesheet">
        <script src="<s:url value="/js/jquery-1.8.2.min.js"/>"></script>
        <script src="<s:url value="/js/bootstrap.min.js"/>"></script>
        <decorator:head/>
    </head>
    <body>
        <div class="navbar navbar-inverse nav">
            <div class="navbar-inner">
                <div class="container">
                    <a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
                        <span class="icon-bar"></span> 
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </a>
                    <s:a cssClass="brand" action="index" namespace="/"> <s:text name="project.name" /> </s:a>
                    <shiro:user>
                        <div class="nav-collapse collapse">
                            <ul class="nav pull-right">
                                <li class="dropdown">
                                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">Welcome, <shiro:principal property="username"/><b class="caret"></b></a>
                                    <ul class="dropdown-menu">
                                        <li><s:a action="Logout" namespace="/auth"><i class="icon-off"></i> Logout</s:a></li>
                                        </ul>
                                    </li>
                                </ul>
                            </div>
                            <!--
                                <div class="btn-group pull-right">
                                    <button class="btn"><s:a action="Plan" namespace="/auth">Menu 1</s:a></button>
                                    <button class="btn">Menu 2</button>
                                    <button class="btn">Menu 3</button>
                                </div>
                        -->
                    </shiro:user>
                </div>
            </div>
        </div>
        <shiro:user>
            <ul class="nav nav-tabs">
                <li class="active"><s:a action="index" namespace="/">Summary</s:a></li>
                <li><s:a action="index" namespace="/">Maintenance</s:a></li>
                <li><s:a action="index" namespace="/">Plan</s:a></li>
                <li class="dropdown">
                    <a class="dropdown-toggle" data-toggle="dropdown" href="#">Dropdown <b class="caret"></b></a>
                    <ul class="dropdown-menu">
                        <li><a href="#">Action</a></li>
                        <li><a href="#">Another action</a></li>
                        <li><a href="#">Something else here</a></li>
                        <li class="divider"></li>
                        <li><a href="#">Separated link</a></li>
                    </ul>
                </li>
            </ul>
        </shiro:user>
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