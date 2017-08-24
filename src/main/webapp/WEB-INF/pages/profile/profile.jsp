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
    <div class="container">
        <section>
            <div class="row">
                <h1>User Profile</h1>
                <jsp:include page="user_menu.jsp" />
            </div>
        </section>
    </div>
    <jsp:include page="../common/footer.jsp" />
    </body>
    </html>
</compress:html>