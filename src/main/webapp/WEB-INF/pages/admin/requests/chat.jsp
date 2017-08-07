<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html ng-app="edit">
<head>
    <title>Request chat #${id}</title>
</head>
<body>
<div class="container-fluid">
    <jsp:include page="../header.jsp" />

</div>
<jsp:include page="../include.jsp" />
<script src="/resources/js/admin/requests/chat.js?id=${id}" id="loader"></script>
</body>
</html>
