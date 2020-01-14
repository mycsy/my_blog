$(function () {
    var article = new Vue({
        el: '#article',
        data: {
            article: {}
        },
    });
    var articleId = queryString('article_id');
    jQuery.get('/front/article/find/detail', {article_id: articleId}, function (res) {
        res.data.articleDate = timeStamp2String(res.data.articleDate);
        article.article = res.data;
        $('#articleContent').html(res.data.articleContent);
    })
});