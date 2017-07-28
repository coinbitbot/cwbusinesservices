<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html ng-app="all">
<head>
    <title>Companies</title>
</head>
<body>
<jsp:include page="../header.jsp" />

<div class="row main_row" ng-controller="all">
    <div class="col-md-12">
        <div class="col-md-3">
            <a href="/admin/companies/create" class="btn btn-success" target="_blank">Add new company</a>
        </div>
        <div class="col-md-3">
            total count: {{total_count}}, on page: {{number_on_page}}
        </div>
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
                <label>active</label>
                <select ng-model="filters.active" class="form-control">
                    <option value="">ignore</option>
                    <option value="true">yes</option>
                    <option value="false">no</option>
                </select>
            </div>
            <div class="form-group">
                <label>has image</label>
                <select ng-model="filters.has_img" class="form-control">
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
                <th>image</th>
                <th>active</th>
                <th>actions</th>
            </tr>
            </thead>
            <tbody>
            <tr ng-repeat="entity in entities">
                <td>{{entity.name}}</td>
                <td>
                    <img ng-src="/api/image/{{entity.id}}?type=COMPANY" style="width: 300px;">
                </td>
                <td>
                    active: <input type="checkbox" ng-model="entity.active" disabled>
                </td>
                <td>
                    <a href="/admin/companies/{{entity.id}}/edit" target="_blank" class="btn btn-success">Edit</a>
                </td>
            </tr>
            </tbody>
        </table>
    </div>
</div>

<jsp:include page="../include.jsp" />
<script src="/resources/js/admin/companies/all.js"></script>
</body>
</html>
