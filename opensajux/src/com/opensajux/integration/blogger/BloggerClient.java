package com.opensajux.integration.blogger;

import java.io.Serializable;

import com.google.appengine.api.taskqueue.Queue;
import com.google.appengine.api.taskqueue.QueueFactory;
import com.google.appengine.api.taskqueue.TaskOptions;

public class BloggerClient implements Serializable {
	private static final long serialVersionUID = -5359690231487914747L;

	public BloggerClient() {
		// setupTask();
	}

	private void setupTask() {
		Queue queue = QueueFactory.getQueue("cache");
		queue.add(TaskOptions.Builder.withUrl("/tasks/blogger.do"));
	}
}
