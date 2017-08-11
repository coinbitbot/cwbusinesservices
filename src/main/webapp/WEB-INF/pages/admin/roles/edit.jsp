<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html ng-app="edit">
<head>
    <title>Edit role #${id}</title>
</head>
<body>
<jsp:include page="../header.jsp" />
<div class="container">
    <div class="col-md-10 col-md-offset-1" ng-controller="edit_forms">
        <div class="text-center text-uppercase warning-message">
            <i class="fa fa-exclamation-circle" aria-hidden="true"></i> Please note that these changes will only be applied <b>after</b> the user logs out and logs back in.
        </div>
        <div class="top-nav">
            <a href="/admin/roles/all"><i class="fa fa-long-arrow-left" aria-hidden="true"></i>Back to all roles</a>
        </div>
        <div class="page-title">
            Role name: {{entity.name.toLowerCase()}}
        </div>
        <div class="form-group col-sm-6 col-sm-offset-3">
            <label>New permission</label>
            <select ng-model="new_permission" class="form-control">
                <c:forEach var="permission" items="${permissions}">
                    <option value="${permission}">${permission.toString().toLowerCase().replace('_', '  ')}</option>
                </c:forEach>
            </select>
            <br/>
            <div class="text-center">
                <button class="btn btn-theme-ok" ng-click="addPermission()">Add permission</button>
            </div>
        </div>
        <div class="clearfix"></div>
        <table class="table">
            <thead>
                <tr>
                    <th>Permission name</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <tr ng-repeat="permission in entity.permissions">
                    <td>{{permission.name.toLowerCase()}}</td>
                    <td>
                        <button ng-click="deletePermission(permission)" class="btn btn-danger">Delete</button>
                    </td>
                </tr>
            </tbody>
        </table>
    </div>
</div>
<jsp:include page="../include.jsp" />
<script src="/resources/js/admin/roles/edit.js?id=${id}" id="loader"></script>
</body>
</html>
