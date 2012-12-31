/**
 * 
 */
package com.opensajux.common;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.api.services.blogger.model.Blog;
import com.google.api.services.blogger.model.Post;
import com.google.api.services.blogger.model.PostList;
import com.google.appengine.api.datastore.Text;
import com.opensajux.dao.PMF;
import com.opensajux.entity.BlogPost;
import com.opensajux.entity.MyBlog;
import com.opensajux.integration.BloggerClient;
import com.opensajux.integration.TwitterClient;

/**
 * @author Sheikh Mohammad Sajid
 * 
 */
public class TasksServlet extends HttpServlet {
	private static final long serialVersionUID = 2175074959875861703L;

	private static Logger LOGGER = Logger.getLogger(TasksServlet.class.getName());
	@Inject
	private transient PersistenceManagerFactory pmf;
	@Inject
	BloggerClient bloggerClient;
	TwitterClient twitterClient;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter out = resp.getWriter();
		if (req.getRequestURI().endsWith("blogs")) {
			try {
				PersistenceManager pm = PMF.getPMF().getPersistenceManager();
				Query query = pm.newQuery(BlogPost.class);
				List<BlogPost> blogCache = (List<BlogPost>) query.execute();

				bloggerClient = new BloggerClient();
				query = pm.newQuery(MyBlog.class);
				List<MyBlog> myBlogs = (List<MyBlog>) query.execute();
				for (MyBlog mb : myBlogs) {
					Blog blog = bloggerClient.getBlog(mb.getUrl().getValue());
					PostList posts = bloggerClient.getPostList(blog);
					for (Post p : posts.getItems()) {
						boolean found = false;
						for (BlogPost b : blogCache) {
							if (b.getId().equals(p.getId().toString())) {
								b.setTitle(new Text(p.getTitle()));
								b.setContent(new Text(p.getContent()));
								b.setPublishDate(new Date(p.getPublished().getValue()));
								b.setUpdatedDate(new Date()); // set to today
								b.setSourceUrl(new Text(p.getUrl()));
								found = true;

								pm.makePersistent(b);
							}
						}
						if (!found) { // new post
							BlogPost b = new BlogPost();
							b.setId(p.getId().toString());
							b.setTitle(new Text(p.getTitle()));
							b.setContent(new Text(p.getContent()));
							b.setPublishDate(new Date(p.getPublished().getValue()));
							b.setUpdatedDate(new Date()); // set to today
							b.setSourceUrl(new Text(p.getUrl()));
							b.setSource(SocialSource.BLOGGER);
							b.setBlogKey(mb.getKey());
							pm.makePersistent(b);
						}
					}
				}
				pm.close();
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		} else if (req.getRequestURI().endsWith("talkstream")) {
			try {
				twitterClient = new TwitterClient();
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (req.getRequestURI().endsWith("keepalive")) {
			LOGGER.info("Breathing ...");
		}
	}
}
