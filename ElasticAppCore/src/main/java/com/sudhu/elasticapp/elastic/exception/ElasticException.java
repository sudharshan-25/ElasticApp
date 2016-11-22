package com.sudhu.elasticapp.elastic.exception;

public class ElasticException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ElasticException(String message) {
		super(message);
	}

	public ElasticException(Exception ex) {
		super(ex);
	}

	public ElasticException(Exception ex, String message) {
		super(message, ex);
	}
}
