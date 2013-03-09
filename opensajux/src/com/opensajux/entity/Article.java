package com.opensajux.entity;

import java.util.Date;

import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.InheritanceStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

import com.google.appengine.api.datastore.Text;
import com.opensajux.common.SocialSource;

/**
 * <code>Article</code> is the base class for all web content like
 * {@link BlogPost}, {@link TalkStream}, {@link News}
 * 
 * @author Sheikh Mohammad Sajid
 * @since 0.5.0
 */
@PersistenceCapable
@Inheritance(strategy = InheritanceStrategy.SUBCLASS_TABLE)
public abstract class Article extends BaseEntity {
	private static final long serialVersionUID = -4013553911044429316L;

	@Persistent
	private String id;

	@Persistent
	private Text title;

	@Persistent
	private Text content;

	@Persistent
	private SocialSource source;

	@Persistent
	private Date createdDate;

	@Persistent
	private Date updatedDate;

	@Persistent
	private boolean isPublished;

	@Persistent
	private Date publishDate;

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
	 * @return the createdDate
	 */
	public Date getCreatedDate() {
		return createdDate;
	}

	/**
	 * @param createdDate
	 *            the createdDate to set
	 */
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
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
	 * @return the isPublished
	 */
	public boolean isPublished() {
		return isPublished;
	}

	/**
	 * @param isPublished
	 *            the isPublished to set
	 */
	public void setPublished(boolean isPublished) {
		this.isPublished = isPublished;
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

}
