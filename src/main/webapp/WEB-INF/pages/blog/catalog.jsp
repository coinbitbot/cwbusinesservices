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

    <div class="container">
        <section>
            <h1 class="col-xs-12">Blog</h1>
            <c:choose>
                <c:when test="${posts ne null}">
                    <c:forEach var="post" items="${posts}">
                        <div class="col-xs-12 col-sm-6 col-md-4 column-block">
                            <div class="blog-item dotdot">
                                <c:choose>
                                    <c:when test="${post.has_img}">
                                        <a href="/blog/post/${post.url}" class="img-container"><img src="/api/file/${post.id}?type=POST"></a>
                                    </c:when>
                                    <c:otherwise>
                                    <a href="/blog/post/${post.url}" class="img-container no-image"><img src="/resources/images/no-image.jpg"></a>
                                    </c:otherwise>
                                </c:choose>
                                <div class="meta-info">
                                    <span><i class="fa fa-calendar" aria-hidden="true"></i><jsp:setProperty name="dateValue" property="time" value="${post.date}"/>
                                    <fmt:formatDate value="${dateValue}" pattern="dd MMMM yyyy HH:mm"/></span>
                                    <span><i class="fa fa-list" aria-hidden="true"></i><a href="/blog/${post.category_code}">${post.category_name}</a></span>
                                </div>
                                <h3><a href="/blog/post/${post.url}">${post.title}</a></h3>
                                <div>
                                    ${post.short_description}
                                </div>
                            </div>
                            <a href="/blog/post/${post.url}" class="col-xs-12 btn-theme btn-transp read-more">Read more</a>
                        </div>
                    </c:forEach>
                </c:when>
                <c:otherwise>
                    <div class="no-info text-center">No post</div>
                </c:otherwise>
            </c:choose>

            <div class="clearfix"></div>
            <div class="text-center">
                <c:set var="cat" value="" />
                <c:if test="${current_category ne null}">
                    <c:set var="cat" value="/${current_category.code}" />
                </c:if>

                <a href="/blog${cat}/1/page" class="page page-nav">Start</a>

                <c:choose>
                    <c:when test="${current_page > 1}">
                        <a href="/blog${cat}/${current_page-1}/page" class="page page-nav">Prev</a>
                    </c:when>
                    <c:otherwise>
                        <span class="page page-nav" disabled>Prev</span>
                    </c:otherwise>
                </c:choose>

                <c:forEach begin="${min_page}" end="${max_page}" var="page">
                    <a href="/blog${cat}/${page}/page" class="page page-nav">${page}</a>
                </c:forEach>

                <c:choose>
                    <c:when test="${current_page ne number_of_pages}">
                        <a href="/blog${cat}/${current_page+1}/page" class="page page-nav">Next</a>
                    </c:when>
                    <c:otherwise>
                        <span  class="page page-nav" disabled>Next</span>
                    </c:otherwise>
                </c:choose>

                <a href="/blog${cat}/${number_of_pages}/page" class="page page-nav">End</a>
            </div>
        </section>
        <!--<div class="col-md-2">
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
        </div>-->
    </div>

    <jsp:include page="../common/footer.jsp" />
    <script src="/resources/js/utils/jquery.dotdotdot.min.js"></script>
    <script>
        $(function(){
            var url = location.pathname;
            if (url.indexOf('/page') > -1) {
                $('.page[href^="' + url + '"]').addClass('active-page');
            } else {
                $('.page[href="/blog${cat}/1/page"]').addClass('active-page');
            }
        });

        $(document).ready(function() {
            // Short text
            $('.dotdot').dotdotdot();

            // Active menu
            $('#menu_blog').addClass('active');
        });
    </script>
    </body>
    </html>
</compress:html>