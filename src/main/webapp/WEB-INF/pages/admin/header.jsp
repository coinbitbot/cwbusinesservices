<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>


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
                        <security:authorize access="hasPermission(1,'CREATE_INFO_PAGE,EDIT_INFO_PAGE')">
                            <li><a href="/admin/info_pages/all" class="active">All info pages</a></li>
                        </security:authorize>
                        <security:authorize access="hasPermission(1,'CREATE_SERVICE,EDIT_SERVICE')">
                            <li><a href="/admin/services/all" class="active">All service</a></li>
                        </security:authorize>
                        <security:authorize access="hasPermission(1,'CREATE_COMPANY,EDIT_COMPANY')">
                            <li><a href="/admin/companies/all" class="active">All companies</a></li>
                        </security:authorize>
                        <security:authorize access="hasPermission(1,'CREATE_TESTIMONIAL,EDIT_TESTIMONIAL')">
                            <li><a href="/admin/testimonials/all" class="active">All testimonials</a></li>
                        </security:authorize>
                        <security:authorize access="hasPermission(1,'CREATE_BLOCK,EDIT_BLOCK')">
                            <li><a href="/admin/blocks/all" class="active">All blocks</a></li>
                        </security:authorize>
                        <security:authorize access="hasPermission(1,'CREATE_INDUSTRY,EDIT_INDUSTRY')">
                            <li><a href="/admin/industries/all" class="active">All industries</a></li>
                        </security:authorize>
                        <security:authorize access="hasPermission(1,'CREATE_INTEREST,EDIT_INTEREST')">
                            <li><a href="/admin/interests/all" class="active">All interests</a></li>
                        </security:authorize>
                        <security:authorize access="hasPermission(1,'ROLE_ADMIN')">
                            <li><a href="/admin/roles/all" class="active">All roles</a></li>
                        </security:authorize>
                        <security:authorize access="hasPermission(1,'CREATE_EMAIL_SUBSCRIBE,EDIT_EMAIL_SUBSCRIBE')">
                            <li><a href="/admin/subscription/all" class="active">All subscriptions</a></li>
                        </security:authorize>
                        <security:authorize access="hasPermission(1,'CREATE_EMAIL_TEMPLATE,EDIT_EMAIL_TEMPLATE')">
                            <li><a href="/admin/emails/all" class="active">All email templates</a></li>
                        </security:authorize>
                        <security:authorize access="hasPermission(1,'CREATE_BLOG_CATEGORY,EDIT_BLOG_CATEGORY')">
                            <li><a href="/admin/blog/all" class="active">All blog</a></li>
                        </security:authorize>
                        <security:authorize access="hasPermission(1,'CREATE_POST,EDIT_POST')">
                            <li><a href="/admin/posts/all" class="active">All blog</a></li>
                        </security:authorize>
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