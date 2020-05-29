$(function () {
    //0.初始化fileinput
    $('#pdf_file').fileinput({
            language: 'zh', //设置语言
            showUpload: false,
            showPreview:false,
            allowedFileTypes: ['pdf'],
            maxFileCount: 1
        })
    $('#file').fileinput({
        language: 'zh', //设置语言
        showUpload: false,
        showPreview:false,
        allowedFileTypes: ['pdf'],
        maxFileCount: 1
    })
});
$('#delete_on_btn').click(function () {
    if ($('#pdf_file').val() == null || $('#pdf_file').val() === '') {
        toastr.error("请先上传PDF文件");
        return false;
    }
    if ($('#pageNum').val() == null || $('#pageNum').val() === '') {
        toastr.error("请填写删除的页码");
        return false;
    }
    $("#delete_one").attr("action", config.url + "/front/pdf/delete/one").submit();
});
$('#delete_range_btn').click(function () {
    if ($('#file').val() == null || $('#file').val() === '') {
        toastr.error("请先上传PDF文件");
        return false;
    }
    if ($('#from').val() == null || $('#from').val() === '') {
        toastr.error("请填写起始页码");
        return false;
    }
    if ($('#end').val() == null || $('#end').val() === '') {
        toastr.error("请填写结束页码");
        return false;
    }
    $("#delete_range").attr("action", config.url + "/front/pdf/delete/range").submit();
});
console.log(JSON.parse($.cookie("top_list")))
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
