<%@ taglib uri="http://htmlcompressor.googlecode.com/taglib/compressor" prefix="compress"%>
<compress:html removeIntertagSpaces="true">
    <%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
    <!DOCTYPE html>
    <html>
    <head>
        <title>Template</title>
        <jsp:include page="../common/include_resources.jsp" />
    </head>
    <body>
        <jsp:include page="../common/header.jsp"/>

        <div class="wrapper" style="min-height: 500px;">
            <div class="container">
                <div class="row">
                    <div class="col-xs-12">

                        <c:if test="${blocks ne null}">
                            <c:forEach var="block" items="${blocks}">
                                <c:choose>
                                    <c:when test="${block.code eq 'SERVICES'}">
                                        <c:if test="${services ne null}">
                                            <section>
                                                <h2>${block.title}</h2>
                                                <c:forEach var="service" items="${services}">
                                                    <div>
                                                        <img src="/api/file/${service.id}?type=SERVICE" style="width: 300px;">
                                                        ${service.name}
                                                    </div>
                                                </c:forEach>
                                            </section>
                                        </c:if>
                                    </c:when>

                                    <c:when test="${block.code eq 'COMPANIES'}">
                                        <c:if test="${companies ne null}">
                                            <section>
                                                <h2>${block.title}</h2>
                                                <c:forEach var="company" items="${companies}">
                                                    <div>
                                                        <img src="/api/file/${company.id}?type=COMPANY" style="width: 300px;" />
                                                        <a href="/companies/${company.id}">${company.name}</a>
                                                    </div>
                                                </c:forEach>
                                            </section>
                                        </c:if>
                                    </c:when>

                                    <c:when test="${block.code eq 'TESTIMONIALS'}">
                                        <c:if test="${testimonials ne null}">
                                            <section>
                                                <h2>${block.title}</h2>
                                                <c:forEach var="testimonial" items="${testimonials}">
                                                    <div>
                                                        ${testimonial.name}
                                                        <Br />
                                                        ${testimonial.text}
                                                    </div>
                                                </c:forEach>
                                            </section>
                                        </c:if>
                                    </c:when>
                                </c:choose>
                            </c:forEach>
                        </c:if>

                        <section>
                            <form id="subscribe_form">
                                <h2>subscribe to get more info</h2>
                                <input type="email" name="email" placeholder="me@example.com">
                                <button>subscribe</button>
                            </form>
                        </section>
                    </div>
                </div>
            </div>
        </div>

        <jsp:include page="../common/footer.jsp" />
    </body>
    </html>
</compress:html>