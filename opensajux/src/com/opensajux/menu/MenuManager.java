package com.opensajux.menu;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.faces.validator.ValidatorException;
import javax.inject.Named;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.opensajux.dao.PMF;
import com.opensajux.entity.Menu;
import com.opensajux.entity.MenuItem;

@Named
@SessionScoped
public class MenuManager implements Serializable {
	private static final long serialVersionUID = -6923886655285428390L;

	private LazyDataModel<Menu> menuModel;
	private Menu[] selectedMenus;
	private List<Menu> filteredMenus;
	private String menuName;
	private Menu menu;

	@SuppressWarnings("unchecked")
	public List<MenuItem> getMainMenus() {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Query query = pm.newQuery("select from " + Menu.class.getName() + " where name == n && published == true");

		query.declareParameters("String n");
		List<Menu> m = (List<Menu>) query.execute("menu_main");
		List<MenuItem> items = null;
		if (!m.isEmpty()) {
			items = (List<MenuItem>) pm
					.newQuery(
							"select from "
									+ MenuItem.class.getName()
									+ " where menuKey == key parameters com.google.appengine.api.datastore.Key key order by ordering ")
					.execute(m.get(0).getKey());
			items = new ArrayList<MenuItem>(items);
		}
		pm.close();
		return items;
	}

	/**
	 * @return the menuModel
	 */
	public LazyDataModel<Menu> getMenuModel() {
		if (menuModel == null) {
			menuModel = new LazyDataModel<Menu>() {
				private static final long serialVersionUID = -516437745115611946L;

				@Override
				public int getRowCount() {
					PersistenceManager pm = PMF.get().getPersistenceManager();
					Long count = (Long) pm.newQuery("select count(key) from " + Menu.class.getName()).execute();
					pm.close();
					return count.intValue();
				}

				@Override
				public List<Menu> load(int first, int pageSize, String sortField, SortOrder sortOrder,
						Map<String, String> filters) {
					PersistenceManager pm = PMF.get().getPersistenceManager();
					Query q = pm.newQuery("select from " + Menu.class.getName());
					q.setRange(first, pageSize);
					List<Menu> list = new ArrayList<>((List<Menu>) q.execute());
					for (Menu m : list) {
						Long count = (Long) pm
								.newQuery(
										"select count(key) from "
												+ MenuItem.class.getName()
												+ " where menuKey == menu_key parameters com.google.appengine.api.datastore.Key menu_key")
								.execute(m.getKey());
						m.setItemCount(count.intValue());
					}

					pm.close();
					return list;
				}

				@Override
				public void setRowIndex(int rowIndex) {
					if (rowIndex >= 0)
						super.setRowIndex(rowIndex);
				}
			};
		}
		return menuModel;
	}

	public List<Menu> getFilteredMenus() {
		return filteredMenus;
	}

	public void setFilteredMenus(List<Menu> filteredMenus) {
		this.filteredMenus = filteredMenus;
	}

	public Menu[] getSelectedMenus() {
		return selectedMenus;
	}

	public void setSelectedMenus(Menu[] menus) {
		this.selectedMenus = menus;
	}

	@SuppressWarnings("unchecked")
	public void removeMenu() {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		for (Menu m : selectedMenus) {
			List<Menu> t = (List<Menu>) pm.newQuery(
					"select from " + Menu.class.getName() + " where name == id parameters String id").execute(
					m.getName());
			if (!t.isEmpty())
				pm.deletePersistent(t.get(0));
		}
		pm.close();
	}

	public Menu getMenu() {
		if (menu == null)
			menu = new Menu();
		return menu;
	}

	@SuppressWarnings("unchecked")
	public void validateMenuName(FacesContext context, UIComponent component, Object object) throws ValidatorException {
		if ((context == null) || (component == null)) {
			throw new NullPointerException();
		}
		if (menu != null && menu.getKey() != null)
			return;
		PersistenceManager pm = PMF.get().getPersistenceManager();
		List<Menu> t = (List<Menu>) pm.newQuery(
				"select from " + Menu.class.getName() + " where name == id parameters String id").execute(
				object.toString());
		try {
			if (!t.isEmpty()) {
				throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Menu Name already exists",
						"Menu Name already exists"));
			}
		} finally {
			pm.close();
		}
	}

	public String addMenu() {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		menu.setPublished(true);
		pm.makePersistent(menu);
		pm.close();
		menu = null;
		return "/admin/menuManager.jsf";
	}

	public String saveMenu() {
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
		List<Menu> m = (List<Menu>) pm.newQuery(
				"select from " + Menu.class.getName() + " where name == n parameters String n").execute(menuName);
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
