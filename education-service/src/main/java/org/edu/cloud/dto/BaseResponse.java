package org.edu.cloud.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BaseResponse<T> {
	private boolean error;
	private T data;
	private String message;

	public BaseResponse(T data, String message) {
		this(false, data, message);
	}

	public BaseResponse(T data) {
		this(false, data, "Success");
	}

}
