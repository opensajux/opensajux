/**
 * 
 */
package com.opensajux.jsf.inject.impl;

import javax.enterprise.inject.Produces;
import javax.faces.context.FacesContext;

/**
 * @author Sheikh Mohammad Sajid
 * 
 */
public class FacesContextProducer {
	@Produces
	public FacesContext getFacesContext() {
		return FacesContext.getCurrentInstance();
	}
}
