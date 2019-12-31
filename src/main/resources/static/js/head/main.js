$(document).ready(function () {
    getUrl('index.html');
   $('li').click(function () {
      $('li.active').removeClass('active');
      $(this).addClass('active');
   });
});

function getUrl(url) {
    var main = $('#main');
    main.html("<iframe border='none' style='border:0; padding:15px' scrolling='no' width='100%' height='3000px' src='" + url + "'></iframe>");
}
