package com.opensajux.view;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.opensajux.entity.MyBlog;
import com.opensajux.service.BlogService;

@RequestScoped
@Named
public class BlogBean implements Serializable {
	private static final long serialVersionUID = 5073749853189883398L;

	@Inject
	private PersistenceManagerFactory pmf;
	@Inject
	private BlogService blogService;

	private LazyDataModel<MyBlog> blogModel;
	private MyBlog[] selectedBlogs;
	private MyBlog[] filteredBlogs;
	private String blogUrl = "";

	public BlogBean() {
	}

	public LazyDataModel<MyBlog> getBlogs() {
		if (blogModel == null) {
			blogModel = new LazyDataModel<MyBlog>() {
				private static final long serialVersionUID = 1219229442313878528L;

				@Override
				public int getRowCount() {
					PersistenceManager pm = pmf.getPersistenceManager();
					Long count = (Long) pm.newQuery("select count(key) from " + MyBlog.class.getName()).execute();
					pm.close();
					return count.intValue();
				}

				@Override
				public List<MyBlog> load(int first, int pageSize, String sortField, SortOrder sortOrder,
						Map<String, String> filters) {
					setPageSize(pageSize);
					PersistenceManager pm = pmf.getPersistenceManager();
					Query query = pm.newQuery(MyBlog.class);
					query.setRange(first, first + pageSize);
					query.setOrdering("publishDate desc");

					List<MyBlog> blogs = (List<MyBlog>) query.execute();
					pm.close();
					return blogs;
				}

				@Override
				public Object getRowKey(MyBlog blog) {
					return blog.getId();
				}

				@Override
				public MyBlog getRowData(String rowKey) {
					PersistenceManager pm = pmf.getPersistenceManager();
					Query query = pm.newQuery("select from " + MyBlog.class.getName() + " where id == rowId");
					query.declareParameters("String rowId");
					MyBlog b = (MyBlog) ((List<MyBlog>) query.execute(rowKey)).get(0);
					pm.close();
					return b;
				}

			};
			blogModel.setPageSize(5);
		}
		return blogModel;
	}

	/**
	 * @return the selectedBlogs
	 */
	public MyBlog[] getSelectedBlogs() {
		return selectedBlogs;
	}

	/**
	 * @param selectedBlogs
	 *            the selectedBlogs to set
	 */
	public void setSelectedBlogs(MyBlog[] selectedBlogs) {
		this.selectedBlogs = selectedBlogs;
	}

	/**
	 * @return the filteredBlogs
	 */
	public MyBlog[] getFilteredBlogs() {
		return filteredBlogs;
	}

	/**
	 * @param filteredBlogs
	 *            the filteredBlogs to set
	 */
	public void setFilteredBlogs(MyBlog[] filteredBlogs) {
		this.filteredBlogs = filteredBlogs;
	}

	/**
	 * @return the blogUrl
	 */
	public String getBlogUrl() {
		return blogUrl;
	}

	/**
	 * @param blogUrl
	 *            the blogUrl to set
	 */
	public void setBlogUrl(String blogUrl) {
		this.blogUrl = blogUrl;
	}

	public void saveBlog() {
		blogService.saveBlog(blogUrl);
	}

	public void removeBlogs() {
		for (MyBlog b : selectedBlogs) {
			blogService.removeBlog(b.getId());
		}
	}
}
