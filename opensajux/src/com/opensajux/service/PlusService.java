package com.opensajux.service;

import java.io.Serializable;

/**
 * @author Sheikh Mohammad Sajid
 * 
 */
public interface PlusService extends Serializable {

	public void saveBlog(String url);

	public void removeBlog(String id);
}
