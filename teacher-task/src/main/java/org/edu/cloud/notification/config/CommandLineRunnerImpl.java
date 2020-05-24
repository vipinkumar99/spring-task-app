package org.edu.cloud.notification.config;

import java.util.logging.Logger;

import org.edu.cloud.notification.teacher.NotificationHistoryDao;
import org.edu.cloud.notification.teacher.TeacherNotificationHistory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CommandLineRunnerImpl implements CommandLineRunner {
	final Logger logger = Logger.getLogger(CommandLineRunnerImpl.class.getName());

	@Autowired
	private NotificationHistoryDao historyDao;

	@Override
	public void run(String... args) throws Exception {
		if (args != null && args.length > 0) {
			long count = 0;
			for (String mobileNo : args) {
				historyDao.save(new TeacherNotificationHistory(mobileNo));
				count++;
			}
			logger.info("total notification send to teacher:" + count);
		} else {
			logger.info("mobileNos args is null");
		}

	}
}
