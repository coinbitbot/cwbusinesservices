<%@ page import="java.util.ResourceBundle" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>

<div id="cookie_popup" class="hidden-print">
    <div class="container">We use cookies on this site to enhance your experience.
        By clicking any link on this website you are giving your consent for us to use
        cookies. Our cookie policy can be found <a href="#" class="policy-popup-show" data-toggle="modal" data-target="#cookie_policy_popup">here</a>.
    <span class="close-popup"><i class="fa fa-times" aria-hidden="true"></i></span>
    </div>
</div>

<div class="modal" style="display: none;" id="cookie_policy_popup" tabindex="-1" role="dialog" aria-labelledby="exampleModalLongTitle" aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">&times;</button>
                <h4 class="modal-title">Policy</h4>
            </div>
            <div class="modal-body">
                <p>
                A cookie is a small file which asks permission to be placed on your computer's hard drive. Once you agree, the file is added and the cookie helps analyze web traffic or lets you know when you visit a particular site. Cookies allow web applications to respond to you as an individual. The web application can tailor its operations to your needs, likes and dislikes by gathering and remembering information about your preferences.
                </p>
                <p>
                We use traffic log cookies to identify which pages are being used. This helps us analyze data about web page traffic and improve our website in order to tailor it to customer needs. We only use this information for statistical analysis purposes and then the data is removed from the system.
                </p>
                <p>
                Cookies help us provide you with a better website, by enabling us to monitor which pages you find useful and which you do not. A cookie in no way gives us access to your computer or any information about you, other than the data you choose to share with us.
                </p>
                <p>
                You can choose to accept or decline cookies. Most web browsers automatically accept cookies, but you can usually modify your browser setting to decline cookies if you prefer. This may prevent you from taking full advantage of the website.
                </p>
            </div>
        </div>
    </div>
</div>

<footer>
    <div class="container">
        <div class="row">
            <div class="col-xs-12 col-md-4 bottom-brand footer-block hidden-print">
                <a href="/">
                    <img src="/resources/images/logo_header.png" alt="<s:message code='brand'/>" />
                    <h4 class="font-theme-medium text-uppercase"><s:message code="brand.name"/><br/><span class="font-theme-normal"><s:message code="brand.subheader"/></span></h4>
                </a>
                <address>Phone: <a href="tel:+4401484506250">+44(0) 1484 506250</a><br>
                Email: <a href="mailto:cbs@charlesworth-group.com">cbs@charlesworth-group.com</a></address>
            </div>
            <div class="visible-print-inline-block footer-block bottom-brand col-xs-12">
                <img src="/resources/images/logo_grey.png" class=""/>
                <h4 class="font-theme-medium text-uppercase"><s:message code="brand.name"/><br/><span class="font-theme-normal"><s:message code="brand.subheader"/></span></h4>
                <address>
                    Address: 250 Deighton Road, Huddersfield, HD2 1JJ<br>
                    Phone: +44(0) 1484 506250<br>
                    Email: cbs@charlesworth-group.com
                </address>
            </div>
            <div class="col-sm-6 col-md-4 hidden-print footer-block">
                <h4 class="text-uppercase font-theme-medium">Information</h4>
                <ul>
                    <li><a href="/services">Services</a></li>
                    <li><a href="/companies/catalog">Companies</a></li>
                    <li><a href="/blog">Blog</a></li>
                    <li><a href="/employee">Employees</a></li>
                </ul>
            </div>
            <div class="col-sm-6 col-md-4 hidden-print social footer-block">
                <h4 class="text-uppercase font-theme-medium">Follow Us</h4>
                <a href="https://www.linkedin.com/company/11132848/"><i class="fa fa-linkedin" aria-hidden="true"></i></a>
                <a href="https://twitter.com/CWBusServ?lang=en"><i class="fa fa-twitter" aria-hidden="true"></i></a>
            </div>
            <div class="clearfix"></div>
        </div>
    </div>
</footer>
<div class="copyright hidden-print">
    <div class="container text-center">
        <script type="text/javascript">var mdate = new Date(); document.write(mdate.getFullYear());</script> &copy; All rights reserved
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