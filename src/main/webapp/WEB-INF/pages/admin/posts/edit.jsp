<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html ng-app="edit">
<head>
    <title>Edit post #${id}</title>
</head>
<body>
    <jsp:include page="../header.jsp" />
    <div class="container" ng-controller="edit_forms">
        <div class="col-md-10 col-md-offset-1">
            <div class="top-nav">
                <a href="/admin/posts/all"><i class="fa fa-long-arrow-left" aria-hidden="true"></i>Back to all posts</a>
            </div>
            <div class="page-title">Create / Edit post</div>
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
                    <input type="text" ng-model="entity.short_description" class="form-control">
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
                <div class="text-center"><button class="btn btn-theme-ok">Save</button></div>
            </form>
            <div ng-show="entity.id">
                <div class="hr-separator"></div>
                <div id="image_container">

                </div>
                <form ng-submit="uploadFile();">
                    <div class="block-fileinput text-center">
                        <input type="file" id="image" accept="image/*" class="inputfile">
                        <input type="text" id="filename" class="filename" disabled />
                        <label for="image" class="btn-theme-dark btn">Choose file</label>
                    </div>
                    <div class="clearfix"></div>
                    <div class="text-center">
                        <button class="btn btn-theme-ok">Save</button>
                    </div>
                </form>
            </div>
        </div>
        <div class="clearfix"></div>
    </div>
<jsp:include page="../include.jsp" />
<script src="/resources/js/utils/tinymce/tinymce.min.js"></script>
<script src="/resources/js/admin/posts/edit.js?id=${id}" id="loader"></script>
</body>
</html>
