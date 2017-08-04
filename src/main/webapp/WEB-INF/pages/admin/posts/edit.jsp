<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html ng-app="edit">
<head>
    <title>Edit post #${id}</title>
</head>
<body>
<div class="container-fluid">
    <jsp:include page="../header.jsp" />
    <div class="row main_row" ng-controller="edit_forms">
        <form ng-submit="save();">
            <div class="form-group">
                <label>URL</label>
                <input type="text" ng-model="entity.url" ng-change="validateUrl()" class="form-control">
            </div>
            <div class="form-group">
                <label>Title</label>
                <input type="text" ng-model="entity.title" class="form-control">
            </div>
            <div class="form-group">
                <label>Short description</label>
                <input id="short_description" type="text" ng-model="entity.short_description" class="form-control">
            </div>
            <div class="form-group">
                <label>Post text</label>
                <textarea id="full_text" ng-model="entity.post_text" class="form-control"></textarea>
            </div>
            <div class="form-group">
                <label>Category</label>
                <select ng-model="entity.category" class="form-control">
                    <c:forEach var="category" items="${categories}">
                        <option value="${category.id}">${category.name}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="form-group">
                <label>Meta title</label>
                <input type="text" ng-model="entity.meta_title" class="form-control">
            </div>
            <div class="form-group">
                <label>Meta description</label>
                <textarea ng-model="entity.meta_description" class="form-control"></textarea>
            </div>
            <div class="form-group">
                <label>Meta keywords</label>
                <textarea ng-model="entity.meta_keywords" class="form-control"></textarea>
            </div>
            <button class="btn btn-success">Save</button>
        </form>
        <div ng-show="entity.id">
            <hr>
            <div id="image_container">

            </div>
            <form ng-submit="uploadFile();">
                <input type="file" id="image" accept="image/*">
                <button class="btn btn-success">Save</button>
            </form>
        </div>
    </div>
</div>
<jsp:include page="../include.jsp" />
<script src="/resources/js/utils/tinymce/tinymce.min.js"></script>
<script src="/resources/js/admin/posts/edit.js?id=${id}" id="loader"></script>
</body>
</html>
