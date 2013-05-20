package com.opensajux.view;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.opensajux.entity.Education;
import com.opensajux.entity.Portfolio;
import com.opensajux.entity.Position;
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

	public List<Position> getPositions() {
		List<Position> positions = null;
		if (portfolio != null) {
			positions = new ArrayList<>(portfolio.getPositions());
			Collections.sort(positions, new Comparator<Position>() {
				@Override
				public int compare(Position p1, Position p2) {
					return p2.getStartDate().compareTo(p1.getStartDate());
				}
			});
		}
		return positions;
	}

	public List<Education> getEducations() {
		List<Education> educations = null;
		if (portfolio != null) {
			educations = new ArrayList<>(portfolio.getEducations());
			Collections.sort(educations, new Comparator<Education>() {
				@Override
				public int compare(Education p1, Education p2) {
					return p2.getStartDate().compareTo(p1.getStartDate());
				}
			});
		}
		return educations;
	}
}
