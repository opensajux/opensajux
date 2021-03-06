/**
 * 
 */
package com.opensajux.service;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;

import com.opensajux.entity.SiteInfo;

/**
 * @author Sheikh Mohammad Sajid
 * 
 */
@ApplicationScoped
public class ApplicationService implements Serializable {
	private static final long serialVersionUID = -3854704220446440968L;

	@Inject
	private transient PersistenceManagerFactory pmf;

	@SuppressWarnings("unchecked")
	public SiteInfo getSiteInfo() {
		PersistenceManager pm = pmf.getPersistenceManagerProxy();
		Query query = pm.newQuery("select from " + SiteInfo.class.getName());
		List<SiteInfo> list = (List<SiteInfo>) query.execute();
		return list != null && list.size() > 0 ? list.get(0) : null;
	}

	public void saveSiteInfo(SiteInfo siteInfo) {
		PersistenceManager pm = pmf.getPersistenceManagerProxy();
		pm.makePersistent(siteInfo);
	}
}
