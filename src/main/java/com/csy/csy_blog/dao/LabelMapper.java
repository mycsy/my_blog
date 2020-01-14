package com.csy.csy_blog.dao;

import com.csy.csy_blog.domain.Label;
import com.csy.csy_blog.vomain.LabelVo;

import java.util.List;
import java.util.Map;

public interface LabelMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Label record);

    int insertSelective(Label record);

    Label selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Label record);

    int updateByPrimaryKey(Label record);

    /**
     * 为文章填充标签
     * @param list
     * @return
     */
    List<LabelVo> findListForArticleIds(List<Long> list);

    /**
     * 查询所有标签
     * @return
     */
    List<Label> findAllLabel();

    int findPageCount(Map<String, Object> params);

    List<Label> findPageInfo(Map<String, Object> params);

    /**
     * 通过文章id查询对应的标签
     * @param articleId
     * @return
     */
    List<LabelVo> findVoListByArticleId(Long articleId);
}