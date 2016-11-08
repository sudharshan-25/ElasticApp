package com.sudhu.elasticapp.home.form;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author sudha
 *
 */
public class AbstractResponseVO {

	@JsonProperty("error")
	private String errorMessage;

	@JsonProperty("success")
	private String message;

	@JsonProperty("data")
	private Object data;

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
}
