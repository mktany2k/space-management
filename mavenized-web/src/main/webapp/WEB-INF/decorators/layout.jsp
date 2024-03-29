<%@taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <title><decorator:title default="Office Space Management"/></title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="shortcut icon" href="<s:url value="/ico/favicon.ico"/>">
        <link href="<s:url value="/webjars/bootstrap/2.3.2/css/bootstrap.min.css"/>" rel="stylesheet">
        <link href="<s:url value="/webjars/bootstrap/2.3.2/css/bootstrap-responsive.min.css"/>" rel="stylesheet">
        <script src="<s:url value="/webjars/jquery/2.0.3/jquery.min.js"/>"></script>
        <script src="<s:url value="/webjars/bootstrap/2.3.2/js/bootstrap.min.js"/>"></script>
        <script src="<s:url value="/webjars/angularjs/1.1.5/angular.min.js"/>"></script>
        <script src="<s:url value="/js/osm.js"/>"></script>
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
                                        <li><s:a action="logout" namespace="/auth"><i class="icon-off"></i> Logout</s:a></li>
                                        </ul>
                                    </li>
                                </ul>
                            </div>
                    </shiro:user>
                </div>
            </div>
        </div>
        <shiro:user>
            <div class="nav">
                <ul class="nav nav-tabs">
                    <li id="homeTab"><s:a action="index" namespace="/">Home</s:a></li>
                    <li id="summaryTab"><s:a action="summary" namespace="/admin">Summary</s:a></li>
                    <li id="maintenanceTab"><s:a action="maintenance" namespace="/admin">Maintenance</s:a></li>
                    <li id="planTab"><s:a action="plan" namespace="/admin">Plan</s:a></li>
                        <shiro:hasRole name="admin">
                        <li id="administrationTab"><s:a action="list" namespace="/user">Administration</s:a></li>
                        </shiro:hasRole>
                </ul>
            </div>
        </shiro:user>
        <decorator:body/>
        <div class="container">
            <hr>
            <footer>
                <div class="row">
                    <div class="offset4 span4">
                        <p>FOOTER &copy; Company 2012</p>
                    </div>
                </div>
            </footer>
        </div>
    </body>
</html>
