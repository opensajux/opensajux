package com.opensajux.service;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import com.opensajux.common.PaginationParameters;
import com.opensajux.entity.News;

/**
 * @author Sheikh Mohammad Sajid
 * 
 */
public interface NewsService extends Serializable {

	public void saveNews(News news);

	public void removeNews(String id);

	public void removeNews(Collection<News> coll);

	public void removeNews(News[] selectedNews);

	public List<News> getNews(PaginationParameters params);

	public Long getCount();

	public News getById(String id);
}
