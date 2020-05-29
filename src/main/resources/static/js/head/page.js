//当前激活的页码对象，当前显示的最小页码，前一页、下一页、第一页、最后一页的对象
var $currentPageNo, $maxPageNo;
function createPageNo(pageTitleNum) {
    $currentPageNo = 1;
    $maxPageNo = pageTitleNum;
    var $thisPageNumber = $('#page-box');
    var $prePage = '<li id="prePage" class="page-item"><a class="page-link"><i class="mdi mdi-chevron-left"></i></a></li>';
    var $nextPage = '<li id="nextPage" class="page-item"><a class="page-link" href="#"><i class="mdi mdi-chevron-right"></i></a></li>';
    var $showPage = '';
    //需要展示页码
    for (let i = 0; i <pageTitleNum; i ++) {
        var pageNo = i + 1;
        console.log(pageNo)
        if (pageNo === 1) {
            $showPage += '<li class="page-item active" id="'+pageNo+'" value="'+pageNo+'"><a class="page-link" href="#">'+pageNo+'</a></li>'
        } else if (pageNo > 5) {
            $showPage += '<li class="page-item" id="'+pageNo+'" value="'+pageNo+'" style="display: none"><a class="page-link" href="#">'+pageNo+'</a></li>'
        } else {
            $showPage += '<li class="page-item" id="'+pageNo+'" value="'+pageNo+'"><a class="page-link" href="#">'+pageNo+'</a></li>'
        }
    }
    console.log($prePage + $showPage + $nextPage)
    $thisPageNumber.html($prePage + $showPage + $nextPage + '<li class="page-item" style="margin-top: 2px;margin-left: 5px">共&nbsp;('+pageTitleNum+')&nbsp;页</li>');
}

//监听点击事件
$('body').on('click', '.page-item', function(){
    //点击上一页
    if ($(this).attr('id') === "prePage") {
        if ($currentPageNo <= 1) {
            return false;
        }
        $(".page-item").removeClass("active");
        $currentPageNo = $currentPageNo - 1;
        $('#'+ $currentPageNo).addClass("active");
        if ($maxPageNo > 5 && ($('#'+$currentPageNo).css('display')==='none')) {
            $('#'+($currentPageNo)).css('display', 'block');
            $('#'+($currentPageNo + 5)).css('display', 'none');
        }

    } else if ($(this).attr('id') === "nextPage") {
        //点击下一页
        if ($currentPageNo >= $maxPageNo) {
            return false;
        }
        $(".page-item").removeClass("active");
        $currentPageNo = $currentPageNo + 1;
        $('#'+ ($currentPageNo)).addClass("active");
        if ($maxPageNo > 5 && ($('#'+$currentPageNo).css('display')==='none')) {
            $('#'+($currentPageNo)).css('display', 'block');
            $('#'+($currentPageNo - 5)).css('display', 'none');
        }
    } else {
        //点击号码页
        $(".page-item").removeClass("active");
        $(this).addClass("active");
        $currentPageNo = Number($(this).attr('value'));
    }
});