<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<div>
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
