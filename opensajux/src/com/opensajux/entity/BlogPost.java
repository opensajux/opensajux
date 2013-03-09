package com.opensajux.entity;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.Text;

@PersistenceCapable
public class BlogPost extends Article {
	private static final long serialVersionUID = -954263210399934800L;

	@Persistent
	private Text sourceUrl;

	@Persistent
	private Key blogKey;

	/**
	 * @return the sourceUrl
	 */
	public Text getSourceUrl() {
		return sourceUrl;
	}

	/**
	 * @param sourceUrl
	 *            the sourceUrl to set
	 */
	public void setSourceUrl(Text sourceUrl) {
		this.sourceUrl = sourceUrl;
	}

	/**
	 * @return the blogKey
	 */
	public Key getBlogKey() {
		return blogKey;
	}

	/**
	 * @param blogKey
	 *            the blogKey to set
	 */
	public void setBlogKey(Key blogKey) {
		this.blogKey = blogKey;
	}

}
