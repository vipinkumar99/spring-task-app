package org.edu.cloud.util;

import java.util.List;

import org.edu.cloud.dto.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.cloud.task.launcher.TaskLaunchRequest;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.stereotype.Component;

@Component
@EnableBinding(Source.class)
public class NotificationSender {

	@Autowired
	private Source source;

	private final String studentNotificationUrl = "maven://com.task.cloud:student-task:jar:0.0.1-SNAPSHOT";
	private final String teacherNotificationUrl = "maven://com.task.cloud:teacher-task:jar:0.0.1-SNAPSHOT";

	public void sendNotification(Notification notification, List<String> mobileNoList) {
		TaskLaunchRequest request = null;
		switch (notification) {
		case TEACHER:
			request = new TaskLaunchRequest(teacherNotificationUrl, mobileNoList, null, null, null);
			break;
		case STUDENT:
			request = new TaskLaunchRequest(studentNotificationUrl, mobileNoList, null, null, null);
			break;
		default:
			break;
		}
		if (request != null) {
			GenericMessage<TaskLaunchRequest> message = new GenericMessage<>(request);
			source.output().send(message);
		}
	}

}
