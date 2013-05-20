package com.opensajux.service;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import com.opensajux.common.PaginationParameters;
import com.opensajux.entity.Menu;

/**
 * @author Sheikh Mohammad Sajid
 * 
 */
public interface MenuService extends Serializable {

	public void saveMenu(Menu menu);

	public void removeMenu(String id);

	public void removeMenu(Collection<Menu> coll);

	public void removeMenu(Menu[] selectedMenus);

	public List<Menu> getMenus(PaginationParameters params);

	public Long getCount();

	public Menu getById(String id);

	public Menu getMenuByName(String name);
}
