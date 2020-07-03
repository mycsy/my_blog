var article_id;
$(function(){
    $('#article_content').summernote({
        height: 280,
        minHeight: null,
        maxHeight: null,
        focus: true,
        lang: 'zh-CN',
        // 重写图片上传
        callbacks: {
            onImageUpload: function (files, editor, $editable) {
                uploadSummerPic(files[0], editor, $editable);
            }
        }
    });

    var article = new Vue({
        el: '#article',
        data: {
            article: {},
            label_list: []
        }
    });
    jQuery.get(config.url + '/cms/label/find/all',null,function (res) {
        article.label_list = res.data;
        console.log(res.data)
    });
    //查询文章详情
    var articleId = queryString('article_id');
    article_id =articleId;
    jQuery.axpost(config.url + '/cms/article/find/detail', {articleId: articleId}, function (res) {
        article.article = res.data;
        $('#article_content').summernote('code', res.data.articleContent);// 赋值
        //选中标签
        res.data.labelList.forEach(function (v, k) {
            article.label_list.forEach(function (label, kk) {
                if (v.id === label.id) {
                    label["is_checked"] = true;
                }
            })
        })
    });
});
/*summernote的图片上传*/
function uploadSummerPic(file) {
    debugger
    var fd = new FormData();
    fd.append("img", file);
    $.ajax({
        type:"POST",
        url:config.url + "/common/upload/image",
        data: fd,
        cache: false,
        contentType: false,
        processData: false,
        success: function (res) {
            $('#article_content').summernote('insertImage', "http://image.csy666.club", res.fileName);
        }
    });
}

$('#cancel').click(function () {
    window.location.href = '/article-list.html';
});
$('#save').click(function () {
    var article_title = $('#article_title').val();
    var article_introduction = $('#article_introduction').val();
    var article_content = $("#article_content").summernote("code");
    var arr = [];
    $("input:checkbox[name='label_id']:checked").each(function (i) {
        arr[i] = {'id':$(this).attr("value")};
    });
    var article = {
        id: article_id,
        articleTitle:article_title,
        articleIntroduction:article_introduction,
        articleContent:article_content,
        labelList:arr
    };
    jQuery.axspost(config.url + '/cms/article/edit',JSON.stringify(article), function (res) {
        if (res.success) {
            toastr.success("文章修改成功");
            setTimeout(function() {
                window.location.href = 'article-list.html';
            }, 2000)
        } else {
            toastr.error("文章修改失败，请重试");
            return false;
        }
    },function () {
        toastr.error("文章修改失败，请重试");
    })
});
