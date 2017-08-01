<%@ taglib uri="http://htmlcompressor.googlecode.com/taglib/compressor" prefix="compress"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<compress:html removeIntertagSpaces="true">
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Реєстрація</title>
    <jsp:include page="../common/include_resources.jsp" />
</head>
<body>
<jsp:include page="../common/header.jsp"/>

<div>
    <form id="sign_up_form">
        <h2>Personal info</h2>
        <div id="user_fields">
            <label>First name</label>
            <input type="text" name="first_name" placeholder="Ibrahimovich" class="form-field">
            <br />
            <label>Last name</label>
            <input type="text" name="last_name" placeholder="Zlatan" class="form-field">
            <br />
            <label>Email</label>
            <input type="email" name="email" placeholder="example@example.com" class="form-field">
            <br />
            <label>Phone</label>
            <input type="tel" name="phone" placeholder="+23453452345234" class="form-field">
        </div>
        <Br />
        <h2>Request company info</h2>
        <br />
        <div id="company_fields">
            <label>Company name</label>
            <input type="text" name="company_name" placeholder="My company" class="form-field">
            <br />
            <label>Business plan</label>
            <input type="file" name="file" id="request_file">
            <br />
            <h3>you interests</h3>
            <c:if test="${interests ne null}">
                <c:forEach var="interest" items="${interests}">
                    <Br />
                    <input type="checkbox" name="interests" value="${interest.id}" class="form-field"> ${interest.name}
                </c:forEach>
            </c:if>
            <br />
            <input type="text " name="interest_alter" class="form-field">
            <p style="font-size: 12px;">
                does not find your interest? enter it here
            </p>
            <h3>industry</h3>
            <c:if test="${industries ne null}">
                <select name="industry" class="form-field">
                <c:forEach var="industry" items="${industries}">
                    <option value="${industry.id}">${industry.name}</option>
                </c:forEach>
                </select>
            </c:if>
        </div>
        <br />
        <button type="submit">Register an request</button>
    </form>
</div>

<jsp:include page="../common/footer.jsp" />

<script src="/resources/js/auth/register.js"></script>
</body>
</html>
</compress:html>

