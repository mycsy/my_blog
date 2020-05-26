package com.csy.csy_blog.vomain;

import com.csy.csy_blog.domain.ArticleComment;
import com.csy.csy_blog.domain.CommentReply;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class CommentVo extends ArticleComment{
    private Date commentTime;//评论时间
    List<CommentReply> replyList;//回复集合
}
