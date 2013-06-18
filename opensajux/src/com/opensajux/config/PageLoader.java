/**
 * 
 */
package com.opensajux.config;

import org.ocpsoft.rewrite.context.EvaluationContext;
import org.ocpsoft.rewrite.servlet.config.HttpOperation;
import org.ocpsoft.rewrite.servlet.http.event.HttpServletRewrite;

/**
 * @author Sheikh Mohammad Sajid
 * 
 */
public class PageLoader extends HttpOperation {

	private PageLoader() {

	}

	public static PageLoader load() {
		return new PageLoader();
	}

	@Override
	public void performHttp(HttpServletRewrite event, EvaluationContext context) {
		String path = event.getAddress().getPath();
		event.getRequest().setAttribute("path", path.substring(1));
	}

}
