package com.opensajux.service;

import java.io.Serializable;
import java.util.List;

import com.opensajux.common.PaginationParameters;
import com.opensajux.entity.BlogPost;
import com.opensajux.entity.MyBlog;

/**
 * @author Sheikh Mohammad Sajid
 * 
 */
public interface BlogService extends Serializable {
	public Long getCount();

	public List<MyBlog> getBlogs(PaginationParameters params);

	public MyBlog getById(String id);

	public void saveBlog(String url);

	public void removeBlog(String id);

	public Long getBlogPostCount();

	public List<BlogPost> getBlogPosts(PaginationParameters params);
}
