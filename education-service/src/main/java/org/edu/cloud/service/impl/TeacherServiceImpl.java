package org.edu.cloud.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.edu.cloud.dto.Notification;
import org.edu.cloud.dto.TeacherRequest;
import org.edu.cloud.dto.TeacherResponse;
import org.edu.cloud.entity.Teacher;
import org.edu.cloud.mapper.TeacherMapper;
import org.edu.cloud.repository.TeacherRepository;
import org.edu.cloud.service.TeacherService;
import org.edu.cloud.util.NotificationSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

@Service
public class TeacherServiceImpl implements TeacherService {

	@Autowired
	private TeacherRepository teacherRespository;

	@Autowired
	private NotificationSender notificationSender;

	@Override
	public List<TeacherResponse> getAll() {
		List<Teacher> entities = teacherRespository.findAll();
		if (CollectionUtils.isEmpty(entities)) {
			return null;
		}
		return entities.parallelStream().map(s -> TeacherMapper.of(s)).collect(Collectors.toList());
	}

	@Override
	public TeacherResponse getById(Long id) {
		return TeacherMapper.of(teacherRespository.findById(id).orElse(null));
	}

	@Override
	public TeacherResponse save(TeacherRequest request) {
		Teacher entity = TeacherMapper.of(request);
		if (entity == null) {
			return null;
		}
		return TeacherMapper.of(teacherRespository.save(entity));
	}

	@Override
	public TeacherResponse update(Long id, TeacherRequest request) {
		if (request == null) {
			return null;
		}
		Teacher entity = teacherRespository.findById(id).orElse(null);
		if (entity == null) {
			return null;
		}
		TeacherMapper.of(entity, request);
		return TeacherMapper.of(teacherRespository.save(entity));
	}

	@Override
	public boolean delete(Long id) {
		Teacher entity = teacherRespository.findById(id).orElse(null);
		if (entity == null) {
			return false;
		}
		teacherRespository.delete(entity);
		return true;
	}

	@Override
	public String sendNotification() {
		List<String> MobileNo = teacherRespository.findMobileNo();
		if (CollectionUtils.isEmpty(MobileNo)) {
			return null;
		}
		notificationSender.sendNotification(Notification.TEACHER, MobileNo);
		return "Start sending";
	}
}
