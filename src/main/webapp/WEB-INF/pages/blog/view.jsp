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
                <div class="text-center"><img src="/api/file/${post.id}?type=POST" class="img-responsive"></div>
            </c:if>
            <div class="meta-info">
                <span><i class="fa fa-calendar" aria-hidden="true"></i><jsp:setProperty name="dateValue" property="time" value="${post.date}"/>
                    <fmt:formatDate value="${dateValue}" pattern="dd MMMM yyyy HH:mm"/>
                </span>
                <span><i class="fa fa-list" aria-hidden="true"></i><a href="/blog/${post.category_code}">${post.category_name}</a></span>
            </div>
            <h1>${post.title}</h1>
            <div class="text-desc">${post.post_text}</div>
        </section>
        <!-- Post navigation
        <div class="bottom-nav">
            <div class="col-xs-6"><a href="#"><span class="post-nav nav-left"><< Prev</span><span class="text-ellipsis hidden-xs">Title prev post</span></a></div>
            <div class="col-xs-6 text-right"><a href="#"><span class="post-nav nav-right">Next >></span><span class="text-ellipsis hidden-xs">Title next post</span></a></div>
            <div class="clearfix"></div>
        </div> -->
    </div>

    <jsp:include page="../common/footer.jsp" />
    </body>
    </html>
</compress:html>