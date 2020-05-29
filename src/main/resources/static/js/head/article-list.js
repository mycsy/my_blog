$(function () {
    var labelId = queryString('labelId');
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
            },
            labelToArticle: function (labelId) {
                window.location.href="/page/head/article-list.html?labelId="+labelId;
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
    //创建页码
    findPageInfo(1, true);
    function findPageInfo(pageNo, createPage) {
        var params = {
            page:pageNo,
            limit:6,
            paramMap: {
                labelId: labelId
            }
        }
        jQuery.axspost('/front/article/page',JSON.stringify(params),function (res) {
            articleList.articleList = res.data;
            //创建分页标签
            if (createPage) {
                createPageNo(Math.ceil(res.count/6));
            }
        });
    }
    //点击页码时请求数据
    $('body').on('click', '.page-item', function(){
        findPageInfo($currentPageNo);
    });
});
