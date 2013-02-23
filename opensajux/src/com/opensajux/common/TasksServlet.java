/**
 * 
 */
package com.opensajux.common;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.social.linkedin.api.Education;
import org.springframework.social.linkedin.api.LinkedInProfileFull;
import org.springframework.social.linkedin.api.Position;

import twitter4j.Status;

import com.google.api.services.blogger.model.Blog;
import com.google.api.services.blogger.model.Post;
import com.google.api.services.blogger.model.PostList;
import com.google.api.services.plus.model.Activity;
import com.google.appengine.api.datastore.Text;
import com.opensajux.dao.PMF;
import com.opensajux.dto.FacebookStatusMessage;
import com.opensajux.dto.SiteDetails;
import com.opensajux.entity.BlogPost;
import com.opensajux.entity.MyBlog;
import com.opensajux.entity.Portfolio;
import com.opensajux.entity.SiteInfo;
import com.opensajux.entity.TalkStream;
import com.opensajux.integration.BloggerClient;
import com.opensajux.integration.FacebookClient;
import com.opensajux.integration.LinkedInClient;
import com.opensajux.integration.PlusClient;
import com.opensajux.integration.TwitterClient;

/**
 * @author Sheikh Mohammad Sajid
 * 
 */
public class TasksServlet extends HttpServlet {
	private static final long serialVersionUID = 2175074959875861703L;

	private static Logger LOGGER = Logger.getLogger(TasksServlet.class.getName());

	@SuppressWarnings("unchecked")
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter out = resp.getWriter();
		BloggerClient bloggerClient;
		PlusClient plusClient;
		PersistenceManager pm = PMF.getPMF().getPersistenceManager();
		SiteInfo siteInfo = ((List<SiteInfo>) pm.newQuery(SiteInfo.class).execute()).get(0);
		SiteDetails siteDetails = new SiteDetails();
		siteDetails.setAboutMe(siteInfo.getAboutMe().getValue());
		siteDetails.setTitle(siteInfo.getTitle());
		siteDetails.setSubTitle(siteInfo.getSubTitle());
		siteDetails.setGoogleUserId(siteInfo.getGoogleUserId());
		siteDetails.setGoogleApiKey(siteInfo.getGoogleApiKey());
		siteDetails.setTwitterUsername(siteInfo.getTwitterUsername());
		siteDetails.setTwitterAccessToken(siteInfo.getTwitterAccessToken());
		siteDetails.setTwitterAccessTokenSecret(siteInfo.getTwitterAccessTokenSecret());
		siteDetails.setTwitterConsumerKey(siteInfo.getTwitterConsumerKey());
		siteDetails.setTwitterConsumerSecret(siteInfo.getTwitterConsumerSecret());
		siteDetails.setFacebookUsername(siteInfo.getFacebookUsername());
		siteDetails.setFacebookAppId(siteInfo.getFacebookAppId());
		siteDetails.setFacebookAppSecret(siteInfo.getFacebookAppSecret());
		siteDetails.setFacebookAccessToken(siteInfo.getFacebookAccessToken());
		siteDetails.setLinkedinUsername(siteInfo.getLinkedinUsername());
		siteDetails.setLinkedinApiKey(siteInfo.getLinkedinApiKey());
		siteDetails.setLinkedinSecretKey(siteInfo.getLinkedinSecretKey());
		siteDetails.setLinkedinUserSecret(siteInfo.getLinkedinUserSecret());
		siteDetails.setLinkedinUserToken(siteInfo.getLinkedinUserToken());

		if (req.getRequestURI().endsWith("blogs")) {
			try {
				Query query = pm.newQuery(BlogPost.class);
				List<BlogPost> blogCache = (List<BlogPost>) query.execute();

				bloggerClient = new BloggerClient(siteDetails);
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
				out.println("Blogs: Success");
			} catch (Exception e) {
				LOGGER.log(Level.SEVERE, e.getMessage(), e);
				out.println("Blogs: Error");
			} finally {
				pm.close();
			}
		} else if (req.getRequestURI().endsWith("talkstream")) {
			Query query = pm.newQuery(TalkStream.class);
			List<TalkStream> talkCache = (List<TalkStream>) query.execute();
			try {
				// check Google Plus
				plusClient = new PlusClient(siteDetails);
				for (Activity act : plusClient.getActivities(siteDetails.getGoogleUserId())) {
					boolean found = false;
					for (TalkStream talk : talkCache) {
						if (act.getId().equals(talk.getId())) {
							found = true;
							if ("".equals(act.getObject().getContent())) {
								if (act.getObject().getAttachments() != null
										&& act.getObject().getAttachments().size() > 0) {
									talk.setTitle(new Text(act.getObject().getAttachments().get(0).getDisplayName()));
									talk.setContent(new Text(act.getObject().getAttachments().get(0).getContent()));
								}
							}
							talk.setPublishDate(new Date(act.getPublished().getValue()));
							talk.setUpdatedDate(new Date(act.getUpdated().getValue()));
							talk.setSource(SocialSource.GOOGLE_PLUS);
							talk.setSourceUrl(new Text(act.getUrl()));
							pm.makePersistent(talk);
						}
					}
					if (!found && act.getVerb().equalsIgnoreCase("post")) {
						TalkStream talk = new TalkStream();
						talk.setId(act.getId());
						if ("".equals(act.getObject().getContent())) {
							if (act.getObject().getAttachments() != null && act.getObject().getAttachments().size() > 0) {
								talk.setTitle(new Text(act.getObject().getAttachments().get(0).getDisplayName()));
								talk.setContent(new Text(act.getObject().getAttachments().get(0).getContent()));
							}
						}
						talk.setPublishDate(new Date(act.getPublished().getValue()));
						talk.setUpdatedDate(new Date(act.getUpdated().getValue()));
						talk.setSource(SocialSource.GOOGLE_PLUS);
						talk.setSourceUrl(new Text(act.getUrl()));
						try {
							pm.makePersistent(talk);
						} catch (Exception e) {
							// TODO Debug why this is being generated? Some
							// content is null which is incorrect.
							LOGGER.log(Level.SEVERE, e.getMessage(), e);
						}
					}
				}
				out.println("Plus: Success");
			} catch (Exception e) {
				LOGGER.log(Level.SEVERE, e.getMessage(), e);
				out.println("Plus: Error");
			}
			try {
				// check Twitter
				TwitterClient twitterClient = new TwitterClient(siteDetails);
				List<Status> list = twitterClient.getUserStatuses(siteDetails.getTwitterUsername());
				for (Status s : list) {
					boolean found = false;
					for (TalkStream talk : talkCache) {
						if (talk.getId().equals("" + s.getId())) {
							found = true;
							talk.setTitle(new Text(s.getText()));
							talk.setPublishDate(s.getCreatedAt());
							talk.setUpdatedDate(s.getCreatedAt());
							talk.setSource(SocialSource.TWITTER);
							talk.setSourceUrl(new Text("https://twitter.com/" + siteDetails.getTwitterUsername()
									+ "/status/" + s.getId()));
							pm.makePersistent(talk);
						}
					}
					if (!found) {
						TalkStream talk = new TalkStream();
						talk.setId(s.getId() + "");
						talk.setTitle(new Text(s.getText()));
						talk.setPublishDate(s.getCreatedAt());
						talk.setUpdatedDate(s.getCreatedAt());
						talk.setSource(SocialSource.TWITTER);
						talk.setSourceUrl(new Text("https://twitter.com/" + siteDetails.getTwitterUsername()
								+ "/status/" + s.getId()));
						pm.makePersistent(talk);
					}
				}
				out.println("Twitter: Success");
			} catch (Exception e) {
				LOGGER.log(Level.SEVERE, e.getMessage(), e);
				out.println("Twitter: Error");
			}
			try {
				// check facebook
				FacebookClient facebookClient = new FacebookClient(siteDetails);
				List<FacebookStatusMessage> statuses = facebookClient.getUserPosts(siteDetails.getFacebookUsername());
				if (statuses != null) {
					for (FacebookStatusMessage sm : statuses) {
						boolean found = false;
						for (TalkStream talk : talkCache) {
							if (talk.getId().equals(sm.getId())) {
								found = true;
								talk.setTitle(new Text(sm.getMessage()));
								talk.setPublishDate(sm.getUpdatedTime());
								talk.setSource(SocialSource.FACEBOOK);
								talk.setSourceUrl(new Text("https://www.facebook.com/"
										+ siteDetails.getFacebookUsername() + "/posts/" + sm.getUpdatedTime().getTime()));
								pm.makePersistent(talk);
							}
						}
						if (!found) {
							TalkStream talk = new TalkStream();
							talk.setId(sm.getId());
							talk.setTitle(new Text(sm.getMessage()));
							talk.setPublishDate(sm.getUpdatedTime());
							talk.setSource(SocialSource.FACEBOOK);
							talk.setSourceUrl(new Text("https://www.facebook.com/" + siteDetails.getFacebookUsername()
									+ "/posts/" + sm.getUpdatedTime().getTime()));
							pm.makePersistent(talk);
						}
					}
				}
				out.println("Facebook: Success");
			} catch (Exception e) {
				LOGGER.log(Level.SEVERE, e.getMessage(), e);
				out.println("Facebook: Error");
			} finally {
				pm.close();
			}
		} else if (req.getRequestURI().endsWith("portfolio")) {
			try {
				Query query = pm.newQuery(Portfolio.class);
				List<Portfolio> portfolios = (List<Portfolio>) query.execute();
				Portfolio portfolio = null;
				if (portfolios == null || portfolios.size() == 0) {
					portfolio = new Portfolio();
				} else {
					portfolio = portfolios.get(0);
				}

				LinkedInClient linkedinClient = new LinkedInClient(siteDetails);
				LinkedInProfileFull me = linkedinClient.getMyProfile();
				portfolio.setId(me.getId());
				if (me.getFirstName() != null)
					portfolio.setFirstName(me.getFirstName());
				if (me.getLastName() != null)
					portfolio.setLastName(me.getLastName());
				if (me.getDateOfBirth() != null) {
					Calendar cal = Calendar.getInstance();
					cal.set(me.getDateOfBirth().getYear(), me.getDateOfBirth().getMonth(),
							me.getDateOfBirth().getDay(), 0, 0, 0);
					portfolio.setDob(cal.getTime());
				}
				if (me.getMainAddress() != null)
					portfolio.setAddress(new Text(me.getMainAddress()));
				if (me.getHeadline() != null)
					portfolio.setHeadline(new Text(me.getHeadline()));
				if (me.getSummary() != null)
					portfolio.setSummary(new Text(me.getSummary().replaceAll("\n", "<br/>")));
				if (me.getIndustry() != null)
					portfolio.setIndustry(me.getIndustry());
				if (me.getInterests() != null)
					portfolio.setInterests(me.getInterests());
				if (me.getLocation() != null) {
					String location = null;
					if (me.getLocation().getName() != null)
						location = me.getLocation().getName();
					if (location == null && me.getLocation().getCountry() != null)
						location = me.getLocation().getCountry();
					else
						location += ", " + me.getLocation().getCountry();
					portfolio.setLocation(location);
				}
				portfolio.setUpdatedDate(new Date());

				pm.makePersistent(portfolio);

				if (me.getPositions() != null && me.getPositions().size() > 0) {
					List<com.opensajux.entity.Position> positions = new ArrayList<com.opensajux.entity.Position>();
					for (Position pos : me.getPositions()) {
						boolean exists = false;
						com.opensajux.entity.Position localPos = null;
						for (com.opensajux.entity.Position p : portfolio.getPositions()) {
							if (pos.getId().equals(p.getId())) {
								localPos = p;
								exists = true;
								break;
							}
						}
						if (!exists) {
							localPos = new com.opensajux.entity.Position();
							localPos.setId(pos.getId());
							localPos.setPortfolio(portfolio);
						}
						if (pos.getCompany() != null)
							localPos.setCompanyName(pos.getCompany().getName());
						localPos.setCurrent(pos.getIsCurrent());
						Calendar cal = Calendar.getInstance();
						if (pos.getEndDate() != null) {
							cal.set(pos.getEndDate().getYear(), pos.getEndDate().getMonth(), pos.getEndDate().getDay(),
									0, 0, 0);
							localPos.setEndDate(cal.getTime());
						}
						if (pos.getStartDate() != null) {
							cal.set(pos.getStartDate().getYear(), pos.getStartDate().getMonth(), pos.getStartDate()
									.getDay(), 0, 0, 0);
							localPos.setStartDate(cal.getTime());
						}
						if (pos.getSummary() != null)
							localPos.setSummary(new Text(pos.getSummary().replaceAll("\n",	"<br />")));
						if (pos.getTitle() != null)
							localPos.setTitle(pos.getTitle());
						positions.add(localPos);
					}
					pm.makePersistentAll(positions);
					portfolio.setPositions(positions);
					pm.makePersistent(portfolio);
				}

				if (me.getEducations() != null && me.getEducations().size() > 0) {
					List<com.opensajux.entity.Education> educations = new ArrayList<com.opensajux.entity.Education>();
					for (Education edu : me.getEducations()) {
						boolean exists = false;
						com.opensajux.entity.Education localEdu = null;
						for (com.opensajux.entity.Education ed : portfolio.getEducations()) {
							if (edu.getId().equals(ed.getId())) {
								localEdu = ed;
								exists = true;
								break;
							}
						}
						if (!exists) {
							localEdu = new com.opensajux.entity.Education();
							localEdu.setId(edu.getId());
							localEdu.setPortfolio(portfolio);
						}
						if (edu.getActivities() != null)
							localEdu.setActivities(new Text(edu.getActivities()));
						if (edu.getDegree() != null)
							localEdu.setDegree(edu.getDegree());
						Calendar cal = Calendar.getInstance();
						if (edu.getStartDate() != null) {
							cal.set(edu.getStartDate().getYear(), edu.getStartDate().getMonth(), edu.getStartDate()
									.getDay(), 0, 0, 0);
							localEdu.setStartDate(cal.getTime());
						}
						if (edu.getEndDate() != null) {
							cal.set(edu.getEndDate().getYear(), edu.getEndDate().getMonth(), edu.getEndDate().getDay(),
									0, 0, 0);
							localEdu.setEndDate(cal.getTime());
						}
						if (edu.getFieldOfStudy() != null)
							localEdu.setFieldOfStudy(edu.getFieldOfStudy());
						if (edu.getNotes() != null)
							localEdu.setNotes(new Text(edu.getNotes()));
						if (edu.getSchoolName() != null)
							localEdu.setSchoolName(edu.getSchoolName());

						educations.add(localEdu);
					}
					pm.makePersistentAll(educations);
					portfolio.setEducations(educations);
					pm.makePersistent(portfolio);
				}

				out.println("Portfolio: Success");
			} catch (Exception e) {
				LOGGER.log(Level.SEVERE, e.getMessage(), e);
				out.println("Portfolio: Error");
			} finally {
				pm.close();
			}

		} else if (req.getRequestURI().endsWith("keepalive")) {
			LOGGER.info("Breathing ...");
		}
	}
}
