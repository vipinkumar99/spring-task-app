package org.edu.cloud.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentResponse extends StudentRequest {
	private Long id;
	@JsonFormat(shape = Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss", timezone = "IST")
	private Date created;
}
