package com.opensajux.integration;

import javax.inject.Singleton;

import com.google.api.client.auth.oauth2.BearerToken;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.http.BasicAuthentication;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.plus.Plus;
import com.google.api.services.plus.model.Activity;
import com.google.api.services.plus.model.ActivityFeed;

@Singleton
public class TwitterClient {
	/** Global instance of the HTTP transport. */
	private static final HttpTransport HTTP_TRANSPORT = new NetHttpTransport();

	/** Global instance of the JSON factory. */
	private static final JsonFactory JSON_FACTORY = new JacksonFactory();

	/** Authorizes the installed application to access user's protected data. */
	private static Credential authorize() throws Exception {
		// Set up OAuth 2.0 access of protected resources
		// using the refresh and access tokens, automatically
		// refreshing the access token when it expires
		Credential credential = new Credential.Builder(BearerToken.authorizationHeaderAccessMethod())
				.setJsonFactory(JSON_FACTORY)
				.setTransport(HTTP_TRANSPORT)
				.build();
		// authorize
		return credential;
	}

	public TwitterClient() throws Exception {
		Credential credential = authorize();
		// set up global Plus instance
		Plus plus = new Plus.Builder(HTTP_TRANSPORT, JSON_FACTORY, credential).setApplicationName("Open-Sajux/0.0.1")
				.build();
		Plus.Activities.List listActivities = plus.activities().list("me", "public");
		listActivities.setMaxResults(5L);
		listActivities.setFields("nextPageToken,items(id,url,object/content)");
		ActivityFeed feed = listActivities.execute();
		for (Activity activity : feed.getItems()) {
			System.out.println(activity.getTitle());
		}
	}
}
