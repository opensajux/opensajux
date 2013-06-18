/**
 * 
 */
package com.opensajux.config;

import javax.servlet.ServletContext;

import org.ocpsoft.rewrite.config.Configuration;
import org.ocpsoft.rewrite.config.ConfigurationBuilder;
import org.ocpsoft.rewrite.config.Direction;
import org.ocpsoft.rewrite.context.EvaluationContext;
import org.ocpsoft.rewrite.event.Rewrite;
import org.ocpsoft.rewrite.param.Validator;
import org.ocpsoft.rewrite.servlet.config.Forward;
import org.ocpsoft.rewrite.servlet.config.HttpConfigurationProvider;
import org.ocpsoft.rewrite.servlet.config.Path;

import com.opensajux.common.ApplicationCacheProvider;

/**
 * @author Sheikh Mohammad Sajid
 * 
 */
public class ApplicationConfigurationProvider extends HttpConfigurationProvider {

	@Override
	public Configuration getConfiguration(final ServletContext context) {
		return ConfigurationBuilder.begin().addRule().when(Direction.isInbound().and(Path.matches("/{path}")))
				.perform(PageLoader.load().and(Forward.to("/webletPage.jsf"))).where("path")
				.validatedBy(new Validator<String>() {
					@Override
					public boolean isValid(Rewrite event, EvaluationContext evcontext, String value) {
						if (ApplicationCacheProvider.get().getURLCache(context).get(value) != null) {
							return true;
						}
						return false;
					}
				});
	}

	@Override
	public int priority() {
		return 0;
	}

}
