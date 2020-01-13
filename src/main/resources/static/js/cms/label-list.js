$('#label_table').bootstrapTable({
    url: '/cms/label/page',
    method: 'POST',
    contentType:"application/json",
    pagination: true, // 是否分页
    queryParams: queryParams,
    toolbar: "#toolbar",
    sidePagination:'server',
    striped: true, // 是否显示行间隔色
    pageNumber : 1, //当前第几页
    pageSize: "10",
    cache : false,
    pageList : [ 10, 25, 50, 100 ], //记录数可选列表
    columns: [
        {
            field: 'labelName',
            title: '标签名称',
            align: 'center'
        },
        {
            field: 'labelDescription',
            title: '标签描述'
        },
        {
            field: 'price',
            title: '操作',
            width: 120,
            align: 'center',
            valign: 'middle',
            formatter: actionFormatter,
        },

    ]
});
//操作栏的格式化
function actionFormatter(value, row, index) {
    var id = value;
    var result = "";
    result += "<a href='javascript:;' class='btn btn-xs green' οnclick=\"EditViewById('" + id + "', view='view')\" title='查看'><span class='glyphicon glyphicon-search'></span></a>";
    result += "<a href='javascript:;' class='btn btn-xs blue' οnclick=\"EditViewById('" + id + "')\" title='编辑'><span class='glyphicon glyphicon-pencil'></span></a>";
    result += "<a href='javascript:;' class='btn btn-xs red' οnclick=\"DeleteByIds('" + id + "')\" title='删除'><span class='glyphicon glyphicon-remove'></span></a>";
    return result;
}

function queryParams(params) {
    console.log(params);
    var query = {
        page: this.pageNumber,
        limit: this.pageSize
    }
    console.log(query);
    return JSON.stringify(query);
}
