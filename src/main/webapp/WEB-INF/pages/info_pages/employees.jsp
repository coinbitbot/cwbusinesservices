<%@ taglib uri="http://htmlcompressor.googlecode.com/taglib/compressor" prefix="compress"%>
<%@ taglib prefix="с" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<compress:html removeIntertagSpaces="true">
    <%@ page contentType="text/html;charset=UTF-8" language="java" %>

    <!DOCTYPE html>
    <html>
    <head>
        <title>${info_page.meta_title}</title>
        <jsp:include page="../common/include_resources.jsp" />
    </head>
    <body>
    <jsp:include page="../common/header.jsp"/>

    <div class="container">
        <section class="page-content">
            <article>
                <h1>Our Team</h1>

                <!-- Repeating template -->
                <с:if test="${employees ne null}">
                    <c:forEach var="employee" items="${employees}">
                        <div class="text-desc employ-tmp wow fadeInUp" data-wow-offset="10">
                            <div class="col-md-4 employ-photo">
                                <img src="" class="img-responsive" alt="${employee.name}" />
                            </div>
                            <div class="col-md-8 employ-info">
                                <h3 class="text-uppercase">${employee.name}</h3>
                                <h4>${employee.position}</h4><br/>
                                <div>${employee.description}</div>
                                <div>
                                    <h4>Contacts:</h4>
                                    Email: <a href="mailto:${employee.email}">${employee.email}</a><br/>
                                    Phone: <a href="tel:${employee.phone}">${employee.phone}</a>
                                </div>
                            </div>
                            <div class="clearfix"></div>
                        </div>
                    </c:forEach>
                </с:if>
                <!-- /Repeating template -->

            </article>
        </section>
    </div>
        <div class="feedback">
            <div class="container">
                <div class="col-sm-8 col-sm-offset-2 col-md-6 col-md-offset-3">
                    <h3 class="text-uppercase text-center">You can write us a message:</h3>
                    <form>
                        <div class="register-block">
                            <div class="row">
                                <div class="col-xs-12 col-sm-6"><input type="text" name="name" placeholder="Your Name" class="form-field col-xs-12"></div>
                                <div class="col-xs-12 col-sm-6"><input type="email" name="email" placeholder="Your Email" class="form-field col-xs-12"></div>
                                <div class="col-xs-12"><textarea placeholder="Your Message" class="form-field col-xs-12"></textarea></div>
                                <div class="text-center"><button class="btn-theme btn-2">Send message</button></div>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>

    <jsp:include page="../common/footer.jsp" />
    <script>
        $(document).ready(function() {
            // Active menu
            $('#menu_employees').addClass('active');
        });
    </script>
    </body>
    </html>
</compress:html>