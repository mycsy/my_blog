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
        index.articleList = res.data.articleList;
        index.labelList = res.data.labelList;
        index.topList = res.data.topList;
        index.topArticle = res.data.topArticle;
        console.log(JSON.stringify(res.data));
    });
});