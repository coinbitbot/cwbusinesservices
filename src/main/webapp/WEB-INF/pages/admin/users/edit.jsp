<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html ng-app="user_edit">
<head>
    <title>Edit user #${id}</title>
</head>
<body>
<div class="container-fluid">
    <jsp:include page="../header.jsp" />
    <div class="row main_row" ng-controller="user_edit_forms">
        <form ng-submit="saveUser();">
            <div class="form-group">
                <label>email</label>
                <input type="text" ng-model="user.email" class="form-control">
            </div>
            <div class="form-group">
                <label>name</label>
                <input type="text" ng-model="user.name" class="form-control">
            </div>
            <div class="form-group">
                <label>role</label>
                <select ng-model="user.role" class="form-control">
                    <option value="user">user</option>
                    <option value="moderator">moderator</option>
                    <option value="admin">admin</option>
                </select>
            </div>
            <div class="form-group">
                <label>active</label>
                <input type="checkbox" ng-model="user.active" class="form-control">
            </div>
            <button class="btn btn-success">Save</button>
        </form>
        <div ng-show="user.id">
            <hr />
            <form ng-submit="updatePassword();">
                <div class="form-group">
                    <label>password</label>
                    <input type="text" ng-model="user.password" class="form-control">
                </div>
                <button class="btn btn-success">Save</button>
            </form>
        </div>

    </div>
</div>
<jsp:include page="../include.jsp" />
<script src="/resources/js/admin/users/edit.js?id=${id}" id="loader"></script>
</body>
</html>
