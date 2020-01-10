package com.csy.csy_blog.pojo;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

/**
 * 分页查询结果
 * @param <T>
 */
@Data
public class QueryResult<T> implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer totalPage;//总页数
	private Integer total;//总条数
	private Collection<T> rows = new ArrayList<T>();//当前页数据集合
	private BaseQuery query;

	public QueryResult(){

	}
}
