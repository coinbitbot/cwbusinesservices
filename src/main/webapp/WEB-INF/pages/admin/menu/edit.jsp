<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html ng-app="edit">
<head>
    <title>Edit menu #${id}</title>
    <style>
        .green {
            background: green;
            color: white;
            border: 1px solid green;
        }
    </style>
</head>
<body>
    <jsp:include page="../header.jsp" />
    <div class="container" ng-controller="edit_forms">
        <div class="col-sm-8 col-sm-offset-2 col-md-6 col-md-offset-3">
            <div class="top-nav">
                <a href="/admin/menu/all"><i class="fa fa-long-arrow-left" aria-hidden="true"></i>Back to all menus</a>
            </div>
            <div class="page-title">Edit menu</div>
            <form ng-submit="save();">
                <div class="form-group">
                    <label>Name</label>
                    <input type="text" ng-model="entity.name" class="form-control">
                </div>
                <div class="text-center">
                    <button class="btn btn-theme-ok">Save</button>
                </div>
            </form>
        </div>
        <div class="clearfix"></div>
        <br />
        <hr style="border: 1px solid gray;"/>
        <Br />
        <div class="list" data-menu="0">

        </div>
        <div class="row">
            <div class="col-md-3">
                name for global item: <input id="global_menu_name" class="form-control">
            </div>
            <div class="col-md-3">
                url for global item: <input id="global_menu_url" class="form-control">
            </div>
            <div class="col-md-6">
                <button type="button" id="add_global_menu_item" class="btn btn-theme-ok">Add global menu item</button>
            </div>
        </div>
    </div>
    <jsp:include page="../include.jsp" />


    <script src="/resources/js/admin/menu/edit.js?id=${id}" id="loader"></script>
</body>
</html>
