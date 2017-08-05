<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<header class="navbar-fixed-top">
    <div class="header">
        <div class="container">
            <div>
                <div class="col-xs-11 col-sm-5 brand-logo text-uppercase">
                    <a href="/">
                        <img src="/resources/images/logo_header.png" alt="<s:message code='brand'/> "/>
                        <h1 class="font-theme-medium"><s:message code="brand.name"/><br/><span class="font-theme-normal"><s:message code="brand.subheader"/></span></h1>
                    </a>
                </div>
                <div class="col-sm-7 text-right">
                    <ul>
                    <security:authorize access="hasAnyRole('ROLE_ADMIN', 'ROLE_MODERATOR')">
                        <li><a href="/admin/cabinet">Кабінет адміна</a></li>
                    </security:authorize>
                    <security:authorize access="isAuthenticated()">
                        <li><a href="#">Профіль</a></li>
                        <li><a href="#" id="logout">Вийти</a></li>
                    </security:authorize>
                    <security:authorize access="isAnonymous()">
                        <li><a href="/register">Зареєструватись</a></li>
                        <li><a href="#" id="sign_in">Увійти</a></li>
                    </security:authorize>
                </ul>
                </div>
            </div>
        </div>
    </div>
</header>