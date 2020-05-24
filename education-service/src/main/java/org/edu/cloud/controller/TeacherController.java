package org.edu.cloud.controller;

import java.util.ArrayList;
import java.util.List;

import org.edu.cloud.dto.BaseResponse;
import org.edu.cloud.dto.TeacherRequest;
import org.edu.cloud.dto.TeacherResponse;
import org.edu.cloud.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/teachers")
public class TeacherController {

	@Autowired
	private TeacherService teacherService;

	@PostMapping("/save")
	public BaseResponse<TeacherResponse> save(@RequestBody TeacherRequest request) {
		TeacherResponse response = teacherService.save(request);
		if (response == null) {
			return new BaseResponse<>(null, "Fail");
		}
		return new BaseResponse<>(response);
	}

	@PatchMapping("/{id}")
	public BaseResponse<TeacherResponse> update(@PathVariable(name = "id") Long id,
			@RequestBody TeacherRequest request) {
		TeacherResponse response = teacherService.update(id, request);
		if (response == null) {
			return new BaseResponse<>(null, "Fail");
		}
		return new BaseResponse<>(response);
	}

	@GetMapping("/all")
	public BaseResponse<List<TeacherResponse>> getAll() {
		List<TeacherResponse> response = teacherService.getAll();
		if (response == null) {
			return new BaseResponse<>(new ArrayList<>(), "Fail");
		}
		return new BaseResponse<>(response);
	}

	@GetMapping("/{id}")
	public BaseResponse<TeacherResponse> getById(@PathVariable("id") Long id) {
		TeacherResponse response = teacherService.getById(id);
		if (response == null) {
			return new BaseResponse<>(null, "Fail");
		}
		return new BaseResponse<>(response);
	}

	@DeleteMapping("/{id}")
	public BaseResponse<Boolean> delete(@PathVariable("id") Long id) {
		boolean response = teacherService.delete(id);
		return new BaseResponse<>(response);
	}

	@GetMapping("/sendNotification")
	public BaseResponse<String> sendNotification() {
		String response = teacherService.sendNotification();
		if (response == null) {
			return new BaseResponse<>(null, "Fail");
		}
		return new BaseResponse<>(response);
	}
}
