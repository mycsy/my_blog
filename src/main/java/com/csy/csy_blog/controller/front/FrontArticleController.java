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

import javax.xml.ws.Action;
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
            List<Article> articleList = (List<Article>)articleService.findPageInfo(query).getRows();
            List<ArticleVo> voList = labelService.fillAutoLabelForArticle(articleList);
            result.addModel("data", voList);
        } catch (Exception e) {

        }
        return ResultHelper.renderAsJsonWipeData(result);
    }
}
