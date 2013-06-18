package com.opensajux.view;

import java.io.IOException;
import java.io.Serializable;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.google.api.services.blogger.model.Blog;
import com.opensajux.entity.MyBlog;
import com.opensajux.integration.BloggerClient;
import com.opensajux.service.BlogService;
import com.opensajux.service.PaginationParameters;

@RequestScoped
@Named
public class BlogBean implements Serializable {
	private static final long serialVersionUID = 5073749853189883398L;

	@Inject
	private BlogService blogService;
	@Inject
	private BloggerClient client;

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
					return blogService.getCount().intValue();
				}

				@Override
				public List<MyBlog> load(int first, int pageSize, String sortField, SortOrder sortOrder,
						Map<String, String> filters) {
					PaginationParameters param = new PaginationParameters();
					param.setFirst(first);
					param.setPageSize(pageSize);
					param.setSortField(sortField == null ? "updatedDate" : sortField);
					param.setSortOrder(sortOrder == null ? "desc" : sortOrder == SortOrder.ASCENDING ? "asc" : "desc");

					return blogService.getBlogs(param);
				}

				@Override
				public Object getRowKey(MyBlog blog) {
					return blog.getId();
				}

				@Override
				public MyBlog getRowData(String rowKey) {
					return blogService.getById(rowKey);
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
		try {
			Blog blog = client.getBlog(blogUrl);
			Calendar publishedDate = Calendar.getInstance();
			publishedDate.setTimeInMillis(blog.getPublished().getValue());
			Calendar updatedDate = Calendar.getInstance();
			updatedDate.setTimeInMillis(blog.getUpdated().getValue());
			blogService.saveBlog(blog.getUrl(), blog.getId().toString(), blog.getName(), publishedDate.getTime(),
					updatedDate.getTime());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void removeBlogs() {
		for (MyBlog b : selectedBlogs) {
			blogService.removeBlog(b.getId());
		}
	}
}
