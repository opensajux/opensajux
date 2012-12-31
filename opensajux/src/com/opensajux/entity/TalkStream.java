package com.opensajux.entity;

import java.io.Serializable;
import java.util.Date;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;
import com.opensajux.common.SocialSource;

@PersistenceCapable
public class TalkStream implements Serializable {
	private static final long serialVersionUID = -954263210399934800L;

	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Key key;

	@Persistent
	private String text;

	@Persistent
	private SocialSource source;

	@Persistent
	private Date publishDate;

	/**
	 * @param key
	 *            the key to set
	 */
	public void setKey(Key key) {
		this.key = key;
	}

	/**
	 * @return the key
	 */
	public Key getKey() {
		return key;
	}

	/**
	 * @return the text
	 */
	public String getText() {
		return text;
	}

	/**
	 * @param text
	 *            the text to set
	 */
	public void setText(String text) {
		this.text = text;
	}

	/**
	 * @return the source
	 */
	public SocialSource getSource() {
		return source;
	}

	/**
	 * @param source
	 *            the source to set
	 */
	public void setSource(SocialSource source) {
		this.source = source;
	}

	/**
	 * @return the publishDate
	 */
	public Date getPublishDate() {
		return publishDate;
	}

	/**
	 * @param publishDate
	 *            the publishDate to set
	 */
	public void setPublishDate(Date publishDate) {
		this.publishDate = publishDate;
	}

}
