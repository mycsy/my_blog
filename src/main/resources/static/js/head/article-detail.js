$(function () {
    var article = new Vue({
        el: '#article',
        data: {
            article: {}
        },
        filters: {
            labHandle(labList) {
                var allLab = "";
                for (var index in labList) {
                    allLab += labList[index]['labelName'] + ',';
                }
                return allLab.substr(0, allLab.length - 1);
            }
        }
    });
    var articleId = queryString('article_id');
    jQuery.axpost(config.url + '/front/article/find/detail', {article_id: articleId}, function (res) {
        res.data.articleDate = timeStamp2String(res.data.articleDate);
        article.article = res.data;
        $('#articleContent').html(res.data.articleContent);
    });


    var right_data = new Vue({
        el: '#right-box',
        data: {
            labelList: JSON.parse($.cookie("label_list")),
            articleByDateVoList: JSON.parse($.cookie("date_list")),
            topList: JSON.parse($.cookie("top_list"))
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

    /**
     * 用户注册
     */
    $('#register_').on('click', function () {
        $.axpost(config.url + '/front/user/register', $("#registerForm").serialize(), function (res) {
            $('#register').modal('hide');
            if (res.msg === 'success') {
                $.cookie("nick_name", res.user_info.nickName);
                $.cookie("user_id", res.user_info.id);
                toastr.success("已注册并登录");
            }
        })
    });

    //用户登录
    $('#login_').on('click', function () {
        $.axpost(config.url + '/front/user/login', $("#loginForm").serialize(), function (res) {
            if (res.msg === 'success') {
                $('#login').modal('hide');
                $.cookie("nick_name", res.user_info.nickName);
                $.cookie("user_id", res.user_info.id);
                toastr.success("登录成功");
            }
        })
    });
});

function submitComment() {
    //如果用户为登录弹出登录框
    if ($.cookie("user_id") == null) {
        $('#login').modal("show");
    }
}

function go(name) {
    if (name === 'login') {
        $('#register').modal('hide');
        $('#login').modal("show");
    } else {
        $('#login').modal('hide');
        $('#register').modal("show");
    }
}
