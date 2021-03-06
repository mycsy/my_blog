package com.csy.csy_blog.controller.front;

import com.alibaba.fastjson.JSONObject;
import com.csy.csy_blog.aspect.ArticleReadCount;
import com.csy.csy_blog.controller.cms.CmsArticleController;
import com.csy.csy_blog.domain.Article;
import com.csy.csy_blog.pojo.BaseQuery;
import com.csy.csy_blog.pojo.QueryResult;
import com.csy.csy_blog.pojo.Result;
import com.csy.csy_blog.service.ArticleCommentService;
import com.csy.csy_blog.service.ArticleService;
import com.csy.csy_blog.service.LabelService;
import com.csy.csy_blog.utils.ResultHelper;
import com.csy.csy_blog.vomain.ArticleVo;
import com.csy.csy_blog.vomain.CommentVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/front/article")
public class FrontArticleController {
    @Autowired
    private ArticleService articleService;
    @Autowired
    private LabelService labelService;
    @Autowired
    private ArticleCommentService articleCommentService;

    /**
     * 查询文章全文
     * @param article_id
     * @return
     */
    @ArticleReadCount
    @RequestMapping("/find/detail")
    public JSONObject findDetail(Long article_id) {
        Result result = new Result();
        result.setSuccess(true);
        try {
            ArticleVo articleVo = articleService.findById(article_id);
            List<CommentVo> commentList = articleCommentService.findCommentListByArticleId(article_id);
            articleVo.setCommentList(commentList);
            result.addModel("data", articleVo);
        } catch (Exception e) {

        }
        return ResultHelper.renderAsJsonWipeData(result);
    }

    /**
     * 分页查询文章
     */
    @RequestMapping("/page")
    public JSONObject findPage(@RequestBody BaseQuery query) {
        Result result = new Result();
        result.setSuccess(true);
        try {
            QueryResult<Article> queryResult = articleService.findPageInfo(query);
            //为文章添加标签
            List<Article> articleList = (List<Article>)queryResult.getRows();
            List<ArticleVo> voList = labelService.fillAutoLabelForArticle(articleList);
            //返回总数和页面文章
            result.addModel("count", queryResult.getTotal());
            result.addModel("data", voList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResultHelper.renderAsJsonWipeData(result);
    }
}
