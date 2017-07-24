<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<link href="/resources/icons/favicon.png" rel="shortcut icon" type="image/png" />

<jsp:include page="../common/bootstrap_include.jsp" />
<script src="/resources/js/jquery-3.1.0.min.js"></script>
<script src="/resources/js/ejs.min.js"></script>
<script src="/resources/js/ajax.js"></script>
<link rel="stylesheet" href="/resources/js/utils/notification/css/alertify.min.css">
<link rel="stylesheet" href="/resources/js/utils/notification/css/themes/default.min.css">
<script src="/resources/js/utils/notification/alertify.min.js"></script>
<script src="/resources/js/global_utils.js"></script>
<script src="/resources/js/auth/sign_in.js"></script>
<script src="/resources/js/utils/parse_url.js"></script>
<script src="/resources/js/md5.min.js"></script>

<script src="/resources/js/angular.min.js"></script>
<script>
    Ajax.setCSRF('${_csrf.headerName}', '${_csrf.token}');
    var HEADERS = {
        '${_csrf.headerName}': '${_csrf.token}',
        'Content-Type': 'application/json',
        'Accept': 'application/json'
    };

    function formPages(number_of_entities, per_page, container) {
        container.empty();
        var i = 0;
        do {
            container.append(['<option value="', i, '">', i+1, '</option>'].join(''));
            ++i;
            number_of_entities -= per_page;
        } while (number_of_entities > per_page);

        if (number_of_entities > 0)
            container.append(['<option value="', i, '">', i+1, '</option>'].join(''));
    }

    function redirectAfterFreeze(page) {
        setTimeout(function(){
            location.href = page;
        }, 1000);
    }

    $(function(){
        var li = $('a[href="'+location.pathname+'"]').parent().addClass('active');

        if(li.parent().hasClass('dropdown-menu')){
            li.parent().parent().addClass('active');
        }
    });
</script>

