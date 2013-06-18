package com.opensajux.view;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletContext;

import com.opensajux.common.ApplicationCacheProvider;
import com.opensajux.entity.Page;
import com.opensajux.service.PageService;

@Named
@SessionScoped
public class PageBean implements Serializable {
	private static final long serialVersionUID = 5376283545078969627L;

	@Inject
	private PageService pageService;

	public List<Page> getTopPages() {
		return pageService.getTopPages();
	}

	public void addPage() {
		Page page = pageService.addDefaultPage();
		ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext()
				.getContext();
		ApplicationCacheProvider.get().getURLCache(servletContext).put(page.getFriendlyUrl(), page);
	}
}
