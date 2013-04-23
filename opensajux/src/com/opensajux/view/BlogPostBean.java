package com.opensajux.view;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.opensajux.common.PaginationParameters;
import com.opensajux.entity.BlogPost;
import com.opensajux.service.BlogService;

@RequestScoped
@Named
public class BlogPostBean implements Serializable {
	private static final long serialVersionUID = 5073749853189883398L;

	@Inject
	private BlogService blogService;
	private LazyDataModel<BlogPost> blogPostModel;

	public BlogPostBean() {
		blogPostModel = new LazyDataModel<BlogPost>() {
			private static final long serialVersionUID = 1219229442313878528L;

			@Override
			public int getRowCount() {
				return blogService.getBlogPostCount().intValue();
			}

			@Override
			public List<BlogPost> load(int first, int pageSize, String sortField, SortOrder sortOrder,
					Map<String, String> filters) {
				PaginationParameters param = new PaginationParameters();
				param.setFirst(first);
				param.setPageSize(pageSize);
				param.setSortField(sortField == null ? "updatedDate" : sortField);
				param.setSortOrder(sortOrder == null ? "desc" : sortOrder == SortOrder.ASCENDING ? "asc" : "desc");

				return blogService.getBlogPosts(param);
			}
		};
		blogPostModel.setPageSize(5);
	}

	public LazyDataModel<BlogPost> getPosts() {
		return blogPostModel;
	}
}
