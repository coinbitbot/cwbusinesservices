<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html ng-app="all">
<head>
    <title>Posts</title>
</head>
<body>
<jsp:include page="../header.jsp" />

<div class="col-xs-12" ng-controller="all">
    <div class="col-xs-12 page-title">All Posts</div>
    <div class="col-sm-6 total-count text-muted">
        total count: {{total_count}}, on page: {{number_on_page}}
    </div>
    <div class="col-sm-6 text-right add-new">
        <a href="/admin/posts/create" class="btn btn-theme-ok">Add new post</a>
    </div>
    <div class="clearfix"></div>
    <div class="col-sm-3">
        <div class="block-content bg-pattern">
            <form ng-submit="filterForm()">
                <div class="form-group">
                    <label>Query</label>
                    <input ng-model="filters.query" class="form-control">
                </div>
                <div class="form-group">
                    <label>Has image</label>
                    <select ng-model="filters.has_img" class="form-control">
                        <option value="">Ignore</option>
                        <option value="true">Yes</option>
                        <option value="false">No</option>
                    </select>
                </div>
                <div class="form-group">
                    <label>Category</label>
                    <select ng-model="filters.category" class="form-control">
                        <option value="">All</option>
                        <c:forEach var="category" items="${categories}">
                            <option value="${category.id}">${category.name}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="text-center"><button class="btn btn-theme-ok">Filter</button></div>
            </form>
        </div>
    </div>
    <div class="col-sm-9">
        <div class="block-content">
            <table class="table">
                <thead>
                <tr>
                    <th>Title</th>
                    <th>Category</th>
                    <th>Info</th>
                    <th>Actions</th>
                </tr>
                </thead>
                <tbody>
                <tr ng-repeat="entity in entities">
                    <td>{{entity.title}}</td>
                    <td>{{entity.category}}</td>
                    <td>
                        Has image: <input type="checkbox" ng-model="entity.has_img" class="form-control checkbox" disabled>
                        <br />
                        Date: {{format(entity.date)}}
                    </td>
                    <td>
                        <a href="/admin/posts/{{entity.id}}/edit" class="btn btn-theme-ok">Edit</a>
                    </td>
                </tr>
                </tbody>
            </table>
        </div>
        <form ng-submit="moveToPage()">
            <div class="form-group text-right page-nav">
                <label>page</label>
                <select id="pages" class="form-control">
                    <option value="0">1</option>
                </select>
                <button class="btn btn-theme-ok">Move to page</button>
            </div>
        </form>
    </div>
    <div class="clearfix"></div>
</div>

<jsp:include page="../include.jsp" />
<script src="/resources/js/utils/date.format.min.js"></script>
<script src="/resources/js/admin/posts/all.js"></script>
</body>
</html>
