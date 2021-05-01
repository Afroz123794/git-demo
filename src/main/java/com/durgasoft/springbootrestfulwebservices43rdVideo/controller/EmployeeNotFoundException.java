package com.durgasoft.springbootrestfulwebservices43rdVideo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class EmployeeNotFoundException extends RuntimeException {
  
	private static final long serialVersionUID = 1L;
	
	pubic EmployeeNotFoundException()
	{
		System.out.println("Default constructor");
	}

	public EmployeeNotFoundException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}


	

}
