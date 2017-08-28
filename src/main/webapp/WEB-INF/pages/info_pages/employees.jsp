<%@ taglib uri="http://htmlcompressor.googlecode.com/taglib/compressor" prefix="compress"%>
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
                <h1>${info_page.header}</h1>
                <h3>${info_page.sub_header}</h3>

                <!-- Repeating template -->
                <div class="text-desc employ-tmp wow fadeInUp" data-wow-offset="10">
                    <div class="col-md-4 employ-photo">
                        <img src="" class="img-responsive" />
                    </div>
                    <div class="col-md-8 employ-info">
                        <h3 class="text-uppercase">Name & Last name</h3>
                        ${info_page.text}
                    </div>
                    <div class="clearfix"></div>
                </div>
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
    </body>
    </html>
</compress:html>