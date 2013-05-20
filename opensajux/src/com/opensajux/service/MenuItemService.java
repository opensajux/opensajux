package com.opensajux.service;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import com.opensajux.common.PaginationParameters;
import com.opensajux.entity.MenuItem;

/**
 * @author Sheikh Mohammad Sajid
 * 
 */
public interface MenuItemService extends Serializable {

	public void saveMenuItem(MenuItem menuItem, String menuName);

	public void removeMenuItem(String id);

	public void removeMenuItem(Collection<MenuItem> coll);

	public void removeMenuItem(MenuItem[] selectedMenuItems);

	public List<MenuItem> getMenuItems(PaginationParameters params, String menuName);

	public Long getCount();

	public MenuItem getById(String id);
}
