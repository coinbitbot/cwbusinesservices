<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html ng-app="all">
<head>
    <title>Blogs</title>
</head>
<body>
<jsp:include page="../header.jsp" />

<div class="row main_row" ng-controller="all">
    <div class="col-md-12">
        <div class="col-md-3">
            <a href="/admin/blog/create" class="btn btn-success" target="_blank">Add new blog</a>
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
                <th>code</th>
                <th>actions</th>
            </tr>
            </thead>
            <tbody>
            <tr ng-repeat="entity in entities">
                <td>{{entity.name}}</td>
                <td>{{entity.code}}</td>
                <td>
                    <a href="/admin/blog/{{entity.id}}/edit" target="_blank" class="btn btn-success">Edit</a>
                    <Br />
                    <button class="btn btn-info" ng-click="up(entity);" ng-show="!min(entity)">up</button>
                    <button class="btn btn-info" ng-click="down(entity);" ng-show="!max(entity)">down</button>
                </td>
            </tr>
            </tbody>
        </table>
    </div>
</div>

<jsp:include page="../include.jsp" />
<script src="/resources/js/admin/blog/all.js"></script>
</body>
</html>
