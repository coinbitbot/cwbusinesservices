<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<header class="navbar-fixed-top">
    <div class="header">
        <div class="container">
            <div>
                <div class="col-xs-12 col-md-4 brand-logo text-uppercase">
                    <a href="/">
                        <img src="/resources/images/logo_header.png" alt="<s:message code='brand'/>" class="hidden-print"/>
                        <img src="/resources/images/logo_header_green.png" class="visible-print-inline-block"/>
                        <h1 class="font-theme-medium"><s:message code="brand.name"/><br/><span class="font-theme-normal"><s:message code="brand.subheader"/></span></h1>
                    </a>
                </div>
                <div class="col-md-8 text-right menu hidden-print">
                    <div class="menu-mob-button visible-xs visible-sm"><a href="#"><i class="fa fa-bars" aria-hidden="true"></i></a></div>
                    <nav>
                        <ul class="topmenu">
                        <c:choose>
                            <c:when test="${menuItems eq null}">
                                <li id="home"><a href="/"><s:message code="navmenu.home"/></a></li>
                                <li id="menu_services"><a href="/services"><s:message code="navmenu.services"/></a></li>
                                <li id="menu_companies"><a href="/companies/catalog"><s:message code="navmenu.companies"/></a></li>
                                <li id="menu_blog"><a href="/blog"><s:message code="navmenu.blog"/></a></li>
                                <li id="menu_employees"><a href="/employee">Employees</a></li>
                                <li id="menu_contacts"><a href="/contact_us"><s:message code="navmenu.contacts"/></a></li>
                            </c:when>
                            <c:otherwise>
                                <c:forEach var="menu" items="${menuItems}">
                                    <li>
                                        <c:choose>
                                            <c:when test="${fn:length(menu.childItems) > 0}">
                                                <a href="#" data-toggle="collapse" data-target="#${menu.id}">${menu.name} <span class="caret"></span></a>
                                                <ul id="${menu.id}">
                                                    <c:set var="menu" value="${menu}" scope="request"/>
                                                    <jsp:include page="menu_node.jsp"/>
                                                </ul>
                                            </c:when>
                                            <c:otherwise>
                                                <a href="${menu.url}">${menu.name}</a>
                                            </c:otherwise>
                                        </c:choose>
                                    </li>
                                </c:forEach>

                                <script>
                                    $(function(){
                                        $('.topmenu [href="' + location.pathname + '"]').parent('li').addClass('active');
                                    });
                                </script>
                            </c:otherwise>
                        </c:choose>
                        </ul>
                    </nav>
                </div>
            </div>
        </div>
    </div>
</header>
<div class="fixed-sidebar-right hidden-print">
    <div class="login-block">
        <i class="fa fa-user" aria-hidden="true"></i>
        <div class="login-block-content">
        <security:authorize access="hasAnyRole('ROLE_ADMIN', 'ROLE_MODERATOR')">
            <a href="/admin/cabinet" class="string"><i class="fa fa-tachometer" aria-hidden="true"></i>Admin dashboard</a><br/>
        </security:authorize>
        <security:authorize access="isAuthenticated()">
            <a href="/profile/" class="string"><i class="fa fa-user" aria-hidden="true"></i>Profile</a><br/>
            <a href="#" id="logout" class="string"><i class="fa fa-sign-out" aria-hidden="true"></i>Log out</a>
        </security:authorize>
        <security:authorize access="isAnonymous()">
            <a href="#" id="sign_in"><i class="fa fa-sign-in" aria-hidden="true"></i>Sign in</a> /
            <a href="/register">Sign up</a>
        </security:authorize>
        </div>
    </div>
</div>
<div class="clearfix"></div>