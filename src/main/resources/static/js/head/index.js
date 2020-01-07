$(function () {
    var index = new Vue({
        el: '#index',
        data: {
            articleList: []
        }
    });
    jQuery.get("/front/index/get",function (res) {
        console.log(res);
        index.articleList = res.data.articleList;
        console.log(index.articleList)
    });
})