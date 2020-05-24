package org.edu.cloud.mapper;

import org.edu.cloud.dto.TeacherRequest;
import org.edu.cloud.dto.TeacherResponse;
import org.edu.cloud.entity.Teacher;

public class TeacherMapper {

	public static Teacher of(TeacherRequest request) {
		if (request == null) {
			return null;
		}
		Teacher response = new Teacher();
		response.setName(request.getName());
		response.setAddress(request.getAddress());
		response.setMobileNo(request.getMobileNo());
		return response;
	}

	public static TeacherResponse of(Teacher request) {
		if (request == null) {
			return null;
		}
		TeacherResponse response = new TeacherResponse();
		response.setId(request.getId());
		response.setName(request.getName());
		response.setAddress(request.getAddress());
		response.setMobileNo(request.getMobileNo());
		response.setCreated(request.getCreated());
		return response;
	}

	public static void of(Teacher response, TeacherRequest request) {
		if (response != null && request != null) {
			response.setName(request.getName());
			response.setAddress(request.getAddress());
			response.setMobileNo(request.getMobileNo());
		}
	}
}
