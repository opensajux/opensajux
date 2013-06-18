package com.opensajux.view;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.RowEditEvent;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.opensajux.entity.Menu;
import com.opensajux.entity.MenuItem;
import com.opensajux.service.MenuItemService;
import com.opensajux.service.MenuService;
import com.opensajux.service.PaginationParameters;

@Named
@SessionScoped
public class MenuBean implements Serializable {
	private static final long serialVersionUID = -6923886655285428390L;

	@Inject
	private MenuService menuService;
	@Inject
	private MenuItemService menuItemService;
	private LazyDataModel<Menu> menuModel;
	private Menu[] selectedMenus;
	private List<Menu> filteredMenus;
	private String menuName;
	private Menu menu;
	private String menuTitle;

	public List<MenuItem> getMainMenus() {
		PaginationParameters param = new PaginationParameters();
		param.setFirst(0);
		param.setPageSize(200);
		param.setSortField("ordering");
		param.setSortOrder("asc");

		return menuItemService.getMenuItems(param, "menu_main");
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
					return menuService.getCount().intValue();
				}

				@Override
				public List<Menu> load(int first, int pageSize, String sortField, SortOrder sortOrder,
						Map<String, String> filters) {
					PaginationParameters param = new PaginationParameters();
					param.setFirst(first);
					param.setPageSize(pageSize);
					param.setSortField(sortField == null ? "name" : sortField);
					param.setSortOrder(sortOrder == null ? "desc" : sortOrder == SortOrder.ASCENDING ? "asc" : "desc");

					return menuService.getMenus(param);
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

	/**
	 * @return the selected
	 */
	public Menu getSelected() {
		return getMenu();
	}

	/**
	 * @param selected
	 *            the selected to set
	 */
	public void setSelected(Menu selected) {
		this.menu = selected;
		setMenuTitle(selected.getTitle());
	}

	public Menu[] getSelectedMenus() {
		return selectedMenus;
	}

	public void setSelectedMenus(Menu[] menus) {
		this.selectedMenus = menus;
	}

	public void removeMenus() {
		menuService.removeMenu(selectedMenus);
	}

	public Menu getMenu() {
		if (menu == null) {
			menu = new Menu();
			menu.setPublished(true);
		}
		return menu;
	}

	public void validateMenuName(FacesContext context, UIComponent component, Object object) throws ValidatorException {
		if ((context == null) || (component == null)) {
			throw new NullPointerException();
		}
		if (menu != null && menu.getKey() != null)
			return;
		Menu t = menuService.getMenuByName(object.toString());
		if (t != null) {
			throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Menu Name already exists",
					"Menu Name already exists"));
		}
	}

	public void saveMenu() {
		menuService.saveMenu(menu);
		menu = null;
	}

	/**
	 * @return the menuName
	 */
	public String getMenuName() {
		return menuName;
	}

	/**
	 * @return the menuTitle
	 */
	public String getMenuTitle() {
		return menuTitle;
	}

	/**
	 * @param menuTitle
	 *            the menuTitle to set
	 */
	public void setMenuTitle(String menuTitle) {
		this.menuTitle = menuTitle;
	}

	public void add() {
		clearFields();
	}

	/**
	 * 
	 */
	private void clearFields() {
		menuTitle = "";
		menu = null;
	}

	public void onRowEdit(RowEditEvent event) {
		Menu menu = (Menu) event.getObject();
		menuService.saveMenu(menu);
	}

	public void onCancel(RowEditEvent event) {
	}
}
