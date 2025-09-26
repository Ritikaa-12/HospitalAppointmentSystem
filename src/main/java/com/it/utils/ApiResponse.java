package com.it.utils;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class ApiResponse {
  private Boolean status;
  private String message;
  private String errorMessage;
  private Object data;
  
  public ApiResponse(Boolean status, String message, Object data) {
	super();
	this.status = status;
	this.message = message;
	this.data = data;
  }

  public ApiResponse(Boolean status, String errorMessage) {
	super();
	this.status = status;
	this.errorMessage = errorMessage;
  }
  
  
}
