package com.opensajux.entity;

import java.io.Serializable;
import java.util.Date;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.Text;
import com.opensajux.common.SocialSource;

@PersistenceCapable
public class MyBlog implements Serializable {
	private static final long serialVersionUID = -7623913887733722151L;

	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Key key;

	@Persistent
	private String id;

	@Persistent
	private Text name;

	@Persistent
	private SocialSource source;

	@Persistent
	private Date publishDate;

	@Persistent
	private Date updatedDate;

	@Persistent
	private Text url;

	/**
	 * @return the key
	 */
	public Key getKey() {
		return key;
	}

	/**
	 * @param key
	 *            the key to set
	 */
	public void setKey(Key key) {
		this.key = key;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

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

	/**
	 * @return the updatedDate
	 */
	public Date getUpdatedDate() {
		return updatedDate;
	}

	/**
	 * @param updatedDate
	 *            the updatedDate to set
	 */
	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
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
