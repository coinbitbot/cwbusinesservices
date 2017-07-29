<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html ng-app="all">
<head>
    <title>Roles</title>
</head>
<body>
<jsp:include page="../header.jsp" />

<div class="row main_row" ng-controller="all">
    <div class="col-md-12">
        <div class="col-md-3">

        </div>
        <div class="col-md-3">
            total count: {{total_count}}, on page: {{number_on_page}}
        </div>
    </div>
    <div class="col-md-12">
        <table class="table">
            <thead>
            <tr>
                <th>name</th>
                <th>permissions</th>
                <th>actions</th>
            </tr>
            </thead>
            <tbody>
            <tr ng-repeat="entity in entities">
                <td>{{entity.name}}</td>
                <td>
                    <ol>
                        <li ng-repeat="permission in entity.permissions">
                            {{permission.name.toLowerCase()}}
                        </li>
                    </ol>
                </td>
                <td>
                    <a href="/admin/roles/{{entity.id}}/edit" target="_blank" class="btn btn-success">Edit</a>
                </td>
            </tr>
            </tbody>
        </table>
    </div>
</div>

<jsp:include page="../include.jsp" />
<script src="/resources/js/admin/roles/all.js"></script>
</body>
</html>
