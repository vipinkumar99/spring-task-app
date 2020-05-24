package org.edu.cloud.controller;

import java.util.ArrayList;
import java.util.List;

import org.edu.cloud.dto.BaseResponse;
import org.edu.cloud.dto.StudentRequest;
import org.edu.cloud.dto.StudentResponse;
import org.edu.cloud.service.StudentService;
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
@RequestMapping("/students")
public class StudentController {

	@Autowired
	private StudentService studentService;

	@PostMapping("/save")
	public BaseResponse<StudentResponse> save(@RequestBody StudentRequest request) {
		StudentResponse response = studentService.save(request);
		if (response == null) {
			return new BaseResponse<>(null, "Fail");
		}
		return new BaseResponse<>(response);
	}

	@PatchMapping("/{id}")
	public BaseResponse<StudentResponse> update(@PathVariable(name = "id") Long id,
			@RequestBody StudentRequest request) {
		StudentResponse response = studentService.update(id, request);
		if (response == null) {
			return new BaseResponse<>(null, "Fail");
		}
		return new BaseResponse<>(response);
	}

	@GetMapping("/all")
	public BaseResponse<List<StudentResponse>> getAll() {
		List<StudentResponse> response = studentService.getAll();
		if (response == null) {
			return new BaseResponse<>(new ArrayList<>(), "Fail");
		}
		return new BaseResponse<>(response);
	}

	@GetMapping("/{id}")
	public BaseResponse<StudentResponse> getById(@PathVariable("id") Long id) {
		StudentResponse response = studentService.getById(id);
		if (response == null) {
			return new BaseResponse<>(null, "Fail");
		}
		return new BaseResponse<>(response);
	}

	@DeleteMapping("/{id}")
	public BaseResponse<Boolean> delete(@PathVariable("id") Long id) {
		boolean response = studentService.delete(id);
		return new BaseResponse<>(response);
	}

	@GetMapping("/sendNotification")
	public BaseResponse<String> sendNotification() {
		String response = studentService.sendNotification();
		if (response == null) {
			return new BaseResponse<>(null, "Fail");
		}
		return new BaseResponse<>(response);
	}
}
