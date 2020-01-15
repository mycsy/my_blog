$(function () {
    var index = new Vue({
        el: '#index',
        data: {
            articleList: [],
            labelList: [],
            topArticle: {},
            topList: []
        },
        methods: {
            go: function (article_id) {
                window.location.href="article-detail.html?article_id="+article_id;
            }
        }
    });
    jQuery.get("/front/index/get",function (res) {
        console.log(res);
        index.articleList = res.data.articleList;
        index.labelList = res.data.labelList;
        index.topList = res.data.topList;
        index.topArticle = res.data.topArticle;
        console.log(index)
    });
})