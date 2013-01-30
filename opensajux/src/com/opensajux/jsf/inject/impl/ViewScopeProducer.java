/**
 * 
 */
package com.opensajux.jsf.inject.impl;

import java.util.Map;

import javax.enterprise.inject.Produces;
import javax.faces.context.FacesContext;

import com.opensajux.jsf.inject.ViewScope;

/**
 * @author Sheikh Mohammad Sajid
 * 
 */
public class ViewScopeProducer {
	@Produces
	@ViewScope
	public Map<String, Object> getViewScope(FacesContext fctx) {
		return fctx.getViewRoot().getViewMap();
	}
}
