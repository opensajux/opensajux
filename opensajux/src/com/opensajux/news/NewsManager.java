package com.opensajux.news;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.faces.event.ValueChangeEvent;
import javax.inject.Named;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.opensajux.dao.PMF;
import com.opensajux.entity.News;

@Named
public class NewsManager implements Serializable {
	private static final long serialVersionUID = -6923886655285428390L;

	private PersistenceManagerFactory pmf;

	private LazyDataModel<News> newsModel;
	private String menuName;
	private News menu;

	public void setPersistenceManagerFactory(PersistenceManagerFactory pmf) {
		// this.pmf = pmf;
	}

	/**
	 * @return the newsModel
	 */
	public LazyDataModel<News> getNewsModel() {
		if (newsModel == null) {
			newsModel = new LazyDataModel<News>() {
				private static final long serialVersionUID = -516437745115611946L;

				@Override
				public int getRowCount() {
					PersistenceManager pm = PMF.get().getPersistenceManager();
					int count = (Integer) pm.newQuery("select count(key) from " + News.class.getName()).execute();
					pm.close();
					return count;
				}

				@Override
				public List<News> load(int first, int pageSize, String sortField, SortOrder sortOrder,
						Map<String, String> filters) {
					PersistenceManager pm = PMF.get().getPersistenceManager();
					Query q = pm.newQuery("select from " + News.class.getName());
					q.setRange(first, pageSize);
					List<News> list = new ArrayList<News>((List<News>) q.execute());
					pm.close();
					return list;
				}
			};
		}
		return newsModel;
	}

	public void removeNews() {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		pm.close();
	}

	public News getNews() {
		if (menu == null)
			menu = new News();
		return menu;
	}

	public String addNews() {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		menu.setPublished(true);
		menu.setPublishDate(new Date());
		pm.makePersistent(menu);
		pm.close();
		menu = null;
		return "/admin/menuManager.jsf";
	}

	public String saveNews() {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		pm.makePersistent(menu);
		pm.close();
		menu = null;
		return "/admin/menuManager.jsf";
	}

	/**
	 * @param menuName
	 *            the menuName to set
	 */
	@SuppressWarnings("unchecked")
	public void setMenuName(String menuName) {
		this.menuName = menuName;
		PersistenceManager pm = PMF.get().getPersistenceManager();
		List<News> m = (List<News>) pm.newQuery(
				"select from " + News.class.getName() + " where name == n parameters String n").execute(menuName);
		if (m.size() > 0)
			menu = m.get(0);
		pm.close();
	}

	/**
	 * @return the menuName
	 */
	public String getMenuName() {
		return menuName;
	}

	public void changePublishedState(ValueChangeEvent event) {
		System.out.println(">>>>>>>>>>>>>>>");
	}
}
