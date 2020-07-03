$(function(){
    var article = new Vue({
        el: '#article',
        data: {
            article: {},
        },
        filters: {
            labHandle(labList) {
                var allLab = "";
                for (var index in labList) {
                    allLab += labList[index]['labelName'] + ',';
                }
                return allLab.substr(0, allLab.length - 1);
            }
        },
    });
    //查询文章详情
    var articleId = queryString('article_id');
    jQuery.axpost(config.url + '/cms/article/find/detail', {articleId: articleId}, function (res) {
        res.data.articleDate = timeStamp2String(res.data.articleDate);
        article.article = res.data;
        $('#articleContent').html(res.data.articleContent);
    });
});
