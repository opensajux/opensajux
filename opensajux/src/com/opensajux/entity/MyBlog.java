package com.opensajux.entity;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

import com.google.appengine.api.datastore.Text;

@PersistenceCapable
public class MyBlog extends Article {
	private static final long serialVersionUID = -7623913887733722151L;

	@Persistent
	private Text name;

	@Persistent
	private Text url;

	/**
	 * @return the name
	 */
	public Text getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(Text name) {
		this.name = name;
	}

	/**
	 * @return the url
	 */
	public Text getUrl() {
		return url;
	}

	/**
	 * @param url
	 *            the url to set
	 */
	public void setUrl(Text url) {
		this.url = url;
	}

}
