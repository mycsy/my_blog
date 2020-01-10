$(function () {
    var index = new Vue({
        el: '#index',
        data: {
            articleList: [],
            labelList: []
        }
    });
    jQuery.get("/front/index/get",function (res) {
        console.log(res);
        index.articleList = res.data.articleList;
        index.labelList = res.data.labelList;
        console.log(index.labelList)
    });
})