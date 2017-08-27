<%@ page import="java.util.ResourceBundle" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>

<div id="cookie_popup" class="hidden-print">
    <div class="container">We are using you cookies.
    <span id="close_cookie" class="close-popup"><i class="fa fa-times" aria-hidden="true"></i></span>
    </div>
</div>

<footer>
    <div class="container">
        <div class="row">
            <div class="hidden-xs hidden-sm col-md-4 bottom-brand footer-block hidden-print">
                <a href="/">
                    <img src="/resources/images/logo_header.png" alt="<s:message code='brand'/>" />
                    <h4 class="font-theme-medium text-uppercase"><s:message code="brand.name"/><br/><span class="font-theme-normal"><s:message code="brand.subheader"/></span></h4>
                </a>
            </div>
            <div class="visible-print-inline-block footer-block bottom-brand col-xs-6">
                    <img src="/resources/images/logo_grey.png" class=""/>
                    <h4 class="font-theme-medium text-uppercase"><s:message code="brand.name"/><br/><span class="font-theme-normal"><s:message code="brand.subheader"/></span></h4>
            </div>
            <div class="col-sm-6 col-md-4 hidden-print footer-block">
                <h4 class="text-uppercase font-theme-medium">Information</h4>
                <ul>
                    <li><a href="/services">Services</a></li>
                    <li><a href="/companies/catalog">Companies</a></li>
                    <li><a href="/blog">Blog</a></li>
                </ul>
            </div>
            <div class="col-sm-6 col-md-4 hidden-print social footer-block">
                <h4 class="text-uppercase font-theme-medium">Follow Us</h4>
                <a href=""><i class="fa fa-facebook" aria-hidden="true"></i></a>
                <a href=""><i class="fa fa-twitter" aria-hidden="true"></i></a>
            </div>
            <address class="col-xs-6 visible-print-inline-block text-right">
                <h4 class="text-uppercase font-theme-medium">Address</h4>
            </address>
            <div class="clearfix"></div>
        </div>
    </div>
</footer>
<div class="copyright hidden-print">
    <div class="container text-center">
        2017 &copy; All rights reserved
    </div>
</div>
<div id="scroller" class="hidden-xs hidden-print"></div>

<!-- Preloader -->
<div id="hellopreloader"><div id="hellopreloader_preload"></div></div>
<!-- /Preloader -->

<script src="/resources/js/ejs.min.js"></script>
<script src="/resources/js/ajax.js"></script>
<script>
    Ajax.setCSRF('${_csrf.headerName}', '${_csrf.token}');
    $(function(){
        $('form').append('<input type="hidden"' +
            'name="${_csrf.parameterName}"' +
            'value="${_csrf.token}"/>');
    });
</script>
<script src="/resources/js/md5.min.js"></script>
<script src="/resources/social_networks.js"></script>
<script src="/resources/js/auth/sign_in.js"></script>
<link rel="stylesheet" href="/resources/js/utils/notification/css/alertify.min.css">
<link rel="stylesheet" href="/resources/js/utils/notification/css/themes/default.min.css">
<script src="/resources/js/utils/notification/alertify.min.js"></script>
<script src="/resources/js/global_utils.js"></script>
<script src="/resources/js/utils/wow.min.js"></script>
<script src="/resources/js/utils/preload.js"></script>

<%-- show js error mssages setup --%>
<%
    ResourceBundle resource = ResourceBundle.getBundle("environment");
    boolean dev_server = Boolean.parseBoolean(resource.getString("dev_server"));
    String css = "";
    String script = "";
    String catch_error = "";
    if(dev_server){
        css = "<link rel=\"stylesheet\" href=\"/resources/js/utils/show_errors/show-js-error.css\" />";
        script = "<script src=\"/resources/js/utils/show_errors/show-js-error.js\"></script>";
    } else {
        catch_error = "<script src=\"/resources/js/utils/catch_errors.js?version=1\"></script>";
    }
%>
<%= css %>
<%= script %>
<%= catch_error %>
<%-- /show js error mssages setup --%>