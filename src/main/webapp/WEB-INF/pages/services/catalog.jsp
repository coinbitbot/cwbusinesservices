<%@ taglib uri="http://htmlcompressor.googlecode.com/taglib/compressor" prefix="compress"%>
<compress:html removeIntertagSpaces="true">
    <%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
    <jsp:useBean id="dateValue" class="java.util.Date"/>

    <!DOCTYPE html>
    <html>
    <head>
        <jsp:include page="../common/include_external_head_top.jsp" />
        <title>Services</title>
        <jsp:include page="../common/include_resources.jsp" />
    </head>
    <body>
    <jsp:include page="../common/include_external_body_top.jsp" />
    <jsp:include page="../common/header.jsp"/>

    <div class="container">
        <section>
            <h1 class="col-xs-12">Services</h1>
            <c:choose>
                <c:when test="${services ne null}">
                    <div class="row page-content">
                    <c:forEach var="service" items="${services}">
                        <div class="service-item col-xs-12" id="service-${service.id}">
                            <div class=" col-xs-12 col-sm-3 col-md-2 icon-service">
                                <div class="img-center">
                                <c:choose>
                                    <c:when test="${service.has_icon}">
                                        <!--<a href="/services/${service.id}/view">--><img src="/api/file/${service.id}?type=SERVICE" class="img-circle img-responsive visible-print"><!--</a>-->
                                        <div style="background: url('/api/file/${service.id}?type=SERVICE') no-repeat center; background-size: cover" class="img-service img-circle hidden-print"></div>
                                    </c:when>
                                    <c:otherwise>
                                        <div class="no-image"><img src="/resources/images/no-service.png" class="img-circle img-responsive"></div>
                                    </c:otherwise>
                                </c:choose>
                                </div>
                            </div>
                            <div class="col-xs-12 col-sm-9 col-md-10 service-desc">
                                <h3>${service.name}</h3>
                                <div class="text-desc">
                                    ${service.description}
                                </div>
                            </div>
                        </div>
                        <div class="clearfix"></div>
                    </c:forEach>
                        <div class="col-xs-10 col-xs-offset-1 col-sm-6 col-sm-offset-3 col-md-4 col-md-offset-4 hidden-print">
                            <a href="/register" class="btn-theme btn-1 font-theme-book col-xs-12 btn-start">Let`s get started</a>
                        </div>
                        <div class="clearfix"></div>
                    </div>
                </c:when>
                <c:otherwise>
                    <div class="no-info text-center">No Services</div>
                </c:otherwise>
            </c:choose>

            <div class="clearfix"></div>
            <%--<div class="text-center page-pagination">

                <a href="/services/1/page" class="page page-nav">Start</a>

                <c:choose>
                    <c:when test="${current_page > 1}">
                        <a href="/services/${current_page-1}/page" class="page page-nav">Prev</a>
                    </c:when>
                    <c:otherwise>
                        <span class="page page-nav" disabled>Prev</span>
                    </c:otherwise>
                </c:choose>

                <c:forEach begin="${min_page}" end="${max_page}" var="page">
                    <a href="/services/${page}/page" class="page page-nav">${page}</a>
                </c:forEach>

                <c:choose>
                    <c:when test="${current_page ne number_of_pages}">
                        <a href="/services/${current_page+1}/page" class="page page-nav">Next</a>
                    </c:when>
                    <c:otherwise>
                        <span  class="page page-nav" disabled>Next</span>
                    </c:otherwise>
                </c:choose>

                <a href="/services/${number_of_pages}/page" class="page page-nav">End</a>
            </div>--%>
        </section>
    </div>

    <jsp:include page="../common/footer.jsp" />
    <script>
        $(function(){
            /*var url = location.pathname;
            if (url.indexOf('/page') > -1) {
                $('.page[href^="' + url + '"]').addClass('active-page');
            } else {
                $('.page[href="/services/1/page"]').addClass('active-page');
            }*/

            var hash = location.hash;
            if (hash) {
                var $element = $(hash);
                if ($element.length > 0) {
                    $('html, body').animate({
                        scrollTop: $element.offset().top - 120
                    }, 500);
                }
            }
        });

        $(document).ready(function() {
            // Active menu
            $('#menu_services').addClass('active');
        });
    </script>
    </body>
    </html>
</compress:html>