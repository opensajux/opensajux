package com.opensajux.service;

import java.io.Serializable;
import java.util.List;

import com.google.appengine.api.datastore.Key;
import com.opensajux.common.PaginationParameters;
import com.opensajux.entity.SiteLink;

/**
 * @author Sheikh Mohammad Sajid
 * 
 */
public interface SiteLinkService extends Serializable {
	public void saveSiteLink(String title, String url);

	public void removeSiteLink(Key key);

	public List<SiteLink> getLinks(PaginationParameters params);

	public Long getCount();

	public SiteLink getById(String id);
}
