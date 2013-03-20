package com.opensajux.service;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.opensajux.common.PaginationParameters;
import com.opensajux.entity.MenuItem;

/**
 * @author Sheikh Mohammad Sajid
 * 
 */
@ApplicationScoped
public class MenuItemService implements Serializable {
	private static final long serialVersionUID = -1855982945173023120L;
	private static Logger LOGGER = Logger.getLogger(MenuItemService.class.getName());

	@Inject
	private transient PersistenceManagerFactory pmf;

	public void saveMenuItem(MenuItem menuItem) {
		PersistenceManager pm = pmf.getPersistenceManager();
		try {
			pm.makePersistent(menuItem);
			if (LOGGER.isLoggable(Level.INFO))
				LOGGER.info("Saving menu item: " + menuItem.getTitle());
		} finally {
			pm.close();
		}
	}

	public void removeMenuItem(String id) {
		MenuItem menuItem = getById(id);
		PersistenceManager pm = pmf.getPersistenceManager();
		pm.deletePersistent(menuItem);
		pm.close();
	}

	public void removeMenuItem(Collection<MenuItem> coll) {
		PersistenceManager pm = pmf.getPersistenceManager();
		pm.deletePersistentAll(coll);
		pm.close();
	}

	public void removeMenuItem(MenuItem[] selectedMenuItems) {
		PersistenceManager pm = pmf.getPersistenceManager();
		pm.makePersistentAll(selectedMenuItems);
		pm.deletePersistentAll(selectedMenuItems);
		pm.close();
	}

	@SuppressWarnings("unchecked")
	public List<MenuItem> getMenuItems(PaginationParameters params, String menuName) {
		PersistenceManager pm = pmf.getPersistenceManager();
		Query query = pm.newQuery(MenuItem.class);
		//query.setFilter("menu.name =='" + menuName + "'");

		if (params != null) {
			query.setRange(params.getFirst(), params.getFirst() + params.getPageSize());
			if (params.getSortField() != null)
				query.setOrdering(params.getSortField() + " " + params.getSortOrder());
		}

		Object object = query.execute();
		List<MenuItem> menuItems = (List<MenuItem>) object;
		menuItems = menuItems.subList(0, menuItems.size());
		pm.close();
		return menuItems;
	}

	public Long getCount() {
		PersistenceManager pm = pmf.getPersistenceManager();
		Long count = (Long) pm.newQuery("select count(key) from " + MenuItem.class.getName()).execute();
		pm.close();
		return count;
	}

	public MenuItem getById(String id) {
		PersistenceManager pm = pmf.getPersistenceManager();
		Key k = KeyFactory.createKey(MenuItem.class.getSimpleName(), Long.valueOf(id));
		MenuItem menuItem = pm.getObjectById(MenuItem.class, k);
		pm.close();
		return menuItem;
	}
}
