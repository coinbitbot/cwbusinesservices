<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<div>
    <ul>
        <security:authorize access="hasAnyRole('ROLE_ADMIN', 'ROLE_MODERATOR')">
            <li><a href="/admin/cabinet">Admin dashboard</a></li>
        </security:authorize>
        <security:authorize access="isAuthenticated()">
            <li><a href="/profile/">Profile</a></li>
            <li><a href="#" id="logout">Log out</a></li>
        </security:authorize>
        <security:authorize access="isAnonymous()">
            <li><a href="/register">Sign up</a></li>
            <li><a href="#" id="sign_in">Sign in</a></li>
        </security:authorize>
    </ul>
</div>
