package com.opensajux.common;

import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.jdo.PersistenceManagerFactory;
import javax.servlet.ServletContext;

import net.sf.jsr107cache.Cache;
import net.sf.jsr107cache.CacheException;
import net.sf.jsr107cache.CacheFactory;
import net.sf.jsr107cache.CacheManager;

import com.opensajux.dao.PMF;
import com.opensajux.entity.Page;
import com.opensajux.service.PageService;
import com.opensajux.service.impl.PageServiceImpl;

public class ApplicationCacheProvider {
	private final static Logger LOGGER = Logger.getLogger(ApplicationCacheProvider.class.getName());
	private final static String APPLICATION_NAVIGATION_CACHE = "app_nav_cache";
	private static ApplicationCacheProvider cacheProvider = new ApplicationCacheProvider();

	private PageService pageService;

	private ApplicationCacheProvider() {
		pageService = new PageServiceImpl();
		PersistenceManagerFactory pmf = PMF.getPMF();
		((PageServiceImpl) pageService).setPmf(pmf);
	}

	public static ApplicationCacheProvider get() {
		return cacheProvider;
	}

	public Cache getURLCache(ServletContext context) {
		// read from context
		Cache cache = (Cache) context.getAttribute(APPLICATION_NAVIGATION_CACHE);
		if (cache == null) {
			try {
				CacheFactory cacheFactory = CacheManager.getInstance().getCacheFactory();
				cache = cacheFactory.createCache(Collections.emptyMap());
				List<Page> pages = pageService.getTopPages();
				for (Page page : pages) {
					cache.put(page.getFriendlyUrl(), page);
				}
				cache.put("home", "index.jsf");
				context.setAttribute(APPLICATION_NAVIGATION_CACHE, cache);
			} catch (CacheException e) {
				LOGGER.log(Level.SEVERE, e.getMessage(), e);
			}
		}
		return cache;
	}

}
