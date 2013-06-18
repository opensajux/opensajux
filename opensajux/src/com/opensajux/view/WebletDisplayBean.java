package com.opensajux.view;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named
@RequestScoped
public class WebletDisplayBean {

	private List<WebletDisplay> weblets;

	public WebletDisplayBean() {
		weblets = new ArrayList<>();
		WebletDisplay display = new WebletDisplay();
		display.setWebletId("w1");
		display.setDisplayName("Web Content Display");
		weblets.add(display);

		display = new WebletDisplay();
		display.setWebletId("w2");
		display.setDisplayName("Twitter");
		weblets.add(display);

		display = new WebletDisplay();
		display.setWebletId("w3");
		display.setDisplayName("Wiki");
		weblets.add(display);

		display = new WebletDisplay();
		display.setWebletId("w4");
		display.setDisplayName("Messages");
		weblets.add(display);
	}

	/**
	 * @return the weblets
	 */
	public List<WebletDisplay> getWeblets() {
		return weblets;
	}

	/**
	 * @param weblets
	 *            the weblets to set
	 */
	public void setWeblets(List<WebletDisplay> weblets) {
		this.weblets = weblets;
	}
}
