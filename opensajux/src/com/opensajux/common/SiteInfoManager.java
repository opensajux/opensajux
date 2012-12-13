package com.opensajux.common;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import com.opensajux.dao.PMF;
import com.opensajux.entity.SiteInfo;

@Named
@ApplicationScoped
public class SiteInfoManager {
	private SiteInfo siteInfo;

	public SiteInfo getSiteInfo() {
		if (siteInfo == null) {
			siteInfo = new SiteInfo();
			PersistenceManager pm = PMF.get().getPersistenceManager();
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
		PersistenceManager pm = PMF.get().getPersistenceManager();
		pm.makePersistent(siteInfo);
		pm.close();
		return "/admin/index.jsf";
	}
}
