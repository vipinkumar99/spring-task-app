package org.edu.cloud.notification.student;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationHistoryDao extends CrudRepository<StudentNotificationHistory, Long> {

}
