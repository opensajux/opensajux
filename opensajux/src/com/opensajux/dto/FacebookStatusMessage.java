package com.opensajux.dto;

import static com.restfb.util.DateUtils.toDateFromLongFormat;
import static java.util.Collections.unmodifiableList;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.restfb.Facebook;
import com.restfb.types.Comment;
import com.restfb.types.NamedFacebookType;

public class FacebookStatusMessage {
	@Facebook("status_id")
	private String id;

	@Facebook
	private NamedFacebookType from;

	@Facebook
	private String message;

	@Facebook
	private String type;

	@Facebook("time")
	private String updatedTime;

	@Facebook
	private List<NamedFacebookType> likes = new ArrayList<NamedFacebookType>();

	/**
	 * Hack so JSON mapping won't fail when FB returns inconsistent JSON when
	 * there are 0 likes.
	 */
	@Facebook("likes")
	private EmptyLikes emptyLikes;

	@Facebook
	private List<Comment> comments = new ArrayList<Comment>();

	/**
	 * Hack so JSON mapping won't fail when FB returns inconsistent JSON when
	 * there are 0 comments.
	 */
	@Facebook("comments")
	private EmptyComments emptyComments;

	/**
	 * Sometimes Facebook will return <tt>"likes":{"count":0}</tt> instead of
	 * the connection-formatted likes object that's documented - this class
	 * handles that so JSON mapping won't fail.
	 * 
	 * @author <a href="http://restfb.com">Mark Allen</a>
	 * @since 1.6.8
	 */
	private static class EmptyLikes implements Serializable {
		@Facebook
		private Long count;

		private static final long serialVersionUID = 1L;
	}

	/**
	 * Sometimes Facebook will return <tt>"comments":{"count":0}</tt> instead of
	 * the connection-formatted comments object that's documented - this class
	 * handles that so JSON mapping won't fail.
	 * 
	 * @author <a href="http://restfb.com">Mark Allen</a>
	 * @since 1.6.8
	 */
	private static class EmptyComments implements Serializable {
		@Facebook
		private Long count;

		private static final long serialVersionUID = 1L;
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
	 * The user who posted the message.
	 * 
	 * @return The user who posted the message.
	 */
	public NamedFacebookType getFrom() {
		return from;
	}

	/**
	 * The status message content.
	 * 
	 * @return The status message content.
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * The object type which is set to status.
	 * 
	 * @return The object type which is set to status.
	 */
	public String getType() {
		return type;
	}

	/**
	 * The time the message was published.
	 * 
	 * @return The time the message was published.
	 */
	public Date getUpdatedTime() {
		return toDateFromLongFormat(updatedTime);
	}

	/**
	 * The users that have liked this message.
	 * 
	 * @return The users that have liked this message.
	 */
	public List<NamedFacebookType> getLikes() {
		return unmodifiableList(likes);
	}

	/**
	 * All of the comments on this message.
	 * 
	 * @return All of the comments on this message.
	 */
	public List<Comment> getComments() {
		return unmodifiableList(comments);
	}
}
