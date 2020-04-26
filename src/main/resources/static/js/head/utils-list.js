$(function () {
    //0.初始化fileinput
    $('#txt_file').fileinput({
            language: 'zh', //设置语言
            showUpload: false,
            showPreview:false,
            allowedFileTypes: ['pdf'],
            maxFileCount: 1
        })
});
$('#delete_on_btn').click(function () {
    $("#delete_one").attr("action", config.url + "/front/pdf/delete/one").submit();
});

