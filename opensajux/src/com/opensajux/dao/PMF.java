package com.opensajux.dao;

import java.util.logging.Logger;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManagerFactory;

public final class PMF {
	private static final PersistenceManagerFactory pmfInstance = JDOHelper
			.getPersistenceManagerFactory("transactions-optional");
	Logger log = Logger.getLogger(PMF.class.getName());

	private PMF() {
	}

	public static PersistenceManagerFactory get() {
		return pmfInstance;
	}
}
