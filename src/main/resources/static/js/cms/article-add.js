$(function(){
    var labels = new Vue({
        el: '#label-list',
        data : {
            label_list:[]
        }
    });
   jQuery.get(config.url + '/cms/label/find/all',null,function (res) {
        labels.label_list = res.data;
   });

    $('#article_content').summernote({
        height: 280,
        minHeight: null,
        maxHeight: null,
        focus: true,
        lang: 'zh-CN',
        // 重写图片上传
        onImageUpload: function (files, editor, $editable) {
            sendFile(files[0], editor, $editable);
        }
    });
    var markupStr = 'hello world!';
    $('#article_content').summernote('code', markupStr);// 赋值
});

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
        articleTitle:article_title,
        articleIntroduction:article_introduction,
        articleContent:article_content,
        labelList:arr
    }
    jQuery.axspost(config.url + '/cms/article/create',JSON.stringify(article), function (res) {
        if (res.success) {
            toastr.success("文章添加成功");
            setTimeout(function() {
                window.location.href = 'article-list.html';
            }, 2000)
        }
    },function () {
        toastr.error("标签添添加失败，请重试");
    })
});