<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html ng-app="all">
<head>
    <title>Blocks</title>
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
                <th>title</th>
                <th>code</th>
                <th>actions</th>
            </tr>
            </thead>
            <tbody>
            <tr ng-repeat="entity in entities">
                <td>{{entity.title}}</td>
                <td>{{entity.code.toLowerCase()}}</td>
                <td>
                    <a href="/admin/blocks/{{entity.id}}/edit" target="_blank" class="btn btn-success">Edit</a>
                </td>
            </tr>
            </tbody>
        </table>
    </div>
</div>

<jsp:include page="../include.jsp" />
<script src="/resources/js/admin/blocks/all.js"></script>
</body>
</html>
