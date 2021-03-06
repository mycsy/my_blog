package com.csy.csy_blog.service.impl;

import com.csy.csy_blog.dao.LabelMapper;
import com.csy.csy_blog.domain.Article;
import com.csy.csy_blog.domain.Label;
import com.csy.csy_blog.pojo.BaseQuery;
import com.csy.csy_blog.pojo.QueryResult;
import com.csy.csy_blog.service.LabelService;
import com.csy.csy_blog.utils.SnowflakeIdWorker;
import com.csy.csy_blog.vomain.ArticleVo;
import com.csy.csy_blog.vomain.LabelVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class LabelServiceImpl implements LabelService{
    @Autowired
    private LabelMapper labelMapper;
    @Autowired
    private SnowflakeIdWorker snowflakeIdWorker;

    @Override
    public List<ArticleVo> fillAutoLabelForArticle(List<Article> articleList) {
        List<ArticleVo> voList = new ArrayList<>();
        if (!CollectionUtils.isEmpty(articleList)) {
            //取出文章主键集合
            List<Long> articleIdList = articleList.stream().map(Article::getId).collect(Collectors.toList());
            //查询文章所属的标签
            List<LabelVo> labelVos = labelMapper.findListForArticleIds(articleIdList);
            //以文章id分组
            Map<Long, List<LabelVo>> voMap = labelVos.stream().collect(Collectors.groupingBy(LabelVo::getArticleId));
            //拼装返回数据
            for (Article article: articleList) {
                ArticleVo vo = new ArticleVo();
                BeanUtils.copyProperties(article, vo);
                List<LabelVo> labelVoList = voMap.get(article.getId());
                vo.setLabelList(labelVoList);
                voList.add(vo);
            }
        }
        return voList;
    }

    /**
     * 查询所有标签
     * @return
     */
    @Override
    public List<Label> findAllLabel() {
        return labelMapper.findAllLabel();
    }

    @Override
    public QueryResult<Label> findPageInfo(BaseQuery query) {
        QueryResult<Label> result = new QueryResult<Label>();
        result.setQuery(query);
        Map<String, Object> params = result.getQuery().build();
        int amount = labelMapper.findPageCount(params);
        result.setTotal(amount);
        if (amount == 0) {
            return result;
        }
        List<Label> list = labelMapper.findPageInfo(params);
        if (!CollectionUtils.isEmpty(list)) {
            result.setRows(list);
        }
        return result;
    }

    @Override
    public void create(Label label) {
        Long id = snowflakeIdWorker.nextId();
        label.setId(id);
        labelMapper.insert(label);
    }

    @Override
    public List<LabelVo> findVoListByArticleId(Long articleId) {
        return labelMapper.findVoListByArticleId(articleId);
    }
}
