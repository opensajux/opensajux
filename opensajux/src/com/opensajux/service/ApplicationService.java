package com.opensajux.service;

import java.io.Serializable;

import com.opensajux.entity.SiteInfo;

/**
 * @author Sheikh Mohammad Sajid
 * 
 */
public interface ApplicationService extends Serializable {

	public SiteInfo getSiteInfo();

	public void saveSiteInfo(SiteInfo siteInfo);
}
