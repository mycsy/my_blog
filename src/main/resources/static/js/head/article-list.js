$(function () {
    var blog = new Vue ({
        el: '#blog',
        data: {
            article_list: [],
            label_list: []
        },
        methods: {
            go: function (article_id) {
                window.location.href="article-detail.html?article_id="+article_id;
            }
        }
    });
   jQuery.axspost('/front/article/page',JSON.stringify({page:1,limit:10}),function (res) {
       blog.article_list = res.data;
       console.log(res);
   });
   jQuery.get('/front/label/find/all',null, function (res) {
       blog.label_list = res.data;
       console.log(res.data)
   })
});