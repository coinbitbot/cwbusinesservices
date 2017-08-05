<%@ page import="java.util.ResourceBundle" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div id="cookie_popup" style="display: none;width: 100%; height: 60px; bottom: 0px; position: fixed; background: black; color: white; opacity: 0.8; font-size: 17px; padding: 10px;">
    We are using you cookies.
    <span id="close_cookie" style="width: 30px;height: 30px;border-radius: 15px;background: #ccc; position: fixed; right: 40px; bottom: 20px; text-align: center; line-height: 2;cursor: pointer;">X</span>
</div>

<footer>
    <div class="container">
        <div class="row">
            <a href="/info_pages/catalog">All info pages</a>
            <Br />
            <a href="/companies/catalog">All companies</a>
			<Br />
			<a href="/blog">Blog</a>
        </div>
    </div>
</footer>

<script src="/resources/js/ejs.min.js"></script>
<script src="/resources/js/ajax.js"></script>
<script>
    Ajax.setCSRF('${_csrf.headerName}', '${_csrf.token}');
</script>
<script src="/resources/js/md5.min.js"></script>
<script src="/resources/js/auth/sign_in.js"></script>
<link rel="stylesheet" href="/resources/js/utils/notification/css/alertify.min.css">
<link rel="stylesheet" href="/resources/js/utils/notification/css/themes/default.min.css">
<script src="/resources/js/utils/notification/alertify.min.js"></script>
<script src="/resources/js/global_utils.js"></script>
<script src="/resources/js/utils/wow.min.js"></script>
<script>new WOW().init();</script>

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