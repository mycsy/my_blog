$('.left-nav a').click(function () {
    $(".active").removeClass('active');
    $(this).addClass("active");
    var url = $(this).attr('href').split('#')[1];
    $('#show-box').html("<iframe border='none' style='border:0; padding:15px' scrolling='no' width='100%' height='800' src='" + url + "'></iframe>");
});