package com.opensajux.view;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;

import com.opensajux.entity.SiteInfo;

@Named
@ApplicationScoped
public class SiteInfoManager {
	@Inject
	private transient PersistenceManagerFactory pmf;

	private SiteInfo siteInfo;

	@SuppressWarnings("unchecked")
	public SiteInfo getSiteInfo() {
		if (siteInfo == null) {
			PersistenceManager pm = pmf.getPersistenceManager();
			Query query = pm.newQuery("select from " + SiteInfo.class.getName());
			List<SiteInfo> list = (List<SiteInfo>) query.execute();
			if (list.size() > 0) {
				siteInfo = list.get(0);
				pm.detachCopy(siteInfo);
			} else {
				siteInfo = new SiteInfo();
			}
			pm.close();

		}
		return siteInfo;
	}

	public String saveSiteInfo() {
		PersistenceManager pm = pmf.getPersistenceManager();
		pm.makePersistent(siteInfo);
		pm.close();
		return "/admin/index.jsf";
	}
}
