<%@ taglib uri="http://htmlcompressor.googlecode.com/taglib/compressor" prefix="compress"%>
<compress:html removeIntertagSpaces="true">
    <%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
    <!DOCTYPE html>
    <html>
    <head>
        <title><s:message code="brand"/></title>
        <jsp:include page="../common/include_resources.jsp" />
        <link rel="stylesheet" href="/resources/js/utils/owl_carousel2/owl.carousel.min.css" />
        <link rel="stylesheet" href="/resources/js/utils/owl_carousel2/owl.theme.default.min.css" />
    </head>
    <body>
        <jsp:include page="../common/header.jsp"/>

        <div class="wrapper">
            <div class="container">
                <div class="row">
                    <div class="col-xs-12">

                        <c:if test="${blocks ne null}">
                            <c:forEach var="block" items="${blocks}">
                                <c:choose>
                                    <c:when test="${block.code eq 'SERVICES'}">
                                        <c:if test="${services ne null}">
                                            <section  class="wow fadeInUp" data-wow-offset="10">
                                                <h2>${block.title}</h2>
                                                <div id="services" class="owl-carousel">
                                                    <c:forEach var="service" items="${services}">
                                                        <div class="text-center block-item">
                                                            <div class="service-icon">
                                                                <img src="/api/file/${service.id}?type=SERVICE" class="img-responsive" />
                                                            </div>
                                                            <h3 class="font-theme-book">${service.name}</h3>
                                                            <div class="dotdot text-desc">Description: Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.</div>
                                                            <a href="#" class="btn-theme btn-transp col-xs-12"><s:message code="block.services.more"/></a>
                                                        </div>
                                                    </c:forEach>
                                                </div>
                                            </section>
                                            <div class="clearfix"></div>
                                        </c:if>
                                    </c:when>

                                    <c:when test="${block.code eq 'COMPANIES'}">
                                        <c:if test="${companies ne null}">
                                            <section  class="wow fadeInUp" data-wow-offset="10">
                                                <h2>${block.title}</h2>
                                                <div id="companies" class="owl-carousel">
                                                    <c:forEach var="company" items="${companies}">
                                                        <div class="text-center block-item">
                                                            <a href="/companies/${company.id}" title="${company.name}">
                                                                <img src="/api/file/${company.id}?type=COMPANY" class="img-responsive" />
                                                            </a>
                                                        </div>
                                                    </c:forEach>
                                                </div>
                                                <a href="/companies/catalog" class="text-right col-xs-12"><s:message code="block.companies.all"/> >></a>
                                            </section>
                                            <div class="clearfix"></div>
                                        </c:if>
                                    </c:when>

                                    <c:when test="${block.code eq 'TESTIMONIALS'}">
                                        <c:if test="${testimonials ne null}">
                                            <section  class="wow fadeInUp" data-wow-offset="10">
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
                            <div class="col-md-7 wow fadeInUp" data-wow-offset="10">
                                <h2><s:message code="block.blog.name"/></h2>
                                <div id="blog">
                                    <article class="news-item dotdot">
                                        <h3><a href="#">Title News 1</a></h3>
                                        <div class="meta-info">
                                            <i class="fa fa-calendar" aria-hidden="true"></i>01.01.2017
                                            <i class="fa fa-bars" aria-hidden="true"></i><a href="#">Categories Name</a>
                                        </div>
                                        <div class="text-desc"> Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.</div>
                                    </article>
                                    <article class="news-item dotdot">
                                        <h3><a href="#">Title News 1</a></h3>
                                        <div class="meta-info">
                                            <i class="fa fa-calendar" aria-hidden="true"></i>01.01.2017
                                            <i class="fa fa-bars" aria-hidden="true"></i><a href="#">Categories Name</a>
                                        </div>
                                        <div class="text-desc"> Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.</div>
                                    </article>
                                    <article class="news-item dotdot">
                                        <h3><a href="#">Title News 1</a></h3>
                                        <div class="meta-info">
                                            <i class="fa fa-calendar" aria-hidden="true"></i>01.01.2017
                                            <i class="fa fa-bars" aria-hidden="true"></i><a href="#">Categories Name</a>
                                        </div>
                                        <div class="text-desc"> Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.</div>
                                    </article>
                                </div>
                                <a href="/blog" class="text-right col-xs-12"><s:message code="block.blog.all_news"/> >></a>
                                <div class="clearfix"></div>
                            </div>
                            <div class="col-md-5 block-subscribe wow fadeInUp" data-wow-offset="10">
                                <div class="col-xs-12 col-sm-4 col-md-12 img-subscribe">
                                    <img src="/resources/images/subscribe_img.jpg" class="img-responsive" />
                                </div>
                                <div class="subscribe-form col-xs-12 col-sm-8 col-md-12 text-center">
                                    <form id="subscribe_form">
                                        <h3 class="font-theme-book"><s:message code="block.subscribe.title"/></h3>
                                        <input type="email" name="email" class="col-xs-12" placeholder="me@example.com">
                                        <button class="btn-theme btn-transp col-xs-12"><s:message code="block.subscribe.button"/></button>
                                    </form>
                                </div>
                            </div>
                            <div class="clearfix"></div>
                        </section>
                    </div>
                </div>
            </div>
        </div>

        <jsp:include page="../common/footer.jsp" />
        <script src="/resources/js/utils/owl_carousel2/owl.carousel.min.js"></script>
        <script src="/resources/js/utils/jquery.dotdotdot.min.js"></script>
        <script>
            $(document).ready(function(){
                // Carousel
                $('#services').owlCarousel({
                    loop:true,
                    margin: 50,
                    nav: true,
                    autoplay: true,
                    smartSpeed: 1000,
                    autoplayTimeout: 5000,
                    autoplayHoverPause: true,
                    navText: '<>',
                    responsive:{
                        0:{
                            items:1
                        },
                        600:{
                            items:2
                        },
                        992:{
                            items:3
                        }
                    }
                });

                $('#companies').owlCarousel({
                    loop:true,
                    margin: 50,
                    nav: true,
                    smartSpeed: 1000,
                    autoplayTimeout: 5000,
                    navText: '<>',
                    responsive:{
                        0:{
                            items:1
                        },
                        600:{
                            items:2
                        },
                        992:{
                            items:4
                        }
                    }
                });

                // Short text
                $('.dotdot').dotdotdot();
            });
        </script>
    </body>
    </html>
</compress:html>