package org.edu.cloud.notification.teacher;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationHistoryDao extends CrudRepository<TeacherNotificationHistory, Long> {

}
