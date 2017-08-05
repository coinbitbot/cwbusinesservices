<%@ taglib uri="http://htmlcompressor.googlecode.com/taglib/compressor" prefix="compress"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<compress:html removeIntertagSpaces="true">
    <%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
    <jsp:useBean id="dateValue" class="java.util.Date"/>

    <!DOCTYPE html>
    <html>
    <head>
        <title>${post.meta_title}</title>
        <jsp:include page="../common/include_resources.jsp" />
    </head>
    <body>
    <jsp:include page="../common/header.jsp"/>

    <c:if test="${post.has_img}">
        <img src="/api/file/${post.id}?type=POST" style="width: 300px;">
    </c:if>

    <h1><a href="/blog/${post.category_code}">${post.category_name}</a> -> ${post.title}</h1>
    <jsp:setProperty name="dateValue" property="time" value="${post.date}"/>
    <fmt:formatDate value="${dateValue}" pattern="dd MMMM yyyy HH:mm"/>
    <br />
    ${post.post_text}

    <jsp:include page="../common/footer.jsp" />
    </body>
    </html>
</compress:html>