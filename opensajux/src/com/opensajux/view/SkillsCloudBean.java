package com.opensajux.view;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.jdo.PersistenceManagerFactory;

import org.primefaces.model.tagcloud.DefaultTagCloudItem;
import org.primefaces.model.tagcloud.DefaultTagCloudModel;
import org.primefaces.model.tagcloud.TagCloudModel;

@Named
@RequestScoped
public class SkillsCloudBean {
	@Inject
	private transient PersistenceManagerFactory pmf;

	private TagCloudModel skillsCloudModel;

	public TagCloudModel getCloud() {
		if (skillsCloudModel == null) {
			skillsCloudModel = new DefaultTagCloudModel();
			skillsCloudModel.addTag(new DefaultTagCloudItem("Java", 1));
			skillsCloudModel.addTag(new DefaultTagCloudItem("JavaEE", 1));
			skillsCloudModel.addTag(new DefaultTagCloudItem("Spring", 1));
			skillsCloudModel.addTag(new DefaultTagCloudItem("Hibernate", 1));
			skillsCloudModel.addTag(new DefaultTagCloudItem("Struts", 1));
			skillsCloudModel.addTag(new DefaultTagCloudItem("JSF", 1));
			skillsCloudModel.addTag(new DefaultTagCloudItem("Liferay", 1));
			skillsCloudModel.addTag(new DefaultTagCloudItem("Google AppEngine", 1));
		}
		return skillsCloudModel;
	}
}
