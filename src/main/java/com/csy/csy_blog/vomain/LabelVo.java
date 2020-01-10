package com.csy.csy_blog.vomain;

import com.csy.csy_blog.domain.Label;
import lombok.Data;

@Data
public class LabelVo extends Label{
    private Long articleId;//文章Id
}
