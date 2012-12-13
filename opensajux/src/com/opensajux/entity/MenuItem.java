package com.opensajux.entity;

import java.io.Serializable;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;

@PersistenceCapable
public class MenuItem implements Serializable {
	private static final long serialVersionUID = -4543555094784373996L;

	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	protected Key key;

	@Persistent
	private String title;

	@Persistent
	private String url;

	@Persistent
	private int ordering;

	@Persistent
	private boolean published;

	@Persistent
	private Key menuKey;

	public Key getKey() {
		return key;
	}

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
	 * @param menuKey
	 *            the menuKey to set
	 */
	public void setMenuKey(Key menuKey) {
		this.menuKey = menuKey;
	}

	/**
	 * @return the menuKey
	 */
	public Key getMenuKey() {
		return menuKey;
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
	public boolean isPublished() {
		return published;
	}

}
