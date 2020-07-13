$(document).ready(function () {

    // Side Navbar toggle button
    $('#toggle-btn').on('click', function (e) {
        // blocks default event handling
        e.preventDefault();

        if ($(window).outerWidth() > 1194) {
            // becomes a little smaller on larger screens
            $('nav.side-navbar').toggleClass('shrink');
            $('.content').toggleClass('enlarge');
            $('nav.side-navbar').removeClass('show');
            $('.content').removeClass('narrow');
        } else {
            $('nav.side-navbar').toggleClass('show');
            $('.content').toggleClass('narrow');
            $('nav.side-navbar').removeClass('shrink');
            $('.content').removeClass('enlarge');
        }

    });

    function getYear(){
    	$('#year').text(new Date().getFullYear());
    }
    
    window.onload = getYear();
    
});