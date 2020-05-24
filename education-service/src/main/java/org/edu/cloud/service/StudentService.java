package org.edu.cloud.service;

import java.util.List;

import org.edu.cloud.dto.StudentRequest;
import org.edu.cloud.dto.StudentResponse;

public interface StudentService {
List<StudentResponse>getAll();
StudentResponse getById(Long id);
StudentResponse save(StudentRequest request);
StudentResponse update(Long id,StudentRequest request);
boolean delete(Long id);
String sendNotification();
}
