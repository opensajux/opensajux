package com.opensajux.view;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;

import com.opensajux.entity.Portfolio;

@Named
@RequestScoped
public class PortfolioBean {
	@Inject
	private transient PersistenceManagerFactory pmf;

	private Portfolio portfolio;

	@SuppressWarnings("unchecked")
	public Portfolio getPortfolio() {
		if (portfolio == null) {
			PersistenceManager pm = pmf.getPersistenceManager();
			Query query = pm.newQuery(Portfolio.class);
			List<Portfolio> list = (List<Portfolio>) query.execute();
			portfolio = list.get(0);
			portfolio.getPositions();
			portfolio.getEducations();
			pm.close();
		}
		return portfolio;
	}
}
