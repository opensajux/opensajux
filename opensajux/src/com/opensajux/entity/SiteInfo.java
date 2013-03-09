package com.opensajux.entity;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

import com.google.appengine.api.datastore.Text;

@PersistenceCapable
public class SiteInfo extends BaseEntity {
	private static final long serialVersionUID = -8213497624092274696L;

	@Persistent
	private Text aboutMe;

	@Persistent
	private String title;

	@Persistent
	private String subTitle;

	@Persistent
	private String googleUserId;

	@Persistent
	private String googleApiKey;

	@Persistent
	private String googleAnalyticsId;

	@Persistent
	private String twitterUsername;

	@Persistent
	private String twitterConsumerKey;

	@Persistent
	private String twitterConsumerSecret;

	@Persistent
	private String twitterAccessToken;

	@Persistent
	private String twitterAccessTokenSecret;

	@Persistent
	private String facebookUsername;

	@Persistent
	private String facebookAppId;

	@Persistent
	private String facebookAppSecret;

	@Persistent
	private String facebookAccessToken;

	@Persistent
	private String linkedinUsername;

	@Persistent
	private String linkedinApiKey;

	@Persistent
	private String linkedinSecretKey;

	@Persistent
	private String linkedinUserToken;

	@Persistent
	private String linkedinUserSecret;

	public Text getAboutMe() {
		return aboutMe;
	}

	public void setAboutMe(Text aboutMe) {
		this.aboutMe = aboutMe;
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
