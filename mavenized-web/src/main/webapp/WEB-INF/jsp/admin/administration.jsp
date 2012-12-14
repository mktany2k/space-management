<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><fmt:message key="project.name"/></title>
    </head>
    <body>
        <div class="container">
            <div class="row-fluid">
                <div class="span3">
                    <div class="sidebar-nav">
                        <div class="well">
                            <ul class="nav nav-list">
                                <li class="nav-header">Admin Menu</li>
                                <li><a href="index"><i class="icon-home"></i> Dashboard</a></li>
                                <li><a href="#"><i class="icon-envelope"></i> Messages <span class="badge badge-info">4</span></a></li>
                                <li><a href="#"><i class="icon-comment"></i> Comments <span class="badge badge-info">10</span></a></li>
                                <li class="active"><a href="#"><i class="icon-user"></i> Members</a></li>
                                <li class="divider"></li>
                                <li><a href="#"><i class="icon-comment"></i> Settings</a></li>
                                <li><a href="#"><i class="icon-share"></i> Logout</a></li>
                            </ul>
                        </div>
                    </div>
                </div>
                <div class="span9">
                    <div class="well">
                        <table class="table table-condensed table-hover">
                            <thead>
                                <tr>
                                    <th class="span1"><input type="checkbox"></th>
                                    <th class="span2"></th>
                                    <th class="span2"></th>
                                    <th class="span9"></th>
                                    <th class="span2"></th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <td><input type="checkbox"> <a href="#"><i class="icon-star-empty"></i></a></td>
                                    <td><strong>John Doe</strong></td>
                                    <td><span class="label pull-right">Notifications</span></td>
                                    <td><strong>Message body goes here</strong></td>
                                    <td><strong>11:23 PM</strong></td>
                                </tr>
                                <tr>
                                    <td><input type="checkbox"> <a href="#"><i class="icon-star-empty"></i></a></td>
                                    <td>John Doe</td>
                                    <td><span class="label pull-right">Notifications</span></td>
                                    <td>Message body goes here</td>
                                    <td>Sept4</td>
                                </tr>
                                <tr>
                                    <td><input type="checkbox"> <a href="#"><i class="icon-star"></i></a></td>
                                    <td><strong>John Doe</strong></td>
                                    <td><span class="label pull-right">Notifications</span></td>
                                    <td><strong>Message body goes here</strong></td>
                                    <td><strong>Sept3</strong></td>
                                </tr>
                                <tr>
                                    <td><input type="checkbox"> <a href="#"><i class="icon-star"></i></a></td>
                                    <td><strong>John Doe</strong></td>
                                    <td><span class="label pull-right">Notifications</span></td>
                                    <td><strong>Message body goes here</strong></td>
                                    <td><strong>Sept3</strong></td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>

        <script>
            $(document).ready(function() {
                $("#administrationTab").addClass("active");
            });
        </script>
    </body>
</html>