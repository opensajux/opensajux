package com.opensajux.common;

import java.io.IOException;

import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import com.opensajux.dao.PMF;

/**
 * Servlet Filter implementation class JDOFilter
 */
public class JDOFilter implements Filter {
	private PersistenceManagerFactory pmf;

	/**
	 * Default constructor.
	 */
	public JDOFilter() {
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		pmf = PMF.getPMF();
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// pmf.close();
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
			ServletException {
		PersistenceManager pm = pmf.getPersistenceManager();
		// pass the request along the filter chain
		request.setAttribute(Constants.JDO_PERSISTENCE_MANAGER, pm);
		chain.doFilter(request, response);
		pm.close();
	}

}
