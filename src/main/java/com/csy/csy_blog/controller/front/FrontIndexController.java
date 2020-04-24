package com.csy.csy_blog.controller.front;

import com.alibaba.fastjson.JSONObject;
import com.csy.csy_blog.domain.Article;
import com.csy.csy_blog.domain.Label;
import com.csy.csy_blog.pojo.BaseQuery;
import com.csy.csy_blog.pojo.Result;
import com.csy.csy_blog.service.ArticleService;
import com.csy.csy_blog.service.LabelService;
import com.csy.csy_blog.utils.ResultHelper;
import com.csy.csy_blog.vomain.ArticleByDateVo;
import com.csy.csy_blog.vomain.ArticleVo;
import com.csy.csy_blog.vomain.IndexVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/front/index")
public class FrontIndexController {
    @Autowired
    private ArticleService articleService;
    @Autowired
    private LabelService labelService;

    /**
     * 获取首页内容
     * @return
     */
    @GetMapping("/get")
    public JSONObject index() {
        Result result = new Result();
        result.setSuccess(true);
        try {
            IndexVo indexVo = new IndexVo();
            //获取顶置文章
            Article topArticle = articleService.findTopArticle();
            //推荐文章集合
            List<Article> topList = articleService.findTopList();
            //获取热门文章集合
            List<ArticleVo> articleList = articleService.findHotList(5);
            //获取所有标签
            List<Label> labelList = labelService.findAllLabel();
            //查询时间归档数
            List<ArticleByDateVo> articleByDateVos = articleService.findArticleByDateList();
            //组装数据
            indexVo.setTopList(topList);
            indexVo.setTopArticle(topArticle);
            indexVo.setLabelList(labelList);
            indexVo.setArticleList(articleList);
            indexVo.setArticleByDateVoList(articleByDateVos);
            result.addModel("data", indexVo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResultHelper.renderAsJsonWipeData(result);
    }
}
