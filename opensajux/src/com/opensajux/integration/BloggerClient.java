package com.opensajux.integration;

import java.io.IOException;
import java.io.Serializable;

import javax.inject.Singleton;

import com.google.api.client.auth.oauth2.BearerToken;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.blogger.Blogger;
import com.google.api.services.blogger.BloggerRequestInitializer;
import com.google.api.services.blogger.model.Blog;
import com.google.api.services.blogger.model.PostList;

@Singleton
public class BloggerClient implements Serializable {
	private static final long serialVersionUID = -5359690231487914747L;

	/** Global instance of the HTTP transport. */
	private static final HttpTransport HTTP_TRANSPORT = new NetHttpTransport();

	/** Global instance of the JSON factory. */
	private static final JsonFactory JSON_FACTORY = new JacksonFactory();

	private transient Blogger blogger;

	/** Authorizes the installed application to access user's protected data. */
	private static Credential authorize() throws Exception {
		// Set up OAuth 2.0 access of protected resources
		// using the refresh and access tokens, automatically
		// refreshing the access token when it expires
		Credential credential = new Credential.Builder(BearerToken.queryParameterAccessMethod())
				.setJsonFactory(JSON_FACTORY).setTransport(HTTP_TRANSPORT)
				.setRequestInitializer(new HttpRequestInitializer() {

					@Override
					public void initialize(HttpRequest arg0) throws IOException {
						// TODO Auto-generated method stub

					}
				}).build();
		// authorize
		return credential;
	}

	public BloggerClient() throws Exception {
		Credential credential = authorize();
		blogger = new Blogger.Builder(HTTP_TRANSPORT, JSON_FACTORY, null).setApplicationName("Open-Sajux/0.0.1")
				.setBloggerRequestInitializer(new BloggerRequestInitializer("AIzaSyC3Qyc_t2flZmLJzEG6LFhoinNUyrzaRuY"))
				.build();
	}

	public Blog getBlog(String url) throws IOException {
		Blog blog = blogger.blogs().getByUrl().setUrl(url).execute();
		return blog;
	}

	public PostList getPostList(Blog blog) throws IOException {
		PostList post = blogger.posts().list(blog.getId().toString()).execute();
		return post;
	}
}
