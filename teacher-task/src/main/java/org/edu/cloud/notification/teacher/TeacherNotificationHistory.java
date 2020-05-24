package org.edu.cloud.notification.teacher;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "teacherNotificationHistories", schema = "education")
public class TeacherNotificationHistory {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String mobileNo;
	@CreationTimestamp
	private Date created;

	public TeacherNotificationHistory(String mobileNo) {
		super();
		this.mobileNo = mobileNo;
	}

}
