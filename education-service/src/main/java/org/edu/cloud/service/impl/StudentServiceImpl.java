package org.edu.cloud.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.edu.cloud.dto.Notification;
import org.edu.cloud.dto.StudentRequest;
import org.edu.cloud.dto.StudentResponse;
import org.edu.cloud.entity.Student;
import org.edu.cloud.mapper.StudentMapper;
import org.edu.cloud.repository.StudentRepository;
import org.edu.cloud.service.StudentService;
import org.edu.cloud.util.NotificationSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	private StudentRepository studentRespository;

	@Autowired
	private NotificationSender notificationSender;

	@Override
	public List<StudentResponse> getAll() {
		List<Student> entities = studentRespository.findAll();
		if (CollectionUtils.isEmpty(entities)) {
			return null;
		}
		return entities.parallelStream().map(s -> StudentMapper.of(s)).collect(Collectors.toList());
	}

	@Override
	public StudentResponse getById(Long id) {
		return StudentMapper.of(studentRespository.findById(id).orElse(null));
	}

	@Override
	public StudentResponse save(StudentRequest request) {
		Student entity = StudentMapper.of(request);
		if (entity == null) {
			return null;
		}
		return StudentMapper.of(studentRespository.save(entity));
	}

	@Override
	public StudentResponse update(Long id, StudentRequest request) {
		if (request == null) {
			return null;
		}
		Student entity = studentRespository.findById(id).orElse(null);
		if (entity == null) {
			return null;
		}
		StudentMapper.of(entity, request);
		return StudentMapper.of(studentRespository.save(entity));
	}

	@Override
	public boolean delete(Long id) {
		Student entity = studentRespository.findById(id).orElse(null);
		if (entity == null) {
			return false;
		}
		studentRespository.delete(entity);
		return true;
	}

	@Override
	public String sendNotification() {
		List<String> MobileNo = studentRespository.findMobileNo();
		if (CollectionUtils.isEmpty(MobileNo)) {
			return null;
		}
		notificationSender.sendNotification(Notification.STUDENT, MobileNo);
		return "Start sending";
	}

}
