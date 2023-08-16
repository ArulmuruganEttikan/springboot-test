package com.test.springboot.dto;

public class ExceptionDTO {
	
	private String code;
	private String description;
	private String exceptionType;
	
	public ExceptionDTO() { };
	
	public ExceptionDTO(String code,String description,String exceptionType) {
		this.code = code;
		this.description = description;
		this.exceptionType = exceptionType;
	}
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getExceptionType() {
		return exceptionType;
	}
	public void setExceptionType(String exceptionType) {
		this.exceptionType = exceptionType;
	}

}
