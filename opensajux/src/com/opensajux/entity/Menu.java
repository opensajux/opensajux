package com.opensajux.entity;

import java.util.List;

import javax.jdo.annotations.Element;
import javax.jdo.annotations.NotPersistent;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

@PersistenceCapable
public class Menu extends BaseEntity {
	private static final long serialVersionUID = -1666759814261626215L;

	@Persistent
	private String name;

	@Persistent
	private String title;

	@Persistent
	private String url;

	@Persistent
	private int ordering;

	@Persistent
	private boolean published;

	@Persistent(mappedBy = "menu")
	@Element(dependent = "true")
	private List<MenuItem> menuItems;

	@NotPersistent
	private int itemCount;

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param title
	 *            the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param url
	 *            the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @param ordering
	 *            the ordering to set
	 */
	public void setOrdering(int ordering) {
		this.ordering = ordering;
	}

	/**
	 * @return the ordering
	 */
	public int getOrdering() {
		return ordering;
	}

	public int getItemCount() {
		return itemCount;
	}

	/**
	 * @param itemCount
	 *            the itemCount to set
	 */
	public void setItemCount(int itemCount) {
		this.itemCount = itemCount;
	}

	/**
	 * @param published
	 *            the published to set
	 */
	public void setPublished(boolean published) {
		this.published = published;
	}

	/**
	 * @return the published
	 */
	public boolean getPublished() {
		return published;
	}

	/**
	 * @return the menuItems
	 */
	public List<MenuItem> getMenuItems() {
		return menuItems;
	}

	/**
	 * @param menuItems
	 *            the menuItems to set
	 */
	public void setMenuItems(List<MenuItem> menuItems) {
		this.menuItems = menuItems;
	}
}
