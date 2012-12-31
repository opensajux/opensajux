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
public class BlogPost implements Serializable {
	private static final long serialVersionUID = -954263210399934800L;

	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Key key;

	@Persistent
	private String id;

	@Persistent
	private Text title;

	@Persistent
	private Text content;

	@Persistent
	private SocialSource source;

	@Persistent
	private Date publishDate;

	@Persistent
	private Date updatedDate;

	@Persistent
	private Text sourceUrl;

	@Persistent
	private Key blogKey;

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
	 * @return the title
	 */
	public Text getTitle() {
		return title;
	}

	/**
	 * @param title
	 *            the title to set
	 */
	public void setTitle(Text title) {
		this.title = title;
	}

	/**
	 * @return the content
	 */
	public Text getContent() {
		return content;
	}

	/**
	 * @param content
	 *            the content to set
	 */
	public void setContent(Text content) {
		this.content = content;
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
