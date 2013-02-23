/**
 * 
 */
package com.opensajux.service;

import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.jdo.PersistenceManagerFactory;

/**
 * @author Sheikh Mohammad Sajid
 * 
 */
@ApplicationScoped
public class PortfolioService implements Serializable {
	private static final long serialVersionUID = -5484094961332737765L;

	@Inject
	private transient PersistenceManagerFactory pmf;

}
