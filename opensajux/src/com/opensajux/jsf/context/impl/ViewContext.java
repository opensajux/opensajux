/**
 * 
 */
package com.opensajux.jsf.context.impl;

import java.lang.annotation.Annotation;
import java.util.Map;

import javax.enterprise.context.spi.Context;
import javax.enterprise.context.spi.Contextual;
import javax.enterprise.context.spi.CreationalContext;
import javax.enterprise.inject.spi.Bean;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;

import com.opensajux.jsf.context.ViewScoped;
import com.opensajux.jsf.util.ContextualUtils;

/**
 * @author Sheikh Mohammad Sajid
 * 
 */
public class ViewContext implements Context {

	private Map<String, Object> getViewScope() {
		FacesContext fctx = FacesContext.getCurrentInstance();
		UIViewRoot viewRoot = fctx.getViewRoot();
		return viewRoot.getViewMap();
	}

	public Class<? extends Annotation> getScope() {
		return ViewScoped.class;
	}

	public <T> T get(Contextual<T> contextual, CreationalContext<T> creationalContext) {
		T t = get(contextual);
		if (t == null) {
			t = create(contextual, creationalContext);
		}
		return t;
	}

	@SuppressWarnings("unchecked")
	public <T> T get(Contextual<T> contextual) {
		if (!ContextualUtils.isBean(contextual)) {
			throw new IllegalArgumentException();
		} else {
			Bean<T> bean = (Bean<T>) contextual;
			return (T) getViewScope().get(bean.getName());
		}
	}

	public boolean isActive() {
		return true;
	}

	private <T> T create(Contextual<T> contextual, CreationalContext<T> creationalContext) {
		Bean<T> bean = (Bean<T>) contextual;
		T t = bean.create(creationalContext);
		getViewScope().put(bean.getName(), t);
		return t;
	}

}
