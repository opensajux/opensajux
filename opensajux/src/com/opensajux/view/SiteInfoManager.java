package com.opensajux.view;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;

import com.google.appengine.api.datastore.Text;
import com.opensajux.common.Chosen;
import com.opensajux.dto.SiteDetails;
import com.opensajux.entity.SiteInfo;
import com.opensajux.service.ApplicationService;

@Named
@ApplicationScoped
public class SiteInfoManager {
	@Inject
	private ApplicationService applicationService;

	private SiteDetails siteDetails;

	@Produces
	@Chosen
	public SiteDetails getSiteDetails() {
		if (siteDetails == null) {
			SiteInfo siteInfo = applicationService.getSiteInfo();
			if (siteInfo != null) {
				siteDetails = new SiteDetails();
				siteDetails.setAboutMe(siteInfo.getAboutMe().getValue());
				siteDetails.setTitle(siteInfo.getTitle());
				siteDetails.setSubTitle(siteInfo.getSubTitle());
				siteDetails.setGoogleUserId(siteInfo.getGoogleUserId());
				siteDetails.setGoogleApiKey(siteInfo.getGoogleApiKey());
				siteDetails.setGoogleAnalyticsId(siteInfo.getGoogleAnalyticsId());
				siteDetails.setTwitterUsername(siteInfo.getTwitterUsername());
				siteDetails.setTwitterAccessToken(siteInfo.getTwitterAccessToken());
				siteDetails.setTwitterAccessTokenSecret(siteInfo.getTwitterAccessTokenSecret());
				siteDetails.setTwitterConsumerKey(siteInfo.getTwitterConsumerKey());
				siteDetails.setTwitterConsumerSecret(siteInfo.getTwitterConsumerSecret());
				siteDetails.setFacebookUsername(siteInfo.getFacebookUsername());
				siteDetails.setFacebookAppId(siteInfo.getFacebookAppId());
				siteDetails.setFacebookAppSecret(siteInfo.getFacebookAppSecret());
				siteDetails.setFacebookAccessToken(siteInfo.getFacebookAccessToken());
				siteDetails.setLinkedinUsername(siteInfo.getLinkedinUsername());
				siteDetails.setLinkedinApiKey(siteInfo.getLinkedinApiKey());
				siteDetails.setLinkedinSecretKey(siteInfo.getLinkedinSecretKey());
				siteDetails.setLinkedinUserSecret(siteInfo.getLinkedinUserSecret());
				siteDetails.setLinkedinUserToken(siteInfo.getLinkedinUserToken());
			}
		}
		return siteDetails;
	}

	public String saveSiteInfo() {
		SiteInfo siteInfo = applicationService.getSiteInfo();
		if (siteInfo == null)
			siteInfo = new SiteInfo();
		siteInfo.setAboutMe(new Text(siteDetails.getAboutMe()));
		siteInfo.setTitle(siteDetails.getTitle());
		siteInfo.setSubTitle(siteDetails.getSubTitle());
		siteInfo.setGoogleUserId(siteDetails.getGoogleUserId());
		siteInfo.setGoogleApiKey(siteDetails.getGoogleApiKey());
		siteInfo.setGoogleAnalyticsId(siteDetails.getGoogleAnalyticsId());
		siteInfo.setTwitterUsername(siteDetails.getTwitterUsername());
		siteInfo.setTwitterAccessToken(siteDetails.getTwitterAccessToken());
		siteInfo.setTwitterAccessTokenSecret(siteDetails.getTwitterAccessTokenSecret());
		siteInfo.setTwitterConsumerKey(siteDetails.getTwitterConsumerKey());
		siteInfo.setTwitterConsumerSecret(siteDetails.getTwitterConsumerSecret());
		siteInfo.setFacebookUsername(siteDetails.getFacebookUsername());
		siteInfo.setFacebookAppId(siteDetails.getFacebookAppId());
		siteInfo.setFacebookAppSecret(siteDetails.getFacebookAppSecret());
		siteInfo.setFacebookAccessToken(siteDetails.getFacebookAccessToken());
		siteInfo.setLinkedinUsername(siteDetails.getLinkedinUsername());
		siteInfo.setLinkedinApiKey(siteDetails.getLinkedinApiKey());
		siteInfo.setLinkedinSecretKey(siteDetails.getLinkedinSecretKey());
		siteInfo.setLinkedinUserSecret(siteDetails.getLinkedinUserSecret());
		siteInfo.setLinkedinUserToken(siteDetails.getLinkedinUserToken());
		applicationService.saveSiteInfo(siteInfo);
		return "/admin/index.jsf";
	}
}
