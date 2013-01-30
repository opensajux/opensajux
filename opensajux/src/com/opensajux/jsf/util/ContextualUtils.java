/**
 * 
 */
package com.opensajux.jsf.util;

import javax.enterprise.context.spi.Contextual;
import javax.enterprise.inject.spi.Bean;

/**
 * @author Sheikh Mohammad Sajid
 *
 */
public class ContextualUtils {
	public static <T> boolean isBean(Contextual<T> contextual) {
        if (contextual instanceof Bean) {
            return true;
        }
        return false;
    }
}
