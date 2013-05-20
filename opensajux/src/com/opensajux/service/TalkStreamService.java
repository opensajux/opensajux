package com.opensajux.service;

import java.io.Serializable;
import java.util.List;

import com.opensajux.common.PaginationParameters;
import com.opensajux.entity.TalkStream;

/**
 * @author Sheikh Mohammad Sajid
 * 
 */
public interface TalkStreamService extends Serializable {

	public Long getCount();

	public List<TalkStream> getTalkStreams(PaginationParameters params);
}
