<%@ taglib uri="http://htmlcompressor.googlecode.com/taglib/compressor" prefix="compress"%>
<compress:html removeIntertagSpaces="true">
    <%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

    <!DOCTYPE html>
    <html>
    <head>
        <jsp:include page="../common/include_external_head_top.jsp" />
        <title>All companies</title>
        <jsp:include page="../common/include_resources.jsp" />
    </head>
    <body>
    <jsp:include page="../common/include_external_body_top.jsp" />
    <jsp:include page="../common/header.jsp"/>

    <div class="container">
        <section>
            <h1 class="col-xs-12">Investee Companies</h1>
            <c:if test="${companies ne null}">
                <div class="row">
                <c:forEach var="company" items="${companies}">
                    <div class="col-xs-12 col-sm-6 col-md-4 column-block page-content text-center company-block wow fadeInUp" data-wow-offset="10">
                        <div class="company-item">
                            <a href="/companies/${company.id}">
                                <img src="/api/file/${company.id}?type=COMPANY">
                                <h3>${company.name}</h3>
                            </a>
                        </div>
                    </div>
                </c:forEach>
                </div>
            </c:if>
            <div class="clearfix"></div>
        </section>
    </div>

    <jsp:include page="../common/footer.jsp" />

    <script>
        $(document).ready(function() {
            // Active menu
            $('#menu_companies').addClass('active');
        });
    </script>
    </body>
    </html>
</compress:html>