package com.opensajux.entity;

import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

@PersistenceCapable
@Inheritance(customStrategy = "complete-table")
public class MenuItem extends BaseEntity {
	private static final long serialVersionUID = -4543555094784373996L;

	@Persistent
	private String title;

	@Persistent
	private String url;

	@Persistent
	private int ordering;

	@Persistent
	private boolean isPublished;

	@Persistent
	private Menu menu;

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTitle() {
		return title;
	}

	public void setUrl(String url) {
		this.url = url;
	}

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

	/**
	 * @param menu
	 *            the menu to set
	 */
	public void setMenu(Menu menu) {
		this.menu = menu;
	}

	/**
	 * @return the menu
	 */
	public Menu getMenu() {
		return menu;
	}

	/**
	 * @param published
	 *            the published to set
	 */
	public void setPublished(boolean published) {
		this.isPublished = published;
	}

	/**
	 * @return the published
	 */
	public boolean isPublished() {
		return isPublished;
	}

}
