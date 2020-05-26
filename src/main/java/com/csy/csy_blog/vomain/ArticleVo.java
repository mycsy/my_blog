package com.csy.csy_blog.vomain;

import com.csy.csy_blog.domain.Article;
import com.csy.csy_blog.domain.Label;
import lombok.Data;

import java.util.List;

@Data
public class ArticleVo extends Article{
    private List<LabelVo> labelList;
    private List<CommentVo> commentList;
}
