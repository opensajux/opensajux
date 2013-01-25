/**
 * 
 */
package com.opensajux.service;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.Text;
import com.opensajux.common.PaginationParameters;
import com.opensajux.entity.News;

/**
 * @author Sheikh Mohammad Sajid
 * 
 */
@Singleton
public class NewsService implements Serializable {
	private static final long serialVersionUID = -7611808114349663126L;

	@Inject
	private transient PersistenceManagerFactory pmf;

	public void saveNews(String title, String content) {
		PersistenceManager pm = pmf.getPersistenceManager();
		try {
			News news = new News();
			news.setTitle(title);
			news.setContent(new Text(content));
			news.setPublishDate(new Date());
			news.setUpdatedDate(news.getPublishDate());
			pm.makePersistent(news);
		} finally {
			pm.close();
		}
	}

	public void removeNews(String id) {
		News news = getById(id);
		PersistenceManager pm = pmf.getPersistenceManager();
		pm.deletePersistent(news);
		pm.close();
	}

	@SuppressWarnings("unchecked")
	public List<News> getNews(PaginationParameters params) {
		PersistenceManager pm = pmf.getPersistenceManager();
		Query query = pm.newQuery(News.class);
		if (params != null)
			query.setRange(params.getFirst(), params.getFirst() + params.getPageSize());

		List<News> news = (List<News>) query.execute();
		pm.close();
		return news;
	}

	public Long getCount() {
		PersistenceManager pm = pmf.getPersistenceManager();
		Long count = (Long) pm.newQuery("select count(key) from " + News.class.getName()).execute();
		pm.close();
		return count;
	}

	public News getById(String id) {
		PersistenceManager pm = pmf.getPersistenceManager();
		Key k = KeyFactory.createKey(News.class.getSimpleName(), Long.valueOf(id));
		News news = pm.getObjectById(News.class, k);
		pm.close();
		return news;
	}
}
