function showErrorMessage(error) {
    if (typeof error === 'string') {
        alertify.error(error);
    } else {
        alertify.error(error.message + buildValidationErrors(error.errors));
    }
}

function showSuccessMessage(message) {
    alertify.success(message);
}

function showInfoMessage(message) {
    alertify.message(message);
}

function buildValidationErrors(errors) {
    if (!errors || errors.length < 1) {
        return '';
    }

    return '<br />[' + errors.join(', ') + ']';
}


$(document).ready(function() {
    // Animate
    new WOW().init();

    // Login block
    $('.fa-user').click(function () {
        $('.login-block').toggleClass('shown');
    });

    // Mobile menu
    $('.menu-mob-button').click(function () {
        if ($('.topmenu').is(':visible')) {
            $('.topmenu').hide('slow');
            $(this).html('<a href="#"><i class="fa fa-bars" aria-hidden="true"></i></a>')
        }
        else {
            $('.topmenu').show('slow');
            $(this).html('<a href="#"><i class="fa fa-times" aria-hidden="true"></i></a>')
        }
    });

    // Scroll to top
    $(window).scroll(function () {
        if ($(this).scrollTop() > 0) {
            $('#scroller').fadeIn();
        } else {
            $('#scroller').fadeOut();
        }
    });
    $('#scroller').click(function () {
        $('body,html').animate({
            scrollTop: 0
        }, 400);
        return false;
    });

    // Fixed top menu
    var header = $(".navbar-fixed-top");
    var scrollPrev = 0;

    $(window).scroll(function () {
        var scrolled = $(window).scrollTop();
        var firstScrollUp = false;
        var firstScrollDown = false;
        if (scrolled > 0) {
            if (scrolled > scrollPrev) {
                firstScrollUp = false;
                if (scrolled < header.height() + header.offset().top) {
                    if (firstScrollDown === false) {
                        var topPosition = header.offset().top;
                        header.css({
                            "top": topPosition + "px"
                        });
                        firstScrollDown = true;
                    }
                    header.css({
                        "position": "absolute"
                    });
                } else {
                    header.css({
                        "position": "fixed",
                        "top": "-" + header.height() + "px"
                    });
                }
            } else {
                firstScrollDown = false;
                if (scrolled > header.offset().top) {
                    if (firstScrollUp === false) {
                        var topPosition = header.offset().top;
                        header.css({
                            "top": topPosition + "px"
                        });
                        firstScrollUp = true;
                    }
                    header.css({
                        "position": "absolute"
                    });
                } else {
                    header.removeAttr("style");
                }
            }
            scrollPrev = scrolled;
        }
    });
});

