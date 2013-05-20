package com.opensajux.view;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.opensajux.common.PaginationParameters;
import com.opensajux.entity.TalkStream;
import com.opensajux.service.TalkStreamService;

@RequestScoped
@Named
public class TalkStreamBean implements Serializable {
	private static final long serialVersionUID = 8879513787369863264L;

	@Inject
	private TalkStreamService talkStreamService;
	private LazyDataModel<TalkStream> streamModel;

	public TalkStreamBean() {
	}

	public LazyDataModel<TalkStream> getStream() {
		if (streamModel == null) {
			streamModel = new LazyDataModel<TalkStream>() {
				private static final long serialVersionUID = -4213392862978853235L;

				@Override
				public int getRowCount() {
					return talkStreamService.getCount().intValue();
				}

				@Override
				public List<TalkStream> load(int first, int pageSize, String sortField, SortOrder sortOrder,
						Map<String, String> filters) {
					PaginationParameters param = new PaginationParameters();
					param.setFirst(first);
					param.setPageSize(pageSize);
					param.setSortField(sortField == null ? "updatedDate" : sortField);
					param.setSortOrder(sortOrder == null ? "desc" : sortOrder == SortOrder.ASCENDING ? "asc" : "desc");

					return talkStreamService.getTalkStreams(param);
				}
			};
			streamModel.setPageSize(5);
		}
		return streamModel;
	}
}
