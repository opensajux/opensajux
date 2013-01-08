package com.opensajux.dto;

import java.io.Serializable;

import com.google.appengine.api.datastore.Key;

public class SiteDetails implements Serializable {
	private static final long serialVersionUID = -8213497624092274696L;

	protected Key key;

	private String aboutMe;

	private String title;

	private String subTitle;

	private String googleApiKey;

	private String twitterConsumerKey;

	private String twitterConsumerSecret;

	private String twitterAccessToken;

	private String twitterAccessTokenSecret;

	private String facebookAppId;

	private String facebookAppSecret;

	private String facebookAccessToken;

	public String getAboutMe() {
		return aboutMe;
	}

	public void setAboutMe(String aboutMe) {
		this.aboutMe = aboutMe;
	}

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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the subTitle
	 */
	public String getSubTitle() {
		return subTitle;
	}

	/**
	 * @param subTitle
	 *            the subTitle to set
	 */
	public void setSubTitle(String subTitle) {
		this.subTitle = subTitle;
	}

	/**
	 * @return the googleApiKey
	 */
	public String getGoogleApiKey() {
		return googleApiKey;
	}

	/**
	 * @param googleApiKey
	 *            the googleApiKey to set
	 */
	public void setGoogleApiKey(String googleApiKey) {
		this.googleApiKey = googleApiKey;
	}

	/**
	 * @return the twitterConsumerKey
	 */
	public String getTwitterConsumerKey() {
		return twitterConsumerKey;
	}

	/**
	 * @param twitterConsumerKey
	 *            the twitterConsumerKey to set
	 */
	public void setTwitterConsumerKey(String twitterConsumerKey) {
		this.twitterConsumerKey = twitterConsumerKey;
	}

	/**
	 * @return the twitterConsumerSecret
	 */
	public String getTwitterConsumerSecret() {
		return twitterConsumerSecret;
	}

	/**
	 * @param twitterConsumerSecret
	 *            the twitterConsumerSecret to set
	 */
	public void setTwitterConsumerSecret(String twitterConsumerSecret) {
		this.twitterConsumerSecret = twitterConsumerSecret;
	}

	/**
	 * @return the twitterAccessToken
	 */
	public String getTwitterAccessToken() {
		return twitterAccessToken;
	}

	/**
	 * @param twitterAccessToken
	 *            the twitterAccessToken to set
	 */
	public void setTwitterAccessToken(String twitterAccessToken) {
		this.twitterAccessToken = twitterAccessToken;
	}

	/**
	 * @return the twitterAccessTokenSecret
	 */
	public String getTwitterAccessTokenSecret() {
		return twitterAccessTokenSecret;
	}

	/**
	 * @param twitterAccessTokenSecret
	 *            the twitterAccessTokenSecret to set
	 */
	public void setTwitterAccessTokenSecret(String twitterAccessTokenSecret) {
		this.twitterAccessTokenSecret = twitterAccessTokenSecret;
	}

	/**
	 * @return the facebookAppId
	 */
	public String getFacebookAppId() {
		return facebookAppId;
	}

	/**
	 * @param facebookAppId
	 *            the facebookAppId to set
	 */
	public void setFacebookAppId(String facebookAppId) {
		this.facebookAppId = facebookAppId;
	}

	/**
	 * @return the facebookAppSecret
	 */
	public String getFacebookAppSecret() {
		return facebookAppSecret;
	}

	/**
	 * @param facebookAppSecret
	 *            the facebookAppSecret to set
	 */
	public void setFacebookAppSecret(String facebookAppSecret) {
		this.facebookAppSecret = facebookAppSecret;
	}

	/**
	 * @return the facebookAccessToken
	 */
	public String getFacebookAccessToken() {
		return facebookAccessToken;
	}

	/**
	 * @param facebookAccessToken
	 *            the facebookAccessToken to set
	 */
	public void setFacebookAccessToken(String facebookAccessToken) {
		this.facebookAccessToken = facebookAccessToken;
	}

}