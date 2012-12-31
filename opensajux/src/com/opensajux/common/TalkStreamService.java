package com.opensajux.common;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.opensajux.entity.TalkStream;

@Singleton
@Named(value = "talkStream")
public class TalkStreamService {
	@Inject
	private transient PersistenceManagerFactory pmf;
	private LazyDataModel<TalkStream> streamModel;

	public TalkStreamService() {
		streamModel = new LazyDataModel<TalkStream>() {
			private static final long serialVersionUID = -4213392862978853235L;

			@Override
			public int getRowCount() {
				PersistenceManager pm = pmf.getPersistenceManager();
				Long count = (Long) pm.newQuery("select count(key) from " + TalkStream.class.getName()).execute();
				pm.close();
				return count.intValue();
			}

			@Override
			public List<TalkStream> load(int first, int pageSize, String sortField, SortOrder sortOrder,
					Map<String, String> filters) {
				PersistenceManager pm = pmf.getPersistenceManager();
				Query query = pm.newQuery("select from " + TalkStream.class.getName());
				List<TalkStream> stream = (List<TalkStream>) query.execute();
				pm.close();
				return stream;
			}
		};
		streamModel.setPageSize(5);
	}

	public LazyDataModel<TalkStream> getStream() {
		return streamModel;
	}
}
