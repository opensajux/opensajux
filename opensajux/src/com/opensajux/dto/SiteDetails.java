package com.opensajux.dto;

import java.io.Serializable;

import com.google.appengine.api.datastore.Key;

public class SiteDetails implements Serializable {
	private static final long serialVersionUID = -8213497624092274696L;

	protected Key key;

	private String aboutMe;

	private String title;

	private String subTitle;

	private String googleUserId;

	private String googleApiKey;

	private String googleAnalyticsId;

	private String twitterUsername;

	private String twitterConsumerKey;

	private String twitterConsumerSecret;

	private String twitterAccessToken;

	private String twitterAccessTokenSecret;

	private String facebookUsername;

	private String facebookAppId;

	private String facebookAppSecret;

	private String facebookAccessToken;

	private String linkedinUsername;

	private String linkedinApiKey;

	private String linkedinSecretKey;

	private String linkedinUserToken;

	private String linkedinUserSecret;

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
	 * @return the googleUserId
	 */
	public String getGoogleUserId() {
		return googleUserId;
	}

	/**
	 * @param googleUserId
	 *            the googleUserId to set
	 */
	public void setGoogleUserId(String googleUserId) {
		this.googleUserId = googleUserId;
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
	 * @return the googleAnalyticsId
	 */
	public String getGoogleAnalyticsId() {
		return googleAnalyticsId;
	}

	/**
	 * @param googleAnalyticsId
	 *            the googleAnalyticsId to set
	 */
	public void setGoogleAnalyticsId(String googleAnalyticsId) {
		this.googleAnalyticsId = googleAnalyticsId;
	}

	/**
	 * @return the twitterUsername
	 */
	public String getTwitterUsername() {
		return twitterUsername;
	}

	/**
	 * @param twitterUsername
	 *            the twitterUsername to set
	 */
	public void setTwitterUsername(String twitterUsername) {
		this.twitterUsername = twitterUsername;
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
	 * @return the facebookUsername
	 */
	public String getFacebookUsername() {
		return facebookUsername;
	}

	/**
	 * @param facebookUsername
	 *            the facebookUsername to set
	 */
	public void setFacebookUsername(String facebookUsername) {
		this.facebookUsername = facebookUsername;
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

	/**
	 * @return the linkedinUsername
	 */
	public String getLinkedinUsername() {
		return linkedinUsername;
	}

	/**
	 * @param linkedinUsername
	 *            the linkedinUsername to set
	 */
	public void setLinkedinUsername(String linkedinUsername) {
		this.linkedinUsername = linkedinUsername;
	}

	/**
	 * @return the linkedinApiKey
	 */
	public String getLinkedinApiKey() {
		return linkedinApiKey;
	}

	/**
	 * @param linkedinApiKey
	 *            the linkedinApiKey to set
	 */
	public void setLinkedinApiKey(String linkedinApiKey) {
		this.linkedinApiKey = linkedinApiKey;
	}

	/**
	 * @return the linkedinSecretKey
	 */
	public String getLinkedinSecretKey() {
		return linkedinSecretKey;
	}

	/**
	 * @param linkedinSecretKey
	 *            the linkedinSecretKey to set
	 */
	public void setLinkedinSecretKey(String linkedinSecretKey) {
		this.linkedinSecretKey = linkedinSecretKey;
	}

	/**
	 * @return the linkedinUserToken
	 */
	public String getLinkedinUserToken() {
		return linkedinUserToken;
	}

	/**
	 * @param linkedinUserToken
	 *            the linkedinUserToken to set
	 */
	public void setLinkedinUserToken(String linkedinUserToken) {
		this.linkedinUserToken = linkedinUserToken;
	}

	/**
	 * @return the linkedinUserSecret
	 */
	public String getLinkedinUserSecret() {
		return linkedinUserSecret;
	}

	/**
	 * @param linkedinUserSecret
	 *            the linkedinUserSecret to set
	 */
	public void setLinkedinUserSecret(String linkedinUserSecret) {
		this.linkedinUserSecret = linkedinUserSecret;
	}

}
