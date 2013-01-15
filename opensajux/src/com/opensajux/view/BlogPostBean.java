package com.opensajux.view;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.opensajux.entity.BlogPost;

@RequestScoped
@Named
public class BlogPostBean implements Serializable {
	private static final long serialVersionUID = 5073749853189883398L;

	private static Logger LOGGER = Logger.getLogger(BlogPostBean.class.getName());
	@Inject
	private transient PersistenceManagerFactory pmf;
	private LazyDataModel<BlogPost> blogPostModel;

	public BlogPostBean() {
		blogPostModel = new LazyDataModel<BlogPost>() {
			private static final long serialVersionUID = 1219229442313878528L;

			@Override
			public int getRowCount() {
				PersistenceManager pm = pmf.getPersistenceManager();
				Long count = (Long) pm.newQuery("select count(key) from " + BlogPost.class.getName()).execute();
				pm.close();
				return count.intValue();
			}

			@Override
			public List<BlogPost> load(int first, int pageSize, String sortField, SortOrder sortOrder,
					Map<String, String> filters) {
				setPageSize(pageSize);
				PersistenceManager pm = pmf.getPersistenceManager();
				Query query = pm.newQuery(BlogPost.class);
				query.setRange(first, first + pageSize);
				query.setOrdering("publishDate desc");

				List<BlogPost> blogs = (List<BlogPost>) query.execute();

				pm.close();
				return blogs;
			}
		};
		blogPostModel.setPageSize(5);
	}

	public LazyDataModel<BlogPost> getPosts() {
		return blogPostModel;
	}
}
