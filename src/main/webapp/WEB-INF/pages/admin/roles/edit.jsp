<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html ng-app="edit">
<head>
    <title>Edit role #${id}</title>
</head>
<body>
<div class="container-fluid">
    <jsp:include page="../header.jsp" />
    <p style="font-size: 18px;">
        Please note that these changes will only be applied <b>after</b> the user logs out and logs back in.
    </p>
    <div class="main_row" ng-controller="edit_forms">
        <div style="font-size: 20px;">
            role name: {{entity.name.toLowerCase()}}
        </div>
        <br />
        <div class="form-group">
            <label>new permission</label>
            <select ng-model="new_permission" class="form-control">
                <c:forEach var="permission" items="${permissions}">
                    <option value="${permission}">${permission.toString().toLowerCase().replace('_', '  ')}</option>
                </c:forEach>
            </select>
            <button class="btn btn-success" ng-click="addPermission()">add permission</button>
        </div>
        <Br />
        <table class="table">
            <tr>
                <th>permission name</th>
                <th>actions</th>
            </tr>
            <tr ng-repeat="permission in entity.permissions">
                <td>{{permission.name.toLowerCase()}}</td>
                <td>
                    <button ng-click="deletePermission(permission)" class="btn btn-danger">delete</button>
                </td>
            </tr>
        </table>
    </div>
</div>
<jsp:include page="../include.jsp" />
<script src="/resources/js/admin/roles/edit.js?id=${id}" id="loader"></script>
</body>
</html>
