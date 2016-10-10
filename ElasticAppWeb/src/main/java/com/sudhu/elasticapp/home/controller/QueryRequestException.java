/**
 * 
 */
package com.sudhu.elasticapp.home.controller;

/**
 * @author sudha
 *
 */
public class QueryRequestException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4188392823280169712L;

	public QueryRequestException(String message) {
		super(message);
	}

	public QueryRequestException(Exception exception) {
		super(exception);
	}

	public QueryRequestException(String message, Exception exception) {
		super(message, exception);
	}

}
