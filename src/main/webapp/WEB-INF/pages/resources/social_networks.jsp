<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import = "java.util.ResourceBundle" %>
<% ResourceBundle resource = ResourceBundle.getBundle("environment");
    String app_id = resource.getString("facebook.app_id");
    String host = resource.getString("host.url");
%>

(function(e){
    e.Facebook = {
        app_id: '<%= app_id %>'
    }
    e.host = '<%= host %>'
})(window.SocialNetworks = {})