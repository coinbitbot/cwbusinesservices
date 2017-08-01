<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>


<nav class="navbar navbar-default">
    <div class="container-fluid">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="/"><i class="fa fa-search fa-lg" aria-hidden="true"></i></a>
        </div>

        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav">
                <li><a href="/admin/cabinet">Main dashboard</a></li>
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Users <span class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li><a href="/admin/users/all" class="active">All users</a></li>
                    </ul>
                </li>

                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Settings <span class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li><a href="/admin/info_pages/all" class="active">All info pages</a></li>
                        <li><a href="/admin/services/all" class="active">All service</a></li>
                        <li><a href="/admin/companies/all" class="active">All companies</a></li>
                        <li><a href="/admin/testimonials/all" class="active">All testimonials</a></li>
                        <li><a href="/admin/blocks/all" class="active">All blocks</a></li>
                        <li><a href="/admin/industries/all" class="active">All industries</a></li>
                        <li><a href="/admin/interests/all" class="active">All interests</a></li>
                        <li><a href="/admin/roles/all" class="active">All roles</a></li>
                        <li><a href="/admin/subscription/all" class="active">All subscriptions</a></li>
                    </ul>
                </li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li><a href="#" id="logout">Logout</a></li>
            </ul>
        </div><!-- /.navbar-collapse -->
    </div><!-- /.container-fluid -->
</nav>

<style>
    .main-container {
        margin: auto;
        width: 70%;
    }
</style>