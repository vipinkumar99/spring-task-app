package org.edu.cloud.mapper;

import org.edu.cloud.dto.StudentRequest;
import org.edu.cloud.dto.StudentResponse;
import org.edu.cloud.entity.Student;

public class StudentMapper {
	public static Student of(StudentRequest request) {
		if (request == null) {
			return null;
		}
		Student response = new Student();
		response.setFirstName(request.getFirstName());
		response.setLastName(request.getLastName());
		response.setAddress(request.getAddress());
		response.setMobileNo(request.getMobileNo());
		return response;
	}

	public static StudentResponse of(Student request) {
		if (request == null) {
			return null;
		}
		StudentResponse response = new StudentResponse();
		response.setId(request.getId());
		response.setFirstName(request.getFirstName());
		response.setLastName(request.getLastName());
		response.setAddress(request.getAddress());
		response.setMobileNo(request.getMobileNo());
		response.setCreated(request.getCreated());
		return response;
	}

	public static void of(Student response, StudentRequest request) {
		if (response != null && request != null) {
			response.setFirstName(request.getFirstName());
			response.setLastName(request.getLastName());
			response.setAddress(request.getAddress());
			response.setMobileNo(request.getMobileNo());
		}
	}
}
