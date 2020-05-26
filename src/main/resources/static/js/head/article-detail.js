var reply_res;//用于二级回复数据组装
var article = new Vue({
    el: '#article',
    data: {
        article: {},
        comment_list: []
    },
    filters: {
        labHandle(labList) {
            var allLab = "";
            for (var index in labList) {
                allLab += labList[index]['labelName'] + ',';
            }
            return allLab.substr(0, allLab.length - 1);
        },
        formatDate(time) {
            let date = new Date(time);
            return timeStamp2String(date, '');
        }
    },
    methods: {
        goComment: function (comment) {
            $("#submit").attr("onclick","submitReply("+comment.id+");");
            var parentLi    = $('#'+comment.id),
                parentID    = $(this).data('id'),
                respond     = $('#respond');
            $("#respond").remove();
            parentLi.after(respond.prop("outerHTML"));
            $("#comment_parent").val(parentID);
            $('.cancel-comment-reply').show();
        },
        goReply: function (comment, reply) {
            $("#submit").attr("onclick","replyAgain()");
            var parentLi    = $('#'+comment.id),
                parentID    = $(this).data('id'),
                respond     = $('#respond');
            $("#respond").remove();
            reply_res = {
                articleId: article.article.id,
                commentId: comment.id,
                fromId: $.cookie('user_id'),
                toId: reply.fromId,
                content: $('#comment').val(),
                fromName: $.cookie('nick_name'),
                toName: reply.fromName
            };
            console.log(reply_res)
            parentLi.after(respond.prop("outerHTML"));
            $("#comment_parent").val(parentID);
            $('.cancel-comment-reply').show();
        }
    }
});
$(function () {
    var articleId = queryString('article_id');
    jQuery.axpost(config.url + '/front/article/find/detail', {article_id: articleId}, function (res) {
        console.log(res)
        res.data.articleDate = timeStamp2String(res.data.articleDate);
        article.article = res.data;
        article.comment_list = res.data.commentList;
        $('#commentCount').html("评论 ("+res.data.commentList.length+")");
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

//评论提交
function submitComment() {
    //如果用户为登录弹出登录框
    if ($.cookie("user_id") == null) {
        $('#login').modal("show");
        return false;
    }
    if ($('#comment').val().length === 0) {
        toastr.error("请填写评论");
        return false;
    }
    //编辑评论参数
    var com = {
        articleId: article.article.id,
        userId: $.cookie('user_id'),
        nickName: $.cookie('nick_name'),
        comment: $('#comment').val()
    };
    //提交评论
    $.axpost(config.url + '/front/comment/create', com, function (res) {
        if (res.msg === 'success') {
            toastr.success("评论成功");
            res.data['commentTime'] = res.data.createTime;
            res.data['replyList'] = [];
            article.comment_list.push(res.data);
            $('#comment').val('')
        }
    })
}

//提交回复
function submitReply(commentId) {
    //如果用户为登录弹出登录框
    if ($.cookie("user_id") == null) {
        $('#login').modal("show");
    }
    var comment = {};
    article.comment_list.forEach(function (v, k) {
        if (v.id == commentId) {
            comment = v;
        }
    });
    var reply = {
        articleId: article.article.id,
        commentId: comment.id,
        fromId: $.cookie('user_id'),
        toId: comment.userId,
        content: $('#comment').val(),
        fromName: $.cookie('nick_name'),
        toName: comment.nickName
    };
    //提交回复
    $.axpost(config.url + '/front/reply/create', reply, function (res) {
        if (res.msg === 'success') {
            toastr.success("回复成功");
            //页面添加回复
            article.comment_list.forEach(function (v, k) {
                if (v.id == commentId) {
                    v.replyList.push(res.data);
                }
            });
            $('#comment').val('')
        }
    })
}
//二级回复
function replyAgain() {
    console.log(reply_res)
    if ($.cookie("user_id") == null) {
        $('#login').modal("show");
    }
    var rep = {
        articleId: reply_res.articleId,
        commentId: reply_res.commentId,
        fromId: reply_res.fromId,
        toId: reply_res.toId,
        content: $('#comment').val() ,
        fromName: reply_res.fromName,
        toName: reply_res.toName
    }
    //提交回复
    $.axpost(config.url + '/front/reply/create', rep, function (res) {
        if (res.msg === 'success') {
            toastr.success("回复成功");
            //页面添加回复
            article.comment_list.forEach(function (v, k) {
                if (v.id == rep.commentId) {
                    v.replyList.push(res.data);
                }
            });
            $('#comment').val('')
        }
    })
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

function cancelReply() {
    $("#submit").attr("onclick","submitComment()");
    var respond     = $('#respond'),
        respondHtml = respond.prop("outerHTML");
    console.log(respondHtml)
    respond.remove();
    $('.lyear-comment').after(respondHtml);
    $("#comment_parent").val('0');
    $('.cancel-comment-reply').hide();
}


