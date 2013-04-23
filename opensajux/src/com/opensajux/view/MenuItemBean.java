package com.opensajux.view;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.opensajux.common.PaginationParameters;
import com.opensajux.entity.Menu;
import com.opensajux.entity.MenuItem;
import com.opensajux.service.MenuItemService;
import com.opensajux.service.MenuService;

@Named
@SessionScoped
public class MenuItemBean implements Serializable {

	private static final long serialVersionUID = -4565726770439961584L;

	@Inject
	private MenuItemService menuItemService;
	@Inject
	private MenuService menuService;
	private Menu menu;
	private String menuName;
	private MenuItem menuItem;
	private String menuItemTitle;

	private MenuItem[] selectedMenuItems;
	private LazyDataModel<MenuItem> menuItemModel;

	public MenuItemBean() {
	}

	public Menu getMenu() {
		if (menu == null)
			menu = new Menu();
		return menu;
	}

	public MenuItem getMenuItem() {
		if (menuItem == null)
			menuItem = new MenuItem();
		return menuItem;
	}

	public String getMenuName() {
		return this.menuName;
	}

	/**
	 * @param menuName
	 *            the menuName to set
	 */
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public LazyDataModel<MenuItem> getMenuItemModel() {
		if (menuItemModel == null) {
			menuItemModel = new LazyDataModel<MenuItem>() {
				private static final long serialVersionUID = -516437745115611946L;

				@Override
				public int getRowCount() {
					return menuItemService.getCount().intValue();
				}

				@Override
				public List<MenuItem> load(int first, int pageSize, String sortField, SortOrder sortOrder,
						Map<String, String> filters) {
					PaginationParameters param = new PaginationParameters();
					param.setFirst(first);
					param.setPageSize(pageSize);
					param.setSortField(sortField == null ? "ordering" : sortField);
					param.setSortOrder(sortOrder == null ? "desc" : sortOrder == SortOrder.ASCENDING ? "asc" : "desc");

					return menuItemService.getMenuItems(param, menuName);
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

	public void saveMenuItem() {
		menuItem.setPublished(true);
		menuItemService.saveMenuItem(menuItem, menuName);
		menuItem = null;
	}

	public void removeMenuItems() {
		for (MenuItem m : selectedMenuItems) {
			m.getMenu().getMenuItems().remove(m);
			menuService.saveMenu(m.getMenu());
		}
		menuItemService.removeMenuItem(selectedMenuItems);
	}

	public void add() {
		clearFields();
	}

	/**
	 * 
	 */
	private void clearFields() {
		menuItem = null;
	}

	/**
	 * @return the menuItemTitle
	 */
	public String getMenuItemTitle() {
		return menuItemTitle;
	}

	/**
	 * @param menuItemTitle
	 *            the menuItemTitle to set
	 */
	public void setMenuItemTitle(String menuItemTitle) {
		this.menuItemTitle = menuItemTitle;
	}

}
