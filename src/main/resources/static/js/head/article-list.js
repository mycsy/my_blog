$(function () {
    var articleList = new Vue({
        el: '#articleList',
        data: {
            labelList: JSON.parse($.cookie("label_list")),
            articleByDateVoList: JSON.parse($.cookie("date_list")),
            topList: JSON.parse($.cookie("top_list")),
            articleList: []
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
   jQuery.axspost('/front/article/page',JSON.stringify({page:1,limit:10}),function (res) {
       articleList.articleList = res.data;
       createPageNo(6);
       console.log(res);
   });
});