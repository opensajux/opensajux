/**
 * 
 */
package com.opensajux.jsf.context;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.enterprise.context.NormalScope;

/**
 * @author Sheikh Mohammad Sajid
 * 
 */
@Target(value = { ElementType.METHOD, ElementType.TYPE, ElementType.FIELD })
@Retention(value = RetentionPolicy.RUNTIME)
@NormalScope
@Inherited
public @interface ViewScoped {

}