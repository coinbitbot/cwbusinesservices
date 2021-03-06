<%@ taglib uri="http://htmlcompressor.googlecode.com/taglib/compressor" prefix="compress"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<compress:html removeIntertagSpaces="true">
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <jsp:include page="../common/include_external_head_top.jsp" />
    <title>Create request</title>
    <jsp:include page="../common/include_resources.jsp" />
</head>
<body>
<jsp:include page="../common/include_external_body_top.jsp" />
<jsp:include page="../common/header.jsp"/>

<div class="wrapper">
    <div class="container">
        <section>
            <div class="col-sm-10 col-sm-offset-1 col-md-8 col-md-offset-2">
                <h1>Create request</h1>
                <form id="sign_up_form">
                    <div id="company_fields">
                        <div class="register-block">
                            <h4 class="subheader">Company info</h4>
                            <div class="row">
                                <div class="col-xs-12">
                                    <input type="text" name="company_name" placeholder="My company" class="form-field col-xs-12">
                                </div>
                                <div class="col-xs-12 col-sm-8">
                                    <input type="text" id="filename" class="filename col-xs-12" placeholder="Download Business Plan" disabled>
                                </div>
                                <div class="col-xs-12 text-center col-sm-4">
                                    <input type="file" name="file" id="request_file" class="inputfile">
                                    <label for="request_file" class="col-xs-10 col-xs-offset-1 col-sm-12 col-sm-offset-0 btn-theme btn-1 btn-choose-file">Choose file</label>
                                </div>
                            </div>
                        </div>
                        <div class="register-block">
                            <h4 class="subheader">Your interests</h4>
                            <div class="row">
                                <c:if test="${interests ne null}">
                                    <div class="">
                                        <c:forEach var="interest" items="${interests}">
                                            <div class="col-xs-12 col-sm-6 interested-item">
                                                <input type="checkbox" name="interests" value="${interest.id}" id="interest${interest.id}" class="form-field checkbox"> <label for="interest${interest.id}">${interest.name}</label>
                                            </div>
                                        </c:forEach>
                                    </div>
                                    <div class="clearfix"></div>
                                </c:if>
                                <div class="col-xs-12">
                                    <input type="text" name="interest_alter" class="form-field col-xs-12" placeholder="Does not find your interest? Enter it here">
                                </div>
                            </div>
                            <h4 class="subheader">Industry</h4>
                            <div class="row">
                                <c:if test="${industries ne null}">
                                    <div class="col-xs-12">
                                        <select name="industry" class="form-field col-xs-12">
                                            <c:forEach var="industry" items="${industries}">
                                                <option value="${industry.id}">${industry.name}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </c:if>
                            </div>
                        </div>
                    </div>
                    <div class="col-xs-12 text-center">
                        <button type="submit" class="btn-theme btn-2">Register an request</button>
                    </div>
                    <div class="clearfix"></div>
                </form>
            </div>
            <div class="clearfix"></div>
        </section>
    </div>
</div>

<jsp:include page="../common/footer.jsp" />
<script src="/resources/js/utils/parse_url.js"></script>
<script src="/resources/js/requests/create.js?current_user_id=${current_user.id}" id="loader"></script>
<script type="text/javascript">
    $(document).ready( function() {
        $(".inputfile").change(function(){
            var filename = $(this).val().replace(/.*\\/, "");
            $("#filename").val(filename);
        });
    });
</script>
</body>
</html>
</compress:html>

