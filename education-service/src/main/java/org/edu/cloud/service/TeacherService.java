package org.edu.cloud.service;

import java.util.List;

import org.edu.cloud.dto.TeacherRequest;
import org.edu.cloud.dto.TeacherResponse;

public interface TeacherService {
	List<TeacherResponse>getAll();
	TeacherResponse getById(Long id);
	TeacherResponse save(TeacherRequest request);
	TeacherResponse update(Long id,TeacherRequest request);
	boolean delete(Long id);
	String sendNotification();
}
