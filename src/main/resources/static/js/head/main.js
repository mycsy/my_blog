$(document).ready(function () {
    getUrl('index.html');
   $('li').click(function () {
      $('li.active').removeClass('active');
      $(this).addClass('active');
   });
});
var id;
function getUrl(url) {
    var main = $('#main');
    id = new Date().getMilliseconds();
    main.html("<iframe id='"+id+"' border='none' style='border:0; padding:15px' scrolling='no' width='100%' height='800' onload='this.height=500' src='" + url + "'></iframe>");
}
function reinitIframe(){
    var iframe = document.getElementById(id);
    try{
        var bHeight = iframe.contentWindow.document.body.scrollHeight;
        var dHeight = iframe.contentWindow.document.documentElement.scrollHeight;
        var height = Math.max(bHeight, dHeight);
        iframe.height = height;
        console.log(height);
    }catch (ex){}
}
window.setInterval("reinitIframe(id)", 200);