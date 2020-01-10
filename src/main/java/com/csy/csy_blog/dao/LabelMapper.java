package com.csy.csy_blog.dao;

import com.csy.csy_blog.domain.Label;
import com.csy.csy_blog.vomain.LabelVo;

import java.util.List;

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
}