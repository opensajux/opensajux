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
import com.opensajux.entity.Menu;

/**
 * @author Sheikh Mohammad Sajid
 * 
 */
@ApplicationScoped
public class MenuService implements Serializable {
	private static final long serialVersionUID = -3564748695113507892L;
	private static Logger LOGGER = Logger.getLogger(MenuService.class.getName());

	@Inject
	private transient PersistenceManagerFactory pmf;

	public void saveMenu(Menu menu) {
		PersistenceManager pm = pmf.getPersistenceManager();
		try {
			pm.makePersistent(menu);
			if (LOGGER.isLoggable(Level.INFO))
				LOGGER.info("Saving menu: " + menu.getTitle());
		} finally {
			pm.close();
		}
	}

	public void removeMenu(String id) {
		Menu menu = getById(id);
		PersistenceManager pm = pmf.getPersistenceManager();
		pm.deletePersistent(menu);
		pm.close();
	}

	public void removeMenu(Collection<Menu> coll) {
		PersistenceManager pm = pmf.getPersistenceManager();
		pm.deletePersistentAll(coll);
		pm.close();
	}

	public void removeMenu(Menu[] selectedMenus) {
		PersistenceManager pm = pmf.getPersistenceManager();
		pm.makePersistentAll(selectedMenus);
		pm.deletePersistentAll(selectedMenus);
		pm.close();
	}

	@SuppressWarnings("unchecked")
	public List<Menu> getMenus(PaginationParameters params) {
		PersistenceManager pm = pmf.getPersistenceManager();
		Query query = pm.newQuery(Menu.class);

		if (params != null) {
			query.setRange(params.getFirst(), params.getFirst() + params.getPageSize());
			if (params.getSortField() != null)
				query.setOrdering(params.getSortField() + " " + params.getSortOrder());
		}

		Object object = query.execute();
		List<Menu> menu = (List<Menu>) object;
		menu = menu.subList(0, menu.size());
		pm.close();
		return menu;
	}

	public Long getCount() {
		PersistenceManager pm = pmf.getPersistenceManager();
		Long count = (Long) pm.newQuery("select count(key) from " + Menu.class.getName()).execute();
		pm.close();
		return count;
	}

	public Menu getById(String id) {
		PersistenceManager pm = pmf.getPersistenceManager();
		Key k = KeyFactory.createKey(Menu.class.getSimpleName(), Long.valueOf(id));
		Menu menu = pm.getObjectById(Menu.class, k);
		pm.close();
		return menu;
	}

	@SuppressWarnings("unchecked")
	public Menu getMenuByName(String name) {
		PersistenceManager pm = pmf.getPersistenceManager();
		Query query = pm.newQuery("select from " + Menu.class.getName() + " where name == n && isPublished == true");

		query.declareParameters("String n");
		List<Menu> m = (List<Menu>) query.execute(name);
		pm.close();
		return m != null ? m.get(0) : null;
	}
}
