<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html ng-app="all">
<head>
    <title>Blocks</title>
</head>
<body>
<jsp:include page="../header.jsp" />

<div class="col-xs-12" ng-controller="all">
    <div class="col-xs-12 page-title">All Blocks</div>
    <div class="col-sm-6 total-count text-muted">
        total count: {{total_count}}, on page: {{number_on_page}}
    </div>
    <div class="col-sm-6 text-right add-new">

    </div>
    <div class="clearfix"></div>
    <div>
        <div class="block-content">
            <table class="table">
                <thead>
                <tr>
                    <th>Title</th>
                    <th>Code</th>
                    <th>Actions</th>
                </tr>
                </thead>
                <tbody>
                    <tr ng-repeat="entity in entities">
                        <td>{{entity.title}}</td>
                        <td>{{entity.code.toLowerCase()}}</td>
                        <td>
                            <a href="/admin/blocks/{{entity.id}}/edit" class="btn btn-theme-ok">Edit</a>
                        </td>
                    </tr>
                </tbody>
            </table>
        </div>
    </div>
</div>

<jsp:include page="../include.jsp" />
<script src="/resources/js/admin/blocks/all.js"></script>
</body>
</html>
