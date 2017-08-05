<%@ taglib uri="http://htmlcompressor.googlecode.com/taglib/compressor" prefix="compress"%>
<compress:html removeIntertagSpaces="true">
    <%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
    <jsp:useBean id="dateValue" class="java.util.Date"/>

    <!DOCTYPE html>
    <html>
    <head>
        <title>
            Blog <c:if test="${current_category ne null}">- ${current_category.name}</c:if>
        </title>
        <jsp:include page="../common/include_resources.jsp" />
    </head>
    <body>
    <jsp:include page="../common/header.jsp"/>

    <div class="row">
        <div class="col-md-10">
            <c:choose>
                <c:when test="${posts ne null}">
                    <c:forEach var="post" items="${posts}">
                        <div>
                            <h1><a href="/blog/${post.category_code}">${post.category_name}</a>&nbsp;>&nbsp;<a href="/blog/post/${post.url}">${post.title}</a></h1>
                            <Br />
                            <c:if test="${post.has_img}">
                                <img src="/api/file/${post.id}?type=POST" style="width: 300px;">
                            </c:if>
                            <br />
                            <div>
                                ${post.short_description}
                            </div>
                            <em>
                            <jsp:setProperty name="dateValue" property="time" value="${post.date}"/>
                            <fmt:formatDate value="${dateValue}" pattern="dd MMMM yyyy HH:mm"/>
                            </em>
                        </div>
                    </c:forEach>
                </c:when>
                <c:otherwise>
                    No post
                </c:otherwise>
            </c:choose>
        </div>
        <div class="col-md-2">
            <div>
                <a href="/blog">All posts</a>
            </div>
            <hr />
            <h3>Categories</h3>
            <c:if test="${categories ne null}">
                <c:forEach var="category" items="${categories}">
                    <div>
                        <a href="/blog/${category.code}">${category.name}</a>
                    </div>
                </c:forEach>
            </c:if>
        </div>
    </div>

    <jsp:include page="../common/footer.jsp" />
    </body>
    </html>
</compress:html>