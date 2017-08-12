<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>

<header class="navbar-fixed-top">
    <div class="header">
        <div class="container">
            <div>
                <div class="col-xs-12 col-md-4 brand-logo text-uppercase">
                    <a href="/">
                        <img src="/resources/images/logo_header.png" alt="<s:message code='brand'/> "/>
                        <h1 class="font-theme-medium"><s:message code="brand.name"/><br/><span class="font-theme-normal"><s:message code="brand.subheader"/></span></h1>
                    </a>
                </div>
                <div class="col-md-8 text-right menu">
                    <div class="menu-mob-button visible-xs visible-sm"><a href="#"><i class="fa fa-bars" aria-hidden="true"></i></a></div>
                    <nav>
                        <ul class="topmenu">
                            <li id="home"><a href="/"><s:message code="navmenu.home"/></a></li>
                            <li id="menu-services"><a href=""><s:message code="navmenu.services"/></a></li>
                            <li id="menu-companies"><a href="/companies/catalog"><s:message code="navmenu.companies"/></a></li>
                            <li id="menu-blog"><a href="/blog"><s:message code="navmenu.blog"/></a></li>
                            <li id="menu-contacts"><a href=""><s:message code="navmenu.contacts"/></a></li>
                        </ul>
                    </nav>
                </div>
            </div>
        </div>
    </div>
</header>
<div class="fixed-sidebar-right">
    <div class="login-block">
        <i class="fa fa-user" aria-hidden="true"></i>
        <div class="login-block-content">
        <security:authorize access="hasAnyRole('ROLE_ADMIN', 'ROLE_MODERATOR')">
            <a href="/admin/cabinet" class="string"><i class="fa fa-tachometer" aria-hidden="true"></i>Admin dashboard</a><br/>
        </security:authorize>
        <security:authorize access="isAuthenticated()">
            <a href="/profile/" class="string"><i class="fa fa-user" aria-hidden="true"></i>Profile</a><br/>
            <a href="#" id="logout" class="string"><i class="fa fa-sign-out" aria-hidden="true"></i>Log out</a>
        </security:authorize>
        <security:authorize access="isAnonymous()">
            <a href="#" id="sign_in"><i class="fa fa-sign-in" aria-hidden="true"></i>Sign in</a> /
            <a href="/register">Sign up</a>
        </security:authorize>
        </div>
    </div>
</div>