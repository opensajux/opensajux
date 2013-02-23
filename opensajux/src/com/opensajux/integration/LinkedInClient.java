package com.opensajux.integration;

import java.io.Serializable;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.springframework.social.linkedin.api.LinkedIn;
import org.springframework.social.linkedin.api.LinkedInProfileFull;
import org.springframework.social.linkedin.api.impl.LinkedInTemplate;

import com.opensajux.common.Chosen;
import com.opensajux.dto.SiteDetails;

@Singleton
public class LinkedInClient implements Serializable {
	private static final long serialVersionUID = 2401263486681696985L;

	private LinkedIn linkedin;

	@Inject
	public LinkedInClient(@Chosen SiteDetails siteDetails) throws Exception {
		linkedin = new LinkedInTemplate(siteDetails.getLinkedinApiKey(), siteDetails.getLinkedinSecretKey(),
				siteDetails.getLinkedinUserToken(), siteDetails.getLinkedinUserSecret());
	}

	public LinkedInProfileFull getMyProfile() {
		return linkedin.profileOperations().getUserProfileFull();
	}
}
