<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html ng-app="all_users">
<head>
    <title>Users</title>
</head>
<body>
<jsp:include page="../header.jsp" />

<div class="row main_row" ng-controller="all_users">
    <div class="row">
        всього знайдено: {{total_count}}, на сторінці: {{number_on_page}}
    </div>
    <div class="col-md-3">
        <form ng-submit="moveToPage()">
            <div class="form-group">
                <label>page</label>
                <select id="pages" class="form-control">
                    <option value="0">1</option>
                </select>
            </div>
            <button class="btn btn-success">Move to page</button>
        </form>
        <hr />
        <form ng-submit="filterForm()">
            <div class="form-group">
                <label>query</label>
                <input ng-model="filters.query" class="form-control">
            </div>
            <div class="form-group">
                <label>role</label>
                <select ng-model="filters.role" class="form-control">
                    <option value="">all</option>
                    <option value="user">user</option>
                    <option value="admin">admin</option>
                    <option value="moderator">moderator</option>
                </select>
            </div>
            <div class="form-group">
                <label>active</label>
                <select ng-model="filters.active" class="form-control">
                    <option value="">ignore</option>
                    <option value="true">yes</option>
                    <option value="false">no</option>
                </select>
            </div>
            <button class="btn btn-success">Filter</button>
        </form>
    </div>
    <div class="col-md-9">
        <table class="table">
            <thead>
            <tr>
                <th>name</th>
                <th>role</th>
                <th>actions</th>
            </tr>
            </thead>
            <tbody>
            <tr ng-repeat="user in entities">
                <td>
                    {{user.name}}
                    <br />
                    {{user.email || '-'}}
                </td>
                <td>{{user.role}}</td>
                <td>
                    <a href="/admin/users/{{user.id}}/edit" target="_blank" class="btn btn-success">Edit user</a>
                </td>
            </tr>
            </tbody>
        </table>
    </div>
</div>

<jsp:include page="../include.jsp" />
<script src="/resources/js/admin/users/all.js"></script>
</body>
</html>
