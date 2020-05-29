$(function () {
    var index = new Vue({
        el: '#index',
        data: {
            articleList: [],
            labelList: [],
            topArticle: {},
            topList: [],
            articleByDateVoList: []
        },
        methods: {
            go: function (article_id) {
                window.location.href="/page/head/article-detail.html?article_id="+article_id;
            },
            labelToArticle: function (labelId) {
                window.location.href="/page/head/article-list.html?labelId="+labelId;
            },
            dateToArticle: function (yearMonth) {
                window.location.href="/page/head/article-list.html?yearMonth="+yearMonth;
            }
        },
        filters: {
            formatDate(time) {
                let date = new Date(time);
                return timeStamp2String(date, 'yyyy-MM-dd');
            },
            labHandle(labList) {
                var allLab = "";
                labList.forEach(function (v) {
                    allLab += v['labelName'] + ',';
                });
                return allLab.substr(0, allLab.length - 1);
            }
        }
    });
    jQuery.get(config.url + "/front/index/get",function (res) {
        $.cookie("top_list", JSON.stringify(res.data.topList));
        $.cookie("label_list", JSON.stringify(res.data.labelList));
        $.cookie("date_list", JSON.stringify(res.data.articleByDateVoList));
        index.articleList = res.data.articleList;
        index.labelList = res.data.labelList;
        index.topList = res.data.topList;
        index.topArticle = res.data.topArticle;
        index.articleByDateVoList = res.data.articleByDateVoList;
    });
});