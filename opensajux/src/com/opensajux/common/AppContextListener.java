package com.opensajux.common;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.ServiceLoader;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonParser.Feature;
import com.fasterxml.jackson.core.JsonToken;
import com.opensajux.dao.PMF;
import com.opensajux.service.impl.WebletServiceImpl;
import com.opensajux.weblet.Weblet;

/**
 * @author Sheikh Mohammad Sajid
 * 
 */
public class AppContextListener implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent contextEvent) {

	}

	@Override
	public void contextInitialized(ServletContextEvent contextEvent) {
		ServiceLoader<Weblet> webletServiceLoader = ServiceLoader.load(Weblet.class);
		WebletServiceImpl service = new WebletServiceImpl();
		service.setPmf(PMF.getPMF());
		JsonFactory f = new JsonFactory();
		f.enable(Feature.ALLOW_UNQUOTED_FIELD_NAMES);
		for (Weblet weblet : webletServiceLoader) {
			URL url = weblet.getClass().getResource("/META-INF/weblet.json");
			try {
				JsonParser jp = f.createJsonParser(url.openStream());
				jp.nextToken(); // {
				while (jp.nextToken() != JsonToken.END_OBJECT) {
					String fieldname = jp.getCurrentName();
					if (fieldname.equals("weblet")) {
						com.opensajux.entity.Weblet webletEntity = loadWeblet(jp);
						com.opensajux.entity.Weblet existingWeblet = service.getByWebletId(webletEntity.getWebletId());
						if (existingWeblet == null) {
							webletEntity.setActive(true);
							Date date = new Date();
							webletEntity.setCreatedDate(date);
							webletEntity.setUpdatedDate(date);
							service.save(webletEntity);
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private com.opensajux.entity.Weblet loadWeblet(JsonParser jp) throws JsonParseException, IOException {
		com.opensajux.entity.Weblet weblet = new com.opensajux.entity.Weblet();
		jp.nextToken(); // {
		while (jp.nextToken() != JsonToken.END_OBJECT) {
			String fieldname = jp.getCurrentName();
			jp.nextToken();
			if (fieldname.equals("name")) {
				weblet.setWebletId(jp.getText());
			} else if (fieldname.equals("init-param")) {
				loadInitParams(jp);
			}
		}
		return weblet;
	}

	private void loadInitParams(JsonParser jp) throws JsonParseException, IOException {
		while (jp.nextToken() != JsonToken.END_OBJECT) {

		}
	}

}
