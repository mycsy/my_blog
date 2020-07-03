package com.csy.csy_blog.utils;

import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.Collection;

public class CheckUtils {

    /**
     * 字符串不能为空
     * @param str 字符窜
     * @param errMsg 错误信息
     * @throws Exception
     */
    public static void checkStringNotNull(String str, String errMsg) throws Exception{
        if (str == null || "".equals(str)) {
            throw new Exception(errMsg);
        }
    }

    /**
     * 集合不能为空
     * @param collection
     * @param errMsg
     * @throws Exception
     */
    public static void checkCollectionNotNull(Collection<?> collection, String errMsg) throws Exception{
        if (collection == null || collection.isEmpty()) {
            throw new Exception(errMsg);
        }
    }
}
