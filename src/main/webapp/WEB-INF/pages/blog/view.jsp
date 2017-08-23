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

    <div class="container">
        <section class="page-content">
            <c:if test="${post.has_img}">
                <div class="text-center"><img src="/api/file/${post.id}?type=POST" class="img-responsive img-center post-img"></div>
            </c:if>
            <div class="meta-info text-center">
                <span><i class="fa fa-calendar" aria-hidden="true"></i><jsp:setProperty name="dateValue" property="time" value="${post.date}"/>
                    <fmt:formatDate value="${dateValue}" pattern="dd MMMM yyyy HH:mm"/>
                </span>
                <span><i class="fa fa-list" aria-hidden="true"></i><a href="/blog/${post.category_code}">${post.category_name}</a></span>
            </div>
            <h1 class="text-center">${post.title}</h1>
            <div class="text-desc">${post.post_text}</div>
        </section>
        <div class="bottom-nav">
            <div class="col-xs-6">
                <c:if test="${prev ne null}">
                    <a href="/blog/post/${prev.url}"><span class="post-nav nav-left"><< Prev</span><span class="text-ellipsis hidden-xs">${prev.title}</span></a>
                </c:if>
            </div>
            <div class="col-xs-6 text-right">
                <c:if test="${next ne null}">
                    <a href="/blog/post/${next.url}"><span class="post-nav nav-right">Next >></span><span class="text-ellipsis hidden-xs">${next.title}</span></a>
                </c:if>
            </div>
            <div class="clearfix"></div>
        </div>
    </div>

    <jsp:include page="../common/footer.jsp" />

    <script>
        $(document).ready(function() {
            // Active menu
            $('#menu_blog').addClass('active');
        });
    </script>
    </body>
    </html>
</compress:html>