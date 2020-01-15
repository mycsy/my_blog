package com.csy.csy_blog.aspect;

import com.csy.csy_blog.service.ArticleService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ArticleAspect {
    @Autowired
    private ArticleService articleService;

    /**
     * AOP拦截切入点，通过注解ArticleReadCount
     */
    @Pointcut("@annotation(ArticleReadCount)")
    private void articleRead(){};

    /**
     * 文章阅读数计算
     * @param joinPoint
     */
    @Before("articleRead()")
    public void ArticleReadCount(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        //获取文章id
        Long articleId = (Long)args[0];
        //文章阅读数+1
        articleService.addArticleReadCount(articleId);

    }
}
