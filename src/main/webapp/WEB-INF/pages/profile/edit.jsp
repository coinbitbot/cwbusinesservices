<%@ taglib uri="http://htmlcompressor.googlecode.com/taglib/compressor" prefix="compress"%>
<compress:html removeIntertagSpaces="true">
    <%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <!DOCTYPE html>
    <html>
    <head>
        <title>Profile</title>
        <jsp:include page="../common/include_resources.jsp" />
    </head>
    <body>
    <jsp:include page="../common/header.jsp"/>
    <jsp:include page="user_menu.jsp" />

    <div>
        <h1>Edit profile</h1>
        <Br />
        <h2>Mail profile info</h2>
        <form id="main_info_form">
            <input type="hidden" name="id" value="${current_user.id}">
            first name: <input name="first_name" value="${current_user.firstName}" required />
            <Br/>
            last name: <input name="last_name" value="${current_user.lastName}" required />
            <Br/>
            email: <input name="email" value="${current_user.email}" required />
            <Br/>
            phone: <input name="phone" value="${current_user.phone}" required />
            <Br />
            <button>Save</button>
        </form>
        <Br />
        <h2>Change password</h2>
        <form id="password_form">
            <input type="hidden" name="id" value="${current_user.id}">
            old password: <input name="password" type="password" required />
            <Br/>
            new password: <input name="password_new" type="password" required />
            <Br/>
            new password repeat: <input name="password_new_repeat" type="password" required />
            <Br/>
            <button>Save</button>
        </form>
    </div>

    <jsp:include page="../common/footer.jsp" />
    <script src="/resources/js/profile/edit.js"></script>
    </body>
    </html>
</compress:html>