package org.edu.cloud.notification.config;

import org.springframework.cloud.task.listener.TaskExecutionListener;
import org.springframework.cloud.task.repository.TaskExecution;
import org.springframework.stereotype.Component;


@Component
public class TaskExecutionListenerImpl implements TaskExecutionListener {

	@Override
	public void onTaskStartup(TaskExecution taskExecution) {

	}

	@Override
	public void onTaskEnd(TaskExecution taskExecution) {
		taskExecution.setExitMessage("DONE");
	}

	@Override
	public void onTaskFailed(TaskExecution taskExecution, Throwable throwable) {

	}

}
