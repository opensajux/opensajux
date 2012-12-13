package com.opensajux.entity;

import java.io.Serializable;

import com.google.appengine.api.datastore.Key;

public class BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	protected Key key;

	public Key getKey() {
		return key;
	}

	@Override
	public String toString() {
		return this.getClass().getName() + "[id=" + this.getKey() + "]";
	}

}
