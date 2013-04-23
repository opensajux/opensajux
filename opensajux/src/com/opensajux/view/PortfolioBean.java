package com.opensajux.view;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.opensajux.entity.Portfolio;
import com.opensajux.service.PortfolioService;

@Named
@RequestScoped
public class PortfolioBean {
	@Inject
	private PortfolioService portfolioService;
	private Portfolio portfolio;

	public Portfolio getPortfolio() {
		if (portfolio == null) {
			portfolio = portfolioService.getPortfolio();
		}
		return portfolio;
	}
}
