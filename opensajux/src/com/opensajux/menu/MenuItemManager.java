package com.opensajux.menu;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;
import javax.inject.Named;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.opensajux.entity.Menu;
import com.opensajux.entity.MenuItem;

@Named
@SessionScoped
public class MenuItemManager implements Serializable {

	private static final long serialVersionUID = -4565726770439961584L;

	@Inject
	private transient PersistenceManagerFactory pmf;
	private Menu menu;
	private String menuName;
	private MenuItem menuItem;

	private MenuItem[] selectedMenuItems;
	private LazyDataModel<MenuItem> menuItemModel;

	public MenuItemManager() {
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
		if (menu != null && menu.getKey().getId() != 0)
			return;
		PersistenceManager pm = pmf.getPersistenceManager();
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

	public MenuItem getMenuItem() {
		if (menuItem == null)
			menuItem = new MenuItem();
		return menuItem;
	}

	public String getMenuName() {
		return this.menuName;
	}

	@SuppressWarnings("unchecked")
	public void setMenuName(String name) {
		this.menuName = name;
		PersistenceManager pm = pmf.getPersistenceManager();
		List<Menu> m = (List<Menu>) pm.newQuery(
				"select from " + Menu.class.getName() + " where name == n parameters String n").execute(menuName);
		if (m.size() > 0)
			menu = m.get(0);
		pm.close();
	}

	public LazyDataModel<MenuItem> getMenuItemModel() {
		if (menuItemModel == null) {
			menuItemModel = new LazyDataModel<MenuItem>() {
				private static final long serialVersionUID = -516437745115611946L;

				@SuppressWarnings("unchecked")
				@Override
				public int getRowCount() {
					PersistenceManager pm = pmf.getPersistenceManager();
					List<Menu> m = (List<Menu>) pm.newQuery(
							"select from " + Menu.class.getName() + " where name == n parameters String n").execute(
							menuName);
					Long count = (Long) pm
							.newQuery(
									"select count(key) from "
											+ MenuItem.class.getName()
											+ " where menuKey == menu_key parameters com.google.appengine.api.datastore.Key menu_key")
							.execute(m.get(0).getKey());
					pm.close();
					return count.intValue();
				}

				@Override
				public List<MenuItem> load(int first, int pageSize, String arg2, SortOrder arg3,
						Map<String, String> arg4) {
					PersistenceManager pm = pmf.getPersistenceManager();
					List<Menu> m = (List<Menu>) pm.newQuery(
							"select from " + Menu.class.getName() + " where name == n parameters String n").execute(
							menuName);
					Query q = pm.newQuery("select from " + MenuItem.class.getName() + " where menuKey == menu_key");
					q.setOrdering("ordering");
					q.declareParameters("com.google.appengine.api.datastore.Key menu_key");
					q.setRange(first, pageSize);
					List<MenuItem> list = new ArrayList<MenuItem>((List<MenuItem>) q.execute(m.get(0).getKey()));
					pm.close();
					return list;
				}
			};
		}
		return menuItemModel;
	}

	public MenuItem[] getSelectedMenuItems() {
		return selectedMenuItems;
	}

	public void setSelectedMenuItems(MenuItem[] menus) {
		this.selectedMenuItems = menus;
	}

	@SuppressWarnings("unchecked")
	public String saveMenuItem() {
		PersistenceManager pm = pmf.getPersistenceManager();
		List<Menu> m = (List<Menu>) pm.newQuery(
				"select from " + Menu.class.getName() + " where name == n parameters String n").execute(menuName);
		menuItem.setMenuKey(m.get(0).getKey());
		menuItem.setPublished(true);
		pm.makePersistent(menuItem);
		pm.close();
		menuItem = null;
		return "/admin/menuItemManager.jsf?menu_name=" + menuName;
	}

	@SuppressWarnings("unchecked")
	public void removeMenuItem() {
		PersistenceManager pm = pmf.getPersistenceManager();
		for (MenuItem m : selectedMenuItems) {
			List<MenuItem> t = (List<MenuItem>) pm.newQuery(
					"select from " + MenuItem.class.getName()
							+ " where key == id parameters com.google.appengine.api.datastore.Key id").execute(
					m.getKey());
			if (!t.isEmpty())
				pm.deletePersistent(t.get(0));
		}
		pm.close();
	}

	public String showAddScreen() {
		return "/admin/addMenuItem.jsf?menu_name=" + menuName;
	}
}
