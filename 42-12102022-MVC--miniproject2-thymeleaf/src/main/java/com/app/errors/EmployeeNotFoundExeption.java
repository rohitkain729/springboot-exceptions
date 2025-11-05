package com.app.errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_GATEWAY)
public class EmployeeNotFoundExeption extends Exception {
	public EmployeeNotFoundExeption() {
		super();
	}

	public EmployeeNotFoundExeption(String message) {
		super(message);

	}

}
