/**
 * 
 */
package com.opensajux.jsf.context.impl;

import javax.enterprise.event.Observes;
import javax.enterprise.inject.spi.AfterBeanDiscovery;
import javax.enterprise.inject.spi.BeanManager;
import javax.enterprise.inject.spi.Extension;

/**
 * @author Sheikh Mohammad Sajid
 * 
 */
public class ViewContextExtension implements Extension {

	public void afterBeanDiscovery(@Observes AfterBeanDiscovery event, BeanManager manager) {
		event.addContext(new ViewContext());
	}
}
